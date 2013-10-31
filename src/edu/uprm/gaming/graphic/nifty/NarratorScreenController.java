package edu.uprm.gaming.graphic.nifty;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class NarratorScreenController extends AbstractAppState implements ScreenController {
    private Nifty nifty;
    private Screen screen;
    private SimpleApplication simpleApp;
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    @Override
    public void onStartScreen() 
    {}

    @Override
    public void onEndScreen() 
    {}
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        simpleApp = (SimpleApplication) app;
    }
    
    @Override
    public void update(float tpf)
    {}
}
