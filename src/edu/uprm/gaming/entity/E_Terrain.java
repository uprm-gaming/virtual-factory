/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

import java.util.Map;

/**
 *
 * @author David
 */
public class E_Terrain {
    private int idTerrain;
    private String name;
    private String fileName;
    private double localScale;
    private Map<Integer, E_TerrainReserved> arrZones;
    private String model;

    public E_Terrain() {
    }

    public Map<Integer, E_TerrainReserved> getArrZones() {
        return arrZones;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setArrZones(Map<Integer, E_TerrainReserved> arrZones) {
        this.arrZones = arrZones;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public double getLocalScale() {
        return localScale;
    }

    public void setLocalScale(double localScale) {
        this.localScale = localScale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
