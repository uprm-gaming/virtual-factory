package com.virtualfactory.engine;

import com.virtualfactory.screen.layer.*;
import com.virtualfactory.screen.other.Popups;
import com.virtualfactory.screen.intro.IntroScreen;
import com.virtualfactory.screen.menu.*;
import com.virtualfactory.data.GameData;
import com.jme3.animation.AnimControl;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.*;
import com.jme3.bullet.control.*;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.input.*;
import com.jme3.input.controls.*;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.filters.FadeFilter;
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
import com.virtualfactory.tutorial.Objective;
import com.virtualfactory.tutorial.Tutorial;
import com.virtualfactory.utils.*;
import de.lessvoid.nifty.tools.SizeValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class GameEngine extends AbstractAppState {

    protected BulletAppState bulletAppState;
    Node model;
    Material matCharacter;
    float angle;
    float cont = 0;
    CharacterControl character_Disp;
    private GameData gameData;
    private ManageEvents manageEvents;
    private boolean isLevelStarted;
    private MenuScreenController menuScreenC;
    private LayerScreenController layerScreenC;
    private RigidBodyControl bucketRigid;
    private Box bucketBox;
    private Material bucketMaterial;
    private RigidBodyControl stationRigid;
    private Box stationBox;
    private Material stationMaterial;
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
    private boolean isTutorialFinished = false;
    private boolean isObjectiveFinished = false;

    public SimpleApplication jmonkeyApp;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private GameRunningState gameState;
    private Node guiNode;
    private Node rootNode;
    private InputManager inputManager;
    private FlyByCamera flyCam;
    private Camera cam;
    private ViewPort guiViewPort;

    private Narrator gameNarrator;
    private Node factoryNode;

    @Override
    public void initialize(AppStateManager manager, Application application) {
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
        
        if (Params.SKIP_LOGIN_SCREEN)
            niftyGUI.gotoScreen("start");
        else if (Params.DEBUG_ON)
            niftyGUI.gotoScreen("initialMenu");
        else
            niftyGUI.gotoScreen("start");

        gameNarrator = new Narrator(stateManager, assetManager, guiNode);
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
        guiNode = jmonkeyApp.getGuiNode();
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

        deleteDefaultControls();

        String[] mappings = {"Picking", "Toggle Dashboard", 
                             "Toggle Full Screen", "Mouse Picking", "Tutorial Step Forward"};

        Trigger[] triggers = {new KeyTrigger(KeyInput.KEY_LSHIFT), new KeyTrigger(KeyInput.KEY_RSHIFT),
                              new KeyTrigger(KeyInput.KEY_F1), new MouseButtonTrigger(MouseInput.BUTTON_LEFT), 
                              new KeyTrigger(KeyInput.KEY_SPACE)};

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
        inputManager.deleteMapping("FLYCAM_Left");
        inputManager.deleteMapping("FLYCAM_Right");

        inputManager.deleteTrigger("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {

            if (name.equals("Toggle Full Screen") && !keyPressed)
                changeScreenSize();

            if (!isLevelStarted)
                return;

            switch (name) {
                case "Picking": case "Mouse Picking":
                    if (!keyPressed)
                        handlePickedObject(name);
                    break;

                case "Toggle Dashboard":
                    if (!keyPressed)
                        toggleDashBoard();
                    break;

                case "Tutorial Step Forward":
                    if (!keyPressed)
                        if (Params.isTutorialLevel && Params.DEBUG_ON) {
                            if (Params.tutorial.getCurrentStep() == 20)
                                Params.tutorial.setCurrentStep(0);
                            else
                                Params.tutorial.nextStep();
                        }
                        else if (Params.isObjectiveLevel && Params.DEBUG_ON) {
                            if (Params.objective.getCurrentStep() == 20)
                                Params.objective.setCurrentStep(0);
                            else
                                Params.objective.nextStep();
                        }
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
        initialGameId = tempGame.getIdGame();

        updateCursorIcon(1);

        if (newGame) {
            this.getArrGameSounds().clear();
            this.gameSounds.stopSound(Sounds.Background);
            this.gameSounds.stopSound(Sounds.TutorialLevel);
            gameData.createGame(tempGame);
            flushPreviousGame();
            if (gameState == null) {
                factoryNode = new Node();
                gameState = new GameRunningState(bulletAppState);
                gameState.initialize(stateManager, jmonkeyApp, bulletAppState);
            }
            else
                gameState.attachFactory(bulletAppState);
           
        }
        else {
            gameData.loadGame(tempGame);
        }
        
        manageEvents = new ManageEvents(this, gameData);

        gameData.updateTimeOrders();

        resetLayerScreen();

        initSimPack();

        loadElementsToDisplay(newGame ? GameType.New : GameType.Load);
        
        enableLayerScreen();

        updateCursorIcon(0);

        
        if (!isLevelStarted) {
            if (gameData.getCurrentGame().getGameName().equalsIgnoreCase("tutorial")) {
                Params.isTutorialLevel = true;
                Params.tutorial = new Tutorial(gameNarrator);
                Params.tutorial.update();
                getGameSounds().playSound(Sounds.TutorialLevel);
            }
            else if (gameData.getCurrentGame().getGameName().equalsIgnoreCase("objective")) {
                Params.isTutorialLevel = false;
                Params.objective = new Objective(gameNarrator);
                Params.objective.update();
                Params.isObjectiveLevel = true;
//                getGameSounds().playSound(Sounds.TutorialLevel);
            }
            else {
                Params.isObjectiveLevel = false;
                Params.isTutorialLevel = false;
                gameNarrator.talk("Welcome to Virtual Factory!\nPress 'T' for a top view of the factory.", 5);
            }
        }
        else if (!gameData.getCurrentGame().getGameName().equalsIgnoreCase("tutorial")) {
                Params.isTutorialLevel = false;
        }
        
        if (isLevelStarted && !gameData.getCurrentGame().getGameName().equalsIgnoreCase("objective"))
            Params.isObjectiveLevel = false;

        isLevelStarted = true;
    }

    private void flushPreviousGame() {
        rootNode.detachAllChildren();
        resetPhysicsEngine();
        System.gc();

        terrainMap = new TerrainMap();
        shootables = new Node("Shootables");
        rootNode.attachChild(shootables);
    }

    private void resetPhysicsEngine() {
        if (bulletAppState == null) {
            bulletAppState = new BulletAppState();
            bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
            stateManager.attach(bulletAppState);
        } 
        else {
            stateManager.detach(bulletAppState);
            bulletAppState = new BulletAppState();
            bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
            stateManager.attach(bulletAppState);

//            bulletAppState.getPhysicsSpace().destroy();
//            bulletAppState.getPhysicsSpace().create();
        }
    }

    private void resetLayerScreen() {
        getLayerScreenController().updateStartScreen();
        getLayerScreenController().setTimeFactor((float) gameData.getCurrentGame().getTimeFactor());
        getLayerScreenController().setGameNamePrincipal(gameData.getCurrentGame().getGameName());
        getLayerScreenController().setNextDueDate("-");
        getLayerScreenController().setNextPurchaseDueDate("-");
    }

    private void initSimPack() {
        currentSystemStatus = Status.Busy;
        currentSystemTime = gameData.getCurrentGame().getCurrentTime();
        currentTempSystemTime = System.currentTimeMillis();
        Sim.init(currentSystemTime, new LinkedListFutureEventList());
        Sim.schedule(new SimEvent(Sim.time() + getLayerScreenController().getTimeFactor(), Params.startEvent));
    }

    private void loadElementsToDisplay(GameType gameType) {
        
        E_Terrain tempTerrain = gameData.getMapTerrain();

        //blocked zones
        Map<Integer, E_TerrainReserved> tempBlockedZones = tempTerrain.getArrZones();
        for (E_TerrainReserved tempBlockedZone : tempBlockedZones.values()) {
            setTerrainMap(tempBlockedZone.getLocationX(), tempBlockedZone.getLocationZ(), tempBlockedZone.getWidth(), tempBlockedZone.getLength(), true);
        }

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

        if (Params.isTopViewEnabled) {
            Params.isTopViewEnabled = false;
            Params.viewNumber = 0;
            Params.fadeFilter.setValue(1);
            Params.fadeFilter = new FadeFilter(1.5f);
            cam.setAxes(Params.camAxesLeft, Params.camAxesUp, Params.camAxesDir);
            flyCam.setMoveSpeed(100);
            Params.camMaxX = Params.playerMaxX;
            Params.camMinX = Params.playerMinX;
            Params.camMaxY = Params.playerMaxY;
            Params.camMinY = Params.playerMinY;
            Params.camMaxZ = Params.playerMaxZ;
            Params.camMinZ = Params.playerMinZ;
        }
    }

    private void enableLayerScreen() {
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
        getLayerScreenController().forcePauseGame();
        initialRealSystemTime = System.currentTimeMillis() / 1000;
        currentIdleSystemTime = System.currentTimeMillis() / 1000;
        currentWindowRefreshSystemTime = System.currentTimeMillis() / 1000;
        timeToUpdateSlots = gameData.getCurrentTimeWithFactor();
        getLayerScreenController().hideCurrentControlsWindow();
        getLayerScreenController().showHideDynamicButtons(0);
        getLayerScreenController().showHideDynamicSubLevelButtons(0);

        niftyGUI.getScreen("layerScreen").findElementByName("winOvC_Element").getControl(OverallScreenController.class).HideWindow();
        niftyGUI.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderScreenController.class).HideWindow();
        niftyGUI.getScreen("layerScreen").findElementByName("winGLC_Element").getControl(GameLogScreenController.class).showHide();
    }
    
    @Override
    public void update(float tpf) {
        updateGameDataAndLogic();
        if (gameState != null)
            gameState.update(tpf);
    }

    public void updateGameDataAndLogic() {
        
        if (!this.getLayerScreenController().getPauseStatus() && gameSounds.machineSoundPlaying())
            this.gameSounds.pauseSound(Sounds.MachineWorking);
        
        Params.isLevelStarted = isLevelStarted;
        if (Params.isTutorialLevel) {
            Params.tutorial.update();
            
            if (Params.tutorial.isTutorialCompleted() && !isTutorialFinished) {
                niftyGUI.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderScreenController.class).updateData();
                isTutorialFinished = true;
            }
        }
        if (Params.isObjectiveLevel) {
            Params.objective.update();
            
            if (Params.objective.isObjectiveCompleted() && !isObjectiveFinished) {
                niftyGUI.getScreen("layerScreen").findElementByName("winOrC_Element").getControl(OrderScreenController.class).updateData();
                isObjectiveFinished = true;
            }
        }
        
        if (!isLevelStarted) { 
            return;
        }
        
        if (currentSystemStatus.equals(Status.Busy))
            currentSystemTime += (double) ((System.currentTimeMillis() - currentTempSystemTime) / 1000.0);

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

        // Update dashboard data
        niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardScreenController.class).updateData();

        currentTempSystemTime = System.currentTimeMillis();
        gameData.getCurrentGame().setCurrentTime(currentSystemTime);
        getLayerScreenController().setCurrentGameTime(gameData.getCurrentTimeGame());
        SimEvent nextEvent = Sim.next_event(currentSystemTime, Sim.Mode.SYNC);
        while (nextEvent != null) {
            if (nextEvent.getId() == Params.startEvent) {
                gameData.manageMachineStates();
                manageEvents.executeEvent();
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

    private void toggleDashBoard() {
        if (isDashboardVisible)
            niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").hide();
        else {
            niftyGUI.getScreen("layerScreen").findElementByName("winDashboard_Element").show();
            if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 16)
                Params.tutorial.nextStep();
        }
        isDashboardVisible = !isDashboardVisible;
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
            if (tempToken.getAttribute(1) != 0) {
                tempEvent.setTime((oldTime - currentSystemTime) * getLayerScreenController().getTimeFactor() / tempToken.getAttribute(1) + currentSystemTime);
                if (tempToken.getAttribute(2) == Params.simpackPurchase && tempToken.getAttribute(0) == gameData.getCurrentPurchaseId()) {
                    gameData.setNextPurchaseDueDate((int) ((tempEvent.getTime() - currentSystemTime) * getLayerScreenController().getTimeFactorForSpeed()));
                    getLayerScreenController().setNextPurchaseDueDate(gameData.convertTimeUnistToHourMinute(gameData.getNextPurchaseDueDate()));
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
        shootables.attachChild(stationGeo);
        if (station.getStationType().toString().toUpperCase().contains("Storage".toUpperCase())) {
            //create grid, only in Storages
            //createGrid(station.getStationLocationX() - (int) station.getSizeW() / 2, station.getStationLocationY() - (int) station.getSizeL() / 2, (int) station.getSizeW(), (int) station.getSizeL());
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
        bucketMaterial.setColor("Color", ColorRGBA.Pink);
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
            partMaterial.setColor("Color", ColorRGBA.Cyan);//.setColor("Color", Colors.getColorRGBA(gameData.getMapUserPart().get(bucket.getIdPart()).getPartDesignColor()));
            part.setMaterial(partMaterial);
            rootNode.attachChild(part);
            shootables.attachChild(part);
        } else {
            part.setMesh(partBox);
        }
        part.setLocalTranslation(new Vector3f((float) bucket.getCurrentLocationX(), 1.5f + (float) bucket.getSize() / 2.0f, (float) bucket.getCurrentLocationZ()));
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
        model.setLocalScale(0.2f);
        model.setName(TypeElements.MACHINE + String.valueOf(machine.getIdMachine()));
        model.setLocalTranslation(new Vector3f(machine.getCurrentLocationX(), 0.5f, machine.getCurrentLocationZ()));
        rootNode.attachChild(model);
        // I cannot add an animation because my MACHINES do not support animation!!!
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

    public void changeScreenSize() {
        AppSettings settings = ScreenSettings.generate();

        if (jmonkeyApp.getContext().getSettings().isFullscreen()) {
            settings.setFullscreen(false);
            settings.setResolution(1280, 720);
            Params.screenHeight = 720;
        }
        else {
            Params.screenHeight = settings.getHeight();
        }
        
        updateInterfacePosition();
        jmonkeyApp.setSettings(settings);
        jmonkeyApp.restart();
    }

    private void updateInterfacePosition() {
        int yPos = Params.screenHeight - (720 - 700);
        niftyGUI.getScreen("layerScreen").findElementByName("OrderLabel").setConstraintY(new SizeValue(yPos + "px"));
        niftyGUI.getScreen("layerScreen").findElementByName("OverallLabel").setConstraintY(new SizeValue(yPos + "px"));
        niftyGUI.getScreen("layerScreen").findElementByName("LogLabel").setConstraintY(new SizeValue(yPos + "px"));

        yPos = Params.screenHeight - (720 - 488);
        niftyGUI.getScreen("layerScreen").findElementByName("winOrderControl").setConstraintY(new SizeValue(yPos + "px"));
        niftyGUI.getScreen("layerScreen").findElementByName("winGameLogControl").setConstraintY(new SizeValue(yPos + "px"));
        niftyGUI.getScreen("layerScreen").findElementByName("winOvC_Element").getControl(OverallScreenController.class).refresh(isLevelStarted);

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

            if (closest.getDistance() > 60f && !gameState.isTopViewEnabled()) {
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
            if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 20)
                Params.tutorial.nextStep();
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

            for (Pair<GameSounds, Sounds> gs : arrGameSounds) {
                if (gs.getSecond() != Sounds.TutorialLevel)
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
        return isLevelStarted;
    }

    public void setExecuteGame(boolean isLevelStarted) {
        this.isLevelStarted = isLevelStarted;
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

    public double getCurrentSystemTime() {
        return currentSystemTime;
    }

    public void setCurrentSystemTime(double currentSystemTime) {
        this.currentSystemTime = currentSystemTime;
    }

    public ArrayList<StationAnimation> getArrStationAnimations() {
        return arrStationAnimations;
    }

    public Nifty getNifty() {
        return niftyGUI;
    }

    public int getInitialGameId() {
        return initialGameId;
    }

    public void setInitialGameId(int initialGameId) {
        this.initialGameId = initialGameId;
    }
}