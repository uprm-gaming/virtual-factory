package com.virtualfactory.threads;

import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Box;
import com.virtualfactory.engine.GameEngine;
import com.virtualfactory.utils.Status;

/**
 *
 * @author David
 */
public class StationAnimation extends Thread{
    private Box partBox;
    private GameEngine gameEngine;
    private double timeToFinish;
    private double timeFactor;
    private double initialTime;
    private boolean addItems = false;
    private boolean isZeroItems = false;
    private int quantity;
    private int idStrategy;
//    private boolean isCompleted = false;
    
    public StationAnimation(){
        partBox = null;
        gameEngine = null;
        timeToFinish = 0;
        timeFactor = 0;
        initialTime = 0;
        quantity = 0;
//        isCompleted = false;
    }
    
    @Override
    public void run(){
        timeFactor = gameEngine.getLayerScreenController().getTimeFactor();
        initialTime = gameEngine.getCurrentSystemTime();
        float currentHeight = partBox.getYExtent();
        int numberIterations = 0;
//        isCompleted = false;
        while (initialTime + timeToFinish > gameEngine.getCurrentSystemTime() && gameEngine.getCurrentSystemStatus().equals(Status.Busy)&&gameEngine.isExecuteGame()){
            if (timeFactor != gameEngine.getLayerScreenController().getTimeFactor()){
                double timeToComplete = initialTime + timeToFinish - gameEngine.getCurrentSystemTime();
                timeToFinish = timeToFinish - timeToComplete;
                timeToFinish = timeToFinish + timeToComplete*gameEngine.getLayerScreenController().getTimeFactor()/timeFactor;
                timeFactor = gameEngine.getLayerScreenController().getTimeFactor();
            }            
            numberIterations++;
            if (addItems){  //add
                currentHeight = currentHeight + 1;
            }else{  //remove
                if (currentHeight > 0){
                    currentHeight = (currentHeight - 1 < 0) ? 0 : (currentHeight - 1);
                }
            }
            if (currentHeight <= 5)
            partBox.updateGeometry(new Vector3f(partBox.getCenter().getX(), currentHeight, partBox.getCenter().getZ()), partBox.getXExtent(), currentHeight, partBox.getZExtent());
            try {
                Thread.sleep((long)(1000*timeToFinish/quantity));//(200);
            } catch (InterruptedException ex) {
                System.out.println("ERROR:" + ex.getMessage());
            }
        }
        if (gameEngine.getCurrentSystemStatus().equals(Status.Idle)){
            timeToFinish = initialTime + timeToFinish - gameEngine.getCurrentSystemTime();
            gameEngine.getArrStationAnimations().add(this);
            quantity = Math.abs(quantity - numberIterations);
//            System.out.println("End Thread:" + getName());
//            isCompleted = true;
            return;
        }
        if (!isZeroItems && (numberIterations != quantity)){
            int missedIterations;
            if (addItems){
                missedIterations = quantity - numberIterations;
            }else{
                missedIterations = numberIterations - quantity;
                if (partBox.getYExtent() == 0)
                    missedIterations = 0;
            }
            if (missedIterations <= 5)
            partBox.updateGeometry(new Vector3f(partBox.getCenter().getX(), partBox.getCenter().getY() + missedIterations, partBox.getCenter().getZ()), partBox.getXExtent(), partBox.getYExtent() + missedIterations, partBox.getZExtent());
        }
        if (!addItems && isZeroItems){
            partBox.updateGeometry(new Vector3f(partBox.getCenter().getX(), 0, partBox.getCenter().getZ()), partBox.getXExtent(), 0, partBox.getZExtent());
        }
//        System.out.println("End Thread:" + getName());
//        isCompleted = true;
        if (idStrategy != -1)
            gameEngine.getManageEvents().getEvent(idStrategy).release();
        Thread.currentThread().interrupt();
    }

//    public boolean isIsCompleted() {
//        return isCompleted;
//    }

    public int getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(int idStrategy) {
        this.idStrategy = idStrategy;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsZeroItems(boolean isZeroItems) {
        this.isZeroItems = isZeroItems;
    }

    public void setAddItems(boolean addItems) {
        this.addItems = addItems;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void setPartBox(Box part) {
        this.partBox = part;
    }

    public void setTimeToFinish(double timeToFinish) {
        this.timeToFinish = timeToFinish;
    }

    public boolean isAddItems() {
        return addItems;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public double getInitialTime() {
        return initialTime;
    }

    public boolean isIsZeroItems() {
        return isZeroItems;
    }

    public Box getPartBox() {
        return partBox;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTimeFactor() {
        return timeFactor;
    }

    public double getTimeToFinish() {
        return timeToFinish;
    }
    
}
