/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.strategy;

import edu.uprm.gaming.entity.E_Machine;
import edu.uprm.gaming.entity.E_Operator;
import edu.uprm.gaming.entity.E_Station;
import edu.uprm.gaming.utils.Status;

/**
 *
 *
 */
public interface EventStrategy {
    //FIXME: Sending the token every time is not very efficient. We will modify SimPack later to fix it.
//    public boolean validateResources(); 
//    public boolean validatePositions(); 
//    public double executeAlgorithm();
//    public void releaseResources();
    
    public String getType();
//    public String getValidateMessage();
//    public String getExecuteMessage();
//    public String getReleaseMessage();
//    public String getOperatorStatus();
    public int getIdActivity();
    public void addWorkCounter();
    public Object getData();
    public Status getStatus();
    public E_Operator getOperator();
    public E_Machine getMachine();
    public E_Station getStation();
    public void loadEvent(boolean movingAlone, int idOperator);
    public double execute();
    public void release();
    public StateMachine getStateMachine();
    public int getPriorityQueue();
    public int getIdStrategy();
    public void updateStrategy();
}
