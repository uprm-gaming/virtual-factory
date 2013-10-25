package edu.uprm.edu.gaming.prototype;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

/**
 * Virtual Factory 2.0 (Prototype)
 * @author Abner Coimbre
 */
public class Prototype extends SimpleApplication {
    private BulletAppState bulletAppState;
    private AudioNode audioBackground;
    
    private Node shop;
    private RigidBodyControl shopPhysics;
    
    private Node playerNode;
    private BetterCharacterControl playerControl;
    
    private CameraNode camNode;
    
    private Vector3f walkDirection = new Vector3f(0, 0, 0);
    private Vector3f viewDirection = new Vector3f(0, 0, 1);
    
    private boolean forward;
    private boolean backward;
    private boolean rotateLeft;
    private boolean rotateRight;
    
    private int playerSpeed = 48;
    
    /**
     * Kickstart the demo.
     * @param args 
     */
    public static void main(String[] args) {
        /* Configure settings */
        AppSettings settings = new AppSettings(true);

        /* Start */
        Prototype app = new Prototype();
        app.setSettings(settings);
        app.setShowSettings(true);
        app.setPauseOnLostFocus(true);
        app.start();
    } 
    
    

    /**
     * Initializes the scene. Called automatically.
     */
    @Override
    public void simpleInitApp() {
        /* Activate physics engine */
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        /* Load machine shop (which also contains the entire game world) */
        shop = (Node) assetManager.loadModel("Scenes/World_/shop01.j3o");
        shop.setLocalScale(0.2f);
        
        /* Make shop solid and static */
        shopPhysics = new RigidBodyControl(0); // A zero makes it solid and static
        shop.addControl(shopPhysics);
        bulletAppState.getPhysicsSpace().add(shopPhysics);
        rootNode.attachChild(shop);
        
        /* First-person player */
        // ---------------------------------
        playerNode = new Node("Player Node");
        
        // Use BetterCharacterControl() instead of deprecated CharacterControl()
        playerControl = new BetterCharacterControl(1.4f, 14, 30);
        
        // Initial view of the game (may be changed)
        playerNode.setLocalTranslation(new Vector3f(592.9f, 81.82f, -491.27f));
        viewDirection.set(new Vector3f(-0.94f, 0, 0.33f));
        playerControl.setViewDirection(viewDirection);
        
        // Set gravity, jump force, activate physics and add him to the scene
        playerControl.setGravity(new Vector3f(0, -10, 0));
        playerControl.setJumpForce(new Vector3f(0, 300, 0));
        bulletAppState.getPhysicsSpace().add(playerControl);
        playerNode.addControl(playerControl);
        rootNode.attachChild(playerNode);
        // ---------------------------------
        
        /* Add some light */
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White.mult(2f));
        rootNode.addLight(ambient);
        
        /* Add some nice background music */
        audioBackground = new AudioNode(assetManager, "Sounds/theme.wav", false);
        audioBackground.setPositional(false);
        audioBackground.setLooping(true);
        audioBackground.play();
        
        /* Other relevant settings */
        setDisplayFps(false);
        setDisplayStatView(false);
        initKeyControls();
        setupCustomCam();
        createSkyBox();
        setMaterials();
        
        flyCam.setMoveSpeed(100); // done with setup. Game begins
    }
    
     /**
     * Initializes the game's input controls.
     */
    private void initKeyControls() {
        String[] mappings = {"Toggle Cam", "Forward", "Backward", "Rotate Left",    
                             "Rotate Right", "Jump"};
        
        int[] triggers = {KeyInput.KEY_0, KeyInput.KEY_UP, KeyInput.KEY_DOWN, 
                          KeyInput.KEY_LEFT, KeyInput.KEY_RIGHT, KeyInput.KEY_SPACE};
        
        for (int i = 0; i < mappings.length; i++) {
            inputManager.addMapping(mappings[i], new KeyTrigger(triggers[i]));
            inputManager.addListener(actionListener, mappings[i]);
        }
    }
    
    /**
     * Handles player input.
     */
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String binding, boolean isPressed, float tpf) {
            if (binding.equals("Toggle Cam") && !isPressed) {
                toggleCam();
                return;
            }
            
            if (binding.equals("Forward")) {
                forward = isPressed;
                return;
            }
            
            if (binding.equals("Backward")) {
                backward = isPressed;
                return;
            }
            
            if (binding.equals("Rotate Left")) {
               rotateLeft = isPressed;
               return;
            }
            
            if (binding.equals("Rotate Right")) {
                rotateRight = isPressed;
                return;
            }
            
            if (binding.equals("Jump")) {
                playerControl.jump();
            }
        }
    };
    
    /**
     * This custom cam will follow the first-person player around the scene.
     */
    private void setupCustomCam() {
        /* Setup */
        camNode = new CameraNode("Cam Node", cam);
        camNode.setLocalTranslation(new Vector3f(0, 12, -6));
        Quaternion quat = new Quaternion();
        quat.lookAt(Vector3f.UNIT_Z, Vector3f.UNIT_Y);
        camNode.setLocalRotation(quat);
        
        /* Always see the scene from the player's point of view */
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        playerNode.attachChild(camNode);
    }
    
    /**
     * Toggles between the first-person player cam and fly camera. For
     * development purposes only (should be removed in final game).
     */
    private void toggleCam() {
        if (camNode.isEnabled()) {
            camNode.setEnabled(false);
            flyCam.setEnabled(true);
        } else {
            camNode.setEnabled(true);
            flyCam.setEnabled(false);  
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
        shop.getChild("Asphalt").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphalt.j3m"));
        shop.getChild("AsphaltEnd").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphaltEnd.j3m"));
        shop.getChild("BackWheels").setMaterial(assetManager.loadMaterial(pathFileMaterial + "backWheels.j3m"));
        shop.getChild("BarbedWire").setMaterial(assetManager.loadMaterial(pathFileMaterial + "barbedWireFence.j3m"));
        shop.getChild("BathDor").setMaterial(assetManager.loadMaterial(pathFileMaterial + "bathDor.j3m"));
        shop.getChild("board01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board01.j3m"));
        shop.getChild("board02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board02.j3m"));
        shop.getChild("board03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board03.j3m"));
        shop.getChild("board04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board04.j3m"));
        shop.getChild("board05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "board05.j3m"));
        shop.getChild("Body").setMaterial(assetManager.loadMaterial(pathFileMaterial + "body.j3m"));
        shop.getChild("Box001").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box001.j3m"));
        shop.getChild("Box002").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box002.j3m"));
        shop.getChild("Box003").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box003.j3m"));
        shop.getChild("Box004").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box004.j3m"));
        shop.getChild("Box005").setMaterial(assetManager.loadMaterial(pathFileMaterial + "box005.j3m"));
        shop.getChild("CabinetL10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg01.j3m"));
        shop.getChild("CabinetL11").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg03.j3m"));
        shop.getChild("CabinetL12").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg003.j3m"));
        shop.getChild("CabinetLe0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg004.j3m"));
        shop.getChild("CabinetLe1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg005.j3m"));
        shop.getChild("CabinetLe2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg006.j3m"));
        shop.getChild("CabinetLe3").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg007.j3m"));
        shop.getChild("CabinetLe4").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg008.j3m"));
        shop.getChild("CabinetLe5").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg009.j3m"));
        shop.getChild("CabinetLe6").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg010.j3m"));
        shop.getChild("CabinetLe7").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLeg011.j3m"));
        shop.getChild("CabinetLe8").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut01.j3m"));
        shop.getChild("CabinetLe9").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut02.j3m"));
        shop.getChild("CabinetLeg").setMaterial(assetManager.loadMaterial(pathFileMaterial + "cabinetLegCut02.j3m"));
        shop.getChild("CloseVege0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege0.j3m"));
        shop.getChild("CloseVege1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege1.j3m"));
        shop.getChild("CloseVege2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege2.j3m"));
        shop.getChild("CloseVege3").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege3.j3m"));
        shop.getChild("CloseVege4").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege4.j3m"));
        shop.getChild("CloseVege5").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege5.j3m"));
        shop.getChild("CloseVege6").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege6.j3m"));
        shop.getChild("CloseVege7").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege7.j3m"));
        shop.getChild("CloseVeget").setMaterial(assetManager.loadMaterial(pathFileMaterial + "closeVege8.j3m"));
        shop.getChild("Colm01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "colm01.j3m"));
        shop.getChild("Colm02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "colm02.j3m"));
        shop.getChild("Compresor").setMaterial(assetManager.loadMaterial(pathFileMaterial + "compresor.j3m"));
        shop.getChild("CompWheels").setMaterial(assetManager.loadMaterial(pathFileMaterial + "compWheels.j3m"));
        shop.getChild("Contact ce").setMaterial(assetManager.loadMaterial(pathFileMaterial + "contactCE.j3m"));
        shop.getChild("Distributi").setMaterial(assetManager.loadMaterial(pathFileMaterial + "distributi.j3m"));
        shop.getChild("FarVegetat").setMaterial(assetManager.loadMaterial(pathFileMaterial + "farVegetat.j3m"));
        shop.getChild("FLCenterWh").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flCenterWh.j3m"));
        shop.getChild("FLHandle").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flHandle.j3m"));
        shop.getChild("FLLeftWhee").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flLeftWhee.j3m"));
        shop.getChild("FLRightWhe").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flRightWhe.j3m"));
        shop.getChild("FLMainFram").setMaterial(assetManager.loadMaterial(pathFileMaterial + "flMainFram.j3m"));
        shop.getChild("FordBody").setMaterial(assetManager.loadMaterial(pathFileMaterial + "fordBody.j3m"));
        shop.getChild("FrontWheel").setMaterial(assetManager.loadMaterial(pathFileMaterial + "frontWheel.j3m"));
        shop.getChild("Ground Edg").setMaterial(assetManager.loadMaterial(pathFileMaterial + "asphaltEnd.j3m"));
        shop.getChild("House 1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house1.j3m"));
        shop.getChild("House 2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house2.j3m"));
        shop.getChild("House 3").setMaterial(assetManager.loadMaterial(pathFileMaterial + "house3.j3m"));
        shop.getChild("Kart01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "kart01.j3m"));
        shop.getChild("Juanito").setMaterial(assetManager.loadMaterial(pathFileMaterial + "juanito.j3m"));
        shop.getChild("Lake").setMaterial(assetManager.loadMaterial(pathFileMaterial + "lake.j3m"));
        shop.getChild("LakeEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "lakeEdge.j3m"));
        shop.getChild("LegBoard00").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard00.j3m"));
        shop.getChild("LegBoard01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard01.j3m"));
        shop.getChild("LegBoard02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard02.j3m"));
        shop.getChild("LegBoard03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard03.j3m"));
        shop.getChild("LegBoard04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard04.j3m"));
        shop.getChild("LegBoard05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard05.j3m"));
        shop.getChild("LegBoard06").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard06.j3m"));
        shop.getChild("LegBoard07").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard07.j3m"));
        shop.getChild("LegBoard08").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard08.j3m"));
        shop.getChild("LegBoard09").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard09.j3m"));
        shop.getChild("LegBoard10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard10.j3m"));
        shop.getChild("LegBoard11").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard11.j3m"));
        shop.getChild("LegBoard12").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard12.j3m"));
        shop.getChild("LegBoard13").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard13.j3m"));
        shop.getChild("LegBoard14").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard14.j3m"));
        shop.getChild("LegBoard15").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard15.j3m"));
        shop.getChild("LegBoard16").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard16.j3m"));
        shop.getChild("LegBoard17").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard17.j3m"));
        shop.getChild("LegBoard18").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard18.j3m"));
        shop.getChild("LegBoard19").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard19.j3m"));
        shop.getChild("LegBoard20").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard20.j3m"));
        shop.getChild("LegBoard21").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard21.j3m"));
        shop.getChild("LegBoard22").setMaterial(assetManager.loadMaterial(pathFileMaterial + "legBoard22.j3m"));
        shop.getChild("MesaHerram").setMaterial(assetManager.loadMaterial(pathFileMaterial + "mesaHerram.j3m"));
        shop.getChild("MetalBarr0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "metalBarr0.j3m"));
        shop.getChild("MetalBarre").setMaterial(assetManager.loadMaterial(pathFileMaterial + "metalBarre.j3m"));
        shop.getChild("MitterSawB").setMaterial(assetManager.loadMaterial(pathFileMaterial + "mitterSawB.j3m"));
        shop.getChild("PaintBooth").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintBooth.j3m"));
        shop.getChild("PaintCan01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan01.j3m"));
        shop.getChild("PaintCan02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan02.j3m"));
        shop.getChild("PaintCan03").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan03.j3m"));
        shop.getChild("PaintCan04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan04.j3m"));
        shop.getChild("PaintCan05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan05.j3m"));
        shop.getChild("PaintCan06").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan06.j3m"));
        shop.getChild("PaintCan07").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan07.j3m"));
        shop.getChild("PaintCan08").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan08.j3m"));
        shop.getChild("PaintCan09").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan09.j3m"));
        shop.getChild("PaintCan10").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintCan10.j3m"));
        shop.getChild("PaintThinn").setMaterial(assetManager.loadMaterial(pathFileMaterial + "paintThinn.j3m"));
        shop.getChild("Playwood01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood01.j3m"));
        shop.getChild("Playwood02").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood02.j3m"));
        shop.getChild("Playwood04").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood04.j3m"));
        shop.getChild("Playwood05").setMaterial(assetManager.loadMaterial(pathFileMaterial + "playWood05.j3m"));
        shop.getChild("RackPanele").setMaterial(assetManager.loadMaterial(pathFileMaterial + "rackPanele.j3m"));
        shop.getChild("River").setMaterial(assetManager.loadMaterial(pathFileMaterial + "river.j3m"));
        shop.getChild("RiverEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "riverEdge.j3m"));
        shop.getChild("RoadBridge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "roadBridge.j3m"));
        shop.getChild("RoadEdge").setMaterial(assetManager.loadMaterial(pathFileMaterial + "roadEdge.j3m"));
        shop.getChild("shop01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "shop01.j3m"));
        shop.getChild("sink").setMaterial(assetManager.loadMaterial(pathFileMaterial + "sink.j3m"));
        shop.getChild("Sky").removeFromParent(); // remove sky created in Blender
        shop.getChild("SteeringWh").setMaterial(assetManager.loadMaterial(pathFileMaterial + "steeringWh.j3m"));
        shop.getChild("Supplier 1").setMaterial(assetManager.loadMaterial(pathFileMaterial + "supplier1.j3m"));
        shop.getChild("Supplier 2").setMaterial(assetManager.loadMaterial(pathFileMaterial + "supplier2.j3m"));
        shop.getChild("Table saw").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tableSaw.j3m"));
        shop.getChild("Table01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "table01.j3m"));
        shop.getChild("Tabliller0").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tabliller0.j3m"));
        shop.getChild("Tablillero").setMaterial(assetManager.loadMaterial(pathFileMaterial + "tablillero.j3m"));
        shop.getChild("Toilet").setMaterial(assetManager.loadMaterial(pathFileMaterial + "toilet.j3m"));
        shop.getChild("TrashCan01").setMaterial(assetManager.loadMaterial(pathFileMaterial + "trashCan01.j3m"));
        Material grassMaterial = new Material(assetManager, "Common/MatDefs/Terrain/TerrainLighting.j3md");
        grassMaterial.setBoolean("useTriPlanarMapping", false);
        grassMaterial.setFloat("Shininess", 0.0f);
        Texture grass = assetManager.loadTexture("Scenes/Textures/grs1rgb_3.jpg");
        grass.setWrap(Texture.WrapMode.Repeat);
        grassMaterial.setTexture("DiffuseMap", grass);
        shop.getChild("Grass").setMaterial(grassMaterial);
    }

    /**
     * Interacts with update loop.
     * @param tpf 
     */
    @Override
    public void simpleUpdate(float tpf) {
       /* Get current forward and left vectors of the playerNode */
       Vector3f modelForwardDir = playerNode.getWorldRotation().mult(Vector3f.UNIT_Z);
       Vector3f modelLeftDir = playerNode.getWorldRotation().mult(Vector3f.UNIT_X);
       
       /* Determine the change in direction */
       walkDirection.set(0, 0, 0);
       if (forward) {
           walkDirection.addLocal(modelForwardDir.mult(playerSpeed));
       } else if (backward) {
           walkDirection.addLocal(modelForwardDir.mult(playerSpeed).negate());
       }
       playerControl.setWalkDirection(walkDirection); // walk!
       
       /* Determine the change in rotation */
       if (rotateLeft) {
           Quaternion rotateL = new Quaternion().fromAngleAxis(FastMath.PI * tpf, Vector3f.UNIT_Y);
           rotateL.multLocal(viewDirection);
       } else if (rotateRight) {
           Quaternion rotateR = new Quaternion().fromAngleAxis(-FastMath.PI * tpf, Vector3f.UNIT_Y);
           rotateR.multLocal(viewDirection);
       }
       playerControl.setViewDirection(viewDirection); // turn!
    }
    
    /**
     * Advanced renderer/framebuffer modifications.
     * @param rm 
     */
    @Override
    public void simpleRender(RenderManager rm) {}
}
