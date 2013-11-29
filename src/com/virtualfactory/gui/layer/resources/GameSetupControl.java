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
import com.virtualfactory.entity.E_Activity;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.entity.E_Purchase;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.entity.E_TransportStore;
import com.virtualfactory.gui.CommonBuilders;
import com.virtualfactory.utils.MessageType;
import com.virtualfactory.utils.Messages;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Status;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author David
 */
public class GameSetupControl implements Controller {
    private static Nifty nifty;
    private static Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private static GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
    private String currentWindowVisible = "";
    private String resources = "Resources";
    private String storage = "Storage";
    private String unitLoad = "UnitLoad";
    private String purchase = "Purchase";
    private String operators = "Operators";
    private String priority = "Priority";
    private NiftyImage imageSetupDone;
    private NiftyImage imageSetupWait;
    private boolean isReadyToStart = false;
    private String buttonNoReady = "Textures/rojo.png";
    private String buttonReady = "Textures/verde.png";
    private boolean isResourcesReady = false;
    private boolean isStorageReady = false;
    private boolean isUnitLoadReady = false;
    private boolean isPurchaseReady = false;
    private boolean isOperatorsReady = false;
    private boolean isPriorityReady = false;

    public String getCurrentWindowVisible() {
        return currentWindowVisible;
    }

    public void setCurrentWindowVisible(String currentWindowVisible) {
        this.currentWindowVisible = currentWindowVisible;
    }

    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winGameSetupControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
        ((TextField)screen.findNiftyControl("setupSteps", TextField.class)).setEnabled(false);
        ((TextField)screen.findNiftyControl("setupStatus", TextField.class)).setEnabled(false);
        
        imageSetupDone = nifty.createImage("Interface/Principal/done.png", false);
        imageSetupWait = nifty.createImage("Interface/Principal/wait_red.png", false);
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
    
    private void loadValues(int index){
     
    }

    public boolean isIsReadyToStart() {
        return isReadyToStart;
    }
    
    public void clickSetupResources(){
        manageWindows(resources);
        gameEngine.getGeneralScreenController().optionButtonClicked("buttonOptionControls");
        gameEngine.getGeneralScreenController().onDynamicButtonClicked("dynBut3");
    }
    
    public void setupResourcesDone(){
        if (currentWindowVisible.equals(resources)){
            screen.findElementByName("imageSetupResourcesStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            isResourcesReady = true;
            validateReadyToStart();
        }            
    }
    
    public void clickSetupStorage(){
        manageWindows(storage);
        gameEngine.getGeneralScreenController().optionButtonClicked("buttonOptionControls");
        gameEngine.getGeneralScreenController().onDynamicButtonClicked("dynBut0");
    }
    
    public void setupStorageDone(){
        if (currentWindowVisible.equals(storage)){
            screen.findElementByName("imageSetupStorageStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            isStorageReady = true;
            validateReadyToStart();
        }
    }
    
    public void clickSetupUnitLoad(){
        manageWindows(unitLoad);
        gameEngine.getGeneralScreenController().optionButtonClicked("buttonOptionControls");
        gameEngine.getGeneralScreenController().onDynamicButtonClicked("dynBut4");
    }
    
    public void setupUnitLoadDone(){
        if (currentWindowVisible.equals(unitLoad)){
            screen.findElementByName("imageSetupUnitLoadStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            isUnitLoadReady = true;
            validateReadyToStart();
        }
    }
    
    public void clickSetupPurchase(){
        manageWindows(purchase);
        gameEngine.getGeneralScreenController().optionButtonClicked("buttonOptionActivities");
        gameEngine.getGeneralScreenController().onDynamicButtonClicked("dynBut1");
    }
    
    public void setupPurchaseDone(){
        if (currentWindowVisible.equals(purchase)){
            screen.findElementByName("imageSetupPurchaseStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            isPurchaseReady = true;
            validateReadyToStart();
        }
    }
    
    public void clickSetupOperators(){
        manageWindows(operators);
        gameEngine.getGeneralScreenController().optionButtonClicked("buttonOptionControls");
        gameEngine.getGeneralScreenController().onDynamicButtonClicked("dynBut1");
    }
    
    public void setupOperatorsDone(){
        if (currentWindowVisible.equals(operators)){
            screen.findElementByName("imageSetupOperatorsStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            isOperatorsReady = true;
            validateReadyToStart();
        }            
    }
    
    public void clickSetupPriority(){
        manageWindows(priority);
        gameEngine.getGeneralScreenController().optionButtonClicked("buttonOptionControls");
        gameEngine.getGeneralScreenController().onDynamicButtonClicked("dynBut2");
    }
    
    public void setupPriorityDone(){
        if (currentWindowVisible.equals(priority)){
            screen.findElementByName("imageSetupPriorityStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            isPriorityReady = true;
            validateReadyToStart();
        }            
    }
    
    private void manageWindows(String newWindows){
        if (!currentWindowVisible.equals(newWindows)){
            currentWindowVisible = newWindows;
            if (!gameEngine.getGeneralScreenController().getCurrentOptionselected().isEmpty())
                gameEngine.getGeneralScreenController().optionButtonClicked(gameEngine.getGeneralScreenController().getCurrentOptionselected());
        }else{
            if (gameEngine.getGeneralScreenController().getCurrentOptionselected().isEmpty())
                gameEngine.getGeneralScreenController().setButtonSelectedSecondLevel("");
        }
    }
    
    public void updateAllStepStatus(boolean isReady){
        if (!isReady){
            screen.findElementByName("imageSetupResourcesStatus").getRenderer(ImageRenderer.class).setImage(imageSetupWait);
            screen.findElementByName("imageSetupStorageStatus").getRenderer(ImageRenderer.class).setImage(imageSetupWait);
            screen.findElementByName("imageSetupUnitLoadStatus").getRenderer(ImageRenderer.class).setImage(imageSetupWait);
            screen.findElementByName("imageSetupPurchaseStatus").getRenderer(ImageRenderer.class).setImage(imageSetupWait);
            screen.findElementByName("imageSetupOperatorsStatus").getRenderer(ImageRenderer.class).setImage(imageSetupWait);
            screen.findElementByName("imageSetupPriorityStatus").getRenderer(ImageRenderer.class).setImage(imageSetupWait);
            screen.findElementByName("setupStartGame").getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonNoReady, false));
            isReadyToStart = false;
            isResourcesReady = isStorageReady = isUnitLoadReady = isPurchaseReady = isOperatorsReady = isPriorityReady = false;
        }else{
            screen.findElementByName("imageSetupResourcesStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            screen.findElementByName("imageSetupStorageStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            screen.findElementByName("imageSetupUnitLoadStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            screen.findElementByName("imageSetupPurchaseStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            screen.findElementByName("imageSetupOperatorsStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            screen.findElementByName("imageSetupPriorityStatus").getRenderer(ImageRenderer.class).setImage(imageSetupDone);
            screen.findElementByName("setupStartGame").getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonReady, false));
            isReadyToStart = true;
            isResourcesReady = isStorageReady = isUnitLoadReady = isPurchaseReady = isOperatorsReady = isPriorityReady = true;
        }
    }
    
    private void validateReadyToStart(){
        if (isResourcesReady && isStorageReady && isUnitLoadReady && isPurchaseReady && isOperatorsReady && isPriorityReady){
            screen.findElementByName("setupStartGame").getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonReady, false));
            isReadyToStart = true;
        }else{
            screen.findElementByName("setupStartGame").getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonNoReady, false));
            isReadyToStart = false;
        }
    }
    
    @NiftyEventSubscriber(id="setupDefaultGame")
    public void onDefaultButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        //Operators
        double newOperatorsHire = 0.0;
        double newOperatorsFire = 0.0;
        for (E_Operator temp : gameEngine.getGameData().getMapUserOperator().values()){
            if (temp.getState().equals(ObjectState.Inactive) && temp.getDefaultValue() == 1){
                temp.setState(ObjectState.Active);
                newOperatorsHire += temp.getPriceForHire();
            }else
            if (temp.getState().equals(ObjectState.Active) && temp.getDefaultValue() == -1){
                if (temp.getStatus().equals(Status.Idle))
                    temp.setState(ObjectState.Inactive);
                else
                    temp.activateLaterDeactivation = true;
                newOperatorsFire += temp.getPriceForFire();
            }
        }
        gameEngine.getGameData().setTotalOperatorsHire(gameEngine.getGameData().getTotalOperatorsHire() + newOperatorsHire);
        gameEngine.getGameData().setTotalOperatorsFire(gameEngine.getGameData().getTotalOperatorsFire() + newOperatorsFire);
        //Machines/Equipment
        double newMachinesBuy = 0.0;
        double newMachinesSell = 0.0;
        for (E_Machine temp : gameEngine.getGameData().getMapUserMachineByOperation().values()){
            if (temp.getMachineState().equals(ObjectState.Inactive) && temp.getDefaultValue() == 1){
                temp.setMachineState(ObjectState.Active);
                newMachinesBuy += temp.getPriceForPurchase();
            }else
            if (temp.getMachineState().equals(ObjectState.Active) && temp.getDefaultValue() == -1){
                if (temp.getStatus().equals(Status.Idle))
                    temp.setMachineState(ObjectState.Inactive);
                else
                    temp.activateLaterDeactivation = true;
                newMachinesSell += temp.getPriceForSell();
            }
        }
        gameEngine.getGameData().setTotalMachinesPurchase(gameEngine.getGameData().getTotalMachinesPurchase() + newMachinesBuy);
        gameEngine.getGameData().setTotalMachinesSale(gameEngine.getGameData().getTotalMachinesSale() + newMachinesSell);
        double newEquipmentBuy = 0.0;
        double newEquipmentSell = 0.0;
        for (E_Machine temp : gameEngine.getGameData().getMapUserMachineByTransport().values()){
            if (temp.getMachineState().equals(ObjectState.Inactive) && temp.getDefaultValue() == 1){
                temp.setMachineState(ObjectState.Active);
                newEquipmentBuy += temp.getPriceForPurchase();
            }else
            if (temp.getMachineState().equals(ObjectState.Active) && temp.getDefaultValue() == -1){
                if (temp.getStatus().equals(Status.Idle))
                    temp.setMachineState(ObjectState.Inactive);
                else
                    temp.activateLaterDeactivation = true;
                newEquipmentSell += temp.getPriceForSell();
            }
        }
        gameEngine.getGameData().setTotalEquipmentPurchase(gameEngine.getGameData().getTotalEquipmentPurchase() + newEquipmentBuy);
        gameEngine.getGameData().setTotalEquipmentSale(gameEngine.getGameData().getTotalEquipmentSale() + newEquipmentSell);
        //Storage Stations Slots
        for (E_Station tempStation : gameEngine.getGameData().getMapUserStorageStation().values()){
            tempStation.setPercentageSelectedSlots(tempStation.getDefaultValue());
            tempStation.setTempSelectedSlots((int)(tempStation.getTotalSlots() * tempStation.getPercentageSelectedSlots()));
            tempStation.updateSlots();
            tempStation.setTempSelectedSlots(tempStation.getSelectedSlots());
        }
        //Transport Activity
        for (E_TransportStore transport : gameEngine.getGameData().getMapTransport().values()){
            transport.setUnitLoad(transport.getDefaultValue());
        }
        //Reorder Point/Quantity - Purchase
        for (E_Purchase purchase : gameEngine.getGameData().getMapPurchase().values()){
            purchase.setOrderPoint(purchase.getDefaultValueOP());
            purchase.setOrderQuantity(purchase.getDefaultValueOQ());
        }
        //Assign Operators
        //it is not defined by default
        //Priority number
        ArrayList<E_Activity> arrActivity = gameEngine.getGameData().getActivities_OrderByPriority();
        for (E_Activity activity : arrActivity){
            activity.setPriorityQueue(activity.getDefaultValuePriority());
        }
        updateAllStepStatus(true);
        gameEngine.getGeneralScreenController().hideCurrentControlsWindow();
        gameEngine.getGeneralScreenController().showHideDynamicButtons(0);
        gameEngine.getGeneralScreenController().showHideDynamicSubLevelButtons(0);
    }
    
    @NiftyEventSubscriber(id="setupStartGame")
    public void onStartButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        if (isReadyToStart){
            gameEngine.getGeneralScreenController().playGame();
            loadWindowControl(gameEngine, -1, null);
        }else{
            GameLogControl.addMessage(MessageType.Notification, Messages.gameSetup);
        }
    }
}
