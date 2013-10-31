package edu.uprm.gaming;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;

public class NarratorTester extends SimpleApplication {
    private Nifty nifty;
    
    public static void main(String[] args) {
        NarratorTester app = new NarratorTester();
        app.start();
    }
    private boolean isAwayAgain;

    @Override
    public void simpleInitApp() {
        createUserInterface();
        createBox();
        flyCam.setMoveSpeed(50.0f);
    }
    
    private void createUserInterface() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
                assetManager, inputManager, audioRenderer, guiViewPort);
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/Narrator/screen.xml", "start");
        guiViewPort.addProcessor(niftyDisplay);
    }
    
    private void createBox() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }
    
    private Element getElement(final String id) {
        return nifty.getCurrentScreen().findElementByName(id);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        if (cam.getLocation().getZ() < 5) {
            getElement("narrator_text").getRenderer(TextRenderer.class).setText("EY EY EY! Dame espacio personal...");
            isAwayAgain = true;
        } 
        else if (isAwayAgain) {
            getElement("narrator_text").getRenderer(TextRenderer.class).setText("Okay, gracias.");
        }     
    }
}