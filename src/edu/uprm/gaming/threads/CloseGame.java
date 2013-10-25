/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.threads;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.utils.Messages;
import edu.uprm.gaming.utils.Params;

/**
 *
 * @author David
 */
public class CloseGame extends Thread {
    private GameEngine gameEngine;
    private boolean continueGame;
    private Element exitPopup;
    private Nifty nifty;
    private Screen screen;
    private Element quitPopup;
    private Label updatingLabel;
    
    @Override
    public void run(){
        long currentTimeExit = 0;
        long initialTimeExit = 0;
        continueGame = false;
        gameEngine.updateLastActivitySystemTime();
        initialTimeExit = System.currentTimeMillis()/1000;
        updatingLabel = nifty.getCurrentScreen().findNiftyControl("gameClosingMessage", Label.class);
        updatingLabel.setText(Messages.gameClosing.replace(Messages.wildCard, String.valueOf(Params.timeToExitGameSeconds)));
        int newMissingTime = 0;
        while (newMissingTime >= 0 && !continueGame&&!this.gameEngine.getGeneralScreenController().getPauseStatus()){
            currentTimeExit = System.currentTimeMillis()/1000;
            newMissingTime = Params.timeToExitGameSeconds - (int)(currentTimeExit - initialTimeExit);
            updatingLabel.setText(Messages.gameClosing.replace(Messages.wildCard, String.valueOf(newMissingTime)));
            gameEngine.updateLastActivitySystemTime();
        }
        if (continueGame){
            nifty.closePopup(exitPopup.getId());
            gameEngine.updateLastActivitySystemTime();
        }else{//exit
            updatingLabel.setText(Messages.gameIsClosed);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("ERROR:" + ex.getMessage());
            }
            nifty.closePopup(exitPopup.getId());
            gameEngine.getGameData().logoutPlayer();
            gameEngine.app.stop();
             System.exit(0);
        }        
    }

    public boolean isContinueGame() {
        return continueGame;
    }

    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }
    
    public Element getExitPopup() {
        return exitPopup;
    }

    public void setExitPopup(Element exitPopup) {
        this.exitPopup = exitPopup;
    }

    public Element getQuitPopup() {
        return quitPopup;
    }

    public void setQuitPopup(Element quitPopup) {
        this.quitPopup = quitPopup;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Nifty getNifty() {
        return nifty;
    }

    public void setNifty(Nifty nifty) {
        this.nifty = nifty;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    
}
