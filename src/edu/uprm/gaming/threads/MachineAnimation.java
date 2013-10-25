/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.threads;

import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Box;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.utils.Status;

/**
 *
 * @author David
 */
public class MachineAnimation extends Thread {
    private boolean moveItemsDynamically = false;
    private boolean addItems = false;
    private boolean isZeroItems = false;
    private Box boxForMachine;
    private GameEngine gameEngine = null;
    private double timeFactor;
    private double initialTime;
    private int quantity;
    private double timeToFinish;
    
    public MachineAnimation(){
        timeToFinish = 0;
        timeFactor = 0;
        initialTime = 0;
        quantity = 0;
    }
    
    @Override
    public void run(){
        timeFactor = gameEngine.getGeneralScreenController().getTimeFactor();
        initialTime = gameEngine.getCurrentSystemTime();
        float currentHeight = boxForMachine.getYExtent();
        int numberIterations = 0;
        int heightFactor = 4;
        while (moveItemsDynamically && (initialTime + timeToFinish > gameEngine.getCurrentSystemTime()) && gameEngine.getCurrentSystemStatus().equals(Status.Busy)&&gameEngine.getGeneralScreenController().getPauseStatus()){
            if (timeFactor != gameEngine.getGeneralScreenController().getTimeFactor()&&this.gameEngine.isExecuteGame()){
                double timeToComplete = initialTime + timeToFinish - gameEngine.getCurrentSystemTime();
                timeToFinish = timeToFinish - timeToComplete;
                timeToFinish = timeToFinish + timeToComplete*gameEngine.getGeneralScreenController().getTimeFactor()/timeFactor;
                timeFactor = gameEngine.getGeneralScreenController().getTimeFactor();
            }            
            numberIterations++;
            if (addItems){  //add
                currentHeight = currentHeight + heightFactor;
            }else{  //remove
                if (currentHeight > 0){
                    currentHeight = (currentHeight - heightFactor < 0) ? 0 : (currentHeight - heightFactor);
                }
            }
            boxForMachine.updateGeometry(new Vector3f(boxForMachine.getCenter().getX(), currentHeight, boxForMachine.getCenter().getZ()), boxForMachine.getXExtent(), currentHeight, boxForMachine.getZExtent());
            try {
                Thread.sleep((long)(1000*Math.abs(timeToFinish/quantity)));//(200);
            } catch (InterruptedException ex) {
                System.out.println("ERROR:" + ex.getMessage());
            }
//            float currentHeight = boxForMachine.getYExtent();
//            float paramCenterY = 0;            
//            if (addItems){  //add
//                currentHeight = currentHeight + (int)gameEngine.getGeneralScreenController().getTimeFactorForSpeed();
//                paramCenterY = (int)gameEngine.getGeneralScreenController().getTimeFactorForSpeed();
//            }else{  //remove
//                if (currentHeight > 0){
//                    currentHeight = (currentHeight - (int)gameEngine.getGeneralScreenController().getTimeFactorForSpeed() < 0)
//                            ? 0 : (currentHeight - (int)gameEngine.getGeneralScreenController().getTimeFactorForSpeed());
//                    paramCenterY = -(int)gameEngine.getGeneralScreenController().getTimeFactorForSpeed();
//                }
//            }
//            boxForMachine.updateGeometry(new Vector3f(boxForMachine.getCenter().getX(), boxForMachine.getCenter().getY() + paramCenterY, boxForMachine.getCenter().getZ()), boxForMachine.getXExtent(), currentHeight, boxForMachine.getZExtent());
//            try {
//                Thread.currentThread().sleep(200);
//            } catch (InterruptedException ex) {
//                System.out.println("ERROR:" + ex.getMessage());
//            }
        }
        if (gameEngine.getCurrentSystemStatus().equals(Status.Idle)){
            timeToFinish = initialTime + timeToFinish - gameEngine.getCurrentSystemTime();
            quantity = Math.abs(quantity - numberIterations);
            return;
        }
        if (!isZeroItems && (numberIterations != quantity)){
            int missedIterations;
            if (addItems){
                missedIterations = quantity - numberIterations;
            }else{
                missedIterations = numberIterations - quantity;
                if (boxForMachine.getYExtent() == 0)
                    missedIterations = 0;
            }
            missedIterations = missedIterations*heightFactor;
            boxForMachine.updateGeometry(new Vector3f(boxForMachine.getCenter().getX(), boxForMachine.getCenter().getY() + missedIterations, boxForMachine.getCenter().getZ()), boxForMachine.getXExtent(), boxForMachine.getYExtent() + missedIterations, boxForMachine.getZExtent());
        }
        if (!addItems && isZeroItems){
            boxForMachine.updateGeometry(new Vector3f(boxForMachine.getCenter().getX(), 0, boxForMachine.getCenter().getZ()), boxForMachine.getXExtent(), 0, boxForMachine.getZExtent());
        }
    }
    
    public boolean isAddItems() {
        return addItems;
    }

    public void setAddItems(boolean addItems) {
        this.addItems = addItems;
    }

    public boolean isMoveItemsDynamically() {
        return moveItemsDynamically;
    }

    public void setMoveItemsDynamically(boolean moveItemsDynamically) {
        this.moveItemsDynamically = moveItemsDynamically;
    }

    public Box getBoxForMachine() {
        return boxForMachine;
    }

    public void setBoxForMachine(Box boxForMachine) {
        this.boxForMachine = boxForMachine;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public boolean isIsZeroItems() {
        return isZeroItems;
    }

    public void setIsZeroItems(boolean isZeroItems) {
        this.isZeroItems = isZeroItems;
    }

    public double getInitialTime() {
        return initialTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(double timeToFinish) {
        this.timeToFinish = timeToFinish;
    }
    
}
