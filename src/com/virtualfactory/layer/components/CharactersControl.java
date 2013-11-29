/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.layer.components;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.strategy.EventStrategy;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.OperatorCategory;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeActivity;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 *
 * @author David
 */
public class CharactersControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private GameEngine gameEngine;
    private ArrayList<Integer> arrOperators;
    private boolean anyChange = false;
    double newMachineBuy = 0.0;
    double newMachineSell = 0.0;
    double newEquipmentBuy = 0.0;
    double newEquipmentSell = 0.0;
    boolean isMachineBuying = true;
    boolean isEquipmentBuying = true;
    double newOperatorHire = 0.0;
    double newOperatorFire = 0.0;
    double newTotalValue = 0.0;
    private Map<Integer, E_Machine> mapUserMachineTemp;
    private Map<Integer, E_Machine> mapUserEquipmentTemp;
    private Map<Integer, E_Operator> mapUserOperatorTemp;

    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winCharactersControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
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
        mapUserOperatorTemp = new HashMap<Integer, E_Operator>();
        mapUserMachineTemp = new HashMap<Integer, E_Machine>();
        mapUserEquipmentTemp = new HashMap<Integer, E_Machine>();
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
                if (winControls.getWidth() + position.getFirst() > gameEngine.app.getGuiViewPort().getCamera().getWidth())
                    position.setFirst(gameEngine.app.getGuiViewPort().getCamera().getWidth() - winControls.getWidth());
                if (winControls.getHeight() + position.getSecond() > gameEngine.app.getGuiViewPort().getCamera().getHeight())
                    position.setSecond(gameEngine.app.getGuiViewPort().getCamera().getHeight() - winControls.getHeight());
                winControls.getElement().setConstraintX(new SizeValue(position.getFirst() + "px"));
                winControls.getElement().setConstraintY(new SizeValue(position.getSecond() + "px"));
                winControls.getElement().getParent().layoutElements();
            }            
            winControls.getElement().setConstraintX(null);
            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
    
    private void setSliders(){
        gameEngine.getGameData().updateUserMachineEquipment();
        gameEngine.getGameData().updateUserOperatorCategories();
        ((Slider) screen.findNiftyControl("operatorsValue_WChC", Slider.class)).setup(0.f, gameEngine.getGameData().getMapUserOperator().size(), gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Versatile_Act(), 1.f, 1.f);
        ((Slider) screen.findNiftyControl("machinesValue_WChC", Slider.class)).setup(0.f, gameEngine.getGameData().getMapUserMachineByOperation().size(), gameEngine.getGameData().getUserMachine_Act(), 1.f, 1.f);
        ((Slider) screen.findNiftyControl("equipmentValue_WChC", Slider.class)).setup(0.f, gameEngine.getGameData().getMapUserMachineByTransport().size(), gameEngine.getGameData().getUserEquipment_Act(), 1.f, 1.f);
        ((Slider) screen.findNiftyControl("carrierValue_WChC", Slider.class)).setup(0.f, gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Versatile_Act(), gameEngine.getGameData().getUserOperator_Carrier_Act(), 1.f, 1.f);
        ((Slider) screen.findNiftyControl("assemblerValue_WChC", Slider.class)).setup(0.f, gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Versatile_Act(), gameEngine.getGameData().getUserOperator_Assembler_Act(), 1.f, 1.f);
        ((Slider) screen.findNiftyControl("versatileValue_WChC", Slider.class)).setup(0.f, gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Versatile_Act(), gameEngine.getGameData().getUserOperator_Versatile_Act(), 1.f, 1.f);
        nifty.executeEndOfFrameElementActions();
    }
    
    private void loadValues(int index){
        if (index == -1){
            ((Label)screen.findNiftyControl("carrierText_WChC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("assemblerText_WChC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("versatileText_WChC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("operatorsText_WChC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("machinesText_WChC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("equipmentText_WChC", Label.class)).setText("_");
        }else{
            setSliders();
            ((Label)screen.findNiftyControl("carrierText_WChC", Label.class)).setText(String.valueOf((int)((Slider) screen.findNiftyControl("carrierValue_WChC", Slider.class)).getValue()));
            ((Label)screen.findNiftyControl("assemblerText_WChC", Label.class)).setText(String.valueOf((int)((Slider) screen.findNiftyControl("assemblerValue_WChC", Slider.class)).getValue()));
            ((Label)screen.findNiftyControl("versatileText_WChC", Label.class)).setText(String.valueOf((int)((Slider) screen.findNiftyControl("versatileValue_WChC", Slider.class)).getValue()));
            ((Label)screen.findNiftyControl("operatorsText_WChC", Label.class)).setText(String.valueOf((int)((Slider) screen.findNiftyControl("operatorsValue_WChC", Slider.class)).getValue()) + "/" + gameEngine.getGameData().getMapUserOperator().size());
            ((Label)screen.findNiftyControl("machinesText_WChC", Label.class)).setText(String.valueOf((int)((Slider) screen.findNiftyControl("machinesValue_WChC", Slider.class)).getValue()) + "/" + gameEngine.getGameData().getMapUserMachineByOperation().size());
            ((Label)screen.findNiftyControl("equipmentText_WChC", Label.class)).setText(String.valueOf((int)((Slider) screen.findNiftyControl("equipmentValue_WChC", Slider.class)).getValue()) + "/" + gameEngine.getGameData().getMapUserMachineByTransport().size());
            someChange();
            updateData();
        }
        cleanControls();
    }
    
    private void cleanControls(){
        ((Label)screen.findNiftyControl("hire_WChC", Label.class)).setText("Hire x 0:");
        ((Label)screen.findNiftyControl("fire_WChC", Label.class)).setText("Fire x 0:");
        ((Label)screen.findNiftyControl("buyMachine_WChC", Label.class)).setText("Buy x 0:");
        ((Label)screen.findNiftyControl("sellMachine_WChC", Label.class)).setText("Sell x 0:");
        ((Label)screen.findNiftyControl("buyEquipment_WChC", Label.class)).setText("Buy x 0:");
        ((Label)screen.findNiftyControl("sellEquipment_WChC", Label.class)).setText("Sell x 0:");
        ((Label)screen.findNiftyControl("hireValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("fireValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("buyMachineValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("sellMachineValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("buyEquipmentValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("sellEquipmentValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("totalValue_WChC", Label.class)).setText("$ 0.00");
        newMachineBuy = 0.0;
        newMachineSell = 0.0;
        newEquipmentBuy = 0.0;
        newEquipmentSell = 0.0;
        newOperatorHire = 0.0;
        newOperatorFire = 0.0;
        newTotalValue = 0.0;
    }
    
    @NiftyEventSubscriber(id="carrierValue_WChC")
    public void onCarrierSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("carrierText_WChC", Label.class)).setText(String.valueOf((int)event.getValue()));
        someChange();
    }
    
    @NiftyEventSubscriber(id="assemblerValue_WChC")
    public void onAssemblerSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("assemblerText_WChC", Label.class)).setText(String.valueOf((int)event.getValue()));
        someChange();
    }
    
    @NiftyEventSubscriber(id="versatileValue_WChC")
    public void onVersatileSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("versatileText_WChC", Label.class)).setText(String.valueOf((int)event.getValue()));
        someChange();
    }
    
    @NiftyEventSubscriber(id="operatorsValue_WChC")
    public void onOperatorsSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("operatorsText_WChC", Label.class)).setText(String.valueOf((int)event.getValue()) + "/" + gameEngine.getGameData().getMapUserOperator().size());
        someChange();
        int newOperatorValue = (int)event.getValue();
        int oldOperatorValue = gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Versatile_Act();
        newOperatorHire = 0.00;
        newOperatorFire = 0.00;
        int countOperatorHire = 0;
        int countOperatorFire = 0;
        mapUserOperatorTemp.clear();
        //*********************************************************************************
        ((Slider) screen.findNiftyControl("carrierValue_WChC", Slider.class)).setMax(event.getValue());
        ((Slider) screen.findNiftyControl("assemblerValue_WChC", Slider.class)).setMax(event.getValue());
        ((Slider) screen.findNiftyControl("versatileValue_WChC", Slider.class)).setMax(event.getValue());
        //*********************************************************************************        
        for (E_Operator temp : gameEngine.getGameData().getMapUserOperator().values()){
            if (oldOperatorValue == newOperatorValue) break;
            if (oldOperatorValue < newOperatorValue && temp.getState().equals(ObjectState.Inactive)){//add
                oldOperatorValue++;
                newOperatorHire = newOperatorHire + temp.getPriceForHire();
                countOperatorHire++;
                mapUserOperatorTemp.put(temp.getIdOperator(), temp);
            }else
            if (oldOperatorValue > newOperatorValue && temp.getState().equals(ObjectState.Active)){//remove
                oldOperatorValue--;
                newOperatorFire = newOperatorFire + temp.getPriceForFire();
                countOperatorFire++;
                mapUserOperatorTemp.put(temp.getIdOperator(), temp);
            }
        }
        if (newOperatorHire != 0)
            ((Label)screen.findNiftyControl("hireValue_WChC", Label.class)).setText("$ (" + Utils.formatValue2DecToString(newOperatorHire) + ")");
        else
            ((Label)screen.findNiftyControl("hireValue_WChC", Label.class)).setText("$ 0.00");
        if (newOperatorFire != 0)
            ((Label)screen.findNiftyControl("fireValue_WChC", Label.class)).setText("$ (" + Utils.formatValue2DecToString(newOperatorFire) + ")");
        else
            ((Label)screen.findNiftyControl("fireValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("hire_WChC", Label.class)).setText("Hire x " + countOperatorHire + ":");
        ((Label)screen.findNiftyControl("fire_WChC", Label.class)).setText("Fire x " + countOperatorFire + ":");
        updateTotalCosts();
    }
    
    @NiftyEventSubscriber(id="machinesValue_WChC")
    public void onMachineSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("machinesText_WChC", Label.class)).setText(String.valueOf((int)event.getValue()) + "/" + gameEngine.getGameData().getMapUserMachineByOperation().size());
        someChange();
        int newMachineValue = (int)event.getValue();
        int oldMachineValue = gameEngine.getGameData().getUserMachine_Act();
        newMachineBuy = 0.00;
        newMachineSell = 0.00;
        int countMachineBuy = 0;
        int countMachineSell = 0;
        mapUserMachineTemp.clear();
        for (E_Machine temp : gameEngine.getGameData().getMapUserMachineByOperation().values()){
            if (oldMachineValue == newMachineValue) break;
            if (oldMachineValue < newMachineValue && temp.getMachineState().equals(ObjectState.Inactive)){//add
                oldMachineValue++;
                newMachineBuy = newMachineBuy + temp.getPriceForPurchase();
                countMachineBuy++;
                mapUserMachineTemp.put(temp.getIdMachine(), temp);
                isMachineBuying = true;
            }else
            if (oldMachineValue > newMachineValue && temp.getMachineState().equals(ObjectState.Active)){//remove
                oldMachineValue--;
                newMachineSell = newMachineSell + temp.getPriceForSell();
                countMachineSell++;
                mapUserMachineTemp.put(temp.getIdMachine(), temp);
                isMachineBuying = false;
            }
        }
        if (newMachineBuy != 0)
            ((Label)screen.findNiftyControl("buyMachineValue_WChC", Label.class)).setText("$ (" + Utils.formatValue2DecToString(newMachineBuy) + ")");
        else
            ((Label)screen.findNiftyControl("buyMachineValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("sellMachineValue_WChC", Label.class)).setText("$ " + Utils.formatValue2DecToString(newMachineSell));
        ((Label)screen.findNiftyControl("buyMachine_WChC", Label.class)).setText("Buy x " + countMachineBuy + ":");
        ((Label)screen.findNiftyControl("sellMachine_WChC", Label.class)).setText("Sell x " + countMachineSell + ":");
        updateTotalCosts();
    }
    
    @NiftyEventSubscriber(id="equipmentValue_WChC")
    public void onEquipmentSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("equipmentText_WChC", Label.class)).setText(String.valueOf((int)event.getValue()) + "/" + gameEngine.getGameData().getMapUserMachineByTransport().size());
        someChange();
        int newEquipmentValue = (int)event.getValue();
        int oldEquipmentValue = gameEngine.getGameData().getUserEquipment_Act();
        newEquipmentBuy = 0.00;
        newEquipmentSell = 0.00;
        int countEquipmentBuy = 0;
        int countEquipmentSell = 0;
        mapUserEquipmentTemp.clear();
        for (E_Machine temp : gameEngine.getGameData().getMapUserMachineByTransport().values()){
            if (oldEquipmentValue == newEquipmentValue) break;
            if (oldEquipmentValue < newEquipmentValue && temp.getMachineState().equals(ObjectState.Inactive)){//add
                oldEquipmentValue++;
                newEquipmentBuy = newEquipmentBuy + temp.getPriceForPurchase();
                countEquipmentBuy++;
                mapUserEquipmentTemp.put(temp.getIdMachine(), temp);
                isEquipmentBuying = true;
            }else
            if (oldEquipmentValue > newEquipmentValue && temp.getMachineState().equals(ObjectState.Active)){//remove
                oldEquipmentValue--;
                newEquipmentSell = newEquipmentSell + temp.getPriceForSell();
                countEquipmentSell++;
                mapUserEquipmentTemp.put(temp.getIdMachine(), temp);
                isEquipmentBuying = false;
            }
        }
        if (newEquipmentBuy != 0)
            ((Label)screen.findNiftyControl("buyEquipmentValue_WChC", Label.class)).setText("$ (" + Utils.formatValue2DecToString(newEquipmentBuy) + ")");
        else
            ((Label)screen.findNiftyControl("buyEquipmentValue_WChC", Label.class)).setText("$ 0.00");
        ((Label)screen.findNiftyControl("sellEquipmentValue_WChC", Label.class)).setText("$ " + Utils.formatValue2DecToString(newEquipmentSell));
        ((Label)screen.findNiftyControl("buyEquipment_WChC", Label.class)).setText("Buy x " + countEquipmentBuy + ":");
        ((Label)screen.findNiftyControl("sellEquipment_WChC", Label.class)).setText("Sell x " + countEquipmentSell + ":");
        updateTotalCosts();
    }
    
    public void updateData(){
        //EQUIPMENT ************************************************************
        if (!isEquipmentBuying){
            newEquipmentSell = 0.00;
            for (E_Machine tempEquipment : mapUserEquipmentTemp.values()){
                newEquipmentSell += tempEquipment.getPriceForSell();
            }
            ((Label)screen.findNiftyControl("sellEquipmentValue_WChC", Label.class)).setText("$ " + Utils.formatValue2DecToString(newEquipmentSell));
        }
        //MACHINE **************************************************************
        if (!isMachineBuying){
            newMachineSell = 0.00;
            for (E_Machine tempMachine : mapUserMachineTemp.values()){
                newMachineSell += tempMachine.getPriceForSell();
            }
            ((Label)screen.findNiftyControl("sellMachineValue_WChC", Label.class)).setText("$ " + Utils.formatValue2DecToString(newMachineSell));
        }
        updateTotalCosts();
    }
    
    private void updateTotalCosts(){
        newTotalValue = newMachineSell + newEquipmentSell - newOperatorHire - newMachineBuy - newEquipmentBuy - newOperatorFire;
        if (newTotalValue >= 0)
            ((Label)screen.findNiftyControl("totalValue_WChC", Label.class)).setText("$ " + Utils.formatValue2DecToString(newTotalValue));
        else
            ((Label)screen.findNiftyControl("totalValue_WChC", Label.class)).setText("$ (" + Utils.formatValue2DecToString(Math.abs(newTotalValue)) + ")");
    }
    
    private void someChange(){
        anyChange = true;
        ((Label)screen.findNiftyControl("messageResult_WChC", Label.class)).setText("");
    }
    
    private boolean validatedMaxOperators(){
        int maxCarrierSelected = (int)((Slider) screen.findNiftyControl("carrierValue_WChC", Slider.class)).getValue();
        int maxAssemblerSelected = (int)((Slider) screen.findNiftyControl("assemblerValue_WChC", Slider.class)).getValue();
        int maxVersatileSelected = (int)((Slider) screen.findNiftyControl("versatileValue_WChC", Slider.class)).getValue();
        int maxOperators = (int)((Slider) screen.findNiftyControl("operatorsValue_WChC", Slider.class)).getValue();
        if (maxOperators != maxCarrierSelected + maxAssemblerSelected + maxVersatileSelected){
            ((Label)screen.findNiftyControl("messageResult_WChC", Label.class)).setText("Error in selecting operators");
            return false;
        }else
            return true;
    }
    
    private boolean validateAvailableMoney(){
        if (newTotalValue > gameEngine.getGameData().getCurrentMoney())
            return false;
        else{
            gameEngine.getGameData().setTotalOperatorsHire(gameEngine.getGameData().getTotalOperatorsHire() + newOperatorHire);
            gameEngine.getGameData().setTotalOperatorsFire(gameEngine.getGameData().getTotalOperatorsFire() + newOperatorFire);
            gameEngine.getGameData().setTotalMachinesPurchase(gameEngine.getGameData().getTotalMachinesPurchase() + newMachineBuy);
            gameEngine.getGameData().setTotalMachinesSale(gameEngine.getGameData().getTotalMachinesSale() + newMachineSell);
            gameEngine.getGameData().setTotalEquipmentPurchase(gameEngine.getGameData().getTotalEquipmentPurchase() + newEquipmentBuy);
            gameEngine.getGameData().setTotalEquipmentSale(gameEngine.getGameData().getTotalEquipmentSale() + newEquipmentSell);
            return true;
        }        
    }
    
    @NiftyEventSubscriber(id="charactersUpdate")
    public void onUpdateButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        if (!anyChange) return;
        anyChange = false;
        if (!validatedMaxOperators()) return;
        if (!validateAvailableMoney()) return;
        cleanControls();
//        int numOperatorCarrier = Integer.valueOf(((DropDown)screen.findNiftyControl("carrierMaxValue_WChC", DropDown.class)).getSelection().toString());
//        int numOperatorAssembler = Integer.valueOf(((DropDown)screen.findNiftyControl("assemblerMaxValue_WChC", DropDown.class)).getSelection().toString());
//        int numOperatorVersatile = Integer.valueOf(((DropDown)screen.findNiftyControl("versatileMaxValue_WChC", DropDown.class)).getSelection().toString());
        int numOperatorCarrierAct = (int)((Slider) screen.findNiftyControl("carrierValue_WChC", Slider.class)).getValue();
        int numOperatorAssemblerAct = (int)((Slider) screen.findNiftyControl("assemblerValue_WChC", Slider.class)).getValue();
        int numOperatorVersatileAct = (int)((Slider) screen.findNiftyControl("versatileValue_WChC", Slider.class)).getValue();
        int numOperatorAct = (int)((Slider) screen.findNiftyControl("operatorsValue_WChC", Slider.class)).getValue();
        int numMachineAct = (int)((Slider) screen.findNiftyControl("machinesValue_WChC", Slider.class)).getValue();
        int numEquipmentAct = (int)((Slider) screen.findNiftyControl("equipmentValue_WChC", Slider.class)).getValue();
        
//        int oldNumOperatorCarrier = gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Carrier_Inact();
//        int oldNumOperatorAssembler = gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Assembler_Inact();
//        int oldNumOperatorVersatile = gameEngine.getGameData().getUserOperator_Versatile_Act() + gameEngine.getGameData().getUserOperator_Versatile_Inact();
        gameEngine.getGameData().updateUserOperatorCategories();
        gameEngine.getGameData().updateUserMachineEquipment();
        int oldNumOperatorAct = gameEngine.getGameData().getUserOperator_Carrier_Act() + gameEngine.getGameData().getUserOperator_Assembler_Act() + gameEngine.getGameData().getUserOperator_Versatile_Act();
        int oldNumMachineAct = gameEngine.getGameData().getUserMachine_Act();
        int oldNumEquipmentAct = gameEngine.getGameData().getUserEquipment_Act();
        
        //******************************************************
        //******************************************************
        //UPDATE ACTIVE/INACTIVE ALL OPERATORS
        if (numOperatorAct != oldNumOperatorAct){
            for (E_Operator temp : mapUserOperatorTemp.values()){
                if (numOperatorAct > oldNumOperatorAct){//+++
                    temp.setState(ObjectState.Active);
                }else{
                    if (temp.getStatus().equals(Status.Idle))
                        temp.setState(ObjectState.Inactive);
                    else
                        temp.activateLaterDeactivation = true;
                }
            }
        }
        gameEngine.getGameData().updateUserOperatorCategories();
        int oldNumOperatorCarrierAct = gameEngine.getGameData().getUserOperator_Carrier_Act();
        int oldNumOperatorAssemblerAct = gameEngine.getGameData().getUserOperator_Assembler_Act();
        int oldNumOperatorVersatileAct = gameEngine.getGameData().getUserOperator_Versatile_Act();
        //RELEASE OPERATORS NOT REQUIRED (DIFFERENT CATEGORIES)
        if (numOperatorCarrierAct < oldNumOperatorCarrierAct){
            for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorCarrier().values()){
                if (numOperatorCarrierAct == oldNumOperatorCarrierAct) break;
                if (ope.getState().equals(ObjectState.Active) && ope.getStatus().equals(Status.Idle) && !ope.getCategory().equals(OperatorCategory.None)){
                    ope.setCategory(OperatorCategory.None);
                    ope.updateSkillsPerCategory();
                    oldNumOperatorCarrierAct--;
                }                        
            }
            if (numOperatorCarrierAct != oldNumOperatorCarrierAct){
                for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorCarrier().values()){
                    if (numOperatorCarrierAct == oldNumOperatorCarrierAct) break;
                    if (ope.getState().equals(ObjectState.Active) && !ope.getCategory().equals(OperatorCategory.None)){
                        ope.setCategory(OperatorCategory.None);
                        ope.updateSkillsPerCategory();
                        oldNumOperatorCarrierAct--;
                    }                        
                }
            }
        }
        if (numOperatorAssemblerAct < oldNumOperatorAssemblerAct){
            for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorAssembler().values()){
                if (numOperatorAssemblerAct == oldNumOperatorAssemblerAct) break;
                if (ope.getState().equals(ObjectState.Active) && ope.getStatus().equals(Status.Idle) && !ope.getCategory().equals(OperatorCategory.None)){
                    ope.setCategory(OperatorCategory.None);
                    ope.updateSkillsPerCategory();
                    oldNumOperatorAssemblerAct--;
                }                        
            }
            if (numOperatorAssemblerAct != oldNumOperatorAssemblerAct){
                for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorAssembler().values()){
                    if (numOperatorAssemblerAct == oldNumOperatorAssemblerAct) break;
                    if (ope.getState().equals(ObjectState.Active) && !ope.getCategory().equals(OperatorCategory.None)){
                        ope.setCategory(OperatorCategory.None);
                        ope.updateSkillsPerCategory();
                        oldNumOperatorAssemblerAct--;
                    }                        
                }
            }
        }
        if (numOperatorVersatileAct < oldNumOperatorVersatileAct){
            for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorVersatile().values()){
                if (numOperatorVersatileAct == oldNumOperatorVersatileAct) break;
                if (ope.getState().equals(ObjectState.Active) && ope.getStatus().equals(Status.Idle) && !ope.getCategory().equals(OperatorCategory.None)){
                    ope.setCategory(OperatorCategory.None);
                    ope.updateSkillsPerCategory();
                    oldNumOperatorVersatileAct--;
                }                        
            }
            if (numOperatorVersatileAct != oldNumOperatorVersatileAct){
                for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorVersatile().values()){
                    if (numOperatorVersatileAct == oldNumOperatorVersatileAct) break;
                    if (ope.getState().equals(ObjectState.Active) && !ope.getCategory().equals(OperatorCategory.None)){
                        ope.setCategory(OperatorCategory.None);
                        ope.updateSkillsPerCategory();
                        oldNumOperatorVersatileAct--;
                    }                        
                }
            }
        }
        //SELECT OPERATORS REQUIRED BY CATEGORY
        gameEngine.getGameData().updateUserOperatorCategories();
        oldNumOperatorCarrierAct = gameEngine.getGameData().getUserOperator_Carrier_Act();
        oldNumOperatorAssemblerAct = gameEngine.getGameData().getUserOperator_Assembler_Act();
        oldNumOperatorVersatileAct = gameEngine.getGameData().getUserOperator_Versatile_Act();
        //some validation, just in case:
        int newOpesToAssign = (numOperatorCarrierAct > oldNumOperatorCarrierAct ? numOperatorCarrierAct - oldNumOperatorCarrierAct : 0) 
                + (numOperatorAssemblerAct > oldNumOperatorAssemblerAct ? numOperatorAssemblerAct - oldNumOperatorAssemblerAct : 0) 
                + (numOperatorVersatileAct > oldNumOperatorVersatileAct ? numOperatorVersatileAct - oldNumOperatorVersatileAct : 0);
        if (newOpesToAssign != gameEngine.getGameData().getMapUserOperatorNone().size())
            System.out.println("ERROR IN RESOURCES MANAGEMENT!!!");
        else
            System.out.println("WORKS GOOD THE RESOURCES MANAGEMENT!!!");
        if (numOperatorCarrierAct > oldNumOperatorCarrierAct){
            for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorNone().values()){
                if (numOperatorCarrierAct == oldNumOperatorCarrierAct) break;
                if (ope.getCategory().equals(OperatorCategory.None)){
                    ope.setCategory(OperatorCategory.Carrier);
                    oldNumOperatorCarrierAct++;
                    ope.updateSkillsPerCategory();
                }
            }
        }
        if (numOperatorAssemblerAct > oldNumOperatorAssemblerAct){
            for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorNone().values()){
                if (numOperatorAssemblerAct == oldNumOperatorAssemblerAct) break;
                if (ope.getCategory().equals(OperatorCategory.None)){
                    ope.setCategory(OperatorCategory.Assembler);
                    oldNumOperatorAssemblerAct++;
                    ope.updateSkillsPerCategory();
                }
            }
        }
        if (numOperatorVersatileAct > oldNumOperatorVersatileAct){
            for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorNone().values()){
                if (numOperatorVersatileAct == oldNumOperatorVersatileAct) break;
                if (ope.getCategory().equals(OperatorCategory.None)){
                    ope.setCategory(OperatorCategory.Versatile);
                    oldNumOperatorVersatileAct++;
                    ope.updateSkillsPerCategory();
                }
            }
        }
        gameEngine.getGameData().updateUserOperatorCategories();
        //******************************************************
        if (numMachineAct != oldNumMachineAct){
            for (E_Machine temp : mapUserMachineTemp.values()){
                if (numMachineAct > oldNumMachineAct){//+++
                    temp.setMachineState(ObjectState.Active);
                }else{
                    if (temp.getStatus().equals(Status.Idle))
                        temp.setMachineState(ObjectState.Inactive);
                    else
                        temp.activateLaterDeactivation = true;
                }
            }
        }
        mapUserMachineTemp.clear();
        isMachineBuying = true;
        if (numEquipmentAct != oldNumEquipmentAct){
            for (E_Machine temp : mapUserEquipmentTemp.values()){
                if (numEquipmentAct > oldNumEquipmentAct){//+++
                    temp.setMachineState(ObjectState.Active);
                }else{//---
                    if (temp.getStatus().equals(Status.Idle))
                        temp.setMachineState(ObjectState.Inactive);
                    else
                        temp.activateLaterDeactivation = true;
                }
            }
        }
        mapUserEquipmentTemp.clear();
        isEquipmentBuying = true;
        for (EventStrategy temp : gameEngine.getManageEvents().getArrEvents()){
            if (temp.getType().equals(TypeActivity.Operation.toString()) || 
                    temp.getType().equals(TypeActivity.Transport.toString()) || 
                    temp.getType().equals(TypeActivity.Store.toString()))
                temp.updateStrategy();
        }
        ((Label)screen.findNiftyControl("messageResult_WChC", Label.class)).setText("Updated successfully");
        gameEngine.getGameData().updateUserOperatorCategories();
        gameEngine.getGameData().updateUserMachineEquipment();
        nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupControl.class).setupResourcesDone();
    }
}
