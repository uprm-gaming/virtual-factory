/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.entity.E_Player;
import edu.uprm.gaming.utils.Messages;
import edu.uprm.gaming.utils.SendEmail;
import java.util.Properties;
/**
 *
 * @author David
 */
public class ForgotYourPasswordController implements Controller {
    private Screen screen;
    private GameEngine gameEngine;
    private Nifty nifty;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.screen = screen;
        this.nifty = nifty;
        this.gameEngine = ((MenuScreenController)screen.getScreenController()).getGameEngine();
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
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    protected void updateControls(){
        ((TextField)screen.findNiftyControl("passRecovered_FYP", TextField.class)).setText("");
        ((TextField)screen.findNiftyControl("passRecovered_FYP", TextField.class)).setMaxLength(100);
        ((Label)screen.findNiftyControl("errorMessage_FYP", Label.class)).setText("");
        ((Button)screen.findNiftyControl("sendEmail_FYP", Button.class)).enable();
    }
    
    @NiftyEventSubscriber(id="sendEmail_FYP")
    public void onSendEmailButtonClicked(final String id, final ButtonClickedEvent event) {
        if (!validateEmail()){
            ((Label)screen.findNiftyControl("errorMessage_FYP", Label.class)).setText("Error: Please type your email correctly!");
            return;
        }
        if (gameEngine.getGameData().validateEmailNewPlayer(((TextField)screen.findNiftyControl("passRecovered_FYP", TextField.class)).getText())){
            ((Label)screen.findNiftyControl("errorMessage_FYP", Label.class)).setText("Error: The email account is not registered!");
            return;
        }
        E_Player player = gameEngine.getGameData().getPlayerByEmail(((TextField)screen.findNiftyControl("passRecovered_FYP", TextField.class)).getText());
        if (player != null){
            gameEngine.updateCursorIcon(1);
            String result = new SendEmail().send(player.getEmail(), player.getName(), Messages.forgotPassword.replace(Messages.wildCard, player.getName()).replace(Messages.wildCard2, player.getPassword()));
            gameEngine.updateCursorIcon(0);
            if (result.isEmpty()){
                ((Label)screen.findNiftyControl("errorMessage_FYP", Label.class)).setText("Thank you " + player.getName() + ". Your password has been sent to your email account");
                ((Button)screen.findNiftyControl("sendEmail_FYP", Button.class)).disable();
            }else{
                ((Label)screen.findNiftyControl("errorMessage_FYP", Label.class)).setText(result);
            }            
        }        
    }
    
    @NiftyEventSubscriber(id="cancel_FYP")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        switchToInitialMenu();
    }
    
    private boolean validateEmail(){
        if (!((TextField)screen.findNiftyControl("passRecovered_FYP", TextField.class)).getText().contains("@"))
            return false;
        if (!((TextField)screen.findNiftyControl("passRecovered_FYP", TextField.class)).getText().contains("."))
            return false;
        return true;
    }
    
    private void switchToInitialMenu(){
        Element nextElement = screen.findElementByName("dialogInitialMenu");
        InitialMenuController initialMenu = nextElement.getControl(InitialMenuController.class);
        initialMenu.updateControls();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogForgotYourPasswordMenu");
        currentElement.hide();
        screen.findElementByName("dialogForgotYourPasswordMenu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogInitialMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
}
