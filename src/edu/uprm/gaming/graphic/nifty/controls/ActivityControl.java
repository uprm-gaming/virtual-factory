/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty.controls;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ElementBuilder.Align;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.SizeValue;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.entity.E_Activity;
import edu.uprm.gaming.entity.E_AssemblyDetails;
import edu.uprm.gaming.entity.E_Bucket;
import edu.uprm.gaming.entity.E_Catalog;
import edu.uprm.gaming.entity.E_Game;
import edu.uprm.gaming.entity.E_Operation;
import edu.uprm.gaming.entity.E_Part;
import edu.uprm.gaming.entity.E_Purchase;
import edu.uprm.gaming.entity.E_Ship;
import edu.uprm.gaming.entity.E_Skill;
import edu.uprm.gaming.entity.E_Supplier;
import edu.uprm.gaming.entity.E_TransportStore;
import edu.uprm.gaming.graphic.nifty.CommonBuilders;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.TypeActivity;
import edu.uprm.gaming.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 *
 * @author David
 */
public class ActivityControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private TextField gameDescription;
    private ListBox<String> listBoxGames;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    private WindowControl winControls;
    private Align activity_label = Align.Right;
    private Align activity_value = Align.Left;
    private String width_label = "110px";
    private String width_value = "120px";
    final CommonBuilders common = new CommonBuilders();
    private E_Activity activity;
    private E_TransportStore transportStore;
    private E_Operation operation;
    private E_Purchase purchase;
    private E_Ship ship;
//    private E_Operator operator;
    private boolean isVisible;
    private TypeActivity gralTypeActivity;
//    private int gralIndex;
    private E_Supplier supplierSelected;
    private String transportHeight = "310px";
    private String purchaseHeight = "375px";
    private String operationHeight = "450px";
    private String transportPositionX = "490px";
    private String purchasePositionX = "385px";
    private String operationPositionX = "385px";
    private ArrayList<E_AssemblyDetails> arrAssembly;
    private ListBox<String> listBoxActivity;
    private Map<Integer, E_Activity> mapActivity;
    private Map<Integer, E_Activity> tempActivity;
    private TypeActivity tempTypeActivity;
    private boolean cleanMessage = true;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winActivityControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
        listBoxActivity = ((ListBox<String>)screen.findNiftyControl("activitiesList_WAC", ListBox.class));
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
    
    public void loadWindowControl(GameEngine game,int index, TypeActivity typeActivity, Pair<Integer,Integer> position){
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
//                if(typeActivity.equals(TypeActivity.Operation))
//                    winControls.getElement().setConstraintX(new SizeValue(operationPositionX));
//                else
//                if(typeActivity.equals(TypeActivity.Purchase))
//                    winControls.getElement().setConstraintX(new SizeValue(purchasePositionX));
//                else
//                if(typeActivity.equals(TypeActivity.Transport))
//                    winControls.getElement().setConstraintX(new SizeValue(transportPositionX));
                winControls.getElement().setConstraintX(new SizeValue("258px"));
                winControls.getElement().getParent().layoutElements();
            }
            winControls.getElement().setConstraintX(null);
            winControls.getElement().setConstraintY(null);
        }
        loadValues(index, typeActivity);
    }
    
    private void loadValues(int index, TypeActivity typeActivity){
//        this.gralIndex = index;
        this.gralTypeActivity = typeActivity;
        if (index == -1){
            activity = null;
            transportStore = null;
            operation = null;
            purchase = null;
            ship = null;
            //REGULAR ITEMS
            ((Label)screen.findNiftyControl("idValue_WAC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("descriptionValue_WAC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("typeActivityValue_WAC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("partValue_WAC", Label.class)).setText("_");
            
            if (screen.findElementByName("panel_WAC_TS") != null)   screen.findElementByName("panel_WAC_TS").markForRemoval();
            if (screen.findElementByName("panel_WAC_O") != null)    screen.findElementByName("panel_WAC_O").markForRemoval();
            if (screen.findElementByName("panel_WAC_P") != null)    screen.findElementByName("panel_WAC_P").markForRemoval();
            if (screen.findElementByName("panel_WAC_S") != null)    screen.findElementByName("panel_WAC_S").markForRemoval();
            screen.findElementByName("winAC_Parent").layoutElements();

            if (typeActivity.equals(TypeActivity.Transport) || typeActivity.equals(TypeActivity.Store)){
                //TRANSPORT - STORE
                createPanelTransportStore();
                screen.findElementByName("fromStationValue_WAC_TS").getRenderer(TextRenderer.class).setText("_");
                screen.findElementByName("toStationValue_WAC_TS").getRenderer(TextRenderer.class).setText("_");
                ((Label)screen.findNiftyControl("fromBucketValue_WAC_TS", Label.class)).setText("_");
                ((Label)screen.findNiftyControl("toBucketValue_WAC_TS", Label.class)).setText("_");
                ((Label)screen.findNiftyControl("unitLoadValue_WAC_TS", Label.class)).setText("");
//                ((ListBox)screen.findNiftyControl("skillsRequiredValue_WAC", ListBox.class)).clear();
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue(transportHeight));
            }else
            if (typeActivity.equals(TypeActivity.Operation)){
                //OPERATION
                createPanelOperation();
                ((Label)screen.findNiftyControl("stationValue_WAC_O", Label.class)).setText("_");
                ((Label)screen.findNiftyControl("outputBucketValue_WAC_O", Label.class)).setText("_");
                ((Label)screen.findNiftyControl("quantityOutputValue_WAC_O", Label.class)).setText("");
                //PARTS REQUIRED
                ((TextField)screen.findNiftyControl("assemblyPartRequired_WAC_O", TextField.class)).setEnabled(false);
                ((TextField)screen.findNiftyControl("assemblyQuantity_WAC_O", TextField.class)).setEnabled(false);
                ((ListBox<ListBoxMessages_Assembly>)screen.findNiftyControl("inputPartsRequiredValue_WAC", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_Assembly());
                ((ListBox<ListBoxMessages_Assembly>)screen.findNiftyControl("inputPartsRequiredValue_WAC", ListBox.class)).clear();
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue(operationHeight));
            }else
            if (typeActivity.equals(TypeActivity.Purchase)){
                //PURCHASE
                createPanelPurchase();
                screen.findElementByName("stationValue_WAC_P").getRenderer(TextRenderer.class).setText("_");
                ((Label)screen.findNiftyControl("bucketValue_WAC_P", Label.class)).setText("_");
                ((Label)screen.findNiftyControl("currentInventoryValue_WAC_P", Label.class)).setText("_");
                ((TextField)screen.findNiftyControl("orderPointValue_WAC_P", TextField.class)).setText("0");
                ((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).setText("0");
                ((Label)screen.findNiftyControl("moneyRequiredValue_WAC_P", Label.class)).setText("_");
                ((DropDown)screen.findNiftyControl("supplierValue_WAC_P", DropDown.class)).selectItemByIndex(0);
                ((TextField)screen.findNiftyControl("pricePerUnitValue_WAC_P", TextField.class)).setText("");
                ((TextField)screen.findNiftyControl("timeToArriveOrderValue_WAC_P", TextField.class)).setText("");
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue(purchaseHeight));
            }else
            if (typeActivity.equals(TypeActivity.Ship)){
                //SHIP
                createPanelShip();
                screen.findElementByName("stationValue_WAC_S").getRenderer(TextRenderer.class).setText("_");
                ((Label)screen.findNiftyControl("bucketValue_WAC_S", Label.class)).setText("_");
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue("225px"));
            }
            screen.findElementByName("winAC_Parent").layoutElements();
            screen.findElementByName("winActivityControl").getParent().layoutElements();
            winControls.setTitle("Activity");
            listBoxActivity.clear();
        }else{
            listBoxActivity.clear();
            mapActivity = new HashMap<Integer, E_Activity>();
            activity = null;
            tempActivity = gameEngine.getGameData().getActivityByType(typeActivity);
            tempTypeActivity = typeActivity;
            getActivityList();
            
            if (typeActivity.equals(TypeActivity.Transport) || typeActivity.equals(TypeActivity.Store)){
                //TRANSPORT - STORE
                if (screen.findElementByName("panel_WAC_TS") == null)   createPanelTransportStore();
                if (screen.findElementByName("panel_WAC_O") != null)    screen.findElementByName("panel_WAC_O").markForRemoval();
                if (screen.findElementByName("panel_WAC_P") != null)    screen.findElementByName("panel_WAC_P").markForRemoval();
                if (screen.findElementByName("panel_WAC_S") != null)    screen.findElementByName("panel_WAC_S").markForRemoval();
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue(transportHeight));
            }else
            if (typeActivity.equals(TypeActivity.Operation)){
                //OPERATION
                if (screen.findElementByName("panel_WAC_TS") != null)   screen.findElementByName("panel_WAC_TS").markForRemoval();
                if (screen.findElementByName("panel_WAC_O") == null)    createPanelOperation();
                if (screen.findElementByName("panel_WAC_P") != null)    screen.findElementByName("panel_WAC_P").markForRemoval();
                if (screen.findElementByName("panel_WAC_S") != null)    screen.findElementByName("panel_WAC_S").markForRemoval();
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue(operationHeight));
            }else
            if (typeActivity.equals(TypeActivity.Purchase)){
                //PURCHASE
                if (screen.findElementByName("panel_WAC_TS") != null)   screen.findElementByName("panel_WAC_TS").markForRemoval();
                if (screen.findElementByName("panel_WAC_O") != null)    screen.findElementByName("panel_WAC_O").markForRemoval();
                if (screen.findElementByName("panel_WAC_P") == null)    createPanelPurchase();
                if (screen.findElementByName("panel_WAC_S") != null)    screen.findElementByName("panel_WAC_S").markForRemoval();
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue(purchaseHeight));
            }else
            if (typeActivity.equals(TypeActivity.Ship)){
                //SHIP
                if (screen.findElementByName("panel_WAC_TS") != null)   screen.findElementByName("panel_WAC_TS").markForRemoval();
                if (screen.findElementByName("panel_WAC_O") != null)    screen.findElementByName("panel_WAC_O").markForRemoval();
                if (screen.findElementByName("panel_WAC_P") != null)    screen.findElementByName("panel_WAC_P").markForRemoval();
                if (screen.findElementByName("panel_WAC_S") == null)    createPanelShip();
                ((WindowControl)screen.findNiftyControl("winActivityControl", WindowControl.class)).setHeight(new SizeValue("225px"));
            }
            screen.findElementByName("descriptionValue_WAC").getRenderer(TextRenderer.class).setLineWrapping(true);
            screen.findElementByName("descriptionValue_WAC").getParent().layoutElements();
            screen.findElementByName("winAC_Parent").layoutElements();
            screen.findElementByName("winActivityControl").getParent().layoutElements();
            listBoxActivity.selectItemByIndex(0);
//            this.gralIndex = 0;
        }        
    }
    
    private void getActivityList(){
        listBoxActivity.clear();
        mapActivity.clear();
        int i=0;
        for (E_Activity tActivity : tempActivity.values()){
            listBoxActivity.addItem(tempTypeActivity.toString() + String.valueOf(i+1));
            mapActivity.put(i, tActivity);
            i++;
        }
    }
    
    @NiftyEventSubscriber(id="activitiesList_WAC")
    public void onSelectionListBox_Activity(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getSelectionIndices().size() > 0){
            if (cleanMessage) ((Label)screen.findNiftyControl("messageResult_WAC", Label.class)).setText("");
            updateValues(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))));
        }
    }
    
    private void updateValues(int listBoxIndex){
        activity = mapActivity.get(listBoxIndex);
//        this.gralIndex = activity.getIdActivity();
        if (tempTypeActivity.equals(TypeActivity.Transport) || tempTypeActivity.equals(TypeActivity.Store)){
            transportStore = null;
            if (tempTypeActivity.equals(TypeActivity.Transport)){
                transportStore = gameEngine.getGameData().getMapTransport().get(activity.getIdActivity());
            }else{
                transportStore = gameEngine.getGameData().getMapStore().get(activity.getIdActivity());
            }
            ((Label)screen.findNiftyControl("fromStationValue_WAC_TS", Label.class)).setText(gameEngine.getGameData().getMapUserStation().get(transportStore.getIdStationInitial()).getStationDescription());
            ((Label)screen.findNiftyControl("toStationValue_WAC_TS", Label.class)).setText(gameEngine.getGameData().getMapUserStation().get(transportStore.getIdStationEnd()).getStationDescription());
            ((Label)screen.findNiftyControl("unitLoadValue_WAC_TS", Label.class)).setText(String.valueOf(transportStore.getUnitLoad()));
            ((Slider) screen.findNiftyControl("unitLoadSlider_WAC_TS", Slider.class)).setup(0.f, 100.f, transportStore.getUnitLoad(), 1.f, 1.f);
        }else
        if (tempTypeActivity.equals(TypeActivity.Operation)){
            operation = gameEngine.getGameData().getMapOperation().get(activity.getIdActivity());
            E_Part outputPart = gameEngine.getGameData().getMapUserPart().get(operation.getIdPart());
            ((Label)screen.findNiftyControl("stationValue_WAC_O", Label.class)).setText(gameEngine.getGameData().getMapUserStation().get(operation.getIdStation()).getStationDescription());
            ((Label)screen.findNiftyControl("quantityOutputValue_WAC_O", Label.class)).setText(String.valueOf(operation.getQuantityOutput()*outputPart.getOutputQuantity()));
            //INPUT PARTS REQUIRED
            arrAssembly = outputPart.getArrAssemblyDetails();
            ((TextField)screen.findNiftyControl("assemblyPartRequired_WAC_O", TextField.class)).setEnabled(false);
            ((TextField)screen.findNiftyControl("assemblyQuantity_WAC_O", TextField.class)).setEnabled(false);
            ((ListBox<ListBoxMessages_Assembly>)screen.findNiftyControl("inputPartsRequiredValue_WAC", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_Assembly());
            ((ListBox)screen.findNiftyControl("inputPartsRequiredValue_WAC", ListBox.class)).clear();
            ((ListBox)screen.findNiftyControl("inputPartsRequiredValue_WAC", ListBox.class)).addAllItems(getInputPartsRequired(operation.getQuantityOutput()));
        }else
        if (tempTypeActivity.equals(TypeActivity.Purchase)){
            purchase = gameEngine.getGameData().getMapPurchase().get(activity.getIdActivity());
            ((Label)screen.findNiftyControl("stationValue_WAC_P", Label.class)).setText(gameEngine.getGameData().getMapUserStation().get(purchase.getIdStation()).getStationDescription());
            supplierSelected = gameEngine.getGameData().getMapGameSupplier().get(purchase.getIdSupplier());
            ((DropDown)screen.findNiftyControl("supplierValue_WAC_P", DropDown.class)).clear();
            ((DropDown)screen.findNiftyControl("supplierValue_WAC_P", DropDown.class)).addAllItems(getSuppliers());
            ((DropDown)screen.findNiftyControl("supplierValue_WAC_P", DropDown.class)).selectItem(supplierSelected.getSupplierDescription());
            ((TextField)screen.findNiftyControl("orderPointValue_WAC_P", TextField.class)).setText(String.valueOf(purchase.getOrderPoint()));
           // ((Slider) screen.findNiftyControl("orderPointSlider_WAC_P", Slider.class)).setup(0.f, 100.f, purchase.getOrderPoint(), 1.f, 1.f);
            ((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).setText(String.valueOf(purchase.getOrderQuantity()));
            //((Slider) screen.findNiftyControl("orderQuantitySlider_WAC_P", Slider.class)).setup(0.f, 100.f, purchase.getOrderQuantity(), 1.f, 1.f);
        }else
        if (tempTypeActivity.equals(TypeActivity.Ship)){
            ship = gameEngine.getGameData().getMapShip().get(activity.getIdActivity());
            screen.findElementByName("stationValue_WAC_S").getRenderer(TextRenderer.class).setText("Station " + ship.getIdStation());
        }
        updateData();//UPDATE DYNAMIC DATA
        //REGULAR ITEMS
        ((Label)screen.findNiftyControl("idValue_WAC", Label.class)).setText(String.valueOf(activity.getIdActivity()));
        ((Label)screen.findNiftyControl("descriptionValue_WAC", Label.class)).setText(activity.getActivityDescription());
        ((Label)screen.findNiftyControl("typeActivityValue_WAC", Label.class)).setText(tempTypeActivity.toString());
        ((Label)screen.findNiftyControl("partValue_WAC", Label.class)).setText(gameEngine.getGameData().getMapUserPart().get(activity.getIdPart()).getPartDescription());
    }
    
    @NiftyEventSubscriber(id="unitLoadSlider_WAC_TS")
    public void onUnitLoadSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("unitLoadValue_WAC_TS", Label.class)).setText(String.valueOf((int)event.getValue()));
    }
    
//    @NiftyEventSubscriber(id="orderPointSlider_WAC_P")
//    public void onOrderPointSliderChange(final String id, final SliderChangedEvent event) {
//        ((Label)screen.findNiftyControl("orderPointValue_WAC_P", Label.class)).setText(String.valueOf((int)event.getValue()));
//    }
//   
//    @NiftyEventSubscriber(id="orderQuantitySlider_WAC_P")
//    public void onOrderQuantitySliderChange(final String id, final SliderChangedEvent event) {
//        ((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).setText(String.valueOf((int)event.getValue()));
//        if (supplierSelected != null && purchase != null){
//            ((Label)screen.findNiftyControl("pricePerUnitValue_WAC_P", Label.class)).setText(String.valueOf(getPrice(supplierSelected.getIdSupplier(), purchase.getIdPart(), Integer.parseInt(((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).getText()))));
//            ((Label)screen.findNiftyControl("moneyRequiredValue_WAC_P", Label.class)).setText(String.valueOf(Utils.formatValue2Dec(getPrice(supplierSelected.getIdSupplier(), purchase.getIdPart(), Integer.parseInt(((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).getText()))*Integer.parseInt(((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).getText()))));
//        }
//    }
    
    private ArrayList<String> getSuppliers(){
        ArrayList<String> suppliers = new ArrayList<String>();
        if (purchase != null){
            for (E_Supplier supplier : gameEngine.getGameData().getMapGameSupplier().values()){
                suppliers.add(supplier.getSupplierDescription());
            }
        }
        return suppliers;
    }
    
    private double getPrice(int idSupplierSelected, int idPartSelected, int idQuantity){
        if (purchase != null){
            E_Catalog catalog = gameEngine.getGameData().getMapGameSupplier().get(idSupplierSelected).getArrCatalog().get(idPartSelected);
            if (idQuantity <= catalog.getPriceFunction1Limit())
                return catalog.getPriceFunction1Charge();
            else
            if (idQuantity <= catalog.getPriceFunction2Limit())
                return catalog.getPriceFunction2Charge();
            else
                return catalog.getPriceFunction3Charge();
        }
        return 0;
    }
    
    private double getCatalogPartTime(int idSupplierSelected, int idPartSelected){
        if (purchase != null){
            E_Catalog catalog = gameEngine.getGameData().getMapGameSupplier().get(idSupplierSelected).getArrCatalog().get(idPartSelected);
            return catalog.getProductionCalculated();
        }
        return 0;
    }
    
    @NiftyEventSubscriber(id="supplierValue_WAC_P")
    public void onDropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
        if (event.getSelection() != null){
            for (E_Supplier supplier : gameEngine.getGameData().getMapGameSupplier().values()){
                if (event.getSelection().equals(supplier.getSupplierDescription())){
                    supplierSelected = supplier;
                    break;
                }
            }
            updateData();
        }
    }
    
    private ArrayList<ListBoxMessages_Assembly> getInputPartsRequired(int newFactor){
        ArrayList<ListBoxMessages_Assembly> arrAssemblyNames = new ArrayList<ListBoxMessages_Assembly>();
        for (int i=0; i<arrAssembly.size(); i++){
            arrAssemblyNames.add(new ListBoxMessages_Assembly(" " + gameEngine.getGameData().getMapUserPart().get(arrAssembly.get(i).getIdInputPart()).getPartDescription() + " - " + arrAssembly.get(i).getIdInputPart(), String.valueOf(arrAssembly.get(i).getQuantity()*newFactor)));
        }
        return arrAssemblyNames;
    }
    
    private void createPanelTransportStore(){
        new PanelBuilder("panel_WAC_TS") {{  //TRANSPORT - STORE
            childLayoutVertical();
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("fromStation_WAC_TS","From Station:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("fromStationValue_WAC_TS","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("fromBucket_WAC_TS","Inventory:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("fromBucketValue_WAC_TS","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();    height("22px");
                control(new LabelBuilder("unitLoad_WAC_TS","Unit Load:"){{   width(width_label);  height("20px"); textHAlign(activity_label); }});  panel(common.hspacer("5px"));
//                control(new TextFieldBuilder("unitLoadValue_WAC_TS"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
                control(new LabelBuilder("unitLoadValue_WAC_TS",""){{   width("20px");  height("20px"); textHAlignCenter(); }});  panel(common.hspacer("5px"));
                control(new SliderBuilder("unitLoadSlider_WAC_TS", false){{ width("95px"); height("20px"); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("toStation_WAC_TS","To Station:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("toStationValue_WAC_TS","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("toBucket_WAC_TS","Inventory:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("toBucketValue_WAC_TS","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
//            panel(common.vspacer("5px"));
//            control(new LabelBuilder("skillRequired_WAC","Skills Required"){{   width("100%");  height("20px");  textHAlign(Align.Center);  }});
//            panel(common.vspacer("5px"));
//            control(new ListBoxBuilder("skillsRequiredValue_WAC") {{
//                displayItems(6);
//                selectionModeSingle();
//                showVerticalScrollbar();
//                hideHorizontalScrollbar();
//                width("*");                        
//            }});
            width("100%");
        }}.build(nifty, screen, screen.findElementByName("winAC_Parent"));
    }
    
    private void createPanelOperation(){
        new PanelBuilder("panel_WAC_O"){{  //OPERATION
            childLayoutVertical();
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("station_WAC_O","Station:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("stationValue_WAC_O","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("outputBucket_WAC_O","Station Inventory:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("outputBucketValue_WAC_O","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();    height("22px");
                control(new LabelBuilder("quantityOutput_WAC_O","Output Quantity:"){{   width(width_label);  height("20px"); textHAlign(activity_label); }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("quantityOutputValue_WAC_O"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(common.vspacer("5px"));
            control(new LabelBuilder("inputPartsRequired_WAC","Input Parts Required"){{   width("100%");  height("20px");  textHAlign(Align.Center);  }});
            panel(common.vspacer("5px"));
            panel(new PanelBuilder() {{
                childLayoutHorizontal();
                control(new TextFieldBuilder("assemblyPartRequired_WAC_O","  Part required") {{ width("140px"); }});
                control(new TextFieldBuilder("assemblyQuantity_WAC_O","  Quantity") {{ width("80px"); }});
            }});
            control(new ListBoxBuilder("inputPartsRequiredValue_WAC") {{
                displayItems(6);
                selectionModeSingle();
                showVerticalScrollbar();
                hideHorizontalScrollbar();
                width("*");
                control(new ControlBuilder("customListBox_AssemblyDetail"){{
                    controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                }});
            }});
            width("100%");
        }}.build(nifty, screen, screen.findElementByName("winAC_Parent"));
    }
    
    private void createPanelPurchase(){
        new PanelBuilder("panel_WAC_P"){{  //PURCHASE
            childLayoutVertical();
            visibleToMouse(true);
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("station_WAC_P","Station:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("stationValue_WAC_P","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("bucket_WAC_P","Station Inventory:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("bucketValue_WAC_P","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("currentInventory_WAC_P","General Inventory:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("currentInventoryValue_WAC_P","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();    height("22px");
                control(new LabelBuilder("orderPoint_WAC_P","Reorder Point:"){{   width(width_label);  height("20px"); textHAlign(activity_label); }});  panel(common.hspacer("5px"));
               // control(new LabelBuilder("orderPointValue_WAC_P",""){{   width("20px");  height("20px"); textHAlignCenter(); }});  panel(common.hspacer("5px"));
               // control(new SliderBuilder("orderPointSlider_WAC_P", false){{ width("95px"); height("20px"); }});
                control(new TextFieldBuilder("orderPointValue_WAC_P"){{   width("60px");  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();    height("22px");
                control(new LabelBuilder("orderQuantity_WAC_P","Order Quantity:"){{   width(width_label);  height("20px"); textHAlign(activity_label); }});  panel(common.hspacer("5px"));
                //control(new LabelBuilder("orderQuantityValue_WAC_P",""){{   width("20px");  height("20px"); textHAlignCenter(); }});  panel(common.hspacer("5px"));
               // control(new SliderBuilder("orderQuantitySlider_WAC_P", false){{ width("95px"); height("20px"); }});
                control(new TextFieldBuilder("orderQuantityValue_WAC_P"){{   width("60px");  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();    height("25px");
                control(new LabelBuilder("supplier_WAC_P","Supplier:"){{   width(width_label);  height("20px"); textHAlign(activity_label); }});  panel(common.hspacer("5px"));
                control(new DropDownBuilder("supplierValue_WAC_P"){{   width(width_value);  height("20px"); textHAlign(activity_value); visibleToMouse(true); interactOnRelease("false;"); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();    height("22px");
                control(new LabelBuilder("pricePerUnit_WAC_P","Price Per Unit:"){{   width(width_label);  height("20px"); textHAlign(activity_label); }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("pricePerUnitValue_WAC_P"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("moneyRequired_WAC_P","Money Required:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("moneyRequiredValue_WAC_P","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("timeToArriveOrder_WAC_P","Time To Arrive:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("timeToArriveOrderValue_WAC_P","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            width("100%");
        }}.build(nifty, screen, screen.findElementByName("winAC_Parent"));
    }
    
    private void createPanelShip(){
        new PanelBuilder("panel_WAC_S"){{  //SHIP
            childLayoutVertical();
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("station_WAC_S","Station:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                text(new TextBuilder("stationValue_WAC_S"){{   
                    text("_");
                    style("base-font-link");  width(width_value);  height("20px");  textHAlign(activity_value); 
                    set("action", "clickStation_S()");
                }});
            }});
            panel(new PanelBuilder(){{
                childLayoutHorizontal();
                control(new LabelBuilder("bucket_WAC_S","Bucket:"){{   width(width_label);  height("20px");  textHAlign(activity_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("bucketValue_WAC_S","_"){{   width(width_value);  height("20px"); textHAlign(activity_value); }});
            }});
            width("100%");
        }}.build(nifty, screen, screen.findElementByName("winAC_Parent"));
    }
    
    public void updateData(){
        if (activity == null) return;
        if (gralTypeActivity.equals(TypeActivity.Transport) || gralTypeActivity.equals(TypeActivity.Store)){
            //TRANSPORT - STORE
            transportStore = null;
            if (gralTypeActivity.equals(TypeActivity.Transport))
                transportStore = gameEngine.getGameData().getMapTransport().get(activity.getIdActivity());
            else
                transportStore = gameEngine.getGameData().getMapStore().get(activity.getIdActivity());
            E_Bucket fromBucket = null;
            for (Pair<ArrayList<Integer>,E_Bucket> tempFixedBucket : gameEngine.getGameData().getMapUserStation().get(transportStore.getIdStationInitial()).getArrBucketsFixed()){
                if (tempFixedBucket.getSecond().getIdPart() == transportStore.getIdPart()){
                    fromBucket = tempFixedBucket.getSecond();
                    break;
                }
            }
            E_Bucket toBucket = null;
            for (Pair<ArrayList<Integer>,E_Bucket> tempFixedBucket : gameEngine.getGameData().getMapUserStation().get(transportStore.getIdStationEnd()).getArrBucketsFixed()){
                if (tempFixedBucket.getSecond().getIdPart() == transportStore.getIdPart()){
                    toBucket = tempFixedBucket.getSecond();
                    break;
                }
            }
            ((Label)screen.findNiftyControl("fromBucketValue_WAC_TS", Label.class)).setText(fromBucket.getSize() + " " + gameEngine.getGameData().getMapUserPart().get(transportStore.getIdPart()).getUnit().toString());
            ((Label)screen.findNiftyControl("toBucketValue_WAC_TS", Label.class)).setText(toBucket.getSize() + " " + gameEngine.getGameData().getMapUserPart().get(transportStore.getIdPart()).getUnit().toString());
        }else
        if (gralTypeActivity.equals(TypeActivity.Operation)){
            //OPERATION
            operation = gameEngine.getGameData().getMapOperation().get(activity.getIdActivity());
            E_Bucket bucket = null;
            for (Pair<ArrayList<Integer>,E_Bucket> tempFixedBucket : gameEngine.getGameData().getMapUserStation().get(operation.getIdStation()).getArrBucketsFixed()){
                if (tempFixedBucket.getSecond().getIdPart() == operation.getIdPart()){
                    bucket = tempFixedBucket.getSecond();
                    break;
                }
            }
            ((Label)screen.findNiftyControl("outputBucketValue_WAC_O", Label.class)).setText(bucket.getSize() + " (Max " + bucket.getCapacity() + ") " + gameEngine.getGameData().getMapUserPart().get(operation.getIdPart()).getUnit().toString());
        }else
        if (gralTypeActivity.equals(TypeActivity.Purchase)){
            //PURCHASE
            purchase = gameEngine.getGameData().getMapPurchase().get(activity.getIdActivity());
            E_Bucket bucket = null;
            for (Pair<ArrayList<Integer>,E_Bucket> tempFixedBucket : gameEngine.getGameData().getMapUserStation().get(purchase.getIdStation()).getArrBucketsFixed()){
                if (tempFixedBucket.getSecond().getIdPart() == purchase.getIdPart()){
                    bucket = tempFixedBucket.getSecond();
                    break;
                }
            }
            ((Label)screen.findNiftyControl("bucketValue_WAC_P", Label.class)).setText(bucket.getSize() + " (Max " + bucket.getCapacity() + ") " + gameEngine.getGameData().getMapUserPart().get(purchase.getIdPart()).getUnit().toString());
            ((Label)screen.findNiftyControl("currentInventoryValue_WAC_P", Label.class)).setText(gameEngine.getGameData().getMapUserPart().get(purchase.getIdPart()).getCurrentStock() + " " + gameEngine.getGameData().getMapUserPart().get(purchase.getIdPart()).getUnit().toString());
            if (supplierSelected != null){
                int temp;
                try
                {
                    temp=Integer.parseInt(((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).getText());
                }
                catch(Exception e)
                {
                    temp=0;
                }
                ((Label)screen.findNiftyControl("pricePerUnitValue_WAC_P", Label.class)).setText(String.valueOf(getPrice(supplierSelected.getIdSupplier(), purchase.getIdPart(),temp)));
                ((Label)screen.findNiftyControl("timeToArriveOrderValue_WAC_P", Label.class)).setText(gameEngine.getGameData().convertTimeUnistToHourMinute((int)getCatalogPartTime(supplierSelected.getIdSupplier(), purchase.getIdPart())) + " hours");
                if (temp > bucket.getCapacity()){
                    ((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).setText(String.valueOf(bucket.getCapacity()));
                }
                else if (temp<0)
                {
                temp=0;
                }
                ((Label)screen.findNiftyControl("moneyRequiredValue_WAC_P", Label.class)).setText(String.valueOf(Utils.formatValue2Dec(getPrice(supplierSelected.getIdSupplier(), purchase.getIdPart(), temp))));
            }
        }else
        if (gralTypeActivity.equals(TypeActivity.Ship)){
            //SHIP
            ship = gameEngine.getGameData().getMapShip().get(activity.getIdActivity());
            E_Bucket bucket = null;
            for (E_Bucket tempBucket : gameEngine.getGameData().getMapUserStation().get(ship.getIdStation()).getArrBuckets()){
                if (tempBucket.getIdPart() == ship.getIdPart()){
                    bucket = tempBucket;
                    break;
                }
            }
            ((Label)screen.findNiftyControl("bucketValue_WAC_S", Label.class)).setText(bucket.getSize() + "(Max " + bucket.getCapacity() + ") " + gameEngine.getGameData().getMapUserPart().get(ship.getIdPart()).getUnit().toString());
        }
    }
    
    @NiftyEventSubscriber(id="activityRefresh")
    public void onRefreshButtonClicked(final String id, final ButtonClickedEvent event) {
        cleanMessage = false;
        gameEngine.updateLastActivitySystemTime();
        updateData();
        if (gralTypeActivity.equals(TypeActivity.Transport) || gralTypeActivity.equals(TypeActivity.Store)){
            transportStore.setUnitLoad(Integer.parseInt(((Label)screen.findNiftyControl("unitLoadValue_WAC_TS", Label.class)).getText()));
            ((Label)screen.findNiftyControl("messageResult_WAC", Label.class)).setText("Updated successfully");
        }else
        if (gralTypeActivity.equals(TypeActivity.Purchase)){
           int temp1,temp2;
           try
           {
               temp1=Integer.parseInt(((TextField)screen.findNiftyControl("orderPointValue_WAC_P", TextField.class)).getText());
               if (temp1<0)
               {
                   temp1=0;
                   ((TextField)screen.findNiftyControl("orderPointValue_WAC_P", TextField.class)).setText("0");
               }
           }
           catch(Exception e)
           {
               temp1=0;
              ((TextField)screen.findNiftyControl("orderPointValue_WAC_P", TextField.class)).setText("0");
           }
           try
           {
               temp2=Integer.parseInt(((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).getText());
               if (temp2<0)
               {
                   temp2=0;
                   ((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).setText("0");
               }
           }
           catch(Exception e)
           {
               temp2=0;
               ((TextField)screen.findNiftyControl("orderQuantityValue_WAC_P", TextField.class)).setText("0");
           }
            purchase.setOrderPoint(temp1);
            purchase.setOrderQuantity(temp2);
            if (supplierSelected != null){
                purchase.setIdSupplier(supplierSelected.getIdSupplier());
            }
            ((Label)screen.findNiftyControl("messageResult_WAC", Label.class)).setText("Updated successfully");
            nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupControl.class).setupPurchaseDone();
        }
        cleanMessage = true;
    }
}