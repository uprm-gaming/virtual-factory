package com.virtualfactory.engine;

import com.virtualfactory.utils.BulletinBoardControl;
import com.virtualfactory.utils.Sensor;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FadeFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.virtualfactory.narrator.Narrator;
import com.virtualfactory.utils.InvisibleWall;
import com.virtualfactory.utils.Params;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This is an app state (http://hub.jmonkeyengine.org/wiki/doku.php/jme3:advanced:application_states).
 * 
 * When this state is attached by the game engine (see the loadElementsToDisplay() method), initialize() will 
 * create and animate the factory , and update() keeps track of player movement.
 * 
 * @author Abner Coimbre
 */
public class GameRunningState 
{
    private SimpleApplication app;
    private AssetManager assetManager;
    private InputManager inputManager;
    private Node rootNode;
    private Camera cam;
    private FlyByCamera flyCam;  
    
    private boolean moveForward;
    private boolean moveBackward;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean lookUp;
    private boolean lookDown;
    private boolean lookLeft;
    private boolean lookLeftA;
    private boolean lookRight;
    private boolean lookRightD;
    
    private boolean isDebugCamEnabled;
    private boolean isLightingEnabled;
    
    private boolean isPlayerUpstairs = true;       
    private float playerSpeed = 1f;
    
    private Vector3f camDir;
    private Vector3f camLeft;
    private Vector3f walkDirection = new Vector3f(0, 0, 0);
    
    private FilterPostProcessor fpp;
    private ViewPort viewPort;
    private Node factory;
    
    private BulletAppState bulletAppState;
    private CharacterControl player;
    
    private PointLight lamp1;
    private PointLight lamp2;
    
    private Narrator gameNarrator;
    
    private HashMap<String, Sensor> factorySensors;
    private AudioNode cameraMovingSound;
    private boolean isKilled = false;
    private Node grass;
    private Spatial skyBox;
    private ArrayList<Geometry> invisibleWalls;
    private ArrayList<Spatial> bulletinBoardsArray;
    private RigidBodyControl rigidBody;
    
    
    public GameRunningState(BulletAppState bulletAppState)
    {
        this.bulletAppState = bulletAppState;
    }

    public void initialize(AppStateManager stateManager, SimpleApplication app, BulletAppState bulletAppState)
    {
        
        this.app = app;
        this.bulletAppState = bulletAppState;
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.viewPort = this.app.getViewPort();
        this.rootNode = this.app.getRootNode();
        this.flyCam = this.app.getFlyByCamera();
        this.cam = this.app.getCamera();
        this.isPlayerUpstairs = true;
        
        createFactory();
        
        initSoundEffects();
        
        loadPlayerKeyControls();
        
        gameNarrator = new Narrator(stateManager, assetManager, this.app.getGuiNode());
    }
    
    public void createFactory() 
    {
        fpp = new FilterPostProcessor(assetManager);
        Params.fadeFilter = new FadeFilter(1.5f);
        fpp.addFilter(Params.fadeFilter);
        viewPort.addProcessor(fpp);

        flyCam.setMoveSpeed(100);

        /* Factory */
        // ----------
        factory = (Node) assetManager.loadModel("Models/factory-32bit HD.j3o");
        factory.setLocalScale(250.0f, 250.0f, 250.0f);
        factory.setLocalTranslation(-9.0f, 0.0f, 82.0f);
        rootNode.attachChild(factory);
        rigidBody = new RigidBodyControl(0);
        factory.addControl(rigidBody);
        bulletAppState.getPhysicsSpace().add(rigidBody);
        // ----------
        
        grass = (Node) assetManager.loadModel("Models/grass.j3o");
        grass.setLocalScale(250.0f, 250.0f, 250.0f);
        grass.setLocalTranslation(-9.0f, 0.0f, 82.0f);
        rootNode.attachChild(grass);
        
        createSkyBox();
        
        createLighting();
        
        createInvisibleWalls();
        
        createSensors();
        
        createBulletinBoards();

        /* First-person Player */
        // ----------
        player = new CharacterControl(new CapsuleCollisionShape(0.4f, 24.5f, 1), 0.05f);
        setupPlayer();
        bulletAppState.getPhysicsSpace().add(player);
        // ----------
    }
    
    /**
     * Any new sound effects (that is used in more than one method) could be initialized here.
     */
    private void initSoundEffects()
    {
        cameraMovingSound = new AudioNode(assetManager, "Sounds/cameraMovingSound.wav", false);
        cameraMovingSound.setPositional(false);
    }

    private void loadPlayerKeyControls()
    {
        String[] mappings = {"move forward", "move backward",
                             "move left", "move right",
                             "look up", "look down",
                             "look left", "look left A",
                             "look right", "look right D",
                             "toggle top view", "debug cam",
                             "debug position"};

        KeyTrigger[] triggers = {new KeyTrigger(KeyInput.KEY_W), new KeyTrigger(KeyInput.KEY_S),
                                 new KeyTrigger(KeyInput.KEY_Q), new KeyTrigger(KeyInput.KEY_E),
                                 new KeyTrigger(KeyInput.KEY_UP), new KeyTrigger(KeyInput.KEY_DOWN),
                                 new KeyTrigger(KeyInput.KEY_LEFT), new KeyTrigger(KeyInput.KEY_A),
                                 new KeyTrigger(KeyInput.KEY_RIGHT), new KeyTrigger(KeyInput.KEY_D),
                                 new KeyTrigger(KeyInput.KEY_T), new KeyTrigger(KeyInput.KEY_0),
                                 new KeyTrigger(KeyInput.KEY_P)};

        for (int i = 0; i < mappings.length; i++)
        {
            inputManager.addMapping(mappings[i], triggers[i]);
            inputManager.addListener(actionListener, mappings[i]);
        }

    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isKeyPressed, float tpf)
        {
            if (isKilled)
                return;
            
            switch (name)
            {
                case "move forward":
                    moveForward = isKeyPressed;
                    break;

                case "move backward":
                    moveBackward = isKeyPressed;
                    break;

                case "move left":
                    moveLeft = isKeyPressed;     
                    break;

                case "move right":
                    moveRight = isKeyPressed;
                    break;

                case "look up":
                    lookUp = isKeyPressed;
                    handleCameraMovingSound(lookUp);
                    break;

                case "look down":
                    lookDown = isKeyPressed;
                    handleCameraMovingSound(lookDown);
                    break;

                case "look left":
                    lookLeft = isKeyPressed;
                    handleCameraMovingSound(lookLeft);
                    break;
                    
                case "look left A":
                    lookLeftA = isKeyPressed;
                    handleCameraMovingSound(lookLeftA);
                    break;

                case "look right":
                    lookRight = isKeyPressed;                   
                    handleCameraMovingSound(lookRight);                  
                    break;
                    
                case "look right D":
                    lookRightD = isKeyPressed;                   
                    handleCameraMovingSound(lookRightD);                  
                    break;
                    
                case "toggle top view":
                    if (!isKeyPressed && isPlayerUpstairs)
                        toggleTopView();
                    break;

                case "debug cam":
                    if (!isKeyPressed && Params.DEBUG_ON)
                        isDebugCamEnabled = !isDebugCamEnabled;
                    break;
                    
                case "debug position":
                    if (!isKeyPressed)
                        System.out.println(""
                                + "\n\nlocation: " + cam.getLocation()
                                + "\nleft: " + cam.getLeft()
                                + "\nup: " + cam.getUp()
                                + "\ndirection: " + cam.getDirection());
                    break;

                default:
                    break;
            }
        }
    };
    
    public void handleCameraMovingSound(boolean isKeyPressed)
    {
        if (isKeyPressed && isSecurityCamActive()) 
            cameraMovingSound.play();
        else 
        {
            if (!isThereLookKeyPressed())
                cameraMovingSound.stop();
        }
    }
    
    public void update(float tpf) {
        if (!isKilled)
            updatePlayerPosition();
    }
    
    public void updatePlayerPosition() {
                        
        if (!Params.isLevelStarted)
            return;

        if (factorySensors.get("top stairs").isPlayerInRange() || factorySensors.get("bottom stairs").isPlayerInRange()
                || (!Params.isTopViewEnabled && Params.fadeFilter.getValue() < 1))
            handleTransition();

        if (lookUp)
            rotateCamera(-Params.rotationSpeed, cam.getLeft());

        if (lookDown)
            rotateCamera(Params.rotationSpeed, cam.getLeft());

        if (lookLeft)
            rotateCamera(Params.rotationSpeed, new Vector3f(0,1,0));
        
        if (lookLeftA)
            rotateCamera(Params.rotationSpeed, new Vector3f(0,1,0));

        if (lookRight)
            rotateCamera(-Params.rotationSpeed, new Vector3f(0,1,0));
        
        if (lookRightD)
            rotateCamera(-Params.rotationSpeed, new Vector3f(0,1,0));

        if (Params.isTopViewEnabled || isDebugCamEnabled) {
            if (Params.fadeFilter.getValue() <= 0)
                Params.fadeFilter.fadeIn();
            return;
        }

        camDir = cam.getDirection().clone().multLocal(playerSpeed);
        camLeft = cam.getLeft().clone().multLocal(playerSpeed);

        walkDirection.set(0, 0, 0); // reset walkDirection vector

        if (moveForward) 
            walkDirection.addLocal(camDir);

        if (moveBackward)
            walkDirection.addLocal(camDir.negate());

        if (moveLeft)
            walkDirection.addLocal(camLeft);

        if (moveRight)
            walkDirection.addLocal(camLeft.negate());

        if (Params.fadeFilter.getValue() == 1) 
            player.setWalkDirection(walkDirection); // walk!
        
        cam.setLocation(player.getPhysicsLocation());

        if (isPlayerUpstairs && player.getPhysicsLocation().getY() < 57.0f)
            player.warp(new Vector3f(new Vector3f(130.96266f, 59.064148f, -291.2517f)));
        else if (player.getPhysicsLocation().getY() < 12.65f)
            player.warp(new Vector3f(player.getPhysicsLocation().getX(), 12.65f, player.getPhysicsLocation().getZ()));
    }
    
    private void handleTransition() {
        
//        if (true)
//            return;
        if (Params.fadeFilter.getDuration() < 1.5f)
            Params.fadeFilter.setDuration(1.5f);
                
        boolean isFadeEffectStarted = Params.fadeFilter.getValue() < 1;

        if (!isFadeEffectStarted) {
            playerSpeed = 0;
            Params.fadeFilter.fadeOut();

            AudioNode footsteps;
            footsteps = new AudioNode(assetManager, isPlayerUpstairs ? "Sounds/footsteps1.wav" : "Sounds/footsteps2.wav", false);
            footsteps.setPositional(false);
            footsteps.play();
        }

        boolean isFadeEffectFinished = Params.fadeFilter.getValue() <= 0;

        if (isFadeEffectFinished) {
            if (isPlayerUpstairs) {
                player.warp(new Vector3f(121.2937f, 12.65f, -309.41315f));
                cam.setRotation(new Quaternion(0.04508071f, -0.4710204f, 0.02474963f, 0.8806219f));
                isPlayerUpstairs = false;
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 0)
                    Params.tutorial.nextStep();
            }
            else {
                player.setPhysicsLocation(new Vector3f(51.68367f, 59.064148f, -292.67755f));
                cam.setRotation(new Quaternion(0.07086334f, -0.01954512f, 0.0019515193f, 0.99729264f));
                if (!Params.isTutorialLevel && !Params.isObjectiveLevel)
                    gameNarrator.talk("Second Floor.\nPress 'T' for a top view of the factory.", 5);//"Sounds/Narrator/instructions.wav");
                isPlayerUpstairs = true;
                
            }
            Params.fadeFilter.fadeIn();
            playerSpeed = 1f;
        }
    }

    public void createSkyBox() {
        String path = "Textures/Skybox/";
        
        Texture west = assetManager.loadTexture(path + "skyLeft.jpg");
        Texture east = assetManager.loadTexture(path + "skyRight.jpg");
        Texture north = assetManager.loadTexture(path + "skyFront.jpg");
        Texture south = assetManager.loadTexture(path + "skyBack.jpg");
        Texture top = assetManager.loadTexture(path + "skyTop.jpg");
        Texture bottom = assetManager.loadTexture(path + "skyDown.jpg");
        
        skyBox = SkyFactory.createSky(assetManager, west, east, north, 
                                                            south, top, bottom);
        
        rootNode.attachChild(skyBox);
    }

    private void createLighting() {
        if (isLightingEnabled || !Params.firstRun)
            return;
        
        Params.firstRun = false;
        isLightingEnabled = true;
        ColorRGBA color = ColorRGBA.White;
        lamp1 = new PointLight();
        lamp1.setPosition(new Vector3f(40, 200, 150));
        lamp1.setColor(color);
        lamp1.setRadius(lamp1.getRadius()/20);
        rootNode.addLight(lamp1);

        lamp2 = new PointLight();
        lamp2.setPosition(new Vector3f(43.50383f, 80.081642f, -310.90753f));
        lamp2.setColor(color);
        lamp2.setRadius(lamp1.getRadius());
        rootNode.addLight(lamp2);
    }

    private void createInvisibleWalls() {
        String[] wallNames = {"bottom right wall", 
                              "bottom left wall", 
                              "bottom front wall",
                              "bottom back wall", 
                              "upper right wall", 
                              "upper left wall",
                              "upper front wall", 
                              "upper back wall"};
        
        Vector3f[] sizes = {new Vector3f(0.6f, 0.6f, 23.600018f),
                            new Vector3f(1.4000001f, 0.6f, 23.600018f),
                            new Vector3f(9.399996f, 1.0f, 1.0f),
                            new Vector3f(9.399996f, 1.0f, 0.6f),
                            new Vector3f(0.40000004f, 1.0f, 3.4000006f),
                            new Vector3f(0.40000004f, 1.0f, 3.0000005f),
                            new Vector3f(7.599997f, 1.0f, 0.20000003f),
                            new Vector3f(9.599996f, 1.0f, 0.40000004f)};
        
        Vector3f[] locations = {new Vector3f(-37.600044f, 5.850006f, -117.43932f),
                                new Vector3f(137.1993f, 5.850006f, -117.43932f),
                                new Vector3f(50.80012f, 9.999995f, 106.9995f),
                                new Vector3f(50.60012f, 9.999995f, -346.80283f),
                                new Vector3f(-40.543167f, 56.8007f, -318.2905f),
                                new Vector3f(140.85591f, 56.8007f, -322.69077f),
                                new Vector3f(31.656963f, 56.8007f, -289.88876f),
                                new Vector3f(50.433777f, 56.38947f, -350.81924f)};
        
        invisibleWalls = new ArrayList<>();
        for (int i = 0; i < wallNames.length; i++)
        {
            Geometry invisibleWall = new InvisibleWall(bulletAppState, sizes[i], locations[i]);
            invisibleWall.setName(wallNames[i]);
            invisibleWalls.add(invisibleWall);
            rootNode.attachChild(invisibleWall);
        }
    }
    
    private void createSensors()
    {    
        String[] sensorNames = {"top stairs", "bottom stairs", 
                                "staff zone"};
        
        Vector3f[] sensorSizes = {new Vector3f(15, 10, 5), new Vector3f(15, 10, 5),
                                  new Vector3f(25, 10, 10)};
        
        Vector3f[] sensorLocations = {new Vector3f(134.05f, 59.06f, -285.02f), new Vector3f(107.42f, 12.67f, -284.9f),
                                      new Vector3f(0, 8, -290)};
        
        factorySensors = new HashMap<>();

        for (int i = 0; i < sensorNames.length; i++)
            factorySensors.put(sensorNames[i], new Sensor(sensorSizes[i], sensorLocations[i], bulletAppState));
    }
    
    private void createBulletinBoards()
    {
        
        Spatial staffZoneStation = assetManager.loadModel("Models/BulletinBoards/staffZone.j3o");
        staffZoneStation.setLocalScale(0, 0, 0);
        staffZoneStation.setLocalTranslation(new Vector3f(11.2f, 6.7999988f, -321.1999f));
        staffZoneStation.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.DEG_TO_RAD * 90, Vector3f.UNIT_X));
        staffZoneStation.addControl(new BulletinBoardControl(assetManager, factorySensors.get("staff zone")));
        bulletinBoardsArray = new ArrayList<>();
        bulletinBoardsArray.add(staffZoneStation);
        rootNode.attachChild(staffZoneStation);
    }

    private void rotateCamera(float value, Vector3f axis) 
    {
        
        if (Params.fadeFilter.getValue() < 1) //don't rotate if screen is fading in/out
            return;
        
        Matrix3f matrix = new Matrix3f();

        if (Params.isTopViewEnabled)
            value = value * 0.3f;

        matrix.fromAngleNormalAxis(flyCam.getRotationSpeed() * value, axis);

        Vector3f tempUp = cam.getUp();
        Vector3f tempLeft = cam.getLeft();
        Vector3f tempDir = cam.getDirection();

        matrix.mult(tempUp, tempUp);
        matrix.mult(tempLeft, tempLeft);
        matrix.mult(tempDir, tempDir);

        if (       tempDir.getX() > Params.camMaxX || tempDir.getX() < Params.camMinX
                || tempDir.getY() > Params.camMaxY || tempDir.getY() < Params.camMinY
                || tempDir.getZ() > Params.camMaxZ || tempDir.getZ() < Params.camMinZ)
            return;

        Quaternion q = new Quaternion();
        q.fromAxes(tempLeft, tempUp, tempDir);
        q.normalizeLocal();

        cam.setAxes(q);
    }
    
    private void playSoundEffect(String pathToAudioFile)
    {
        AudioNode sfx;
        sfx = new AudioNode(assetManager, pathToAudioFile, false);
        sfx.setPositional(false);
        sfx.setPitch(1.3f);
        sfx.play();
    }
    
    private void toggleTopView() 
    {
        
        if (Params.fadeFilter.getValue() < 1)
            return;
        
        if (Params.topViewAvailable && Params.viewNumber != 5) {
            Params.isTopViewEnabled = true;
            
            if (Params.fadeFilter.getDuration() > 0.25f)
                Params.fadeFilter.setDuration(0.25f);
            
            if (Params.viewNumber == 0) {
                playSoundEffect("Sounds/enteredTopView.wav");
            } else {
                Params.fadeFilter.fadeOut();
                playSoundEffect("Sounds/cameraSwitch.wav");
            }

            switch(Params.viewNumber) {
                case 0:
                    Params.camAxesLeft = cam.getLeft();
                    Params.camAxesUp = cam.getUp();
                    Params.camAxesDir = cam.getDirection();
                    Params.flyCamRotationSpeed = flyCam.getRotationSpeed();

                    cam.setLocation(new Vector3f(210.75597f, 191.22467f, -111.45984f));
                    cam.setAxes(new Vector3f(0.006238699f, 0.0011283755f, 0.9999799f),
                            new Vector3f(-0.7573153f, 0.6530373f, 0.0039878786f),
                            new Vector3f(-0.6530197f, -0.75732493f, 0.004928589f));
                    break;

                case 1:
                    Params.camMaxY = Params.securityCamsMaxY;
                    Params.camMinY = Params.securityCamsMinY;
                    Params.camMaxX = Params.cam1MaxX;
                    Params.camMinX = Params.cam1MinX;
//                    Params.camMaxZ = Params.cam1MaxX;
//                    Params.camMinZ = Params.cam1MinX;
                    cam.setLocation(new Vector3f(138.94714f, 74.204185f, -118.346085f));
                    cam.setAxes(new Vector3f(-0.004745364f, 0.0011234581f, 0.99998814f),
                            new Vector3f(-0.69315696f, 0.720775f, -0.0040991306f),
                            new Vector3f(-0.720771f, -0.69316816f, -0.0026416779f));
                    break;

                case 2:
                    Params.camMaxX = Params.playerMaxX;
                    Params.camMinX = Params.playerMinX;
                    Params.camMaxZ = 0;
                    Params.camMinZ = -100;
                    cam.setLocation(new Vector3f(50.173473f, 78.43454f, 112.47995f));
                    cam.setAxes(new Vector3f(-0.9999976f, 0.0011224343f, 0.0018219932f),
                            new Vector3f(0f, 0.8618528f, -0.5071584f),
                            new Vector3f(-0.002139542f, -0.5071572f, -0.86185086f));
                    break;

                case 3:
                    Params.camMaxX = 100f;
                    Params.camMinX = 0f;
                    Params.camMaxZ = Params.playerMaxZ;
                    Params.camMinZ = Params.playerMinZ;
                    cam.setLocation(new Vector3f(-37.94872f, 71.8763f, -118.55907f));
                    cam.setAxes(new Vector3f(-0.0045000315f, 0.0011213869f, -0.9999892f),
                            new Vector3f(0.4902432f, 0.8715848f, -0.0012287796f),
                            new Vector3f(0.871574f, -0.49024346f, -0.004471898f));
                    break;

                case 4:  
                    Params.camMaxX = Params.playerMaxX;
                    Params.camMinX = Params.playerMinX;
                    Params.camMaxZ = 100f;
                    Params.camMinZ = 0f;
                    cam.setLocation(new Vector3f(54.01033f, 79.31754f, -347.24677f));
                    cam.setAxes(new Vector3f(0.99922884f, 0.0011243783f, 0.039248988f),
                            new Vector3f(-0.019561216f, 0.8809709f, 0.47276595f),
                            new Vector3f(-0.034045644f, -0.47316912f, 0.8803135f));
                    break;
            }

            Params.viewNumber = (Params.viewNumber + 1) % 6;
            flyCam.setMoveSpeed(0);
            factory.getChild("Beams-Metal").setCullHint(Spatial.CullHint.Always);
        }
        else if (Params.topViewAvailable && Params.isTopViewEnabled) {
            playSoundEffect("Sounds/exitTopView.wav");
            
            Params.isTopViewEnabled = false;
            cam.setAxes(Params.camAxesLeft, Params.camAxesUp, Params.camAxesDir);
            flyCam.setMoveSpeed(100);
            flyCam.setRotationSpeed(Params.flyCamRotationSpeed);
            factory.getChild("Beams-Metal").setCullHint(Spatial.CullHint.Never);
            Params.viewNumber = (Params.viewNumber + 1)%6;
            Params.camMaxX = Params.playerMaxX;
            Params.camMinX = Params.playerMinX;
            Params.camMaxY = Params.playerMaxY;
            Params.camMinY = Params.playerMinY;
            Params.camMaxZ = Params.playerMaxZ;
            Params.camMinZ = Params.playerMinZ;
        }
    }
    
    public boolean isTopViewEnabled()
    {
        return Params.isTopViewEnabled;
    }
    
    public boolean isSecurityCamActive()
    {
        return Params.isTopViewEnabled && Params.viewNumber > 1;
    }
    
    public boolean isThereLookKeyPressed()
    {
        return lookUp || lookDown || lookLeft || lookRight || lookLeftA || lookRightD;
    }
    
    public void setTopViewEnabled(boolean enabled)
    {
        Params.isTopViewEnabled = enabled;
    }
    
    public int getViewNumber() 
    {
        return Params.viewNumber;
    }
    
    public void setViewNumber(int number)
    {
        Params.viewNumber = number;
    }
     public void attachFactory(BulletAppState bulletAppState) {
         this.bulletAppState = bulletAppState;
         factory.addControl(rigidBody);
         rootNode.attachChild(factory);
         rootNode.attachChild(grass);
         rootNode.attachChild(skyBox);
         for (Spatial board: bulletinBoardsArray)
             rootNode.attachChild(board);
         for (Geometry wall: invisibleWalls)
             rootNode.attachChild(wall);
         resetSensors();                 
         this.bulletAppState.getPhysicsSpace().add(rigidBody);
         setupPlayer();
         this.bulletAppState.getPhysicsSpace().add(player);
     }
     
     private void setupPlayer() {
        player.setJumpSpeed(45);
        player.setFallSpeed(120);
        player.setGravity(Params.playerGravity);
        player.setPhysicsLocation(new Vector3f(51.68367f, 59.064148f, -292.67755f));
        cam.setRotation(new Quaternion(0.07086334f, -0.01954512f, 0.0019515193f, 0.99729264f));
        flyCam.setRotationSpeed(1.4f);
        player.setViewDirection(new Vector3f(0, 0, 1));
        this.isPlayerUpstairs = true;

     }
     
     private void resetSensors() {
         for (Sensor sensor: factorySensors.values()) {
             sensor.resetPhysicsEngine(this.bulletAppState);
         }
         createSensors();
     }
}