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
import com.virtualfactory.gui.layer.resources.GameLogControl;
import com.virtualfactory.threads.StationAnimation;
import com.virtualfactory.utils.Distributions;
import com.virtualfactory.utils.MessageType;
import com.virtualfactory.utils.Messages;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.StationType;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeActivity;
import com.virtualfactory.utils.TypeElements;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *    
 * 
 */
public class TransportStrategy implements EventStrategy {
//    private String validateMessage = "";
//    private String executeMessage = "";
//    private String releaseMessage = "";
    
    private E_TransportStore dataTransport;
    private E_Operator curOperator;
    private E_Machine curMachine;
    private E_Station fromStation;
    private E_Station toStation;
    private Pair<Integer,Integer> fromStationLocation;
    private Pair<Integer,Integer> toStationLocation;
    Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> toStationSlot = null;
    private boolean isToStationStorage = false;
    private boolean isFromStationStorage = false;
//    private boolean callToEvent;
//    private boolean pickLoad;
//    private boolean dropLoad;
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
    private double timeCalculatedPickUpPlacement = 0.0;
//    private ArrayList<StationAnimation> arrStationAnimation;

    public Status getStatus() {
        return dataTransport.getStatus();
    }
    
    public TransportStrategy(int idStrategy, E_TransportStore transport, GameEngine gameEngine){
        this.idStrategy = idStrategy;
        this.dataTransport = transport;
        this.gameEngine = gameEngine;
        this.gameData = GameData.getInstance();     
        this.dataTransport.setStatus(Status.Idle);
        this.fromStationLocation = null;
        this.toStationLocation = null;
        //stateMachine = transport.getStateMachine();
        stateMachine = new StateMachine();
        //the order of 'adding' is the order of the state machine
        stateMachine.add("Validate");
        stateMachine.add("OperatorWalksToMachine");
        stateMachine.add("OperatorAndMachineWalkToFROMstation");
        stateMachine.add("PickUpItems");
        stateMachine.add("OperatorAndMachineWalkToTOstation");
        stateMachine.add("PlacementItems");
        stateMachine.add("Release");
//        if (!(transport.getActivityState().equals("") && transport.getActivityStatus().equals(""))){
//            while(!stateMachine.getState().equals(transport.getActivityState()))
//                stateMachine.update(StateMachine.Status.DONE);
//            stateMachine.update(StateMachine.Status.valueOf(transport.getActivityStatus()));
//        }
//        arrStationAnimation = new ArrayList<StationAnimation>();
        arrIdOperatorSkilled = gameData.getOperatorsSkilled(dataTransport.getArrSkillsRequired(), dataTransport.getArrAssignedOperators());
    }
    
    public void updateStrategy(){
        arrIdOperatorSkilled = gameData.getOperatorsSkilled(dataTransport.getArrSkillsRequired(), dataTransport.getArrAssignedOperators());
    }
    
    public double execute(){
//        System.out.println("Transport Activity:" + idStrategy + " - BEGIN EXECUTE StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
        if (stateMachine.getState().equals("Validate")){
            if (validateResources()){
//                System.out.println("Transport Activity:" + idStrategy + " - ValidateResources - SystemTime:" + gameEngine.getCurrentSystemTime());
                stateMachine.update(StateMachine.Status.DONE);
                return execute();
            }
//            System.out.println("Transport Activity:" + idStrategy + " - ValidateResources NO - SystemTime:" + gameEngine.getCurrentSystemTime());
        }else
        if (stateMachine.getState().equals("OperatorWalksToMachine")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Transport Activity:" + idStrategy + " - OperatorWalksToMachine - SystemTime:" + gameEngine.getCurrentSystemTime());
                if (validatePositions_OperatorWalksToMachine()){
                    stateMachine.update(StateMachine.Status.DONE);
                    return execute();
                }
                stateMachine.update(StateMachine.Status.BUSY);
            }
        }else
        if (stateMachine.getState().equals("OperatorAndMachineWalkToFROMstation")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
//                curMachine.updateVirtualPosition(Status.Walking.toString(),0,0,0,0);
//                curMachine.assignMachineInParkingZone(fromStation, fromStationLocation);
                if (validatePositions_OperatorAndMachineWalkToStation(fromStationLocation, false)){
                    stateMachine.update(StateMachine.Status.DONE);
                    return execute();
                }
                stateMachine.update(StateMachine.Status.BUSY);
            }
        }else
        if (stateMachine.getState().equals("PickUpItems")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Transport Activity:" + idStrategy + " - PickUpItems - SystemTime:" + gameEngine.getCurrentSystemTime());
                curOperator.updateVirtualPosition(TypeElements.STATION + String.valueOf(fromStation.getIdStation()),0,0);
                curMachine.updateVirtualPosition(TypeElements.STATION + String.valueOf(fromStation.getIdStation()),0,0,0,0);
                stateMachine.update(StateMachine.Status.BUSY);
                return pickUpItems();
            }
        }else
        if (stateMachine.getState().equals("OperatorAndMachineWalkToTOstation")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Transport Activity:" + idStrategy + " - OperatorAndMachineWalkToTOMachine - SystemTime:" + gameEngine.getCurrentSystemTime());
                stateMachine.update(StateMachine.Status.BUSY);
                executeAlgorithm();
                curMachine.assignMachineInParkingZone(toStation, toStationLocation);
                validatePositions_OperatorAndMachineWalkToStation(toStationLocation, true);
            }
        }else
        if (stateMachine.getState().equals("PlacementItems")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Transport Activity:" + idStrategy + " - PlacementItems - SystemTime:" + gameEngine.getCurrentSystemTime());
                curOperator.updateVirtualPosition(TypeElements.STATION + String.valueOf(toStation.getIdStation()),0,0);
                curMachine.updateVirtualPosition(TypeElements.STATION + String.valueOf(toStation.getIdStation()),0,0,0,0);
                stateMachine.update(StateMachine.Status.BUSY);
                double placementTime = placementItems();
                if (placementTime > 0)
                    return placementTime;
            }
        }else
        if (stateMachine.getState().equals("Release")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
                stateMachine.update(StateMachine.Status.BUSY);
//                System.out.println("RELEASE Activity:" + dataTransport.getIdActivity()+ " - Machine:" + curMachine.getIdMachine());
                releaseResources();
//                System.out.println("Transport Activity:" + idStrategy + " - Release - SystemTime:" + gameEngine.getCurrentSystemTime());
            }            
        }
//        System.out.println("Transport Activity:" + idStrategy + " - END EXECUTE StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
        return -1;
    }
    
    public void release(){
        stateMachine.update(StateMachine.Status.DONE);
//        System.out.println("Transport Activity:" + idStrategy + " - RELEASE METHOD StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
    }
    
    private boolean validateResources(){
//        if (validated) return true;
        
        this.fromStation = gameData.getMapUserStation().get(dataTransport.getIdStationInitial());
        this.toStation = gameData.getMapUserStation().get(dataTransport.getIdStationEnd());
        this.fromBucket = fromStation.outBucketWith(dataTransport.getIdPart());
        if (this.fromBucket == null)
            this.fromBucket = fromStation.bothOrOutBucketWith(dataTransport.getIdPart());
        this.toBucket = toStation.inBucketWith(dataTransport.getIdPart());
        if (this.toBucket == null)
            this.toBucket = toStation.bothOrInBucketWith(dataTransport.getIdPart());
        curMachine = gameData.getNearestMachineTo(dataTransport.getMachineCategory().toString(), (int)(fromStation.getStationLocationX() + fromStation.getSizeW()/2), (int)(fromStation.getStationLocationY() - fromStation.getSizeL()/2));
        if (curMachine != null)
            curOperator = gameData.getNearestOperatorTo(arrIdOperatorSkilled, curMachine.getCurrentLocationX(), curMachine.getCurrentLocationZ());
        else
            return false;
//        System.out.println("Transport ACTIVITY:" + dataTransport.getActivityDescription() + " fromStation:" + fromStation + " -toStation:" + toStation
//                + " -fromBucket:" + fromBucket + " - toBucket:" + toBucket + " -Operator:" + curOperator + " -Machine:" + curMachine);
        if(fromStation !=null && toStation !=null && fromBucket !=null && toBucket !=null && curOperator !=null && curMachine !=null){
            toMove = this.dataTransport.getUnitLoad();
//            System.out.println("Transport ACTIVITY:" + dataTransport.getActivityDescription() + " -toBucket.getSize:" + toBucket.getSize() + 
//                    " -toBucket.getUnitsToArrive:" + toBucket.getUnitsToArrive() + " -toMove:" + toMove + " -toBucket.getCapacity:" + toBucket.getCapacity() +
//                    " -fromBucket.getSize:" + fromBucket.getSize() + " -fromBucket.getUnitsToRemove:" + fromBucket.getUnitsToRemove() + 
//                    " -machine.getStatus:" + curMachine.getStatus() + " -Operator.getStatus:" + curOperator.getStatus() + 
//                    " -machine.getState:" + curMachine.getMachineState() + " -Operator.getState:" + curOperator.getState());
            if(toBucket.getSize() +toBucket.getUnitsToArrive() +toMove > toBucket.getCapacity() 
                   || fromBucket.getSize() - fromBucket.getUnitsToRemove() < toMove
                   || curMachine.getStatus() != Status.Idle || curOperator.getStatus() != Status.Idle
                   || curMachine.getMachineState() != ObjectState.Active || curOperator.getState() != ObjectState.Active){
//                System.out.println("Unable to make Transport #" +dataTransport.getIdTransportStore()+ ".");
//                validateMessage = "Unable to make Transport #" +dataTransport.getIdTransportStore()+ ".";
                return false;
            }
            
            if (fromStation.getStationType().toString().contains("Storage")){
                isFromStationStorage = true;
            }else{
                isFromStationStorage = false;
            }
            if (isFromStationStorage && toStation.getStationType().equals(StationType.ShippingZone)){
                int numberRequiredParts = gameData.existsWaitOrderByRequiredPart(dataTransport.getIdPart());
                String namePart = gameData.getMapUserPart().get(dataTransport.getIdPart()).getPartDescription();
                if (numberRequiredParts > 0 && toBucket.getSize() - toBucket.getUnitsToRemove() < numberRequiredParts){
                    GameLogControl.addMessage(MessageType.Info, Messages.orderMoveParts.replace(Messages.wildCard, namePart));
                    if (fromBucket.getSize() - fromBucket.getUnitsToRemove() >= numberRequiredParts){
                        GameLogControl.addMessage(MessageType.Info, Messages.orderEnoughParts.replace(Messages.wildCard, namePart));
                    }else{
                        GameLogControl.addMessage(MessageType.Info, Messages.orderInsufficientParts.replace(Messages.wildCard, namePart));
                    }
                }else{
                    return false;
                }
            }
            if (Integer.parseInt(curMachine.getVirtualIdLocation().replace(TypeElements.STATION.toString(), "")) != fromStation.getIdStation())
                fromStationLocation = fromStation.getParkingZone();
            else{
                fromStationLocation = new Pair<Integer,Integer>(curMachine.getCurrentLocationX(), curMachine.getCurrentLocationZ());
            }
            if (fromStationLocation == null){
                GameLogControl.addMessage(MessageType.Error, Messages.noSpaceAvailableInStation.replace(Messages.wildCard, fromStation.getStationDescription()));
                return false;
            }
            if (Integer.parseInt(curMachine.getVirtualIdLocation().replace(TypeElements.STATION.toString(), "")) != toStation.getIdStation())
                toStationLocation = toStation.getParkingZone();
            else{
                toStationLocation = new Pair<Integer,Integer>(curMachine.getCurrentLocationX(), curMachine.getCurrentLocationZ());
            }
            if (toStationLocation == null){
                GameLogControl.addMessage(MessageType.Error, Messages.noSpaceAvailableInStation.replace(Messages.wildCard, toStation.getStationDescription()));
                fromStation.releaseParkingZone(fromStationLocation.getSecond());//AT THIS LEVEL WE HAVE 'RESERVED' PARKING ZONE IN 'FROM' STATION
                return false;
            }
            if (toStation.getStationType().toString().contains("Storage")){
                isToStationStorage = true;
                toStationSlot = toStation.getAvailableSlot(gameEngine.getGameData().getMapUserPart().get(dataTransport.getIdPart()).getPartDescription());
                if (toStationSlot == null){
                    fromStation.releaseParkingZone(fromStationLocation.getSecond());//AT THIS LEVEL WE HAVE 'RESERVED' PARKING ZONE IN 'FROM' STATION
                    toStation.releaseParkingZone(toStationLocation.getSecond());//AT THIS LEVEL WE HAVE 'RESERVED' PARKING ZONE IN 'TO' STATION
                    return false;
                }
                toStationSlot.getSecond().setFirst(dataTransport.getIdPart());
                toStationSlot.getSecond().setSecond(0);
            }else{
                isToStationStorage = false;
            }
            //Everything's ok
//            System.out.println("Resources for Transport #" +dataTransport.getIdTransportStore()+ " validated.");
//            validateMessage = "Resources for Transport #" +dataTransport.getIdTransportStore()+ " validated.";
            fromBucket.setUnitsToRemove(fromBucket.getUnitsToRemove() + toMove);
            this.dataTransport.setStatus(Status.Busy);
            //station.setStatus(Status.Busy);   //A station can do more than one thing at a time.
            //This probably shouldn't be here. Let's keep it for now to simplify things.
            curOperator.setIdStrategyAssigned(idStrategy);
            curOperator.setActivityDoing(TypeActivity.Transport);
            curOperator.setStatus(Status.Busy);
            curMachine.setStatus(Status.Busy);
            curOperator.setActivityDescriptionAssigned(dataTransport.getActivityDescription());
            curMachine.setActivityDescriptionAssigned(dataTransport.getActivityDescription());
            E_Part curPart = gameData.getMapUserPart().get(dataTransport.getIdPart());
            curMachine.updateItemsForMachine(dataTransport.getIdPart(), curPart.getPartDesignColor(), "");//partTEXTURE
            try {
                //Reporting to the input bucket the amount of parts to arrive   (to)
                toBucket.addUnitsToArrive(toMove);
            } catch (ExceededCapacityException ex) {
                System.out.println(ex);
                System.out.println("Problem with executeAlgorithm on TransportStrategy.");
            }
            //if (toStationLocation != null) toStation.releaseParkingZone(toStationLocation.getSecond());
//            validated = true;
//            System.out.println("TransportStrategy ValidateResources - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
            return true;
        }
        else{
            //Some of the required resourses are not available.
//            System.out.println("Required resources for Transport #" +dataTransport.getIdTransportStore()+ " are not available.");
//            validateMessage = "Required resources for Transport #" +dataTransport.getIdTransportStore()+ " are not available.";
//            validated = false;
            return false;
        }
    }
    
    private boolean validatePositions_OperatorWalksToMachine()
    {
        if (!operatorNearToMachine(curOperator, curMachine) && !curOperator.getModelCharacter().hasChild(curMachine.getModelCharacter())){
            gameEngine.operatorWalksTo(curOperator, curMachine.getCurrentLocationX(), curMachine.getCurrentLocationZ() - curMachine.getSizeL()/2);//ADD
            curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
//            System.out.println("TransportStrategy WalksToMachine - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
            return false;
        }
        return true;
    }
    
    private double pickUpItems()
    {
        //double factorMachine = Distributions.calculateDist(Distributions.distNormal, Params.equipmentNormalParam, curMachine.getPickUpTimeCalculated());
        double timeForTaskExecution = Params.operatorTaskExecutionParam;
        double factorOperator = 0;
        for (Pair<Integer,Double> temp : curOperator.getArrSkills()){
            if (temp.getFirst() == Params.skillPickUpId){
                factorOperator = temp.getSecond();
                break;
            }
        }
        curMachine.showHideItemsInMachine(true);
        //double timeCalculated = dataTransport.getUnitLoad()*(factorMachine * factorOperator) * gameEngine.getGeneralScreenController().getTimeFactor();
        timeCalculatedPickUpPlacement = toMove*(timeForTaskExecution * factorOperator) * gameEngine.getGeneralScreenController().getTimeFactor();
        double timeCalculated = timeCalculatedPickUpPlacement;
//        System.out.println("Operator: " + curOperator.getNameOperator() + " - timePickup:" + timeCalculated);
        if (timeCalculated < 0) timeCalculated = Math.abs(timeCalculated);
        if (isFromStationStorage) fromStation.removeItemsInSlotDynamically(dataTransport.getIdPart(), toMove, timeCalculated);
        curMachine.addItemsDynamically(true,toMove,timeCalculated);
        return timeCalculated;
    }
    
    private boolean validatePositions_OperatorAndMachineWalkToStation(Pair<Integer,Integer> stationLocation, boolean isLoaded)
    {
        if (curMachine.getCurrentLocationX() != stationLocation.getFirst() || curMachine.getCurrentLocationZ() != stationLocation.getSecond()){
            if (!isLoaded){//FROM station
                curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
                curMachine.updateVirtualPosition(Status.Walking.toString(),0,0,0,0);
                toStation.reserveParkingZone(toStationLocation.getSecond());//JUST IN CASE IT WILL RESERVE AGAIN
                curMachine.assignMachineInParkingZone(fromStation, fromStationLocation);
            }
            double factorMachine = curMachine.getFactor_calculated();
            double factorOperator = 1;
            double tempOperatorSpeed = curOperator.getOperatorSpeedCalculated(isLoaded == true ? curOperator.getNormalParamLoad() : curOperator.getNormalParamUnload());
            for (Pair<Integer,Double> temp : curOperator.getArrSkills()){
                if (temp.getFirst() == Params.skillMoveId){
                    factorOperator = temp.getSecond();
                    break;
                }
            }
            curOperator.setTempSpeed(tempOperatorSpeed / (factorMachine * factorOperator));
            gameEngine.operatorAndMachineMovingTo(curOperator, curMachine, stationLocation.getFirst(), stationLocation.getSecond()); //ADD
            return false;
        }  
        return true;
    }
    
    private double placementItems()
    {
        //double factorMachine = Distributions.calculateDist(Distributions.distNormal, Params.equipmentNormalParam, curMachine.getPlacementTimeCalculated());
//        double factorMachine = Params.equipmentNormalParam;
//        double factorOperator = 0;
//        for (Pair<Integer,Double> temp : curOperator.getArrSkills()){
//            if (temp.getFirst() == Params.skillPlacementId){
//                factorOperator = temp.getSecond();
//                break;
//            }
//        }
//        double timeCalculated = dataTransport.getUnitLoad()*(factorMachine * factorOperator ) * gameEngine.getGeneralScreenController().getTimeFactor();
        double timeCalculated = timeCalculatedPickUpPlacement;
//        System.out.println("Operator: " + curOperator.getNameOperator() + " - timePlacement:" + timeCalculated);
        if (timeCalculated < 0) timeCalculated = Math.abs(timeCalculated);
        curMachine.removeItemsDynamically(true,toMove,timeCalculated);
        if (isToStationStorage){
            toStation.addItemsInSlotDynamically(toStationSlot,timeCalculated, true, false, toMove, idStrategy);
            return -1;
        }        
        return timeCalculated;
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
//        this.pickLoad = true;
        //update resources machine-stations-parts-operator-others
        //Pre: The validateResources() method must be used just before this one. Excecute ONLY if 
        // validateResources() returns true.
//        E_Operator curOperator = gameData.getMapUserOperator().get((int)currentToken.getAttribute(1));
//        E_Machine curMachine = gameData.getMapUserMachine().get((int)currentToken.getAttribute(2));
//        int toMove = (int)currentToken.getAttribute(3);
        try{
            //Updating amount of parts in output bucket  (from)
//            System.out.println("** Transport ACTIVITY:" + dataTransport.getActivityDescription() + " - Size:" + fromBucket.getSize());
            fromBucket.subtract(toMove);
            fromBucket.setUnitsToRemove(fromBucket.getUnitsToRemove() - toMove);
            //remove Parts to be transported in bucket graphically
            //gameEngine.updatePartsInBucket(fromBucket);
//            System.out.println("-- Transport ACTIVITY:" + dataTransport.getActivityDescription() + " - Size:" + fromBucket.getSize());
            if (!isFromStationStorage)
                fromStation.updatePartsInBucket(fromBucket.getIdPart());
            //machineAnimation - graphic
            //operatorAndMachineWalkTo"ToStation"
            //fromStation.releaseParkingZone(fromStationLocation.getSecond());
            curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
            curMachine.updateVirtualPosition(Status.Walking.toString(),0,0,0,0);
            //curMachine.showHideItemsInMachine(true);// it is moved to the STEP BEFORE
            curMachine.addItemsDynamically(false,0,0);//STOP adding more items
//            System.out.println("TransportStrategy Executing - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
        }catch(InsufficientPartsException e){
            System.out.println(e);
            System.out.println("Problem with executeAlgorithm on TransportStrategy.");
        }
//        }catch(ExceededCapacityException e2){
//            System.out.println(e2);
//            System.out.println("Problem with executeAlgorithm on TransportStrategy.");
//        }
        
        //FIXME: Should use the time distributions to calculate processiong time.
//        System.out.println("Algorithim for Transport #" +dataTransport.getIdTransportStore()+ " executed successfully.");
//        executeMessage = "Algorithim for Transport #" +dataTransport.getIdTransportStore()+ " executing successfully.";
        //return dataTransport.getProcessingTime();
//        return curOperator.getMotionControl().getDuration();
    }
    
    private void releaseResources(){
        try{
//            Iterator<StationAnimation> tempAnim;
//            System.out.println("STRATEGY ini:" + idStrategy + " -ArrSize:" + arrStationAnimation.size());
//            while (arrStationAnimation.size() > 0){
//                System.out.println("STRATEGY ini:" + idStrategy + " waiting...");
//                tempAnim = arrStationAnimation.iterator();
//                while (tempAnim.hasNext()){
//                    if (tempAnim.next().isIsCompleted())
//                        tempAnim.remove();
//                }
//            }            
//            System.out.println("STRATEGY fin:" + idStrategy + " -ArrSize:" + arrStationAnimation.size());
            //adding moved parts to input(to) bucket.
            this.toBucket.arrivedParts(toMove);
//            System.out.println("Arrived "+toMove+" parts from Transport #"+dataTransport.getIdTransportStore()+".");
//            releaseMessage = "Arrived "+toMove+" parts from Transport #"+dataTransport.getIdTransportStore()+".";
//            System.out.println("--- release activity:" + idStrategy + " - operator:" + curOperator.getIdOperator() + " - machine:" + curMachine.getIdMachine());
            
//            curOperator.setIsExecutingActivity(false);
            curOperator.setStatus(Status.Idle);
            curOperator.updateVirtualPosition(TypeElements.STATION + String.valueOf(toStation.getIdStation()),0,0);
//            curOperator = null;
            curMachine.setStatus(Status.Idle);
            //station.setStatus(Status.Idle);
            this.dataTransport.setStatus(Status.Idle);
//            System.out.println("Transport: " +dataTransport.getActivityDescription()+ " - Time: " +dataTransport.getProcessingTime()+"secs.");
//            releaseMessage = releaseMessage + "\nTransport: " +dataTransport.getActivityDescription();//+ " - Time: " +dataTransport.getProcessingTime()+"secs.";

            //add Parts transported in bucket graphically
            //gameEngine.updatePartsInBucket(toBucket);
            if (!isToStationStorage)
                toStation.updatePartsInBucket(toBucket.getIdPart());
            else{
                toStationSlot.getSecond().setSecond(toMove);
                toStation.getMatrixValue()[toStationSlot.getFirst().getFirst()][toStationSlot.getFirst().getSecond()].setQuantity(toMove);
            }
                

            curMachine.updateVirtualPosition(TypeElements.STATION + String.valueOf(toStation.getIdStation()),0,0,0,0);
            curMachine.removeItemsDynamically(false,0,0);//STOP removing items
            curMachine.showHideItemsInMachine(false);
//            validated = false; //enable a new activity
//            executing = false;
            stateMachine.reset();
//            System.out.println("Release Transport - Part:" + dataTransport.getIdPart() + " -Strategy:" + idStrategy);
//            System.out.println("TransportStrategy Release - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
        }catch(InsufficientPartsException e){
            System.out.println(e);
            System.out.println("Problem with releaseResources on TransportStrategy.");
        }catch(ExceededCapacityException e2){
            System.out.println(e2);
            System.out.println("Problem with releaseResources on TransportStrategy.");
        }
    }

    public String getType(){
        return dataTransport.getTypeActivity().toString();
    }

//    public ArrayList<StationAnimation> getArrStationAnimation() {
//        return arrStationAnimation;
//    }
//
//    public void setArrStationAnimation(ArrayList<StationAnimation> arrStationAnimation) {
//        this.arrStationAnimation = arrStationAnimation;
//    }
    
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
        return dataTransport.getIdActivity();
    }
    
    public void addWorkCounter(){
        dataTransport.setCountWorksPerHour(dataTransport.getCountWorksPerHour() + 1);
    }
    
    public Object getData(){
        return dataTransport;
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
        return dataTransport.getPriorityQueue();
    }
    
    public int getIdStrategy(){
        return idStrategy;
    }
    
    public void loadEvent(boolean movingAlone, int idOperator){
        this.curOperator = gameData.getMapUserOperator().get(idOperator);
        if (curOperator != null) curOperator.setIdStrategyAssigned(idStrategy);
        this.fromStation = gameData.getMapUserStation().get(dataTransport.getIdStationInitial());
        this.toStation = gameData.getMapUserStation().get(dataTransport.getIdStationEnd());
        this.fromBucket = fromStation.outBucketWith(dataTransport.getIdPart());
        if (this.fromBucket == null)
            this.fromBucket = fromStation.bothOrOutBucketWith(dataTransport.getIdPart());
        this.toBucket = toStation.inBucketWith(dataTransport.getIdPart());
        if (this.toBucket == null)
            this.toBucket = toStation.bothOrInBucketWith(dataTransport.getIdPart());
        //curMachine = gameData.getMapUserMachine().get(dataTransport.getIdMachine());
        curMachine = gameData.getNearestMachineTo(dataTransport.getMachineCategory().toString(), (int)(fromStation.getStationLocationX() + fromStation.getSizeW()/2), (int)(fromStation.getStationLocationY() - fromStation.getSizeL()/2));
        curMachine.updateItemsForMachine(dataTransport.getIdPart(), gameData.getMapUserPart().get(dataTransport.getIdPart()).getPartDesignColor(), "");//partTEXTURE
        fromStationLocation = fromStation.getParkingZone();
        toStationLocation = toStation.getParkingZone();
        toMove = this.dataTransport.getUnitLoad();
        if (stateMachine.getState().equals("OperatorAndMachineWalkToTOstation") || stateMachine.getState().equals("PlacementItems")){
            curMachine.showHideItemsInMachine(true);
        }
        
    }
    
}
