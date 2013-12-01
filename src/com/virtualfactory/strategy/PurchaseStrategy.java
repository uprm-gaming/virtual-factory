/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.strategy;

import com.virtualfactory.data.GameData;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Bucket;
import com.virtualfactory.entity.E_Catalog;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.entity.E_Part;
import com.virtualfactory.entity.E_Purchase;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.entity.E_Supplier;
import com.virtualfactory.exceptions.ExceededCapacityException;
import com.virtualfactory.exceptions.InsufficientPartsException;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Sounds;
import com.virtualfactory.utils.Status;

/**
 *
 * 
 */
public class PurchaseStrategy implements EventStrategy {
//    private String validateMessage="";
//    private String executeMessage="";
//    private String releaseMessage="";
    
    private E_Purchase dataPurchase;
    private E_Station dock;
//    private boolean placeOrder;
//    private boolean arrivalOrder;
    //private Status status;
    
    private GameEngine gameEngine;
    private GameData gameData;
    private E_Part partToBuy;
    private E_Bucket bucket;
    private E_Supplier supplier;
    private E_Catalog catalog;
    private int idStrategy;
    private StateMachine stateMachine;
    private boolean isFirstPurchase = false;
    private int tempOrderPoint = 0;
    private int tempOrderQuantity = 0;
    
    public Status getStatus() {
        return dataPurchase.getStatus();
    }
    
    public PurchaseStrategy(int idStrategy, E_Purchase purchase, GameEngine gameEngine){
        this.idStrategy = idStrategy;
        this.dataPurchase = purchase;
        this.gameEngine = gameEngine;
        this.gameData = GameData.getInstance();     
        this.dataPurchase.setStatus(Status.Idle);
        //stateMachine = purchase.getStateMachine();
        stateMachine = new StateMachine();
        //the order of 'adding' is the order of the state machine
        stateMachine.add("Validate");
        stateMachine.add("Execute");
        stateMachine.add("Release");
        isFirstPurchase = true;
    }
    
    public void updateStrategy(){
        
    }
    
    public double execute(){
        if (stateMachine.getState().equals("Validate")){
            if (validateResources()){
                stateMachine.update(StateMachine.Status.DONE);
//                System.out.println("Purchase Activity:" + idStrategy + " - ValidateResources - SystemTime:" + gameEngine.getCurrentSystemTime());
                return execute();
            }
        }else
        if (stateMachine.getState().equals("Execute")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
                stateMachine.update(StateMachine.Status.BUSY);
//                System.out.println("Purchase Activity:" + idStrategy + " - Execute - SystemTime:" + gameEngine.getCurrentSystemTime());
                return executeAlgorithm();
            }
        }else
        if (stateMachine.getState().equals("Release")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
                stateMachine.update(StateMachine.Status.BUSY);
//                System.out.println("Purchase Activity:" + idStrategy + " - Release - SystemTime:" + gameEngine.getCurrentSystemTime());
                releaseResources();
            }            
        }
        return -1;
    }
    
    public void release(){
        stateMachine.update(StateMachine.Status.DONE);
        execute();
    }
    
    private boolean validateResources(){           
        dock = gameData.getMapUserStation().get(dataPurchase.getIdStation());
        //Is it arrUserPart or arrGamePart?
        partToBuy = gameData.getMapUserPart().get(dataPurchase.getIdPart());
        bucket = dock.outBucketWith(partToBuy.getIdPart());
        supplier = gameData.getMapGameSupplier().get(dataPurchase.getIdSupplier());
        catalog = supplier.getArrCatalog().get(dataPurchase.getIdPart());
        tempOrderPoint = dataPurchase.getOrderPoint();
        tempOrderQuantity = dataPurchase.getOrderQuantity();
        if(dock !=null && partToBuy !=null && bucket !=null){
            if(partToBuy.getCurrentStock() +bucket.getUnitsToArrive() > tempOrderPoint /*|| dock.getStatus()==Status.Busy*/
                   || bucket.getSize() + bucket.getUnitsToArrive() + tempOrderQuantity > bucket.getCapacity()){
//                System.out.println("No need to make Purchase #" +dataPurchase.getIdPurchase()+ ".");
//                validateMessage = "No need to make Purchase #" +dataPurchase.getIdPurchase()+ ".";
                return false;
            }
            //FIXME: Currently, there's no entity set storing the id of the current
            // supplier for this purchase. We wont calculate the part cost for now.
            if (getPricePerPart(tempOrderQuantity)*tempOrderQuantity > gameData.getCurrentMoney()){
//                System.out.println("No enough money to make Purchase #" +dataPurchase.getIdPurchase()+ ".");
//                validateMessage = "No enough money to make Purchase #" +dataPurchase.getIdPurchase()+ ".";
                return false;
            }
            
            //Everything's ok
//            System.out.println("Resources for Purchase #" +dataPurchase.getIdPurchase()+ " validated.");
//            validateMessage = "Resources for Purchase #" +dataPurchase.getIdPurchase()+ " validated.";
            dataPurchase.setStatus(Status.Busy);
//            dock.setStatus(Status.Busy);
            //dock.setStatus(Status.Busy);  //A station can do more than one thing at a time.
            //no operator or machine needed to make busy.
            return true;
        }
        else{
            //Some of the required resourses are not available.
//            System.out.println("Required resources for Purchase #" +dataPurchase.getIdPurchase()+ " are not available.");
//            validateMessage = "Required resources for Purchase #" +dataPurchase.getIdPurchase()+ " are not available.";
            return false;
        }
    }
    public boolean validatePositions(){
        //this method for Puchase does not require any logic
        return true;
    }
    
    private double getPricePerPart(int quantity){
        if (catalog.getPriceFunction1Limit() >= quantity)
            return catalog.getPriceFunction1Charge();
        else
        if (catalog.getPriceFunction2Limit() >= quantity)
            return catalog.getPriceFunction2Charge();
        else
            return catalog.getPriceFunction3Charge();
    }
    
    public double executeAlgorithm(){
        try{
            gameData.setCurrentMoney(gameData.getCurrentMoney() - getPricePerPart(tempOrderQuantity)*tempOrderQuantity);
            dataPurchase.addCosts(getPricePerPart(tempOrderQuantity)*tempOrderQuantity);
            this.bucket.addUnitsToArrive(tempOrderQuantity);
        }catch(ExceededCapacityException e2){
            System.out.println(e2);
            System.out.println("Problem with executeAlgorithm on PurchaseStrategy.");
        }
        double timeCalculated;
        int newPurchaseTime;
        if (isFirstPurchase){
            timeCalculated = Params.firstPurchaseTime;
            newPurchaseTime = Params.firstPurchaseTime;
            isFirstPurchase = false;
        }else{
            timeCalculated = catalog.getProductionCalculated() * gameEngine.getLayerScreenController().getTimeFactor();
            newPurchaseTime = (int)catalog.getProductionCalculated();
        }
        if (gameEngine.getGameData().getCurrentPurchaseId() < 0){
            gameEngine.getGameData().setCurrentPurchaseId(idStrategy);
            gameEngine.getGameData().setNextPurchaseDueDate(newPurchaseTime);
        }else{
            if (newPurchaseTime < gameEngine.getGameData().getNextPurchaseDueDate()){
                gameEngine.getGameData().setCurrentPurchaseId(idStrategy);
                gameEngine.getGameData().setNextPurchaseDueDate(newPurchaseTime);
            }
        }
        return timeCalculated;
    }
    
    private void releaseResources(){
            try{
                int bought = tempOrderQuantity;
                //adding bought parts to dock bucket.
                this.bucket.arrivedParts(bought);
                this.partToBuy.setCurrentStock(this.partToBuy.getCurrentStock() +bought);
                this.dataPurchase.setStatus(Status.Idle);
                dock.updatePartsInBucket(bucket.getIdPart());
                stateMachine.reset();
                gameEngine.getGameSounds().playSound(Sounds.PurchaseArrived);
                if (gameEngine.getGameData().getCurrentPurchaseId() == idStrategy){
                    gameEngine.getGameData().setNextPurchaseDueDate(0);
                }
            }catch(InsufficientPartsException e){
                System.out.println(e);
                System.out.println("Problem with releaseResources on PurchaseStrategy.");
            }catch(ExceededCapacityException e2){
                System.out.println(e2);
                System.out.println("Problem with releaseResources on PurchaseStrategy.");
            }
    }
    
    public String getType(){
        return dataPurchase.getTypeActivity().toString();
    }

    public int getIdActivity(){
        return dataPurchase.getIdActivity();
    }
    
    public void addWorkCounter(){
        dataPurchase.setCountWorksPerHour(dataPurchase.getCountWorksPerHour() + 1);
    }
    
    public Object getData(){
        return dataPurchase;
    }
    
    public E_Operator getOperator(){
        return null;
    }
    
    public StateMachine getStateMachine(){
        return this.stateMachine;
    }
    
    public E_Machine getMachine(){
        return null;
    }
    
    public E_Station getStation(){
        return this.dock;
    }
    
    public int getPriorityQueue(){
        return dataPurchase.getPriorityQueue();
    }
    
    public int getIdStrategy(){
        return idStrategy;
    }
    
    public void loadEvent(boolean movingAlone, int idOperator){
        dock = gameData.getMapUserStation().get(dataPurchase.getIdStation());
        partToBuy = gameData.getMapUserPart().get(dataPurchase.getIdPart());
        bucket = dock.outBucketWith(partToBuy.getIdPart());
    }

}
