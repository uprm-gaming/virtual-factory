/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui;

/**
 *
 * @author David
 */
public class ListBoxMessages_TransportList_DB {
    private String idFromTo;
    private String fromToName;
    private String partName;
    private String quantity;
    
    public ListBoxMessages_TransportList_DB(final String idFromTo, final String fromToName, final String partName, final String quantity) {
        this.idFromTo = idFromTo;
        this.fromToName = fromToName;
        this.partName = partName;
        this.quantity = quantity;
    }

    public String getIdFromTo() {
        return idFromTo;
    }

    public void setIdFromTo(String idFromTo) {
        this.idFromTo = idFromTo;
    }

    public String getFromToName() {
        return fromToName;
    }

    public void setFromToName(String fromToName) {
        this.fromToName = fromToName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
}
