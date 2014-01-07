package com.virtualfactory.screen.layer;

import com.virtualfactory.screen.menu.MenuScreenController;
import com.virtualfactory.utils.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBox;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.KeyInputHandler;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.checkbox.builder.CheckboxBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.engine.GameEngine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.screen.layer.components.*;
import com.virtualfactory.screen.menu.components.MainMenuController;
import com.virtualfactory.utils.MessageType;
import com.virtualfactory.utils.Messages;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Sounds;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeActivity;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David Bengoa
 */
public class LayerScreenController implements ScreenController, KeyInputHandler {
    private Nifty nifty;
    private Screen screen;
    private Label labelCurrentGameTime;
    private Label labelDueDateNextOrder;
    private Label labelPurchaseDueDate;
    private float currentTimeFactor;
    private Label labelCurrentTimeFactor;
//    private Label labelPeopleBusy;
//    private Label labelPeopleIdle;
//    private Label labelCurrentMoney;
    private GameEngine gameEngine;
    private Slider sliderTimeFactor;
    private boolean isGamePlaying;
    private String currentOptionselected = "";
    private String currentDynamicButtonSelected = "";
    private String currentDynamicSubLevelButtonSelected = "";
    private boolean isVisibleWindowOverall = true;
    private boolean isVisibleWindowOrder = true;
    private boolean isVisibleWindowGameLog = true;
    private boolean isVisibleWindowGameSetup = true;
    private boolean isVisibleWindowFlowChart = true;
    private Map<String, Integer> arrActivitiesId;
    private NiftyImage imagePlayRed;
    private NiftyImage imagePlayGreen;
    private NiftyImage imagePauseRed;
    private NiftyImage imagePauseGreen;
    private String buttonSelectedFirstLevel = "";
    private String buttonSelectedSecondLevel = "";
    private String buttonSelectedThirdLevel = "";
    private String buttonActive = "Textures/azul.png";
    private String buttonInactive = "Textures/anaranjado.png";
    private Element quitPopup;
    private boolean callOnceMoneyWarning = true;
    private boolean callOnceNoMoney = true;
    private boolean isVolumeManage = false;
    private float oldVolumeMusic = 100.f;
    private float oldVolumeSFX = 100.f;
    private boolean isEnableVolumeMusic = true;
    private boolean isEnableVolumeSFX = true;
    final CommonBuilders common = new CommonBuilders();

    public String getButtonSelectedSecondLevel() {
        return buttonSelectedSecondLevel;
    }

    public void setButtonSelectedSecondLevel(String buttonSelectedSecondLevel) {
        this.buttonSelectedSecondLevel = buttonSelectedSecondLevel;
    }

    public String getCurrentOptionselected() {
        return currentOptionselected;
    }

    public void setCurrentOptionselected(String currentOptionselected) {
        this.currentOptionselected = currentOptionselected;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    
    public LayerScreenController(){
        arrActivitiesId = new HashMap<String, Integer>();
    }
    
    @Override
    public void bind(final Nifty nifty, final Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
        this.labelCurrentGameTime = screen.findNiftyControl("labelCurrentGameTime", Label.class);
        this.labelDueDateNextOrder = screen.findNiftyControl("labelDueDateNextOrder", Label.class);
        this.labelPurchaseDueDate = screen.findNiftyControl("labelPurchaseDueDate", Label.class);
        this.labelCurrentTimeFactor = screen.findNiftyControl("labelSliderTime", Label.class);
//        this.labelPeopleBusy = screen.findNiftyControl("peopleBusy", Label.class);
//        this.labelPeopleIdle = screen.findNiftyControl("peopleIdle", Label.class);
//        this.labelCurrentMoney = screen.findNiftyControl("currentMoney", Label.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.sliderTimeFactor = screen.findNiftyControl("sliderTime", Slider.class);
        this.isGamePlaying = true;
        imagePlayGreen = nifty.createImage("Interface/button_play_green.png", false);
        imagePlayRed = nifty.createImage("Interface/button_play_red.png", false);
        imagePauseGreen = nifty.createImage("Interface/button_pause_green.png", false);
        imagePauseRed = nifty.createImage("Interface/button_pause_red.png", false);
        quitPopup = nifty.createPopup("quitPopup");
    }
    
    @NiftyEventSubscriber(id="gameClosingContinue")
    public void onGameClosingContinueButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.stopClosingGame();
    }
    
    @NiftyEventSubscriber(id="gameClosingExit")
    public void onGameClosingExitButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.showPopup(screen, quitPopup.getId(), null);
    }
    
    @NiftyEventSubscriber(pattern="quitPopup.*")
    public void onGameClosingQuitPopupButtonClicked(final String id, final ButtonClickedEvent event) {
        if (id.equals("quitPopupYes")){
            gameEngine.getGameData().logoutPlayer();
            gameEngine.jmonkeyApp.stop();
            System.exit(0);
        }else{
            nifty.closePopup(quitPopup.getId());
        }
    }
    
    @SuppressWarnings("unchecked")
    private ListBox<ListBoxMessages> getListBox(final String name) {
        return (ListBox<ListBoxMessages>) screen.findNiftyControl(name, ListBox.class);
    }
    
    @Override
    public void onStartScreen() {
//        ((Slider) screen.findNiftyControl("sliderTime", Slider.class)).setup(1.f, 8.f, 4.f, 1.f, 1.f);
//        showHideDynamicButtons(0);
//        showHideDynamicSubLevelButtons(0);
    }
    
    @Override
    public void onEndScreen() {
    }
    
    @Override
    public boolean keyEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    @NiftyEventSubscriber(id="sliderTime")
    public void onAlphaSliderChange(final String id, final SliderChangedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        String strTimeFactor = "";
        //The TIME_FACTOR has been changed only this part, in order to not change others things in the code.
        //Becareful in case somebody change it
        int selectedValue = 0;
        switch ((int)event.getValue()){
            case 1: strTimeFactor = "1/8x"; selectedValue = 8; break;
            case 2: strTimeFactor = "1/4x"; selectedValue = 7; break;
            case 3: strTimeFactor = "1/2x"; selectedValue = 6; break;
            case 4: strTimeFactor = "1x"; selectedValue = 5; break;
            case 5: strTimeFactor = "2x"; selectedValue = 4; break;
            case 6: strTimeFactor = "4x"; selectedValue = 3; break;
            case 7: strTimeFactor = "8x"; selectedValue = 2; break;
            case 8: strTimeFactor = "16x"; selectedValue = 1; break;
        }
        currentTimeFactor = 0.0625f;
        for(int i=1; i<selectedValue; i++){
            currentTimeFactor = currentTimeFactor * 2;
        }
        labelCurrentTimeFactor.setText(strTimeFactor);
        GameLogScreenController.addMessage(MessageType.Info, Messages.timeFactor.replace(Messages.wildCard, strTimeFactor));
        gameEngine.getGameData().updateSpeedElements();
        gameEngine.updateEventsTime();
    }
    
    public float getTimeFactor(){
        return this.currentTimeFactor;
    }
    
    public float getTimeFactorForSpeed(){
        return 1/getTimeFactor();
    }
    
    public void setTimeFactor(float timeFactor){
        float newTimeFactor = 1;
        switch ((int)(timeFactor/0.0625)){
            case 1:     newTimeFactor = 8; break;
            case 2:     newTimeFactor = 7; break;
            case 4:     newTimeFactor = 6; break;
            case 8:     newTimeFactor = 5; break;
            case 16:    newTimeFactor = 4; break;
            case 32:    newTimeFactor = 3; break;
            case 64:    newTimeFactor = 2; break;
            case 128:   newTimeFactor = 1; break;
        }
        sliderTimeFactor.setValue(newTimeFactor);
    }

    public void updateStartScreen(){
        ((Slider) screen.findNiftyControl("sliderTime", Slider.class)).setup(1.f, 8.f, 4.f, 1.f, 1.f);
        showHideDynamicButtons(0);
        showHideDynamicSubLevelButtons(0);
        currentOptionselected = "";
        currentDynamicButtonSelected = "";
        currentDynamicSubLevelButtonSelected = "";
    }
    
    public void setCurrentGameTime(String time){
        labelCurrentGameTime.setText(time);
    }
    
    public void setNextDueDate(String time){
        labelDueDateNextOrder.setText("NextOrder Due: " + time);
    }
    
    public void setNextPurchaseDueDate(String time){
        labelPurchaseDueDate.setText("NextPurchase: " + time);
    }
    
//    public void updateQuantityPeopleStatus(int noPeopleBusy, int noPeopleIdle){
//        this.labelPeopleBusy.setText(noPeopleBusy + " Busy");
//        this.labelPeopleIdle.setText(noPeopleIdle + " Idle");
//    }
    
    public void updateQuantityCurrentMoney(double currentMoney){
//        this.labelCurrentMoney.setText(Params.moneySign + " " + Utils.formatValue2DecToString(currentMoney));
        if (currentMoney < 1000 && currentMoney > 980){
            GameLogScreenController.addMessage(MessageType.Notification, Messages.moneyBetween1000and980);
            if (callOnceMoneyWarning){
                gameEngine.getGameSounds().playSound(Sounds.GameWarningMoney);
                callOnceMoneyWarning = false;
            }
        }
        if (currentMoney < 100){
            GameLogScreenController.addMessage(MessageType.Error, Messages.moneyLessThan100);
            if (callOnceNoMoney){
                gameEngine.getGameSounds().playSound(Sounds.GameNoMoney);
                callOnceNoMoney = false;
            }
        }
        if (currentMoney > 1000) callOnceMoneyWarning = true;
        if (currentMoney > 100) callOnceNoMoney = true;
    }
    
    public void setGameNamePrincipal(String newGameName){
        ((Label)screen.findNiftyControl("gameNamePrincipal", Label.class)).setText("  Game: " + newGameName);
    }
    
    public void setGamePrincipalStatus(String principalStatus){
        ((Label)screen.findNiftyControl("gamePrincipalStatus", Label.class)).setText(principalStatus);
    }
    
    public void playGameValidated(){
        gameEngine.updateLastActivitySystemTime();
        if (nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupScreenController.class).isIsReadyToStart()){
            playGame();
        }else{
            GameLogScreenController.addMessage(MessageType.Notification, Messages.gameSetup);
        }
//        nifty.gotoScreen("initialMenu");
    }
    
    public void playGame(){
        gameEngine.updateLastActivitySystemTime();
        if (!isGamePlaying){
            screen.findElementByName("imagePlay").getRenderer(ImageRenderer.class).setImage(imagePauseRed);
//            screen.findElementByName("imagePause").getRenderer(ImageRenderer.class).setImage(imagePauseRed);
            isGamePlaying = true;
            playPauseGame();
            GameLogScreenController.addMessage(MessageType.Info, Messages.gamePlay);
            setGamePrincipalStatus(" (Playing)");
            gameEngine.getGameSounds().playSound(Sounds.PlayPause);
            gameEngine.getGameSounds().playSound(Sounds.Background);
            if (Params.isTutorialLevel)
                gameEngine.getGameSounds().stopSound(Sounds.TutorialLevel);

            
            gameEngine.updateGameSounds(true);
            sliderTimeFactor.enable();
        }else{
            forcePauseGame();
        }
    }
    
    public void pauseGame(){
        gameEngine.updateLastActivitySystemTime();
        if (isGamePlaying){
            forcePauseGame();
        }
    }
    
    public void forcePauseGame(){
        isGamePlaying = false;
        playPauseGame();
        GameLogScreenController.addMessage(MessageType.Info, Messages.gamePause);
        setGamePrincipalStatus(" (Paused)");
        screen.findElementByName("imagePlay").getRenderer(ImageRenderer.class).setImage(imagePlayGreen);
//        screen.findElementByName("imagePlay").getRenderer(ImageRenderer.class).setImage(imagePlayRed);
//        screen.findElementByName("imagePause").getRenderer(ImageRenderer.class).setImage(imagePauseGreen);
        gameEngine.getGameSounds().playSound(Sounds.PlayPause);
        gameEngine.getGameSounds().pauseSound(Sounds.Background);
        gameEngine.updateGameSounds(false);
        sliderTimeFactor.disable();
        for (E_Operator op: this.gameEngine.getGameData().getMapUserOperator().values()){
            op.playStopAnimation(false);
            if (op.getMotionControl()!=null)
                op.getMotionControl().pause();
        }
        
        if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 21)
                    Params.tutorial.nextStep();
    }
    
    public void manageGameVolume(){
        if (isVolumeManage){    //hide
            isEnableVolumeMusic = screen.findElementByName("controlVolumeMusic_MGV").isEnabled();
            isEnableVolumeSFX = screen.findElementByName("controlVolumeSFX_MGV").isEnabled();
            oldVolumeMusic = ((Slider) screen.findNiftyControl("controlVolumeMusic_MGV", Slider.class)).getValue();
            oldVolumeSFX = ((Slider) screen.findNiftyControl("controlVolumeSFX_MGV", Slider.class)).getValue();
            if (screen.findElementByName("container_MGV") != null)   screen.findElementByName("container_MGV").markForRemoval();
            nifty.executeEndOfFrameElementActions();
        }else{//show
            new PanelBuilder("container_MGV"){{
                backgroundImage("Interface/panel2.png");
                childLayoutVertical();
                panel(new PanelBuilder(){{
                    childLayoutHorizontal();
                    panel(common.hspacer("5px"));
                    control(new LabelBuilder("labelMusic_MGV", "Music :"){{ textHAlignLeft(); }});
                    panel(common.hspacer("5px"));
                    control(new LabelBuilder("labelMusicValue_MGV", "0%"){{ textHAlignLeft(); width("60px"); }});
                }});
                panel(new PanelBuilder(){{
                    childLayoutHorizontal();
                    panel(common.hspacer("5px"));
                    control(new CheckboxBuilder("enableMusic_MGV"));
                    panel(common.hspacer("5px"));
                    control(new SliderBuilder("controlVolumeMusic_MGV", false){{ width("110px"); }});
                }});
                panel(common.vspacer("10px"));
                panel(new PanelBuilder(){{
                    childLayoutHorizontal();
                    panel(common.hspacer("5px"));
                    control(new LabelBuilder("labelSFX_MGV", "SFX :"){{ textHAlignLeft(); }});
                    panel(common.hspacer("5px"));
                    control(new LabelBuilder("labelSFXValue_MGV", "0%"){{ textHAlignLeft(); width("60px"); }});
                }});
                panel(new PanelBuilder(){{
                    childLayoutHorizontal();
                    panel(common.hspacer("5px"));
                    control(new CheckboxBuilder("enableSFX_MGV"));
                    panel(common.hspacer("5px"));
                    control(new SliderBuilder("controlVolumeSFX_MGV", false){{ width("110px"); }});
                }});
                width("150");
                height("110");
            }}.build(nifty, screen, screen.findElementByName("parent_MGV"));
            nifty.executeEndOfFrameElementActions();
            ((Slider) screen.findNiftyControl("controlVolumeMusic_MGV", Slider.class)).setup(0.f, 100.f, oldVolumeMusic, 1.f, 1.f);
            ((Slider) screen.findNiftyControl("controlVolumeSFX_MGV", Slider.class)).setup(0.f, 100.f, oldVolumeSFX, 1.f, 1.f);
            if (isEnableVolumeMusic){
                ((CheckBox) screen.findNiftyControl("enableMusic_MGV", CheckBox.class)).check();
                screen.findElementByName("controlVolumeMusic_MGV").enable();
            }else{
                ((CheckBox) screen.findNiftyControl("enableMusic_MGV", CheckBox.class)).uncheck();
                screen.findElementByName("controlVolumeMusic_MGV").disable();
            }
            if (isEnableVolumeSFX){
                ((CheckBox) screen.findNiftyControl("enableSFX_MGV", CheckBox.class)).check();
                screen.findElementByName("controlVolumeSFX_MGV").enable();
            }else{
                ((CheckBox) screen.findNiftyControl("enableSFX_MGV", CheckBox.class)).uncheck();
                screen.findElementByName("controlVolumeSFX_MGV").disable();
            }
        }
        isVolumeManage = !isVolumeManage;
    }
    
    @NiftyEventSubscriber(pattern="enable.*")
    public void onEnableControlsClicked(final String id, final CheckBoxStateChangedEvent event) {
        if (id.equals("enableMusic_MGV")){
            if (event.isChecked()){
                screen.findElementByName("controlVolumeMusic_MGV").enable();
                gameEngine.getGameSounds().setVolumeMusic(((Slider) screen.findNiftyControl("controlVolumeMusic_MGV", Slider.class)).getValue()/100.f);
            }else{
                screen.findElementByName("controlVolumeMusic_MGV").disable();
                gameEngine.getGameSounds().setVolumeMusic(0);
            }
        }else
        if (id.equals("enableSFX_MGV")){
            if (event.isChecked()){
                screen.findElementByName("controlVolumeSFX_MGV").enable();
                gameEngine.getGameSounds().setVolumeSFX(((Slider) screen.findNiftyControl("controlVolumeSFX_MGV", Slider.class)).getValue()/100.f,gameEngine);
            }else{
                screen.findElementByName("controlVolumeSFX_MGV").disable();
                gameEngine.getGameSounds().setVolumeSFX(0,gameEngine);
            }
        }
    }
    
    @NiftyEventSubscriber(pattern="controlVolume.*")
    public void onControlVolumeSliderChange(final String id, final SliderChangedEvent event) {
        if (id.equals("controlVolumeMusic_MGV")){
            ((Label)screen.findNiftyControl("labelMusicValue_MGV", Label.class)).setText((int)event.getValue() + "%");
            if(((CheckBox) screen.findNiftyControl("enableMusic_MGV", CheckBox.class)).isChecked())
                gameEngine.getGameSounds().setVolumeMusic(((Slider) screen.findNiftyControl("controlVolumeMusic_MGV", Slider.class)).getValue()/100.f);
        }else
        if (id.equals("controlVolumeSFX_MGV")){
            ((Label)screen.findNiftyControl("labelSFXValue_MGV", Label.class)).setText((int)event.getValue() + "%");
            if(((CheckBox) screen.findNiftyControl("enableSFX_MGV", CheckBox.class)).isChecked())
                gameEngine.getGameSounds().setVolumeSFX(((Slider) screen.findNiftyControl("controlVolumeSFX_MGV", Slider.class)).getValue()/100.f,gameEngine);
        }
    }
    
    private void playPauseGame(){
        if (isGamePlaying){//PLAY
            gameEngine.setCurrentSystemStatus(Status.Busy);
            gameEngine.getGameData().playPauseElements(Status.Busy);
            gameEngine.updateAnimations();
        }else{//PAUSE
            gameEngine.setCurrentSystemStatus(Status.Idle);
            gameEngine.updateGameDataAndLogic();
            gameEngine.getGameData().playPauseElements(Status.Idle);
        }
    }
    
    public void showHideDynamicButtons(int numberButtonsVisible){
        showHideDynamicButtons(numberButtonsVisible, "98%");
    }
    
    private void showHideDynamicButtons(int numberButtonsVisible, String sizeWidth){
        Element dynamicPanel = nifty.getScreen("layerScreen").findElementByName("dynamicButtons");
        int i=0;
        for (Element element : dynamicPanel.getElements()){
            if (element.getId() != null){
                i++;
                element.setVisible(i <= numberButtonsVisible ? true : false);
                ((Button)screen.findNiftyControl(element.getId(), Button.class)).setWidth(new SizeValue(sizeWidth));
            }            
        }
        screen.findElementByName("dynamicButtons").layoutElements();
    }
    
    public void showHideDynamicSubLevelButtons(int numberButtonsVisible){
        showHideDynamicSubLevelButtons(numberButtonsVisible, "98%");
    }
    
    private void showHideDynamicSubLevelButtons(int numberButtonsVisible, String sizeWidth){
        Element dynamicPanel = nifty.getScreen("layerScreen").findElementByName("dynamicSubLevelButtons");
        int i=0;
        for (Element element : dynamicPanel.getElements()){
            if (element.getId() != null){
                i++;
                element.setVisible(i <= numberButtonsVisible ? true : false);
                ((Button)screen.findNiftyControl(element.getId(), Button.class)).setWidth(new SizeValue(sizeWidth));
            }            
        }
        screen.findElementByName("dynamicSubLevelButtons").layoutElements();
    }
    
    private void changeBackgroundButtonActiveInactiveFirst(String nameButton){
        if (nameButton.equals(""))
            return;
        if (buttonSelectedFirstLevel.equals("")){
            screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonActive, false));
            buttonSelectedFirstLevel = nameButton;
        }else{
            if (buttonSelectedFirstLevel.equals(nameButton)){
                screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonInactive, false));
                buttonSelectedFirstLevel = "";
            }else{
                screen.findElementByName(buttonSelectedFirstLevel).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonInactive, false));
                screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonActive, false));
                buttonSelectedFirstLevel = nameButton;
            }
            changeBackgroundButtonActiveInactiveSecond(buttonSelectedSecondLevel);
        }
    }
    
    private void changeBackgroundButtonActiveInactiveSecond(String nameButton){
        if (nameButton.equals(""))
            return;
        if (buttonSelectedSecondLevel.equals("")){
            screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonActive, false));
            buttonSelectedSecondLevel = nameButton;
        }else{
            if (buttonSelectedSecondLevel.equals(nameButton)){
                screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonInactive, false));
                buttonSelectedSecondLevel = "";
            }else{
                screen.findElementByName(buttonSelectedSecondLevel).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonInactive, false));
                screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonActive, false));
                buttonSelectedSecondLevel = nameButton;
            }
            changeBackgroundButtonActiveInactiveThird(buttonSelectedThirdLevel);
        }
    }
    
    private void changeBackgroundButtonActiveInactiveThird(String nameButton){
        if (nameButton.equals(""))
            return;
        if (buttonSelectedThirdLevel.equals("")){
            screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonActive, false));
            buttonSelectedThirdLevel = nameButton;
        }else
        if (buttonSelectedThirdLevel.equals(nameButton)){
            screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonInactive, false));
            buttonSelectedThirdLevel = "";
        }else{
            screen.findElementByName(buttonSelectedThirdLevel).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonInactive, false));
            screen.findElementByName(nameButton).getRenderer(ImageRenderer.class).setImage(nifty.createImage(buttonActive, false));
            buttonSelectedThirdLevel = nameButton;
        }
    }

    public void hideCurrentControlsWindow(){
        if (currentOptionselected.equals("buttonOptionControls")){
            if (currentDynamicButtonSelected.contains("Resources")){
                screen.findElementByName("winChC_Element").getControl(CharactersScreenController.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("PriorityActivities")){
                screen.findElementByName("winPrC_Element").getControl(PriorityScreenController.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("AssignOperators")){
                screen.findElementByName("winAsOpC_Element").getControl(AssignOperatorScreenController.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("UnitLoad")){
                screen.findElementByName("winULC_Element").getControl(UnitLoadScreenController.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("AllocateStorages")){
                screen.findElementByName("winASCC_Element").getControl(StorageCostScreenController.class).loadWindowControl(gameEngine, -1, null);
            }
        }else
        if (currentOptionselected.equals("buttonOptionActivities")){
            screen.findElementByName("winAC_Element").getControl(ActivityScreenController.class).loadWindowControl(gameEngine, -1, TypeActivity.None, null);
        }else
        if (currentOptionselected.equals("buttonOptionUtilities")){
            if (currentDynamicButtonSelected.contains("Station")){
                screen.findElementByName("winSSC_Element").getControl(StorageStationScreenController.class).loadWindowControl(gameEngine,-1,null);
//                screen.findElementByName("winSC_Element").getControl(StationControl.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("Machine") || currentDynamicButtonSelected.contains("Equipment")){
                screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("Operator")){
                screen.findElementByName("winOC_Element").getControl(OperatorScreenController.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("Part")){
                screen.findElementByName("winPC_Element").getControl(PartScreenController.class).loadWindowControl(gameEngine, -1, null);
            }else
            if (currentDynamicButtonSelected.contains("Supplier")){
                screen.findElementByName("winSuC_Element").getControl(SupplierScreenController.class).loadWindowControl(gameEngine, -1, null);
            }
        }else
        if (currentOptionselected.equals("windowOperator")){
            screen.findElementByName("winOC_Element").getControl(OperatorScreenController.class).loadWindowControl(gameEngine, -1, null);
        }else
        if (currentOptionselected.equals("windowPart")){
            screen.findElementByName("winPC_Element").getControl(PartScreenController.class).loadWindowControl(gameEngine, -1, null);
        }else
        if (currentOptionselected.equals("windowStorageStation")){
            screen.findElementByName("winSSC_Element").getControl(StorageStationScreenController.class).loadWindowControl(gameEngine,-1,null);
        }else
//        if (currentOptionselected.equals("windowStation")){
//            screen.findElementByName("winSC_Element").getControl(StationControl.class).loadWindowControl(gameEngine, -1, null);
//        }else
        if (currentOptionselected.equals("windowMachine")){
            screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).loadWindowControl(gameEngine, -1, null);
        }
    }

    @NiftyEventSubscriber(pattern="dynSubLevelBut.*")
    public void onDynamicSubLevelButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        changeBackgroundButtonActiveInactiveThird(id);
        hideCurrentControlsWindow();
        if (currentDynamicSubLevelButtonSelected.equals(((Button)nifty.getScreen("layerScreen").findNiftyControl(id, Button.class)).getText())){
            hideCurrentControlsWindow();
            currentDynamicSubLevelButtonSelected = "";
            return;
        }
        currentDynamicSubLevelButtonSelected = ((Button)nifty.getScreen("layerScreen").findNiftyControl(id, Button.class)).getText();
//        if (currentDynamicButtonSelected.contains(TypeActivity.Operation.toString()) || currentDynamicButtonSelected.contains(TypeActivity.Purchase.toString()) || currentDynamicButtonSelected.contains(TypeActivity.Transport.toString())){
//            TypeActivity tempActivityType = TypeActivity.None;
//            int tempIdActivity = arrActivitiesId.get(currentDynamicSubLevelButtonSelected);
//            if (currentDynamicButtonSelected.contains(TypeActivity.Operation.toString())){
//                tempActivityType = TypeActivity.Operation;
//            }else
//            if (currentDynamicButtonSelected.contains(TypeActivity.Purchase.toString())){
//                tempActivityType = TypeActivity.Purchase;
//            }else
//            if (currentDynamicButtonSelected.contains(TypeActivity.Transport.toString())){
//                tempActivityType = TypeActivity.Transport;
//            }
//            screen.findElementByName("winAC_Element").getControl(ActivityScreenController.class).loadWindowControl(gameEngine, tempIdActivity, tempActivityType, null);
//        }else
//        if (currentDynamicButtonSelected.contains("Station")){
//            for (E_Station temp : gameEngine.getGameData().getMapUserStation().values()){
//                if (temp.getStationDescription().equals(currentDynamicSubLevelButtonSelected)){
//                    if (temp.getStationType().equals(StationType.StorageFG) || 
//                            temp.getStationType().equals(StationType.StorageIG) ||
//                            temp.getStationType().equals(StationType.StorageRM))
//                        screen.findElementByName("winSSC_Element").getControl(StorageStationScreenController.class).loadWindowControl(gameEngine,temp.getIdStation(),null);
//                    else
//                        screen.findElementByName("winSC_Element").getControl(StationControl.class).loadWindowControl(gameEngine,temp.getIdStation(),null);
//                }                
//            }
//        }//else
//        if (currentDynamicButtonSelected.contains("Machine")){ 
//            screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).loadWindowControl(gameEngine, Integer.valueOf(currentDynamicSubLevelButtonSelected.replace(" ", "").replace("Machine", "").replace("(", "").replace(")", "").replace(Params.active, "").replace(Params.inactive, "")), null);
//            screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).setIdButton(id);
//        }else
//        if (currentDynamicButtonSelected.contains("Equipment")){
//            screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).loadWindowControl(gameEngine, Integer.valueOf(currentDynamicSubLevelButtonSelected.replace(" ", "").replace("Equipment", "").replace("(", "").replace(")", "").replace(Params.active, "").replace(Params.inactive, "")), null);
//            screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).setIdButton(id);
//        }else
//        if (currentDynamicButtonSelected.contains("Operator")){
//            screen.findElementByName("winOC_Element").getControl(OperatorScreenController.class).loadWindowControl(gameEngine, Integer.valueOf(currentDynamicSubLevelButtonSelected.replace(" ", "").replace("(", "").replace(")", "").replace("Operator", "").replace("Material", "").replace("Handler", "").replace("Ope", "").replace(Params.opeActive, "").replace(Params.opeInactive, "").replace(OperatorCategory.Versatile.toString(), "")), null);
//            screen.findElementByName("winOC_Element").getControl(OperatorScreenController.class).setIdButton(id);
//        }else
//        if (currentDynamicButtonSelected.contains("Part")){
//            screen.findElementByName("winPC_Element").getControl(PartScreenController.class).loadWindowControl(gameEngine, Integer.valueOf(currentDynamicSubLevelButtonSelected.replace(" ", "").replace("Part", "")), null);
//        }else
//        if (currentDynamicButtonSelected.contains("Supplier")){
//            screen.findElementByName("winSuC_Element").getControl(SupplierScreenController.class).loadWindowControl(gameEngine, Integer.valueOf(currentDynamicSubLevelButtonSelected.replace(" ", "").replace("Supplier", "")), null);
//        }
    }
    
    public void onDynamicButtonClicked(String id){
        changeBackgroundButtonActiveInactiveSecond(id);
        showHideDynamicSubLevelButtons(0);
        hideCurrentControlsWindow();
        if (currentDynamicButtonSelected.equals(((Button)nifty.getScreen("layerScreen").findNiftyControl(id, Button.class)).getText().replace(" ", ""))){
//            if (currentOptionselected.equals("buttonOptionInformation")){
//                if (currentDynamicButtonSelected.contains("Overall")){
//                    screen.findElementByName("winOvC_Element").getControl(OverallControl.class).loadWindowControl(gameEngine, -1, null);
//                }else
//                if (currentDynamicButtonSelected.contains("Order")){
//                    screen.findElementByName("winOrC_Element").getControl(OrderControl.class).loadWindowControl(gameEngine, -1, null);
//                }else
//                if (currentDynamicButtonSelected.contains("FlowChart")){
//                    screen.findElementByName("winFCC_Element").getControl(FlowChartScreenController.class).loadWindowControl(gameEngine, -1, null);
//                }
//            }            
            currentDynamicButtonSelected = "";
            return;
        }
        //load appropriate action
        currentDynamicButtonSelected = ((Button)nifty.getScreen("layerScreen").findNiftyControl(id, Button.class)).getText().replace(" ", "");
        
//        if (currentOptionselected.equals("buttonOptionInformation")){
//            if (currentDynamicButtonSelected.contains("Overall")){
//                screen.findElementByName("winOvC_Element").getControl(OverallControl.class).loadWindowControl(gameEngine, 0, null);
//            }else
//            if (currentDynamicButtonSelected.contains("Order")){
//                screen.findElementByName("winOrC_Element").getControl(OrderControl.class).loadWindowControl(gameEngine, 0, null);
//            }else
//            if (currentDynamicButtonSelected.contains("FlowChart")){
//                screen.findElementByName("winFCC_Element").getControl(FlowChartScreenController.class).loadWindowControl(gameEngine, 0, null);
//            }
//        }else
        if (currentOptionselected.equals("buttonOptionControls")){
            if (currentDynamicButtonSelected.contains("Resources")){
                screen.findElementByName("winChC_Element").getControl(CharactersScreenController.class).loadWindowControl(gameEngine, 0, null);
            }else
            if (currentDynamicButtonSelected.contains("PriorityActivities")){
                screen.findElementByName("winPrC_Element").getControl(PriorityScreenController.class).loadWindowControl(gameEngine, 0, null);
            }else
            if (currentDynamicButtonSelected.contains("AssignOperators")){
                screen.findElementByName("winAsOpC_Element").getControl(AssignOperatorScreenController.class).loadWindowControl(gameEngine, 0, null);
            }else
            if (currentDynamicButtonSelected.contains("UnitLoad")){
                screen.findElementByName("winULC_Element").getControl(UnitLoadScreenController.class).loadWindowControl(gameEngine, 0, null);
            }else
            if (currentDynamicButtonSelected.contains("AllocateStorages")){
                screen.findElementByName("winASCC_Element").getControl(StorageCostScreenController.class).loadWindowControl(gameEngine, 0, null);
            }
        }else
        if (currentOptionselected.equals("buttonOptionActivities")){
            TypeActivity tempActivityType = TypeActivity.None;
            Button dynamicButton;
            int position = 0;
            Screen screenButton = nifty.getScreen("layerScreen");
            arrActivitiesId.clear();
           if (currentDynamicButtonSelected.contains(TypeActivity.Operation.toString())){
                tempActivityType = TypeActivity.Operation;
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapOperation().size());
//                for (E_Operation temp : gameEngine.getGameData().getMapOperation().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                    dynamicButton.setText(temp.getActivityDescription());
//                    arrActivitiesId.put(temp.getActivityDescription(), temp.getIdActivity());
//                    position++;
//                }
            }else
            if (currentDynamicButtonSelected.contains(TypeActivity.Purchase.toString())){
                tempActivityType = TypeActivity.Purchase;
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 9)
                    Params.tutorial.nextStep();
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapPurchase().size());
//                for (E_Purchase temp : gameEngine.getGameData().getMapPurchase().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                    dynamicButton.setText(temp.getActivityDescription());
//                    arrActivitiesId.put(temp.getActivityDescription(), temp.getIdActivity());
//                    position++;
//                }

            }else
            if (currentDynamicButtonSelected.contains(TypeActivity.Transport.toString())){
                tempActivityType = TypeActivity.Transport;
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 10)
                    Params.tutorial.nextStep();
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapTransport().size(),"180%");
//                for (E_TransportStore temp : gameEngine.getGameData().getMapTransport().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class)manageSo
//                    dynamicButton.setText(temp.getActivityDescription());
//                    arrActivitiesId.put(temp.getActivityDescription(), temp.getIdActivity());
//                    position++;
//                }
            }
            screen.findElementByName("winAC_Element").getControl(ActivityScreenController.class).loadWindowControl(gameEngine, 0, tempActivityType, null);
        }else
        if (currentOptionselected.equals("buttonOptionUtilities")){
//            Button dynamicButton;
//            int position = 0;
//            Screen screenButton = nifty.getScreen("layerScreen");
            if (currentDynamicButtonSelected.contains("Station")){
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapUserStation().size()-2,"120%");
//                for (E_Station temp : gameEngine.getGameData().getMapUserStation().values()){
//                    if (!(temp.getStationType().equals(StationType.MachineZone) || temp.getStationType().equals(StationType.StaffZone))){
//                        dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                        dynamicButton.setText(temp.getStationDescription());
//                        position++;
//                    }
//                }
                screen.findElementByName("winSSC_Element").getControl(StorageStationScreenController.class).loadWindowControl(gameEngine,Params.stationList,null);
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 13)
                    Params.tutorial.nextStep();
            }else
            if (currentDynamicButtonSelected.contains("Machine")){
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapUserMachineByOperation().size());
//                for (E_Machine temp : gameEngine.getGameData().getMapUserMachineByOperation().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                    dynamicButton.setText("Machine " +temp.getIdMachine() + " (" + (temp.getMachineState().equals(ObjectState.Inactive) ? Params.inactive : Params.active) + ")");
//                    position++;
//                }
                screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).loadWindowControl(gameEngine, Params.machineList, null);
                screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).setIdButton(id);
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 7)
                    Params.tutorial.nextStep();
            }else
            if (currentDynamicButtonSelected.contains("Equipment")){
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapUserMachineByTransport().size());
//                for (E_Machine temp : gameEngine.getGameData().getMapUserMachineByTransport().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                    dynamicButton.setText("Equipment " +temp.getIdMachine() + " (" + (temp.getMachineState().equals(ObjectState.Inactive) ? Params.inactive : Params.active) + ")");
//                    position++;
//                }
                screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).loadWindowControl(gameEngine, Params.equipmentList, null);
                screen.findElementByName("winMC_Element").getControl(MachineScreenController.class).setIdButton(id);
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 8)
                    Params.tutorial.nextStep();
            }else
            if (currentDynamicButtonSelected.contains("Operator")){
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapUserOperator().size(),"140%");
//                for (E_Operator temp : gameEngine.getGameData().getMapUserOperator().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                    dynamicButton.setText("Ope" + temp.getIdOperator() + " ( " + (temp.getState().equals(ObjectState.Inactive) ? Params.opeInactive : Params.opeActive) + " ) " + (temp.getCategory().equals(OperatorCategory.Assembler) ? "Operator" : (temp.getCategory().equals(OperatorCategory.Carrier) ? "Material Handler" : OperatorCategory.Versatile.toString())));
//                    position++;
//                }
               screen.findElementByName("winOC_Element").getControl(OperatorScreenController.class).loadWindowControl(gameEngine, Params.operatorList, null);
                screen.findElementByName("winOC_Element").getControl(OperatorScreenController.class).setIdButton(id);
            }else
            if (currentDynamicButtonSelected.contains("Part")){
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapUserPart().size());
//                for (E_Part temp : gameEngine.getGameData().getMapUserPart().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                    dynamicButton.setText("Part " + temp.getIdPart());
//                    position++;
//                }
                screen.findElementByName("winPC_Element").getControl(PartScreenController.class).loadWindowControl(gameEngine, Params.partList, null);
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 12)
                    Params.tutorial.nextStep();
            }else
            if (currentDynamicButtonSelected.contains("Supplier")){
//                showHideDynamicSubLevelButtons(gameEngine.getGameData().getMapGameSupplier().size());
//                for (E_Supplier temp : gameEngine.getGameData().getMapGameSupplier().values()){
//                    dynamicButton = screenButton.findNiftyControl("dynSubLevelBut" + position, Button.class);
//                    dynamicButton.setText("Supplier " + temp.getIdSupplier());
//                    position++;
//                }
                screen.findElementByName("winSuC_Element").getControl(SupplierScreenController.class).loadWindowControl(gameEngine, Params.supplierList, null);
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 11)
                    Params.tutorial.nextStep();
            }
        }
    }
    
    @NiftyEventSubscriber(pattern="dynBut.*")
    public void onDynamicButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        onDynamicButtonClicked(id);
    }

    @NiftyEventSubscriber(pattern="buttonStaticOption.*")
    public void onOptionStaticButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
//        if (id.equals("buttonStaticOptionGameLog")){
            //showGAMELOG window
//            if (isVisibleWindowGameLog)
//                screen.findElementByName("winGLC_Element").getControl(GameLogScreenController.class).loadWindowControl(gameEngine, -1, null);
//            else
//                screen.findElementByName("winGLC_Element").getControl(GameLogScreenController.class).loadWindowControl(gameEngine, 0, null);
//            isVisibleWindowGameLog = !isVisibleWindowGameLog;
//        }else
        if (id.equals("buttonStaticOptionFlowChart")){
            if (isVisibleWindowFlowChart)
                screen.findElementByName("winFCC_Element").getControl(FlowChartScreenController.class).loadWindowControl(gameEngine, -1, null);
            else {
                screen.findElementByName("winFCC_Element").getControl(FlowChartScreenController.class).loadWindowControl(gameEngine, 0, null);
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 14)
                    Params.tutorial.nextStep();
            }
            isVisibleWindowFlowChart = !isVisibleWindowFlowChart;
        }else
        if (id.equals("buttonStaticOptionReturnToMenu")){
            
        if (Params.isTutorialLevel)
            Params.tutorial.hide();

            pauseGame();
            ((MenuScreenController)nifty.getScreen("initialMenu").getScreenController()).setDefaultStart(false);
            nifty.gotoScreen("initialMenu");
            nifty.getScreen("initialMenu").findElementByName("dialogMainMenu").hideWithoutEffect();
            nifty.getScreen("initialMenu").findElementByName("dialogNewGameStage1Menu").hideWithoutEffect();
            nifty.getScreen("initialMenu").findElementByName("dialogInitialMenu").hideWithoutEffect();
            Element nextElement = nifty.getScreen("initialMenu").findElementByName("dialogMainMenu");
            MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
            mainMenu.updateControls();
            nextElement.show();
            gameEngine.getGameData().updatePlayerLog();
        } else
        if (id.equals("buttonStaticOptionGameSetup")){
            //showGAMESETUP window
            if (isVisibleWindowGameSetup)
                screen.findElementByName("winGSC_Element").getControl(GameSetupScreenController.class).loadWindowControl(gameEngine, -1, null);
            else {
                screen.findElementByName("winGSC_Element").getControl(GameSetupScreenController.class).loadWindowControl(gameEngine, 0, null);
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 1)
                    Params.tutorial.nextStep();
            }
            isVisibleWindowGameSetup = !isVisibleWindowGameSetup;
        }
    }
    
    @NiftyEventSubscriber(pattern="buttonOption.*")
    public void onOptionButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        optionButtonClicked(id);
    }
    
    public void optionButtonClicked(String id){
        changeBackgroundButtonActiveInactiveFirst(id);
        showHideDynamicSubLevelButtons(0);
        hideCurrentControlsWindow();
        if (id.equals(currentOptionselected)){
            showHideDynamicButtons(0);
            currentOptionselected = "";
            return;
        }
        Screen screenButton = nifty.getScreen("layerScreen");
        Button dynamicButton;
        int position = 0;
        
//        if (id.equals("buttonOptionMenu")){
//            showHideDynamicButtons(0);
//            pauseGame();
//            ((MenuScreenController)nifty.getScreen("initialMenu").getScreenController()).setDefaultStart(false);
//            nifty.gotoScreen("initialMenu");
//            (nifty.getScreen("initialMenu").findElementByName("dialogNewGameStage1Menu")).hide();
//            (nifty.getScreen("initialMenu").findElementByName("dialogInitialMenu")).hide();
//            Element nextElement = nifty.getScreen("initialMenu").findElementByName("dialogMainMenu");
//            MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
//            mainMenu.updateControls();
//            nextElement.show();
//            nifty.getScreen("initialMenu").findElementByName("dialogNewGameStage1Menu").stopEffect(EffectEventId.onCustom);
//            nifty.getScreen("initialMenu").findElementByName("dialogInitialMenu").stopEffect(EffectEventId.onCustom);
//            nifty.getScreen("initialMenu").findElementByName("dialogMainMenu").startEffect(EffectEventId.onCustom, null, "selected");
//            gameEngine.getGameData().updatePlayerLog();
//        }else
        
//        if (id.equals("buttonOptionInformation")){
//           showHideDynamicButtons(3);
//            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Overall"); position++;
//            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Order"); position++;
//            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Process Flow Chart"); position++;
//        }else
        if (id.equals("buttonOptionControls")){
            showHideDynamicButtons(5);
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Allocate Storages"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Assign Operators"); position++;            
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Priority Activities"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Resources"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Unit Load"); position++;
        }else
        if (id.equals("buttonOptionActivities")){
            showHideDynamicButtons(3);
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText(TypeActivity.Operation.toString() + " (" + gameEngine.getGameData().getMapOperation().size() + ")"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText(TypeActivity.Purchase.toString() + " (" + gameEngine.getGameData().getMapPurchase().size() + ")"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText(TypeActivity.Transport.toString() + " (" + gameEngine.getGameData().getMapTransport().size() + ")"); position++;
        }else
        if (id.equals("buttonOptionUtilities")){
            showHideDynamicButtons(6);
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Station (" + (gameEngine.getGameData().getMapUserStation().size()-2) + ")"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Machine (" + gameEngine.getGameData().getMapUserMachineByOperation().size() + ")"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Equipment (" + gameEngine.getGameData().getMapUserMachineByTransport().size() + ")"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Operator (" + gameEngine.getGameData().getMapUserOperator().size() + ")"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Part (" + gameEngine.getGameData().getMapUserPart().size() + ")"); position++;
            dynamicButton = screenButton.findNiftyControl("dynBut" + position, Button.class); dynamicButton.setText("Supplier(" + gameEngine.getGameData().getMapGameSupplier().size() + ")"); position++;
            if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 4)
                Params.tutorial.nextStep();

        }
        currentOptionselected = id;
    }
    
    public void updateSubLevelButtonText(String idButton, String newText){
        nifty.getScreen("layerScreen").findNiftyControl(idButton, Button.class).setText(newText);
    }
    public boolean getPauseStatus()
    {
        return this.isGamePlaying;
    }
    public void notifySound()
    { if(screen.findElementByName("controlVolumeMusic_MGV")!=null)
    {
        if(!screen.findElementByName("controlVolumeMusic_MGV").isEnabled())
            gameEngine.getGameSounds().setVolumeSFX(0,gameEngine);
        else
        gameEngine.getGameSounds().setVolumeSFX(((Slider) screen.findNiftyControl("controlVolumeSFX_MGV", Slider.class)).getValue()/100.f,gameEngine);
    }
    }
}
