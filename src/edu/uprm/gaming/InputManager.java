package edu.uprm.gaming;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import edu.uprm.gaming.utils.Params;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        System.out.println("\n\n\n" + Params.renderer + "\n\n\n");
        settings.setRenderer(AppSettings.LWJGL_OPENGL2);
        settings.setFullscreen(false);

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
}