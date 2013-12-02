package com.virtualfactory.main;

import com.virtualfactory.engine.GameEngine;
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
        disableJmonkeyHUD();
        loadGame();      
    }
    
    private void disableJmonkeyHUD() {
        setDisplayFps(false);
        setDisplayStatView(false);
    }
    
    private void loadGame() {
        stateManager.attach(new GameEngine());
    }

    @Override
    public void destroy() {
        stop();
        System.exit(0);
    }
}