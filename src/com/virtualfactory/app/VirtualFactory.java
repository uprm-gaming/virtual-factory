package com.virtualfactory.app;

import com.virtualfactory.utils.ScreenSettings;
import com.jme3.app.SimpleApplication;

/**
 * Virtual Factory 2.0
 */
public final class VirtualFactory extends SimpleApplication {
    
    public static void main(String[] args) {
        VirtualFactory app = new VirtualFactory();
        app.setSettings(ScreenSettings.generate());
        app.setShowSettings(false);
        app.setPauseOnLostFocus(false);
        app.start(); // calls simpleInitApp()
    }
    
    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        stateManager.attach(new GameEngine()); // start the game       
    }
}