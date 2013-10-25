/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

import edu.uprm.gaming.utils.TypeActivity;

/**
 *
 * @author David
 */
public class E_TransportStore extends E_Activity {
    private int idTransportStore;
    private int unitLoad;
    private int defaultValue = 0;
    private int idStationInitial;
    private int idStationEnd;

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public E_TransportStore() {
        super();
    }

//    public int getIdMachine() {
//        return idMachine;
//    }
//
//    public void setIdMachine(int idMachine) {
//        this.idMachine = idMachine;
//    }

    public int getIdStationEnd() {
        return idStationEnd;
    }

    public void setIdStationEnd(int idStationEnd) {
        this.idStationEnd = idStationEnd;
    }

    public int getIdStationInitial() {
        return idStationInitial;
    }

    public void setIdStationInitial(int idStationInitial) {
        this.idStationInitial = idStationInitial;
    }

    public int getIdTransportStore() {
        return idTransportStore;
    }

    public void setIdTransportStore(int idTransportStore) {
        this.idTransportStore = idTransportStore;
    }

    public int getUnitLoad() {
        return unitLoad;
    }

    public void setUnitLoad(int unitLoad) {
        this.unitLoad = unitLoad;
    }
    
}
