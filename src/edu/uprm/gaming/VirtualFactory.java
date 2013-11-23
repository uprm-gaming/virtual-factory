package edu.uprm.gaming;

import com.jme3.app.SimpleApplication;

/**
 * Virtual Factory 2.0
 */
public final class VirtualFactory extends SimpleApplication {
    private static VirtualFactory instance;
    
    public static void main(String[] args) {
        VirtualFactory app = VirtualFactory.getInstance();
        app.setSettings(ScreenSettings.generate());
        app.setShowSettings(false);
        app.setPauseOnLostFocus(false);
        app.start(); // calls simpleInitApp()
    }
    
    private VirtualFactory() {}
    
    public static VirtualFactory getInstance() {
        if (instance == null)
            instance = new VirtualFactory();
        return instance;
    }
    
    @Override
    public void simpleInitApp() {
        // Turn off default jMonkey HUD
        // -----------
        setDisplayFps(false);
        setDisplayStatView(false);
        // -----------
        stateManager.attach(new GameEngine()); // start the game       
    }
}