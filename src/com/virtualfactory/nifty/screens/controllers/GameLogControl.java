/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.nifty.CommonBuilders;
import com.virtualfactory.utils.MessageType;
import com.virtualfactory.utils.Pair;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author David
 */
public class GameLogControl implements Controller {
    private static Nifty nifty;
    private static Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private static GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
    private static ArrayList<ListBoxMessages_Log> arrMessages;

    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winGameLogControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        Attributes y = new Attributes();
        y.set("closeable", "false");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, y);
        isVisible = false;
        ((TextField)screen.findNiftyControl("time_WGLC", TextField.class)).setEnabled(false);
        ((TextField)screen.findNiftyControl("message_WGLC", TextField.class)).setEnabled(false);
        ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_Log());
       
    }
    
    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public void init(Properties parameter, Attributes controlDefinitionAttributes) {
        
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onFocus(boolean getFocus) {
       
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
         return false;
    }
    
    public void loadWindowControl(GameEngine game,int index, Pair<Integer,Integer> position){
        this.gameEngine = game;
        if (index == -1){
            winControls.getElement().setVisible(false);
            winControls.getContent().hide(); 
            isVisible = false;
        }
        else {
            winControls.getElement().setVisible(true);
            winControls.getContent().show();
            isVisible = true;
            if (position != null){
                if (winControls.getWidth() + position.getFirst() > gameEngine.app.getGuiViewPort().getCamera().getWidth())
                    position.setFirst(gameEngine.app.getGuiViewPort().getCamera().getWidth() - winControls.getWidth());
                if (winControls.getHeight() + position.getSecond() > gameEngine.app.getGuiViewPort().getCamera().getHeight())
                    position.setSecond(gameEngine.app.getGuiViewPort().getCamera().getHeight() - winControls.getHeight());
                winControls.getElement().setConstraintX(new SizeValue(position.getFirst() + "px"));
                winControls.getElement().setConstraintY(new SizeValue(position.getSecond() + "px"));
                winControls.getElement().getParent().layoutElements();
            }            
//            winControls.getElement().setConstraintX(null);
//            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
    
   public void showHide(){
        nifty.getScreen("layerScreen").findElementByName("winGameLogControl").hide();
          nifty.getScreen("layerScreen").findElementByName("LogLabel").show();
    }
  
   public void HideWindow(){
          nifty.getScreen("layerScreen").findElementByName("winGameLogControl").show();
          nifty.getScreen("layerScreen").findElementByName("LogLabel").hide();

   }
    
    public void cleanMessages(){
        arrMessages.clear();
    }
    
    private void loadValues(int index){
        if (index == -1){
            ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).clear();
        }else{
            ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).clear();
            if (arrMessages == null) arrMessages = new ArrayList<ListBoxMessages_Log>();
            ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).addAllItems(arrMessages);
            ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).setFocusItemByIndex(arrMessages.size()-1);
        }        
    }
    
    public static void addMessage(MessageType messageType, String message){
        ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).clear();
        if (arrMessages == null) return;
        arrMessages.add(new ListBoxMessages_Log(nifty, gameEngine.getGameData().getCurrentTimeGame(), messageType, message));
        ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).addAllItems(arrMessages);
        ((ListBox<ListBoxMessages_Log>)screen.findNiftyControl("gameLog_WGLC", ListBox.class)).setFocusItemByIndex(arrMessages.size()-1);
    }
    
@NiftyEventSubscriber(id="winGLC_Element")
public void onClick(String id, NiftyMousePrimaryClickedEvent event)
   {
         System.out.println("lol");
}

}
