/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.screen.layer.components;

/**
 *
 * @author David
 */
public class ListBoxMessages_StationList_DB {
    private String idStation;
    private String stationName;
    private String partName;
    private String quantity;
    
    public ListBoxMessages_StationList_DB(final String idStation, final String stationName, final String partName, final String quantity) {
        this.idStation = idStation;
        this.stationName = stationName;
        this.partName = partName;
        this.quantity = quantity;
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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
