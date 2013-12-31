package com.virtualfactory.entity;

import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.Unit;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class E_Part {
    private int idPart;
    private String partDescription;
    private Unit unit;
    private double volume;
    private double weight;
    private int quantityPalette;
    private int currentStock;
    private double priceForSale;
    private Owner owner;
    private String partDesign;
    private double partDesignScale;
    private String partDesignColor;
    private ArrayList<E_AssemblyDetails> arrAssemblyDetails;
    private double factor;
    private int outputQuantity;
 
    public ArrayList<E_AssemblyDetails> getArrAssemblyDetails() {
        return arrAssemblyDetails;
    }

    public void setArrAssemblyDetails(ArrayList<E_AssemblyDetails> arrAssemblyDetails) {
        this.arrAssemblyDetails = arrAssemblyDetails;
    }

    public int getOutputQuantity() {
        return outputQuantity;
    }

    public void setOutputQuantity(int outputQuantity) {
        this.outputQuantity = outputQuantity;
    }

    public E_Part(int idPart, String partDescription, Unit unit, double volume, double weight, int quantityPalette, int currentStock, double priceForSale, Owner owner) {
        this.idPart = idPart;
        this.partDescription = partDescription;
        this.unit = unit;
        this.volume = volume;
        this.weight = weight;
        this.quantityPalette = quantityPalette;
        this.currentStock = currentStock;
        this.priceForSale = priceForSale;
        this.owner = owner;
    }

    public E_Part() {
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public String getPartDesignColor() {
        return partDesignColor;
    }

    public void setPartDesignColor(String partDesignColor) {
        this.partDesignColor = partDesignColor;
    }
    
    public double getPartDesignScale() {
        return partDesignScale;
    }

    public void setPartDesignScale(double partDesignScale) {
        this.partDesignScale = partDesignScale;
    }

    public String getPartDesign() {
        return partDesign;
    }

    public void setPartDesign(String partDesign) {
        this.partDesign = partDesign;
    }
    
    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public double getPriceForSale() {
        return priceForSale;
    }

    public void setPriceForSale(double priceForSale) {
        this.priceForSale = priceForSale;
    }

    public int getQuantityPalette() {
        return quantityPalette;
    }

    public void setQuantityPalette(int quantityPalette) {
        this.quantityPalette = quantityPalette;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
}
