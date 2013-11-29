/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.menu.components;

import com.virtualfactory.menu.components.MenuScreenController;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Status;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Jose Martinez
 * [Note: Based on David Bengoa's original game interface for Virtual Factory 1.0]
 */
public class MainMenuController implements Controller {
    private Screen screen;
    private Nifty nifty;
    private Button newGameButton;
    private Button controlsButton;
//    private Button loadGameButton;
//    private Button saveGameButton;
    private Button optionsButton;
    private Button switchUserButton;
    private Button creditsButton;
    private Button quitButton;
    private GameEngine gameEngine;
    private Label userName;
    private Element creditsPopup;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.screen = screen;
        this.nifty = nifty;
        this.newGameButton = screen.findNiftyControl("newGameButton_MMD", Button.class);
        this.controlsButton = screen.findNiftyControl("controlsButton_MMD", Button.class);
//        this.saveGameButton = screen.findNiftyControl("saveGameButton_MMD", Button.class);
//        this.loadGameButton = screen.findNiftyControl("loadGameButton_MMD", Button.class);
//        this.optionsButton = screen.findNiftyControl("optionsButton_MMD", Button.class);
        this.switchUserButton = screen.findNiftyControl("switchUserButton_MMD", Button.class);
        this.creditsButton = screen.findNiftyControl("creditsButton_MMD", Button.class);
        this.quitButton = screen.findNiftyControl("quitButton_MMD", Button.class);
        this.userName = screen.findNiftyControl("userName_MMD", Label.class);
        this.gameEngine = ((MenuScreenController)screen.getScreenController()).getGameEngine();
        this.creditsPopup = nifty.createPopup("creditsPopup");
    }
    
    @Override
    public void init(final Properties parameter, final Attributes controlDefinitionAttributes) {
    }

    @Override
    public void onStartScreen() {
        if (gameEngine.isExecuteGame()){
            screen.findElementByName("returnToGameButton_MMD").enable();
        }else{
            screen.findElementByName("returnToGameButton_MMD").disable();
        }
    }

    @Override
    public void onFocus(final boolean getFocus) {
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    public void updateControls(){
        if (this.gameEngine.getGameData().getPlayer() != null)
            this.userName.setText("Welcome back: " + this.gameEngine.getGameData().getPlayer().getName().toUpperCase());
        
        if (!gameEngine.isExecuteGame()) {
            screen.findElementByName("returnToGameButton_MMD").disable();
        }
        
        if (Params.BUILD_FOR_TESTING_SESSION) {
                    screen.findElementByName("editProfileButton_MMD").disable();
                    screen.findElementByName("creditsButton_MMD").disable();
        }
    }

    @NiftyEventSubscriber(id="newGameButton_MMD")
    public void onNewGameButtonClicked(final String id, final ButtonClickedEvent event) {
        Element currentElement = screen.findElementByName("dialogMainMenu");
        currentElement.hide();
        Element nextElement = screen.findElementByName("dialogNewGameStage1Menu");
        NewGame1MenuController loadGameMenu = nextElement.getControl(NewGame1MenuController.class);
        nextElement.show();
        loadGameMenu.updateControls_stage1();
        screen.findElementByName("dialogMainMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogNewGameStage1Menu").startEffect(EffectEventId.onCustom, null, "selected");
    }
    
    @NiftyEventSubscriber(id="controlsButton_MMD")
    public void onControlsButtonClicked(final String id, final ButtonClickedEvent event) {
        Element currentElement = screen.findElementByName("dialogMainMenu");
        currentElement.hide();
        Element nextElement = screen.findElementByName("dialogControlsMenu");
        ControlsController controlsMenu = nextElement.getControl(ControlsController.class);
        nextElement.show();
        screen.findElementByName("dialogMainMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogControlsMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
    
//    @NiftyEventSubscriber(id="saveGameButton_MMD")
//    public void onSaveGameButtonClicked(final String id, final ButtonClickedEvent event) {
//        System.out.println("*** SAVING GAME ***");
//        gameEngine.setCurrentSystemStatus(Status.Idle);
//        gameEngine.getGameData().playPauseElements(Status.Idle);
//        gameEngine.getGameData().saveGame();
//        System.out.println("*** END GAME SAVED ***");
//
//        Element currentElement = screen.findElementByName("dialogMainMenu");
//        currentElement.hide();
//        screen.endScreen(null);
//    }
    
    @NiftyEventSubscriber(id="loadGameButton_MMD")
    public void onLoadGameButtonClicked(final String id, final ButtonClickedEvent event) {
        Element nextElement = screen.findElementByName("dialogLoadGameMenu");
        LoadGameMenuController loadGameMenu = nextElement.getControl(LoadGameMenuController.class);
        loadGameMenu.updateControls();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogMainMenu");
        currentElement.hide();
        screen.findElementByName("dialogMainMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogLoadGameMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
    
    @NiftyEventSubscriber(id="editProfileButton_MMD")
    public void onEditProfileButtonClicked(final String id, final ButtonClickedEvent event) {
        Element nextElement = screen.findElementByName("dialogNewUserMenu");
        NewUserMenuController newUserMenu = nextElement.getControl(NewUserMenuController.class);
//        newUserMenu.updateControls(false, gameEngine.getGameData().getPlayer());
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogMainMenu");
        currentElement.hide();
        screen.findElementByName("dialogMainMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogNewUserMenu").startEffect(EffectEventId.onCustom, null, "selected");
        newUserMenu.updateControls(false, gameEngine.getGameData().getPlayer());
    }
    
//    @NiftyEventSubscriber(id="optionsButton_MMD")
//    public void onOptionsButtonClicked(final String id, final ButtonClickedEvent event) {
//        Element nextElement = screen.findElementByName("dialogOptionsMenu");
//        nextElement.show();
//        Element currentElement = screen.findElementByName("dialogMainMenu");
//        currentElement.hide();
//        screen.findElementByName("dialogMainMenu").stopEffect(EffectEventId.onCustom);
//        screen.findElementByName("dialogOptionsMenu").startEffect(EffectEventId.onCustom, null, "selected");
//    }
    
    @NiftyEventSubscriber(id="switchUserButton_MMD")
    public void onSwitchUserButtonClicked(final String id, final ButtonClickedEvent event) {
        Element nextElement = screen.findElementByName("dialogInitialMenu");
        InitialMenuController initialMenu = nextElement.getControl(InitialMenuController.class);
        nextElement.show();
        initialMenu.updateControls();        
        Element currentElement = screen.findElementByName("dialogMainMenu");
        currentElement.hide();
        screen.findElementByName("dialogMainMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogInitialMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
    
    @NiftyEventSubscriber(id="creditsButton_MMD")
    public void onCreditsButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.showPopup(screen, creditsPopup.getId(), null);
    }
    
//    @NiftyEventSubscriber(id="tutorialButton_MMD")
//    public void onTutorialButtonClicked(final String id, final ButtonClickedEvent event) {
//        gameEngine.updateCursorIcon(1);
//        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
//        if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)){
//            try{
//                File tutorialFile = new File("TutorialGaming.mp4");
//                desktop.browse(tutorialFile.toURI());
//            }catch(Exception e){
//                System.out.println("Error opening tutorial: " + e.getMessage());
//            }
//        }
//        gameEngine.updateCursorIcon(0);
//    }
    
    @NiftyEventSubscriber(id="userManualButton_MMD")
    public void onUserManualButtonClicked(final String id, final ButtonClickedEvent event) {
        //if the game is fullscreen, change the view in order to see the manual 
        //in the browser
        if (gameEngine.app.getContext().getSettings().isFullscreen())
            gameEngine.changeScreenSize();
        
        gameEngine.updateCursorIcon(1);
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            String path = "gamingUserManual.html";
            try{
                if (Params.BUILD_FOR_MAC_APP) {
                    path = "";
                    String javaPath = System.getProperty("java.class.path");
                    int index = javaPath.indexOf(".app/Contents/Java");
                    for (int i = 0; i < index; i++)
                        path += javaPath.charAt(i);
                    
                    path += ".app/Contents/Resources/gamingUserManual.html";
                }

                File tutorialFile = new File(path);
                desktop.browse(tutorialFile.toURI());
            }catch(Exception e){
                System.out.println("Error opening user manual: " + e.getMessage());
            }
        }
        gameEngine.updateCursorIcon(0);
    }
    
    @NiftyEventSubscriber(id="returnToGameButton_MMD")
    public void onReturnToGameButtonClicked(final String id, final ButtonClickedEvent event) {
        Element currentElement = screen.findElementByName("dialogMainMenu");
        currentElement.hide();
        screen.findElementByName("dialogNewGameStage1Menu").stopEffect(EffectEventId.onCustom);
        nifty.gotoScreen("layerScreen");
    }

    @NiftyEventSubscriber(id="creditsBack")
    public void onCreditsBackClick(final String id, final ButtonClickedEvent event) {
        nifty.closePopup(creditsPopup.getId());
    }
    
    
}
