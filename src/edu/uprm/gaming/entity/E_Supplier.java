/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author David
 */
public class E_Supplier {
    private int idSupplier;
    private String supplierDescription;
    private int supplierLocationX;
    private int supplierLocationY;
    private double serviceLevel;
    //private ArrayList<E_Catalog> arrCatalog;
    private Map<Integer, E_Catalog> arrCatalog;

    public Map<Integer, E_Catalog> getArrCatalog() {
        return arrCatalog;
    }

    public void setArrCatalog(Map<Integer, E_Catalog> arrCatalog) {
        this.arrCatalog = arrCatalog;
    }

//    public ArrayList<E_Catalog> getArrCatalog() {
//        return arrCatalog;
//    }
//
//    public void setArrCatalog(ArrayList<E_Catalog> arrCatalog) {
//        this.arrCatalog = arrCatalog;
//    }
    
    public E_Supplier(int idSupplier, String supplierDescription, int supplierLocationX, int supplierLocationY, int serviceLevel) {
        this.idSupplier = idSupplier;
        this.supplierDescription = supplierDescription;
        this.supplierLocationX = supplierLocationX;
        this.supplierLocationY = supplierLocationY;
        this.serviceLevel = serviceLevel;
    }

    public E_Supplier() {
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public double getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(double serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getSupplierDescription() {
        return supplierDescription;
    }

    public void setSupplierDescription(String supplierDescription) {
        this.supplierDescription = supplierDescription;
    }

    public int getSupplierLocationX() {
        return supplierLocationX;
    }

    public void setSupplierLocationX(int supplierLocationX) {
        this.supplierLocationX = supplierLocationX;
    }

    public int getSupplierLocationY() {
        return supplierLocationY;
    }

    public void setSupplierLocationY(int supplierLocationY) {
        this.supplierLocationY = supplierLocationY;
    }
    
}
