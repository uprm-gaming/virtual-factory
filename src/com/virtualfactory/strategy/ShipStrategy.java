/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.strategy;

import com.virtualfactory.data.GameData;
import com.virtualfactory.GameEngine;
import com.virtualfactory.entity.E_Bucket;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.entity.E_Order;
import com.virtualfactory.entity.E_Part;
import com.virtualfactory.entity.E_Ship;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.utils.OrderStates;
import com.virtualfactory.utils.Status;

/**
 *
 *
 */
public class ShipStrategy implements EventStrategy {
//    private String validateMessage = "";
//    private String executeMessage = "";
//    private String releaseMessage = "";
    
    private E_Ship dataShip;
    private E_Station dock;
    private E_Bucket bucket;
    //private Status status;
    private GameEngine gameEngine;
    private GameData gameData;
    private E_Part partToShip;
    private int idStrategy;
    private StateMachine stateMachine;
    private E_Order order;

    public Status getStatus() {
        return dataShip.getStatus();
    }
            
    public ShipStrategy(int idStrategy, E_Ship ship, GameEngine gameEngine){
        this.idStrategy = idStrategy;
        this.dataShip = ship;
        this.gameEngine = gameEngine;
        this.gameData = GameData.getInstance();     
        this.dataShip.setStatus(Status.Idle);
        order = null;
        //stateMachine = ship.getStateMachine();
        stateMachine = new StateMachine();
        //the order of 'adding' is the order of the state machine
        stateMachine.add("AssignOrder");
        stateMachine.add("Validate");
        stateMachine.add("Execute");
        stateMachine.add("Release");
//        if (!(ship.getActivityState().equals("") && ship.getActivityStatus().equals(""))){
//            while(!stateMachine.getState().equals(ship.getActivityState()))
//                stateMachine.update(StateMachine.Status.DONE);
//            stateMachine.update(StateMachine.Status.valueOf(ship.getActivityStatus()));
//        }
    }
    
    public void updateStrategy(){
        
    }
    
    public double execute(){
//        System.out.println("Ship Activity:" + idStrategy + " - SystemTime:" + gameEngine.getCurrentSystemTime() + " - Order:" + order);
        if (stateMachine.getState().equals("AssignOrder")){
            if (assignOrder()){
                stateMachine.update(StateMachine.Status.DONE);
//                System.out.println("Ship Activity:" + idStrategy + " - AssignOrder - SystemTime:" + gameEngine.getCurrentSystemTime());
                return execute();
            }
        }else
        if (stateMachine.getState().equals("Validate")){
            if (validateResources()){
                stateMachine.update(StateMachine.Status.DONE);
//                System.out.println("Ship Activity:" + idStrategy + " - ValidateResources - SystemTime:" + gameEngine.getCurrentSystemTime());
                return execute();
            }
        }else
        if (stateMachine.getState().equals("Execute")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
                stateMachine.update(StateMachine.Status.BUSY);
//                System.out.println("Ship Activity:" + idStrategy + " - Execute - SystemTime:" + gameEngine.getCurrentSystemTime());
                return executeAlgorithm();
            }
        }else
        if (stateMachine.getState().equals("Release")){
            if (stateMachine.getStatus().equals(StateMachine.Status.FREE)){
                stateMachine.update(StateMachine.Status.BUSY);
//                System.out.println("Ship Activity:" + idStrategy + " - Release - SystemTime:" + gameEngine.getCurrentSystemTime());
                releaseResources();
            }            
        }
        return -1;
    }
    
    public void release(){
        stateMachine.update(StateMachine.Status.DONE);
        execute();
    }
    
    private boolean assignOrder(){
        if (order == null){
            order = gameEngine.getGameData().getNextOrder();
            if (order != null){
//                System.out.println("Ship Activity - Order ASSIGNED:" + order.getIdOrder() + " - status:" + order.getState().toString());
                return true;
            }else
                return false;
        }
        return false;
    }
    
    private boolean validateResources(){
        dock = gameData.getMapUserStation().get(dataShip.getIdStation());
        //partToShip = gameData.getMapUserPart().get(dataShip.getIdPart());
        order = gameEngine.getGameData().getNextOrder(order);
        if (order == null){
            stateMachine.reset();
            return false;
        }
        partToShip = gameData.getMapUserPart().get(order.getIdPart());
        bucket = dock.inBucketWith(partToShip.getIdPart());
        if(dock !=null && partToShip !=null && bucket !=null && order != null){
            if(bucket.getSize() < order.getQuantity()){//dataShip.getLoadQuantity()){
//                validateMessage = "No need to make Ship #" +dataShip.getIdShip()+ ".";
                return false;
            }
            
            //Everything's ok
//            System.out.println("Resources for Ship #" +dataShip.getIdShip()+ " validated.");
//            validateMessage = "Resources for Ship #" +dataShip.getIdShip()+ " validated.";
            this.dataShip.setStatus(Status.Busy);
            order.setState(OrderStates.Working);
            gameEngine.getGameData().setNextOrderDueDate(Integer.MAX_VALUE);
            gameEngine.getGeneralScreenController().setNextDueDate("-");
            gameEngine.getGameData().updateOrderControlData();
//            dock.setStatus(Status.Busy);
            return true;
        }
        else{
            //Some of the required resourses are not available.
//            System.out.println("Required resources for Ship #" +dataShip.getIdShip()+ " are not available.");
//            validateMessage = "Required resources for Ship #" +dataShip.getIdShip()+ " are not available.";
            return false;
        }
    }
    
    private double executeAlgorithm(){
        //Updating dock bucket the amount of parts to ship
        //this.bucket.setSize(this.bucket.getSize() - dataShip.getLoadQuantity());
        this.bucket.setSize(this.bucket.getSize() - order.getQuantity());
        //FIXME: Should use the time distributions to calculate processiong time.
//        executeMessage = "Algorithim for Ship #" +dataShip.getIdShip()+ " executing successfully.";
        return dataShip.getShippingTimeCalculated() * gameEngine.getGeneralScreenController().getTimeFactor();
    }
    
    private void releaseResources(){
        //int shipped = dataShip.getLoadQuantity();
        int shipped = order.getQuantity();
//        releaseMessage = "Sent "+shipped+" parts from Ship #"+dataShip.getIdShip()+".";
        //gameData.setCurrentMoney(gameData.getCurrentMoney() + partToShip.getPriceForSale()*shipped);
        dataShip.setStatus(Status.Idle);
        dataShip.addIncomes(partToShip.getPriceForSale()*shipped);
//        releaseMessage = releaseMessage + "\nPurchase: " +dataShip.getActivityDescription();//+ " - Time: " +dataShip.getProcessingTime()+"secs.";
        //remove Parts shipped in bucket graphically
        //gameEngine.updatePartsInBucket(bucket);
        dock.updatePartsInBucket(bucket.getIdPart());
        order.setState(OrderStates.Done);
        order = null;
        stateMachine.reset();
        gameEngine.getGameData().updateOrderControlData();
    }
    
    public String getType(){
        return dataShip.getTypeActivity().toString();
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
        return dataShip.getIdActivity();
    }
    
    public void addWorkCounter(){
        dataShip.setCountWorksPerHour(dataShip.getCountWorksPerHour() + 1);
    }
    
    public Object getData(){
        return dataShip;
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
        return dataShip.getPriorityQueue();
    }
    
    public int getIdStrategy(){
        return idStrategy;
    }
    
    public void loadEvent(boolean movingAlone, int idOperator){
        dock = gameData.getMapUserStation().get(dataShip.getIdStation());
        partToShip = gameData.getMapUserPart().get(order.getIdPart());
        bucket = dock.inBucketWith(partToShip.getIdPart());
    }
    
}
