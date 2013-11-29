/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Slot;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.entity.E_TransportStore;
import com.virtualfactory.gui.CommonBuilders;
import com.virtualfactory.gui.ListBoxMessages_StationList_DB;
import com.virtualfactory.gui.ListBoxMessages_TransportList_DB;
import com.virtualfactory.gui.MessagesViewConverter_StationList_DB;
import com.virtualfactory.gui.MessagesViewConverter_TransportList_DB;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.SlotStatus;
import com.virtualfactory.utils.StationType;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author David
 */
public class DashboardControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
    private NiftyImage flowChartImage;
    private int noPeopleBusy = 0;
    private int noPeopleIdle = 0;
    private ArrayList<ListBoxMessages_StationList_DB> arrStations;
    private ArrayList<ListBoxMessages_TransportList_DB> arrTransport;
//    private ArrayList<ListBoxMessages_StationList_DB> arrPurchase;
//    private ArrayList<ListBoxMessages_StationList_DB> arrOperation;
    private boolean stationFirstTime = true;
//    private boolean transportFirstTime = true;
//    private boolean purchaseFirstTime = true;
//    private boolean operationFirstTime = true;

    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winDashboardControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        Attributes y = new Attributes();
        y.set("closeable", "false");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, y);
        isVisible = false;
        
        ((TextField) screen.findNiftyControl("stationName_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("stationPart_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("stationQuantity_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("transportName_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("transportPart_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("transportRequired_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("purchasePart_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("purchaseInventory_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("purchaseOrderQuantity_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("purchaseReorderPoint_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("operationPart_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("operationToProduce_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("operationPartRequired_DB", TextField.class)).disable();
        ((TextField) screen.findNiftyControl("operationPartQuantity_DB", TextField.class)).disable();
        
        ((ListBox<ListBoxMessages_StationList_DB>)screen.findNiftyControl("stationsList_DB", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_StationList_DB());
        ((ListBox<ListBoxMessages_TransportList_DB>)screen.findNiftyControl("transportList_DB", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_TransportList_DB());
//        ((ListBox<ListBoxMessages_StationList_DB>)screen.findNiftyControl("purchaseList_DB", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_StationList_DB());
//        ((ListBox<ListBoxMessages_StationList_DB>)screen.findNiftyControl("operationList_DB", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_StationList_DB());
        
        arrStations = new ArrayList<ListBoxMessages_StationList_DB>();
        arrTransport = new ArrayList<ListBoxMessages_TransportList_DB>();
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
        }else{
            //winControls.getElement().setVisible(true);
            //winControls.getContent().show();
            //isVisible = true;
            if (position != null){
                if (winControls.getWidth() + position.getFirst() > gameEngine.app.getGuiViewPort().getCamera().getWidth())
                    position.setFirst(gameEngine.app.getGuiViewPort().getCamera().getWidth() - winControls.getWidth());
                if (winControls.getHeight() + position.getSecond() > gameEngine.app.getGuiViewPort().getCamera().getHeight())
                    position.setSecond(gameEngine.app.getGuiViewPort().getCamera().getHeight() - winControls.getHeight());
                winControls.getElement().setConstraintX(new SizeValue(position.getFirst() + "px"));
                winControls.getElement().setConstraintY(new SizeValue(position.getSecond() + "px"));
                winControls.getElement().getParent().layoutElements();
            }
            stationFirstTime = true;
            arrStations.clear();
            arrTransport.clear();
//            winControls.getElement().setConstraintX(null);
//            winControls.getElement().setConstraintY(null);
        }
    }
    
    public void updateLocation(boolean isVisible){
        int currentX = 0;
        if (isVisible){
            currentX = 1278 - winControls.getWidth();
            updateData();
            isVisible = true;
           nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").show();

        }else{
       
            currentX = 1260;
            isVisible = false;
            nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").hide();
        }
        winControls.getElement().setConstraintX(new SizeValue(currentX + "px"));
        winControls.getElement().getParent().layoutElements();
    }
    
    public void updateData(){
        // operators
        gameEngine.getGameData().updateUserOperatorCategories();
        ((Label) screen.findNiftyControl("hiredTotalValue_DB", Label.class)).setText((gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Versatile_Act()) + " / " + gameEngine.getGameData().getMapUserOperator().size());
        ((Label) screen.findNiftyControl("busyIdleValue_DB", Label.class)).setText(noPeopleBusy + " / " + noPeopleIdle);
        ((Label) screen.findNiftyControl("materialHandlerValue_DB", Label.class)).setText(String.valueOf(gameEngine.getGameData().getUserOperator_Carrier_Act()));
        ((Label) screen.findNiftyControl("lineOperatorValue_DB", Label.class)).setText(String.valueOf(gameEngine.getGameData().getUserOperator_Assembler_Act()));
        ((Label) screen.findNiftyControl("versatileValue_DB", Label.class)).setText(String.valueOf(gameEngine.getGameData().getUserOperator_Versatile_Act()));
        // machines/equipment
        gameEngine.getGameData().updateUserMachineEquipment();
        ((Label) screen.findNiftyControl("machinePurchasedTotalValue_DB", Label.class)).setText(gameEngine.getGameData().getUserMachine_Act() + " / " + gameEngine.getGameData().getMapUserMachineByOperation().size());
        ((Label) screen.findNiftyControl("equipmentPurchasedTotalValue_DB", Label.class)).setText(gameEngine.getGameData().getUserEquipment_Act() + " / " + gameEngine.getGameData().getMapUserMachineByTransport().size());
        ((Label) screen.findNiftyControl("machineBrokenAvailableValue_DB", Label.class)).setText(gameEngine.getGameData().getUserMachine_Busy() + " / " + gameEngine.getGameData().getUserMachine_Idle());
        ((Label) screen.findNiftyControl("equipmentBrokenAvailableValue_DB", Label.class)).setText(gameEngine.getGameData().getUserEquipment_Busy() + " / " + gameEngine.getGameData().getUserEquipment_Idle());
        // stations
        int tempStationIndex = 0;
        for (E_Station station : gameEngine.getGameData().getMapUserStation().values()){
            if (!station.getStationType().equals(StationType.StaffZone) && !station.getStationType().equals(StationType.MachineZone)){
                if (stationFirstTime){
                    if (station.getStationType().equals(StationType.StorageFG) || station.getStationType().equals(StationType.StorageIG) || station.getStationType().equals(StationType.StorageRM)){
                        arrStations.add(new ListBoxMessages_StationList_DB(station.getIdStation() + "_", station.getStationType().toString(), "Slots", getNoSlotsUsed(station) + "/" + station.getSelectedSlots()));
                    }else{
                        for (int i=0; i<station.getArrBucketsFixed().size(); i++){
                            arrStations.add(new ListBoxMessages_StationList_DB(station.getIdStation() + "_" + station.getArrBucketsFixed().get(i).getSecond().getIdPart(), station.getStationType().toString(), gameEngine.getGameData().getMapUserPart().get(station.getArrBucketsFixed().get(i).getSecond().getIdPart()).getPartDescription(), station.getArrBucketsFixed().get(i).getSecond().getSize() + "/" + station.getArrBucketsFixed().get(i).getSecond().getCapacity()));
                        }
                    }
                }else{
                    if (station.getStationType().equals(StationType.StorageFG) || station.getStationType().equals(StationType.StorageIG) || station.getStationType().equals(StationType.StorageRM)){
                        arrStations.get(tempStationIndex).setQuantity(getNoSlotsUsed(station) + "/" + station.getSelectedSlots());
                        tempStationIndex++;
                    }else{
                        for (int i=0; i<station.getArrBucketsFixed().size(); i++){
                            arrStations.get(tempStationIndex).setQuantity(station.getArrBucketsFixed().get(i).getSecond().getSize() + "/" + station.getArrBucketsFixed().get(i).getSecond().getCapacity());
                            tempStationIndex++;
                        }
                    }
                }
            }
        }
        ((ListBox<ListBoxMessages_StationList_DB>)screen.findNiftyControl("stationsList_DB", ListBox.class)).clear();
        ((ListBox<ListBoxMessages_StationList_DB>)screen.findNiftyControl("stationsList_DB", ListBox.class)).addAllItems(arrStations);
        stationFirstTime = false;
        // transport
        if (arrTransport.isEmpty()){
            for (E_TransportStore transport : gameEngine.getGameData().getMapTransport().values()){
                arrTransport.add(new ListBoxMessages_TransportList_DB(String.valueOf(transport.getIdActivity()), gameEngine.getGameData().getMapUserStation().get(transport.getIdStationInitial()).getStationType().toString() + " -> " + gameEngine.getGameData().getMapUserStation().get(transport.getIdStationEnd()).getStationType().toString(), gameEngine.getGameData().getMapUserPart().get(transport.getIdPart()).getPartDescription(), String.valueOf(transport.getUnitLoad())));
            }
            ((ListBox<ListBoxMessages_TransportList_DB>)screen.findNiftyControl("transportList_DB", ListBox.class)).clear();
            ((ListBox<ListBoxMessages_TransportList_DB>)screen.findNiftyControl("transportList_DB", ListBox.class)).addAllItems(arrTransport);
        }
        // purchase
        
        // operation
        
    }
    
    private int getNoSlotsUsed(E_Station station){
        int noSlots = 0;
        for (E_Slot slot : station.getMapSlots().values()){
            if (slot.getSlotStatus().equals(SlotStatus.Busy)){
                noSlots++;
            }
        }
        return noSlots;
    }
    
    public void updateTransportList(){
        for (int i=0; i<arrTransport.size(); i++){
            arrTransport.get(i).setQuantity(String.valueOf(gameEngine.getGameData().getMapTransport().get(Integer.parseInt(arrTransport.get(i).getIdFromTo())).getUnitLoad()));
        }
        ((ListBox<ListBoxMessages_TransportList_DB>)screen.findNiftyControl("transportList_DB", ListBox.class)).clear();
        ((ListBox<ListBoxMessages_TransportList_DB>)screen.findNiftyControl("transportList_DB", ListBox.class)).addAllItems(arrTransport);
    }
    
    public void updateQuantityPeopleStatus(int noPeopleBusy, int noPeopleIdle){
        this.noPeopleBusy = noPeopleBusy;
        this.noPeopleIdle = noPeopleIdle;
    }
}