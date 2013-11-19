package edu.uprm.gaming;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import edu.uprm.gaming.utils.Params;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Virtual Factory 2.0
 */
public class InputManager extends SimpleApplication {
    public static void main(String[] args) {
        /* Configure settings */
        AppSettings settings = new AppSettings(true);
        try {
            Class<InputManager> myClass = InputManager.class;
            settings.setIcons(new BufferedImage[]{
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
        settings.setResolution(1280, 720);
        settings.setTitle("Virtual Factory 2.0 (Alpha) - ININ-UPRM - NSF #0835990");// (" + Params.selectDatabase + ")");
        Params.renderer = settings.getRenderer();
        settings.setRenderer(AppSettings.LWJGL_OPENGL2);
        settings.setSettingsDialogImage("Interface/icon.png");
        
        DisplayMode mode = getDisplayParams();
        if (mode != null)
            setDisplaySettings(settings, mode);

        InputManager app = new InputManager();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.setPauseOnLostFocus(false);
        app.start();
       
    }
    
    /**
     * Enables the game engine. Called automatically after app.start().
     */
    @Override
    public void simpleInitApp() {
        /* Turn off default HUD */
        setDisplayFps(false);
        setDisplayStatView(false);
        stateManager.attach(new GameEngine()); // start the game
    }
    
    @Override
    public void destroy() {
        System.exit(0);
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
}