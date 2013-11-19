package edu.uprm.gaming;

import com.jme3.system.AppSettings;
import edu.uprm.gaming.utils.Params;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ScreenSettings {
    private GraphicsDevice computerScreen;
    private AppSettings customJmonkeyScreen;
    private DisplayMode selectedScreenMode;

    private ScreenSettings() {
        loadComputerScreenMode();
        loadJmonkeyScreen();
        loadIcons();
    }
    
    public static AppSettings generate() {
        ScreenSettings virtualFactoryScreen = new ScreenSettings();
        return virtualFactoryScreen.getJmonkeyScreen();
    }
    
    private void loadComputerScreenMode() {
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
            selectedScreenMode = allowedScreenModes.get(0);
            if (allowedScreenModes.size() > 1) {
                chooseBestScreenMode(allowedScreenModes);
            }
        }
    }
    
    private void chooseBestScreenMode(ArrayList<DisplayMode> allowedScreenModes) {
        for (int i = 0; i < allowedScreenModes.size() - 1; i++) {
            DisplayMode nextAvailableMode = allowedScreenModes.get(i+1);
            boolean nextModeHasBetterHeight = Math.abs(selectedScreenMode.getHeight() - 720) 
                                              > Math.abs(nextAvailableMode.getHeight() - 720);
            if (nextModeHasBetterHeight)
                selectedScreenMode = nextAvailableMode;
        }
    }
    
    private void loadJmonkeyScreen() {
        customJmonkeyScreen = new AppSettings(true);
        Params.renderer = customJmonkeyScreen.getRenderer();
        
        customJmonkeyScreen.setTitle("Virtual Factory 2.0 (Alpha) - ININ-UPRM - NSF #0835990");
        customJmonkeyScreen.setRenderer(AppSettings.LWJGL_OPENGL2);
        customJmonkeyScreen.setSettingsDialogImage("Interface/icon.png");
        
        if (isComputerCapableForFullscreen()) {
            loadFullscreenSettings();
        }   
        else
            loadDefaultScreenSettings();
    }
    
    private boolean isComputerCapableForFullscreen() {
        return selectedScreenMode != null;
    }
    
    private void loadDefaultScreenSettings() {
        customJmonkeyScreen.setFullscreen(false);
        customJmonkeyScreen.setResolution(1280, 720);
    }

    private void loadFullscreenSettings() {
        customJmonkeyScreen.setFullscreen(true);
        customJmonkeyScreen.setWidth(selectedScreenMode.getWidth());
        customJmonkeyScreen.setHeight(selectedScreenMode.getHeight());
        customJmonkeyScreen.setFrequency(selectedScreenMode.getRefreshRate());
    }

    private void loadIcons() {
        try {
            Class<VirtualFactory> myClass = VirtualFactory.class;
            customJmonkeyScreen.setIcons(new BufferedImage[]{
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_16.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_24.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_32.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_48.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_64.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/icon_72.png"))
            });
        }
        catch (NullPointerException e) {
            throw new NullPointerException("customJmonkeyScreen was not initialized - " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Unable to load program icons - " + e.getMessage());
        }
    }
    
    public AppSettings getJmonkeyScreen() { 
        return customJmonkeyScreen; 
    }
}