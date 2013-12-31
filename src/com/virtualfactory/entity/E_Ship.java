package com.virtualfactory.entity;

import com.virtualfactory.utils.Distributions;

/**
 *
 * @author David
 */
public class E_Ship extends E_Activity {
    private int idShip;
    private int idStation;
    private int loadQuantity;
    private double incomeAccumulated = 0;
    private String shippingTimeDistn;
    private double shippingTimeParameter1;
    private double shippingTimeParameter2;
    private double shippingTimeCalculated;
    
    public void calculateDistributionsTime(){
        shippingTimeCalculated = Distributions.calculateDist(shippingTimeDistn, shippingTimeParameter1, shippingTimeParameter2);
    }

    public double getShippingTimeCalculated() {
        return shippingTimeCalculated;
    }

    public void setShippingTimeCalculated(double shippingTimeCalculated) {
        this.shippingTimeCalculated = shippingTimeCalculated;
    }

    public String getShippingTimeDistn() {
        return shippingTimeDistn;
    }

    public void setShippingTimeDistn(String shippingTimeDistn) {
        this.shippingTimeDistn = shippingTimeDistn;
    }

    public double getShippingTimeParameter1() {
        return shippingTimeParameter1;
    }

    public void setShippingTimeParameter1(double shippingTimeParameter1) {
        this.shippingTimeParameter1 = shippingTimeParameter1;
    }

    public double getShippingTimeParameter2() {
        return shippingTimeParameter2;
    }

    public void setShippingTimeParameter2(double shippingTimeParameter2) {
        this.shippingTimeParameter2 = shippingTimeParameter2;
    }

    public double getIncomeAccumulated() {
        return incomeAccumulated;
    }

    public void setIncomeAccumulated(double incomeAccumulated) {
        this.incomeAccumulated = incomeAccumulated;
    }
    
    public void addIncomes(double income){
        this.incomeAccumulated += income;
    }

    public E_Ship() {
        super();
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public int getIdShip() {
        return idShip;
    }

    public void setIdShip(int idShip) {
        this.idShip = idShip;
    }

    public int getLoadQuantity() {
        return loadQuantity;
    }

    public void setLoadQuantity(int loadQuantity) {
        this.loadQuantity = loadQuantity;
    }
    
}
