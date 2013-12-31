package com.virtualfactory.entity;

/**
 *
 * @author David
 */
public class E_AssemblyDetails {
    private int idOutputPart;
    private int idInputPart;
    private int quantity;
    private String assemblyDescription;

    public E_AssemblyDetails(int idOutputPart, int idInputPart, int quantity, String assemblyDescription) {
        this.idOutputPart = idOutputPart;
        this.idInputPart = idInputPart;
        this.quantity = quantity;
        this.assemblyDescription = assemblyDescription;
    }

    public E_AssemblyDetails() {
    }

    public String getAssemblyDescription() {
        return assemblyDescription;
    }

    public void setAssemblyDescription(String assemblyDescription) {
        this.assemblyDescription = assemblyDescription;
    }

    public int getIdInputPart() {
        return idInputPart;
    }

    public void setIdInputPart(int idInputPart) {
        this.idInputPart = idInputPart;
    }

    public int getIdOutputPart() {
        return idOutputPart;
    }

    public void setIdOutputPart(int idOutputPart) {
        this.idOutputPart = idOutputPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
