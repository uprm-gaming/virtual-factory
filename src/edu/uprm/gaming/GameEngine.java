package edu.uprm.gaming;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Grid;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.ElementBuilder.Align;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.StyleBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioButtonBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioGroupBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.controls.window.builder.WindowBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import edu.uprm.gaming.entity.E_Bucket;
import edu.uprm.gaming.entity.E_Game;
import edu.uprm.gaming.entity.E_Machine;
import edu.uprm.gaming.entity.E_Operator;
import edu.uprm.gaming.entity.E_Station;
import edu.uprm.gaming.entity.E_Terrain;
import edu.uprm.gaming.entity.E_TerrainReserved;
import edu.uprm.gaming.graphic.DispOperatorMachineMovingTo;
import edu.uprm.gaming.graphic.DispOperatorWalksTo;
import edu.uprm.gaming.graphic.TerrainMap;
import edu.uprm.gaming.graphic.nifty.CommonBuilders;
import edu.uprm.gaming.graphic.nifty.DialogPanelControlDefinition;
import edu.uprm.gaming.graphic.nifty.ForgotYourPasswordDisplay;
import edu.uprm.gaming.graphic.nifty.GeneralScreenController;
import edu.uprm.gaming.graphic.nifty.InitialMenuDisplay;
import edu.uprm.gaming.graphic.nifty.LoadGameMenuDisplay;
import edu.uprm.gaming.graphic.nifty.MainMenuDisplay;
import edu.uprm.gaming.graphic.nifty.MenuScreenController;
import edu.uprm.gaming.graphic.nifty.NewGame1MenuDisplay;
import edu.uprm.gaming.graphic.nifty.NewUserMenuDisplay;
import edu.uprm.gaming.graphic.nifty.OptionsMenuDisplay;
import edu.uprm.gaming.graphic.nifty.ProgressBarController;
import edu.uprm.gaming.graphic.nifty.controls.ActivityControl;
import edu.uprm.gaming.graphic.nifty.controls.AssignOperatorControl;
import edu.uprm.gaming.graphic.nifty.controls.CharactersControl;
import edu.uprm.gaming.graphic.nifty.controls.DashboardControl;
import edu.uprm.gaming.graphic.nifty.controls.FlowChartControl;
import edu.uprm.gaming.graphic.nifty.controls.GameLogControl;
import edu.uprm.gaming.graphic.nifty.controls.GameSetupControl;
import edu.uprm.gaming.graphic.nifty.controls.MachineControl;
import edu.uprm.gaming.graphic.nifty.controls.MainMultipleControls;
import edu.uprm.gaming.graphic.nifty.controls.OperatorControl;
import edu.uprm.gaming.graphic.nifty.controls.OrderControl;
import edu.uprm.gaming.graphic.nifty.controls.OverallControl;
import edu.uprm.gaming.graphic.nifty.controls.PartControl;
import edu.uprm.gaming.graphic.nifty.controls.PriorityControl;
import edu.uprm.gaming.graphic.nifty.controls.StorageCostControl;
import edu.uprm.gaming.graphic.nifty.controls.StorageStationControl;
import edu.uprm.gaming.graphic.nifty.controls.SupplierControl;
import edu.uprm.gaming.graphic.nifty.controls.UnitLoadControl;
import edu.uprm.gaming.pathfinding.Path;
import edu.uprm.gaming.pathfinding.Path.Step;
import edu.uprm.gaming.simpack.LinkedListFutureEventList;
import edu.uprm.gaming.simpack.Sim;
import edu.uprm.gaming.simpack.SimEvent;
import edu.uprm.gaming.simpack.TOKEN;
import edu.uprm.gaming.strategy.ManageEvents;
import edu.uprm.gaming.threads.CloseGame;
import edu.uprm.gaming.threads.StationAnimation;
import edu.uprm.gaming.threads.UpdateSlotsStorage;
import edu.uprm.gaming.utils.Colors;
import edu.uprm.gaming.utils.GameSounds;
import edu.uprm.gaming.utils.GameType;
import edu.uprm.gaming.utils.ObjectState;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.Params;
import edu.uprm.gaming.utils.Sounds;
import edu.uprm.gaming.utils.StationType;
import edu.uprm.gaming.utils.Status;
import edu.uprm.gaming.utils.TypeElements;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Game Engine 2.0
 * @author Abner Coimbre
 * @author Jose Martinez
 * [Note: Based on David Bengoa's original game engine for Virtual Factory 1.0]
 */
public class GameEngine extends AbstractAppState implements AnimEventListener, PhysicsCollisionListener {
    
    protected BulletAppState bulletAppState;
    /* Terrain */
    RigidBodyControl terrainPhysicsNode;
    /* Materials */
    Material matTerrain;
    Material matWire;
    Material matCharacter;
    /* Character */
    ArrayList<CharacterControl> arrCharacter;
    ArrayList<CharacterControl> arrCharacter2;
    ArrayList<CharacterControl> arrCharacter3;
    ArrayList<CharacterControl> arrCharacter4;
    Node model;
    float angle;
    /* Camera */
    boolean left = false, right = false, up = false, down = false;
    ChaseCamera chaseCam;
    /* Animation */
    AnimControl animationControl;
    ArrayList<AnimControl> arrAnimationControl;
    float cont = 0;
    AnimChannel animationChannel_Disp;
    CharacterControl character_Disp;
    private GameData gameData;
    private ManageEvents manageEvents;
    private boolean executeGame;
    private MenuScreenController menuScreenC;
    private GeneralScreenController generalScreenController;
    private Spatial floor;
    private RigidBodyControl floorRigid;
    private RigidBodyControl bucketRigid;
    private Box bucketBox;
    private Material bucketMaterial;
    private RigidBodyControl stationRigid;
    private Box stationBox;
    private Material stationMaterial;
    private RigidBodyControl partRigid;
    private Box partBox;
    private Material partMaterial;
    private ArrayList<DispOperatorWalksTo> arrOperatorsWalksTo;
    private ArrayList<DispOperatorMachineMovingTo> arrOperatorsMachinesMovingTo;
    private ArrayList<StationAnimation> arrStationAnimations;
    private TerrainMap terrainMap;
    private NiftyJmeDisplay niftyDisplay;
    private Nifty nifty;
    private Status currentSystemStatus;
    private double currentSystemTime;
    private long currentTempSystemTime;
    private long initialRealSystemTime;
    private long currentIdleSystemTime;
    private long currentWindowRefreshSystemTime;
    private int timeToUpdateSlots;
    private Node shootables;
    private int initialGameId;
    private Align operator_label = Align.Right;
    private Align operator_value = Align.Left;
    private Align machine_label = Align.Right;
    private Align machine_value = Align.Left;
    private Align station_label = Align.Right;
    private Align station_value = Align.Left;
    private Align part_label = Align.Right;
    private Align part_value = Align.Left;
    private Align activity_label = Align.Right;
    private Align activity_value = Align.Left;
    private Align overall_label = Align.Left;
    private Align overall_value = Align.Right;
    private Align supplier_label = Align.Right;
    private Align supplier_value = Align.Left;
    private Align order_label = Align.Left;
    private Align order_value = Align.Left;
    private static String panelBackgroundImage = "Interface/panel2.png";//Principal/nifty-panel-simple.png";
    private static String buttonBackgroundImage = "Textures/button-green.png";
    private static String popupBackgroundImage = "Interface/panelpopup2.png";//"Textures/background_gray.png";
    private ArrayList<JmeCursor> cursors;
    private CloseGame closeGame;
    private Geometry showSpotObject;
    private GameSounds gameSounds;
    private ArrayList<Pair<GameSounds, Sounds>> arrGameSounds;
    private int minDashboardPositionX = 1260;
    private int minDashboardPositionY = 30;
    private int dashboardWidth = 535;
    private int dashboardHeight = 430;
    private boolean isDashboardVisible = false;
    private boolean showHideDashboard = false;
    private long currentDashboardTime;
    private boolean isMouseToggled = false;

    public SimpleApplication app;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private Node rootNode;
    private InputManager inputManager;
    private FlyByCamera flyCam;
    private Camera cam;
    private ViewPort viewPort;
    private ViewPort guiViewPort;
    private CharacterControl player;
    
    private boolean forward = false;
    private boolean backward = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean isPlayerDisabled = false;
    
    private float playerSpeed = 1.3f;
    
    private Vector3f camDir;
    private Vector3f camLeft;
    
    private Vector3f walkDirection = new Vector3f(0, 0, 0);
    
    private boolean isGameStarted = false;
    private AudioNode footstep;
    private AudioNode[] jumpSounds;
  
    @Override
    public void initialize(AppStateManager manager, Application application) {
        app = (SimpleApplication) application;
        stateManager = manager;
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        flyCam = app.getFlyByCamera();
        cam = app.getCamera();
        viewPort = app.getViewPort();
        guiViewPort = app.getGuiViewPort();
        rootNode = app.getRootNode();
        
        Logger.getLogger("com.jme3").setLevel(Level.WARNING);
        Logger.getLogger("de.lessvoid").setLevel(Level.WARNING);
        Logger root = Logger.getLogger("");
        
        Handler[] handlers = root.getHandlers();
        for (int i = 0; i < handlers.length; i++) {
            if (handlers[i] instanceof ConsoleHandler) {
                ((ConsoleHandler) handlers[i]).setLevel(Level.WARNING);
            }
        }
        
        bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
        
        //createLight();
        setupFilter();
        initKeys();
        initSoundEffects();
        
        //***********************************************
        gameData = GameData.getInstance();
        gameData.setGameEngine(this);
        gameSounds = new GameSounds(assetManager);
        arrGameSounds = new ArrayList<>();
        //screens previous the game - START NIFTY
        executeGame = false;
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, app.getAudioRenderer(), guiViewPort);
        nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);
        nifty.loadStyleFile("Interface/NiftyJars/nifty-style-black/nifty-default-styles.xml");//Interface/NiftyJars/nifty-style-black/
        nifty.loadControlFile("nifty-default-controls.xml");//Interface/NiftyJars/nifty-default-controls/
        nifty.registerSound("intro", "Interface/sound/19546__tobi123__Gong_mf2.wav");
        nifty.registerMusic("credits", "Interface/sound/Loveshadow_-_Almost_Given_Up.ogg");
        //nifty.setDebugOptionPanelColors(true);
        // register the dialog and credits controls
        registerStyles(nifty);
        registerCreditsPopup(nifty);
        registerQuitPopup(nifty);
        registerGameOverPopup(nifty);
        registerGameUpdatingPopup(nifty);
        registerGameClosingPopup(nifty);
        registerWarningNewGamePopup(nifty);
        DialogPanelControlDefinition.register(nifty);
        InitialMenuDisplay.register(nifty);
        ForgotYourPasswordDisplay.register(nifty);
        MainMenuDisplay.register(nifty);
        NewUserMenuDisplay.register(nifty);
        NewGame1MenuDisplay.register(nifty);
        LoadGameMenuDisplay.register(nifty);
        OptionsMenuDisplay.register(nifty);

        createIntroScreen(nifty);
        createMenuScreen(nifty);
        createLayerScreen(nifty);
        if (!Params.DEBUG_ON) {
            nifty.gotoScreen("start");
        }
        
        menuScreenC = (MenuScreenController) nifty.getScreen("initialMenu").getScreenController();
        menuScreenC.setGameEngine(this);
        generalScreenController = (GeneralScreenController) nifty.getScreen("layerScreen").getScreenController();
        generalScreenController.setGameEngine(this);
        inputManager.setCursorVisible(true);
        cursors = new ArrayList<>();
        cursors.add((JmeCursor) assetManager.loadAsset("Interface/Cursor/cursorWood.ico"));//pointer2.cur"));//cursor.cur"));//PliersLeft.cur"));//
        cursors.add((JmeCursor) assetManager.loadAsset("Interface/Cursor/busy.ani"));
        inputManager.setMouseCursor(cursors.get(0));
        flyCam.setDragToRotate(true);
        //END NIFTY
        inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));
        inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);
        System.out.println("--------End-of-SimpleAppInit-------");
        if (Params.DEBUG_ON) {
            nifty.gotoScreen("initialMenu");
        }
    }

    public void updateCursorIcon(int newCursorValue) {
        inputManager.setMouseCursor(cursors.get(newCursorValue));
    }

    public void playGame(E_Game tempGame, boolean newGame) {
        setupChaseCamera();
        if (newGame) {
            this.getArrGameSounds().clear();
        }
        inputManager.setMouseCursor(cursors.get(1));
        terrainMap = new TerrainMap();
        initialGameId = tempGame.getIdGame();
        //clean memory after new reload
        rootNode.detachAllChildren();
        bulletAppState.getPhysicsSpace().destroy();
        bulletAppState.getPhysicsSpace().create();
        System.gc();
        //end clean memory
        if (newGame) {
            //Chris:fixes background music issue if starts a
            //new game after exiting an ongoing game instance
            this.gameSounds.stopSound(Sounds.Background);
            //endFix

            gameData.createGame(tempGame);
        } else {
            gameData.loadGame(tempGame);
        }
        getGeneralScreenController().updateStartScreen();
        getGeneralScreenController().setTimeFactor((float) gameData.getCurrentGame().getTimeFactor());
        getGeneralScreenController().setGameNamePrincipal(gameData.getCurrentGame().getGameName());
        getGeneralScreenController().setNextDueDate("-");
        getGeneralScreenController().setNextPurchaseDueDate("-");
        LoadElementsToDisplay(newGame ? GameType.New : GameType.Load);
        manageEvents = new ManageEvents(this, gameData);
        //initialize Simpack
        currentSystemStatus = Status.Busy;
        currentSystemTime = gameData.getCurrentGame().getCurrentTime();
        currentTempSystemTime = System.currentTimeMillis();// 1000.0;
        Sim.init(currentSystemTime, new LinkedListFutureEventList());
        Sim.schedule(new SimEvent(Sim.time() + getGeneralScreenController().getTimeFactor(), Params.startEvent));
        executeGame = true;
        //load extra data
        gameData.updateTimeOrders();
        //show static windows
        nifty.getScreen("layerScreen").findElementByName("winOvC_Element").getControl(OverallControl.class).loadWindowControl(this, 0, null);
        nifty.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderControl.class).loadWindowControl(this, 0, null);
        nifty.getScreen("layerScreen").findElementByName("winGLC_Element").getControl(GameLogControl.class).loadWindowControl(this, 0, null);
        nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupControl.class).loadWindowControl(this, -1, null);// -1 because we dont want it to be visible
        nifty.getScreen("layerScreen").findElementByName("winFCC_Element").getControl(FlowChartControl.class).loadWindowControl(this, -1, null);
        nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardControl.class).loadWindowControl(this, 0, null);
        //clean lists
        nifty.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderControl.class).cleanOrders();
        nifty.getScreen("layerScreen").findElementByName("winGLC_Element").getControl(GameLogControl.class).cleanMessages();
        nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupControl.class).updateAllStepStatus(false);
        getGeneralScreenController().updateQuantityCurrentMoney(gameData.getCurrentMoney());
        inputManager.setMouseCursor(cursors.get(0));
        getGeneralScreenController().forcePauseGame();
        initialRealSystemTime = System.currentTimeMillis() / 1000;
        currentIdleSystemTime = System.currentTimeMillis() / 1000;
        currentWindowRefreshSystemTime = System.currentTimeMillis() / 1000;
        currentDashboardTime = 0;
        timeToUpdateSlots = gameData.getCurrentTimeWithFactor();
        getGeneralScreenController().hideCurrentControlsWindow();
        getGeneralScreenController().showHideDynamicButtons(0);
        getGeneralScreenController().showHideDynamicSubLevelButtons(0);
        
        
        nifty.getScreen("layerScreen").findElementByName("winOvC_Element").getControl(OverallControl.class).HideWindow();
        nifty.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderControl.class).HideWindow();
        nifty.getScreen("layerScreen").findElementByName("winGLC_Element").getControl(GameLogControl.class).showHide();
          
    }

    private void LoadElementsToDisplay(GameType gameType) {
        createShootable();
        createTerrain();
        createSkyDome();
        //*******************        
        arrOperatorsWalksTo = new ArrayList<>();
        arrOperatorsMachinesMovingTo = new ArrayList<>();
        arrStationAnimations = new ArrayList<>();
        Iterable<E_Station> tempStation = gameData.getMapUserStation().values();
        for (E_Station station : tempStation) {
            createStations(station);
        }
        Iterable<E_Operator> tempOperator = gameData.getMapUserOperator().values();
        for (E_Operator operator : tempOperator) {
            createOperators(operator, gameType);
            operator.updateInactiveBrokenOperator();
            if (operator.getState().equals(ObjectState.Inactive)) {
                operator.showHideInactiveOperator(true);
            }
        }
        Iterable<E_Machine> tempMachine = gameData.getMapUserMachine().values();
        for (E_Machine machine : tempMachine) {
            createMachines(machine, gameType);
            machine.updateInactiveBrokenMachine();
            if (machine.getMachineState().equals(ObjectState.Inactive)) {
                machine.showHideInactiveMachine(true);
            }
        }
        createShowSpotObject();
    }
    
    /**
     * Creates the beautiful sky for the game.
     */
    private void createSkyBox() {
        Spatial sky = SkyFactory.createSky(assetManager, 
                             assetManager.loadTexture("Textures/Skybox/skyLeft.jpg"), 
                             assetManager.loadTexture("Textures/Skybox/skyRight.jpg"), 
                             assetManager.loadTexture("Textures/Skybox/skyFront.jpg"), 
                             assetManager.loadTexture("Textures/Skybox/skyBack.jpg"), 
                             assetManager.loadTexture("Textures/Skybox/skyTop.jpg"), 
                             assetManager.loadTexture("Textures/Skybox/skyDown.jpg"));
    }
    
    private void createSkyDome() {
        SkyDomeControl skyDome = new SkyDomeControl(assetManager, app.getCamera(),
                "ShaderBlow/Models/SkyDome/SkyDome.j3o",
                "ShaderBlow/Textures/SkyDome/SkyNight_L.png",
                "ShaderBlow/Textures/SkyDome/Moon_L.png",
                "ShaderBlow/Textures/SkyDome/Clouds_L.png",
                "ShaderBlow/Textures/SkyDome/Fog_Alpha.png");
        Node sky = new Node();
        sky.setQueueBucket(RenderQueue.Bucket.Sky);
        sky.addControl(skyDome);
        sky.setCullHint(Spatial.CullHint.Never);

// Either add a reference to the control for the existing JME fog filter or use the one I posted…
// But… REMEMBER!  If you use JME’s… the sky dome will have fog rendered over it.
// Sorta pointless at that point
//        FogFilter fog = new FogFilter(ColorRGBA.Blue, 0.5f, 10f);
//        skyDome.setFogFilter(fog, viewPort);

// Set some fog colors… or not (defaults are cool)
        skyDome.setFogColor(ColorRGBA.Pink);
        //skyDome.setFogColor(new ColorRGBA(255f, 128f, 131f, 1f));
       
        skyDome.setFogNightColor(new ColorRGBA(0.5f, 0.5f, 1f, 1f));
        skyDome.setDaySkyColor(new ColorRGBA(0.5f, 0.5f, 0.9f, 1f));

// Enable the control to modify the fog filter
        skyDome.setControlFog(true);

// Add the directional light you use for sun… or not
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        sun.setColor(new ColorRGBA(1, 1, 1, 1));
        rootNode.addLight(sun);
        skyDome.setSun(sun);
        
        /*
        AmbientLight al = new AmbientLight();
        al.setColor(new ColorRGBA(0.7f, 0.7f, 1f, 1.0f));
        rootNode.addLight(al);*/

// Set some sunlight day/night colors… or not
        skyDome.setSunDayLight(new ColorRGBA(1, 1, 1, 1));
        skyDome.setSunNightLight(new ColorRGBA(0.5f, 0.5f, 0.9f, 1f));

// Enable the control to modify your sunlight
        skyDome.setControlSun(true);

// Enable the control
        skyDome.setEnabled(true);

// Add the skydome to the root… or where ever
        rootNode.attachChild(sky);
        skyDome.cycleNightToDay();
        skyDome.setDayNightTransitionSpeed(1.2f);
    }

    @Override
    public void update(float tpf) {
        updatePlayerPosition();
        simpleUpdateLocal(); // Legacy code
    }
    
    public void updatePlayerPosition() {
        camDir = app.getCamera().getDirection().clone().multLocal(playerSpeed);
        camLeft = app.getCamera().getLeft().clone().multLocal(playerSpeed);
        
        walkDirection.set(0, 0, 0); // reset walkDirection vector
        if (forward) { walkDirection.addLocal(camDir); }
        if (backward) { walkDirection.addLocal(camDir.negate()); }
        if (moveLeft) { walkDirection.addLocal(camLeft); }
        if (moveRight) { walkDirection.addLocal(camLeft.negate()); }
        
        if (executeGame) {
            player.setWalkDirection(walkDirection); // walk!
            app.getCamera().setLocation(player.getPhysicsLocation());
            if (flyCam.isDragToRotate()) flyCam.setDragToRotate(false);
            if (!inputManager.isCursorVisible()) inputManager.setCursorVisible(true);
        } 
    }

    public void simpleUpdateLocal() {
        //Added by Chris
        if (!this.getGeneralScreenController().getPauseStatus() && gameSounds.machineSoundPlaying()) {
            this.gameSounds.pauseSound(Sounds.MachineWorking);
        }
        inputManager.deleteTrigger("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        if (!executeGame) {
            return;
        }
        if (currentSystemStatus.equals(Status.Busy)) {
            currentSystemTime += (double) ((System.currentTimeMillis() - currentTempSystemTime) / 1000.0);
        }
        updateGraphicElementsArray();
        if (gameData.getCurrentTimeWithFactor() - timeToUpdateSlots >= Params.timeToUpdateSlotsMinutes) {
            timeToUpdateSlots = gameData.getCurrentTimeWithFactor();
            executeThreadToUpdateSlots();
        }
        if ((System.currentTimeMillis() / 1000) - initialRealSystemTime >= Params.timeToSaveLogSeconds) {
            gameData.updatePlayerLog();
            initialRealSystemTime = System.currentTimeMillis() / 1000;
        }
        if ((System.currentTimeMillis() / 1000) - currentIdleSystemTime >= Params.timeToCloseGameSeconds) {
            showPopupAttemptingToCloseGame();
            updateLastActivitySystemTime();
        }
        if ((System.currentTimeMillis() / 1000) - currentWindowRefreshSystemTime >= gameData.getCurrentGame().getTimeFactor() * Params.timeUnitsToRefresh) {
            gameData.updateControlsWindows();
            currentWindowRefreshSystemTime = System.currentTimeMillis() / 1000;
        }
        manageDashboard();
        currentTempSystemTime = System.currentTimeMillis();
        gameData.getCurrentGame().setCurrentTime(currentSystemTime);
        getGeneralScreenController().setCurrentGameTime(gameData.getCurrentTimeGame());
        SimEvent nextEvent = Sim.next_event(currentSystemTime, Sim.Mode.SYNC);
        while (nextEvent != null) {
            if (nextEvent.getId() == Params.startEvent) {
                gameData.manageMachineStates();
                manageEvents.executeEvent();
                //it happens each TIME-UNIT
                Sim.schedule(new SimEvent(Sim.time() + getGeneralScreenController().getTimeFactor(), Params.startEvent));
                nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardControl.class).updateQuantityPeopleStatus(gameData.getNoUserOperator(Status.Busy), gameData.getNoUserOperator(Status.Idle));
            } else {
                manageEvents.setStrategy(nextEvent.getToken());
                manageEvents.releaseResourcesEvent();
            }
            nextEvent = Sim.next_event(currentSystemTime, Sim.Mode.SYNC);
            getGeneralScreenController().updateQuantityCurrentMoney(gameData.getCurrentMoney());
        }
    }

    private void manageDashboard() {
        nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardControl.class).updateData();
//        if (isDashboardVisible) {
//            if (!isDashboardVisiblePosition()) {
//                if (currentDashboardTime == 0) {
//                    currentDashboardTime = System.currentTimeMillis() / 1000;
//                }
//                if ((System.currentTimeMillis() / 1000) - currentDashboardTime > Params.timeToHideDashboard) {
//
//                    nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").hide();
//
//                    currentDashboardTime = 0;
//                    isDashboardVisible = false;
//                }
//            } else {
//                currentDashboardTime = 0;
//            }
//        } else {
//            if (isDashboardInvisiblePosition()) {
//                if (currentDashboardTime == 0) {
//                    currentDashboardTime = System.currentTimeMillis() / 1000;
//                }
//                if ((System.currentTimeMillis() / 1000) - currentDashboardTime > Params.timeToShowDashboard) {
//             
//                    nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").show();
//
//                    currentDashboardTime = 0;
//                    isDashboardVisible = true;
//                }
//            } else {
//                currentDashboardTime = 0;
//            }

        if (showHideDashboard) {
            if (isDashboardVisible) {
                nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").hide();
                isDashboardVisible = false;
            } else {
                nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").show();
                isDashboardVisible = true;

            }
            showHideDashboard = false;
        }
    }

//    private boolean isDashboardVisiblePosition() {
//        Vector2f mousePosition = inputManager.getCursorPosition();
//        float mouseX = mousePosition.getX();
//        float mouseY = getGuiViewPort().getCamera().getHeight() - mousePosition.getY();
//        if ((minDashboardPositionX - dashboardWidth) <= mouseX && minDashboardPositionY <= mouseY && mouseY <= (minDashboardPositionY + dashboardHeight)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private boolean isDashboardInvisiblePosition() {
//        Vector2f mousePosition = inputManager.getCursorPosition();
//        float mouseX = mousePosition.getX();
//        float mouseY = getGuiViewPort().getCamera().getHeight() - mousePosition.getY();
//        if (minDashboardPositionX <= mouseX && minDashboardPositionY <= mouseY && mouseY <= (minDashboardPositionY + dashboardHeight)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    public GameSounds getGameSounds() {
        return gameSounds;
    }

    private void executeThreadToUpdateSlots() {
        UpdateSlotsStorage updateSlotsStorage = new UpdateSlotsStorage();
        updateSlotsStorage.setMapUserStorageStation(gameData.getMapUserStorageStation());
        updateSlotsStorage.setGameEngine(this);
        updateSlotsStorage.start();
    }

    public void updateLastActivitySystemTime() {
        currentIdleSystemTime = System.currentTimeMillis() / 1000;
    }

    private void showPopupAttemptingToCloseGame() {
        getGeneralScreenController().pauseGame();
        Element exitPopup = nifty.createPopup("gameClosing");
        nifty.showPopup(nifty.getCurrentScreen(), exitPopup.getId(), null);
        nifty.getCurrentScreen().processAddAndRemoveLayerElements();
        closeGame = new CloseGame();
        closeGame.setGameEngine(this);
        closeGame.setScreen(nifty.getCurrentScreen());
        closeGame.setNifty(nifty);
        closeGame.setExitPopup(exitPopup);
        closeGame.start();
    }

    public void stopClosingGame() {
        closeGame.setContinueGame(true);
    }

    public void updateEventsTime() {
        if (Sim.getFutureEventList() == null) {
            return;
        }
        Iterator<SimEvent> arrEvents = (Iterator) Sim.getFutureEventList().getQueue().iterator();
        while (arrEvents.hasNext()) {
            SimEvent tempEvent = arrEvents.next();
            TOKEN tempToken = tempEvent.getToken();
            double oldTime = tempEvent.getTime();
            //System.out.println("UPDATE: token:" + tempToken + " attribute0:" + tempToken.getAttribute(0) + " - attribute1:" + tempToken.getAttribute(1) + " - tempEvent:" + tempEvent.getId());
            if (tempToken.getAttribute(1) != 0) {
                tempEvent.setTime((oldTime - currentSystemTime) * getGeneralScreenController().getTimeFactor() / tempToken.getAttribute(1) + currentSystemTime);
//                System.out.println("CHANGED TIME - attritube0:" + tempToken.getAttribute(0) + " - Time:" + currentSystemTime + " - EndTime:" + tempEvent.getTime() + " - OldEndTime:" + oldTime + " - OldFactorTime:" + tempToken.getAttribute(1) + " - NewFactorTime:" + getGeneralScreenController().getTimeFactor());
                if (tempToken.getAttribute(2) == Params.simpackPurchase && tempToken.getAttribute(0) == gameData.getCurrentPurchaseId()) {
                    gameData.setNextPurchaseDueDate((int) ((tempEvent.getTime() - currentSystemTime) * getGeneralScreenController().getTimeFactorForSpeed()));
                    getGeneralScreenController().setNextPurchaseDueDate(gameData.convertTimeUnistToHourMinute(gameData.getNextPurchaseDueDate()));
                    //System.out.println("PURCHASE MISSING REAL_TIME:" + (tempEvent.getTime()-currentSystemTime) + " - GAME_TIME:" + (tempEvent.getTime()-currentSystemTime)*getGeneralScreenController().getTimeFactorForSpeed() + " - CLOCK_TIME:" + gameData.getNextPurchaseDueDate());
                }
                tempToken.setAttribute(1, getGeneralScreenController().getTimeFactor());
            }
        }
    }

    private void updateGraphicElementsArray() {
        Iterator<DispOperatorWalksTo> tempElements = arrOperatorsWalksTo.iterator();
        while (tempElements.hasNext()) {
            if (tempElements.next().isToBeRemoved()) {
                tempElements.remove();
            }
        }
        Iterator<DispOperatorMachineMovingTo> tempElements2 = arrOperatorsMachinesMovingTo.iterator();
        while (tempElements2.hasNext()) {
            if (tempElements2.next().isToBeRemoved()) {
                tempElements2.remove();
            }
        }
        //System.out.println("SystemTime:" + currentSystemTime + " - UPDATE GRAPHIC ELEMENT ARRAY");
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
//        if (channel == shootingChannel) {
//            channel.setAnim("Walk");
//        }
    }
    
    /**
     * Make each spatial on the scene cartoonish.
     * @param spatial the spatial to apply the cartoon effect
     */
    public void transformToCartoon(Spatial spatial) {
        /* Use recursion to access every geometry of each node */
        if (spatial instanceof Node) {
            Node n = (Node) spatial;
            
            for (Spatial child : n.getChildren()) {
                transformToCartoon(child);
            }
        } else if (spatial instanceof Geometry) {
            Geometry g = (Geometry) spatial;
            
            String geomName = g.getName();
            if (geomName.equals("Juanito body") || geomName.equals("Barbed wire")) { 
                g.removeFromParent(); 
                return; 
            }
            
            /* We use a LightBlow material definition, particularly good for toon shaders */
            Material newMat = new Material(assetManager, "ShaderBlow/MatDefs/LightBlow.j3md");
            newMat.setTexture("DiffuseMap", g.getMaterial().getTextureParam("DiffuseMap").getTextureValue());
            newMat.setTexture("ColorRamp", assetManager.loadTexture("Textures/toon.png"));
            newMat.setBoolean("Toon", true);
            //newMat.setFloat("EdgeSize", 0.2f);
            //newMat.setBoolean("Fog_Edges", true);
            
            
            /* Enable Professor Zapata's backface culling of the factory */
            if (g.getName().equals("Shop building")) {
                newMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
            }
            
            /* Give the geometry its new toonish material */ 
            g.setMaterial(newMat);
        }
    }

    private void createShootable() {
        shootables = new Node("Shootables");
        rootNode.attachChild(shootables);
    }

    private void createLight() {
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);

        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }

    private void setMaterials() {
        String pathFileMaterial = "Scenes/Materials/";
        //String pathFileTexture = "Scenes/Textures/";
        ((Node) floor).getChild("Asphalt").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphalt.j3m"));
        ((Node) floor).getChild("AsphaltEnd").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphaltEnd.j3m"));
        ((Node) floor).getChild("BackWheels").setMaterial(assetManager.loadMaterial(pathFileMaterial + "backWheels.j3m"));
        ((Node) floor).getChild("BarbedWire").setMaterial(assetManager.loadMaterial(pathFileMaterial + "barbedWireFence.j3m"));
        ((Node) floor).getChild("BathDor").setMaterial(assetManager.loadMaterial(pathFileMaterial + "bathDor.j3m"));
        ((Node) floor).getChild("board01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board01.j3m"));
        ((Node) floor).getChild("board02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board02.j3m"));
        ((Node) floor).getChild("board03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board03.j3m"));
        ((Node) floor).getChild("board04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board04.j3m"));
        ((Node) floor).getChild("board05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board05.j3m"));
        ((Node) floor).getChild("Body").setMaterial(assetManager.loadMaterial(pathFileMaterial + "body.j3m"));
        ((Node) floor).getChild("Box001").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box001.j3m"));
        ((Node) floor).getChild("Box002").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box002.j3m"));
        ((Node) floor).getChild("Box003").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box003.j3m"));
        ((Node) floor).getChild("Box004").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box004.j3m"));
        ((Node) floor).getChild("Box005").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box005.j3m"));
        ((Node) floor).getChild("CabinetL10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg01.j3m"));
        ((Node) floor).getChild("CabinetL11").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg03.j3m"));
        ((Node) floor).getChild("CabinetL12").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg003.j3m"));
        ((Node) floor).getChild("CabinetLe0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg004.j3m"));
        ((Node) floor).getChild("CabinetLe1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg005.j3m"));
        ((Node) floor).getChild("CabinetLe2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg006.j3m"));
        ((Node) floor).getChild("CabinetLe3").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg007.j3m"));
        ((Node) floor).getChild("CabinetLe4").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg008.j3m"));
        ((Node) floor).getChild("CabinetLe5").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg009.j3m"));
        ((Node) floor).getChild("CabinetLe6").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg010.j3m"));
        ((Node) floor).getChild("CabinetLe7").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg011.j3m"));
        ((Node) floor).getChild("CabinetLe8").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut01.j3m"));
        ((Node) floor).getChild("CabinetLe9").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut02.j3m"));
        ((Node) floor).getChild("CabinetLeg").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut02.j3m"));
        ((Node) floor).getChild("CloseVege0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege0.j3m"));
        
        /* Remove trees */
        // -----------
        for (int i = 0; i < 8; i++) {
            ((Node) floor).getChild("CloseVege" + i).removeFromParent();
        }
        ((Node) floor).getChild("CloseVeget").removeFromParent();
        ((Node) floor).getChild("FarVegetat").removeFromParent();
        // -----------
        
        ((Node) floor).getChild("Colm01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "colm01.j3m"));
        ((Node) floor).getChild("Colm02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "colm02.j3m"));
        ((Node) floor).getChild("Compresor").setMaterial(assetManager.loadMaterial(pathFileMaterial + "compresor.j3m"));
        ((Node) floor).getChild("CompWheels").setMaterial(assetManager.loadMaterial(pathFileMaterial + "compWheels.j3m"));
        ((Node) floor).getChild("Contact ce").setMaterial(assetManager.loadMaterial(pathFileMaterial + "contactCE.j3m"));
        ((Node) floor).getChild("Distributi").setMaterial(assetManager.loadMaterial(pathFileMaterial + "distributi.j3m"));
        ((Node) floor).getChild("FLCenterWh").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flCenterWh.j3m"));
        ((Node) floor).getChild("FLHandle").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flHandle.j3m"));
        ((Node) floor).getChild("FLLeftWhee").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flLeftWhee.j3m"));
        ((Node) floor).getChild("FLRightWhe").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flRightWhe.j3m"));
        ((Node) floor).getChild("FLMainFram").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flMainFram.j3m"));
        ((Node) floor).getChild("FordBody").setMaterial(assetManager.loadMaterial(pathFileMaterial + "fordBody.j3m"));
        ((Node) floor).getChild("FrontWheel").setMaterial(assetManager.loadMaterial(pathFileMaterial + "frontWheel.j3m"));
        ((Node) floor).getChild("Ground Edg").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphaltEnd.j3m"));
        ((Node) floor).getChild("House 1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house1.j3m"));
        ((Node) floor).getChild("House 2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house2.j3m"));
        ((Node) floor).getChild("House 3").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house3.j3m"));
        ((Node) floor).getChild("Kart01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "kart01.j3m"));
        ((Node) floor).getChild("Juanito").setMaterial(assetManager.loadMaterial(pathFileMaterial + "juanito.j3m"));
        ((Node) floor).getChild("Lake").setMaterial(assetManager.loadMaterial(pathFileMaterial + "lake.j3m"));
        ((Node) floor).getChild("LakeEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "lakeEdge.j3m"));
        ((Node) floor).getChild("LegBoard00").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard00.j3m"));
        ((Node) floor).getChild("LegBoard01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard01.j3m"));
        ((Node) floor).getChild("LegBoard02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard02.j3m"));
        ((Node) floor).getChild("LegBoard03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard03.j3m"));
        ((Node) floor).getChild("LegBoard04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard04.j3m"));
        ((Node) floor).getChild("LegBoard05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard05.j3m"));
        ((Node) floor).getChild("LegBoard06").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard06.j3m"));
        ((Node) floor).getChild("LegBoard07").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard07.j3m"));
        ((Node) floor).getChild("LegBoard08").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard08.j3m"));
        ((Node) floor).getChild("LegBoard09").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard09.j3m"));
        ((Node) floor).getChild("LegBoard10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard10.j3m"));
        ((Node) floor).getChild("LegBoard11").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard11.j3m"));
        ((Node) floor).getChild("LegBoard12").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard12.j3m"));
        ((Node) floor).getChild("LegBoard13").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard13.j3m"));
        ((Node) floor).getChild("LegBoard14").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard14.j3m"));
        ((Node) floor).getChild("LegBoard15").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard15.j3m"));
        ((Node) floor).getChild("LegBoard16").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard16.j3m"));
        ((Node) floor).getChild("LegBoard17").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard17.j3m"));
        ((Node) floor).getChild("LegBoard18").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard18.j3m"));
        ((Node) floor).getChild("LegBoard19").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard19.j3m"));
        ((Node) floor).getChild("LegBoard20").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard20.j3m"));
        ((Node) floor).getChild("LegBoard21").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard21.j3m"));
        ((Node) floor).getChild("LegBoard22").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard22.j3m"));
        ((Node) floor).getChild("MesaHerram").setMaterial(assetManager.loadMaterial(pathFileMaterial + "mesaHerram.j3m"));
        ((Node) floor).getChild("MetalBarr0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "metalBarr0.j3m"));
        ((Node) floor).getChild("MetalBarre").setMaterial(assetManager.loadMaterial(pathFileMaterial + "metalBarre.j3m"));
        ((Node) floor).getChild("MitterSawB").setMaterial(assetManager.loadMaterial(pathFileMaterial + "mitterSawB.j3m"));
        ((Node) floor).getChild("PaintBooth").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintBooth.j3m"));
        ((Node) floor).getChild("PaintCan01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan01.j3m"));
        ((Node) floor).getChild("PaintCan02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan02.j3m"));
        ((Node) floor).getChild("PaintCan03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan03.j3m"));
        ((Node) floor).getChild("PaintCan04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan04.j3m"));
        ((Node) floor).getChild("PaintCan05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan05.j3m"));
        ((Node) floor).getChild("PaintCan06").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan06.j3m"));
        ((Node) floor).getChild("PaintCan07").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan07.j3m"));
        ((Node) floor).getChild("PaintCan08").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan08.j3m"));
        ((Node) floor).getChild("PaintCan09").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan09.j3m"));
        ((Node) floor).getChild("PaintCan10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan10.j3m"));
        ((Node) floor).getChild("PaintThinn").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintThinn.j3m"));
        ((Node) floor).getChild("Playwood01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood01.j3m"));
        ((Node) floor).getChild("Playwood02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood02.j3m"));
        ((Node) floor).getChild("Playwood04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood04.j3m"));
        ((Node) floor).getChild("Playwood05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood05.j3m"));
        ((Node) floor).getChild("RackPanele").setMaterial(assetManager.loadMaterial(pathFileMaterial + "rackPanele.j3m"));
        ((Node) floor).getChild("River").setMaterial(assetManager.loadMaterial(pathFileMaterial + "river.j3m"));
        ((Node) floor).getChild("RiverEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "riverEdge.j3m"));
        ((Node) floor).getChild("RoadBridge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "roadBridge.j3m"));
        ((Node) floor).getChild("RoadEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "roadEdge.j3m"));
        ((Node) floor).getChild("shop01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "shop01.j3m"));
        ((Node) floor).getChild("sink").setMaterial(assetManager.loadMaterial(pathFileMaterial + "sink.j3m"));
        
        ((Node) floor).getChild("Sky").removeFromParent(); // remove sky created in Blender
        //viewPort.setBackgroundColor(ColorRGBA.Cyan); // temporary fix until we make a good skybox
        
        ((Node) floor).getChild("SteeringWh").setMaterial(assetManager.loadMaterial(pathFileMaterial + "steeringWh.j3m"));
        ((Node) floor).getChild("Supplier 1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "supplier1.j3m"));
        ((Node) floor).getChild("Supplier 2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "supplier2.j3m"));
        ((Node) floor).getChild("Table saw").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tableSaw.j3m"));
        ((Node) floor).getChild("Table01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "table01.j3m"));
        ((Node) floor).getChild("Tabliller0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tabliller0.j3m"));
        ((Node) floor).getChild("Tablillero").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tablillero.j3m"));
        ((Node) floor).getChild("Toilet").setMaterial(assetManager.loadMaterial(pathFileMaterial + "toilet.j3m"));
        ((Node) floor).getChild("TrashCan01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "trashCan01.j3m"));
        Material grassMaterial = new Material(assetManager, "Common/MatDefs/Terrain/TerrainLighting.j3md");
        grassMaterial.setBoolean("useTriPlanarMapping", false);
        grassMaterial.setFloat("Shininess", 0.0f);
        Texture grass = assetManager.loadTexture("Scenes/Textures/grs1rgb_3.jpg");
        grass.setWrap(Texture.WrapMode.Repeat);
        grassMaterial.setTexture("DiffuseMap", grass);
        ((Node) floor).getChild("Grass").setMaterial(grassMaterial);
    }

    private void createTerrain() {
        E_Terrain tempTerrain = gameData.getMapTerrain();

        //********************************************************************************
        String pathFileWorld = "Scenes/World_/";
        floor = (Node) assetManager.loadModel(pathFileWorld + "shop01.j3o");
        setMaterials();
        floor.setLocalScale(.2f);
        //********************************************************************************

        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape((Node) floor);
        floorRigid = new RigidBodyControl(sceneShape, 0);
        floor.addControl(floorRigid);
        rootNode.attachChild(floor);
        bulletAppState.getPhysicsSpace().add(floorRigid);
        
        /* First-person player */
        // ----------
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.4f, 24.5f, 1);
        // Use deprecated CharacterControl until BetterCharacterControl is updated
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(30);
        player.setPhysicsLocation(new Vector3f(22.10f, 12.47f, -38.73f));
        player.setViewDirection(new Vector3f(0, 0, 1));
        bulletAppState.getPhysicsSpace().add(player);
        // ----------

        //blocked zones
        Map<Integer, E_TerrainReserved> tempBlockedZones = tempTerrain.getArrZones();
        for (E_TerrainReserved tempBlockedZone : tempBlockedZones.values()) {
            setTerrainMap(tempBlockedZone.getLocationX(), tempBlockedZone.getLocationZ(), tempBlockedZone.getWidth(), tempBlockedZone.getLength(), true);
        }
        
        transformToCartoon(rootNode);
    }

    private void createGrid(int iniX, int iniZ, int sizeW, int sizeL) {
        Line line;
        Geometry lineGeo;
        Material lineMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        lineMat.setColor("Color", ColorRGBA.Black);
        int noMatrixWidth = (int) sizeW / Params.standardBucketWidthLength;
        int noMatrixLength = (int) sizeL / Params.standardBucketWidthLength;
        for (int i = 0; i <= noMatrixLength; i++) {
            line = new Line(new Vector3f(iniX, 1f, iniZ + i * Params.standardBucketWidthLength), new Vector3f(iniX + sizeW, 1f, iniZ + i * Params.standardBucketWidthLength));
            lineGeo = new Geometry();
            lineGeo.setMesh(line);
            lineGeo.setMaterial(lineMat);
            rootNode.attachChild(lineGeo);
        }
        for (int i = 0; i <= noMatrixWidth; i++) {
            line = new Line(new Vector3f(iniX + i * Params.standardBucketWidthLength, 1f, iniZ), new Vector3f(iniX + i * Params.standardBucketWidthLength, 1f, iniZ + sizeL));
            lineGeo = new Geometry();
            lineGeo.setMesh(line);
            lineGeo.setMaterial(lineMat);
            rootNode.attachChild(lineGeo);
        }
    }

    private void createPartsInStation(int idStation, int iniX, int iniZ, int sizeW, int sizeL) {
        int noMatrixWidth = (int) sizeW / Params.standardBucketWidthLength;
        int noMatrixLength = (int) sizeL / Params.standardBucketWidthLength;
        Geometry part;
        for (int i = 0; i < noMatrixWidth; i++) {
            for (int j = 0; j < noMatrixLength; j++) {
                partMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                partMaterial.setColor("Color", ColorRGBA.White);
                partBox = new Box(Vector3f.ZERO, (float) Params.standardPartWidthLength / 2.0f, 0.0f, (float) Params.standardPartWidthLength / 2.0f);
                part = new Geometry(TypeElements.STATION.toString() + idStation + "_" + TypeElements.PART.toString() + i + "_" + j, partBox);
                part.setMaterial(partMaterial);
                rootNode.attachChild(part);
                shootables.attachChild(part);
                part.setLocalTranslation(new Vector3f(iniX + (float) Params.standardBucketWidthLength / 2.0f + Params.standardBucketWidthLength * i, 0.5f, iniZ + (float) Params.standardBucketWidthLength / 2.0f + Params.standardBucketWidthLength * j));
            }
        }
    }

    private void createStations(E_Station station) {
        stationBox = new Box(Vector3f.ZERO, (float) station.getSizeW() / 2, .5f, (float) station.getSizeL() / 2);
        Geometry stationGeo = new Geometry(TypeElements.STATION + String.valueOf(station.getIdStation()), stationBox);
        stationMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        if (station.getStationType().toString().toUpperCase().equals(StationType.StaffZone.toString().toUpperCase())) {
            stationMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Stations/staffZone.png"));
        } else if (station.getStationType().toString().toUpperCase().equals(StationType.MachineZone.toString().toUpperCase())) {
            stationMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Stations/machineZone.png"));
        } else {
            station.reserveParkingZone();
            if (station.getStationType().toString().toUpperCase().equals(StationType.AssembleZone.toString().toUpperCase())) {
                stationMaterial.setTexture("ColorMap", assetManager.loadTexture(station.getStationDesign()));
            } else if (station.getStationType().toString().toUpperCase().equals(StationType.PurchaseZone.toString().toUpperCase())) {
                stationMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Stations/purchaseZone.png"));
            } else if (station.getStationType().toString().toUpperCase().equals(StationType.ShippingZone.toString().toUpperCase())) {
                stationMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Stations/shippingZone.png"));
            } else if (station.getStationType().toString().toUpperCase().equals(StationType.StorageIG.toString().toUpperCase())) {
                stationMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Stations/storageIGZone.png"));
            } else if (station.getStationType().toString().toUpperCase().equals(StationType.StorageFG.toString().toUpperCase())) {
                stationMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Stations/storageFGZone.png"));
            } else if (station.getStationType().toString().toUpperCase().equals(StationType.StorageRM.toString().toUpperCase())) {
                stationMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Stations/storageRMZone.png"));
            }
            if (station.getStationType().toString().toUpperCase().equals(StationType.StorageIG.toString().toUpperCase())
                    || station.getStationType().toString().toUpperCase().equals(StationType.StorageFG.toString().toUpperCase())
                    || station.getStationType().toString().toUpperCase().equals(StationType.StorageRM.toString().toUpperCase())) {
                station.initializeSlots();
            }
        }
        stationGeo.setMaterial(stationMaterial);
        rootNode.attachChild(stationGeo);
        stationGeo.setLocalTranslation(new Vector3f(station.getStationLocationX(), 0.5f, station.getStationLocationY()));
        stationRigid = new RigidBodyControl(new MeshCollisionShape(stationBox), 0);
        stationGeo.addControl(stationRigid);
        bulletAppState.getPhysicsSpace().add(stationRigid);
        //to be shootable
        shootables.attachChild(stationGeo);
        if (station.getStationType().toString().toUpperCase().contains("Storage".toUpperCase())) {
            //create grid, only in Storages
            createGrid(station.getStationLocationX() - (int) station.getSizeW() / 2, station.getStationLocationY() - (int) station.getSizeL() / 2, (int) station.getSizeW(), (int) station.getSizeL());
            createPartsInStation(station.getIdStation(), station.getStationLocationX() - (int) station.getSizeW() / 2, station.getStationLocationY() - (int) station.getSizeL() / 2, (int) station.getSizeW(), (int) station.getSizeL());
            setTerrainMap(station.getStationLocationX() - Params.standardBucketWidthLength / 2, station.getStationLocationY(), (int) station.getSizeW() - Params.standardBucketWidthLength, (int) station.getSizeL(), true);
        } else {
            //add buckets!!!! and/or machines
            station.updateBucketsPosition();
            Iterable<E_Bucket> tempBucket = station.getArrBuckets();
            for (E_Bucket bucket : tempBucket) {
                createBuckets(bucket);
                updatePartsInBucket(bucket);
            }
        }
    }

    private void createBuckets(E_Bucket bucket) {
        bucketBox = new Box(Vector3f.ZERO, (float) Params.standardBucketWidthLength / 2.0f, .5f, (float) Params.standardBucketWidthLength / 2.0f);
        Geometry bucketGeo = new Geometry(TypeElements.STATION + String.valueOf(bucket.getIdStation()) + "_" + TypeElements.BUCKET + String.valueOf(bucket.getIdBucket()), bucketBox);
        bucketMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        bucketMaterial.setColor("Color", ColorRGBA.Yellow);
        bucketGeo.setMaterial(bucketMaterial);
        rootNode.attachChild(bucketGeo);
        bucketGeo.setLocalTranslation(new Vector3f((float) bucket.getCurrentLocationX(), 1.0f, (float) bucket.getCurrentLocationZ()));
        bucketRigid = new RigidBodyControl(new MeshCollisionShape(bucketBox), 0);
        bucketGeo.addControl(bucketRigid);
        bulletAppState.getPhysicsSpace().add(bucketRigid);
        setTerrainMap(bucket.getCurrentLocationX(), bucket.getCurrentLocationZ(), Params.standardBucketWidthLength + 3, Params.standardBucketWidthLength + 3, true);
        shootables.attachChild(bucketGeo);
    }

    public void updatePartsInBucket(E_Bucket bucket) {
        Geometry part;
        part = (Geometry) rootNode.getChild(TypeElements.STATION + String.valueOf(bucket.getIdStation()) + "_" + TypeElements.BUCKET + String.valueOf(bucket.getIdBucket() + "_" + TypeElements.PART + String.valueOf(bucket.getIdPart())));
        partBox = new Box(Vector3f.ZERO, (float) Params.standardPartWidthLength / 2.0f, (float) bucket.getSize() / 2.0f, (float) Params.standardPartWidthLength / 2.0f);
        if (part == null) {
            part = new Geometry(TypeElements.STATION + String.valueOf(bucket.getIdStation()) + "_" + TypeElements.BUCKET + String.valueOf(bucket.getIdBucket()) + "_" + TypeElements.PART + String.valueOf(bucket.getIdPart()), partBox);
            partMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            partMaterial.setColor("Color", Colors.getColorRGBA(gameData.getMapUserPart().get(bucket.getIdPart()).getPartDesignColor()));
            part.setMaterial(partMaterial);
            rootNode.attachChild(part);
            shootables.attachChild(part);
        } else {
            part.setMesh(partBox);
        }
        part.setLocalTranslation(new Vector3f((float) bucket.getCurrentLocationX(), 1.5f + (float) bucket.getSize() / 2.0f, (float) bucket.getCurrentLocationZ()));
        //System.out.println("Part loc:" + part.getLocalTranslation() + " - station:" + bucket.getIdStation() + " " + bucket.getCurrentLocationX() + "," + bucket.getCurrentLocationZ());
    }

    public void operatorWalksTo(E_Operator operator, int posX, int posZ) {
        arrOperatorsWalksTo.add(new DispOperatorWalksTo(this, operator, posX, posZ, getGeneralScreenController().getTimeFactorForSpeed()));
    }

    public void operatorWalksToStaffZone(E_Operator operator, int posX, int posZ) {
        arrOperatorsWalksTo.add(new DispOperatorWalksTo(this, operator, posX, posZ, getGeneralScreenController().getTimeFactorForSpeed(), true));
    }

    public void operatorWalksToSpecificMachine(E_Operator operator, E_Machine machine, int posX, int posZ) {
        arrOperatorsWalksTo.add(new DispOperatorWalksTo(this, operator, machine, posX, posZ, getGeneralScreenController().getTimeFactorForSpeed()));
    }

    public void operatorAndMachineMovingTo(E_Operator operator, E_Machine machine, int posX, int posZ) {
        arrOperatorsMachinesMovingTo.add(new DispOperatorMachineMovingTo(this, rootNode, operator, machine, posX, posZ, getGeneralScreenController().getTimeFactorForSpeed()));
    }

    public void operatorAndMachineMovingToMachineZone(E_Operator operator, E_Machine machine, int posX, int posZ) {
        arrOperatorsMachinesMovingTo.add(new DispOperatorMachineMovingTo(this, rootNode, operator, machine, posX, posZ, getGeneralScreenController().getTimeFactorForSpeed(), true));
    }

    private void createMachines(E_Machine machine, GameType gameType) {
        Pair<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Pair<Integer, Integer>> machineLocation = null;
        if (gameType.equals(GameType.New)) {
            E_Station station = gameData.getUserStation_MachineZone();
            machineLocation = station.getLocationInMatrix(machine.getSizeW(), machine.getSizeL());
            if (machineLocation != null) {
                machine.setVirtualMatrixIniI(machineLocation.getFirst().getFirst().getFirst());
                machine.setVirtualMatrixIniJ(machineLocation.getFirst().getFirst().getSecond());
                machine.setVirtualMatrixEndI(machineLocation.getFirst().getSecond().getFirst());
                machine.setVirtualMatrixEndJ(machineLocation.getFirst().getSecond().getSecond());
                machine.setCurrentLocationX(machineLocation.getSecond().getFirst());
                machine.setCurrentLocationZ(machineLocation.getSecond().getSecond());
                machine.setVirtualIdLocation_MachineZone(TypeElements.STATION + String.valueOf(station.getIdStation()));
                machine.setVirtualIdLocation(TypeElements.STATION + String.valueOf(station.getIdStation()));
            }
        }
        model = (Node) assetManager.loadModel(machine.getMachineDesign());
        if (!machine.getMachineMaterial().equals("")) {
            model.setMaterial(assetManager.loadMaterial(machine.getMachineMaterial()));
        }
        //model.setMaterial(assetManager.loadMaterial("Models/Machine/machine1.material"));
        model.setLocalScale(0.2f);
        model.setName(TypeElements.MACHINE + String.valueOf(machine.getIdMachine()));
        model.setLocalTranslation(new Vector3f(machine.getCurrentLocationX(), 0.5f, machine.getCurrentLocationZ()));
        rootNode.attachChild(model);
        // I cannot add an animation because my MACHINES does not support animation!!!
        machine.setModelCharacter(model);
        machine.setAssetManager(assetManager);
        machine.setRootNode(rootNode);
        machine.setBulletAppState(bulletAppState);
        shootables.attachChild(model);
    }

    private void createOperators(E_Operator operator, GameType gameType) {
        Pair<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Pair<Integer, Integer>> operatorLocation = null;
        if (gameType.equals(GameType.New)) {
            E_Station station = gameData.getUserStation_StaffZone();
            operatorLocation = station.getLocationInMatrix(Params.standardOperatorWidthLength, Params.standardOperatorWidthLength);
            if (operatorLocation != null) {
                operator.setVirtualMatrixI(operatorLocation.getFirst().getFirst().getFirst());
                operator.setVirtualMatrixJ(operatorLocation.getFirst().getFirst().getSecond());
                operator.setCurrentLocationX(operatorLocation.getSecond().getFirst());
                operator.setCurrentLocationZ(operatorLocation.getSecond().getSecond());
                operator.setVirtualIdLocation_StaffZone(TypeElements.STATION + String.valueOf(station.getIdStation()));
                operator.setVirtualIdLocation(TypeElements.STATION + String.valueOf(station.getIdStation()));
            }
        }
        //FIX ME: it consider 'operator' has always a location in the matrix, it means a location X/Z
        CapsuleCollisionShape capsule = new CapsuleCollisionShape(1.5f, 6f, 1);
        character_Disp = new CharacterControl(capsule, 0.05f);
        model = (Node) assetManager.loadModel("Models/Operator/Oto.mesh.j3o");
        model.setMaterial(assetManager.loadMaterial("Models/Operator/operatorNone.j3m"));
        model.addControl(character_Disp);
        model.setName(TypeElements.OPERATOR + String.valueOf(operator.getIdOperator()));

        Geometry base = new Geometry(TypeElements.CUBE.toString(), new Grid(8, 8, 1f));
        base.setName(TypeElements.OPERATOR + String.valueOf(operator.getIdOperator()));
        base.setMaterial(matCharacter);
        base.setLocalTranslation(model.getLocalTranslation().getX() - 4, model.getLocalTranslation().getY() - 4.9f, model.getLocalTranslation().getZ() - 4);
        character_Disp.setPhysicsLocation(new Vector3f(operator.getCurrentLocationX(), Params.standardLocationY, operator.getCurrentLocationZ()));
        rootNode.attachChild(model);
        bulletAppState.getPhysicsSpace().add(character_Disp);
        model.getControl(AnimControl.class).addListener(this);
        operator.setCharacter(character_Disp);
        operator.setAnimationChannel(model.getControl(AnimControl.class).createChannel());
        operator.setModelCharacter(model);
        operator.setAssetManager(assetManager);
        operator.setRootNode(rootNode);
        operator.setBaseCharacter(base);
        operator.updateOperatorCategory();
        shootables.attachChild(model);
    }

    public void setTerrainMap(int locX, int locZ, int width, int length, boolean blocked) {
        int realLocX = locX + Params.terrainWidthLoc - width / 2;
        int realLocZ = locZ + Params.terrainHeightLoc - length / 2;
        int valuePixel = (blocked == true ? 1 : 0);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                terrainMap.setUnit(i + realLocX, j + realLocZ, valuePixel);
            }
        }
    }

    public void setTerrainMap(Path path, int locX, int locZ, boolean blocked) {
        int valuePixel = (blocked == true ? 1 : 0);
        if (path != null) {
            for (Step s : path.getSteps()) {
                terrainMap.setUnit(s.getX(), s.getY(), valuePixel);
            }
        } else {
            terrainMap.setUnit(locX, locZ, valuePixel);
        }
    }

    private void setupChaseCamera() {
        flyCam.setMoveSpeed(100);
        cam.setViewPort(0f, 1f, 0f, 1f);
        cam.setLocation(new Vector3f(163.46553f, 305.52246f, -125.38404f));
        cam.setAxes(new Vector3f(-0.0024178028f, 0.0011213422f, 0.9999965f), new Vector3f(-0.96379673f, 0.26662517f, -0.00262928f), new Vector3f(-0.26662725f, -0.96379966f, 0.00043606758f));
    }

    private void setupFilter() {
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);

        CartoonEdgeFilter toonFilter = new CartoonEdgeFilter();
        toonFilter.setEdgeWidth(0.33999988f);
        fpp.addFilter(toonFilter);
        
        viewPort.addProcessor(fpp);
    }
    
    /**
     * For now, here we initialize all sound effects related to the player. 
     */
    private void initSoundEffects() {
        /* Initialize player's footsteps */
        footstep = new AudioNode(assetManager, "Sounds/footstep.ogg", false);
        footstep.setPositional(false);
        footstep.setLooping(true);
    }

    private void initKeys() {
        inputManager.deleteMapping("FLYCAM_ZoomIn");
        inputManager.deleteMapping("FLYCAM_ZoomOut");
        
        inputManager.addMapping("MousePicking", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "MousePicking");

        String[] mappings = {"Forward", "Backward", "Left",
                             "Right", "Jump", "Picking",
                             "Dashboard Control"};
        
        int[] triggers = {KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_A, 
                          KeyInput.KEY_D, KeyInput.KEY_SPACE, KeyInput.KEY_LSHIFT, 
                          KeyInput.KEY_RSHIFT};
        
        for (int i = 0; i < mappings.length; i++) {
            inputManager.addMapping(mappings[i], new KeyTrigger(triggers[i]));
            inputManager.addListener(actionListener, mappings[i]);
        } 
    }
    
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {            
            if (!executeGame) {
                return;
            }
            
            switch (name) {
                case "Forward":
                    forward = keyPressed;
                    if (keyPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                    else { if (!backward && !left && !right) { footstep.stop();  } }
                    break;
                    
                case "Backward":
                    backward = keyPressed;
                    if (keyPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                    else { if (!forward && !left && !right) { footstep.stop(); } }
                    break;
                    
                case "Left":
                    moveLeft = keyPressed;
                    if (keyPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                    else { if (!forward && !backward && !right) { footstep.stop(); } }
                    break;
                    
                case "Right":
                    moveRight = keyPressed;
                    if (keyPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                    else { if (!forward && !backward && !left) { footstep.stop(); } }
                    break;
                
                case "Picking": case "MousePicking":
                    if (!keyPressed)
                        handlePickedObject(name);
                    break;
                
                case "Dashboard Control":
                    if (!keyPressed) {
                        showHideDashboard = true;
                        manageDashboard();
                        if (Params.DEBUG_ON)
                            System.out.println("Dashboard Control Key Selected.");
                    }
                    break;
                    
                default:
                    break;
            }
        }
    };
    
    private void handlePickedObject(String pickingType) {
        getGeneralScreenController().hideCurrentControlsWindow();
        CollisionResults results = new CollisionResults();
        Ray ray;
        if (pickingType.equals("Picking")) { //Picking with the SHIFT Button
            ray = new Ray(cam.getLocation(), cam.getDirection());
        }
        else { //Picking with mouse
            Vector3f origin    = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.0f);
            Vector3f direction = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.3f);
            direction.subtractLocal(origin).normalizeLocal();
            ray = new Ray(origin, direction);
        }
        
        shootables.collideWith(ray, results);
        
        if (results.size() > 0) {
            
            CollisionResult closest = results.getClosestCollision();
            
            if (Params.DEBUG_ON) {
                System.out.println(closest.getDistance());
            }
            
            if (closest.getDistance() > 60f) {
                return;
            }
            
            String shootableObject;
            if (closest.getGeometry().getParent().getName().equals("Shootables")) {
                shootableObject = closest.getGeometry().getName();
            } else {
                Node tempGeometry = closest.getGeometry().getParent();
                while (!tempGeometry.getParent().getName().equals("Shootables")) {
                    tempGeometry = tempGeometry.getParent();
                }
                shootableObject = tempGeometry.getName();
            }
            
            loadWindowControl(shootableObject); //SHOW WINDOW
            System.out.println("######## SHOOT: " + shootableObject);
        }     
    }

    private void loadWindowControl(String winControl) {
        Pair<Integer, Integer> positionWC = new Pair((int) inputManager.getCursorPosition().getX(), guiViewPort.getCamera().getHeight() - (int) inputManager.getCursorPosition().getY());
        getGeneralScreenController().hideCurrentControlsWindow();
        getGeneralScreenController().showHideDynamicButtons(0);
        getGeneralScreenController().showHideDynamicSubLevelButtons(0);
        if (winControl.contains(TypeElements.OPERATOR.toString())) {
            nifty.getScreen("layerScreen").findElementByName("winOC_Element").getControl(OperatorControl.class).loadWindowControl(this, Integer.valueOf(winControl.replace(TypeElements.OPERATOR.toString(), "")), positionWC);
            getGeneralScreenController().setCurrentOptionselected("windowOperator");
        } else if (winControl.contains(TypeElements.PART.toString())) {
            nifty.getScreen("layerScreen").findElementByName("winPC_Element").getControl(PartControl.class).loadWindowControl(this, Integer.valueOf(winControl.split("_")[winControl.split("_").length - 1].replace(TypeElements.PART.toString(), "")), positionWC);
            getGeneralScreenController().setCurrentOptionselected("windowPart");
        } else if (winControl.contains(TypeElements.STATION.toString()) && !winControl.contains(TypeElements.BUCKET.toString())) {
            E_Station tempStation = getGameData().getMapUserStation().get(Integer.valueOf(winControl.replace(TypeElements.STATION.toString(), "")));
            if (!tempStation.getStationType().equals(StationType.MachineZone) && !tempStation.getStationType().equals(StationType.StaffZone)) {
                nifty.getScreen("layerScreen").findElementByName("winSSC_Element").getControl(StorageStationControl.class).loadWindowControl(this, Integer.valueOf(winControl.replace(TypeElements.STATION.toString(), "")), positionWC);
                getGeneralScreenController().setCurrentOptionselected("windowStorageStation");
            }

        } else if (winControl.contains(TypeElements.MACHINE.toString())) {
            nifty.getScreen("layerScreen").findElementByName("winMC_Element").getControl(MachineControl.class).loadWindowControl(this, Integer.valueOf(winControl.replace(TypeElements.MACHINE.toString(), "")), positionWC);
            getGeneralScreenController().setCurrentOptionselected("windowMachine");
        }
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAnimations() {
        if (arrStationAnimations.size() > 0) {
            for (StationAnimation tempStationAnim : arrStationAnimations) {
                StationAnimation stationAnimation = new StationAnimation();
                stationAnimation = new StationAnimation();
                stationAnimation.setGameEngine(this);
                stationAnimation.setPartBox(tempStationAnim.getPartBox());
                stationAnimation.setTimeToFinish(tempStationAnim.getTimeToFinish());
                stationAnimation.setAddItems(tempStationAnim.isAddItems());
                stationAnimation.setIsZeroItems(tempStationAnim.isIsZeroItems());
                stationAnimation.setQuantity(tempStationAnim.getQuantity());
                stationAnimation.setIdStrategy(tempStationAnim.getIdStrategy());
//                if (tempStationAnim.getIdStrategy() != -1)
//                    ((TransportStrategy)getManageEvents().getEvent(tempStationAnim.getIdStrategy())).getArrStationAnimation().add(stationAnimation);
                stationAnimation.start();
            }
        }
        arrStationAnimations.clear();
    }

    private void createShowSpotObject() {
        //showSpotObject
        Sphere sphere = new Sphere(20, 20, (float) Params.standardPartWidthLength / 2);
        showSpotObject = new Geometry("SPOT_OBJECT", sphere);
        Material materialForSpotObject = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        materialForSpotObject.setColor("Color", ColorRGBA.Blue);
        showSpotObject.setMaterial(materialForSpotObject);
        rootNode.attachChild(showSpotObject);
        showSpotObject.setLocalTranslation(new Vector3f(0, -(float) Params.standardPartWidthLength * 4, 0));
    }

    public Geometry getShowSpotObject() {
        return showSpotObject;
    }

    public void setShowSpotObject(Geometry showSpotObject) {
        this.showSpotObject = showSpotObject;
    }

    public void updateGameSounds(boolean isPlaying) {
        if (isPlaying) {
            for (Pair<GameSounds, Sounds> gs : arrGameSounds) {
                gs.getFirst().playSound(gs.getSecond());
            }
        } else {
            for (Pair<GameSounds, Sounds> gs : arrGameSounds) {
                gs.getFirst().pauseSound(gs.getSecond());
            }
        }
    }

    public ArrayList<Pair<GameSounds, Sounds>> getArrGameSounds() {
        return arrGameSounds;
    }

    public void setArrGameSounds(ArrayList<Pair<GameSounds, Sounds>> arrGameSounds) {
        this.arrGameSounds = arrGameSounds;
    }

    public Node getShootables() {
        return shootables;
    }

    public boolean isExecuteGame() {
        return executeGame;
    }

    public void setExecuteGame(boolean executeGame) {
        this.executeGame = executeGame;
    }

    public ManageEvents getManageEvents() {
        return manageEvents;
    }

    public void setManageEvents(ManageEvents manageEvents) {
        this.manageEvents = manageEvents;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public GeneralScreenController getGeneralScreenController() {
        return generalScreenController;
    }

    public TerrainMap getTerrainMap() {
        return terrainMap;
    }

    public Status getCurrentSystemStatus() {
        return currentSystemStatus;
    }

    public void setCurrentSystemStatus(Status currentSystemStatus) {
        this.currentSystemStatus = currentSystemStatus;
    }

    public long getCurrentTempSystemTime() {
        return currentTempSystemTime;
    }

    public void setCurrentTempSystemTime(long currentTempSystemTime) {
        this.currentTempSystemTime = currentTempSystemTime;
    }

    public double getCurrentSystemTime() {
        return currentSystemTime;
    }

    public void setCurrentSystemTime(double currentSystemTime) {
        this.currentSystemTime = currentSystemTime;
    }

    public ArrayList<StationAnimation> getArrStationAnimations() {
        return arrStationAnimations;
    }

    public void setArrStationAnimations(ArrayList<StationAnimation> arrStationAnimations) {
        this.arrStationAnimations = arrStationAnimations;
    }

    public Nifty getNifty() {
        return nifty;
    }

    public void setNifty(Nifty nifty) {
        this.nifty = nifty;
    }

    public int getInitialGameId() {
        return initialGameId;
    }

    public void setInitialGameId(int initialGameId) {
        this.initialGameId = initialGameId;
    }

    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (!executeGame) {
            return;
        }
    }

    private Screen createIntroScreen(final Nifty nifty) {
        Screen screen = new ScreenBuilder("start") {
            {
                controller(new DefaultScreenController() {
                    @Override
                    public void onStartScreen() {
                        nifty.gotoScreen("initialMenu");
                    }
                });
                layer(new LayerBuilder("layer") {
                    {
                        childLayoutCenter();
                        onStartScreenEffect(new EffectBuilder("fade") {
                            {
                                length(3000);
                                effectParameter("start", "#0");
                                effectParameter("end", "#f");
                            }
                        });
                        onStartScreenEffect(new EffectBuilder("playSound") {
                            {
                                startDelay(1400);
                                effectParameter("sound", "intro");
                            }
                        });
                        onActiveEffect(new EffectBuilder("gradient") {
                            {
                                effectValue("offset", "0%", "color", "#66666fff");
                                effectValue("offset", "85%", "color", "#000f");
                                effectValue("offset", "100%", "color", "#44444fff");
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                alignCenter();
                                valignCenter();
                                childLayoutHorizontal();
                                width("856px");
                                panel(new PanelBuilder() {
                                    {
                                        width("300px");
                                        height("256px");
                                        childLayoutCenter();
                                        text(new TextBuilder() {
                                            {
                                                text("GAMING");
                                                style("base-font");
                                                alignCenter();
                                                valignCenter();
                                                onStartScreenEffect(new EffectBuilder("fade") {
                                                    {
                                                        length(1000);
                                                        effectValue("time", "1700", "value", "0.0");
                                                        effectValue("time", "2000", "value", "1.0");
                                                        effectValue("time", "2600", "value", "1.0");
                                                        effectValue("time", "3200", "value", "0.0");
                                                        post(false);
                                                        neverStopRendering(true);
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        alignCenter();
                                        valignCenter();
                                        childLayoutOverlay();
                                        width("256px");
                                        height("256px");
                                        onStartScreenEffect(new EffectBuilder("shake") {
                                            {
                                                length(250);
                                                startDelay(1300);
                                                inherit();
                                                effectParameter("global", "false");
                                                effectParameter("distance", "10.");
                                            }
                                        });
                                        onStartScreenEffect(new EffectBuilder("imageSize") {
                                            {
                                                length(600);
                                                startDelay(3000);
                                                effectParameter("startSize", "1.0");
                                                effectParameter("endSize", "2.0");
                                                inherit();
                                                neverStopRendering(true);
                                            }
                                        });
                                        onStartScreenEffect(new EffectBuilder("fade") {
                                            {
                                                length(600);
                                                startDelay(3000);
                                                effectParameter("start", "#f");
                                                effectParameter("end", "#0");
                                                inherit();
                                                neverStopRendering(true);
                                            }
                                        });
                                        image(new ImageBuilder() {
                                            {
                                                filename("Interface/yin.png");
                                                onStartScreenEffect(new EffectBuilder("move") {
                                                    {
                                                        length(1000);
                                                        startDelay(300);
                                                        timeType("exp");
                                                        effectParameter("factor", "6.f");
                                                        effectParameter("mode", "in");
                                                        effectParameter("direction", "left");
                                                    }
                                                });
                                            }
                                        });
                                        image(new ImageBuilder() {
                                            {
                                                filename("Interface/yang.png");
                                                onStartScreenEffect(new EffectBuilder("move") {
                                                    {
                                                        length(1000);
                                                        startDelay(300);
                                                        timeType("exp");
                                                        effectParameter("factor", "6.f");
                                                        effectParameter("mode", "in");
                                                        effectParameter("direction", "right");
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        width("300px");
                                        height("256px");
                                        childLayoutCenter();
                                        text(new TextBuilder() {
                                            {
                                                text("More Gaming");
                                                style("base-font");
                                                alignCenter();
                                                valignCenter();
                                                onStartScreenEffect(new EffectBuilder("fade") {
                                                    {
                                                        length(1000);
                                                        effectValue("time", "1700", "value", "0.0");
                                                        effectValue("time", "2000", "value", "1.0");
                                                        effectValue("time", "2600", "value", "1.0");
                                                        effectValue("time", "3200", "value", "0.0");
                                                        post(false);
                                                        neverStopRendering(true);
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
                layer(new LayerBuilder() {
                    {
                        backgroundColor("#ddff");
                        onStartScreenEffect(new EffectBuilder("fade") {
                            {
                                length(1000);
                                startDelay(3000);
                                effectParameter("start", "#0");
                                effectParameter("end", "#f");
                            }
                        });
                    }
                });
            }
        }.build(nifty);
        return screen;
    }

    private Screen createMenuScreen(final Nifty nifty) {
        Screen screen = new ScreenBuilder("initialMenu") {
            {
                controller(new MenuScreenController());
                inputMapping("de.lessvoid.nifty.input.mapping.DefaultInputMapping");
                layer(new LayerBuilder("layer") {
                    {
                        backgroundImage("Interface/background-new.png");
                        childLayoutVertical();
                        panel(new PanelBuilder("dialogParent") {
                            {
                                childLayoutOverlay();
                                width("100%");
                                height("100%");
                                alignCenter();
                                valignCenter();
                                control(new ControlBuilder("dialogInitialMenu", InitialMenuDisplay.NAME));
                                control(new ControlBuilder("dialogMainMenu", MainMenuDisplay.NAME));
                                control(new ControlBuilder("dialogForgotYourPasswordMenu", ForgotYourPasswordDisplay.NAME));
                                control(new ControlBuilder("dialogNewUserMenu", NewUserMenuDisplay.NAME));
                                control(new ControlBuilder("dialogNewGameStage1Menu", NewGame1MenuDisplay.NAME));
                                control(new ControlBuilder("dialogLoadGameMenu", LoadGameMenuDisplay.NAME));
                                control(new ControlBuilder("dialogOptionsMenu", OptionsMenuDisplay.NAME));
                            }
                        });
                    }
                });
            }
        }.build(nifty);
        return screen;
    }

    private Screen createLayerScreen(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();
        new ControlDefinitionBuilder("customListBox_Line") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        image(new ImageBuilder("#icon") {
                            {
                                width("25px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#message") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                height("30px");
                                width("*");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBoxOperator_Line") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        image(new ImageBuilder("#icon") {
                            {
                                width("25px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#message") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                height("100px");
                                width("*");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBox_AssemblyDetail") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("140px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#value") {
                            {
                                visibleToMouse();
                                alignCenter();
                                textHAlignCenter();
                                width("60px");
                                height("25px");
                            }
                        });
                        visibleToMouse();
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBox_Orders") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#index") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("20px");
                                height("25px");
                            }
                        });
                        image(new ImageBuilder("#icon") {
                            {
                                width("25px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("120px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#quantity") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("30px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#timeOver") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignRight();
                                width("80px");
                                height("25px");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBox_Slots") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");

                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("75px");
                                height("25px");
                            }
                        });
                        image(new ImageBuilder("#icon") {
                            {
                                width("22px");
                                height("22px");
                            }
                        });
                        control(new LabelBuilder("#quantity") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("60px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#partsNumber") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("60px");
                                height("25px");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_Buckets") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#index") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("20px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#unit") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("60px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#sizeCapacity") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("50px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#partAssigned") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("80px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#unitsToArriveRemove") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("50px");
                                height("25px");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_GameLog") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#time") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("25px");
                                width("130px");
                            }
                        });
                        image(new ImageBuilder("#icon") {
                            {
                                width("20px");
                                height("20px");
                            }
                        });
                        control(new LabelBuilder("#message") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("25px");
                                width("480");
                                
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_StationsList_DB") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#station") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("100px");
                            }
                        });
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("65px");
                            }
                        });
                        control(new LabelBuilder("#quantity") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("60");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_TransportList_DB") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#fromTo") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("180px");
                            }
                        });
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("60px");
                            }
                        });
                        control(new LabelBuilder("#required") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("40");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_AssingOperators") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#id") {
                            {
                                alignLeft();
                                textHAlignLeft();
                                width("10px");
                                height("25px");
                                wrap(true);
                            }
                        });
                        control(new LabelBuilder("#type") {
                            {
                                alignLeft();
                                textHAlignLeft();
                                width("80px");
                                height("25px");
                                wrap(true);
                            }
                        });
                        control(new LabelBuilder("#name") {
                            {
                                alignLeft();
                                textHAlignLeft();
                                width("220px");
                                height("25px");
                                wrap(true);
                            }
                        });
                        inputMapping("de.lessvoid.nifty.input.mapping.MenuInputMapping");
                        onHoverEffect(new HoverEffectBuilder("colorBar") {
                            {
                                effectParameter("color", "#006400");//verde
                                post(true);
                                inset("1px");
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onCustomEffect(new EffectBuilder("colorBar") {
                            {
                                customKey("focus");
                                post(false);
                                effectParameter("color", "#FFA200");//amarillo
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onCustomEffect(new EffectBuilder("colorBar") {
                            {
                                customKey("select");
                                post(false);
                                effectParameter("color", "#FFA200");// "#f00f");
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onCustomEffect(new EffectBuilder("textColor") {
                            {
                                customKey("select");
                                post(false);
                                effectParameter("color", "#000000");// "#000f");
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onClickEffect(new EffectBuilder("focus") {
                            {
                                effectParameter("targetElement", "#parent#parent");
                            }
                        });
                        interactOnClick("listBoxItemClicked_AO()");
                    }
                });
            }
        }.registerControlDefintion(nifty);

        Screen screen = new ScreenBuilder("layerScreen") {
            {
                controller(new GeneralScreenController());
                inputMapping("de.lessvoid.nifty.input.mapping.DefaultInputMapping");
                layer(new LayerBuilder("layer") {
                    {
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                
                                width("100%");
                                alignCenter();
                                childLayoutHorizontal();
                                backgroundImage("Interface/panelBack3.png");//Principal/nifty-panel-simple.png");
                                control(new LabelBuilder("gameNamePrincipal", "  Game: ...") {
                                    {
                                        width("90px");
                                        textHAlignLeft();
                                    }
                                });
                                control(new LabelBuilder("gamePrincipalStatus", "_") {
                                    {
                                        width("60px");
                                        textHAlignLeft();
                                    }
                                });
                                image(new ImageBuilder("imagePlay") {
                                    {
                                        filename("Interface/button_play_red.png");
                                        width("25px");
                                        height("25px");
                                        focusable(true);
                                        interactOnClick("playGameValidated()");
                                    }
                                });
                                panel(common.hspacer("20px"));
                                control(new SliderBuilder("sliderTime", false) {
                                    {
                                        width("125px");
                                    }
                                });
                                panel(common.hspacer("3px"));
                                control(new LabelBuilder("labelSliderTime") {
                                    {
                                        width("30px");
                                    }
                                });
                                panel(common.hspacer("25px"));
                                image(new ImageBuilder("imageCurrentTime") {
                                    {
                                        filename("Interface/clock-blue.png");
                                        width("25px");
                                        height("25px");
                                    }
                                });
                                control(new LabelBuilder("labelCurrentGameTime") {
                                    {
                                        width("130px");
                                        textHAlignLeft();
                                    }
                                });
                                panel(common.hspacer("10px"));
                                image(new ImageBuilder("imageDueDate") {
                                    {
                                        filename("Interface/clock-red.png");
                                        width("25px");
                                        height("25px");
                                    }
                                });
                                control(new LabelBuilder("labelDueDateNextOrder") {
                                    {
                                        width("130px");
                                        textHAlignLeft();
                                    }
                                });
                                panel(common.hspacer("10px"));
                                image(new ImageBuilder("imagePurchaseDueDate") {
                                    {
                                        filename("Interface/clock-green.png");
                                        width("25px");
                                        height("25px");
                                    }
                                });
                                control(new LabelBuilder("labelPurchaseDueDate") {
                                    {
                                        width("120px");
                                        textHAlignLeft();
                                    }
                                });
                                panel(common.hspacer("85px"));

                                image(new ImageBuilder("imageSpeaker") {
                                    {
                                        filename("Interface/speaker_blue2.png");
                                        width("25px");
                                        height("25px");
                                        focusable(true);
                                        interactOnClick("manageGameVolume()");
                                    }
                                });
                                panel(common.hspacer("10px"));
                                control(new ButtonBuilder("buttonStaticOptionFlowChart", "Flow Chart") {
                                    {
                                        width("100px");
                                    }
                                });
                                panel(common.hspacer("3px"));
                                control(new ButtonBuilder("buttonStaticOptionGameSetup", "Setup") {
                                    {
                                        width("100px");
                                    }
                                });
                                panel(common.hspacer("3px"));
                                control(new ButtonBuilder("buttonStaticOptionReturnToMenu", "Return to Menu") {
                                    {
                                        width("100px");
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                childLayoutHorizontal();
                                height("*");
                                panel(new PanelBuilder() {
                                    {
                                        childLayoutVertical();
                                        width("10%");
                                        panel(common.vspacer("2px"));

                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutHorizontal();
                                                image(new ImageBuilder("imageControls") {
                                                    {
                                                        filename("Interface/Principal/controls2.png");
                                                        width("25px");
                                                        height("25px");
                                                        focusable(true);
                                                    }
                                                });
                                                control(new ButtonBuilder("buttonOptionControls", "Controls"));
                                            }
                                        });
                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutHorizontal();
                                                image(new ImageBuilder("imageActivities") {
                                                    {
                                                        filename("Interface/Principal/activity.png");
                                                        width("25px");
                                                        height("25px");
                                                        focusable(true);
                                                    }
                                                });
                                                control(new ButtonBuilder("buttonOptionActivities", "Activities"));
                                            }
                                        });
                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutHorizontal();
                                                image(new ImageBuilder("imageUtilities") {
                                                    {
                                                        filename("Interface/Principal/utilities.png");
                                                        width("25px");
                                                        height("25px");
                                                        focusable(true);
                                                    }
                                                });
                                                control(new ButtonBuilder("buttonOptionUtilities", "Utilities"));
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder("dynamicButtons") {
                                    {
                                        childLayoutVertical();
                                        width("10%");
                                        control(new ButtonBuilder("dynBut0", "test0") {
                                            {
                                                width("98%");
                                                textHAlignCenter();
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut1", "test1") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut2", "test2") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut3", "test3") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut4", "test4") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut5", "test5") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut6", "test6") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut7", "test7") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut8", "test8") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut9", "test9") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut10", "test10") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut11", "test11") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut12", "test12") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut13", "test13") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut14", "test14") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut15", "test15") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut16", "test16") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut17", "test17") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut18", "test18") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut19", "test19") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut20", "test20") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut21", "test21") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut22", "test22") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut23", "test23") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut24", "test24") {
                                            {
                                                width("98%");
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder("dynamicSubLevelButtons") {
                                    {
                                        childLayoutVertical();
                                        width("10%");
                                        control(new ButtonBuilder("dynSubLevelBut0", "test0") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut1", "test1") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut2", "test2") {

                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut3", "test3") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut4", "test4") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut5", "test5") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut6", "test6") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut7", "test7") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut8", "test8") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut9", "test9") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut10", "test10") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut11", "test11") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut12", "test12") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut13", "test13") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut14", "test14") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut15", "test15") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut16", "test16") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut17", "test17") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut18", "test18") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut19", "test19") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut20", "test20") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut21", "test21") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut22", "test22") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut23", "test23") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut24", "test24") {
                                            {
                                                width("98%");
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                       // style(null);
                                        childLayoutAbsolute();
                                        width("70%");
                                        height("*");

                                        panel(new PanelBuilder("multipleControls_SMC") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new MainMultipleControls());
                                                panel(new PanelBuilder("parent_SMC") {
                                                    {
                                                        childLayoutVertical();
//                                backgroundImage(panelBackgroundImage);
                                                        //add elements....

                                                        //end elements...
                                                        x("0px");
                                                        y("25px");
                                                        width("240");
                                                        height("450");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder("manageVolume_MGV") {
                                            {
                                                childLayoutAbsolute();
                                                panel(new PanelBuilder("parent_MGV") {
                                                    {
                                                        childLayoutVertical();
//                                backgroundImage(panelBackgroundImage);
                                                        //add elements....

                                                        //end elements...
                                                        x("938px");
                                                        y("25px");
                                                        width("150");
                                                        height("150");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutAbsolute();
                                                image(new ImageBuilder("imageTimeFactor") {
                                                    {
                                                        filename("Interface/Principal/square_minus.png");
                                                        width("24px");
                                                        height("24px");
                                                        x("195px");
                                                    }
                                                });
                                                image(new ImageBuilder("imageTimeFactor") {
                                                    {
                                                        filename("Interface/Principal/square_plus.png");
                                                        width("24px");
                                                        height("24px");
                                                        x("297px");
                                                    }
                                                });
                                            }
                                        });
                                        //******************************************************************
                                        panel(new PanelBuilder("winOC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new OperatorControl());
                                                control(new WindowBuilder("winOperatorControl", "Operator") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("150px");
                                                                        control(new LabelBuilder("text_WOC", "Operators list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("operatorsList_WOC") {
                                                                            {
                                                                                displayItems(12);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("id_WOC", "Operator ID:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("idValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("name_WOC", "Name:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("nameValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new RadioGroupBuilder("RadioGroup_Activate_WOC"));
                                                                                control(new LabelBuilder("activateValue_WOC", "Hire") {
                                                                                    {
                                                                                        width("60px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                control(new RadioButtonBuilder("activate_WOC_True") {
                                                                                    {
                                                                                        group("RadioGroup_Activate_WOC");
                                                                                        width("40px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("deactivateValue_WOC", "Fire") {
                                                                                    {
                                                                                        width("60px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                control(new RadioButtonBuilder("activate_WOC_False") {
                                                                                    {
                                                                                        group("RadioGroup_Activate_WOC");
                                                                                        width("40px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("hireFire_WOC", "Hire/Fire Cost:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("hireFireValue_WOC", "_") {
                                                                                    {
                                                                                        width("130px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("status_WOC", "Status:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("statusValue_WOC", "_") {
                                                                                    {
                                                                                        width("100px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                height("25px");
                                                                                control(new LabelBuilder("category_WOC", "Category:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new DropDownBuilder("categoryValue_WOC") {
                                                                                    {
                                                                                        width("130px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                        visibleToMouse(true);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("location_WOC", "Current Location:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("locationValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("speed_WOC", "Speed:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("speedValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHourCarrier_WOC", "Material Handler Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourCarrierValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHourAssembler_WOC", "Operator Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourAssemblerValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHourVersatile_WOC", "Versatile Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourVersatileValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHour_WOC", "Current Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("earnedMoney_WOC", "Earned Money:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("earnedMoneyValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("percentageUsage_WOC", "% Usage:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("percentageUsageValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
//                     
                                                                        panel(common.vspacer("5px"));
                                                                        control(new LabelBuilder("messageResult_WOC", "") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        control(new ButtonBuilder("operatorUpdate", "Update") {
                                                                            {
                                                                                width("95%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        visible(false);
                                                        x("312px");
                                                        y("28px");
                                                        width("490px");
                                                        height("385px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winMC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new MachineControl());
                                                control(new WindowBuilder("winMachineControl", "Machine") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("120px");
                                                                        control(new LabelBuilder("text_WMC", "Machines list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("machinesList_WMC") {
                                                                            {
                                                                                displayItems(12);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("id_WMC", "Machine ID:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("idValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("description_WMC", "Description:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("descriptionValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new RadioGroupBuilder("RadioGroup_Activate_WMC"));
                                                                                        panel(common.hspacer("20px"));
                                                                                        control(new LabelBuilder("activateValue_WMC", "Buy") {
                                                                                            {
                                                                                                width("60px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_label);
                                                                                            }
                                                                                        });
                                                                                        control(new RadioButtonBuilder("activate_WMC_True") {
                                                                                            {
                                                                                                group("RadioGroup_Activate_WMC");
                                                                                                width("40px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("deactivateValue_WMC", "Sell") {
                                                                                            {
                                                                                                width("60px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_label);
                                                                                            }
                                                                                        });
                                                                                        control(new RadioButtonBuilder("activate_WMC_False") {
                                                                                            {
                                                                                                group("RadioGroup_Activate_WMC");
                                                                                                width("40px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("buySell_WMC", "Purchase/Sale Price:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("buySellValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageDepreciation_WMC", "% Depreciation:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageDepreciationValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageDepreciationAccumulated_WMC", "% Accumulated Depreciation:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageDepreciationAccumulatedValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("status_WMC", "Status:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("statusValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("currentLocation_WMC", "Current Location:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("currentLocationValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                //                                        panel(new PanelBuilder(){{
                                                                                //                                            childLayoutHorizontal();    height("22px");
                                                                                //                                            control(new LabelBuilder("priceForPurchase_WMC","Price For Purchase:"){{   width("170px");  height("20px"); textHAlign(machine_label); }});  panel(common.hspacer("5px"));
                                                                                //                                            control(new LabelBuilder("priceForPurchaseValue_WMC"){{   width("70px");  height("20px"); textHAlign(machine_value); }});
                                                                                //                                        }});
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("costPerHour_WMC", "Cost Per Hour:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("costPerHourValue_WMC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("totalCost_WMC", "Total Cost:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("totalCostValue_WMC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("10px"));
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageAvailability_WMC", "% Availability:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageAvailabilityValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageUsage_WMC", "% Usage:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageUsageValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder("partsProduced_Parent") {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        //ADD DETAILS

                                                                                        //END DETAILS
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("activityAssigned_WMC", "Activity Assigned:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("activityAssignedValue_WMC", "_") {
                                                                                            {
                                                                                                width("140px");
                                                                                                height("40px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageProbabilityFailure_WMC", "% Probability of Failure:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageProbabilityFailureValue_WMC", "_") {
                                                                                            {
                                                                                                width("100px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("performPreventiveMaintenance_WMC", "Preventive Maintenance:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("performPreventiveMaintenanceValue_WMC", "_") {
                                                                                            {
                                                                                                width("60px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new ButtonBuilder("performPreventiveMaintenanceButton", "Perform") {
                                                                                            {
                                                                                                width("60px");
                                                                                                alignCenter();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        control(new LabelBuilder("messageResult_WMC", "") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new ButtonBuilder("machineUpdate", "Update") {
                                                                            {
                                                                                width("100%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        visible(false);
                                                        x("163px");
                                                        y("28px");
                                                        width("450px");
                                                        height("415px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winSSC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new StorageStationControl());
                                                control(new WindowBuilder("winStorageStationControl", "Station") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        valignTop();
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("90px");
                                                                        control(new LabelBuilder("text_WSSC", "Stations list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("stationsList_WSSC") {
                                                                            {
                                                                                displayItems(8);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder("stationParent_WSSC") {
                                                                    {
                                                                        childLayoutVertical();
                                                                        visibleToMouse(true);
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("400px");
                                                        height("390px");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder("winPC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new PartControl());
                                                control(new WindowBuilder("winPartControl", "Part") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("90px");
                                                                        control(new LabelBuilder("text_WPC", "Parts list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("partsList_WPC") {
                                                                            {
                                                                                displayItems(10);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("id_WPC", "Part ID:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("idValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("name_WPC", "Name:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("nameValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("unit_WPC", "Unit:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("unitValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("currentStock_WPC", "Current Stock:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("currentStockValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("priceForSale_WPC", "Price For Sale:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("priceForSaleValue_WPC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("outputQuantity_WPC", "Output Quantity:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("outputQuantityValue_WPC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("10px"));
                                                                        control(new LabelBuilder("assemblySection_WPC", "Assembly Section") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlign(Align.Center);
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new TextFieldBuilder("assemblyPartRequired", "  Part required") {
                                                                                    {
                                                                                        width("140px");
                                                                                    }
                                                                                });
                                                                                control(new TextFieldBuilder("assemblyQuantity", "  Quantity") {
                                                                                    {
                                                                                        width("84px");
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("assemblySectionValue_WPC") {
                                                                            {
                                                                                displayItems(3);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("*");
                                                                                control(new ControlBuilder("customListBox_AssemblyDetail") {
                                                                                    {
                                                                                        controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("340px");
                                                        height("310px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winAC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new ActivityControl());
                                                control(new WindowBuilder("winActivityControl", "Activity") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("110px");
                                                                        control(new LabelBuilder("text_WAC", "Activities list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("activitiesList_WAC") {
                                                                            {
                                                                                displayItems(10);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder("winAC_Parent") {
                                                                            {
                                                                                childLayoutVertical();
                                                                                visibleToMouse(true);
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("id_WAC", "Activity ID:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("idValue_WAC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("description_WAC", "Description:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("descriptionValue_WAC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("37px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("typeActivity_WAC", "Type Activity:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("typeActivityValue_WAC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("part_WAC", "Part:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("partValue_WAC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("10px"));
                                                                                //ADD TYPE_ACTIVITY

                                                                                //END TYPE_ACTIVITY
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        control(new LabelBuilder("messageResult_WAC", "") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("10px"));
                                                                        control(new ButtonBuilder("activityRefresh", "Refresh") {
                                                                            {
                                                                                width("100%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("490px");
                                                        y("28px");
                                                        visible(false);
                                                        width("380px");
                                                        height("455px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winOvC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new OverallControl());
                                                control(new LabelBuilder("OverallLabel", "Overall") {
                                                    {
//                                                        this.backgroundColor(de.lessvoid.nifty.tools.Color.BLACK);
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        backgroundImage("Interface/panelBack3.png");
                                                        x("948px");
                                                        y("700px");
                                                        width("330px");
                                                        interactOnClick("ShowWindow()");
                                                    }
                                                });
                                                control(new WindowBuilder("winOverallControl", "Overall") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        interactOnClick("HideWindow()");
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();

                                                                        control(new LabelBuilder("currentMoney_WOvC", "Available Cash:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("currentMoneyValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("currentMoneyValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                //TOTAL EXPENDITURES
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("totalExpenditures_WOvC", "Total Expenditures:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalExpendituresValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalExpendituresValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        //ADD DETAILS
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("20px"));
                                                                                image(new ImageBuilder("imageOperatorCosts") {
                                                                                    {
                                                                                        filename("Interface/Principal/add_gray.png");
                                                                                        width("16px");
                                                                                        height("16px");
                                                                                        focusable(true);
                                                                                        interactOnClick("clickToOperatorCosts()");
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("operatorCosts_WOvC", "Operator Costs:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("operatorCostsValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("operatorCostsValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder("operatorCosts_parent") {
                                                                            {
                                                                                childLayoutVertical();

                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("20px"));
                                                                                image(new ImageBuilder("imageMachineEquipmentCosts") {
                                                                                    {
                                                                                        filename("Interface/Principal/add_gray.png");
                                                                                        width("16px");
                                                                                        height("16px");
                                                                                        focusable(true);
                                                                                        interactOnClick("clickToMachineEquipmentCosts()");
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("machineEquipmentCosts_WOvC", "Machine & Equipment Costs:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("machineEquipmentCostsValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("machineEquipmentCostsValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder("machineEquipmentCosts_parent") {
                                                                            {
                                                                                childLayoutVertical();

                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("20px"));
                                                                                image(new ImageBuilder("imageOtherCosts") {
                                                                                    {
                                                                                        filename("Interface/Principal/add_gray.png");
                                                                                        width("16px");
                                                                                        height("16px");
                                                                                        focusable(true);
                                                                                        interactOnClick("clickToOtherCosts()");
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("otherCosts_WOvC", "Other Costs:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("otherCostsValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("otherCostsValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder("otherCosts_parent") {
                                                                            {
                                                                                childLayoutVertical();

                                                                            }
                                                                        });
                                                                        //END DETAILS
                                                                    }
                                                                });
                                                                //TOTAL INCOMES
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("totalIncomes_WOvC", "Total Incomes:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalIncomesValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalIncomesValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("35px"));
                                                                                control(new LabelBuilder("saleMachine_WOvC", "Machine Sale:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("saleMachineValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("saleMachineValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("35px"));
                                                                                control(new LabelBuilder("saleEquipment_WOvC", "Equipment Sale:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("saleEquipmentValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("saleEquipmentValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("35px"));
                                                                                control(new LabelBuilder("incomeForSales_WOvC", "Part Sale:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("incomeForSalesValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("incomeForSalesValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                //TOTAL PROFIT
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();

                                                                        control(new LabelBuilder("totalProfit_WOvC", "Total Profit:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalProfitValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalProfitValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("948px");
                                                        y("488px");
                                                        visible(false);
                                                        width("330px");
                                                        height("230px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winSuC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new SupplierControl());
                                                control(new WindowBuilder("winSupplierControl", "Supplier") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("90px");
                                                                        control(new LabelBuilder("text_WSuC", "Supplier list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("suppliersList_WSuC") {
                                                                            {
                                                                                displayItems(10);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("id_WSuC", "Supplier ID:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("idValue_WSuC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("name_WSuC", "Name:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("nameValue_WSuC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("10px"));
                                                                                control(new LabelBuilder("catalogSection_WSuc", "Catalog Section") {
                                                                                    {
                                                                                        width("100%");
                                                                                        height("20px");
                                                                                        textHAlign(Align.Center);
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("5px"));
                                                                                control(new ListBoxBuilder("catalogSectionValue_WSuC") {
                                                                                    {
                                                                                        displayItems(8);
                                                                                        selectionModeSingle();
                                                                                        optionalVerticalScrollbar();
                                                                                        hideHorizontalScrollbar();
                                                                                        width("90%");
                                                                                    }
                                                                                });
                                                                                width("50%");
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("catalogSelected_WSuc", "Catalog Selected") {
                                                                                    {
                                                                                        width("100%");
                                                                                        height("20px");
                                                                                        textHAlign(Align.Center);
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("5px"));
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("part_WSuC", "Part:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("partValue_WSuC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("25px");
                                                                                        control(new LabelBuilder("productionDistn_WSuC", "Production Distn:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionDistnValue_WSuC") {
                                                                                            {
                                                                                                width("100px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("productionParam1_WSuC", "Production Param1:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionParam1Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("productionParam2_WSuC", "Production Param2:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionParam2Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("productionCalculated_WSuC", "Production Calc.:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionCalculatedValue_WSuC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceLimit1_WSuC", "Price Limit 1:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceLimit1Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceCharge1_WSuC", "Price Charge 1:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceCharge1Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceLimit2_WSuC", "Price Limit 2:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceLimit2Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceCharge2_WSuC", "Price Charge 2:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceCharge2Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceLimit3_WSuC", "Price Limit 3:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceLimit3Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceCharge3_WSuC", "Price Charge 3:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceCharge3Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("50%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("500px");
                                                        height("310px");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder("winOrC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new OrderControl());
                                                control(new LabelBuilder("OrderLabel", "Order") {
                                                    {
                                                        
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
//                                                        this.backgroundColor(de.lessvoid.nifty.tools.Color.BLACK);
                                                        backgroundImage("Interface/panelBack3.png");
                                                        x("2px");
                                                        y("700px");
                                                        width("330px");
                                                        interactOnClick("ShowWindow()");

                                                        //interactOnClick("HideLabel()");

                                                    }
                                                });
                                                control(new WindowBuilder("winOrderControl", "Order") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        interactOnClick("HideWindow()");
                                                        //interactOnClick();
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("objective_WOrC", "Objective:") {
                                                                            {
                                                                                width("55px");
                                                                                height("20px");
                                                                                textHAlign(order_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("objectiveValue_WOrC", "_") {
                                                                            {
                                                                                width("265px");
                                                                                height("20px");
                                                                                textHAlign(order_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("totalOrders_WOrC", "Successful / Total Orders:") {
                                                                            {
                                                                                width("180px");
                                                                                height("20px");
                                                                                textHAlign(order_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalOrdersValue_WOrC", "_") {
                                                                            {
                                                                                width("140px");
                                                                                height("20px");
                                                                                textHAlign(order_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("maxFailedOrders_WOrC", "Failed / Max.Failed Orders:") {
                                                                            {
                                                                                width("180px");
                                                                                height("20px");
                                                                                textHAlign(order_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("maxFailedOrdersValue_WOrC", "_") {
                                                                            {
                                                                                width("140px");
                                                                                height("20px");
                                                                                textHAlign(order_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new TextFieldBuilder("indexOrder_WOrC", "No") {
                                                                            {
                                                                                width("20px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("partRequired_WOrC", "     Part Required") {
                                                                            {
                                                                                width("120px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("quantity_WOrC", " Quantity") {
                                                                            {
                                                                                width("60px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("dueDate_WOrC", "       Due Date") {
                                                                            {
                                                                                width("115px");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                control(new ListBoxBuilder("ordersValue_WOrC") {
                                                                    {
                                                                        displayItems(4);
                                                                        selectionModeSingle();
                                                                        optionalVerticalScrollbar();
                                                                        hideHorizontalScrollbar();
                                                                        width("*");
                                                                        control(new ControlBuilder("customListBox_Orders") {
                                                                            {
                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("2px");
                                                        y("488px");
                                                        visible(false);
                                                        width("330px");
                                                        height("230px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winPrC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new PriorityControl());
                                                control(new WindowBuilder("winPriorityControl", "Priority Activity") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        control(new LabelBuilder("text_WPrC", "Set up the priority for each activity:\n(between: 1=most & 10=less priority)") {
                                                                            {
                                                                                width("100%");
                                                                                height("25px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        panel(common.vspacer("10px"));
                                                                        panel(new PanelBuilder("winPrC_Parent") {
                                                                            {
                                                                                childLayoutVertical();
                                                                                //ADD ACTIVITY

                                                                                //END ACTIVITY
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WPrC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("priorityUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("415px");
                                                        height("100px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winULC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new UnitLoadControl());
                                                control(new WindowBuilder("winUnitLoadControl", "Unit Load (Parts per Trip)") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        control(new LabelBuilder("text_WULC", "Set unit load (parts per trip) for each activity:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("10px"));
                                                                        panel(new PanelBuilder("winULC_Parent") {
                                                                            {
                                                                                childLayoutVertical();
                                                                                //ADD ACTIVITY

                                                                                //END ACTIVITY
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WULC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("unitLoadUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("330px");
                                                        height("0px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winAsOpC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new AssignOperatorControl());
                                                control(new WindowBuilder("winAssignOperatorControl", "Assign Operators") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        control(new LabelBuilder("text_WAsOpC", "Assign operators for each activity:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutVertical();
                                                                                        width("120px");
                                                                                        control(new LabelBuilder("text2_WAsOpC", "Activities list:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new ListBoxBuilder("activitiesList_WAsOpC") {
                                                                                            {
                                                                                                displayItems(10);
                                                                                                selectionModeSingle();
                                                                                                optionalVerticalScrollbar();
                                                                                                hideHorizontalScrollbar();
                                                                                                width("100%");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("10px"));
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutVertical();
                                                                                        width("360px");
                                                                                        control(new LabelBuilder("description_WAsOpC") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(common.vspacer("3px"));
                                                                                        control(new LabelBuilder("text3_WAsOpC", "Material handler:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(new PanelBuilder("winMH_AsOpC_Parent") {
                                                                                            {
                                                                                                childLayoutVertical();
                                                                                                //ADD ACTIVITY
                                                                                            }
                                                                                        });
                                                                                        panel(common.vspacer("5px"));
                                                                                        control(new LabelBuilder("text4_WAsOpC", "Line operator:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(new PanelBuilder("winLO_AsOpC_Parent") {
                                                                                            {
                                                                                                childLayoutVertical();
                                                                                                //ADD ACTIVITY
                                                                                            }
                                                                                        });
                                                                                        panel(common.vspacer("5px"));
                                                                                        control(new LabelBuilder("text5_WAsOpC", "Versatile:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(new PanelBuilder("winV_AsOpC_Parent") {
                                                                                            {
                                                                                                childLayoutVertical();
                                                                                                //ADD ACTIVITY
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("text11_WAsOpC", "(?) : Number of operators/activities assigned") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                control(new LabelBuilder("messageResult_WAsOpC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("assingOperatorUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("500px");
                                                        height("500px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winFCC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new FlowChartControl());
                                                control(new WindowBuilder("winFlowChartControl", "Process Flow Chart") {
                                                    {                                             
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                image(new ImageBuilder("imageFlowOfActivities") {
                                                                    {
                                                                        filename("Models/Flows/none.png");
                                                                        width("473px");
                                                                        height("383px");
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new ButtonBuilder("closeFlowChart", "Close") {
                                                                    {
                                                                        width("100%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("245px");
                                                        y("30px");
                                                        visible(false);
                                                        width("493px");
                                                        height("448px");
                                                    }
                                                });
                                            }
                                        });




                                        panel(new PanelBuilder("winGLC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new GameLogControl());

                                                control(new LabelBuilder("LogLabel", "Game Log") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        //this.backgroundColor(de.lessvoid.nifty.tools.Color.BLACK);//.NONE);//
                                                        backgroundImage("Interface/panelBack3.png");
                                                        x("334px");
                                                        y("700px");
                                                        width("612px");
                                                        interactOnClick("HideWindow()");
                                                        
                                                    }
                                                });


                                                control(new WindowBuilder("winGameLogControl", "Game Log") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        interactOnClickRepeat("showHide()");
                                                        closeable(false);
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new TextFieldBuilder("time_WGLC", " Date Time") {
                                                                            {
                                                                                width("130px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("message_WGLC", " Message") {
                                                                            {
                                                                                width("467px");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                control(new ListBoxBuilder("gameLog_WGLC") {
                                                                    {
                                                                        displayItems(7);
                                                                        selectionModeSingle();
                                                                        optionalVerticalScrollbar();
                                                                        hideHorizontalScrollbar();
                                                                        width("*");
                                                                        control(new ControlBuilder("customListBox_GameLog") {
                                                                            {
                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("334px");
                                                        y("488px");
                                                        visible(false);
                                                        width("612px");
                                                        height("230px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winGSC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new GameSetupControl());
                                                control(new WindowBuilder("winGameSetupControl", "Game Setup") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("setupDescription", Params.setupDescription) {
                                                                            {
                                                                                textHAlignLeft();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupResources") {
                                                                            {
                                                                                filename("Interface/Principal/resources2.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupResources()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupResources", Params.setupResources) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupResourcesStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupResources()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupStorage") {
                                                                            {
                                                                                filename("Interface/Principal/storage.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupStorage()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupStorage", Params.setupStorage) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupStorageStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupStorage()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupUnitLoad") {
                                                                            {
                                                                                filename("Interface/Principal/unit_load.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupUnitLoad()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupUnitLoad", Params.setupUnitLoad) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupUnitLoadStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupUnitLoad()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupPurchase") {
                                                                            {
                                                                                filename("Interface/Principal/purchase.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPurchase()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupPurchase", Params.setupPurchase) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupPurchaseStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPurchase()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupOperators") {
                                                                            {
                                                                                filename("Interface/Principal/assign_operators.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupOperators()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupOperators", Params.setupOperators) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupOperatorsStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupOperators()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupPriority") {
                                                                            {
                                                                                filename("Interface/Principal/priority.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPriority()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupPriority", Params.setupPriority) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupPriorityStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPriority()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("5%"));
                                                                        control(new ButtonBuilder("setupDefaultGame", "Default Setup") {
                                                                            {
                                                                                width("40%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10%"));
                                                                        control(new ButtonBuilder("setupStartGame", "Start Game") {
                                                                            {
                                                                                width("40%");
                                                                                alignCenter();
                                                                                backgroundImage(buttonBackgroundImage);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5%"));
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("743px");
                                                        y("30px");
                                                        visible(false);
                                                        width("405px");
                                                        height("280px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winChC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new CharactersControl());
                                                control(new WindowBuilder("winCharactersControl", "Resources Management") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("operator_WChC", "Operators:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("operatorsText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("operatorsValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("hire_WChC", "Hire x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("hireValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("carrier_WChC", " -Mat.Handler:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("carrierText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("carrierValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("fire_WChC", "Fire x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("fireValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });

                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("assembler_WChC", " -Operator:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("assemblerText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("assemblerValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("versatile_WChC", " -Versatile:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("versatileText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("versatileValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("machines_WChC", "Machines:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("machinesText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("machinesValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("buyMachine_WChC", "Buy x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("buyMachineValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("245px"));
                                                                        control(new LabelBuilder("sellMachine_WChC", "Sell x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("sellMachineValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("equipment_WChC", "Equipment:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("equipmentText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("equipmentValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("buyEquipment_WChC", "Buy x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("buyEquipmentValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("245px"));
                                                                        control(new LabelBuilder("sellEquipment_WChC", "Sell x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("sellEquipmentValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("245px"));
                                                                        control(new LabelBuilder("total_WChC", "Total :") {
                                                                            {
                                                                                width("45px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WChC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("charactersUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("390px");
                                                        height("300px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winASCC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new StorageCostControl());
                                                control(new WindowBuilder("winStorageCostControl", "Assign Storage Costs") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                control(new LabelBuilder("description_WASCC", "Select the number of slots available for each storage") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignLeft();
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder("storageCostParent") {
                                                                    {
                                                                        childLayoutVertical();
                                                                        //add storages

                                                                        //end storages
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("295px"));
                                                                        control(new LabelBuilder("totalCost_WASCC", "Total Cost/Hour:") {
                                                                            {
                                                                                width("100px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalCostValue_WASCC", "") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WASCC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("assingStorageCostsUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("485px");
                                                        height("320px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winDashboard_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new DashboardControl());
                                                control(new WindowBuilder("winDashboardControl", "Dashboard") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "right", 600));
                                                        onHideEffect(common.createMoveEffect("out", "right", 600));
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                onShowEffect(common.createMoveEffect("in", "right", 600));
                                                                onHideEffect(common.createMoveEffect("out", "right", 600));
                                                                //operators & equipment
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        //operators
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("operator_DB", "Operators :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("hiredTotal_DB", "  - Hired / Total :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("hiredTotalValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("busyIdle_DB", "  - Busy / Idle :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("busyIdleValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("materialHandler_DB", "  - Material Handler :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("materialHandlerValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("lineOperator_DB", "  - Line Operator :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("lineOperatorValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("versatile_DB", "  - Versatile :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("versatileValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("230px");
                                                                                height("120px");
                                                                            }
                                                                        });
                                                                        //line
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                image(new ImageBuilder("imageLineVertical") {
                                                                                    {
                                                                                        filename("Interface/Principal/line_vertical.png");
                                                                                        width("5px");
                                                                                        height("140px");
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                            }
                                                                        });
                                                                        //equipment
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("machineEquipment_DB", "Machines / Equipment :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("machinePurchasedTotal_DB", "  - Machine Purchased / Total :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("machinePurchasedTotalValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("equipmentPurchasedTotal_DB", "  - Equipment Purchased / Total :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("equipmentPurchasedTotalValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("machineBrokenAvailable_DB", "  - Machine Busy / Idle :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("machineBrokenAvailableValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("equipmentBrokenAvailable_DB", "  - Equipment Busy / Idle :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("equipmentBrokenAvailableValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("250px");
                                                                                height("120px");
                                                                            }
                                                                        });
                                                                        height("140px");
                                                                    }
                                                                });
                                                                //line
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(common.vspacer("5px"));
                                                                        image(new ImageBuilder("imageLineHorizontal") {
                                                                            {
                                                                                filename("Interface/Principal/line_horizontal.png");
                                                                                width("*");
                                                                                height("5px");
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        //stations
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("stations_DB", "Stations :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new TextFieldBuilder("stationName_DB", " Station") {
                                                                                            {
                                                                                                width("100px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("stationPart_DB", " Part") {
                                                                                            {
                                                                                                width("60px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("stationQuantity_DB", " Quantity") {
                                                                                            {
                                                                                                width("65px");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                control(new ListBoxBuilder("stationsList_DB") {
                                                                                    {
                                                                                        displayItems(9);
                                                                                        selectionModeSingle();
                                                                                        optionalVerticalScrollbar();
                                                                                        hideHorizontalScrollbar();
                                                                                        width("225px");
                                                                                        control(new ControlBuilder("customListBox_StationsList_DB") {
                                                                                            {
                                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("230px");
                                                                                height("320px");
                                                                            }
                                                                        });
                                                                        //line
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                image(new ImageBuilder("imageLineVertical2") {
                                                                                    {
                                                                                        filename("Interface/Principal/line_vertical.png");
                                                                                        width("5px");
                                                                                        height("*");
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                            }
                                                                        });
                                                                        //transport
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("transport_DB", "Transport :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new TextFieldBuilder("transportName_DB", " Transport") {
                                                                                            {
                                                                                                width("180px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("transportPart_DB", " Part") {
                                                                                            {
                                                                                                width("60px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("transportRequired_DB", " No") {
                                                                                            {
                                                                                                width("40px");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                control(new ListBoxBuilder("transportList_DB") {
                                                                                    {
                                                                                        displayItems(9);
                                                                                        selectionModeSingle();
                                                                                        optionalVerticalScrollbar();
                                                                                        hideHorizontalScrollbar();
                                                                                        width("280px");
                                                                                        control(new ControlBuilder("customListBox_TransportList_DB") {
                                                                                            {
                                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("280px");
                                                                                height("280px");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                //line
                                                            }
                                                        });
                                                        x(1278 - dashboardWidth + "px");
                                                        y(minDashboardPositionY + "px");
                                                        visible(false);
                                                        width(dashboardWidth + "px");
                                                        height(dashboardHeight + "px");
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.build(nifty);
        return screen;
    }

    private static void registerWarningNewGamePopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("warningNewGamePopup") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("280px");
                        height("200px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });
                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("40px");
                                childLayoutHorizontal();
                                control(new LabelBuilder("warningMessage", "You currently have a game in progress,\nare you sure you want to start a new game?") {
                                    {
                                        alignCenter();
                                        width("*");
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                width("*");
                                paddingTop("60px");
                                alignCenter();
                                childLayoutHorizontal();
                                panel(common.hspacer("10px"));
                                control(new ButtonBuilder("warningPopupYes") {
                                    {
                                        label("Yes");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                                panel(common.hspacer("30px"));
                                control(new ButtonBuilder("warningPopupNo") {
                                    {
                                        label("No");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerQuitPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("quitPopup") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("200px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });
                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("40px");
                                childLayoutHorizontal();
                                control(new LabelBuilder("login", "Are you sure you want to quit game?") {
                                    {
                                        alignCenter();
                                        width("*");
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                width("*");
                                paddingTop("60px");
                                alignCenter();
                                childLayoutHorizontal();
                                control(new ButtonBuilder("quitPopupYes") {
                                    {
                                        label("Quit");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                                panel(common.hspacer("20px"));
                                control(new ButtonBuilder("quitPopupNo") {
                                    {
                                        label("Cancel");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerGameUpdatingPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new ControlDefinitionBuilder("progressBarUtility") {
            {
                controller(new ProgressBarController());
                image(new ImageBuilder("imageBorder") {
                    {
                        childLayoutAbsolute();
                        filename("Interface/Principal/border_bar.png");
                        alignCenter();
                        imageMode("resize:15,2,15,15,15,2,15,2,15,2,15,15");
                        image(new ImageBuilder("imageInner") {
                            {
                                filename("Interface/Principal/inner_bar.png");
                                width("32px");
                                height("100%");
                                alignCenter();
                                x("0");
                                y("0");
                                imageMode("resize:15,2,15,15,15,2,15,2,15,2,15,15");
                            }
                        });
                        text(new TextBuilder("textInner") {
                            {
                                text("0%");
                                textHAlignCenter();
                                textVAlignCenter();
                                width("100%");
                                height("100%");
                                x("0");
                                y("0");
                                style("base-font-link");
                                color("#f00f");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new PopupBuilder("gameUpdating") {
            {
                childLayoutCenter();
                backgroundColor("#000a");
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("300px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });
                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                paddingLeft("10px");
                                childLayoutVertical();
                                control(new LabelBuilder("updatingHeader", "Updating...") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                image(new ImageBuilder("updatingImage") {
                                    {
                                        filename("Interface/Principal/update.png");
                                        width("128px");
                                        height("128px");
                                        alignCenter();
                                    }
                                });
                                panel(common.vspacer("10px"));
                                control(new ControlBuilder("progressBarUtility") {
                                    {
                                        alignCenter();
                                        valignCenter();
                                        width("200px");
                                        height("32px");
                                    }
                                });
                                control(new LabelBuilder("updatingElement", "---") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerGameClosingPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("gameClosing") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("300px");
                        height("240px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });
                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameClosingImage") {
                                    {
                                        filename("Interface/Principal/game_closing.png");
                                        width("128px");
                                        height("128px");
                                        alignCenter();
                                    }
                                });
                                control(new LabelBuilder("gameClosingMessage", "Your session will be closed in: 0 seconds") {
                                    {
                                        width("300px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        width("*");
                                        paddingTop("10px");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        panel(common.hspacer("30px"));
                                        control(new ButtonBuilder("gameClosingContinue", "Continue") {
                                            {
                                                alignCenter();
                                                valignCenter();
                                            }
                                        });
                                        panel(common.hspacer("20px"));
                                        control(new ButtonBuilder("gameClosingExit", "Exit") {
                                            {
                                                alignCenter();
                                                valignCenter();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerGameOverPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("gameOverByLostOrders") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("300px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });

//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });

                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameOverImage") {
                                    {
                                        filename("Interface/Principal/gameover.png");
                                        width("200px");
                                        height("200px");
                                        alignCenter();
                                    }
                                });
                                control(new LabelBuilder("gameOverMessage", "You missed too many orders!!!") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        paddingTop("10px");
                                        width("*");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        control(new ButtonBuilder("gameOverRestartO", "Restart"));
                                        panel(common.hspacer("20px"));
                                        control(new ButtonBuilder("gameOverQuitO", "Quit"));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);

        new PopupBuilder("gameOverByBankruptcy") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("300px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });

//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });

                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameOverImage") {
                                    {
                                        filename("Interface/Principal/gameover.png");
                                        width("200px");
                                        height("200px");
                                        alignCenter();
                                    }
                                });
                                control(new LabelBuilder("gameOverMessage", "You fell into bankruptcy!!!") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        paddingTop("10px");
                                        width("*");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        control(new ButtonBuilder("gameOverRestartB", "Restart"));
                                        panel(common.hspacer("20px"));
                                        control(new ButtonBuilder("gameOverQuitB", "Quit"));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);

        new PopupBuilder("youWon") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("360px");
                        height("280px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(10);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });

//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });

                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameOverImage") {
                                    {
                                        filename("Interface/Principal/youwon.png");
                                        width("200px");
                                        height("200px");
                                        alignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        width("*");
                                        paddingTop("10px");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        control(new ButtonBuilder("gameWonRestart", "Restart"));
                                        panel(common.hspacer("10px"));
                                        control(new ButtonBuilder("gameWonNextGame", "Next Game"));
                                        panel(common.hspacer("10px"));
                                        control(new ButtonBuilder("gameWonQuit", "Quit"));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerCreditsPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();
        new PopupBuilder("creditsPopup") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        width("80%");
                        height("80%");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        onActiveEffect(new EffectBuilder("gradient") {
                            {
                                effectValue("offset", "0%", "color", "#00bffecc");
                                effectValue("offset", "75%", "color", "#00213cff");
                                effectValue("offset", "100%", "color", "#880000cc");
                            }
                        });
                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                width("100%");
                                height("*");
                                childLayoutOverlay();
                                childClip(true);
                                panel(new PanelBuilder() {
                                    {
                                        width("100%");
                                        childLayoutVertical();
                                        onActiveEffect(new EffectBuilder("autoScroll") {
                                            {
                                                length(20000);//100000
                                                effectParameter("start", "0");
                                                effectParameter("end", "-3200");
                                                inherit(true);
                                            }
                                        });
                                        panel(common.vspacer("800px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Nifty 1.3");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text("Standard Controls Demonstration using JavaBuilder pattern");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("30px"));
                                        text(new TextBuilder() {
                                            {
                                                text("\"Look ma, No XML!\" :)");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("70px"));
                                        panel(new PanelBuilder() {
                                            {
                                                width("100%");
                                                height("256px");
                                                childLayoutCenter();
                                                panel(new PanelBuilder() {
                                                    {
                                                        alignCenter();
                                                        valignCenter();
                                                        childLayoutHorizontal();
                                                        width("656px");
                                                        panel(new PanelBuilder() {
                                                            {
                                                                width("200px");
                                                                height("256px");
                                                                childLayoutCenter();
                                                                text(new TextBuilder() {
                                                                    {
                                                                        text("Nifty 1.3 Core");
                                                                        style("base-font");
                                                                        alignCenter();
                                                                        valignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        panel(new PanelBuilder() {
                                                            {
                                                                width("256px");
                                                                height("256px");
                                                                alignCenter();
                                                                valignCenter();
                                                                childLayoutOverlay();
                                                                image(new ImageBuilder() {
                                                                    {
                                                                        filename("Interface/yin.png");
                                                                    }
                                                                });
                                                                image(new ImageBuilder() {
                                                                    {
                                                                        filename("Interface/yang.png");
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        panel(new PanelBuilder() {
                                                            {
                                                                width("200px");
                                                                height("256px");
                                                                childLayoutCenter();
                                                                text(new TextBuilder() {
                                                                    {
                                                                        text("Nifty 1.3 Standard Controls");
                                                                        style("base-font");
                                                                        alignCenter();
                                                                        valignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                        panel(common.vspacer("70px"));
                                        text(new TextBuilder() {
                                            {
                                                text("written and performed\nby void");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Sound Credits");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text("This demonstration uses creative commons licenced sound samples\nand music from the following sources");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("30px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/freesound.png");
                                            }
                                        });
                                        panel(common.vspacer("25px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Interface/19546__tobi123__Gong_mf2.wav");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("50px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/cc-mixter-logo.png");
                                                set("action", "openLink(http://ccmixter.org/)");
                                            }
                                        });
                                        panel(common.vspacer("25px"));
                                        text(new TextBuilder() {
                                            {
                                                text("\"Almost Given Up\" by Loveshadow");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Additional Credits");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text("ueber awesome Yin/Yang graphic by Dori\n(http://www.nadori.de)\n\nThanks! :)");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Special thanks go to");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text(
                                                        "The following people helped creating Nifty with valuable feedback,\nfixing bugs or sending patches.\n(in no particular order)\n\n"
                                                        + "chaz0x0\n"
                                                        + "Tumaini\n"
                                                        + "arielsan\n"
                                                        + "gaba1978\n"
                                                        + "ractoc\n"
                                                        + "bonechilla\n"
                                                        + "mdeletrain\n"
                                                        + "mulov\n"
                                                        + "gouessej\n");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("75px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Greetings and kudos go out to");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text(
                                                        "Ariel Coppes and Ruben Garat of Gemserk\n(http://blog.gemserk.com/)\n\n\n"
                                                        + "Erlend, Kirill, Normen, Skye and Ruth of jMonkeyEngine\n(http://www.jmonkeyengine.com/home/)\n\n\n"
                                                        + "Brian Matzon, Elias Naur, Caspian Rychlik-Prince for lwjgl\n(http://www.lwjgl.org/\n\n\n"
                                                        + "KappaOne, MatthiasM, aho, Dragonene, darkprophet, appel, woogley, Riven, NoobFukaire\nfor valuable input and discussions at #lwjgl IRC on the freenode network\n\n\n"
                                                        + "... and Kevin Glass\n(http://slick.cokeandcode.com/)\n\n\n\n\n\n\n\n"
                                                        + "As well as everybody that has not yet given up on Nifty =)\n\n"
                                                        + "And again sorry to all of you that I've forgotten. You rock too!\n\n\n");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("350px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/nifty-logo.png");
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                width("100%");
                                paddingTop("10px");
                                childLayoutCenter();
                                control(new ButtonBuilder("creditsBack") {
                                    {
                                        label("Back");
                                        alignRight();
                                        valignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerStyles(final Nifty nifty) {
        new StyleBuilder() {
            {
                id("base-font-link");
                base("base-font");
                color("#8fff");
                interactOnRelease("$action");
                onHoverEffect(new HoverEffectBuilder("changeMouseCursor") {
                    {
                        effectParameter("id", "hand");
                    }
                });
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("creditsImage");
                alignCenter();
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("creditsCaption");
                font("Interface/verdana-48-regular.fnt");
                width("100%");
                textHAlignCenter();
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("creditsCenter");
                base("base-font");
                width("100%");
                textHAlignCenter();
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("nifty-panel-red");
            }
        }.build(nifty);
    }
}
