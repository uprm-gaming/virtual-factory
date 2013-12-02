package com.virtualfactory.app;

import com.virtualfactory.screen.layer.*;
import com.virtualfactory.screen.other.Popups;
import com.virtualfactory.screen.intro.IntroScreen;
import com.virtualfactory.screen.menu.*;
import com.virtualfactory.data.GameData;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.*;
import com.jme3.bullet.control.*;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.input.*;
import com.jme3.input.controls.*;
import com.jme3.light.*;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.*;
import com.jme3.scene.debug.Grid;
import com.jme3.scene.shape.*;
import com.jme3.scene.shape.Line;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import com.virtualfactory.entity.*;
import com.virtualfactory.narrator.Narrator;
import com.virtualfactory.screen.layer.components.*;
import com.virtualfactory.pathfinding.Path;
import com.virtualfactory.pathfinding.Path.Step;
import com.virtualfactory.screen.other.Credits;
import com.virtualfactory.simpack.*;
import com.virtualfactory.strategy.ManageEvents;
import com.virtualfactory.threads.*;
import com.virtualfactory.utils.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
    private boolean isPlayerInsideFactory;
    private MenuScreenController menuScreenC;
    private LayerScreenController layerScreenC;
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
    private Nifty niftyGUI;
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

    public SimpleApplication jmonkeyApp;
    private Node rootNode;
    private InputManager inputManager;
    private FlyByCamera flyCam;
    private Camera cam;
    private ViewPort guiViewPort;
    private CharacterControl player;
    private GhostControl secondFloorSensor;
    
    private boolean forward = false;
    private boolean backward = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean lookUp = false;
    private boolean lookDown = false;

    private float playerSpeed = 1.3f;
    
    private Vector3f camDir;
    private Vector3f camLeft;
    
    private Vector3f walkDirection = new Vector3f(0, 0, 0);

    private boolean topViewEnabled = false;

    private PointLight lamp2, lamp3;
    private AssetManager assetManager;
    private AppStateManager stateManager;
  
    @Override
    public void initialize(AppStateManager manager, Application application) {
        Params.tempTime = System.currentTimeMillis();
        Params.screenHeight = application.getContext().getSettings().getHeight();
        
        jmonkeyApp = (SimpleApplication) application;
        
        loadJmonkeyAppResources();

        loadGameData();
        loadGameSounds();
        loadGameControls();

        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, jmonkeyApp.getAudioRenderer(), guiViewPort);
        guiViewPort.addProcessor(niftyDisplay);
        niftyGUI = niftyDisplay.getNifty();
        
        buildGameScreens();
        
        if (Params.DEBUG_ON)
            niftyGUI.gotoScreen("initialMenu");
        else
            niftyGUI.gotoScreen("start");

        Params.gameNarrator = new Narrator(stateManager, assetManager, jmonkeyApp.getGuiNode());
    }
    
    private void loadJmonkeyAppResources() {
        if (jmonkeyApp == null)
            throw new IllegalStateException("jmonkeyApp has not been initialized.");

        stateManager = jmonkeyApp.getStateManager();
        assetManager = jmonkeyApp.getAssetManager();
        inputManager = jmonkeyApp.getInputManager();
        flyCam = jmonkeyApp.getFlyByCamera();
        cam = jmonkeyApp.getCamera();
        guiViewPort = jmonkeyApp.getGuiViewPort();
        rootNode = jmonkeyApp.getRootNode();
    }

    private void loadGameData() {
        gameData = GameData.getInstance();
        gameData.setGameEngine(this);
    }

    private void loadGameSounds() {
        gameSounds = new GameSounds(assetManager);
        arrGameSounds = new ArrayList<>();
    }

    private void loadGameControls() {
        loadAvailableCursors();

        updateCursorIcon(0);

        deleteDefaultControls(); // TODO: Pepe, it is ideal to replace this method with inputManager.clearMappings(),
                                         // but there is only one problem.
        //inputManager.clearMappings();

        String[] mappings = {"Forward", "Backward", 
                             "Move Left", "Move Right", 
                             "Look Up", "Look Down", 
                             "Jump", "Picking", 
                             "Toggle Dashboard", "Toggle Top View", 
                             "Toggle Full Screen", "Debug Position",
                             "Mouse Picking"};
        
        Trigger[] triggers = {new KeyTrigger(KeyInput.KEY_W), new KeyTrigger(KeyInput.KEY_S), 
                              new KeyTrigger(KeyInput.KEY_A), new KeyTrigger(KeyInput.KEY_D), 
                              new KeyTrigger(KeyInput.KEY_UP), new KeyTrigger(KeyInput.KEY_DOWN), 
                              new KeyTrigger(KeyInput.KEY_SPACE), new KeyTrigger(KeyInput.KEY_LSHIFT), 
                              new KeyTrigger(KeyInput.KEY_RSHIFT), new KeyTrigger(KeyInput.KEY_T), 
                              new KeyTrigger(KeyInput.KEY_F1), new KeyTrigger(KeyInput.KEY_H),
                              new MouseButtonTrigger(MouseInput.BUTTON_LEFT)};
        
        for (int i = 0; i < mappings.length; i++) {
            inputManager.addMapping(mappings[i], triggers[i]);
            inputManager.addListener(actionListener, mappings[i]);
        } 
    }

    private void loadAvailableCursors() {
        cursors = new ArrayList<>();
        cursors.add((JmeCursor) assetManager.loadAsset("Interface/Cursor/cursorWood.ico"));
        cursors.add((JmeCursor) assetManager.loadAsset("Interface/Cursor/busy.ani"));    

        inputManager.setCursorVisible(true);   
    }
    
    public void updateCursorIcon(int newCursorValue) {
        inputManager.setMouseCursor(cursors.get(newCursorValue));
    }

    private void deleteDefaultControls() {
        inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);
        inputManager.deleteMapping("FLYCAM_ZoomIn");
        inputManager.deleteMapping("FLYCAM_ZoomOut");
        inputManager.deleteMapping("FLYCAM_Up");
        inputManager.deleteMapping("FLYCAM_Down");

        inputManager.deleteTrigger("FLYCAM_Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.deleteTrigger("FLYCAM_Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.deleteTrigger("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    }
    
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {   
            
            if (name.equals("Toggle Full Screen") && !keyPressed)
                changeScreenSize();
            
            if (!isPlayerInsideFactory) {
                return;
            }
            
            switch (name) {
                case "Forward":
                    forward = keyPressed;
                    break;
                    
                case "Backward":
                    backward = keyPressed;
                    break;
                    
                case "Move Left":
                    moveLeft = keyPressed;
                    break;
                    
                case "Move Right":
                    moveRight = keyPressed;
                    break;
                
                case "Look Up":
                    lookUp = keyPressed;
                    break;
                
                case "Look Down":
                    lookDown = keyPressed;
                    break;
                    
                case "Picking": case "Mouse Picking":
                    if (!keyPressed)
                        handlePickedObject(name);
                    break;
                
                case "Toggle Dashboard":
                    if (!keyPressed) {
                        showHideDashboard = true;
                        manageDashboard();
                    }
                    break;
                    
                case "Debug Position":
                    if (!keyPressed)
                        System.out.println("\n\nDesired direction is: " + cam.getDirection() + "\nUp: " 
                                           + cam.getUp() + "\nFrustrum top : " + cam.getFrustumTop());
                    break;
                    
                case "Toggle Top View":
                    if (!keyPressed)
                        handleTopView();
                    break;

                case "Jump":
                    if (!keyPressed)
                        player.jump();
                    break;

                default:
                    break;
            }
        }
    };

    private void buildGameScreens() {
        if (niftyGUI == null) 
            throw new IllegalStateException("Cannot build game screens. Nifty GUI was not initialized.");

        niftyGUI.loadStyleFile("Interface/NiftyJars/nifty-style-black/nifty-default-styles.xml");
        niftyGUI.loadControlFile("nifty-default-controls.xml");
        niftyGUI.registerSound("intro", "Interface/sound/19546__tobi123__Gong_mf2.wav");
        niftyGUI.registerMusic("credits", "Interface/sound/Loveshadow_-_Almost_Given_Up.ogg");

        IntroScreen.build(niftyGUI);
        MenuScreen.build(niftyGUI);
        LayerScreen.build(niftyGUI);
        
        Credits.register(niftyGUI);
        Popups.register(niftyGUI);
        
        menuScreenC = (MenuScreenController) niftyGUI.getScreen("initialMenu").getScreenController();
        menuScreenC.setGameEngine(this);

        layerScreenC = (LayerScreenController) niftyGUI.getScreen("layerScreen").getScreenController();
        layerScreenC.setGameEngine(this);
    }
    
    public void playGame(E_Game tempGame, boolean newGame) {
        updateCursorIcon(1);

        terrainMap = new TerrainMap();
        initialGameId = tempGame.getIdGame();

        rootNode.detachAllChildren();
        resetPhysicsEngine();
        System.gc(); // garbage collector

        if (newGame) {
            this.getArrGameSounds().clear();
            this.gameSounds.stopSound(Sounds.Background); // added by Chris
            gameData.createGame(tempGame);
        } else {
            gameData.loadGame(tempGame);
        }

        getLayerScreenController().updateStartScreen();
        getLayerScreenController().setTimeFactor((float) gameData.getCurrentGame().getTimeFactor());
        getLayerScreenController().setGameNamePrincipal(gameData.getCurrentGame().getGameName());
        getLayerScreenController().setNextDueDate("-");
        getLayerScreenController().setNextPurchaseDueDate("-");

        loadElementsToDisplay(newGame ? GameType.New : GameType.Load);
        manageEvents = new ManageEvents(this, gameData);

        //initialize Simpack
        currentSystemStatus = Status.Busy;
        currentSystemTime = gameData.getCurrentGame().getCurrentTime();
        currentTempSystemTime = System.currentTimeMillis();// 1000.0;
        Sim.init(currentSystemTime, new LinkedListFutureEventList());
        Sim.schedule(new SimEvent(Sim.time() + getLayerScreenController().getTimeFactor(), Params.startEvent));

        isPlayerInsideFactory = true;
        
        //load extra data
        gameData.updateTimeOrders();

        //show static windows
        niftyGUI.getScreen("layerScreen").findElementByName("winOvC_Element").getControl(OverallScreenController.class).loadWindowControl(this, 0, null);
        niftyGUI.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderScreenController.class).loadWindowControl(this, 0, null);
        niftyGUI.getScreen("layerScreen").findElementByName("winGLC_Element").getControl(GameLogScreenController.class).loadWindowControl(this, 0, null);
        niftyGUI.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupScreenController.class).loadWindowControl(this, -1, null); // -1 because we dont want it to be visible
        niftyGUI.getScreen("layerScreen").findElementByName("winFCC_Element").getControl(FlowChartScreenController.class).loadWindowControl(this, -1, null);
        niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardScreenController.class).loadWindowControl(this, 0, null);
        
        //clean lists
        niftyGUI.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderScreenController.class).cleanOrders();
        niftyGUI.getScreen("layerScreen").findElementByName("winGLC_Element").getControl(GameLogScreenController.class).cleanMessages();
        niftyGUI.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupScreenController.class).updateAllStepStatus(false);
        
        getLayerScreenController().updateQuantityCurrentMoney(gameData.getCurrentMoney());
        updateCursorIcon(0);
        getLayerScreenController().forcePauseGame();
        initialRealSystemTime = System.currentTimeMillis() / 1000;
        currentIdleSystemTime = System.currentTimeMillis() / 1000;
        currentWindowRefreshSystemTime = System.currentTimeMillis() / 1000;
        currentDashboardTime = 0;
        timeToUpdateSlots = gameData.getCurrentTimeWithFactor();
        getLayerScreenController().hideCurrentControlsWindow();
        getLayerScreenController().showHideDynamicButtons(0);
        getLayerScreenController().showHideDynamicSubLevelButtons(0);
        
        niftyGUI.getScreen("layerScreen").findElementByName("winOvC_Element").getControl(OverallScreenController.class).HideWindow();
        niftyGUI.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderScreenController.class).HideWindow();
        niftyGUI.getScreen("layerScreen").findElementByName("winGLC_Element").getControl(GameLogScreenController.class).showHide();
        
        if (topViewEnabled) {
            topViewEnabled = false;
            cam.setAxes(Params.camAxesLeft, Params.camAxesUp, Params.camAxesDir);
            flyCam.setMoveSpeed(100);
        }
        
        Params.gameNarrator.talk("Press 'T' for a top view of the factory. This is a really long test just to see if the"
                + " test wraps around and does not continue to the infinite and beyond. Change this with a better intro message in the future.", 15);
    }

    private void resetPhysicsEngine() {
        if (bulletAppState == null) {
            bulletAppState = new BulletAppState();
            bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
            stateManager.attach(bulletAppState);
        } else {
            bulletAppState.getPhysicsSpace().destroy();
            bulletAppState.getPhysicsSpace().create();
        }
    }

    private void loadElementsToDisplay(GameType gameType) {
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
        if (isPlayerInsideFactory) {
            if (secondFloorSensor.getOverlappingCount() > 1) // NOTE: getOverlappingCount() has a value of 1 by default
                System.out.println("Character is on second floor.");
        }
        
        if (!topViewEnabled)
            updatePlayerPosition();
        
        simpleUpdateLocal(); // Legacy code
    }
    
    public void updatePlayerPosition() {
        camDir = cam.getDirection().clone().multLocal(playerSpeed);
        camLeft = cam.getLeft().clone().multLocal(playerSpeed);
        
        walkDirection.set(0, 0, 0); // reset walkDirection vector
        if (forward)
            walkDirection.addLocal(camDir);
        
        if (backward)
            walkDirection.addLocal(camDir.negate());
        
        if (moveLeft)
            walkDirection.addLocal(camLeft);
        
        if (moveRight)
            walkDirection.addLocal(camLeft.negate());
        
        if (lookUp)
            rotateCamera(-Params.rotationSpeed, cam.getLeft());

        if (lookDown)
            rotateCamera(Params.rotationSpeed, cam.getLeft());

        if (isPlayerInsideFactory) {
            player.setWalkDirection(walkDirection); // walk!
            cam.setLocation(player.getPhysicsLocation());
            
            if ((Math.abs(System.currentTimeMillis() - Params.tempTime)/1000.00) > 0.5) {
                Params.tempTime = System.currentTimeMillis();
                Vector3f newPosition = cam.getLocation();
                checkNarratorMessages(newPosition);
            }
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
        if (!this.getLayerScreenController().getPauseStatus() && gameSounds.machineSoundPlaying()) {
            this.gameSounds.pauseSound(Sounds.MachineWorking);
        }
        if (!isPlayerInsideFactory) {
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
        getLayerScreenController().setCurrentGameTime(gameData.getCurrentTimeGame());
        SimEvent nextEvent = Sim.next_event(currentSystemTime, Sim.Mode.SYNC);
        while (nextEvent != null) {
            if (nextEvent.getId() == Params.startEvent) {
                gameData.manageMachineStates();
                manageEvents.executeEvent();
                //it happens each TIME-UNIT
                Sim.schedule(new SimEvent(Sim.time() + getLayerScreenController().getTimeFactor(), Params.startEvent));
                niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardScreenController.class).updateQuantityPeopleStatus(gameData.getNoUserOperator(Status.Busy), gameData.getNoUserOperator(Status.Idle));
            } else {
                manageEvents.setStrategy(nextEvent.getToken());
                manageEvents.releaseResourcesEvent();
            }
            nextEvent = Sim.next_event(currentSystemTime, Sim.Mode.SYNC);
            getLayerScreenController().updateQuantityCurrentMoney(gameData.getCurrentMoney());
        }
    }

    private void manageDashboard() {
        niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardScreenController.class).updateData();
        if (showHideDashboard) {
            if (isDashboardVisible) {
                niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").hide();
                isDashboardVisible = false;
            } else {
                niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").show();
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
        getLayerScreenController().pauseGame();
        Element exitPopup = niftyGUI.createPopup("gameClosing");
        niftyGUI.showPopup(niftyGUI.getCurrentScreen(), exitPopup.getId(), null);
        niftyGUI.getCurrentScreen().processAddAndRemoveLayerElements();
        closeGame = new CloseGame();
        closeGame.setGameEngine(this);
        closeGame.setScreen(niftyGUI.getCurrentScreen());
        closeGame.setNifty(niftyGUI);
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
                tempEvent.setTime((oldTime - currentSystemTime) * getLayerScreenController().getTimeFactor() / tempToken.getAttribute(1) + currentSystemTime);
//                System.out.println("CHANGED TIME - attritube0:" + tempToken.getAttribute(0) + " - Time:" + currentSystemTime + " - EndTime:" + tempEvent.getTime() + " - OldEndTime:" + oldTime + " - OldFactorTime:" + tempToken.getAttribute(1) + " - NewFactorTime:" + getLayerScreenController().getTimeFactor());
                if (tempToken.getAttribute(2) == Params.simpackPurchase && tempToken.getAttribute(0) == gameData.getCurrentPurchaseId()) {
                    gameData.setNextPurchaseDueDate((int) ((tempEvent.getTime() - currentSystemTime) * getLayerScreenController().getTimeFactorForSpeed()));
                    getLayerScreenController().setNextPurchaseDueDate(gameData.convertTimeUnistToHourMinute(gameData.getNextPurchaseDueDate()));
                    //System.out.println("PURCHASE MISSING REAL_TIME:" + (tempEvent.getTime()-currentSystemTime) + " - GAME_TIME:" + (tempEvent.getTime()-currentSystemTime)*getLayerScreenController().getTimeFactorForSpeed() + " - CLOCK_TIME:" + gameData.getNextPurchaseDueDate());
                }
                tempToken.setAttribute(1, getLayerScreenController().getTimeFactor());
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
        cam.setRotation(new Quaternion(0.07086334f, -0.01954512f, 0.0019515193f, 0.99729264f));
        flyCam.setRotationSpeed(1.9499999f);
        player.setViewDirection(new Vector3f(0, 0, 1));
        bulletAppState.getPhysicsSpace().add(player);
        // ----------

        //blocked zones
        Map<Integer, E_TerrainReserved> tempBlockedZones = tempTerrain.getArrZones();
        for (E_TerrainReserved tempBlockedZone : tempBlockedZones.values()) {
            setTerrainMap(tempBlockedZone.getLocationX(), tempBlockedZone.getLocationZ(), tempBlockedZone.getWidth(), tempBlockedZone.getLength(), true);
        }
        
        boolean isToonFilterSupported = Params.renderer.equalsIgnoreCase(Params.supportedRenderer);
        if (isToonFilterSupported)
            loadToonFilter(); 

        createLightBulb();
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
        arrOperatorsWalksTo.add(new DispOperatorWalksTo(this, operator, posX, posZ, getLayerScreenController().getTimeFactorForSpeed()));
    }

    public void operatorWalksToStaffZone(E_Operator operator, int posX, int posZ) {
        arrOperatorsWalksTo.add(new DispOperatorWalksTo(this, operator, posX, posZ, getLayerScreenController().getTimeFactorForSpeed(), true));
    }

    public void operatorWalksToSpecificMachine(E_Operator operator, E_Machine machine, int posX, int posZ) {
        arrOperatorsWalksTo.add(new DispOperatorWalksTo(this, operator, machine, posX, posZ, getLayerScreenController().getTimeFactorForSpeed()));
    }

    public void operatorAndMachineMovingTo(E_Operator operator, E_Machine machine, int posX, int posZ) {
        arrOperatorsMachinesMovingTo.add(new DispOperatorMachineMovingTo(this, rootNode, operator, machine, posX, posZ, getLayerScreenController().getTimeFactorForSpeed()));
    }

    public void operatorAndMachineMovingToMachineZone(E_Operator operator, E_Machine machine, int posX, int posZ) {
        arrOperatorsMachinesMovingTo.add(new DispOperatorMachineMovingTo(this, rootNode, operator, machine, posX, posZ, getLayerScreenController().getTimeFactorForSpeed(), true));
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

    private void loadToonFilter() {
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        CartoonEdgeFilter toonFilter = new CartoonEdgeFilter();
        toonFilter.setEdgeWidth(1.0f);
        fpp.addFilter(toonFilter);
        jmonkeyApp.getViewPort().addProcessor(fpp);
        //transformToCartoon(rootNode);
    }

    public void transformToCartoon(Spatial spatial) {
        if (spatial instanceof Node) {
            Node n = (Node) spatial;
            
            for (Spatial child : n.getChildren())
                transformToCartoon(child);

        } else if (spatial instanceof Geometry) {
            Geometry g = (Geometry) spatial;
            
            Material newCartoonishMat = new Material(assetManager, "ShaderBlow/MatDefs/LightBlow.j3md");
    
            newCartoonishMat.setTexture("ColorRamp", assetManager.loadTexture("Textures/toon.png"));

            newCartoonishMat.setBoolean("Toon", true);

            g.setMaterial(newCartoonishMat);
        }
    }
    
    public void changeScreenSize() {
        AppSettings settings = ScreenSettings.generate();
        
        if (jmonkeyApp.getContext().getSettings().isFullscreen()) {
            settings.setFullscreen(false);
            settings.setResolution(1280, 720);
        }
        jmonkeyApp.setSettings(settings);
        jmonkeyApp.restart();

    }
    
    private void handleTopView() {
        
        if (Params.topViewAvailable && !topViewEnabled) {
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
        else if (Params.topViewAvailable && topViewEnabled) {
            topViewEnabled = false;
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
            getLayerScreenController().hideCurrentControlsWindow();
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
        getLayerScreenController().hideCurrentControlsWindow();
        getLayerScreenController().showHideDynamicButtons(0);
        getLayerScreenController().showHideDynamicSubLevelButtons(0);
        if (winControl.contains(TypeElements.OPERATOR.toString())) {
            niftyGUI.getScreen("layerScreen").findElementByName("winOC_Element").getControl(OperatorScreenController.class).loadWindowControl(this, Integer.valueOf(winControl.replace(TypeElements.OPERATOR.toString(), "")), positionWC);
            getLayerScreenController().setCurrentOptionselected("windowOperator");
        } else if (winControl.contains(TypeElements.PART.toString())) {
            niftyGUI.getScreen("layerScreen").findElementByName("winPC_Element").getControl(PartScreenController.class).loadWindowControl(this, Integer.valueOf(winControl.split("_")[winControl.split("_").length - 1].replace(TypeElements.PART.toString(), "")), positionWC);
            getLayerScreenController().setCurrentOptionselected("windowPart");
        } else if (winControl.contains(TypeElements.STATION.toString()) && !winControl.contains(TypeElements.BUCKET.toString())) {
            E_Station tempStation = getGameData().getMapUserStation().get(Integer.valueOf(winControl.replace(TypeElements.STATION.toString(), "")));
            if (!tempStation.getStationType().equals(StationType.MachineZone) && !tempStation.getStationType().equals(StationType.StaffZone)) {
                niftyGUI.getScreen("layerScreen").findElementByName("winSSC_Element").getControl(StorageStationScreenController.class).loadWindowControl(this, Integer.valueOf(winControl.replace(TypeElements.STATION.toString(), "")), positionWC);
                getLayerScreenController().setCurrentOptionselected("windowStorageStation");
            }

        } else if (winControl.contains(TypeElements.MACHINE.toString())) {
            niftyGUI.getScreen("layerScreen").findElementByName("winMC_Element").getControl(MachineScreenController.class).loadWindowControl(this, Integer.valueOf(winControl.replace(TypeElements.MACHINE.toString(), "")), positionWC);
            getLayerScreenController().setCurrentOptionselected("windowMachine");
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
            for (Pair<GameSounds, Sounds> gs : arrGameSounds)
                gs.getFirst().playSound(gs.getSecond());
        }
        else {
            for (Pair<GameSounds, Sounds> gs : arrGameSounds)
                gs.getFirst().pauseSound(gs.getSecond());
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
        return isPlayerInsideFactory;
    }

    public void setExecuteGame(boolean isPlayerInsideFactory) {
        this.isPlayerInsideFactory = isPlayerInsideFactory;
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

    public LayerScreenController getLayerScreenController() {
        return layerScreenC;
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
        return niftyGUI;
    }

    public void setNifty(Nifty nifty) {
        this.niftyGUI = nifty;
    }

    public int getInitialGameId() {
        return initialGameId;
    }

    public void setInitialGameId(int initialGameId) {
        this.initialGameId = initialGameId;
    }
}