/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.joints.ConeJoint;
import com.jme3.bullet.joints.PhysicsJoint;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.entity.E_Machine;
import edu.uprm.gaming.entity.E_Operator;
import edu.uprm.gaming.pathfinding.AStarPathFinder;
import edu.uprm.gaming.pathfinding.PathFinder;
import edu.uprm.gaming.pathfinding.Path;
import edu.uprm.gaming.pathfinding.Path.Step;
import edu.uprm.gaming.utils.Params;
import edu.uprm.gaming.utils.Status;

/**
 *
 * @author David
 */
public class DispOperatorMachineMovingTo {
    private E_Operator operator;
    private E_Machine machine;
    private int finalPosX;
    private int finalPosZ;
    private PathFinder pathFinder;
    private boolean toBeRemoved;
    private float timeFactor;
    private Node rootNode;
    private GameEngine game;
    private boolean toMachineZone;
    
    public DispOperatorMachineMovingTo(GameEngine gameEngine, Node rootNode, E_Operator tempOperator, E_Machine tempMachine, int posX, int posZ, float timeFactor, boolean toMachineZone)
    {
        this.pathFinder = new AStarPathFinder(gameEngine.getTerrainMap(), 1000, true);
        this.operator = tempOperator;
        this.machine = tempMachine;
        this.finalPosX = posX;
        this.finalPosZ = posZ;
        this.toBeRemoved = false;
        this.timeFactor = timeFactor;
        this.rootNode = rootNode;
        this.game = gameEngine;
        this.toMachineZone = toMachineZone;
        printRoute();
    }
    
    public DispOperatorMachineMovingTo(GameEngine gameEngine, Node rootNode, E_Operator tempOperator, E_Machine tempMachine, int posX, int posZ, float timeFactor)
    {
        this.pathFinder = new AStarPathFinder(gameEngine.getTerrainMap(), 1000, true);
        this.operator = tempOperator;
        this.machine = tempMachine;
        this.finalPosX = posX;
        this.finalPosZ = posZ;
        this.toBeRemoved = false;
        this.timeFactor = timeFactor;
        this.rootNode = rootNode;
        this.game = gameEngine;
        printRoute();
    }
        
    public boolean validateRepeated(E_Operator tempOperator, E_Machine tempMachine, int posX, int posZ)
    {
        if (this.operator.getIdOperator() == tempOperator.getIdOperator() && this.machine.getIdMachine() == tempMachine.getIdMachine() && finalPosX == posX && finalPosZ == posZ)
            return true;
        return false;
    }
    
    private void printRoute()
    {
        Path path = null;
        operator.setMotionPath(new MotionPath());
        operator.getModelCharacter().attachChild(machine.getModelCharacter());
        operator.getModelCharacter().getChild(machine.getModelCharacter().getName()).setLocalTranslation(new Vector3f(0, -5f, 4));
        operator.setIdMachineAttached(machine.getIdMachine());
        path = pathFinder.findPath(operator.getCurrentLocationX(), operator.getCurrentLocationZ(), finalPosX, finalPosZ);
//        System.out.println("### OperatorMachineWalkTo, Operator:" + operator.getIdOperator() + " - PathSize:" + path.getLength() + " - Machine:" + operator.getIdMachineAttached() + " - Strategy:" + operator.getIdStrategyAssigned());
        if (path != null) {
            for (Step s : path.getSteps()) {
                operator.getMotionPath().addWayPoint(new Vector3f(s.getX() - Params.terrainWidthLoc, 6f,  s.getY() - Params.terrainHeightLoc));
            }
//            operator.setTempSpeed(operator.getSpeed() >= machine.getSpeed() ? machine.getSpeed() : operator.getSpeed());
            //final SPEED is the Operator TEMP_SPEED, which was updated in each Activity
            operator.setEndLocationX(finalPosX);
            operator.setEndLocationZ(finalPosZ);
            operator.updateMotionControl(path.getLength(),(float)operator.getTempSpeed()*timeFactor);
            operator.getMotionControl().play();
            operator.setIsMoving(true);
            operator.getMotionPath().addListener(new MotionPathListener() {
                public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                    operator.setPhysicsLocation(motionControl.getPath().getWayPoint(wayPointIndex));
//                    operator.setCurrentPathStep(wayPointIndex);
                    machine.setPhysicsLocation(motionControl.getPath().getWayPoint(wayPointIndex));
                    if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1) {
                        operator.setCurrentLocationX(finalPosX);
                        operator.setCurrentLocationZ(finalPosZ);
                        operator.updatePhysicsLocation();
                        operator.setIdMachineAttached(-1);
                        machine.setCurrentLocationX(finalPosX);
                        machine.setCurrentLocationZ(finalPosZ);
                        machine.getModelCharacter().removeFromParent();
                        rootNode.attachChild(machine.getModelCharacter());
                        game.getShootables().attachChild(machine.getModelCharacter());
                        machine.updatePhysicsLocation();
                        toBeRemoved = true;
                        operator.playStopAnimation(false);
                        operator.setIsMoving(false);
                        operator.setMotionControl(null);
//                        System.out.println("DispOperatorMachineMovingTo - Activity:" + operator.getIdActivityAssigned() + " - Operator:" + operator.getIdOperator() + " - Machine:" + operator.getIdMachineAttached());
                        //game.getManageEvents().getArrEvents().get(operator.getIdActivityAssigned()).release();
                        if (!toMachineZone){
                            game.getManageEvents().getEvent(operator.getIdStrategyAssigned()).release();
                        }else{
                            operator.setStatus(Status.Idle);
                            machine.setStatus(Status.Idle);
                            machine.setMovingToMachineZone(false);
                            machine.updateTempToCurrentVirtualPosition();
                        }                        
//                        System.out.println("-- Finished moving, operator:" + operator.getIdOperator() + " - activity:" + operator.getIdActivityAssigned() + " - machine:" + operator.getIdMachineAttached());
                    }else{
                        machine.setCurrentLocationX((int)motionControl.getPath().getWayPoint(wayPointIndex).getX());
                        machine.setCurrentLocationZ((int)motionControl.getPath().getWayPoint(wayPointIndex).getZ());
                        operator.setCurrentLocationX((int)motionControl.getPath().getWayPoint(wayPointIndex).getX());
                        operator.setCurrentLocationZ((int)motionControl.getPath().getWayPoint(wayPointIndex).getZ());
                        operator.playStopAnimation(true);
                    }
//                    System.out.println("--- Operator:" + operator.getIdOperator() + " - Index:" + wayPointIndex + " - Moving:" + control.getPath().getWayPoint(wayPointIndex) + " - Machine:" + operator.getIdMachineAttached() + " - Activity:" + operator.getIdActivityAssigned());
                }
            });
        }else{
            System.out.println("Error in OperatorMachineMovingTo (not path found) - Operator:" + operator.getIdOperator() + " - Machine:" + machine.getIdMachine());
        }
        pathFinder = null;
    }

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
    
    private PhysicsJoint join(Node A, Node B, Vector3f connectionPoint) {
        Vector3f pivotA = A.worldToLocal(connectionPoint, new Vector3f());
        Vector3f pivotB = B.worldToLocal(connectionPoint, new Vector3f());
        ConeJoint joint = new ConeJoint(A.getControl(RigidBodyControl.class), B.getControl(RigidBodyControl.class), pivotA, pivotB);
        joint.setLimit(1f, 1f, 0);
        return joint;
    }
}
