package edu.uprm.gaming;

import com.jme3.app.SimpleApplication;
import edu.uprm.gaming.utils.Params;

/**
 * Virtual Factory 2.0
 */
public final class VirtualFactory extends SimpleApplication {
    private static VirtualFactory instance;
    
    public static void main(String[] args) {
        VirtualFactory app = VirtualFactory.getInstance();
        app.setSettings(ScreenSettings.generate());
//        app.setShowSettings(false);
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
        
        //Move this to a better location in the project
        Params.gameNarrator = edu.uprm.gaming.NarratorAppState.newInstance(assetManager, guiNode);
        stateManager.attach(new GameEngine()); // start the game
        
    }
}