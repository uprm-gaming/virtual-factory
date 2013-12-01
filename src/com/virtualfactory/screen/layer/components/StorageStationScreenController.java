package com.virtualfactory.screen.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Bucket;
import com.virtualfactory.entity.E_Part;
import com.virtualfactory.entity.E_Slot;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.utils.CommonBuilders;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.SlotStatus;
import com.virtualfactory.utils.StationType;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author David
 */
public class StorageStationScreenController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
//    private ArrayList<ListBoxMessages_Slot> arrSlots;
    private Map<String, ListBoxMessages_Slot> mapSlots;
    private Map<Integer, ListBoxMessages_Bucket> mapBuckets;
    private E_Station station;
//    private Comparator<ListBoxMessages_Slot> comparator = new Comparator<ListBoxMessages_Slot>(){
//                    @Override
//                    public int compare(ListBoxMessages_Slot a,ListBoxMessages_Slot b)
//                    {
//                      if (a.getIndex() > b.getIndex())
//                      { return  1; }
//                      else if (a.getIndex() < b.getIndex())
//                      { return -1; }
//                      else
//                      { return  0; }
//                    }
//                };
    private ListBox<String> listStations;
    private Map<Integer, E_Station> mapStations;
    private Map<Integer, E_Station> tempStations;
    private boolean cleanMessage = true;
    private E_Bucket bucket;
    private ArrayList<E_Bucket> arrBuckets = null;
    private int idPart = -1;
    private ElementBuilder.Align station_label = ElementBuilder.Align.Right;
    private ElementBuilder.Align station_value = ElementBuilder.Align.Left;
    private boolean controlsCompleted = false;

    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winStorageStationControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
//        arrSlots = new ArrayList<ListBoxMessages_Slot>();
        mapSlots = new HashMap<String, ListBoxMessages_Slot>();
        mapBuckets = new HashMap<Integer, ListBoxMessages_Bucket>();
        listStations = ((ListBox<String>)screen.findNiftyControl("stationsList_WSSC", ListBox.class));
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
            winControls.getElement().setVisible(true);
            winControls.getContent().show();
            isVisible = true;
            if (position != null){
                if (winControls.getWidth() + position.getFirst() > gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getWidth())
                    position.setFirst(gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getWidth() - winControls.getWidth());
                if (winControls.getHeight() + position.getSecond() > gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getHeight())
                    position.setSecond(gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getHeight() - winControls.getHeight());
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
            if (screen.findElementByName("panel_StorageStation_WSSC") != null)   screen.findElementByName("panel_StorageStation_WSSC").markForRemoval();
            if (screen.findElementByName("panel_OtherStation_WSSC") != null)    screen.findElementByName("panel_OtherStation_WSSC").markForRemoval();
            mapSlots.clear();
            mapBuckets.clear();
            listStations.clear();
            screen.findElementByName("stationParent_WSSC").layoutElements();
            screen.findElementByName("winStorageStationControl").getParent().layoutElements();
            nifty.executeEndOfFrameElementActions();
        }else{
            listStations.clear();
            mapSlots.clear();
            mapBuckets.clear();
            mapStations = new HashMap<Integer, E_Station>();
            if (index != Params.stationList){
                station = gameEngine.getGameData().getMapUserStation().get(index);
            }
            tempStations = gameEngine.getGameData().getMapUserStation();
            index = getStationList();
            listStations.selectItemByIndex(index);
        }
    }
    
    private int getStationList(){
        listStations.clear();
        mapStations.clear();
        int i=0;
        int newIndex = 0;
        for (E_Station tStation : tempStations.values()){
            if (!tStation.getStationType().equals(StationType.MachineZone) && !tStation.getStationType().equals(StationType.StaffZone)){
                listStations.addItem("Station " + String.valueOf(i+1));
                mapStations.put(i, tStation);
                if (station != null)
                    if (station.equals(tStation))
                        newIndex = i;
                i++;
            }
        }
        return newIndex;
    }
    
    @NiftyEventSubscriber(id="stationsList_WSSC")
    public void onSelectionListBox(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getSelectionIndices().size() > 0){
            updateValues(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))));
        }
    }
    
    private void updateValues(int listBoxIndex){
        station = mapStations.get(listBoxIndex);
        if (station.getStationType().equals(StationType.StorageFG) || station.getStationType().equals(StationType.StorageIG) || station.getStationType().equals(StationType.StorageRM)){
            controlsCompleted = false;
            if (screen.findElementByName("panel_StorageStation_WSSC") == null)   createStorageStationControls();
            if (screen.findElementByName("panel_OtherStation_WSSC") != null)    screen.findElementByName("panel_OtherStation_WSSC").markForRemoval();
            controlsCompleted = true;
            ((WindowControl)screen.findNiftyControl("winStorageStationControl", WindowControl.class)).setHeight(new SizeValue("430px"));
            ((WindowControl)screen.findNiftyControl("winStorageStationControl", WindowControl.class)).setWidth(new SizeValue("370px"));
            ((TextField)screen.findNiftyControl("part_WSSC", TextField.class)).setEnabled(false);
            ((TextField)screen.findNiftyControl("slotsNumber_WSSC", TextField.class)).setEnabled(false);
            ((TextField)screen.findNiftyControl("partsNumber_WSSC", TextField.class)).setEnabled(false);
            
            winControls.setTitle("Station " + (listBoxIndex+1) + " - " + station.getStationType().toString());
            ((Label)screen.findNiftyControl("idValue_WSSC", Label.class)).setText(String.valueOf(station.getIdStation()));
            ((Label)screen.findNiftyControl("descriptionValue_WSSC", Label.class)).setText(station.getStationDescription());
            ((Label)screen.findNiftyControl("costPerHourValue_WSSC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(station.getCostPerHour()));
            ((Label)screen.findNiftyControl("selectedSlots_WSSC", Label.class)).setText("Selected Slots: " + station.getSelectedSlots() + "/" + station.getTotalSlots());
            ((Slider)screen.findNiftyControl("selectedSlotsValue_WSSC", Slider.class)).setup(0f, station.getTotalSlots(), station.getSelectedSlots(), 1.f, 1.f);
            ((Label)screen.findNiftyControl("totalCostPerHourValue_WSSC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(station.getSelectedSlots()*station.getCostPerHour()));
        }else
        if (station.getStationType().equals(StationType.AssembleZone) || station.getStationType().equals(StationType.PurchaseZone) || station.getStationType().equals(StationType.ShippingZone)){
            if (screen.findElementByName("panel_OtherStation_WSSC") == null)    createOtherStationControls();
            if (screen.findElementByName("panel_StorageStation_WSSC") != null)   screen.findElementByName("panel_StorageStation_WSSC").markForRemoval();
            ((WindowControl)screen.findNiftyControl("winStorageStationControl", WindowControl.class)).setHeight(new SizeValue("280px"));
            ((WindowControl)screen.findNiftyControl("winStorageStationControl", WindowControl.class)).setWidth(new SizeValue("400px"));
            ((TextField)screen.findNiftyControl("idBucket_WSC", TextField.class)).setEnabled(false);
            ((TextField)screen.findNiftyControl("unit_WSC", TextField.class)).setEnabled(false);
            ((TextField)screen.findNiftyControl("sizeCapacity_WSC", TextField.class)).setEnabled(false);
            ((TextField)screen.findNiftyControl("partAssigned_WSC", TextField.class)).setEnabled(false);
            ((TextField)screen.findNiftyControl("unitsToArriveRemove_WSC", TextField.class)).setEnabled(false);
            
            winControls.setTitle("Station " + (listBoxIndex+1) + " - " + station.getStationType().toString());
            ((Label)screen.findNiftyControl("idValue_WSC", Label.class)).setText(String.valueOf(station.getIdStation()));
            ((Label)screen.findNiftyControl("descriptionValue_WSC", Label.class)).setText(station.getStationDescription());
        }
        screen.findElementByName("stationParent_WSSC").layoutElements();
        screen.findElementByName("winStorageStationControl").getParent().layoutElements();
        nifty.executeEndOfFrameElementActions();
        updateData();
    }
    
    public void updateData(){
        if (station == null) return;
        if (station.getStationType().equals(StationType.StorageFG) || station.getStationType().equals(StationType.StorageIG) || station.getStationType().equals(StationType.StorageRM)){
            ((ListBox<ListBoxMessages_Slot>)screen.findNiftyControl("slotsSummary_WSSC", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_Slot());
            ((ListBox<ListBoxMessages_Slot>)screen.findNiftyControl("slotsSummary_WSSC", ListBox.class)).clear();
            ListBoxMessages_Slot tempSlotItem = null;
            mapSlots.clear();
            String empty = "Empty";
            double currentWorkingTime = 0;
            for (E_Part part : gameEngine.getGameData().getMapUserPart().values()){
                mapSlots.put(part.getPartDescription(), new ListBoxMessages_Slot(" " + part.getPartDescription(), 0, 0, nifty, part.getPartDesignColor()));
            }
            mapSlots.put(empty, new ListBoxMessages_Slot(" " + empty, 0, 0, nifty,"None"));
            for (E_Slot slot : station.getMapSlots().values()){
                tempSlotItem = mapSlots.get(slot.getPartDescription());
                if (tempSlotItem == null)
                    tempSlotItem = mapSlots.get(empty);
                if (!slot.getSlotStatus().equals(SlotStatus.Unavailable))
                    tempSlotItem.setQuantity(tempSlotItem.getQuantity() + 1);
                if (slot.getSlotStatus().equals(SlotStatus.Busy))
                    tempSlotItem.setPartsNumber(tempSlotItem.getPartsNumber() + slot.getQuantity());
                else
                if (slot.getSlotStatus().equals(SlotStatus.Free))
                    tempSlotItem.setPartsNumber(0);
                currentWorkingTime += slot.getCurrentWorkingTime();
//                tempSlotItem.setCurrentCost(Params.moneySign + " " + Utils.formatValue2DecToString(Double.parseDouble(tempSlotItem.getCost().replace(Params.moneySign, "").replace(" ", "")) + currentWorkingTime));
             }
            int currentSlotUsage = (station.getSelectedSlots() - mapSlots.get(empty).getQuantity()) < 0 ? 0 : (station.getSelectedSlots() - mapSlots.get(empty).getQuantity());
            ((Label)screen.findNiftyControl("currentSlotUsageValue_WSSC", Label.class)).setText(currentSlotUsage + "/" + station.getSelectedSlots());
            ((Label)screen.findNiftyControl("currentTotalCostValue_WSSC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString((currentWorkingTime/60.0)*station.getCostPerHour()));
            ((ListBox<ListBoxMessages_Slot>)screen.findNiftyControl("slotsSummary_WSSC", ListBox.class)).addAllItems(new ArrayList<ListBoxMessages_Slot>(mapSlots.values()));
//            ((Slider)screen.findNiftyControl("selectedSlotsValue_WSSC", Slider.class)).setup(0f, station.getTotalSlots(), station.getSelectedSlots(), 1.f, 1.f);
//            ((Label)screen.findNiftyControl("totalCostPerHourValue_WSSC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(station.getSelectedSlots()*station.getCostPerHour()));
        }else
        if (station.getStationType().equals(StationType.AssembleZone) || station.getStationType().equals(StationType.PurchaseZone) || station.getStationType().equals(StationType.ShippingZone)){
            ((ListBox<ListBoxMessages_Bucket>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_Bucket());
            ((ListBox<ListBoxMessages_Bucket>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).clear();
            mapBuckets.clear();
            arrBuckets = station.getArrBuckets();
            for (E_Bucket bucket : arrBuckets){
                mapBuckets.put(bucket.getIdBucket(), new ListBoxMessages_Bucket(String.valueOf(bucket.getIdBucket())," " + bucket.getUnit().toString(), bucket.getSize() + "/" + bucket.getCapacity(), gameEngine.getGameData().getMapUserPart().get(bucket.getIdPart()).getPartDescription(), bucket.getUnitsToArrive() + "/" + bucket.getUnitsToRemove()));
            }
            ((ListBox<ListBoxMessages_Bucket>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).addAllItems(new ArrayList<ListBoxMessages_Bucket>(mapBuckets.values()));
        }
    }
    
    private void createStorageStationControls(){
        new PanelBuilder("panel_StorageStation_WSSC") {{
            childLayoutVertical();
            width("100%");
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("id_WSSC","Station ID:"){{   width("125px");  height("20px");  textHAlign(station_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("idValue_WSSC","_"){{   width("70px");  height("20px"); textHAlign(station_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("description_WSSC","Description:"){{   width("125px");  height("20px");  textHAlign(station_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("descriptionValue_WSSC","_"){{   width("125px");  height("20px"); textHAlign(station_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();  
                control(new LabelBuilder("costPerHour_WSSC","Slot Cost/Hour:"){{   width("125px");  height("20px"); textHAlign(station_label); }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("costPerHourValue_WSSC"){{   width("70px");  height("20px"); textHAlign(station_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();   // height("22px");
                control(new LabelBuilder("selectedSlots_WSSC","Selected Slots:"){{   width("125px");  height("20px"); textHAlign(station_label); }});  panel(common.hspacer("5px"));
                control(new SliderBuilder("selectedSlotsValue_WSSC", false){{ width("120px"); height("20px"); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();   
                control(new LabelBuilder("totalCostPerHour_WSSC","Slot Total Cost/Hour:"){{   width("125px");  height("20px"); textHAlign(station_label); }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("totalCostPerHourValue_WSSC"){{   width("70px");  height("20px"); textHAlign(station_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();   
                control(new LabelBuilder("currentTotalCost_WSSC","Current Total Cost:"){{   width("125px");  height("20px"); textHAlign(station_label); }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("currentTotalCostValue_WSSC"){{   width("70px");  height("20px"); textHAlign(station_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();   
                control(new LabelBuilder("currentSlotUsage_WSSC","Current Slot Usage:"){{   width("125px");  height("20px"); textHAlign(station_label); }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("currentSlotUsageValue_WSSC"){{   width("70px");  height("20px"); textHAlign(station_value); }});
            }});
            panel(common.vspacer("5px"));
            control(new LabelBuilder("slotsListDescription_WSSC","Slots Summary"){{   width("100%");  height("20px"); textHAlignCenter(); }});
            panel(common.vspacer("5px"));
            panel(new PanelBuilder() {{
                childLayoutHorizontal();
                panel(common.hspacer("20px"));
                panel(new PanelBuilder() {{
                    childLayoutVertical();
                    panel(new PanelBuilder() {{
                        childLayoutHorizontal();
                        control(new TextFieldBuilder("part_WSSC","      Part") {{ width("100px"); }});
                        control(new TextFieldBuilder("slotsNumber_WSSC"," Slots No.") {{ width("60px"); }});
                        control(new TextFieldBuilder("partsNumber_WSSC"," Parts No.") {{ width("61px"); }});
                    }});
                    control(new ListBoxBuilder("slotsSummary_WSSC") {{
                        displayItems(6);
                        selectionModeSingle();
                        optionalVerticalScrollbar();
                        hideHorizontalScrollbar();
                        width("220px");
                        control(new ControlBuilder("customListBox_Slots"){{
                            controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                        }});
                    }});    
                }});
            }});
            panel(common.vspacer("5px"));
            control(new LabelBuilder("messageResult_WSSC",""){{   width("100%");  height("20px"); textHAlignCenter(); }});
            panel(common.vspacer("5px"));
            control(new ButtonBuilder("storageStationRefresh", "Update"){{ width("90%"); alignCenter(); }});
        }}.build(nifty, screen, screen.findElementByName("stationParent_WSSC"));
    }
    
    private void createOtherStationControls(){
        new PanelBuilder("panel_OtherStation_WSSC") {{
            childLayoutVertical();
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("id_WSC","Station ID:"){{   width("125px");  height("20px");  textHAlign(station_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("idValue_WSC","_"){{   width("70px");  height("20px"); textHAlignCenter(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("description_WSC","Description:"){{   width("125px");  height("20px");  textHAlign(station_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("descriptionValue_WSC","_"){{   width("125px");  height("20px"); textHAlignCenter(); }});
            }});
            panel(common.vspacer("5px"));
            control(new LabelBuilder("bucketsList_WBC","Buckets List"){{   width("100%");  height("20px");  textHAlignCenter();  }});
            panel(common.vspacer("5px"));
            panel(new PanelBuilder() {{
                childLayoutHorizontal();
                control(new TextFieldBuilder("idBucket_WSC"," ID") {{ width("20px"); }});
                control(new TextFieldBuilder("unit_WSC","  Unit") {{ width("40px"); }});
                control(new TextFieldBuilder("sizeCapacity_WSC"," Capacity") {{ width("60px"); }});
                control(new TextFieldBuilder("partAssigned_WSC","   Assigned") {{ width("75px"); }});
                control(new TextFieldBuilder("unitsToArriveRemove_WSC","Arrive/Remove") {{ width("90px"); }});
            }});
            control(new ListBoxBuilder("bucketsListValue_WBC") {{
                displayItems(6);
                selectionModeSingle();
                optionalVerticalScrollbar();
                hideHorizontalScrollbar();
                width("*"); 
                control(new ControlBuilder("customListBox_Buckets"){{
                    controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                }});
            }});
//            paddingBottom("150px");
        }}.build(nifty, screen, screen.findElementByName("stationParent_WSSC"));
    }
    
    @NiftyEventSubscriber(id="selectedSlotsValue_WSSC")
    public void onQuantitySliderChange(final String id, final SliderChangedEvent event) {
        if (!controlsCompleted) return;
        int valueSelected = (int)((Slider) screen.findNiftyControl(id, Slider.class)).getValue();
        int valueMax = (int)((Slider) screen.findNiftyControl(id, Slider.class)).getMax();
        station.setTempSelectedSlots(valueSelected);
        ((Label)screen.findNiftyControl("selectedSlots_WSSC", Label.class)).setText("Selected Slots: " + valueSelected + "/" + valueMax);
        ((Label)screen.findNiftyControl("totalCostPerHourValue_WSSC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(valueSelected*station.getCostPerHour()));
        ((Label)screen.findNiftyControl("messageResult_WSSC", Label.class)).setText("");
    }
    
    @NiftyEventSubscriber(id="storageStationRefresh")
    public void onUpdateButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        if (station.getSelectedSlots() != station.getTempSelectedSlots()){
            station.updateSlots();
            station.setTempSelectedSlots(station.getSelectedSlots());
            ((Label)screen.findNiftyControl("selectedSlots_WSSC", Label.class)).setText("Selected Slots: " + station.getSelectedSlots() + "/" + station.getTotalSlots());
            ((Label)screen.findNiftyControl("totalCostPerHourValue_WSSC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(station.getSelectedSlots()*station.getCostPerHour()));
        }            
        updateData();
        ((Label)screen.findNiftyControl("messageResult_WSSC", Label.class)).setText("Updated successfully");
    }
}
