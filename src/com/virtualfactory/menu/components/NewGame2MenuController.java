/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.menu.components;

import com.virtualfactory.gui.MessagesViewConverter_NewGame2;
import com.virtualfactory.gui.ListBoxMessages_NewGame2;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
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
public class NewGame2MenuController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private ListBox<ListBoxMessages_NewGame2> listBoxObjectives;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.listBoxObjectives = getListBox("listBox_NG2MD");
        this.listBoxObjectives.setStyle("nifty-listbox-item");
        this.listBoxObjectives.setListBoxViewConverter(new MessagesViewConverter_NewGame2());
        this.gameEngine = ((MenuScreenController)screen.getScreenController()).getGameEngine();
        //System.out.println("======== NewGameMenuController ========");
    }
    
    @SuppressWarnings("unchecked")
    private ListBox<ListBoxMessages_NewGame2> getListBox(final String name) {
        return (ListBox<ListBoxMessages_NewGame2>) screen.findNiftyControl(name, ListBox.class);
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
    
    protected void updateControls_stage2(){
        //load parts and/or money and time
        listBoxObjectives.addItem(new ListBoxMessages_NewGame2(nifty, screen, "xxxxxxxxx", "Money"));
        listBoxObjectives.addItem(new ListBoxMessages_NewGame2(nifty, screen, "xx2222xxx", "Money"));
        listBoxObjectives.addItem(new ListBoxMessages_NewGame2(nifty, screen, "yyyyyyyyy", "Time"));
        listBoxObjectives.addItem(new ListBoxMessages_NewGame2(nifty, screen, "yy3333yyy", "Time"));
        listBoxObjectives.addItem(new ListBoxMessages_NewGame2(nifty, screen, "zzzzzzzzz", "Part"));
        listBoxObjectives.addItem(new ListBoxMessages_NewGame2(nifty, screen, "zz4444zzz", "Part"));
    }
    
    @NiftyEventSubscriber(id="playButton_NG2MD")
    public void onPlayButtonNG2Clicked(final String id, final ButtonClickedEvent event) {
        int i=0;
        for (ListBoxMessages_NewGame2 temp : listBoxObjectives.getItems()){
            System.out.println(i + ": " + temp.getTextFieldValue());
            i++;
        }
        Element currentElement = screen.findElementByName("dialogNewGameStage2Menu");
        currentElement.hide();
        screen.endScreen(null);
//        this.gameEngine.playGame(null);
    }
    
    @NiftyEventSubscriber(id="cancelButton_NG2MD")
    public void onCancelButtonNG2Clicked(final String id, final ButtonClickedEvent event) {
        Element nextElement = screen.findElementByName("dialogMainMenu");
        MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
        mainMenu.updateControls();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogNewGameStage2Menu");
        currentElement.hide();
        screen.findElementByName("dialogNewGameStage2Menu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogMainMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
    
    @NiftyEventSubscriber(id="backButton_NG2MD")
    public void onBackButtonNG2Clicked(final String id, final ButtonClickedEvent event) {
        Element nextElement = screen.findElementByName("dialogNewGameStage1Menu");
        NewGame1MenuController newGame1 = nextElement.getControl(NewGame1MenuController.class);
        newGame1.updateControls_stage1();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogNewGameStage2Menu");
        currentElement.hide();
        screen.findElementByName("dialogNewGameStage2Menu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogNewGameStage1Menu").startEffect(EffectEventId.onCustom, null, "selected");
    }
}