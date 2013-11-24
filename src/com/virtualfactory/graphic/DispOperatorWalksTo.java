package com.virtualfactory.graphic;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Vector3f;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.pathfinding.AStarPathFinder;
import com.virtualfactory.pathfinding.Path;
import com.virtualfactory.pathfinding.Path.Step;
import com.virtualfactory.pathfinding.PathFinder;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Status;

/**
 *
 * @author David Bengoa
 */
public class DispOperatorWalksTo {
    private E_Operator operator;
    private E_Machine machine;
    private int finalPosX;
    private int finalPosZ;
    private PathFinder pathFinder;
    private boolean toBeRemoved;
    private float timeFactor;
    private GameEngine game;
    private boolean toStaffZone = false;
    private boolean toSpecificMachine = false;
    
    public DispOperatorWalksTo(GameEngine gameEngine, E_Operator tempOperator, E_Machine tempMachine, int posX, int posZ, float timeFactor){
        this.pathFinder = new AStarPathFinder(gameEngine.getTerrainMap(), 1000, true);
        this.operator = tempOperator;
        this.machine = tempMachine;
        this.finalPosX = posX;
        this.finalPosZ = posZ;
        this.toBeRemoved = false;
        this.timeFactor = timeFactor;
        this.game = gameEngine;
        this.toSpecificMachine = true;
        printRoute();
    }
    
    public DispOperatorWalksTo(GameEngine gameEngine, E_Operator tempOperator, int posX, int posZ, float timeFactor, boolean toStaffZone){
        this.pathFinder = new AStarPathFinder(gameEngine.getTerrainMap(), 1000, true);
        this.operator = tempOperator;
        this.finalPosX = posX;
        this.finalPosZ = posZ;
        this.toBeRemoved = false;
        this.timeFactor = timeFactor;
        this.game = gameEngine;
        this.toStaffZone = toStaffZone;
        printRoute();
    }
        
    public DispOperatorWalksTo(GameEngine gameEngine, E_Operator tempOperator, int posX, int posZ, float timeFactor)
    {
        this.pathFinder = new AStarPathFinder(gameEngine.getTerrainMap(), 1000, true);
        this.operator = tempOperator;
        this.finalPosX = posX;
        this.finalPosZ = posZ;
        this.toBeRemoved = false;
        this.timeFactor = timeFactor;
        this.game = gameEngine;
        printRoute();
    }
    
    public boolean validateRepeated(E_Operator tempOperator, int posX, int posZ)
    {
        if (this.operator.getIdOperator() == tempOperator.getIdOperator() && finalPosX == posX && finalPosZ == posZ)
            return true;
        return false;
    }
    
    private void printRoute()
    {
        Path path = null;
        operator.setMotionPath(new MotionPath());
        path = pathFinder.findPath(operator.getCurrentLocationX(), operator.getCurrentLocationZ(), finalPosX, finalPosZ);
//        System.out.println("### OperatorWalksTo, Operator:" + operator.getIdOperator() + " - PathSize:" + path.getLength() + " - Strategy:" + operator.getIdStrategyAssigned());
        if (path != null) {
//            int i=0;
            for (Step s : path.getSteps()) {
                operator.getMotionPath().addWayPoint(new Vector3f(s.getX() - Params.terrainWidthLoc, 6f,  s.getY() - Params.terrainHeightLoc));
//                System.out.println("Step:" + operator.getMotionPath().getWayPoint(i));
//                i++;
            }
            //operator.setTempSpeed(operator.getSpeed());
            //NEW ADDED BY FORMULA.....
            double factorOperator = 0;
            for (Pair<Integer,Double> temp : operator.getArrSkills()){
                if (temp.getFirst() == Params.skillMoveId){
                    factorOperator = temp.getSecond();
                    break;
                }
            }
            operator.setTempSpeed(operator.getOperatorSpeedCalculated(operator.getNormalParamUnload())/factorOperator);//normal(4,uniform(0,1))
            //END ADDED
//            System.out.println("Operator:" + operator.getNameOperator());
//            System.out.println("STEP NUMBER:" + path.getLength());
//            System.out.println("OPERATOR SPEED:" + operator.getTempSpeed());
            operator.setEndLocationX(finalPosX);
            operator.setEndLocationZ(finalPosZ);
            operator.updateMotionControl(path.getLength(), (float)operator.getTempSpeed()*timeFactor);
            operator.getMotionControl().play();
            operator.setIsMoving(true);
            operator.getMotionPath().addListener(new MotionPathListener() {
                @Override
                public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                    operator.setPhysicsLocation(motionControl.getPath().getWayPoint(wayPointIndex));
//                    operator.setCurrentPathStep(wayPointIndex);
                    if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1) {
                        operator.setCurrentLocationX(finalPosX);
                        operator.setCurrentLocationZ(finalPosZ);
                        toBeRemoved = true;
                        operator.updatePhysicsLocation();
                        operator.playStopAnimation(false);
                        operator.setIsMoving(false);
                        operator.setMotionControl(null);
//                        System.out.println("DispOperatorWalksTo - Activity:" + operator.getIdActivityAssigned() + " - Operator:" + operator.getIdOperator());
                        //game.getManageEvents().getArrEvents().get(operator.getIdStrategyAssigned()).release();
                        if (!toSpecificMachine){
                            if (!toStaffZone){
                                game.getManageEvents().getEvent(operator.getIdStrategyAssigned()).release();
                            }else{
                                operator.setStatus(Status.Idle);
                                operator.updateTempToCurrentVirtualPosition();
                            }
                        }else{
                            double factorMachine = machine.getMachineTimeCalculated();
                            double factorOperator = 1;
                            for (Pair<Integer,Double> temp : operator.getArrSkills()){
                                if (temp.getFirst() == Params.skillMoveId){
                                    factorOperator = temp.getSecond();
                                    break;
                                }
                            }
                            operator.setTempSpeed(operator.getSpeed() * factorMachine * factorOperator);
                            game.operatorAndMachineMovingToMachineZone(operator, machine, machine.getTempFinalLocationX(), machine.getTempFinalLocationZ());
                        }
                    }else{
                        operator.setCurrentLocationX((int)motionControl.getPath().getWayPoint(wayPointIndex).getX());
                        operator.setCurrentLocationZ((int)motionControl.getPath().getWayPoint(wayPointIndex).getZ());
                        operator.playStopAnimation(true);
                    }
//                    System.out.println("Operator:" + operator.getIdOperator() + " - Index:" + wayPointIndex + " - Moving:" + control.getPath().getWayPoint(wayPointIndex) + " - Activity:" + operator.getIdActivityAssigned() + " - EndLocation:" + operator.getEndLocationX() + "," + operator.getEndLocationZ());
                }
            });
        }else{
            System.out.println("Error in OperatorMovingTo (not path found) - Operator:" + operator.getIdOperator());
        }
        pathFinder = null;
    }

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
}
