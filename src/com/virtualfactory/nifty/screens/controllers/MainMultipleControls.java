/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.nifty.CommonBuilders;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import java.util.Properties;

/**
 *
 * @author David
 */
public class MainMultipleControls  implements Controller {
    private Nifty nifty;
    private Screen screen;
    private GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
    private NiftyImage flowChartImage;
    private String currentButton = "";

    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
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
    
    public void loadMultipleControls(){
        loadButtons();
    }

    private void loadButtons(){
        String panelName = "parent_SMC";
        new PanelBuilder("child_Purchase_SMC"){{
            childLayoutVertical();
            control(new ButtonBuilder("menuButton_Purchase", "Purchase"){{ width("*"); }});
        }}.build(nifty, screen, screen.findElementByName(panelName));
        
        new PanelBuilder("child_Storage_SMC"){{
            childLayoutVertical();
            control(new ButtonBuilder("menuButton_Storage", "Storage"){{ width("*"); }});
        }}.build(nifty, screen, screen.findElementByName(panelName));
        
        new PanelBuilder("child_Transport_SMC"){{
            childLayoutVertical();
            control(new ButtonBuilder("menuButton_Transport", "Transport"){{ width("*"); }});
        }}.build(nifty, screen, screen.findElementByName(panelName));
                
        new PanelBuilder("child_Machine_SMC"){{
            childLayoutVertical();
            control(new ButtonBuilder("menuButton_Machine", "Machine"){{ width("*"); }});
        }}.build(nifty, screen, screen.findElementByName(panelName));
        
        new PanelBuilder("child_Equipment_SMC"){{
            childLayoutVertical();
            control(new ButtonBuilder("menuButton_Equipment", "Equipment"){{ width("*"); }});
        }}.build(nifty, screen, screen.findElementByName(panelName));
        
        screen.findElementByName(panelName).setHeight(100);
        nifty.executeEndOfFrameElementActions();
    }
    
    @NiftyEventSubscriber(pattern="menuButton_.*")
    public void onLoadButtonsClicked(final String id, final ButtonClickedEvent event) {
        if (screen.findElementByName("childContainer_Purchase_SMC") != null)   screen.findElementByName("childContainer_Purchase_SMC").markForRemoval();
        if (screen.findElementByName("childContainer_Storage_SMC") != null)   screen.findElementByName("childContainer_Storage_SMC").markForRemoval();
        if (screen.findElementByName("childContainer_Transport_SMC") != null)   screen.findElementByName("childContainer_Transport_SMC").markForRemoval();
        if (screen.findElementByName("childContainer_Machine_SMC") != null)   screen.findElementByName("childContainer_Machine_SMC").markForRemoval();
        if (screen.findElementByName("childContainer_Equipment_SMC") != null)   screen.findElementByName("childContainer_Equipment_SMC").markForRemoval();
        nifty.executeEndOfFrameElementActions();
        if (id.equals(currentButton)){
            currentButton = "";
            screen.findElementByName("parent_SMC").setHeight(100);
            nifty.executeEndOfFrameElementActions();
            return;
        }
        if (id.equals("menuButton_Purchase"))
            loadPurchaseScreen();
        else
        if (id.equals("menuButton_Storage"))
            loadStorageScreen();
        else
        if (id.equals("menuButton_Transport"))
            loadTransportScreen();
        else
        if (id.equals("menuButton_Machine"))
            loadMachineScreen();
        else
        if (id.equals("menuButton_Equipment"))
            loadEquipmentScreen();
        currentButton = id;
        screen.findElementByName("parent_SMC").setHeight(450);
        nifty.executeEndOfFrameElementActions();
    }
    
    private void loadPurchaseScreen(){
        new PanelBuilder("childContainer_Purchase_SMC"){{
            childLayoutVertical();
            
        }}.build(nifty, screen, screen.findElementByName("child_Purchase_SMC"));
    }
    
    private void loadStorageScreen(){
        new PanelBuilder("childContainer_Storage_SMC"){{
            childLayoutVertical();
            
        }}.build(nifty, screen, screen.findElementByName("child_Storage_SMC"));
    }
    
    private void loadTransportScreen(){
        new PanelBuilder("childContainer_Transport_SMC"){{
            childLayoutVertical();
            
        }}.build(nifty, screen, screen.findElementByName("child_Transport_SMC"));
    }
    
    private void loadMachineScreen(){
        new PanelBuilder("childContainer_Machine_SMC"){{
            childLayoutVertical();
            
        }}.build(nifty, screen, screen.findElementByName("child_Machine_SMC"));
    }
    
    private void loadEquipmentScreen(){
        new PanelBuilder("childContainer_Equipment_SMC"){{
            childLayoutVertical();
            
        }}.build(nifty, screen, screen.findElementByName("child_Equipment_SMC"));
    }
}
