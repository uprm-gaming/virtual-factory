/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui;

import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.Nifty;
import com.virtualfactory.utils.TypeActivity;
/**
 *
 * @author David
 */
public class ListBoxMessages {
    private String message;
    private NiftyImage iconTransport;
    private NiftyImage iconStore;
    private NiftyImage iconPurchase;
    private NiftyImage iconOperation;
    private NiftyImage iconShip;
    private NiftyImage iconOperator;
    private NiftyImage iconSelected;

    public ListBoxMessages(final Nifty nifty, final String message, final String typeActivity) {
        this.message = message;
        this.iconTransport = nifty.createImage("Interface/Activities/chatIconTransport.png", false);
        this.iconStore = nifty.createImage("Interface/Activities/chatIconStore.png", false);
        this.iconPurchase = nifty.createImage("Interface/Activities/chatIconPurchase.png", false);
        this.iconOperation = nifty.createImage("Interface/Activities/chatIconOperation.png", false);
        this.iconOperator = nifty.createImage("Interface/Activities/iconOperator.png", false);
        this.iconShip = nifty.createImage("Interface/Activities/chatIconShip.png", false);
        if (typeActivity.equals(TypeActivity.Transport.toString()))
            iconSelected = iconTransport;
        else
        if (typeActivity.equals(TypeActivity.Store.toString()))
            iconSelected = iconStore;
        else
        if (typeActivity.equals(TypeActivity.Purchase.toString()))
            iconSelected = iconPurchase;
        else
        if (typeActivity.equals(TypeActivity.Operation.toString()))
            iconSelected = iconOperation;
        else
        if (typeActivity.equals(TypeActivity.Ship.toString()))
            iconSelected = iconShip;
        else
            iconSelected = iconOperator;
    }
    
    public String getText() {
        return  message;
    }
    
    public NiftyImage getIcon(){
        return iconSelected;
    }
}
