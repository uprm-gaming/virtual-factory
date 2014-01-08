package com.virtualfactory.utils;

import com.jme3.system.AppSettings;
import com.virtualfactory.main.VirtualFactory;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ScreenSettings {
    private GraphicsDevice computerScreen;
    private AppSettings gameScreen;
    private DisplayMode chosenScreenMode;

    private ScreenSettings() {
        loadGameScreen();
    }
    
    public static AppSettings generate() {
        return new ScreenSettings().getLoadedGameScreen();
    }
    
    private void loadGameScreen() {
        gameScreen = new AppSettings(true);
        Params.renderer = gameScreen.getRenderer();
        gameScreen.setTitle("Virtual Factory 2.0 (Beta) - ININ-UPRM - NSF #0835990");
        gameScreen.setRenderer(AppSettings.LWJGL_OPENGL2);
        gameScreen.setSettingsDialogImage("Interface/icon.png");
        
        loadScreenMode();
        loadIcons();
    }
    
    private void loadScreenMode() {
        computerScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (!computerScreen.isFullScreenSupported())
            return;
        
        DisplayMode[] availableScreenModes = computerScreen.getDisplayModes();
        ArrayList<DisplayMode> allowedScreenModes = new ArrayList<>();
        
        for (DisplayMode mode: availableScreenModes) {
            if (mode.getWidth() == 1280)
                allowedScreenModes.add(mode);
        }
        
        if (!allowedScreenModes.isEmpty()) {
            chosenScreenMode = allowedScreenModes.get(0);
            if (allowedScreenModes.size() > 1) {
                chooseBestScreenMode(allowedScreenModes);
            }
        }
        
        if (isComputerCapableForFullscreen())
            makeItFullscreen();  
        else
            makeItStandardScreen();
    }
    
    private void chooseBestScreenMode(ArrayList<DisplayMode> allowedScreenModes) {
        for (int i = 0; i < allowedScreenModes.size() - 1; i++) {
            DisplayMode nextAvailableMode = allowedScreenModes.get(i+1);
            boolean nextModeHasBetterHeight = Math.abs(chosenScreenMode.getHeight() - 720) 
                                              > Math.abs(nextAvailableMode.getHeight() - 720);
            if (nextModeHasBetterHeight)
                chosenScreenMode = nextAvailableMode;
        }
    }
    
    private boolean isComputerCapableForFullscreen() {
        return chosenScreenMode != null;
    }
    
    private void makeItFullscreen() {
        gameScreen.setFullscreen(true);
        gameScreen.setWidth(chosenScreenMode.getWidth());
        gameScreen.setHeight(chosenScreenMode.getHeight());
        gameScreen.setFrequency(chosenScreenMode.getRefreshRate());
        Params.fullScreenHeight = chosenScreenMode.getHeight();
    }
    
    private void makeItStandardScreen() {
        gameScreen.setFullscreen(false);
        gameScreen.setResolution(1280, 720);
    }

    private void loadIcons() {
        try {
            Class<VirtualFactory> myClass = VirtualFactory.class;
            gameScreen.setIcons(new BufferedImage[]{
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_16.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_24.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_32.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_48.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_64.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_72.png"))
            });
        }
        catch (NullPointerException e) {
            throw new NullPointerException("gameScreen was not initialized - " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Unable to load program icons - " + e.getMessage());
        }
    }
    
    public AppSettings getLoadedGameScreen() { 
        return gameScreen; 
    }
}