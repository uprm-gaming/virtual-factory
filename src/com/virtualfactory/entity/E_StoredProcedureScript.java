/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.entity;

/**
 *
 * @author David
 */
public class E_StoredProcedureScript {
    int idSPScript;
    String spName;
    int variablesNumber;
    String definition;
    int returnValue;
    String tableName;
    String spType;

    public int getIdSPScript() {
        return idSPScript;
    }

    public void setIdSPScript(int idSPScript) {
        this.idSPScript = idSPScript;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public int getVariablesNumber() {
        return variablesNumber;
    }

    public void setVariablesNumber(int variablesNumber) {
        this.variablesNumber = variablesNumber;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSpType() {
        return spType;
    }

    public void setSpType(String spType) {
        this.spType = spType;
    }
    
}
