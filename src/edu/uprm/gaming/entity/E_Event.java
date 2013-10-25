/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

/**
 *
 * @author David
 */
public class E_Event {
    private int idEvent;
    private double timeFactorForSpeed;
    private double timeMissing;
    private int idOperator;

    public E_Event(int idEvent, double timeFactorForSpeed, double timeMissing, int idOperator) {
        this.idEvent = idEvent;
        this.timeFactorForSpeed = timeFactorForSpeed;
        this.timeMissing = timeMissing;
        this.idOperator = idOperator;
    }

    public E_Event() {
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(int idOperator) {
        this.idOperator = idOperator;
    }

    public double getTimeFactorForSpeed() {
        return timeFactorForSpeed;
    }

    public void setTimeFactorForSpeed(double timeFactorForSpeed) {
        this.timeFactorForSpeed = timeFactorForSpeed;
    }

    public double getTimeMissing() {
        return timeMissing;
    }

    public void setTimeMissing(double timeMissing) {
        this.timeMissing = timeMissing;
    }
}
