/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.entity;

import com.virtualfactory.utils.TypeActivity;

/**
 *
 * @author David
 */
public class E_Operation extends E_Activity {
    private int idOperation;
    private String operationDescription;
//    private String processingDistn;
//    private double processingParameter1;
//    private double processingParameter2;
    private int productionPolicy;
//    private int idTypeOperation;
//    private int idMachine;
    private int idStation;
    private int quantityOutput;

    public E_Operation() {
        super();
    }

//    public int getIdMachine() {
//        return idMachine;
//    }
//
//    public void setIdMachine(int idMachine) {
//        this.idMachine = idMachine;
//    }

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

//    public int getIdTypeOperation() {
//        return idTypeOperation;
//    }
//
//    public void setIdTypeOperation(int idTypeOperation) {
//        this.idTypeOperation = idTypeOperation;
//    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

//    public String getProcessingDistn() {
//        return processingDistn;
//    }
//
//    public void setProcessingDistn(String processingDistn) {
//        this.processingDistn = processingDistn;
//    }
//
//    public double getProcessingParameter1() {
//        return processingParameter1;
//    }
//
//    public void setProcessingParameter1(double processingParameter1) {
//        this.processingParameter1 = processingParameter1;
//    }
//
//    public double getProcessingParameter2() {
//        return processingParameter2;
//    }
//
//    public void setProcessingParameter2(double processingParameter2) {
//        this.processingParameter2 = processingParameter2;
//    }

    public int getProductionPolicy() {
        return productionPolicy;
    }

    public void setProductionPolicy(int productionPolicy) {
        this.productionPolicy = productionPolicy;
    }

    public int getQuantityOutput() {
        return quantityOutput;
    }

    public void setQuantityOutput(int quantityOutput) {
        this.quantityOutput = quantityOutput;
    }
    
}
