/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.strategy;

import com.virtualfactory.data.GameData;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Bucket;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.entity.E_Part;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.entity.E_TransportStore;
import com.virtualfactory.exceptions.ExceededCapacityException;
import com.virtualfactory.exceptions.InsufficientPartsException;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeActivity;
import com.virtualfactory.utils.TypeElements;
import java.util.ArrayList;
/**
 *
 * 
 */
public class StoreStrategy implements EventStrategy {
//    private String validateMessage = "";
//    private String executeMessage = "";
//    private String releaseMessage = "";
    
    private E_TransportStore dataStore;
    private E_Operator curOperator;
    private E_Machine curMachine;
    private E_Station fromStation;
    private E_Station toStation;
    private Pair<Integer,Integer> fromStationLocation;
    private Pair<Integer,Integer> toStationLocation;
//    private boolean validated;
//    private boolean executing;

    private GameEngine gameEngine;
    private GameData gameData;
    private E_Bucket fromBucket;
    private E_Bucket toBucket;
    private int toMove;
    private int idStrategy;
    private StateMachine stateMachine;
    private ArrayList<Integer> arrIdOperatorSkilled;
    
    public Status getStatus() {
        return dataStore.getStatus();
    }
    
    public StoreStrategy(int idStrategy, E_TransportStore store, GameEngine gameEngine){
        this.idStrategy = idStrategy;
        this.dataStore = store;
//        this.pickLoad = false;
//        this.dropLoad = false;
        this.gameEngine = gameEngine;
        this.gameData = GameData.getInstance();
//        this.validated = false;
//        this.executing = false;
        this.fromStationLocation = null;
        this.toStationLocation = null;
        //stateMachine = store.getStateMachine();
        stateMachine = new StateMachine();
        //the order of 'adding' is the order of the state machine
        stateMachine.add("Validate");
        stateMachine.add("OperatorWalksToMachine");
        stateMachine.add("OperatorAndMachineWalkToFROMstation");
        stateMachine.add("PickUpItems");
        stateMachine.add("OperatorAndMachineWalkToTOstation");
        stateMachine.add("StoringItems");
        stateMachine.add("Release");
//        if (!(store.getActivityState().equals("") && store.getActivityStatus().equals(""))){
//            while(!stateMachine.getState().equals(store.getActivityState()))
//                stateMachine.update(StateMachine.Status.DONE);
//            stateMachine.update(StateMachine.Status.valueOf(store.getActivityStatus()));
//        }
        arrIdOperatorSkilled = gameData.getOperatorsSkilled(dataStore.getArrSkillsRequired(), dataStore.getArrAssignedOperators());
    }
    
    public void updateStrategy(){
        arrIdOperatorSkilled = gameData.getOperatorsSkilled(dataStore.getArrSkillsRequired(), dataStore.getArrAssignedOperators());
    }
    
    public double execute(){
//        System.out.println("Store Activity:" + idStrategy + " - BEGIN EXECUTE StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
        if (stateMachine.getState().equals("Validate")){
            if (validateResources()){
//                System.out.println("Store Activity:" + idStrategy + " - ValidateResources - SystemTime:" + gameEngine.getCurrentSystemTime());
                stateMachine.update(StateMachine.Status.DONE);
                return execute();
            }
        }else
        if (stateMachine.getState().equals("OperatorWalksToMachine")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Store Activity:" + idStrategy + " - OperatorWalksToMachine - SystemTime:" + gameEngine.getCurrentSystemTime());
                if (validatePositions_OperatorWalksToMachine()){
                    stateMachine.update(StateMachine.Status.DONE);
                    return execute();
                }
                stateMachine.update(StateMachine.Status.BUSY);
            }
        }else
        if (stateMachine.getState().equals("OperatorAndMachineWalkToFROMstation")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Store Activity:" + idStrategy + " - OperatorAndMachineWalkToFROMstation - SystemTime:" + gameEngine.getCurrentSystemTime());
                curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
                curMachine.updateVirtualPosition(Status.Walking.toString(),0,0,0,0);
                curMachine.assignMachineInParkingZone(fromStation, fromStationLocation);
                if (validatePositions_OperatorAndMachineWalkToStation(fromStationLocation)){
                    stateMachine.update(StateMachine.Status.DONE);
                    return execute();
                }
                stateMachine.update(StateMachine.Status.BUSY);
            }
        }else
        if (stateMachine.getState().equals("PickUpItems")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Store Activity:" + idStrategy + " - PickUpItems - SystemTime:" + gameEngine.getCurrentSystemTime());
                curOperator.updateVirtualPosition(TypeElements.STATION + String.valueOf(fromStation.getIdStation()),0,0);
                curMachine.updateVirtualPosition(TypeElements.STATION + String.valueOf(fromStation.getIdStation()),0,0,0,0);
                stateMachine.update(StateMachine.Status.BUSY);
                return pickUpItems();
            }
        }else
        if (stateMachine.getState().equals("OperatorAndMachineWalkToTOstation")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Store Activity:" + idStrategy + " - OperatorAndMachineWalkToTOstation - SystemTime:" + gameEngine.getCurrentSystemTime());
                stateMachine.update(StateMachine.Status.BUSY);
                validatePositions_OperatorAndMachineWalkToStation(toStationLocation);
                executeAlgorithm();
                curMachine.assignMachineInParkingZone(toStation, toStationLocation);
            }
        }else
        if (stateMachine.getState().equals("StoringItems")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Store Activity:" + idStrategy + " - StoringItems - SystemTime:" + gameEngine.getCurrentSystemTime());
                curOperator.updateVirtualPosition(TypeElements.STATION + String.valueOf(toStation.getIdStation()),0,0);
                curMachine.updateVirtualPosition(TypeElements.STATION + String.valueOf(toStation.getIdStation()),0,0,0,0);
                stateMachine.update(StateMachine.Status.BUSY);
                return storingItems();
            }
        }else
        if (stateMachine.getState().equals("Release")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Store Activity:" + idStrategy + " - Release - SystemTime:" + gameEngine.getCurrentSystemTime());
                stateMachine.update(StateMachine.Status.BUSY);
                releaseResources();
            }            
        }
//        System.out.println("Store Activity:" + idStrategy + " - END EXECUTE StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
        return -1;
    }
    
    public void release(){
        stateMachine.update(StateMachine.Status.DONE);
//        System.out.println("Store Activity:" + idStrategy + " - RELEASE METHOD StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
        //execute();
    }
    
    private boolean validateResources(){
//        if (validated) return true;
        
        this.fromStation = gameData.getMapUserStation().get(dataStore.getIdStationInitial());
        this.toStation = gameData.getMapUserStation().get(dataStore.getIdStationEnd());
        
        //FIXME: We should put two ArrayLists in E_Station; one for input buckets and other 
        // for output buckets. If the station is a storage, then both ArrayList variables
        // would reference the same ArrayList object.
        this.fromBucket = fromStation.outBucketWith(dataStore.getIdPart());
        if (this.fromBucket == null)
            this.fromBucket = fromStation.bothOrOutBucketWith(dataStore.getIdPart());
        this.toBucket = toStation.inBucketWith(dataStore.getIdPart());
        if (this.toBucket == null)
            this.toBucket = toStation.bothOrInBucketWith(dataStore.getIdPart());
        
        
        //curMachine = gameData.getMapUserMachine().get(dataStore.getIdMachine());
        curMachine = gameData.getNearestMachineTo(dataStore.getMachineCategory().toString(), (int)(fromStation.getStationLocationX() + fromStation.getSizeW()/2), (int)(fromStation.getStationLocationY() - fromStation.getSizeL()/2));
        if (curMachine != null)
            curOperator = gameData.getNearestOperatorTo(arrIdOperatorSkilled, curMachine.getCurrentLocationX(), curMachine.getCurrentLocationZ());
        else
            return false;
        
        if(fromStation !=null && toStation !=null && fromBucket !=null && toBucket !=null && curOperator !=null && curMachine !=null){
            
            toMove = this.dataStore.getUnitLoad();
            if(toBucket.getSize() +toBucket.getUnitsToArrive() +toMove > toBucket.getCapacity() 
                   || fromBucket.getSize() - fromBucket.getUnitsToRemove() < toMove
                   || curMachine.getStatus() != Status.Idle || curOperator.getStatus() != Status.Idle
                   || curMachine.getMachineState() != ObjectState.Active || curOperator.getState() != ObjectState.Active){
//                System.out.println("Unable to make Store #" +dataStore.getIdTransportStore()+ ".");
//                validateMessage = "Unable to make Store #" +dataStore.getIdTransportStore()+ ".";
                return false;
            }
            fromBucket.setUnitsToRemove(fromBucket.getUnitsToRemove() + toMove);
            fromStationLocation = fromStation.getParkingZone();
            if (fromStationLocation == null)    return false;
            toStationLocation = toStation.getParkingZone();
            if (toStationLocation == null)    return false;
            //Everything's ok
//            System.out.println("Resources for Store #" +dataStore.getIdTransportStore()+ " validated.");
//            validateMessage = "Resources for Store #" +dataStore.getIdTransportStore()+ " validated.";
            this.dataStore.setStatus(Status.Busy);
            //station.setStatus(Status.Busy);   //A station can do more than one thing at a time.
            //This probably shouldn't be here. Let's keep it for now to simplify things.
            curOperator.setActivityDoing(TypeActivity.Store);
            curOperator.setIdStrategyAssigned(idStrategy);
            curOperator.setStatus(Status.Busy);
            curMachine.setStatus(Status.Busy);
            curOperator.setActivityDescriptionAssigned(dataStore.getActivityDescription());
            curMachine.setActivityDescriptionAssigned(dataStore.getActivityDescription());
            E_Part curPart = gameData.getMapUserPart().get(dataStore.getIdPart());
            curMachine.updateItemsForMachine(dataStore.getIdPart(), curPart.getPartDesignColor(), "");//partTEXTURE
            try {
                //Reporting to the input bucket the amount of parts to arrive   (to)
                toBucket.addUnitsToArrive(toMove);
            } catch (ExceededCapacityException ex) {
                System.out.println(ex);
                System.out.println("Problem with executeAlgorithm on StoreStrategy.");
            }
            
            //if (toStationLocation != null) toStation.releaseParkingZone(toStationLocation.getSecond());
            
//            validated = true;
//            System.out.println("StoreStrategy ValidateResources - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
            return true;
        }
        else{
            //Some of the required resourses are not available.
//            System.out.println("Required resources for Store #" +dataStore.getIdTransportStore()+ " are not available.");
//            validateMessage = "Required resources for Store #" +dataStore.getIdTransportStore()+ " are not available.";
//            validated = false;
            return false;
        }
    }
    
    private boolean validatePositions_OperatorWalksToMachine()
    {
        if (!operatorNearToMachine(curOperator, curMachine) && !curOperator.getModelCharacter().hasChild(curMachine.getModelCharacter())){
            gameEngine.operatorWalksTo(curOperator, curMachine.getCurrentLocationX(), curMachine.getCurrentLocationZ() - curMachine.getSizeL()/2);//ADD
            curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
//            System.out.println("StoreStrategy WalksToMachine - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
            return false;
        }
        return true;
    }
    
    private double pickUpItems()
    {
        double factorMachine = curMachine.getPickUpTimeCalculated();
        double factorOperator = 0;
        double factorPart = gameData.getMapUserPart().get(dataStore.getIdPart()).getFactor();
        double quantityPart = (double)dataStore.getUnitLoad();
        for (Pair<Integer,Double> temp : curOperator.getArrSkills()){
            if (temp.getFirst() == Params.skillPickUpId){
                factorOperator = temp.getSecond();
                break;
            }
        }
        curMachine.showHideItemsInMachine(true);
//        curMachine.addItemsDynamically(true);
//        System.out.println("==== STORE:" + idStrategy + " - PickUpTime:" + (quantityPart / (factorMachine * factorOperator * factorPart)));
        return (quantityPart / (factorMachine * factorOperator * factorPart)) * gameEngine.getGeneralScreenController().getTimeFactor();
    }
    
    private boolean validatePositions_OperatorAndMachineWalkToStation(Pair<Integer,Integer> stationLocation)
    {
        if (curMachine.getCurrentLocationX() != stationLocation.getFirst() || curMachine.getCurrentLocationZ() != stationLocation.getSecond()){
            double factorMachine = curMachine.getMachineTimeCalculated();
            double factorOperator = 0;
            double factorPart = gameData.getMapUserPart().get(dataStore.getIdPart()).getFactor();
            double quantityPart = (double)dataStore.getUnitLoad();
            for (Pair<Integer,Double> temp : curOperator.getArrSkills()){
                if (temp.getFirst() == Params.skillMoveId){
                    factorOperator = temp.getSecond();
                    break;
                }
            }
            curOperator.setTempSpeed(quantityPart / (factorMachine * factorOperator * factorPart));
            gameEngine.operatorAndMachineMovingTo(curOperator, curMachine, stationLocation.getFirst(), stationLocation.getSecond()); //ADD
//            System.out.println("StoreStrategy OperatorAndMachineWalkToStation - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
            return false;
        }  
        return true;
    }
    
    private double storingItems()
    {
        double factorMachine = curMachine.getPlacementTimeCalculated();
        double factorOperator = 0;
        double factorPart = gameData.getMapUserPart().get(dataStore.getIdPart()).getFactor();
        double quantityPart = (double)dataStore.getUnitLoad();
        for (Pair<Integer,Double> temp : curOperator.getArrSkills()){
            if (temp.getFirst() == Params.skillPlacementId){
                factorOperator = temp.getSecond();
                break;
            }
        }
//        curMachine.removeItemsDynamically(true);
//        System.out.println("==== STORE:" + idStrategy + " - StoringTime:" + (quantityPart / (factorMachine * factorOperator * factorPart)));
        return (quantityPart / (factorMachine * factorOperator * factorPart)) * gameEngine.getGeneralScreenController().getTimeFactor();
    }
    
    private boolean operatorNearToMachine(E_Operator tempOpe, E_Machine tempMach)
    {
        if (tempOpe.getCurrentLocationX() == tempMach.getCurrentLocationX() - tempMach.getSizeW()/2
                && tempOpe.getCurrentLocationZ() == tempMach.getCurrentLocationZ())
            return true;
        if (tempOpe.getCurrentLocationX() == tempMach.getCurrentLocationX() + tempMach.getSizeW()/2
                && tempOpe.getCurrentLocationZ() == tempMach.getCurrentLocationZ())
            return true;
        if (tempOpe.getCurrentLocationX() == tempMach.getCurrentLocationX()
                && tempOpe.getCurrentLocationZ() == tempMach.getCurrentLocationZ() - tempMach.getSizeL()/2)
            return true;
        if (tempOpe.getCurrentLocationX() == tempMach.getCurrentLocationX()
                && tempOpe.getCurrentLocationZ() == tempMach.getCurrentLocationZ() + tempMach.getSizeL()/2)
            return true;
        if (tempOpe.getCurrentLocationX() == tempMach.getCurrentLocationX()
                && tempOpe.getCurrentLocationZ() == tempMach.getCurrentLocationZ())
            return true;
        return false;
    }
    
    private void executeAlgorithm(){
        //update resources machine-stations-parts-operator-others
        //Pre: The validateResources() method must be used just before this one. Excecute ONLY if 
        // validateResources() returns true.

        //fromStation.releaseParkingZone(fromStationLocation.getSecond());
//        gameEngine.operatorAndMachineMovingTo(curOperator, curMachine, toStationLocation.getFirst(), toStationLocation.getSecond()); //ADD
        curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
//        curOperator.setIsExecutingActivity(true);
        curMachine.updateVirtualPosition(Status.Walking.toString(),0,0,0,0);
//        curMachine.addItemsDynamically(false);//STOP adding more items
        try{
            //Updating amount of parts in output bucket  (from)
            fromBucket.subtract(toMove);
            fromBucket.setUnitsToRemove(fromBucket.getUnitsToRemove() - toMove);
            //remove Parts to be transported in bucket graphically
            //gameEngine.updatePartsInBucket(fromBucket);
            fromStation.updatePartsInBucket(fromBucket.getIdPart());
            
        }catch(InsufficientPartsException e){
            System.out.println(e);
            System.out.println("Problem with executeAlgorithm on StoreStrategy.");
        }
//        catch(ExceededCapacityException e2){
//            System.out.println(e2);
//            System.out.println("Problem with executeAlgorithm on StoreStrategy.");
//        }
//        executing = true;
//        System.out.println("StoreStrategy Executing - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
        //FIXME: Should use the time distributions to calculate processiong time.
//        System.out.println("Algorithim for Store #" +dataStore.getIdTransportStore()+ " executed successfully.");
//        executeMessage = "Algorithim for Store #" +dataStore.getIdTransportStore()+ " executing successfully.";
//        return curOperator.getMotionControl().getDuration() + dataStore.getProcessingTime() * gameEngine.getGeneralScreenController().getTimeFactor();
    }
    
    private void releaseResources(){
//        if(this.pickLoad){
//            
//            this.pickLoad = false;
//            this.dropLoad = true;
            
        try{
//                E_Operator curOperator = gameData.getMapUserOperator().get( (int)currentToken.getAttribute(1) );
//                E_Machine curMachine = gameData.getMapUserMachine().get( (int)currentToken.getAttribute(2) );

            //adding moved parts to input(to) bucket.
            this.toBucket.arrivedParts(toMove);
//            System.out.println("Arrived "+toMove+" parts from Store #"+dataStore.getIdTransportStore()+".");
//            releaseMessage = "Arrived "+toMove+" parts from Store #"+dataStore.getIdTransportStore()+".";

//            curOperator.setIsExecutingActivity(false);
            curOperator.setStatus(Status.Idle);
//            curOperator = null;
            curMachine.setStatus(Status.Idle);
            //station.setStatus(Status.Idle);
            this.dataStore.setStatus(Status.Idle);
//            System.out.println("Store: " +dataStore.getActivityDescription()+ " - Time: " +dataStore.getProcessingTime()+"secs.");
//            releaseMessage = releaseMessage + "\nStore: " +dataStore.getActivityDescription();//+ " - Time: " +dataStore.getProcessingTime()+"secs.";

            //add Parts transported in bucket graphically
            //gameEngine.updatePartsInBucket(toBucket);
            toStation.updatePartsInBucket(toBucket.getIdPart());
            //gameEngine.operatorAndMachineMovingTo(curOperator, curMachine, toStation.getStationLocationX() + (int)toStation.getSizeW()/2, toStation.getStationLocationY(), false); //REMOVE
//            validated = false;//enable a new activity
//            executing = false;
//            curMachine.removeItemsDynamically(false);//STOP removing items
            curMachine.showHideItemsInMachine(false);
            stateMachine.reset();
//            System.out.println("StoreStrategy Release - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
        }catch(InsufficientPartsException e){
            System.out.println(e);
            System.out.println("Problem with releaseResources on StoreStrategy.");
        }catch(ExceededCapacityException e2){
            System.out.println(e2);
            System.out.println("Problem with releaseResources on StoreStrategy.");
        }
            
//        } else System.out.println("Problem with releaseResources on StoreStrategy. "
//                + "Boolean pickLoad = "+pickLoad);
    }
    
//    private E_Operator getOperator(E_TransportStore storeToDo){
//    //FIXME: Have to check if the operator has the skills needed.
//        
//        //Debug code
//        //System.out.println("Operator Id\tName \tStatus");
//        operatorStatus = "Operator Id\tName \tStatus";
//        for(E_Operator worker : gameData.getMapUserOperator().values()){            
//            //System.out.println(worker.getIdOperator() +"\t\t"+ worker.getNameOperator() +"\t"+ worker.getStatus());
//            operatorStatus = operatorStatus + worker.getIdOperator() +"\t\t"+ worker.getNameOperator() +"\t"+ worker.getStatus() + "\n";
//        }    
//        //end of debug code
//        
//        for(E_Operator worker : gameData.getMapUserOperator().values()){
//            if(worker.getStatus() == Status.Idle){
//                //System.out.println(worker.getNameOperator()+" for Store#" +storeToDo.getIdTransportStore());
//                operatorStatus = operatorStatus + worker.getNameOperator()+" for Trasport#" +storeToDo.getIdTransportStore();
//                return worker;
//            }
//        }
//        return null;
//    }

    public String getType(){
        return dataStore.getTypeActivity().toString();
    }
    
    
//    public String getValidateMessage(){
//        return this.validateMessage;
//    }
//    
//    public String getExecuteMessage(){
//        return this.executeMessage;
//    }
//    
//    public String getReleaseMessage(){
//        return this.releaseMessage;
//    }
    
    public int getIdActivity(){
        return dataStore.getIdActivity();
    }
    
    public void addWorkCounter(){
        dataStore.setCountWorksPerHour(dataStore.getCountWorksPerHour() + 1);
    }
    
    public Object getData(){
        return dataStore;
    }
    
    public E_Operator getOperator(){
        return curOperator;
    }
    
    public StateMachine getStateMachine(){
        return this.stateMachine;
    }
    
    public E_Machine getMachine(){
        return this.curMachine;
    }
    
    public E_Station getStation(){
        return this.toStation;
    }
    
    public int getPriorityQueue(){
        return dataStore.getPriorityQueue();
    }
    
    public int getIdStrategy(){
        return idStrategy;
    }

    public void loadEvent(boolean movingAlone, int idOperator){
        this.curOperator = gameData.getMapUserOperator().get(idOperator);
        if (curOperator != null) curOperator.setIdStrategyAssigned(idStrategy);
        this.fromStation = gameData.getMapUserStation().get(dataStore.getIdStationInitial());
        this.toStation = gameData.getMapUserStation().get(dataStore.getIdStationEnd());
        this.fromBucket = fromStation.outBucketWith(dataStore.getIdPart());
        if (this.fromBucket == null)
            this.fromBucket = fromStation.bothOrOutBucketWith(dataStore.getIdPart());
        this.toBucket = toStation.inBucketWith(dataStore.getIdPart());
        if (this.toBucket == null)
            this.toBucket = toStation.bothOrInBucketWith(dataStore.getIdPart());
        //curMachine = gameData.getMapUserMachine().get(dataStore.getIdMachine());
        curMachine = gameData.getNearestMachineTo(dataStore.getMachineCategory().toString(), (int)(fromStation.getStationLocationX() + fromStation.getSizeW()/2), (int)(fromStation.getStationLocationY() - fromStation.getSizeL()/2));
        toMove = this.dataStore.getUnitLoad();
        curMachine.updateItemsForMachine(dataStore.getIdPart(), gameData.getMapUserPart().get(dataStore.getIdPart()).getPartDesignColor(), "");//partTEXTURE
        fromStationLocation = fromStation.getParkingZone();
        //toStation.releaseParkingZone(toStationLocation.getSecond());
        toStationLocation = toStation.getParkingZone();
        if (stateMachine.getState().equals("OperatorAndMachineWalkToTOstation") || stateMachine.getState().equals("PlacementItems")){
            curMachine.showHideItemsInMachine(true);
        }
    }
}
