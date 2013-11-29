/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.Nifty;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.SlotStatus;
import com.virtualfactory.utils.Utils;
/**
 *
 * @author David
 */
public class ListBoxMessages_Slot {
    private String partDescription;
    private int quantity;
    private int partsNumber;
    private NiftyImage icon;

    public ListBoxMessages_Slot(final String partDescription, final int quantity, final int partsNumber, final Nifty nifty, final String partColor) {
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.partsNumber = partsNumber;
        this.icon = nifty.createImage("Interface/Part/" + partColor + ".png", false);
    }
    
    public String getPartDescription() {
        return partDescription;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public int getPartsNumber() {
        return partsNumber;
    }

    public NiftyImage getIcon() {
        return icon;
    }

    public void setIcon(NiftyImage icon) {
        this.icon = icon;
    }

    public void setPartsNumber(int partsNumber) {
        this.partsNumber = partsNumber;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
