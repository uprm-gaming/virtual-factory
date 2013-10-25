/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public final class StateMachine {
    public enum Status 
    {FREE, BUSY, DONE}
    private Map<Integer, String> states;
    private int currentState;
    private Status currentStatus;
    
    public StateMachine(){
        states = new HashMap<Integer, String>();
        reset();
    }
    
    public void add(String state){
        states.put(states.size(), state);
    }
        
    public String getState(){
        return states.get(currentState);
    }
    
    public int getStateIndex(){
        return currentState;
    }
    
    public void reset(){
        currentState = 0;
        currentStatus = Status.FREE;
    }
    
    public void update(Status uState){
        if (uState.equals(Status.DONE)){
            currentStatus = Status.FREE;
            currentState++;
            if (currentState >= states.size())
                reset();
        }else{
            currentStatus = uState;
        }        
    }
    
    public Status getStatus(){
        return currentStatus;
    }
    
    public Map<Integer, String> getStates(){
        return states;
    }
}
