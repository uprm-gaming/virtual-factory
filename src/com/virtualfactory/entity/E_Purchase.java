package com.virtualfactory.entity;

/**
 *
 * @author David
 */
public class E_Purchase extends E_Activity {
    private int idPurchase;
    private int idStation;
    private int idSupplier;
    private int orderPoint;
    private int defaultValueOP = 0;
    private int orderQuantity;
    private int defaultValueOQ = 0;
    private double accumulatedCosts = 0;

    public int getDefaultValueOP() {
        return defaultValueOP;
    }

    public void setDefaultValueOP(int defaultValueOP) {
        this.defaultValueOP = defaultValueOP;
    }

    public int getDefaultValueOQ() {
        return defaultValueOQ;
    }

    public void setDefaultValueOQ(int defaultValueOQ) {
        this.defaultValueOQ = defaultValueOQ;
    }

    public double getAccumulatedCosts() {
        return accumulatedCosts;
    }

    public void setAccumulatedCosts(double accumulatedCosts) {
        this.accumulatedCosts = accumulatedCosts;
    }

    public void addCosts(double cost){
        this.accumulatedCosts += cost;
    }
    
    public E_Purchase() {
        super();
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }
    
    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public int getOrderPoint() {
        return orderPoint;
    }

    public void setOrderPoint(int orderPoint) {
        this.orderPoint = orderPoint;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
    
}
