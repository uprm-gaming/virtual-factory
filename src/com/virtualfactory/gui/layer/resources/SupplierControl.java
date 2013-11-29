/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui.layer.resources;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
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
import com.virtualfactory.entity.E_Catalog;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Supplier;
import com.virtualfactory.utils.Distributions;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Unit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 *
 * @author David
 */
public class SupplierControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private TextField gameDescription;
    private ListBox<String> listBoxGames;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    private WindowControl winControls;
    private ArrayList<E_Catalog> arrCatalog = null;
    private E_Supplier supplier = null;
    private boolean isVisible;
    private ListBox<String> listBoxSupplier;
    private Map<Integer, E_Supplier> mapSupplier;
    private Map<Integer, E_Supplier> tempSupplier;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winSupplierControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
        listBoxSupplier = ((ListBox<String>)screen.findNiftyControl("suppliersList_WSuC", ListBox.class));
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
            ((Label)screen.findNiftyControl("idValue_WSuC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("nameValue_WSuC", Label.class)).setText("_");
            ((ListBox<String>)screen.findNiftyControl("catalogSectionValue_WSuC", ListBox.class)).clear();
            arrCatalog = null;
            loadCatalogData("");
            winControls.setTitle("Supplier");
            listBoxSupplier.clear();
        }else{
            listBoxSupplier.clear();
            mapSupplier = new HashMap<Integer, E_Supplier>();
            supplier = null;
            if (index != Params.supplierList) supplier = gameEngine.getGameData().getMapGameSupplier().get(index);
            tempSupplier = gameEngine.getGameData().getMapGameSupplier();
            index = getSupplierList();
            listBoxSupplier.selectItemByIndex(index);
        }        
    }
    
    private int getSupplierList(){
        listBoxSupplier.clear();
        mapSupplier.clear();
        int i=0;
        int newIndex = 0;
        for (E_Supplier tSupplier : tempSupplier.values()){
            listBoxSupplier.addItem("Supplier " + String.valueOf(i+1));
            mapSupplier.put(i, tSupplier);
            if (supplier != null)
                if (supplier.equals(tSupplier))
                    newIndex = i;
            i++;
        }
        return newIndex;
    }
    
    @NiftyEventSubscriber(id="suppliersList_WSuC")
    public void onSelectionListBox(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getSelectionIndices().size() > 0){
            updateValues(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))));
        }
    }
    
    private void updateValues(int listBoxIndex){
        supplier = mapSupplier.get(listBoxIndex);
        winControls.setTitle("Supplier " + supplier.getIdSupplier());
        ((Label)screen.findNiftyControl("idValue_WSuC", Label.class)).setText(String.valueOf(supplier.getIdSupplier()));
        ((Label)screen.findNiftyControl("nameValue_WSuC", Label.class)).setText(supplier.getSupplierDescription());
        arrCatalog = new ArrayList<E_Catalog>(supplier.getArrCatalog().values());
        ((ListBox<String>)screen.findNiftyControl("catalogSectionValue_WSuC", ListBox.class)).clear();
        ((ListBox<String>)screen.findNiftyControl("catalogSectionValue_WSuC", ListBox.class)).addAllItems(getArrCatalog());
        if (((ListBox<String>)screen.findNiftyControl("catalogSectionValue_WSuC", ListBox.class)).itemCount() > 0)
            ((ListBox<String>)screen.findNiftyControl("catalogSectionValue_WSuC", ListBox.class)).selectItemByIndex(0);
    }
    
    private ArrayList<String> getArrCatalog(){
        ArrayList<String> arrCatalogName = new ArrayList<String>();
        for (int i=0; i<arrCatalog.size(); i++){
            arrCatalogName.add("Catalog " + i);
        }
        return arrCatalogName;
    }
    
    @NiftyEventSubscriber(id="catalogSectionValue_WSuC")
    public void onBucketListBoxClicked(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getListBox().getFocusItem() != null){
            if (!event.getListBox().getFocusItem().equals("")){
                loadCatalogData(event.getListBox().getFocusItem().toString().replace("Catalog ", ""));
            }
        }        
    }
    
    private void loadCatalogData(String idCatalog){
        if (idCatalog.equals("")){
            ((Label)screen.findNiftyControl("catalogSelected_WSuc", Label.class)).setText("Catalog Selected _");
            ((Label)screen.findNiftyControl("partValue_WSuC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("productionDistnValue_WSuC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("productionParam1Value_WSuC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("productionParam2Value_WSuC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("productionCalculatedValue_WSuC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("priceLimit1Value_WSuC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("priceCharge1Value_WSuC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("priceLimit2Value_WSuC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("priceCharge2Value_WSuC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("priceLimit3Value_WSuC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("priceCharge3Value_WSuC", Label.class)).setText("");
        }else{
            E_Catalog catalog = null;
            for (int i=0; i<arrCatalog.size(); i++){
                if (i == Integer.parseInt(idCatalog)){
                    catalog = arrCatalog.get(i);
                    break;
                }
            }
            ((Label)screen.findNiftyControl("catalogSelected_WSuc", Label.class)).setText("Catalog Selected " + idCatalog);
            ((Label)screen.findNiftyControl("partValue_WSuC", Label.class)).setText(gameEngine.getGameData().getMapUserPart().get(catalog.getIdPart()).getPartDescription() + " - " + catalog.getIdPart());
            ((Label)screen.findNiftyControl("productionDistnValue_WSuC", Label.class)).setText(catalog.getProductionDistn());
            ((Label)screen.findNiftyControl("productionParam1Value_WSuC", Label.class)).setText(String.valueOf(catalog.getProductionParameter1()));
            ((Label)screen.findNiftyControl("productionParam2Value_WSuC", Label.class)).setText(String.valueOf(catalog.getProductionParameter2()));
            ((Label)screen.findNiftyControl("productionCalculatedValue_WSuC", Label.class)).setText(catalog.getProductionCalculated() + " " + Params.timeUnitShort);
            ((Label)screen.findNiftyControl("priceLimit1Value_WSuC", Label.class)).setText(catalog.getPriceFunction1Limit() + " " + Unit.PART.toString());
            ((Label)screen.findNiftyControl("priceCharge1Value_WSuC", Label.class)).setText(Params.moneySign + " " + catalog.getPriceFunction1Charge());
            ((Label)screen.findNiftyControl("priceLimit2Value_WSuC", Label.class)).setText(catalog.getPriceFunction2Limit() + " " + Unit.PART.toString());
            ((Label)screen.findNiftyControl("priceCharge2Value_WSuC", Label.class)).setText(Params.moneySign + " " + catalog.getPriceFunction2Charge());
            ((Label)screen.findNiftyControl("priceLimit3Value_WSuC", Label.class)).setText(catalog.getPriceFunction3Limit() + " " + Unit.PART.toString());
            ((Label)screen.findNiftyControl("priceCharge3Value_WSuC", Label.class)).setText(Params.moneySign + " " + catalog.getPriceFunction3Charge());
        }
    }
}