package com.virtualfactory.app;

import com.virtualfactory.gui.layer.LayerScreen;
import com.virtualfactory.gui.ScreenManager;
import com.virtualfactory.gui.intro.IntroScreen;
import com.virtualfactory.gui.menu.MenuScreen;
import com.virtualfactory.data.GameData;
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
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.GhostControl;
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
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Grid;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import com.virtualfactory.entity.E_Bucket;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.entity.E_Terrain;
import com.virtualfactory.entity.E_TerrainReserved;
import com.virtualfactory.utils.DispOperatorMachineMovingTo;
import com.virtualfactory.utils.DispOperatorWalksTo;
import com.virtualfactory.utils.TerrainMap;
import com.virtualfactory.gui.menu.resources.ControlsDisplay;
import com.virtualfactory.gui.DialogPanelControlDefinition;
import com.virtualfactory.gui.menu.resources.ForgotYourPasswordDisplay;
import com.virtualfactory.gui.layer.resources.GeneralScreenController;
import com.virtualfactory.gui.menu.resources.InitialMenuDisplay;
import com.virtualfactory.gui.menu.resources.LoadGameMenuDisplay;
import com.virtualfactory.gui.menu.resources.MainMenuDisplay;
import com.virtualfactory.gui.menu.resources.MenuScreenController;
import com.virtualfactory.gui.menu.resources.NewGame1MenuDisplay;
import com.virtualfactory.gui.menu.resources.NewUserMenuDisplay;
import com.virtualfactory.gui.menu.resources.OptionsMenuDisplay;
import com.virtualfactory.gui.layer.resources.DashboardControl;
import com.virtualfactory.gui.layer.resources.FlowChartControl;
import com.virtualfactory.gui.layer.resources.GameLogControl;
import com.virtualfactory.gui.layer.resources.GameSetupControl;
import com.virtualfactory.gui.layer.resources.MachineControl;
import com.virtualfactory.gui.layer.resources.OperatorControl;
import com.virtualfactory.gui.layer.resources.OrderControl;
import com.virtualfactory.gui.layer.resources.OverallControl;
import com.virtualfactory.gui.layer.resources.PartControl;
import com.virtualfactory.gui.layer.resources.StorageStationControl;
import com.virtualfactory.pathfinding.Path;
import com.virtualfactory.pathfinding.Path.Step;
import com.virtualfactory.simpack.LinkedListFutureEventList;
import com.virtualfactory.simpack.Sim;
import com.virtualfactory.simpack.SimEvent;
import com.virtualfactory.simpack.TOKEN;
import com.virtualfactory.strategy.ManageEvents;
import com.virtualfactory.threads.CloseGame;
import com.virtualfactory.threads.StationAnimation;
import com.virtualfactory.threads.UpdateSlotsStorage;
import com.virtualfactory.utils.Colors;
import com.virtualfactory.utils.GameSounds;
import com.virtualfactory.utils.GameType;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.ScreenSettings;
import com.virtualfactory.utils.Sounds;
import com.virtualfactory.utils.StationType;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeElements;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
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
public class GameEngine extends AbstractAppState implements AnimEventListener {
    
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
    private Node world;
    private RigidBodyControl worldRigid;
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
    private ArrayList<JmeCursor> cursors;
    private CloseGame closeGame;
    private Geometry showSpotObject;
    private GameSounds gameSounds;
    private ArrayList<Pair<GameSounds, Sounds>> arrGameSounds;
    private boolean isDashboardVisible = false;
    private boolean showHideDashboard = false;
    private long currentDashboardTime;

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
    private GhostControl secondFloorSensor;
    
    private boolean forward = false;
    private boolean backward = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean camUp = false;
    private boolean camDown = false;

    private float playerSpeed = 1.3f;
    
    private Vector3f camDir;
    private Vector3f camLeft;
    
    private Vector3f walkDirection = new Vector3f(0, 0, 0);

    private AudioNode footstep;
    private boolean isDebugCamEnabled = false;
    private boolean topViewEnabled = false;
    private Node shopFloorNode;
    
    private PointLight lamp;
    private PointLight lamp2, lamp3;
  
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
        Params.screenHeight = application.getContext().getSettings().getHeight();
        
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
        createLightBulb();
        
        if (Params.renderer.equalsIgnoreCase(Params.supportedRenderer))
            setupFilter();
        
        initKeys();
        initSoundEffects();
        Params.tempTime = System.currentTimeMillis();
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
        nifty.loadStyleFile("Interface/NiftyJars/nifty-style-black/nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");//Interface/NiftyJars/nifty-default-controls/
        nifty.registerSound("intro", "Interface/sound/19546__tobi123__Gong_mf2.wav");
        nifty.registerMusic("credits", "Interface/sound/Loveshadow_-_Almost_Given_Up.ogg");
//        nifty.setDebugOptionPanelColors(true);
        
        // register the dialog and credits controls
        ScreenManager.registerStyles(nifty);
        ScreenManager.registerPopups(nifty);

        DialogPanelControlDefinition.register(nifty);
        InitialMenuDisplay.register(nifty);
        ForgotYourPasswordDisplay.register(nifty);
        MainMenuDisplay.register(nifty);
        ControlsDisplay.register(nifty);
        NewUserMenuDisplay.register(nifty);
        NewGame1MenuDisplay.register(nifty);
        LoadGameMenuDisplay.register(nifty);
        OptionsMenuDisplay.register(nifty);

        IntroScreen.build(nifty);
        MenuScreen.build(nifty);
        LayerScreen.build(nifty);
        
        if (!Params.DEBUG_ON)
            nifty.gotoScreen("start");
        
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
            System.out.println("DEBUG MODE: Entered to initial menu successfully.");
        }
        Params.gameNarrator = com.virtualfactory.narrator.NarratorAppState.newInstance(assetManager, app.getGuiNode());
        stateManager.attach(Params.gameNarrator);
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
        
        player.setJumpSpeed(45);
        player.setFallSpeed(120);
        player.setGravity(Params.playerGravity);
        player.setPhysicsLocation(new Vector3f(51.68367f, 59.064148f, -292.67755f));
        app.getCamera().setRotation(new Quaternion(0.07086334f, -0.01954512f, 0.0019515193f, 0.99729264f));
        flyCam.setRotationSpeed(1.9499999f);
        player.setViewDirection(new Vector3f(0, 0, 1));
        
        if (topViewEnabled) {
            topViewEnabled = false;
            isDebugCamEnabled = !isDebugCamEnabled;
            cam.setAxes(Params.camAxesLeft, Params.camAxesUp, Params.camAxesDir);
            flyCam.setMoveSpeed(100);
        }
        
        Params.gameNarrator.talk("Press 'T' for a top view of the factory. This is a really long test just to see if the"
                + " test wraps around and does not continue to the infinite and beyond. Change this with a better intro message in the future.", 15);
    }

    private void LoadElementsToDisplay(GameType gameType) {
        createShootable();
        createTerrain();
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

    @Override
    public void update(float tpf) {
        // TEST Second floor sensor
        if (executeGame) {
            if (secondFloorSensor.getOverlappingCount() > 1) // NOTE: getOverlappingCount() has a value of 1 by default
                System.out.println("Character is on second floor.");
        }
        
        if (!isDebugCamEnabled)
            updatePlayerPosition();
        
        simpleUpdateLocal(); // Legacy code
    }
    
    public void updatePlayerPosition() {
        camDir = app.getCamera().getDirection().clone().multLocal(playerSpeed);
        camLeft = app.getCamera().getLeft().clone().multLocal(playerSpeed);
        
        walkDirection.set(0, 0, 0); // reset walkDirection vector
        if (forward)
            walkDirection.addLocal(camDir);
        
        if (backward)
            walkDirection.addLocal(camDir.negate());
        
        if (moveLeft)
            walkDirection.addLocal(camLeft);
        
        if (moveRight)
            walkDirection.addLocal(camLeft.negate());
        
        if (camUp) {
            rotateCamera(-Params.rotationSpeed, cam.getLeft());
        }
        if (camDown) {
            rotateCamera(Params.rotationSpeed, cam.getLeft());

        }


        if (executeGame) {
            
            
            player.setWalkDirection(walkDirection); // walk!
            app.getCamera().setLocation(player.getPhysicsLocation());
            
            if ((Math.abs(System.currentTimeMillis() - Params.tempTime)/1000.00) > 0.5) {
                Params.tempTime = System.currentTimeMillis();
                Vector3f newPosition = app.getCamera().getLocation();
                checkNarratorMessages(newPosition);
            }
            
            if (flyCam.isDragToRotate()) 
                flyCam.setDragToRotate(false);
            
            if (!inputManager.isCursorVisible()) 
                inputManager.setCursorVisible(true);
            
        }
    }
    
     private void rotateCamera(float value, Vector3f axis){
        
        Matrix3f mat = new Matrix3f();
        mat.fromAngleNormalAxis(flyCam.getRotationSpeed() * value, axis);
        
        Vector3f tempUp = cam.getUp();
        Vector3f tempLeft = cam.getLeft();
        Vector3f tempDir = cam.getDirection();
        
        mat.mult(tempUp, tempUp);
        mat.mult(tempLeft, tempLeft);
        mat.mult(tempDir, tempDir);
        
        if (tempDir.getY() > Params.camMaxY ||
                tempDir.getY() < Params.camMinY)
            return;
            
        Quaternion q = new Quaternion();
        q.fromAxes(tempLeft, tempUp, tempDir);
        q.normalizeLocal();
        
        cam.setAxes(q);
    }

    
    private void checkNarratorMessages(Vector3f newPosition) {
        
        if (Params.oldPosition.getY() < Params.SECOND_FLOOR_Y_POS) {
            Params.topViewAvailable = false;
            if (newPosition.getY() > Params.SECOND_FLOOR_Y_POS) {
                Params.topViewAvailable = true;
                Params.gameNarrator.talk("Second Floor.\nPress 'T' for a top view of the factory.", 5);
            }
            else {
                Params.topViewAvailable = false;
            }
        }
        Params.oldPosition.set(newPosition.getX(), newPosition.getY(), newPosition.getZ());
    }
    
    public void simpleUpdateLocal() {
        //Added by Chris
        if (!this.getGeneralScreenController().getPauseStatus() && gameSounds.machineSoundPlaying()) {
            this.gameSounds.pauseSound(Sounds.MachineWorking);
        }
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
            //newMat.setTexture("DiffuseMap", g.getMaterial().getTextureParam("DiffuseMap").getTextureValue());
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
    
    private void createLightBulb() {
        ColorRGBA color = ColorRGBA.White;
        lamp2 = new PointLight();
        lamp2.setPosition(new Vector3f(40, 200, 150));
        lamp2.setColor(color);
        lamp2.setRadius(lamp2.getRadius()/20);
        rootNode.addLight(lamp2);
        
        lamp3 = new PointLight();
        lamp3.setPosition(new Vector3f(43.50383f, 80.081642f, -310.90753f));
        lamp3.setColor(color);
        lamp3.setRadius(lamp2.getRadius());
        rootNode.addLight(lamp3);

    
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
        
        DirectionalLight dlNew = new DirectionalLight();
        dlNew.setColor(ColorRGBA.White);
        dlNew.setDirection(new Vector3f(0f, 40f, 0));
        rootNode.addLight(dlNew);
        
        DirectionalLight dlOpposite = new DirectionalLight();
        dlOpposite.setColor(ColorRGBA.White);
        dlOpposite.setDirection(new Vector3f(2.8f, -2.8f, 2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }
    
    private void createTerrain() {
        E_Terrain tempTerrain = gameData.getMapTerrain();

        /* Factory */
        // ----------
        world = (Node) assetManager.loadModel("Models/World28/World28.j3o");
        world.getChild("Machine vibration Empty").removeFromParent();
        world.setLocalScale(250.0f, 250.0f, 250.0f);
        world.setLocalTranslation(-9.0f, 0.0f, 82.0f);
        // ----------
        
        /* Factory's Collision Shape */
        // ----------
        CollisionShape worldShape = CollisionShapeFactory.createMeshShape(world);
        worldRigid = new RigidBodyControl(worldShape, 0);
        world.addControl(worldRigid);
        rootNode.attachChild(world);
        bulletAppState.getPhysicsSpace().add(worldRigid);
        // ----------

        /* TEST: Adding a sensor to the second floor (see simpleUpdate() method to see it in action) */
        // ----------
        secondFloorSensor = new GhostControl(new BoxCollisionShape(new Vector3f(90, 5, 25)));
        world.getChild("Mesani Floor").addControl(secondFloorSensor);
        bulletAppState.getPhysicsSpace().add(secondFloorSensor);
        bulletAppState.setDebugEnabled(false); // set to true so you can see the invisible physics engine
        // ----------

        /* First-person Player */
        // ----------
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.4f, 24.5f, 1);
        // Use deprecated CharacterControl until BetterCharacterControl is updated
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(45);
        player.setFallSpeed(120);
        player.setGravity(Params.playerGravity);
        player.setPhysicsLocation(new Vector3f(51.68367f, 59.064148f, -292.67755f));
        app.getCamera().setRotation(new Quaternion(0.07086334f, -0.01954512f, 0.0019515193f, 0.99729264f));
        player.setViewDirection(new Vector3f(0, 0, 1));
        bulletAppState.getPhysicsSpace().add(player);
        // ----------

        //blocked zones
        Map<Integer, E_TerrainReserved> tempBlockedZones = tempTerrain.getArrZones();
        for (E_TerrainReserved tempBlockedZone : tempBlockedZones.values()) {
            setTerrainMap(tempBlockedZone.getLocationX(), tempBlockedZone.getLocationZ(), tempBlockedZone.getWidth(), tempBlockedZone.getLength(), true);
        }
        
        //transformToCartoon(rootNode);
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
        //
        toonFilter.setEdgeWidth(1.0f);
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
        inputManager.deleteMapping("FLYCAM_Up");
        inputManager.deleteMapping("FLYCAM_Down");
        inputManager.deleteTrigger("FLYCAM_Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.deleteTrigger("FLYCAM_Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.deleteTrigger("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));

        inputManager.addMapping("MousePicking", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "MousePicking");

        String[] mappings = {"Forward", "Backward", "Left",
                             "Right", "Cam Up", "Cam Down", "Jump", "Picking",
                             "Dashboard Control", "Top View", "Screen Size",
                             "Scale Bigger", "Scale Smaller",
                             "Activate Debug Cam", "debug position"};
        
        int[] triggers = {KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_A, 
                          KeyInput.KEY_D, KeyInput.KEY_UP, KeyInput.KEY_DOWN, 
                          KeyInput.KEY_SPACE, KeyInput.KEY_LSHIFT, 
                          KeyInput.KEY_RSHIFT, KeyInput.KEY_T, KeyInput.KEY_F1,
                          KeyInput.KEY_ADD, KeyInput.KEY_SUBTRACT,
                          KeyInput.KEY_0, KeyInput.KEY_H, KeyInput.KEY_B,
                          KeyInput.KEY_NUMPAD4, KeyInput.KEY_NUMPAD6,
                          KeyInput.KEY_NUMPAD8, KeyInput.KEY_NUMPAD2,
                          KeyInput.KEY_ADD, KeyInput.KEY_SUBTRACT,
                          KeyInput.KEY_2, KeyInput.KEY_1};
        
        for (int i = 0; i < mappings.length; i++) {
            inputManager.addMapping(mappings[i], new KeyTrigger(triggers[i]));
            inputManager.addListener(actionListener, mappings[i]);
        } 
    }
    
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {   
            
            if (name.equals("Screen Size") && !keyPressed)
                changeScreenSize();
            
            if (!executeGame) {
                return;
            }
            
            switch (name) {
                case "Forward":
                    forward = keyPressed;
                    if (keyPressed && !isDebugCamEnabled) { footstep.play(); }
                    else { if (!backward && !left && !right) { footstep.stop();  } }
                    break;
                    
                case "Backward":
                    backward = keyPressed;
                    if (keyPressed && !isDebugCamEnabled) { footstep.play(); }
                    else { if (!forward && !left && !right) { footstep.stop(); } }
                    break;
                    
                case "Left":
                    moveLeft = keyPressed;
                    if (keyPressed && !isDebugCamEnabled) { footstep.play(); }
                    else { if (!forward && !backward && !right) { footstep.stop(); } }
                    break;
                    
                case "Right":
                    moveRight = keyPressed;
                    if (keyPressed && !isDebugCamEnabled) { footstep.play(); }
                    else { if (!forward && !backward && !left) { footstep.stop(); } }
                    break;
                
                case "Cam Up":
                    camUp = keyPressed;
                    break;
                
                case "Cam Down":
                    camDown = keyPressed;
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
                    
                case "debug position":
                    if (!keyPressed) {

//                        System.out.println("\n\nLa posicion deseada es estaaaaaaaa: " +  cam.getLocation()
//                                + "\nLa direccion deseada es: " + cam.getDirection() + "\nUp: " + cam.getUp()+ "\nLeft: " + cam.getLeft());

                        System.out.println("\n\nLa direccion deseada es: " + cam.getDirection() + "\nUp: " + cam.getUp()
                                + "\nFrustrum top : " + cam.getFrustumTop());
                    }
                    break;
                    
                case "Top View":
                    if (!keyPressed){
                        handleTopView();
                    }
                    break;
                    
                case "Activate Debug Cam":
                    if (!keyPressed) {
                        isDebugCamEnabled = !isDebugCamEnabled;
                    }
                    break;
                case "Jump":
                    if (!keyPressed) {
                        player.jump();
                    }
                    break;

                default:
                    break;
            }
        }
    };
    
    public void changeScreenSize() {
        AppSettings settings = ScreenSettings.generate();
        
        if (app.getContext().getSettings().isFullscreen()) {
            settings.setFullscreen(false);
            settings.setResolution(1280, 720);
        }
        app.setSettings(settings);
        app.restart();

    }
    
    private void handleTopView() {
        
        if (Params.topViewAvailable && !topViewEnabled) {
            isDebugCamEnabled = !isDebugCamEnabled;
            topViewEnabled = true;
            Params.camAxesLeft = cam.getLeft();
            Params.camAxesUp = cam.getUp();
            Params.camAxesDir = cam.getDirection();
            cam.setLocation(new Vector3f(210.75597f, 191.22467f, -111.45984f));
            cam.setAxes(new Vector3f(0.006238699f, 0.0011283755f, 0.9999799f), 
                    new Vector3f(-0.7573153f, 0.6530373f, 0.0039878786f), new Vector3f(-0.6530197f, -0.75732493f, 0.004928589f));
            flyCam.setMoveSpeed(0);
            Params.flyCamRotationSpeed = flyCam.getRotationSpeed();
            flyCam.setRotationSpeed(0);
            world.getChild("Beams-Metal").setCullHint(Spatial.CullHint.Always);
        }
        else if (Params.topViewAvailable && topViewEnabled && isDebugCamEnabled) {
            topViewEnabled = false;
            isDebugCamEnabled = !isDebugCamEnabled;
            cam.setAxes(Params.camAxesLeft, Params.camAxesUp, Params.camAxesDir);
            flyCam.setMoveSpeed(100);
            flyCam.setRotationSpeed(Params.flyCamRotationSpeed);
            world.getChild("Beams-Metal").setCullHint(Spatial.CullHint.Never);
        }
        
    }
        
    private void handlePickedObject(String pickingType) {
        CollisionResults results = new CollisionResults();
        Ray ray;
        if (pickingType.equals("Picking")) { //Picking with the SHIFT Button
            ray = new Ray(cam.getLocation(), cam.getDirection());
            getGeneralScreenController().hideCurrentControlsWindow();
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
            
            loadWindowControl(shootableObject);
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
}