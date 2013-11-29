/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.tools.SizeValue;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_AssemblyDetails;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Part;
import com.virtualfactory.gui.ListBoxMessages_Assembly;
import com.virtualfactory.gui.MessagesViewConverter_Assembly;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 *
 * @author David
 */
public class PartControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private TextField gameDescription;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    private WindowControl winControls;
    private E_Part part;
    private ArrayList<E_AssemblyDetails> arrAssembly;
    private boolean isVisible;
    private ListBox<String> listBoxPart;
    private Map<Integer, E_Part> mapParts;
    private Map<Integer, E_Part> tempParts;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winPartControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
        ((TextField)screen.findNiftyControl("assemblyPartRequired", TextField.class)).setEnabled(false);
        ((TextField)screen.findNiftyControl("assemblyQuantity", TextField.class)).setEnabled(false);
        ((ListBox<ListBoxMessages_Assembly>)screen.findNiftyControl("assemblySectionValue_WPC", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_Assembly());
        listBoxPart = ((ListBox<String>)screen.findNiftyControl("partsList_WPC", ListBox.class));
    }
    
    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
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
    
    public void loadWindowControl(GameEngine game,int index, Pair<Integer,Integer> position){
        this.gameEngine = game;
        if (index == -1){
            winControls.getElement().setVisible(false);
            winControls.getContent().hide();            
            isVisible = false;
        }else{
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
            }else{
                winControls.getElement().setConstraintX(new SizeValue("258px"));
                winControls.getElement().getParent().layoutElements();
            }
            winControls.getElement().setConstraintX(null);
            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
    
    private void loadValues(int index){
        if (index == -1){
            ((Label)screen.findNiftyControl("idValue_WPC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("nameValue_WPC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("unitValue_WPC", Label.class)).setText("_");
//            ((Label)screen.findNiftyControl("quantityPaletteValue_WPC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("currentStockValue_WPC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("priceForSaleValue_WPC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("outputQuantityValue_WPC", Label.class)).setText("");
            ((ListBox<ListBoxMessages_Assembly>)screen.findNiftyControl("assemblySectionValue_WPC", ListBox.class)).clear();
            arrAssembly = null;
            winControls.setTitle("Part");
            listBoxPart.clear();
        }else{
            listBoxPart.clear();
            mapParts = new HashMap<Integer, E_Part>();
            part = null;
            if (index != Params.partList) part = gameEngine.getGameData().getMapUserPart().get(index);
            tempParts = gameEngine.getGameData().getMapUserPart();
            index = getPartList();
            listBoxPart.selectItemByIndex(index);
        }        
    }
    
    private int getPartList(){
        listBoxPart.clear();
        mapParts.clear();
        int i=0;
        int newIndex = 0;
        for (E_Part tPart : tempParts.values()){
            listBoxPart.addItem("Part " + String.valueOf(i+1));
            mapParts.put(i, tPart);
            if (part != null)
                if (part.equals(tPart))
                    newIndex = i;
            i++;
        }
        return newIndex;
    }
    
    private void updateValues(int listBoxIndex){
        part = mapParts.get(listBoxIndex);
        winControls.setTitle("Part " + part.getIdPart());
        ((Label)screen.findNiftyControl("idValue_WPC", Label.class)).setText(String.valueOf(part.getIdPart()));
        ((Label)screen.findNiftyControl("nameValue_WPC", Label.class)).setText(part.getPartDescription());
        ((Label)screen.findNiftyControl("unitValue_WPC", Label.class)).setText(part.getUnit().toString());
        ((Label)screen.findNiftyControl("priceForSaleValue_WPC", Label.class)).setText(String.valueOf(part.getPriceForSale()));
        ((Label)screen.findNiftyControl("outputQuantityValue_WPC", Label.class)).setText(String.valueOf(part.getOutputQuantity()));
        arrAssembly = part.getArrAssemblyDetails();
        ((ListBox<ListBoxMessages_Assembly>)screen.findNiftyControl("assemblySectionValue_WPC", ListBox.class)).clear();
        ((ListBox<ListBoxMessages_Assembly>)screen.findNiftyControl("assemblySectionValue_WPC", ListBox.class)).addAllItems(getAssemblyNames());
        updateData();
    }
    
    @NiftyEventSubscriber(id="partsList_WPC")
    public void onSelectionListBox(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getSelectionIndices().size() > 0){
            updateValues(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))));
        }
    }
    
    private ArrayList<ListBoxMessages_Assembly> getAssemblyNames(){
        ArrayList<ListBoxMessages_Assembly> arrAssemblyNames = new ArrayList<ListBoxMessages_Assembly>();
        for (int i=0; i<arrAssembly.size(); i++){
            arrAssemblyNames.add(new ListBoxMessages_Assembly(" " + gameEngine.getGameData().getMapUserPart().get(arrAssembly.get(i).getIdInputPart()).getPartDescription() + " - " + arrAssembly.get(i).getIdInputPart(), String.valueOf(arrAssembly.get(i).getQuantity())));
        }
        return arrAssemblyNames;
    }

    public void updateData(){
        ((Label)screen.findNiftyControl("currentStockValue_WPC", Label.class)).setText(String.valueOf(part.getCurrentStock()));
    }
}