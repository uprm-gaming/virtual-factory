package com.virtualfactory.screen.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ElementBuilder.Align;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.RadioButton;
import de.lessvoid.nifty.controls.RadioButtonStateChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.WindowClosedEvent;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.SizeValue;
import com.virtualfactory.engine.GameEngine;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.utils.CommonBuilders;
import com.virtualfactory.utils.MachineCategory;
import com.virtualfactory.utils.MessageType;
import com.virtualfactory.utils.Messages;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author David
 */
public class MachineScreenController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private TextField gameDescription;
    private ListBox<String> listBoxGames;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    private WindowControl winControls;
    private E_Machine machine;
    private int idActivity = -1;
    private boolean isVisible;
    private Align machine_label = Align.Right;
    private Align machine_value = Align.Left;
    private final CommonBuilders common = new CommonBuilders();
    private String initialHeightSize = "415px";
    private String newHeightSize = "435px";
    private String idButton = "";
    private ListBox<String> listBoxMachineEquipment;
    private Map<Integer, E_Machine> mapMachineEquipment;
    private MachineCategory categoryMachineEquipment;
    private Map<Integer, E_Machine> tempMachineEquipment;
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
        this.winControls = screen.findNiftyControl("winMachineControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
        listBoxMachineEquipment = ((ListBox<String>)screen.findNiftyControl("machinesList_WMC", ListBox.class));
    }
    
    @NiftyEventSubscriber(id="winMachineControl")
    public void onCloseButtonClicked(final String id, final WindowClosedEvent event) {
        if (machine != null) machine.showHideSpot(false);
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
        idButton = "";
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
            ((Label)screen.findNiftyControl("idValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("descriptionValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("buySellValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("percentageDepreciationValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("percentageDepreciationAccumulatedValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("statusValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("currentLocationValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("costPerHourValue_WMC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("totalCostValue_WMC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("percentageAvailabilityValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("percentageUsageValue_WMC", Label.class)).setText("_");
            idActivity = -1;
            ((Label)screen.findNiftyControl("activityAssignedValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("percentageProbabilityFailureValue_WMC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("performPreventiveMaintenanceValue_WMC", Label.class)).setText("_");
            for(Element tempElement : screen.findElementByName("partsProduced_Parent").getElements()){
                tempElement.markForRemoval();
            }
            winControls.setHeight(new SizeValue(initialHeightSize));
            screen.findElementByName("partsProduced_Parent").layoutElements();
            screen.findElementByName("winMachineControl").getParent().layoutElements();
            if (machine != null) machine.showHideSpot(false);
            listBoxMachineEquipment.clear();
        }else{
            listBoxMachineEquipment.clear();
            mapMachineEquipment = new HashMap<Integer, E_Machine>();
            categoryMachineEquipment = null;
            if (index != Params.machineList && index != Params.equipmentList){
                machine = gameEngine.getGameData().getMapUserMachine().get(index);
                categoryMachineEquipment = machine.getMachineCategory();
            }else{
                machine = null;
                if (index == Params.machineList)
                    categoryMachineEquipment = MachineCategory.Operation;
                else
                if (index == Params.equipmentList)
                    categoryMachineEquipment = MachineCategory.Transport;
            }
            if (categoryMachineEquipment.equals(MachineCategory.Operation))
                tempMachineEquipment = gameEngine.getGameData().getMapUserMachineByOperation();
            else
                tempMachineEquipment = gameEngine.getGameData().getMapUserMachineByTransport();
            index = getMachineList();
            
            String newHeight = initialHeightSize;
            for(Element tempElement : screen.findElementByName("partsProduced_Parent").getElements()){//remove elements
                nifty.removeElement(screen, tempElement);
            }
            nifty.executeEndOfFrameElementActions();
//            screen.findElementByName("partsProduced_Parent").layoutElements();
//            screen.findElementByName("winMachineControl").getParent().layoutElements();
            if (categoryMachineEquipment.equals(MachineCategory.Operation)){
                ((Label)screen.findNiftyControl("id_WMC", Label.class)).setText("Machine ID:");
                newHeight = newHeightSize;
                new PanelBuilder(){{//add elements
                    childLayoutHorizontal();
                    control(new LabelBuilder("numberPartsProduced_WMC","# Parts Produced:"){{   width("170px");  height("20px");  textHAlign(machine_label);  }});  panel(common.hspacer("5px"));
                    control(new LabelBuilder("numberPartsProducedValue_WMC","_"){{   width("70px");  height("20px"); textHAlign(machine_value); }});
                }}.build(nifty, screen, screen.findElementByName("partsProduced_Parent"));
            }else
            if (categoryMachineEquipment.equals(MachineCategory.Transport)){
                ((Label)screen.findNiftyControl("id_WMC", Label.class)).setText("Equipment ID:");
            }
            winControls.setHeight(new SizeValue(newHeight));
            nifty.executeEndOfFrameElementActions();
//            screen.findElementByName("partsProduced_Parent").layoutElements();
//            screen.findElementByName("winMachineControl").getParent().layoutElements();
            listBoxMachineEquipment.selectItemByIndex(index);
        }
        screen.findElementByName("activityAssignedValue_WMC").getRenderer(TextRenderer.class).setLineWrapping(true);
        screen.findElementByName("activityAssignedValue_WMC").getParent().layoutElements();
        winControls.getElement().layoutElements();
        winControls.getElement().getParent().layoutElements();
    }
    
    private int getMachineList(){
        listBoxMachineEquipment.clear();
        mapMachineEquipment.clear();
        int i=0;
        int newIndex = 0;
        for (E_Machine tMachine : tempMachineEquipment.values()){
            listBoxMachineEquipment.addItem(categoryMachineEquipment.toString() + " " + String.valueOf(i+1) + " (" + (tMachine.getMachineState().equals(ObjectState.Active) ? Params.active : Params.inactive) + ")");
            mapMachineEquipment.put(i, tMachine);
            if (machine != null)
                if (machine.equals(tMachine))
                    newIndex = i;
            i++;
        }
        return newIndex;
    }
    
    private void updateValues(int listBoxIndex){
        machine = mapMachineEquipment.get(listBoxIndex);
        machine.showHideSpot(true);
        if (machine.getMachineCategory().equals(MachineCategory.Operation)){
            if (machine.getMachineState().equals(ObjectState.Active))
                winControls.setTitle("Machine " + String.valueOf(listBoxIndex+1) + " (Purchased)");
            else
                winControls.setTitle("Machine " + String.valueOf(listBoxIndex+1) + " (Sold)");
        }else
        if (machine.getMachineCategory().equals(MachineCategory.Transport)){
            if (machine.getMachineState().equals(ObjectState.Active))
                winControls.setTitle("Equipment " + String.valueOf(listBoxIndex+1) + " (Purchased)");
            else
                winControls.setTitle("Equipment " + String.valueOf(listBoxIndex+1) + " (Sold)");
        }
        ((Label)screen.findNiftyControl("idValue_WMC", Label.class)).setText(String.valueOf(machine.getIdMachine()));
        ((Label)screen.findNiftyControl("descriptionValue_WMC", Label.class)).setText(machine.getMachineDescription());
        ((Label)screen.findNiftyControl("percentageDepreciationValue_WMC", Label.class)).setText(Utils.formatValue2DecToString(machine.getPercentageDepreciation()*100.0) + "%");
        if (machine.getMachineState() == ObjectState.Active){
            ((RadioButton)screen.findNiftyControl("activate_WMC_True", RadioButton.class)).select();
        }else{
            ((RadioButton)screen.findNiftyControl("activate_WMC_False", RadioButton.class)).select();
        }                
        ((Label)screen.findNiftyControl("costPerHourValue_WMC", Label.class)).setText(String.valueOf(machine.getCostPerHour()));
        ((Label)screen.findNiftyControl("performPreventiveMaintenanceValue_WMC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(machine.getPricePreventiveMaintenance()));
        updateData();
    }
    
    @NiftyEventSubscriber(id="machinesList_WMC")
    public void onSelectionListBox(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getSelectionIndices().size() > 0){
            if (machine != null) machine.showHideSpot(false);
            if (cleanMessage) ((Label)screen.findNiftyControl("messageResult_WMC", Label.class)).setText("");
            updateValues(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))));
        }
    }
    
    @NiftyEventSubscriber(pattern="activate_WMC_.*")
    public void onSelectionRadioButton(final String id, final RadioButtonStateChangedEvent event) {
        ((Label)screen.findNiftyControl("messageResult_WMC", Label.class)).setText("");
    }
    
    public String getIdButton() {
        return idButton;
    }

    public void setIdButton(String idButton) {
        this.idButton = idButton;
    }
    
    @NiftyEventSubscriber(id="machineUpdate")
    public void onUpdateButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        //update STATE
        cleanMessage = false;
        ObjectState currentStateMachine = ((RadioButton)screen.findNiftyControl("activate_WMC_True", RadioButton.class)).isActivated() == true ? ObjectState.Active : ObjectState.Inactive;
        if (machine.getMachineState() != currentStateMachine){
            if (machine.getStatus() == Status.Idle){
                machine.setMachineState(currentStateMachine);
                ((Label)screen.findNiftyControl("messageResult_WMC", Label.class)).setText("Updated successfully");
            }else{
                machine.activateLaterDeactivation = true;
                ((Label)screen.findNiftyControl("messageResult_WMC", Label.class)).setText("Updated to a later deactivation");
            }
            if (machine.getMachineCategory().equals(MachineCategory.Operation)){
                if (currentStateMachine.equals(ObjectState.Active)){
                    gameEngine.getGameData().setTotalMachinesPurchase(gameEngine.getGameData().getTotalMachinesPurchase() + machine.getPriceForPurchase());
                    winControls.setTitle("Machine " + machine.getIdMachine() + " (Purchased)");
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentPurchased.replace(Messages.wildCard, "Machine").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
                }else{
                    gameEngine.getGameData().setTotalMachinesSale(gameEngine.getGameData().getTotalMachinesSale() + machine.getPriceForSell());
                    winControls.setTitle("Machine " + machine.getIdMachine() + " (Sold)");
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentSold.replace(Messages.wildCard, "Machine").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
                }
            }else{
                if (currentStateMachine.equals(ObjectState.Active)){
                    gameEngine.getGameData().setTotalEquipmentPurchase(gameEngine.getGameData().getTotalEquipmentPurchase() + machine.getPriceForPurchase());
                    winControls.setTitle("Equipment " + machine.getIdMachine() + " (Purchased)");
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentPurchased.replace(Messages.wildCard, "Equipment").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
                }else{
                    gameEngine.getGameData().setTotalEquipmentSale(gameEngine.getGameData().getTotalEquipmentSale() + machine.getPriceForSell());
                    winControls.setTitle("Equipment " + machine.getIdMachine() + " (Sold)");
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentSold.replace(Messages.wildCard, "Equipment").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
                }
            }
//            gameEngine.getGeneralScreenController().updateOptionButtonClicked();    //update buttons
            updateOptionButtonClicked();
        }else{
            if (machine.activateLaterDeactivation){
                machine.activateLaterDeactivation = false;
                gameEngine.getGameData().setTotalEquipmentSale(gameEngine.getGameData().getTotalEquipmentSale() - machine.getTempPriceForSell());
                if (machine.getMachineCategory().equals(MachineCategory.Operation))
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentReturned.replace(Messages.wildCard, "Machine").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
                else
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentReturned.replace(Messages.wildCard, "Equipment").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
                ((Label)screen.findNiftyControl("messageResult_WMC", Label.class)).setText("Updated successfully");
            }
        }
        updateData();
        cleanMessage = true;
        if (Params.isObjectiveLevel && Params.objective.getCurrentStep() == 5)
            Params.objective.nextStep();

    }
    
    public void updateData(){
        if (machine == null) return;
        if (machine.activateLaterDeactivation)
            ((Label)screen.findNiftyControl("statusValue_WMC", Label.class)).setText(machine.getStatus().toString() + " (Later Deactivation)");
        else
            ((Label)screen.findNiftyControl("statusValue_WMC", Label.class)).setText(machine.getStatus().toString());
        ((Label)screen.findNiftyControl("currentLocationValue_WMC", Label.class)).setText("(" + machine.getCurrentLocationX() + "," + machine.getCurrentLocationZ() + ")");
        ((Label)screen.findNiftyControl("percentageDepreciationAccumulatedValue_WMC", Label.class)).setText(Utils.formatValue2DecToString(machine.getPercentageAccumulatedDepreciation()*100.0) + "%");
        ((Label)screen.findNiftyControl("buySellValue_WMC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(machine.getPriceForPurchase()) + "/" + Utils.formatValue2DecToString(machine.getPriceForSell()));
        ((Label)screen.findNiftyControl("totalCostValue_WMC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(machine.getAccumulatedCost()));
        ((Label)screen.findNiftyControl("percentageAvailabilityValue_WMC", Label.class)).setText(Utils.formatValue2DecToString(machine.getPercentageAvailability()*100.0) + "%");
        ((Label)screen.findNiftyControl("percentageUsageValue_WMC", Label.class)).setText(Utils.formatValue2DecToString(machine.getPercentageUsage()*100.0) + "%");
        double probFailure = machine.getCurrentWorkingTime()/machine.getTimeBetweenFailuresCalculated() > 1.0 ? 1.0 : 
                (machine.getCurrentWorkingTime()/machine.getTimeBetweenFailuresCalculated() < 0.0 ? 0.0 : machine.getCurrentWorkingTime()/machine.getTimeBetweenFailuresCalculated());
        ((Label)screen.findNiftyControl("percentageProbabilityFailureValue_WMC", Label.class)).setText(Utils.formatValue2DecToString(probFailure*100.0) + "%");
        if (machine.getMachineCategory().equals(MachineCategory.Operation))
            ((Label)screen.findNiftyControl("numberPartsProducedValue_WMC", Label.class)).setText(Utils.formatValue2DecToString(machine.getAccumulatedPartsProduced()));
        ((Label)screen.findNiftyControl("activityAssignedValue_WMC", Label.class)).setText(machine.getActivityDescriptionAssigned());
        screen.findElementByName("activityAssignedValue_WMC").getRenderer(TextRenderer.class).setLineWrapping(true);
        screen.findElementByName("activityAssignedValue_WMC").getParent().layoutElements();
        screen.findElementByName("winMachineControl").layoutElements();
        screen.findElementByName("winMachineControl").getParent().layoutElements();
    }
    
    @NiftyEventSubscriber(id="performPreventiveMaintenanceButton")
    public void onPerformPreventiveMaintenanceButtonClicked(final String id, final ButtonClickedEvent event) {
        if (!machine.getStatus().equals(Status.Broken)){
            machine.setCurrentWorkingTime(0);
            if (machine.getMachineCategory().equals(MachineCategory.Operation)){
                gameEngine.getGameData().setTotalMachinePreventiveMaintenance(gameEngine.getGameData().getTotalMachinePreventiveMaintenance() + machine.getPricePreventiveMaintenance());
                GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentPreventiveMaintenance.replace(Messages.wildCard, "Machine").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
            }else{
                gameEngine.getGameData().setTotalEquipmentPreventiveMaintenance(gameEngine.getGameData().getTotalEquipmentPreventiveMaintenance() + machine.getPricePreventiveMaintenance());
                GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentPreventiveMaintenance.replace(Messages.wildCard, "Equipment").replace(Messages.wildCard2, String.valueOf(machine.getIdMachine())));
            }
        }
    }
    
    private void updateOptionButtonClicked(){
        int newIndex = getMachineList();
        listBoxMachineEquipment.selectItemByIndex(newIndex);
//        if (!idButton.isEmpty() && machine != null){
//            String machineEquipment = "";
//            if (machine.getMachineCategory().equals(MachineCategory.Operation))
//                machineEquipment = "Machine ";
//            else
//                machineEquipment = "Equipment ";
//            gameEngine.getGeneralScreenController().updateSubLevelButtonText(idButton, machineEquipment + machine.getIdMachine() + " (" + (machine.getMachineState().equals(ObjectState.Inactive) ? Params.inactive : Params.active) + ")");
//        }            
    }
}