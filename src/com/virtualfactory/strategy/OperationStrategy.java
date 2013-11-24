/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.strategy;

import com.jme3.math.Vector3f;
import com.virtualfactory.data.GameData;
import com.virtualfactory.GameEngine;
import com.virtualfactory.entity.E_AssemblyDetails;
import com.virtualfactory.entity.E_Bucket;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operation;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.entity.E_Part;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.exceptions.ExceededCapacityException;
import com.virtualfactory.exceptions.InsufficientPartsException;
import com.virtualfactory.utils.Distributions;
import com.virtualfactory.utils.GameSounds;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Sounds;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeActivity;
import com.virtualfactory.utils.TypeElements;
import java.util.ArrayList;
/**
 *
 * 
 */
public class OperationStrategy implements EventStrategy {
//    private String validateMessage = "";
//    private String executeMessage = "";
//    private String releaseMessage = "";
    
    private E_Operation dataOperation;
    private E_Station curStation;
    private E_Operator curOperator;
    private E_Machine curMachine;
//    private boolean startOperation;
//    private boolean finishOperation;
//    private boolean validated;
//    private boolean executing;

    private GameEngine gameEngine;
    private GameData gameData;
    private ArrayList<Pair<E_Part, Integer>> inParts;       //Part and quantity required per part
    private ArrayList<Pair<E_Bucket, Integer>> inBuckets;   //Bucket and quantity to remove
    private E_Part outPart;    
    private E_Bucket outBucket;
    private int idStrategy;
    private StateMachine stateMachine;
    private ArrayList<Integer> arrIdOperatorSkilled;
    private boolean setInitialPosition;
    private GameSounds gameSound;
    private Pair<GameSounds,Sounds> pairGameSound;

    public Status getStatus() {
        return dataOperation.getStatus();
    }
    
    public OperationStrategy(int idStrategy, E_Operation operation, GameEngine gameEngine){
        this.idStrategy = idStrategy;
        this.dataOperation = operation;
        this.gameData = GameData.getInstance();
        this.gameEngine = gameEngine;
        this.setInitialPosition = false;
        //update machine's position
//        setInitialPosition();
        //stateMachine = operation.getStateMachine();
        stateMachine = new StateMachine();
        //the order of 'adding' is the order of the state machine
        stateMachine.add("InitialPosition");
        stateMachine.add("Validate");
        stateMachine.add("OperatorWalksTo");
        stateMachine.add("Execute");
        stateMachine.add("Release");
//        if (!(operation.getActivityState().equals("") && operation.getActivityStatus().equals(""))){
//            while(!stateMachine.getState().equals(operation.getActivityState()))
//                stateMachine.update(StateMachine.Status.DONE);
//            stateMachine.update(StateMachine.Status.valueOf(operation.getActivityStatus()));
//        }
        arrIdOperatorSkilled = gameData.getOperatorsSkilled(dataOperation.getArrSkillsRequired(), dataOperation.getArrAssignedOperators());
        gameSound = new GameSounds(gameEngine.app.getAssetManager());
    }
    
    public void updateStrategy(){
        arrIdOperatorSkilled = gameData.getOperatorsSkilled(dataOperation.getArrSkillsRequired(), dataOperation.getArrAssignedOperators());
    }
    
    public double execute(){
//        System.out.println("Operation Activity:" + idStrategy + " - BEGIN EXECUTE StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
//        System.out.println("OPERATION STRATEGY - StateMachine: " + stateMachine + " - ObjectState:" + stateMachine.getState() + " - Status:" + stateMachine.getStatus());
//        if (stateMachine.getState() == null)
//            System.out.println("OPERATION STRATEGY");
        if (stateMachine.getState().equals("InitialPosition")){
            if (initialPosition()){
                stateMachine.update(StateMachine.Status.DONE);
//                System.out.println("Operation Activity:" + idStrategy + " - ValidateResources - SystemTime:" + gameEngine.getCurrentSystemTime());
                return execute();
            }
        }else
        if (stateMachine.getState().equals("Validate")){
            if (validateResources()){
                stateMachine.update(StateMachine.Status.DONE);
//                System.out.println("Operation Activity:" + idStrategy + " - ValidateResources - SystemTime:" + gameEngine.getCurrentSystemTime());
                return execute();
            }
        }else
        if (stateMachine.getState().equals("OperatorWalksTo")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Operation Activity:" + idStrategy + " - OperatorWalksTo - SystemTime:" + gameEngine.getCurrentSystemTime());
                if (validatePositions()){
                    stateMachine.update(StateMachine.Status.DONE);
                    return execute();
                }
                stateMachine.update(StateMachine.Status.BUSY);
            }
        }else
        if (stateMachine.getState().equals("Execute")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Operation Activity:" + idStrategy + " - Execute - SystemTime:" + gameEngine.getCurrentSystemTime());
                curOperator.updateVirtualPosition(TypeElements.STATION + String.valueOf(curStation.getIdStation()),0,0);
                stateMachine.update(StateMachine.Status.BUSY);
                return executeAlgorithm();
            }
        }else
        if (stateMachine.getState().equals("Release")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
//                System.out.println("Operation Activity:" + idStrategy + " - Release - SystemTime:" + gameEngine.getCurrentSystemTime());
                stateMachine.update(StateMachine.Status.BUSY);
                releaseResources();
            }            
        }
//        System.out.println("Operation Activity:" + idStrategy + " - END EXECUTE StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
        return -1;
    }
    
    public void release(){
        stateMachine.update(StateMachine.Status.DONE);
//        System.out.println("Operation Activity:" + idStrategy + " - RELEASE METHOD StateMachine:" + stateMachine.getState() + " - StatusMachine:" + stateMachine.getStatus());
        //execute();
    }
    
    private boolean initialPosition()
    {
        if (setInitialPosition) return true;
        //curMachine = gameData.getMapUserMachine().get(dataOperation.getIdMachine());
        curStation = gameData.getMapUserStation().get(dataOperation.getIdStation());
        curMachine = gameData.getFreeMachine(dataOperation.getMachineCategory().toString(), dataOperation.getTypeActivity());
        if (curMachine == null) return false;
        Pair<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>,Pair<Integer, Integer>> operatorLocation = null;
        if (!(TypeElements.STATION + String.valueOf(curStation.getIdStation())).equals(curMachine.getVirtualIdLocation())){
            operatorLocation = curStation.getLocationInMatrix(curMachine.getSizeW(), curMachine.getSizeL());
            if (operatorLocation != null){
                curMachine.setVirtualMatrixIniI(operatorLocation.getFirst().getFirst().getFirst());
                curMachine.setVirtualMatrixIniJ(operatorLocation.getFirst().getFirst().getSecond());
                curMachine.setVirtualMatrixEndI(operatorLocation.getFirst().getSecond().getFirst());
                curMachine.setVirtualMatrixEndJ(operatorLocation.getFirst().getSecond().getSecond());
                curMachine.setCurrentLocationX(operatorLocation.getSecond().getFirst());
                curMachine.setCurrentLocationZ(operatorLocation.getSecond().getSecond());
                curMachine.setVirtualIdLocation(TypeElements.STATION + String.valueOf(curStation.getIdStation()));
                curMachine.getModelCharacter().setLocalTranslation(new Vector3f(curMachine.getCurrentLocationX(), 0.5f, curMachine.getCurrentLocationZ()));
//                curMachine.getCharacter().setPhysicsLocation(new Vector3f(curMachine.getCurrentLocationX(), 20, curMachine.getCurrentLocationZ()));
            }
        }
        gameEngine.setTerrainMap(curMachine.getCurrentLocationX()-1, curMachine.getCurrentLocationZ(), curMachine.getSizeW()-2, curMachine.getSizeL()+2, true);
        setInitialPosition = true;
        return true;
    }
    
   
    /**
     * This method checks that in the current state of the game, we have the necessary resources
     * to start the operation. It will also instantiate some class fields that are needed on the
     * executeAlgorithm() and releaseResources() methods.
     * @return true if there are enough resources, false otherwise.
     */
    private boolean validateResources(){
//        if (validated) return true;
        inBuckets = new ArrayList<Pair<E_Bucket, Integer>>();
        inParts = new ArrayList<Pair<E_Part, Integer>>();
        this.outPart = gameData.getMapUserPart().get(dataOperation.getIdPart());
        this.outBucket = curStation.outBucketWith(outPart.getIdPart());
        curOperator = gameData.getNearestOperatorTo(arrIdOperatorSkilled ,curStation.getStationLocationX(), curStation.getStationLocationY());
        
        if(curStation !=null && outPart !=null && outBucket !=null && curOperator !=null && curMachine !=null){
            
            if(outBucket.getSize() + outBucket.getUnitsToArrive() + dataOperation.getQuantityOutput()*outPart.getOutputQuantity() > outBucket.getCapacity()
                   || curMachine.getStatus() != Status.Idle || curOperator.getStatus() != Status.Idle
                   || curMachine.getMachineState() != ObjectState.Active || curOperator.getState() != ObjectState.Active){
//                System.out.println("Unable to make Operation #" +dataOperation.getIdOperation()+ ".");
//                validateMessage = "Unable to make Operation #" +dataOperation.getIdOperation()+ ".";
                return false;
            }
            
            int tempIndex;
            boolean validated = true;
            for (tempIndex=0; tempIndex<outPart.getArrAssemblyDetails().size(); tempIndex++){
                E_AssemblyDetails temp = outPart.getArrAssemblyDetails().get(tempIndex);
                //Finding the input bucket of the current input part.
                E_Bucket input = curStation.inBucketWith(temp.getIdInputPart());                
                //Checking if the station has enough parts on the bucket to start the operation.
                int toRemove = temp.getQuantity()*dataOperation.getQuantityOutput();
                if((input.getSize() - input.getUnitsToRemove()) >= toRemove){
                    input.setUnitsToRemove(input.getUnitsToRemove() + toRemove);
                    this.inBuckets.add(new Pair<E_Bucket, Integer>(input, toRemove));
                }else{
                    validated = false;
                    break;
                }                
                //Adding to ArrayList the E_Part object and quantity for the current input part.
                this.inParts.add(new Pair<E_Part, Integer>(gameData.getMapUserPart().get(temp.getIdInputPart()), temp.getQuantity()));
            }
            if (!validated){
                for (int i=0; i<tempIndex; i++){
                    E_AssemblyDetails temp = outPart.getArrAssemblyDetails().get(i);
                    //Finding the input bucket of the current input part.
                    E_Bucket input = curStation.inBucketWith(temp.getIdInputPart());                
                    //Checking if the station has enough parts on the bucket to start the operation.
                    int toRemove = temp.getQuantity()*dataOperation.getQuantityOutput();
                    input.setUnitsToRemove(input.getUnitsToRemove() - toRemove);
                }
//                validateMessage = "Required parts for Operation #" +dataOperation.getIdOperation()+ " are not available.";
                return false;
            }
            //Everything's ok
            try {                
                //Reporting to the output bucket the amount of parts to arrive
                this.outBucket.addUnitsToArrive(dataOperation.getQuantityOutput()*outPart.getOutputQuantity());
            }catch(ExceededCapacityException e2){
                System.out.println(e2);
                System.out.println("Problem with executeAlgorithm on OperationStrategy.");
            }
//            validateMessage = "Resources for Operation #" +dataOperation.getIdOperation()+ " validated.";
            this.dataOperation.setStatus(Status.Busy);
            //station.setStatus(Status.Busy);   //A station can do more than one thing at a time.
//            curOperator.setActivityDoing(TypeActivity.Operation);
            curOperator.setIdStrategyAssigned(idStrategy);
            curOperator.setStatus(Status.Busy);
            curMachine.setStatus(Status.Busy);
            curOperator.setActivityDescriptionAssigned(dataOperation.getActivityDescription());
            curMachine.setActivityDescriptionAssigned(dataOperation.getActivityDescription());
//            curStation.setStatus(Status.Busy);
//            System.out.println("Activity:" + idStrategy+ " - operatorBusy:" + curOperator.getIdOperator());
            //Setting TOKEN: attribute1 = operatorId and attribute2 = machineId.
//            currentToken.setAttribute(1, curOperator.getIdOperator());
//            currentToken.setAttribute(2, curMachine.getIdMachine());
//            System.out.println("OperationStrategy ValidateResources - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
//            validated = true;
            return true;
        }else{
            //Some of the required resourses are not available.
//            System.out.println("Required resources for Operation #" +dataOperation.getIdOperation()+ " are not available.");
//            validateMessage = "Required resources for Operation #" +dataOperation.getIdOperation()+ " are not available.";
//            validated = false;
            return false;
        }
    }
    
    private boolean validatePositions()
    {
        if (curOperator.getCurrentLocationX() != curMachine.getCurrentLocationX() + curMachine.getSizeW()/2 - 1 || curOperator.getCurrentLocationZ() != curMachine.getCurrentLocationZ()){
            curOperator.setActivityDoing(TypeActivity.Transport);
            gameEngine.operatorWalksTo(curOperator, curMachine.getCurrentLocationX() + curMachine.getSizeW()/2 - 1, curMachine.getCurrentLocationZ());//ADD
            curOperator.updateVirtualPosition(Status.Walking.toString(),0,0);
//            System.out.println("OperationStrategy WalksTo - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
            return false;
        }
        return true;
    }
    
    private synchronized double executeAlgorithm(){
//        this.startOperation = true;
        //update resources status-stock-operator-others
        //Pre: The validateResources() method must be used just before this one. Excecute ONLY if 
        // validateResources() returns true.
        //startMachineAnimation  
        try{
            //Updating amount of parts in input buckets and inventory
            for(int i=0; i<inBuckets.size(); i++){
                Pair<E_Bucket, Integer> current = inBuckets.get(i);
                int toRemove = current.getSecond();
                current.getFirst().subtract(toRemove);
                current.getFirst().setUnitsToRemove(current.getFirst().getUnitsToRemove() - toRemove);
                //inBuckets.size() and inParts.size() MUST be the same.
                E_Part cPart = inParts.get(i).getFirst();
                //updating current part stock
                cPart.setCurrentStock(cPart.getCurrentStock()-toRemove);
                //reduce Parts in bucket graphically
                //gameEngine.updatePartsInBucket(current.getFirst());
                curStation.updatePartsInBucket(current.getFirst().getIdPart());
            }
            curOperator.setActivityDoing(TypeActivity.Operation);
            curOperator.playStopAnimation(this.gameEngine.getGeneralScreenController().getPauseStatus());
            curMachine.setPartsToProduce(dataOperation.getQuantityOutput()*outPart.getOutputQuantity());
            gameSound.playSound(Sounds.MachineWorking);
            pairGameSound = new Pair<GameSounds, Sounds>(gameSound, Sounds.MachineWorking);
            gameEngine.getArrGameSounds().add(pairGameSound);
            gameSound.notifyAudio(this.gameEngine);
//            executing = true;
//            System.out.println("OperationStrategy Execute - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
        }catch(InsufficientPartsException e){
            System.out.println(e);
            System.out.println("Problem with executeAlgorithm on OperationStrategy.");
        }
        
        //FIXME: Should use the time distributions to calculate processiong time.
//        System.out.println("Algorithim for Operation #" +dataOperation.getIdOperation()+ " executed successfully.");
//        executeMessage = "Algorithim for Operation #" +dataOperation.getIdOperation()+ " executing successfully.";
//        System.out.println(" --- Execution Time:" + (dataOperation.getProcessingTime() * gameEngine.getGeneralScreenController().getTimeFactor()));
//        double machineTime = Distributions.calculateDist(Distributions.distNormal, Params.machineNormalParam, curMachine.getMachineTimeCalculated());
        double machineTime = Params.machineTimeParam;
//        System.out.println("OPERATION time:" + machineTime);
        if (machineTime < 0) machineTime = Math.abs(machineTime);
        return  dataOperation.getQuantityOutput()*(machineTime * curMachine.getFactor_calculated()) * gameEngine.getGeneralScreenController().getTimeFactor();
    }
    
    public void releaseResources(){
//        if(this.startOperation){
//            
//            this.startOperation = false;
//            this.finishOperation = true;
            
        try{
            int created = dataOperation.getQuantityOutput()*outPart.getOutputQuantity();
            //adding constructed parts to output bucket.
            this.outBucket.arrivedParts(created);
//            releaseMessage = "Created "+created+" parts from Operation #"+dataOperation.getIdOperation()+".";
            //Updating the inventory of the output part.
            //FIXME: maybe we should create "subtract" and "add" methods like the ones on E_Bucket
            // and use them instead?
            this.outPart.setCurrentStock(this.outPart.getCurrentStock() +created);
            curOperator.playStopAnimation(false);
            curOperator.setStatus(Status.Idle);
            curMachine.setStatus(Status.Idle);
            this.dataOperation.setStatus(Status.Idle);

            //add Parts created in bucket graphically
            //gameEngine.updatePartsInBucket(outBucket);
            curStation.updatePartsInBucket(outBucket.getIdPart());
            gameSound.stopSound(Sounds.MachineWorking);
           gameEngine.getArrGameSounds().remove(pairGameSound);
//            validated = false; //enable a new activity
//            executing = false;
            stateMachine.reset();
//            System.out.println("OperationStrategy Release - Operator:" + curOperator.getIdOperator() + " - Machine:" + curMachine.getIdMachine() + " - Activity:" + idStrategy);
        }catch(InsufficientPartsException e){
            System.out.println(e);
            System.out.println("Problem with releaseResources on OperationStrategy.");
        }catch(ExceededCapacityException e2){
            System.out.println(e2);
            System.out.println("Problem with releaseResources on OperationStrategy.");
        }
            
//        } else System.out.println("Problem with releaseResources on OperationStrategy. "
//                + "Boolean startOperation = "+startOperation);
    }
      
    public String getType(){
        return dataOperation.getTypeActivity().toString();
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
        return dataOperation.getIdActivity();
    }
    
    public void addWorkCounter(){
        dataOperation.setCountWorksPerHour(dataOperation.getCountWorksPerHour() + 1);
    }
    
    public Object getData(){
        return dataOperation;
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
        return this.curStation;
    }
    
    public int getPriorityQueue(){
        return dataOperation.getPriorityQueue();
    }
    
    public int getIdStrategy(){
        return idStrategy;
    }
        
    public void loadEvent(boolean movingAlone, int idOperator){
        this.curOperator = gameData.getMapUserOperator().get(idOperator);
        if (curOperator != null) curOperator.setIdStrategyAssigned(idStrategy);
        inBuckets = new ArrayList<Pair<E_Bucket, Integer>>();
        inParts = new ArrayList<Pair<E_Part, Integer>>();
        this.outPart = gameData.getMapUserPart().get(dataOperation.getIdPart());
        this.outBucket = curStation.outBucketWith(outPart.getIdPart());
    }
}
