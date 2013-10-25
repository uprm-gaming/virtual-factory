/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

import edu.uprm.gaming.utils.OrderStates;

/**
 *
 * @author David
 */
public class E_Order {
    private int idOrder;
    private int idPart;
    private int quantity;
    private int timeUnits;
    private OrderStates state;
    private double time;
    private int timeToAppear;
    private double percentageVariabilityMin;
    private double percentageVariabilityMax;

    public E_Order() {
        state = OrderStates.Wait;
        time = 0;
    }

    public double getPercentageVariabilityMin() {
        return percentageVariabilityMin;
    }

    public void setPercentageVariabilityMin(double percentageVariability) {
        this.percentageVariabilityMin = percentageVariability;
    }

    public double getPercentageVariabilityMax() {
        return percentageVariabilityMax;
    }

    public void setPercentageVariabilityMax(double percentageVariabilityMax) {
        this.percentageVariabilityMax = percentageVariabilityMax;
    }

    public int getTimeToAppear() {
        return timeToAppear;
    }

    public void setTimeToAppear(int timeToAppear) {
        this.timeToAppear = timeToAppear;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public OrderStates getState() {
        return state;
    }

    public void setState(OrderStates state) {
        this.state = state;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTimeUnits() {
        return timeUnits;
    }

    public void setTimeUnits(int timeUnits) {
        this.timeUnits = timeUnits;
    }
    
}
