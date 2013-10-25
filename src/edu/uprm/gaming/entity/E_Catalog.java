/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

import edu.uprm.gaming.utils.Distributions;
import edu.uprm.gaming.utils.Unit;

/**
 *
 * @author David
 */
public class E_Catalog {
    private int idSupplier;
    private int idPart;
    private double quality;
    private String productionDistn;
    private double productionParameter1;
    private double productionParameter2;
    private double productionCalculated;
    private int priceFunction1Limit;
    private double priceFunction1Charge;
    private int priceFunction2Limit;
    private double priceFunction2Charge;
    private int priceFunction3Limit;
    private double priceFunction3Charge;

    public E_Catalog(int idSupplier, int idPart, double quality, String productionDistn, double productionParameter1, double productionParameter2, int priceFunction1Limit, double priceFunction1Charge, int priceFunction2Limit, double priceFunction2Charge, int priceFunction3Limit, double priceFunction3Charge) {
        this.idSupplier = idSupplier;
        this.idPart = idPart;
        this.quality = quality;
        this.productionDistn = productionDistn;
        this.productionParameter1 = productionParameter1;
        this.productionParameter2 = productionParameter2;
        this.priceFunction1Limit = priceFunction1Limit;
        this.priceFunction1Charge = priceFunction1Charge;
        this.priceFunction2Limit = priceFunction2Limit;
        this.priceFunction2Charge = priceFunction2Charge;
        this.priceFunction3Limit = priceFunction3Limit;
        this.priceFunction3Charge = priceFunction3Charge;
    }

    public E_Catalog() {
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public double getPriceFunction1Charge() {
        return priceFunction1Charge;
    }

    public void setPriceFunction1Charge(double priceFunction1Charge) {
        this.priceFunction1Charge = priceFunction1Charge;
    }

    public int getPriceFunction1Limit() {
        return priceFunction1Limit;
    }

    public void setPriceFunction1Limit(int priceFunction1Limit) {
        this.priceFunction1Limit = priceFunction1Limit;
    }

    public double getPriceFunction2Charge() {
        return priceFunction2Charge;
    }

    public void setPriceFunction2Charge(double priceFunction2Charge) {
        this.priceFunction2Charge = priceFunction2Charge;
    }

    public int getPriceFunction2Limit() {
        return priceFunction2Limit;
    }

    public void setPriceFunction2Limit(int priceFunction2Limit) {
        this.priceFunction2Limit = priceFunction2Limit;
    }

    public double getPriceFunction3Charge() {
        return priceFunction3Charge;
    }

    public void setPriceFunction3Charge(double priceFunction3Charge) {
        this.priceFunction3Charge = priceFunction3Charge;
    }

    public int getPriceFunction3Limit() {
        return priceFunction3Limit;
    }

    public void setPriceFunction3Limit(int priceFunction3Limit) {
        this.priceFunction3Limit = priceFunction3Limit;
    }

    public String getProductionDistn() {
        return productionDistn;
    }

    public void setProductionDistn(String productionDistn) {
        this.productionDistn = productionDistn;
    }

    public double getProductionParameter1() {
        return productionParameter1;
    }

    public void setProductionParameter1(double productionParameter1) {
        this.productionParameter1 = productionParameter1;
    }

    public double getProductionParameter2() {
        return productionParameter2;
    }

    public void setProductionParameter2(double productionParameter2) {
        this.productionParameter2 = productionParameter2;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }
    
    public void calculateDistributionsTime(){
        productionCalculated = Distributions.calculateDist(productionDistn, productionParameter1, productionParameter2);
    }

    public double getProductionCalculated() {
        return productionCalculated;
    }

    public void setProductionCalculated(double productionCalculated) {
        this.productionCalculated = productionCalculated;
    }
    
}
