/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;

import com.virtualfactory.nifty.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.TextFieldChangedEvent;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.utils.Distributions;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author David
 */
public class OptionsMenuController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private boolean isExpanded_Machine = true;
    private boolean isExpanded_Equipment = true;
    private NiftyImage imageAdd;
    private NiftyImage imageMinus;
    private String sizePreviousImage = "25px";
    final CommonBuilders common = new CommonBuilders();
    private ArrayList<String> arrMachine = null;
    private ArrayList<String> arrEquipment = null;
    private GameEngine gameEngine;
    private E_Machine machine;
        
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        imageAdd = nifty.createImage("Interface/Principal/add_gray.png", false);
        imageMinus = nifty.createImage("Interface/Principal/minus_gray.png", false);
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
    
    public void clickToMachines(){
        loadDynamicList(isExpanded_Machine, false);
        isExpanded_Machine = !isExpanded_Machine;
        isExpanded_Equipment = true;
    }
    
    public void clickToEquipments(){
        loadDynamicList(false, isExpanded_Equipment);
        isExpanded_Equipment = !isExpanded_Equipment;
        isExpanded_Machine = true;
    }
    
    private void loadDynamicList(boolean loadMachine, boolean loadEquipment){
        if (loadEquipment){
            //expand
            screen.findElementByName("imageEquipmentSettings").getRenderer(ImageRenderer.class).setImage(imageMinus);
        }else{
            //shrink
            screen.findElementByName("imageEquipmentSettings").getRenderer(ImageRenderer.class).setImage(imageAdd);
        }
        loadEquipment(loadEquipment);
        
        if (loadMachine){
            //expand
            screen.findElementByName("imageMachinesSettings").getRenderer(ImageRenderer.class).setImage(imageMinus);
        }else{
            //shrink
            screen.findElementByName("imageMachinesSettings").getRenderer(ImageRenderer.class).setImage(imageAdd);
        }
        loadMachine(loadMachine);
    }
    
    private void loadMachine(boolean isLoading){
        if (isLoading){
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new ListBoxBuilder("machineList_GSC") {{ displayItems(10); selectionModeSingle(); 
                        showVerticalScrollbar(); hideHorizontalScrollbar(); }});//width("100%"); 
            }}.build(nifty, screen, screen.findElementByName("optionsListParent_Machine"));
            loadMachineData();
        }else{
            for(Element tempElement : screen.findElementByName("optionsListParent_Machine").getElements())
                tempElement.markForRemoval();
        }
        screen.findElementByName("optionsListParent_Machine").layoutElements();
        screen.findElementByName("optionsListParent_Machine").getParent().layoutElements();
    }
    
    private void loadEquipment(boolean isLoading){
        if (isLoading){
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new ListBoxBuilder("equipmentList_GSC") {{ displayItems(10); selectionModeSingle(); 
                        showVerticalScrollbar(); hideHorizontalScrollbar(); }});//width("100%"); 
            }}.build(nifty, screen, screen.findElementByName("optionsListParent_Equipment"));
            loadEquipmentData();
        }else{
            for(Element tempElement : screen.findElementByName("optionsListParent_Equipment").getElements())
                tempElement.markForRemoval();
        }
        screen.findElementByName("optionsListParent_Equipment").layoutElements();
        screen.findElementByName("optionsListParent_Equipment").getParent().layoutElements();
    }
    
    private void loadMachineData(){
        if (arrMachine == null){
            arrMachine = new ArrayList<String>();
            for (E_Machine machine : gameEngine.getGameData().getMapUserMachineByOperation().values()){
                arrMachine.add("Machine " + machine.getIdMachine());
            }
        }
        ((ListBox<String>)screen.findNiftyControl("machineList_GSC", ListBox.class)).clear();
        ((ListBox<String>)screen.findNiftyControl("machineList_GSC", ListBox.class)).addAllItems(arrMachine);
    }
    
    private void loadEquipmentData(){
        if (arrEquipment == null){
            arrEquipment = new ArrayList<String>();
            for (E_Machine machine : gameEngine.getGameData().getMapUserMachineByTransport().values()){
                arrEquipment.add("Equipment " + machine.getIdMachine());
            }
        }
        ((ListBox<String>)screen.findNiftyControl("equipmentList_GSC", ListBox.class)).clear();
        ((ListBox<String>)screen.findNiftyControl("equipmentList_GSC", ListBox.class)).addAllItems(arrEquipment);
    }
    
    @NiftyEventSubscriber(id="machineList_GSC")
    public void onMachineListBoxClicked(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getListBox().getFocusItem() != null){
            if (!event.getListBox().getFocusItem().equals("")){
                loadMachineEquipmentData(Integer.parseInt(event.getListBox().getFocusItem().toString().replace("Machine ", "")), true);
            }
        }        
    }
    
    @NiftyEventSubscriber(id="equipmentList_GSC")
    public void onEquipmentListBoxClicked(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getListBox().getFocusItem() != null){
            if (!event.getListBox().getFocusItem().equals("")){
                loadMachineEquipmentData(Integer.parseInt(event.getListBox().getFocusItem().toString().replace("Equipment ", "")), false);
            }
        }        
    }
    
    private void loadMachineEquipmentData(int idMachineEquipment, boolean isMachine){
        //System.out.println("MachineEquipment:" + idMachineEquipment + " is: " + isMachine);
        if (screen.findElementByName("machineEquipmentPanelParent") == null){
            createControlMachineEquipment(isMachine);
            loadDropDownElements();
        }
//        else
//            screen.findElementByName("machineEquipmentPanelParent").markForRemoval();
        screen.findElementByName("detailedOptionsParent").layoutElements();
        machine = gameEngine.getGameData().getMapUserMachine().get(idMachineEquipment);
        ((Label)screen.findNiftyControl("idMachineValue_GSC", Label.class)).setText(String.valueOf(machine.getIdMachine()));
        ((Label)screen.findNiftyControl("descriptionValue_GSC", Label.class)).setText(machine.getMachineDescription());
        ((Label)screen.findNiftyControl("statusValue_GSC", Label.class)).setText(machine.getMachineState()==ObjectState.Active ? "Purchased" : "Sold");
        ((DropDown)screen.findNiftyControl("pickUpDistributionValue_GSC", DropDown.class)).selectItem(machine.getPickUpTimeDistn());
        ((TextField)screen.findNiftyControl("pickUpParam1Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getPickUpTimeParameter1()));
        ((TextField)screen.findNiftyControl("pickUpParam2Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getPickUpTimeParameter2()));
        ((Label)screen.findNiftyControl("pickUpCalculatedValue_GSC", Label.class)).setText(machine.getPickUpTimeCalculated() + " " + Params.timeUnitShort);
        ((DropDown)screen.findNiftyControl("machineDistributionValue_GSC", DropDown.class)).selectItem(machine.getMachineTimeDistn());
        ((TextField)screen.findNiftyControl("machineParam1Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getMachineTimeParameter1()));
        ((TextField)screen.findNiftyControl("machineParam2Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getMachineTimeParameter2()));
        ((Label)screen.findNiftyControl("machineCalculatedValue_GSC", Label.class)).setText(machine.getMachineTimeCalculated() + " " + Params.timeUnitShort);
        ((DropDown)screen.findNiftyControl("placementDistributionValue_GSC", DropDown.class)).selectItem(machine.getPlacementTimeDistn());
        ((TextField)screen.findNiftyControl("placementParam1Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getPlacementTimeParameter1()));
        ((TextField)screen.findNiftyControl("placementParam2Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getPlacementTimeParameter2()));
        ((Label)screen.findNiftyControl("placementCalculatedValue_GSC", Label.class)).setText(machine.getPlacementTimeCalculated() + " " + Params.timeUnitShort);
        ((DropDown)screen.findNiftyControl("timeBetweenFailuresDistributionValue_GSC", DropDown.class)).selectItem(machine.getTimeBetweenFailuresDistn());
        ((TextField)screen.findNiftyControl("timeBetweenFailuresParam1Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getTimeBetweenFailuresParameter1()));
        ((TextField)screen.findNiftyControl("timeBetweenFailuresParam2Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getTimeBetweenFailuresParameter2()));
        ((Label)screen.findNiftyControl("timeBetweenFailuresCalculatedValue_GSC", Label.class)).setText(machine.getTimeBetweenFailuresCalculated() + " " + Params.timeUnitShort);
        ((DropDown)screen.findNiftyControl("repairTimeDistributionValue_GSC", DropDown.class)).selectItem(machine.getRepairTimeDistn());
        ((TextField)screen.findNiftyControl("repairTimeParam1Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getRepairTimeParameter1()));
        ((TextField)screen.findNiftyControl("repairTimeParam2Value_GSC", TextField.class)).setText(Utils.formatValue2DecToString(machine.getRepairTimeParameter2()));
        ((Label)screen.findNiftyControl("repairTimeCalculatedValue_GSC", Label.class)).setText(machine.getRepairTimeCalculated() + " " + Params.timeUnitShort);
        screen.findElementByName("machineEquipmentPanelParent").layoutElements();
        screen.findElementByName("detailedOptionsParent").layoutElements();
        screen.findElementByName("detailedOptionsParent").getParent().layoutElements();
    }
    
    private void createControlMachineEquipment(boolean isMachine){
        final String machineEquipment = isMachine == true ? "Machine" : "Equipment";
        final String widthLeft = "44%";
        final String widthRight = "40%";
        final String widthRightHalf = "30%";
        final String widthRightQuarter = "10%";
        new PanelBuilder("machineEquipmentPanelParent"){{
            childLayoutVertical();
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("idMachine_GSC",machineEquipment + " ID:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("idMachineValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("description_GSC","Description:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("descriptionValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("status_GSC","Status:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("statusValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal(); height("25px");
                control(new LabelBuilder("pickUpDistribution_GSC","PickUp Distribution:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new DropDownBuilder("pickUpDistributionValue_GSC"){{   width(widthRightHalf);  height("20px"); textHAlignLeft(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("pickUpParam1Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("pickUpParam2Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("pickUpCalculated_GSC","PickUp Calculated:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("pickUpCalculatedValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal(); height("25px");
                control(new LabelBuilder("machineDistribution_GSC","Machine Distribution:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new DropDownBuilder("machineDistributionValue_GSC"){{   width(widthRightHalf);  height("20px"); textHAlignLeft(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("machineParam1Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("machineParam2Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("machineCalculated_GSC","Machine Calculated:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("machineCalculatedValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal(); height("25px");
                control(new LabelBuilder("placementDistribution_GSC","Placement Distribution:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new DropDownBuilder("placementDistributionValue_GSC"){{   width(widthRightHalf);  height("20px"); textHAlignLeft(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("placementParam1Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("placementParam2Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("placementCalculated_GSC","Placement Calculated:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("placementCalculatedValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal(); height("25px");
                control(new LabelBuilder("timeBetweenFailuresDistribution_GSC","Time Between Failures Distribution:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new DropDownBuilder("timeBetweenFailuresDistributionValue_GSC"){{   width(widthRightHalf);  height("20px"); textHAlignLeft(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("timeBetweenFailuresParam1Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("timeBetweenFailuresParam2Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("timeBetweenFailuresCalculated_GSC","Time Between Failures Calculated:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("timeBetweenFailuresCalculatedValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal(); height("25px");
                control(new LabelBuilder("repairTimeDistribution_GSC","Repair Time Distribution:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new DropDownBuilder("repairTimeDistributionValue_GSC"){{   width(widthRightHalf);  height("20px"); textHAlignLeft(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("repairTimeParam1Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }}); panel(common.hspacer("5px"));
                control(new TextFieldBuilder("repairTimeParam2Value_GSC"){{   width(widthRightQuarter);  height("20px"); textHAlignRight(); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("repairTimeCalculated_GSC","Repair Time Calculated:"){{   width(widthLeft);  height("20px");  textHAlignRight();  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("repairTimeCalculatedValue_GSC",""){{   width(widthRight);  height("20px"); textHAlignLeft(); }});
            }});
            panel(common.vspacer());
            panel(new PanelBuilder(){{
                childLayoutHorizontal(); width("100%");
                panel(common.hspacer("17%"));
                control(new ButtonBuilder("updateDistributions_GSC", "Calculate Distributions" ){{ width("30%"); alignCenter(); }});
                panel(common.hspacer("20px"));
                control(new ButtonBuilder("saveButton_GSC", "Save Changes"){{ width("30%"); alignCenter(); }});
            }});
            panel(common.vspacer());
            width("100%");
        }}.build(nifty, screen, screen.findElementByName("detailedOptionsParent"));
    }
    
    private void loadDropDownElements(){
        ((DropDown)screen.findNiftyControl("pickUpDistributionValue_GSC", DropDown.class)).addAllItems(Distributions.getDistributions());
        ((DropDown)screen.findNiftyControl("machineDistributionValue_GSC", DropDown.class)).addAllItems(Distributions.getDistributions());
        ((DropDown)screen.findNiftyControl("placementDistributionValue_GSC", DropDown.class)).addAllItems(Distributions.getDistributions());
        ((DropDown)screen.findNiftyControl("timeBetweenFailuresDistributionValue_GSC", DropDown.class)).addAllItems(Distributions.getDistributions());
        ((DropDown)screen.findNiftyControl("repairTimeDistributionValue_GSC", DropDown.class)).addAllItems(Distributions.getDistributions());
    }

    private void updateDistributionsCalculated(String param1, String param2, String newDist, String storeNewValue){
        if (((TextField)screen.findNiftyControl(param1, TextField.class)).getText().isEmpty())
            ((TextField)screen.findNiftyControl(param1, TextField.class)).setText("1.00");
        else
            ((TextField)screen.findNiftyControl(param1, TextField.class)).setText(
            Utils.dropNoIntegers_AllowPoint(((TextField)screen.findNiftyControl(param1, TextField.class)).getText()));
        if (Double.parseDouble(((TextField)screen.findNiftyControl(param1, TextField.class)).getText()) == 0.00 && 
                !((DropDown)screen.findNiftyControl(newDist, DropDown.class)).getSelection().toString().equals(Distributions.distUniform))
            ((TextField)screen.findNiftyControl(param1, TextField.class)).setText("1.00");
        
        if (((TextField)screen.findNiftyControl(param2, TextField.class)).getText().isEmpty())
            ((TextField)screen.findNiftyControl(param2, TextField.class)).setText("1.00");
        else
            ((TextField)screen.findNiftyControl(param2, TextField.class)).setText(
            Utils.dropNoIntegers_AllowPoint(((TextField)screen.findNiftyControl(param2, TextField.class)).getText()));
        if (Double.parseDouble(((TextField)screen.findNiftyControl(param2, TextField.class)).getText()) == 0.00)
            ((TextField)screen.findNiftyControl(param2, TextField.class)).setText("1.00");
        
        if (((DropDown)screen.findNiftyControl(newDist, DropDown.class)).getSelection().toString().equals(Distributions.distUniform)){
            if (Double.parseDouble(((TextField)screen.findNiftyControl(param1, TextField.class)).getText()) >= 
                    Double.parseDouble(((TextField)screen.findNiftyControl(param2, TextField.class)).getText())){
                ((TextField)screen.findNiftyControl(param2, TextField.class)).setText(Utils.formatValue2DecToString(
                        Double.parseDouble(((TextField)screen.findNiftyControl(param1, TextField.class)).getText())+1));
            }
        }
        
        ((Label)screen.findNiftyControl(storeNewValue, Label.class)).setText(String.valueOf(
        Distributions.calculateDist(((DropDown)screen.findNiftyControl(newDist, DropDown.class)).getSelection().toString(), 
                Double.parseDouble(((TextField)screen.findNiftyControl(param1, TextField.class)).getText()), 
                Double.parseDouble(((TextField)screen.findNiftyControl(param2, TextField.class)).getText()))) + " " + Params.timeUnitShort);
    }
    
    private void updateAllDistributions(){
        updateDistributionsCalculated("pickUpParam1Value_GSC", "pickUpParam2Value_GSC", "pickUpDistributionValue_GSC", "pickUpCalculatedValue_GSC");
        updateDistributionsCalculated("machineParam1Value_GSC", "machineParam2Value_GSC", "machineDistributionValue_GSC", "machineCalculatedValue_GSC");
        updateDistributionsCalculated("placementParam1Value_GSC", "placementParam2Value_GSC", "placementDistributionValue_GSC", "placementCalculatedValue_GSC");
        updateDistributionsCalculated("timeBetweenFailuresParam1Value_GSC", "timeBetweenFailuresParam2Value_GSC", "timeBetweenFailuresDistributionValue_GSC", "timeBetweenFailuresCalculatedValue_GSC");
        updateDistributionsCalculated("repairTimeParam1Value_GSC", "repairTimeParam2Value_GSC", "repairTimeDistributionValue_GSC", "repairTimeCalculatedValue_GSC");
    }
    
    @NiftyEventSubscriber(id="updateDistributions_GSC")
    public void onUpdateDistributionsButtonClicked(final String id, final ButtonClickedEvent event) {
        updateAllDistributions();
    }
    
    @NiftyEventSubscriber(id="saveButton_GSC")
    public void onSaveButtonClicked(final String id, final ButtonClickedEvent event) {
        updateAllDistributions();
        saveChanges();
        //switchScreens();
    }
    
    private void saveChanges(){
        machine.setPickUpTimeDistn(((DropDown)screen.findNiftyControl("pickUpDistributionValue_GSC", DropDown.class)).getSelection().toString());
        machine.setPickUpTimeParameter1(Double.parseDouble(((TextField)screen.findNiftyControl("pickUpParam1Value_GSC", TextField.class)).getText()));
        machine.setPickUpTimeParameter2(Double.parseDouble(((TextField)screen.findNiftyControl("pickUpParam2Value_GSC", TextField.class)).getText()));
        machine.setPickUpTimeCalculated(Double.parseDouble(((Label)screen.findNiftyControl("pickUpCalculatedValue_GSC", Label.class)).getText().replace(" " + Params.timeUnitShort, "")));

        machine.setMachineTimeDistn(((DropDown)screen.findNiftyControl("machineDistributionValue_GSC", DropDown.class)).getSelection().toString());
        machine.setMachineTimeParameter1(Double.parseDouble(((TextField)screen.findNiftyControl("machineParam1Value_GSC", TextField.class)).getText()));
        machine.setMachineTimeParameter2(Double.parseDouble(((TextField)screen.findNiftyControl("machineParam2Value_GSC", TextField.class)).getText()));
        machine.setMachineTimeCalculated(Double.parseDouble(((Label)screen.findNiftyControl("machineCalculatedValue_GSC", Label.class)).getText().replace(" " + Params.timeUnitShort, "")));

        machine.setPlacementTimeDistn(((DropDown)screen.findNiftyControl("placementDistributionValue_GSC", DropDown.class)).getSelection().toString());
        machine.setPlacementTimeParameter1(Double.parseDouble(((TextField)screen.findNiftyControl("placementParam1Value_GSC", TextField.class)).getText()));
        machine.setPlacementTimeParameter2(Double.parseDouble(((TextField)screen.findNiftyControl("placementParam2Value_GSC", TextField.class)).getText()));
        machine.setPlacementTimeCalculated(Double.parseDouble(((Label)screen.findNiftyControl("placementCalculatedValue_GSC", Label.class)).getText().replace(" " + Params.timeUnitShort, "")));
        
        machine.setTimeBetweenFailuresDistn(((DropDown)screen.findNiftyControl("timeBetweenFailuresDistributionValue_GSC", DropDown.class)).getSelection().toString());
        machine.setTimeBetweenFailuresParameter1(Double.parseDouble(((TextField)screen.findNiftyControl("timeBetweenFailuresParam1Value_GSC", TextField.class)).getText()));
        machine.setTimeBetweenFailuresParameter2(Double.parseDouble(((TextField)screen.findNiftyControl("timeBetweenFailuresParam2Value_GSC", TextField.class)).getText()));
        machine.setTimeBetweenFailuresCalculated(Double.parseDouble(((Label)screen.findNiftyControl("timeBetweenFailuresCalculatedValue_GSC", Label.class)).getText().replace(" " + Params.timeUnitShort, "")));
        
        machine.setRepairTimeDistn(((DropDown)screen.findNiftyControl("repairTimeDistributionValue_GSC", DropDown.class)).getSelection().toString());
        machine.setRepairTimeParameter1(Double.parseDouble(((TextField)screen.findNiftyControl("repairTimeParam1Value_GSC", TextField.class)).getText()));
        machine.setRepairTimeParameter2(Double.parseDouble(((TextField)screen.findNiftyControl("repairTimeParam2Value_GSC", TextField.class)).getText()));
        machine.setRepairTimeCalculated(Double.parseDouble(((Label)screen.findNiftyControl("repairTimeCalculatedValue_GSC", Label.class)).getText().replace(" " + Params.timeUnitShort, "")));

        ((Label)screen.findNiftyControl("messageResult_GSC", Label.class)).setText("Saved successfully");
    }
    
    @NiftyEventSubscriber(id="cancelButton_GSC")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        switchScreens();
    }
    
    private void switchScreens(){
        Element nextElement = screen.findElementByName("dialogMainMenu");
        MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
        mainMenu.updateControls();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogOptionsMenu");
        currentElement.hide();
        screen.findElementByName("dialogOptionsMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogMainMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
    
    private void cleanMessageResult(){
        ((Label)screen.findNiftyControl("messageResult_GSC", Label.class)).setText("");
    }
    
    @NiftyEventSubscriber(pattern="pickUpParam.*")
    public void onPickUpChange(final String id, final TextFieldChangedEvent event) {
        cleanMessageResult();
//        updateTextFieldWithFormat(id);
    }
    @NiftyEventSubscriber(pattern="machineParam.*")
    public void onMachineChange(final String id, final TextFieldChangedEvent event) {
        cleanMessageResult();
//        updateTextFieldWithFormat(id);
    }
    @NiftyEventSubscriber(pattern="placementParam.*")
    public void onPlacementChange(final String id, final TextFieldChangedEvent event) {
        cleanMessageResult();
//        updateTextFieldWithFormat(id);
    }
    @NiftyEventSubscriber(pattern="timeBetweenFailuresParam.*")
    public void onTimeBetweenFailuresChange(final String id, final TextFieldChangedEvent event) {
        cleanMessageResult();
//        updateTextFieldWithFormat(id);
    }
    @NiftyEventSubscriber(pattern="repairTimeParam.*")
    public void onRepairTimeChange(final String id, final TextFieldChangedEvent event) {
        cleanMessageResult();
//        updateTextFieldWithFormat(id);
    }
    
    @NiftyEventSubscriber(id="pickUpDistributionValue_GSC")
    public void onPickUp_DropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
        cleanMessageResult();
        enableDisableParameter2(id,"pickUpParam2Value_GSC");
    }
    @NiftyEventSubscriber(id="machineDistributionValue_GSC")
    public void onMachine_DropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
        cleanMessageResult();
        enableDisableParameter2(id,"machineParam2Value_GSC");
    }
    @NiftyEventSubscriber(id="placementDistributionValue_GSC")
    public void onPlacement_DropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
        cleanMessageResult();
        enableDisableParameter2(id,"placementParam2Value_GSC");
    }
    @NiftyEventSubscriber(id="timeBetweenFailuresDistributionValue_GSC")
    public void onTimeBetweenFailures_DropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
        cleanMessageResult();
        enableDisableParameter2(id,"timeBetweenFailuresParam2Value_GSC");
    }
    @NiftyEventSubscriber(id="repairTimeDistributionValue_GSC")
    public void onRepairTime_DropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
        cleanMessageResult();
        enableDisableParameter2(id,"repairTimeParam2Value_GSC");
    }
    
    private void enableDisableParameter2(String distributionName, String param2Name){
        if (((DropDown)screen.findNiftyControl(distributionName, DropDown.class)).getSelection().toString().equals(Distributions.distExponential)
            || ((DropDown)screen.findNiftyControl(distributionName, DropDown.class)).getSelection().toString().equals(Distributions.distNone)){
            ((TextField)screen.findNiftyControl(param2Name, TextField.class)).setText("");
            ((TextField)screen.findNiftyControl(param2Name, TextField.class)).disable();
        }else{
            if (!((TextField)screen.findNiftyControl(param2Name, TextField.class)).isEnabled()){
                ((TextField)screen.findNiftyControl(param2Name, TextField.class)).setText("0.00");
                ((TextField)screen.findNiftyControl(param2Name, TextField.class)).enable();
            }
        }
    }
//    
//    private void updateTextFieldWithFormat(String textFieldName){
//        ((TextField)screen.findNiftyControl(textFieldName, TextField.class)).setText(Utils.formatValue2DecToString(
//                Double.parseDouble(((TextField)screen.findNiftyControl(textFieldName, TextField.class)).getText())));
//    }
}
