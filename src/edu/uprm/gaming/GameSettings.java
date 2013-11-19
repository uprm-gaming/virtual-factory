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

public class GameSettings {
    private AppSettings customAppSettings;
    
    public static AppSettings generate() {
        GameSettings s = new GameSettings();
        return s.getAppSettings();
    }
    
    private GameSettings() {
        customAppSettings = new AppSettings(true);
        loadIcons();
        loadDefaultSettings();
        tryFullScreen();
    }

    private void loadIcons() {
        try {
            Class<InputManager> myClass = InputManager.class;
            customAppSettings.setIcons(new BufferedImage[]{
                ImageIO.read(myClass.getResourceAsStream("/Textures/factory_16.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/factory_24.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/factory_32.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/factory_48.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/factory_64.png")),
                ImageIO.read(myClass.getResourceAsStream("/Textures/factory_72.png"))
            });
        }
        catch (IOException e) {
            System.out.println("Unable to load program icons - " + e.getMessage());
        }
    }
    
    private void loadDefaultSettings() {
        customAppSettings.setResolution(1280, 720);
        customAppSettings.setTitle("Virtual Factory 2.0 (Alpha) - ININ-UPRM - NSF #0835990");// (" + Params.selectDatabase + ")");
        Params.renderer = customAppSettings.getRenderer();
        customAppSettings.setRenderer(AppSettings.LWJGL_OPENGL2);
        customAppSettings.setSettingsDialogImage("Interface/icon.png");
    }
    
    private void tryFullScreen() {
        DisplayMode mode = getDisplayParams();
        if (mode != null)
            setDisplaySettings(customAppSettings, mode);
    }
    
    //returns null if fullscreen is not available for the display
    private static DisplayMode getDisplayParams() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] displayModes = device.getDisplayModes();
        ArrayList<DisplayMode> tempModes = new ArrayList<>();
        DisplayMode selectedMode;
        
        for (DisplayMode mode: displayModes) {
            if (!device.isFullScreenSupported())
                return null;
            if (mode.getWidth() == 1280)
                tempModes.add(mode);
        }
        
        if (tempModes.isEmpty())
            return null;
        else {
            selectedMode = tempModes.get(0);
            for (int i = 0; i < tempModes.size(); i++) {
                if (tempModes.size() == i + 1)
                    break;
                
                DisplayMode temp = tempModes.get(i+1);
                if (Math.abs(selectedMode.getHeight() - 720) > Math.abs(temp.getHeight() - 720))
                    selectedMode = temp;
            }
        }
        
        return selectedMode;
    }
    
    private static void setDisplaySettings(AppSettings settings, DisplayMode mode) {
        settings.setFullscreen(true);
        settings.setWidth(mode.getWidth());
        settings.setHeight(mode.getHeight());
        settings.setFrequency(mode.getRefreshRate());
    }
    
    public AppSettings getAppSettings() { 
        return customAppSettings; 
    }
}