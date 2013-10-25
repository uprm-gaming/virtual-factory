/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty.controls;

/**
 *
 * @author David
 */
public class ListBoxMessages_Bucket {
    private String index;
    private String unit;
    private String sizeCapacity;
    private String partAssigned;
    private String unitsToArriveRemove;

    public ListBoxMessages_Bucket(final String index, final String unit, final String sizeCapacity, final String partAssigned, final String unitsToArriveRemove) {
        this.index = index;
        this.unit = unit;
        this.sizeCapacity = sizeCapacity;
        this.partAssigned = partAssigned;
        this.unitsToArriveRemove = unitsToArriveRemove;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSizeCapacity() {
        return sizeCapacity;
    }

    public void setSizeCapacity(String sizeCapacity) {
        this.sizeCapacity = sizeCapacity;
    }

    public String getPartAssigned() {
        return partAssigned;
    }

    public void setPartAssigned(String partAssigned) {
        this.partAssigned = partAssigned;
    }

    public String getUnitsToArriveRemove() {
        return unitsToArriveRemove;
    }

    public void setUnitsToArriveRemove(String unitsToArriveRemove) {
        this.unitsToArriveRemove = unitsToArriveRemove;
    }
    
}
