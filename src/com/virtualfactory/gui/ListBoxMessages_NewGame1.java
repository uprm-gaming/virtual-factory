/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui;

import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.Nifty;
import com.virtualfactory.utils.GameCategory;
import com.virtualfactory.utils.GameStatus;

/**
 *
 * @author David
 */
public class ListBoxMessages_NewGame1 {
    private String description;
    private String category;
    private String index;
    private String status;
    private NiftyImage iconEasy;
    private NiftyImage iconNormal;
    private NiftyImage iconHard;
    private NiftyImage iconSelected;
    private NiftyImage iconLocked;
    private NiftyImage iconUnlocked;
    private NiftyImage iconDone;
    private NiftyImage iconStatusSelected;
    private NiftyImage iconStatusSelected2;
    private String yourBestScore;
    private String gameBestScore;
    private int attemptsNumber;

    public ListBoxMessages_NewGame1(final Nifty nifty, final String index, final String description, final String gameCategory, final String gameStatus,
            final String yourBestScore, final String gameBestScore, final int attemptsNumber) {
        this.description = description;
        this.index = index;
        this.status = gameStatus;
        this.iconEasy = nifty.createImage("Interface/Activities/blue.png", false);
        this.iconNormal = nifty.createImage("Interface/Activities/green.png", false);
        this.iconHard = nifty.createImage("Interface/Activities/red.png", false);
        this.iconLocked = nifty.createImage("Interface/Activities/lock.png", false);
        this.iconUnlocked = nifty.createImage("Interface/Activities/unlock.png", false);
        this.iconDone = nifty.createImage("Interface/Activities/done.png", false);
        this.iconStatusSelected2 = nifty.createImage("Interface/Activities/empty.png", false);        
        if (gameCategory.equals(GameCategory.Easy.toString()))
            iconSelected = iconEasy;
        else
        if (gameCategory.equals(GameCategory.Normal.toString()))
            iconSelected = iconNormal;
        else
        if (gameCategory.equals(GameCategory.Hard.toString()))
            iconSelected = iconHard;
        category = gameCategory;
        if (gameStatus.equals(GameStatus.Locked.toString()))
            iconStatusSelected = iconLocked;        
        else
        if (gameStatus.equals(GameStatus.Unlocked.toString()) || gameStatus.equals(GameStatus.Completed.toString()))
            iconStatusSelected = iconUnlocked;

        if (gameStatus.equals(GameStatus.Completed.toString()))    
            iconStatusSelected2 = iconDone;
        this.yourBestScore = yourBestScore;
        this.gameBestScore = gameBestScore;
        this.attemptsNumber = attemptsNumber;
    }
    
    public String getTextIndex() {
        return  index;
    }
    
    public String getTextName() {
        return  description;
    }
        
    public NiftyImage getIcon(){
        return iconSelected;
    }
    
    public String getTextCategory() {
        return  category;
    }
    
    public NiftyImage getStatusIcon(){
        return iconStatusSelected;
    }
    
    public NiftyImage getStatusIcon2(){
        return iconStatusSelected2;
    }
    
    public String getStatus() {
        return  status;
    }

    public int getAttemptsNumber() {
        return attemptsNumber;
    }

    public String getGameBestScore() {
        return gameBestScore;
    }

    public String getYourBestScore() {
        return yourBestScore;
    }
    
}