package com.virtualfactory.gui.menu.resources;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.KeyInputHandler;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;
import com.virtualfactory.app.GameEngine;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class MenuScreenController implements ScreenController, KeyInputHandler {
    private static final Logger logger = Logger.getLogger(MenuScreenController.class.getName());
    private static final Color HELP_COLOR = new Color("#aaaf");

    private Nifty nifty;
    private Screen screen;
    private GameEngine gameEngine;
    private Element quitPopup;
    private boolean defaultStart = true;

    public boolean isDefaultStart() {
        return defaultStart;
    }

    public void setDefaultStart(boolean defaultStart) {
        this.defaultStart = defaultStart;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    
    @Override
    public void bind(final Nifty nifty, final Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
        this.quitPopup = nifty.createPopup("quitPopup");
    }
    
    @Override
    public void onStartScreen() {
        if (defaultStart){
            screen.findElementByName("dialogInitialMenu").show();
            screen.findElementByName("dialogInitialMenu").startEffect(EffectEventId.onCustom, null, "selected");
        }
    }
    
    @Override
    public void onEndScreen() {
//        nifty.gotoScreen("layerScreen");
    }
    
    @Override
    public boolean keyEvent(final NiftyInputEvent inputEvent) {
//        if (inputEvent == NiftyInputEvent.ConsoleToggle) {
//            if (screen.isActivePopup(consolePopup)) {
//                nifty.closePopup(consolePopup.getId());
//            } else {
//                nifty.showPopup(screen, consolePopup.getId(), null);
//            }
//            return true;
//        }
        return false;
    }
    
    @NiftyEventSubscriber(pattern="quitButton_.*")
    public void onQuitButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.showPopup(screen, quitPopup.getId(), null);
    }
    
    @NiftyEventSubscriber(pattern="quitPopup.*")
    public void onAnswerPopupButtonClicked(final String id, final ButtonClickedEvent event) {
        if (id.equals("quitPopupYes")){
            gameEngine.getGameData().logoutPlayer();
            gameEngine.app.stop();
            System.exit(0);
        }else{
            nifty.closePopup(quitPopup.getId());
        }
    }
}