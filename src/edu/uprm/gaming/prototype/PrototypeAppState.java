package edu.uprm.gaming.prototype;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.util.Random;

/**
 * Virtual Factory 2.0 (PrototypeAppState)
 * @author Abner Coimbre
 */
public class PrototypeAppState extends AbstractAppState  implements ActionListener {
    private SimpleApplication app;
    private AssetManager assetManager;
    private InputManager inputManager;
    private FlyByCamera flyCam;
    private Node rootNode;
    
    private BulletAppState bulletAppState;
    
    private Node world;
    private RigidBodyControl worldPhysics;
    
    private Vector3f walkDirection = new Vector3f(0, 0, 0);
    
    private CharacterControl player;
    private AudioNode footstep;
    private AudioNode[] jumpSounds;
    
    private boolean forward;
    private boolean backward;
    private boolean left;
    private boolean right;
    
    private float playerSpeed = 1.3f;
    
    private Vector3f camDir;
    private Vector3f camLeft;
    
    private boolean isPlayerDisabled;

    /**
     * Initializes the scene. Called automatically.
     */
    @Override
    public void initialize(AppStateManager stateManager, Application application) {
        /* Get application-wide access */
        app = (SimpleApplication) application;
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        flyCam = app.getFlyByCamera();
        rootNode = app.getRootNode();

        /* Activate physics engine */
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        /* Load the game world */
        world = (Node) assetManager.loadModel("Scenes/World_/shop01.j3o");
        world.setLocalScale(0.2f);
        
        /* Make world solid and static */
        CollisionShape worldShape = CollisionShapeFactory.createMeshShape(world); // Ensure mesh-accurate collision shape
        worldPhysics = new RigidBodyControl(worldShape, 0); // A zero makes it solid and static
        world.addControl(worldPhysics);
        bulletAppState.getPhysicsSpace().add(worldPhysics);
        rootNode.attachChild(world);
        
        /* First-person player */
        // ----------
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.4f, 24.5f, 1);
        // Use deprecated CharacterControl until BetterCharacterControl is updated
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(30);
        player.setPhysicsLocation(new Vector3f(78.8f, 11.88f, 7.1f));
        bulletAppState.getPhysicsSpace().add(player);
        // ----------
        
        /* Add some light */
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White.mult(3f));
        rootNode.addLight(ambient);
        
        /* Other relevant settings */
        setMaterials();
        createSkyBox();
        initKeyControls();
        initSoundEffects();

        flyCam.setMoveSpeed(100); // done with setup. Game begins
    }
    
     /**
     * Initializes the game's input controls.
     */
    private void initKeyControls() {
        inputManager.deleteMapping("FLYCAM_ZoomIn");
        inputManager.deleteMapping("FLYCAM_ZoomOut");
        
        String[] mappings = {"Toggle Cam", "Forward", "Backward", "Left",
                             "Right", "Jump"};
        
        int[] triggers = {KeyInput.KEY_0, KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_A, 
                          KeyInput.KEY_D, KeyInput.KEY_SPACE};
        
        for (int i = 0; i < mappings.length; i++) {
            inputManager.addMapping(mappings[i], new KeyTrigger(triggers[i]));
            inputManager.addListener(this, mappings[i]);
        } 
    }
    
    /**
     * For now, here we initialize all sound effects related to the player. 
     */
    private void initSoundEffects() {
        /* Initialize player's footsteps */
        footstep = new AudioNode(assetManager, "Sounds/footstep.ogg", false);
        footstep.setPositional(false);
        footstep.setLooping(true);
        
        /* Initialize the player's grunt sounds when jumping */
        String filePath = "Sounds/Jump/jump";
        jumpSounds = new AudioNode[5];
        for (int i = 0; i < jumpSounds.length; i++) {
            jumpSounds[i] = new AudioNode(assetManager, filePath + (i+1) + ".wav", false);
        }
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
        rootNode.attachChild(sky);
    }
    
    /**
     * Sets custom materials for each object of the game world.
     * The j3m files allow for easy configuration of multi-textured materials, 
     * which will come in handy later on (e.g. when using the Bitmap2Material software).
     */
    private void setMaterials() {
        // TODO: Refactor
        String pathFileMaterial = "Scenes/Materials/";
        world.getChild("Asphalt").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphalt.j3m"));
        world.getChild("AsphaltEnd").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphaltEnd.j3m"));
        world.getChild("BackWheels").setMaterial(assetManager.loadMaterial(pathFileMaterial + "backWheels.j3m"));
        world.getChild("BarbedWire").setMaterial(assetManager.loadMaterial(pathFileMaterial + "barbedWireFence.j3m"));
        world.getChild("BathDor").setMaterial(assetManager.loadMaterial(pathFileMaterial + "bathDor.j3m"));
        world.getChild("board01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board01.j3m"));
        world.getChild("board02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board02.j3m"));
        world.getChild("board03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board03.j3m"));
        world.getChild("board04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board04.j3m"));
        world.getChild("board05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board05.j3m"));
        world.getChild("Body").setMaterial(assetManager.loadMaterial(pathFileMaterial + "body.j3m"));
        world.getChild("Box001").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box001.j3m"));
        world.getChild("Box002").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box002.j3m"));
        world.getChild("Box003").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box003.j3m"));
        world.getChild("Box004").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box004.j3m"));
        world.getChild("Box005").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box005.j3m"));
        world.getChild("CabinetL10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg01.j3m"));
        world.getChild("CabinetL11").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg03.j3m"));
        world.getChild("CabinetL12").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg003.j3m"));
        world.getChild("CabinetLe0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg004.j3m"));
        world.getChild("CabinetLe1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg005.j3m"));
        world.getChild("CabinetLe2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg006.j3m"));
        world.getChild("CabinetLe3").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg007.j3m"));
        world.getChild("CabinetLe4").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg008.j3m"));
        world.getChild("CabinetLe5").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg009.j3m"));
        world.getChild("CabinetLe6").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg010.j3m"));
        world.getChild("CabinetLe7").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg011.j3m"));
        world.getChild("CabinetLe8").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut01.j3m"));
        world.getChild("CabinetLe9").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut02.j3m"));
        world.getChild("CabinetLeg").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut02.j3m"));
        
        /* Remove trees */
        // -----------
        for (int i = 0; i < 8; i++) {
            world.getChild("CloseVege" + i).removeFromParent();
        }
        world.getChild("CloseVeget").removeFromParent();
        world.getChild("FarVegetat").removeFromParent();
        // -----------
        
        world.getChild("Colm01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "colm01.j3m"));
        world.getChild("Colm02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "colm02.j3m"));
        world.getChild("Compresor").setMaterial(assetManager.loadMaterial(pathFileMaterial + "compresor.j3m"));
        world.getChild("CompWheels").setMaterial(assetManager.loadMaterial(pathFileMaterial + "compWheels.j3m"));
        world.getChild("Contact ce").setMaterial(assetManager.loadMaterial(pathFileMaterial + "contactCE.j3m"));
        world.getChild("Distributi").setMaterial(assetManager.loadMaterial(pathFileMaterial + "distributi.j3m"));
        world.getChild("FLCenterWh").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flCenterWh.j3m"));
        world.getChild("FLHandle").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flHandle.j3m"));
        world.getChild("FLLeftWhee").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flLeftWhee.j3m"));
        world.getChild("FLRightWhe").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flRightWhe.j3m"));
        world.getChild("FLMainFram").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flMainFram.j3m"));
        world.getChild("FordBody").setMaterial(assetManager.loadMaterial(pathFileMaterial + "fordBody.j3m"));
        world.getChild("FrontWheel").setMaterial(assetManager.loadMaterial(pathFileMaterial + "frontWheel.j3m"));
        world.getChild("Ground Edg").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphaltEnd.j3m"));
        world.getChild("House 1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house1.j3m"));
        world.getChild("House 2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house2.j3m"));
        world.getChild("House 3").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house3.j3m"));
        world.getChild("Kart01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "kart01.j3m"));
        world.getChild("Juanito").setMaterial(assetManager.loadMaterial(pathFileMaterial + "juanito.j3m"));
        world.getChild("Lake").setMaterial(assetManager.loadMaterial(pathFileMaterial + "lake.j3m"));
        world.getChild("LakeEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "lakeEdge.j3m"));
        world.getChild("LegBoard00").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard00.j3m"));
        world.getChild("LegBoard01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard01.j3m"));
        world.getChild("LegBoard02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard02.j3m"));
        world.getChild("LegBoard03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard03.j3m"));
        world.getChild("LegBoard04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard04.j3m"));
        world.getChild("LegBoard05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard05.j3m"));
        world.getChild("LegBoard06").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard06.j3m"));
        world.getChild("LegBoard07").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard07.j3m"));
        world.getChild("LegBoard08").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard08.j3m"));
        world.getChild("LegBoard09").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard09.j3m"));
        world.getChild("LegBoard10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard10.j3m"));
        world.getChild("LegBoard11").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard11.j3m"));
        world.getChild("LegBoard12").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard12.j3m"));
        world.getChild("LegBoard13").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard13.j3m"));
        world.getChild("LegBoard14").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard14.j3m"));
        world.getChild("LegBoard15").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard15.j3m"));
        world.getChild("LegBoard16").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard16.j3m"));
        world.getChild("LegBoard17").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard17.j3m"));
        world.getChild("LegBoard18").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard18.j3m"));
        world.getChild("LegBoard19").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard19.j3m"));
        world.getChild("LegBoard20").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard20.j3m"));
        world.getChild("LegBoard21").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard21.j3m"));
        world.getChild("LegBoard22").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard22.j3m"));
        world.getChild("MesaHerram").setMaterial(assetManager.loadMaterial(pathFileMaterial + "mesaHerram.j3m"));
        world.getChild("MetalBarr0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "metalBarr0.j3m"));
        world.getChild("MetalBarre").setMaterial(assetManager.loadMaterial(pathFileMaterial + "metalBarre.j3m"));
        world.getChild("MitterSawB").setMaterial(assetManager.loadMaterial(pathFileMaterial + "mitterSawB.j3m"));
        world.getChild("PaintBooth").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintBooth.j3m"));
        world.getChild("PaintCan01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan01.j3m"));
        world.getChild("PaintCan02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan02.j3m"));
        world.getChild("PaintCan03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan03.j3m"));
        world.getChild("PaintCan04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan04.j3m"));
        world.getChild("PaintCan05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan05.j3m"));
        world.getChild("PaintCan06").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan06.j3m"));
        world.getChild("PaintCan07").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan07.j3m"));
        world.getChild("PaintCan08").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan08.j3m"));
        world.getChild("PaintCan09").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan09.j3m"));
        world.getChild("PaintCan10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan10.j3m"));
        world.getChild("PaintThinn").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintThinn.j3m"));
        world.getChild("Playwood01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood01.j3m"));
        world.getChild("Playwood02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood02.j3m"));
        world.getChild("Playwood04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood04.j3m"));
        world.getChild("Playwood05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood05.j3m"));
        world.getChild("RackPanele").setMaterial(assetManager.loadMaterial(pathFileMaterial + "rackPanele.j3m"));
        world.getChild("River").setMaterial(assetManager.loadMaterial(pathFileMaterial + "river.j3m"));
        world.getChild("RiverEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "riverEdge.j3m"));
        world.getChild("RoadBridge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "roadBridge.j3m"));
        world.getChild("RoadEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "roadEdge.j3m"));
        world.getChild("shop01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "shop01.j3m"));
        world.getChild("sink").setMaterial(assetManager.loadMaterial(pathFileMaterial + "sink.j3m"));
        
        /* Fix sink issue */
        world.getChild("sink").setLocalTranslation(new Vector3f(563.8958f, 21.125626f, -1768.6628f));
        
        world.getChild("Sky").removeFromParent(); // remove sky created in Blender
        world.getChild("SteeringWh").setMaterial(assetManager.loadMaterial(pathFileMaterial + "steeringWh.j3m"));
        world.getChild("Supplier 1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "supplier1.j3m"));
        world.getChild("Supplier 2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "supplier2.j3m"));
        world.getChild("Table saw").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tableSaw.j3m"));
        world.getChild("Table01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "table01.j3m"));
        world.getChild("Tabliller0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tabliller0.j3m"));
        world.getChild("Tablillero").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tablillero.j3m"));
        world.getChild("Toilet").setMaterial(assetManager.loadMaterial(pathFileMaterial + "toilet.j3m"));
        
        /* Fix toilet issue */
        world.getChild("Toilet").setLocalTranslation(new Vector3f(570.08624f, 1.4926453f, -1797.9407f));
        
        world.getChild("TrashCan01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "trashCan01.j3m"));
        Material grassMaterial = new Material(assetManager, "Common/MatDefs/Terrain/TerrainLighting.j3md");
        grassMaterial.setBoolean("useTriPlanarMapping", false);
        grassMaterial.setFloat("Shininess", 0.0f);
        Texture grass = assetManager.loadTexture("Scenes/Textures/grs1rgb_3.jpg");
        grass.setWrap(Texture.WrapMode.Repeat);
        grassMaterial.setTexture("DiffuseMap", grass);
        world.getChild("Grass").setMaterial(grassMaterial);
    }
    
    @Override
    public void onAction(String keyPress, boolean isPressed, float tpf) {
        switch (keyPress) {
            case "Forward":
                forward = isPressed;
                if (isPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                else { if (!backward && !left && !right) { footstep.stop();  } }
                break;
                
            case "Backward":
                backward = isPressed;
                if (isPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                else { if (!forward && !left && !right) { footstep.stop(); } }
                break;
                
            case "Left":
                left = isPressed;
                if (isPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                else { if (!forward && !backward && !right) { footstep.stop(); } }
                break;
                
            case "Right":
                right = isPressed;
                if (isPressed && !isPlayerDisabled) { footstep.stop(); footstep.play(); }
                else { if (!forward && !backward && !left) { footstep.stop(); } }
                break;
                
            case "Jump":
                if (isPressed) {
                    player.jump();
                    footstep.stop();
                    jumpSounds[new Random().nextInt(5)].playInstance();
                }
                break;
                
            default:
                if (!isPressed) 
                { 
                    isPlayerDisabled = !isPlayerDisabled; 
                    inputManager.setCursorVisible(true);
                    flyCam.setDragToRotate(true);
                }
                break;
        }
    }

    /**
     * Interacts with update loop.
     * @param tpf 
     */
    @Override
    public void update(float tpf) {
        camDir = app.getCamera().getDirection().clone().multLocal(playerSpeed);
        camLeft = app.getCamera().getLeft().clone().multLocal(playerSpeed);
        
        walkDirection.set(0, 0, 0); // reset walkDirection vector
        if (forward) { walkDirection.addLocal(camDir); }
        if (backward) { walkDirection.addLocal(camDir.negate()); }
        if (left) { walkDirection.addLocal(camLeft); }
        if (right) { walkDirection.addLocal(camLeft.negate()); }
        player.setWalkDirection(walkDirection); // walk!
        
        /* For development purposes. Changing between camera or first-person player (see OnAction() method) */
        if (!isPlayerDisabled) { app.getCamera().setLocation(player.getPhysicsLocation()); }
    }
}
