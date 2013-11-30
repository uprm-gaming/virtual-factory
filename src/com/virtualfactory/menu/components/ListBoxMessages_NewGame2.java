/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.menu.components;

import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import com.virtualfactory.utils.GameCategory;
import com.virtualfactory.utils.GameObjective;
import de.lessvoid.nifty.controls.TextField;
/**
 *
 * @author David
 */
public class ListBoxMessages_NewGame2 {
    private String text;
    private String textField;
    private NiftyImage iconPart;
    private NiftyImage iconMoney;
    private NiftyImage iconTime;
    private NiftyImage iconSelected;
    private Nifty nifty;
    private Screen screen;

    public ListBoxMessages_NewGame2(final Nifty nifty, final Screen screen, final String text, final String objectiveType) {
        this.text = text;
        this.iconPart = nifty.createImage("Interface/Activities/part.png", false);
        this.iconMoney = nifty.createImage("Interface/Activities/money.png", false);
        this.iconTime = nifty.createImage("Interface/Activities/time.png", false);
        if (objectiveType.equals(GameObjective.Part.toString()))
            iconSelected = iconPart;
        else
        if (objectiveType.equals(GameObjective.Money.toString()))
            iconSelected = iconMoney;
        else
        if (objectiveType.equals(GameObjective.Time.toString()))
            iconSelected = iconTime;
        textField = objectiveType;
         screen.findNiftyControl(screen.findElementByName("#value").getId(), TextField.class).setText(textField);
        this.nifty = nifty;
        this.screen = screen;
    }
    
    public String getText() {
        return  text;
    }
    
    public String getTextFieldValue(){
        return screen.findNiftyControl(screen.findElementByName("#value").getId(), TextField.class).getText();
    }
    
    public NiftyImage getIcon(){
        return iconSelected;
    }
}