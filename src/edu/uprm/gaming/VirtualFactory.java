package edu.uprm.gaming;

import com.jme3.app.SimpleApplication;

/**
 * Virtual Factory 2.0
 */
public class VirtualFactory extends SimpleApplication {
    
    public static void main(String[] args) {
        VirtualFactory app = new VirtualFactory();
        app.setSettings(GameSettings.generate());
        app.setShowSettings(false);
        app.setPauseOnLostFocus(false);
        app.start();  
    }
    
    /**
     * Enables the game engine. Called automatically after app.start().
     */
    @Override
    public void simpleInitApp() {
        // Turn off default HUD
        // -----------
        setDisplayFps(false);
        setDisplayStatView(false);
        // -----------
        stateManager.attach(new GameEngine()); // start the game
    }
}