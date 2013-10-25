package edu.uprm.gaming.prototype;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;
import edu.uprm.gaming.GameEngine;

/**
 * Chooses between the game or the editor.
 * @author Abner Coimbre
 */
public class StartMenuAppState extends AbstractAppState {
    private SimpleApplication app;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private InputManager inputManager;
    private Node guiNode;
    
    private Picture startMenuOption;
    
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String ENTER = "enter";
    
    private static final Trigger TRIGGER_UP = new KeyTrigger(KeyInput.KEY_UP);
    private static final Trigger TRIGGER_DOWN = new KeyTrigger(KeyInput.KEY_DOWN);
    private static final Trigger TRIGGER_ENTER = new KeyTrigger(KeyInput.KEY_RETURN);
    
    private AudioNode audioBackground;

    /**
     * Initializes start screen. Called automatically when this state is attached.
     * @param stateManager
     * @param app 
     */
    @Override
    public void initialize(AppStateManager manager, Application application) {
        /* Get application-wide access */
        app = (SimpleApplication) application;
        stateManager = manager;
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        
        /* Gain easy access to gui node and set the initial image */
        guiNode = app.getGuiNode();
        startMenuOption = new Picture("Play Game");
        startMenuOption.setImage(assetManager, "Interface/startMenuGame.png", false);
        startMenuOption.setWidth(1280);
        startMenuOption.setHeight(720);
        guiNode.attachChild(startMenuOption);
        
        /* Add some nice background music */
        /*
        audioBackground = new AudioNode(assetManager, "Sounds/startMenuTheme.wav", false);
        audioBackground.setPositional(false);
        audioBackground.setLooping(true);
        audioBackground.play();*/
        
        initControls();
    }
    
    /**
     * Initializes keyboard controls.
     */
    private void initControls() {
        inputManager.addMapping(UP, TRIGGER_UP, new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(DOWN, TRIGGER_DOWN, new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping(ENTER, TRIGGER_ENTER, new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener, UP, DOWN, ENTER);
    }
    
    /**
     * Handles player input.
     */
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            /* When up arrow key is pressed */
            if (name.equals(UP) && !keyPressed) {
                String oldOption = startMenuOption.getName();

                if (oldOption.equals("Play Game")) {
                    return; // do nothing. Player is already at the top option
                } else {
                   guiNode.detachChildNamed(oldOption); // clear current highlighted option 
                }
                
                /* Verify which option should now be highlighted */
                switch (oldOption) {
                    case "Exit Factory":
                        startMenuOption.setImage(assetManager, "Interface/startMenuEditor.png", false);
                        startMenuOption.setName("World Editor");
                        break;
                    case "World Editor":
                        startMenuOption.setImage(assetManager, "Interface/startMenuGame.png", false);
                        startMenuOption.setName("Play Game");
                        break;
                    default:
                        break;
                }

                /* Highlight new option */
                guiNode.attachChild(startMenuOption);
                AudioNode moveSound = new AudioNode(assetManager, "Sounds/move.wav", false);
                moveSound.setPositional(false);
                moveSound.play();
                return;
            }
            
            if (name.equals(DOWN) && !keyPressed) {
                /* Clear current highlighted option */
                String oldOption = startMenuOption.getName();
                guiNode.detachChildNamed(oldOption);
                
                /* Verify which option should now be highlighted */
                switch (oldOption) {
                    case "Play Game":
                        startMenuOption.setImage(assetManager, "Interface/startMenuEditor.png", false);
                        startMenuOption.setName("World Editor");
                        break;
                    case "World Editor":
                        startMenuOption.setImage(assetManager, "Interface/startMenuExit.png", false);
                        startMenuOption.setName("Exit Factory");
                        break;
                    default:
                        break;
                }

                /* Highlight new option */
                guiNode.attachChild(startMenuOption);
                AudioNode moveSound = new AudioNode(assetManager, "Sounds/move.wav", false);
                moveSound.setPositional(false);
                moveSound.play();
                return;
            }
            
            if (name.equals(ENTER) && !keyPressed) {
                /* Stop theme */
                //audioBackground.stop();
                
                /* Play starting sound */
                AudioNode startSound = new AudioNode(app.getAssetManager(), "Sounds/start.wav", false);
                startSound.setPositional(false);
                startSound.play();
                
                /* Verify which option was selected */
                switch (startMenuOption.getName()) {
                    case "Play Game":
                        startGame();
                        break;
                    case "World Editor":
                        startEditor();
                        break;
                    case "Exit Factory":
                        System.exit(0);
                    default:
                        break;
                }
            }
        }
    };
    
    /**
     * Starts the game and detaches this state.
     */
    private void startGame() {
        stateManager.attach(new GameEngine());
        stateManager.detach(this);
    }
    
    /**
     * Starts the editor and detaches this state.
     */
    private void startEditor() {
        stateManager.attach(new PrototypeAppState());
        stateManager.detach(this);
    }
    
    /**
     * Called automatically after this state is detached.
     */
    @Override
    public void cleanup() {
        super.cleanup();
        guiNode.detachChildNamed(startMenuOption.getName());
        String[] mappings = {UP, DOWN, ENTER};
        for (int i = 0; i < mappings.length; i++) {
            inputManager.deleteMapping(mappings[i]);
        }
    }
}