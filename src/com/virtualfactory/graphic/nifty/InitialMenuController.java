/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.graphic.nifty;

import de.lessvoid.nifty.EndNotify;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.data.GameData;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.utils.Params;
import java.util.Properties;

/**
 *
 * @author David
 */
public class InitialMenuController implements Controller {

    private Screen screen;
    private Button loginButton;
    private Button newUserButton;
    private Button quitButton;
    private TextField userTextField;
    private TextField passTextField;
    private Label errorMessage;
    private GameEngine gameEngine;
    private Nifty nifty;
    private Element creditsPopup;
    private boolean checkedUpdate;
    private Element updatingPopup;

    @Override
    public void bind(
            final Nifty nifty,
            final Screen screen,
            final Element element,
            final Properties parameter,
            final Attributes controlDefinitionAttributes) {
        this.screen = screen;
        this.nifty = nifty;
        this.userTextField = screen.findNiftyControl("userTextField_IMD", TextField.class);
        this.passTextField = screen.findNiftyControl("passTextField_IMD", TextField.class);
        this.loginButton = screen.findNiftyControl("loginButton_IMD", Button.class);
        this.newUserButton = screen.findNiftyControl("newUserButton_IMD", Button.class);
        this.quitButton = screen.findNiftyControl("quitButton_IMD", Button.class);
        this.errorMessage = screen.findNiftyControl("errorMessage_IMD", Label.class);
        this.gameEngine = ((MenuScreenController) screen.getScreenController()).getGameEngine();
        this.gameEngine.setExecuteGame(false);
        this.creditsPopup = nifty.createPopup("creditsPopup");
        checkedUpdate = false;
        this.updatingPopup = nifty.createPopup("gameUpdating");
    }

    @Override
    public void init(final Properties parameter, final Attributes controlDefinitionAttributes) {
        updateControls();
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onFocus(final boolean getFocus) {
//        userTextField.setFocus();
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }

    protected void updateControls() {
        userTextField.setText("");
        userTextField.setMaxLength(100);
        passTextField.setText("");
        passTextField.setMaxLength(20);
        if (Params.DEBUG_ON) {
            userTextField.setText("nnn@nnn.com");
            passTextField.setText("123456");
        }
        errorMessage.setText("");
        if (!checkedUpdate && !Params.DEBUG_ON) {
            checkedUpdate = true;
            nifty.showPopup(screen, updatingPopup.getId(), null);
            screen.processAddAndRemoveLayerElements();
            gameEngine.getGameData().updateLocalDB();
        }
        userTextField.setFocus();
        if (Params.DEBUG_ON) {
            onLoginButtonClicked("", null );
        }
    }

    public void closePopupLoading(boolean isDataLoading) {
        nifty.closePopup(updatingPopup.getId(), new popupClosed());
//        if (isDataLoading){
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                System.out.println("ERROR:" + ex.getMessage());
//            }
//        }        
//        userTextField.setFocus();
    }

    class popupClosed implements EndNotify {

        @Override
        public void perform() {
            userTextField.setFocus();
        }
    }

    @NiftyEventSubscriber(id = "loginButton_IMD")
    public void onLoginButtonClicked(final String id, final ButtonClickedEvent event) {
        LoginAction();
    }

    @NiftyEventSubscriber(id = "userTextField_IMD")
    public void onUserTextFieldInputEvent(final String id, final NiftyInputEvent event) {
        if (NiftyInputEvent.SubmitText.equals(event)) {
            LoginAction();
        }
    }

    @NiftyEventSubscriber(id = "passTextField_IMD")
    public void onPassTextFieldInputEvent(final String id, final NiftyInputEvent event) {
        if (NiftyInputEvent.SubmitText.equals(event)) {
            LoginAction();
        }
    }

    private void LoginAction() {

        if (userTextField.getText().length() == 0 || passTextField.getText().length() == 0) {
            errorMessage.setText("Error: Please fill the fields correctly!");
            return;
        }
        errorMessage.setText("");
        //change to MainMenu screen
        nifty.showPopup(screen, updatingPopup.getId(), null);
        screen.processAddAndRemoveLayerElements();
        gameEngine.getGameData().loginPlayer(userTextField.getText(), passTextField.getText());
    }

    public void LoginResponse(boolean isValidated) {
        closePopupLoading(false);
        screen.processAddAndRemoveLayerElements();
        if (isValidated) {
            if (Params.DEBUG_ON) { //Go straight to the levels menu
                Element nextElement = screen.findElementByName("dialogNewGameStage1Menu");
                NewGame1MenuController loadGameMenu = nextElement.getControl(NewGame1MenuController.class);
                loadGameMenu.updateControls_stage1();
                nextElement.show();
                Element currentElement = screen.findElementByName("dialogInitialMenu");
                currentElement.hide();
                screen.findElementByName("dialogInitialMenu").stopEffect(EffectEventId.onCustom);
                screen.findElementByName("dialogNewGameStage1Menu").startEffect(EffectEventId.onCustom, null, "selected");
                screen.findElementByName("returnToGameButton_MMD").disable();
                gameEngine.setExecuteGame(false);

            } else {
                Element nextElement = screen.findElementByName("dialogMainMenu");
                MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
                mainMenu.updateControls();
                nextElement.show();
                Element currentElement = screen.findElementByName("dialogInitialMenu");
                currentElement.hide();
                screen.findElementByName("dialogInitialMenu").stopEffect(EffectEventId.onCustom);
                screen.findElementByName("dialogMainMenu").startEffect(EffectEventId.onCustom, null, "selected");
                screen.findElementByName("returnToGameButton_MMD").disable();
                gameEngine.setExecuteGame(false);
            }
        } else {
            if (Params.errorDatabaseMessage.equals("")) {
                errorMessage.setText("Error: User and/or password are not valid");
            } else {
                errorMessage.setText(Params.errorDatabaseMessage);
            }
        }
    }

    @NiftyEventSubscriber(id = "newUserButton_IMD")
    public void onNewUserButtonClicked(final String id, final ButtonClickedEvent event) {
        //change to NewUserMenu screen
        Element nextElement = screen.findElementByName("dialogNewUserMenu");
        NewUserMenuController newUserMenu = nextElement.getControl(NewUserMenuController.class);
//        nextElement.show();
//        newUserMenu.updateControls(true, null);
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogInitialMenu");
        currentElement.hide();
        screen.findElementByName("dialogInitialMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogNewUserMenu").startEffect(EffectEventId.onCustom, null, "selected");
        newUserMenu.updateControls(true, null);
    }

    public void clickToForgotPassword() {
        Element nextElement = screen.findElementByName("dialogForgotYourPasswordMenu");
        ForgotYourPasswordController forgotPassword = nextElement.getControl(ForgotYourPasswordController.class);
        forgotPassword.updateControls();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogInitialMenu");
        currentElement.hide();
        screen.findElementByName("dialogInitialMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogForgotYourPasswordMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
}
