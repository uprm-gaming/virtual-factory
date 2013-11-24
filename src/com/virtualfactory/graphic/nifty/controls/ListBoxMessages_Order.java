/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.graphic.nifty.controls;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.Nifty;
import com.virtualfactory.utils.OrderStates;
/**
 *
 * @author David
 */
public class ListBoxMessages_Order {
    private String index;
    private String part;
    private String quantity;
    private String timeOver;
    private NiftyImage iconWait;
    private NiftyImage iconDone;
    private NiftyImage iconFail;
    private NiftyImage iconWorking;
    private NiftyImage iconSelected;
    private OrderStates currentState;
    
    public ListBoxMessages_Order(final Nifty nifty, final String index, final String part, final String quantity, final String timeOver, final String icon) {
        this.index = index;
        this.part = part;
        this.quantity = quantity;
        this.timeOver = timeOver;
        this.iconWait = nifty.createImage("Interface/Principal/wait.png", false);
        this.iconDone = nifty.createImage("Interface/Principal/done.png", false);
        this.iconFail = nifty.createImage("Interface/Principal/fail.png", false);
        this.iconWorking = nifty.createImage("Interface/Principal/working.png", false);
        if (icon.equals(OrderStates.Wait.toString()) || icon.equals(OrderStates.Assigned.toString()))
            iconSelected = iconWait;
        else
        if (icon.equals(OrderStates.Done.toString()))
            iconSelected = iconDone;
        else
        if (icon.equals(OrderStates.Fail.toString()))
            iconSelected = iconFail;
        else
            iconSelected = iconWorking;
        currentState = OrderStates.valueOf(icon);
    }
    
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    
    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }
    
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    public String getTimeOver() {
        return timeOver;
    }

    public void setTimeOver(String timeOver) {
        this.timeOver = timeOver;
    }
    
    public NiftyImage getIcon(){
        return iconSelected;
    }
    
    public OrderStates getCurrentState(){
        return currentState;
    }

    public void setIcon(String icon) {
        currentState = OrderStates.valueOf(icon);
        if (icon.equals(OrderStates.Wait.toString()) || icon.equals(OrderStates.Assigned.toString()))
            iconSelected = iconWait;
        else
        if (icon.equals(OrderStates.Done.toString()))
            iconSelected = iconDone;
        else
        if (icon.equals(OrderStates.Fail.toString()))
            iconSelected = iconFail;
        else
            iconSelected = iconWorking;
    }
    
}
