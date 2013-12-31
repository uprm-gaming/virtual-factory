package com.virtualfactory.entity;

/**
 *
 * @author David
 */
public class E_TerrainReserved {
    private int idTerrainReserved;
    private int locationX;
    private int locationZ;
    private int width;
    private int length;

    public E_TerrainReserved() {
    }

    public int getIdTerrainReserved() {
        return idTerrainReserved;
    }

    public void setIdTerrainReserved(int idTerrainReserved) {
        this.idTerrainReserved = idTerrainReserved;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(int locationZ) {
        this.locationZ = locationZ;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
}
