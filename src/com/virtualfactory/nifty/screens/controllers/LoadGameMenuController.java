/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.effects.EffectEventId;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Game;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author David
 */
public class LoadGameMenuController implements Controller {
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private ListBox<String> listBox;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.screen = screen;
        this.continuePlayingButton = screen.findNiftyControl("continuePlayingButton_LGMD", Button.class);
        this.cancelButton = screen.findNiftyControl("cancelButton_LGMD", Button.class);
        this.listBox = getListBox("listBox_LGMD");
        this.gameEngine = ((MenuScreenController)screen.getScreenController()).getGameEngine();
    }
    
    @Override
    public void init(final Properties parameter, final Attributes controlDefinitionAttributes) {
        
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onFocus(final boolean getFocus) {
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    protected void updateControls(){
        if (this.gameEngine.getGameData().getPlayer() != null){
            arrGames = this.gameEngine.getGameData().loadGamesOfAPlayer(this.gameEngine.getGameData().getPlayer().getIdPlayer());
            E_Game game;
            listBox.clear();
            for (int i=0; i<arrGames.size(); i++){
                game = arrGames.get(i);
                listBox.addItem((i+1) +".  $" + game.getCurrentMoney() + "  -  " + game.getCurrentMonth() + "m "
                        + game.getCurrentDay() + "d " + game.getCurrentHour() + ":" + game.getCurrentMinute() + "  -  " + game.getDateTime() );
            }            
        }
    }
    
    @SuppressWarnings("unchecked")
    private ListBox<String> getListBox(final String name) {
        return (ListBox<String>) screen.findNiftyControl(name, ListBox.class);
    }
    
    @NiftyEventSubscriber(id="continuePlayingButton_LGMD")
    public void onLoadGameButtonClicked(final String id, final ButtonClickedEvent event) {
        if (listBox.getFocusItemIndex() != -1){
            Element currentElement = screen.findElementByName("dialogLoadGameMenu");
            currentElement.hide();
            screen.endScreen(null);
            this.gameEngine.playGame(arrGames.get(listBox.getFocusItemIndex()),false);
        }
    }
    
    @NiftyEventSubscriber(id="cancelButton_LGMD")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        Element nextElement = screen.findElementByName("dialogMainMenu");
        MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
        mainMenu.updateControls();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogLoadGameMenu");
        currentElement.hide();
        screen.findElementByName("dialogLoadGameMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogMainMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
}
