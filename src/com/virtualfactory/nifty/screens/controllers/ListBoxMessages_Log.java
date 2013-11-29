/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.Nifty;
import com.virtualfactory.utils.MessageType;
/**
 *
 * @author David
 */
public class ListBoxMessages_Log {
    private String time;
    private NiftyImage iconError;
    private NiftyImage iconInfo;
    private NiftyImage iconNotification;
    private NiftyImage iconSelected;
    private String message;
    
    public ListBoxMessages_Log(final Nifty nifty, final String time, final MessageType messageType, final String message) {
        this.time = time;
        this.message = message;
        this.iconError = nifty.createImage("Interface/Log/error.png", false);
        this.iconInfo = nifty.createImage("Interface/Log/info.png", false);
        this.iconNotification = nifty.createImage("Interface/Log/notification.png", false);
        if (messageType.equals(MessageType.Error))
            iconSelected = iconError;
        else
        if (messageType.equals(MessageType.Info))
            iconSelected = iconInfo;
        else
            iconSelected = iconNotification;
    }

    public NiftyImage getIcon() {
        return iconSelected;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
    
}
