package com.virtualfactory.narrator;

import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.LineWrapMode;
import com.jme3.font.Rectangle;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import java.util.ArrayList;

public class Narrator extends AbstractAppState
{
    private AssetManager assetManager;
    private BitmapText narratorText;
    private Geometry dialogBox;
    private AudioNode narratorAudio;
    private Node guiNode;
    private long textStartTime = 0;
    private long secondsToWait = 0;
    
    public Narrator(AppStateManager stateManager, AssetManager assetManager, Node guiNode)
    {
        this.assetManager = assetManager;
        this.guiNode = guiNode;
        narratorAudio = new AudioNode();
        initNarratorText();
        stateManager.attach(this);
    }
    
    private void initNarratorText()
    {
        BitmapFont narratorTextFont = assetManager.loadFont("Interface/ArialRoundedMTBold.fnt");
        narratorText = new BitmapText(narratorTextFont);
        narratorText.setSize(narratorTextFont.getCharSet().getRenderedSize());
        narratorText.setLineWrapMode(LineWrapMode.Word);
        narratorText.setBox(new Rectangle(325,0, 1280 - 325*2, 200));
        narratorText.setAlignment(BitmapFont.Align.Center);
        narratorText.move(0, 200, 1);
        narratorText.setColor(ColorRGBA.White);
        createTextBackgound(1280 - 325*2, 150);        
        guiNode.attachChild(narratorText);
    }

    public void talk(String dialogue, int secondsBeforeHiding)
    {
        textStartTime = System.currentTimeMillis();
        secondsToWait = secondsBeforeHiding;
        talk(dialogue);
    }
    
    public void talk(String dialogue, String audioPathFile)
    {
        talk(dialogue);
        playAudioFile(audioPathFile);
    }
    
    public void talk(String dialogue)
    {
        narratorText.setText(dialogue);
        
        float width = getTextWidth();
        float height = narratorText.getLineHeight()*narratorText.getLineCount();        
        createTextBackgound(width, height);

        if (isHidden())
            show();
    }
    
    private void playAudioFile(String path)
    {
        flushAudio();
        narratorAudio = new AudioNode(assetManager, path, false);
        narratorAudio.setPitch(1.1f);
        narratorAudio.play();
    }
    
    private void flushAudio()
    {
        if (narratorAudio != null) 
        {
            narratorAudio.stop();
            narratorAudio = null;
        }
    }
    
    public boolean hasStoppedTalking()
    {
        return narratorAudio.getStatus() == AudioSource.Status.Stopped;
    }

    private boolean hasTimeExpired() 
    {
        boolean timeExpired = false;

        if(Math.abs((System.currentTimeMillis() - textStartTime))/1000 > secondsToWait)
            timeExpired = true;

        return timeExpired;
    }

    public void show()
    {
        narratorText.setCullHint(CullHint.Never);
        dialogBox.setCullHint(CullHint.Never);
    }
    
    public void hide()
    {
        narratorText.setCullHint(CullHint.Always);
        dialogBox.setCullHint(CullHint.Always);

    }
    
    public boolean isHidden()
    {
        return narratorText.getCullHint() == CullHint.Always;
    }

    @Override
    public void update(float tpf)
    {
        if (hasStoppedTalking() && hasTimeExpired())
            hide();
    }

    private void createTextBackgound(float width, float height) {
        
        Quad rectangle = new Quad(width + 20, height + 20);
        dialogBox = new Geometry("My Textured Quad", rectangle);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(0.1f, 0.1f, 0.1f, 0.6f));
        mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        dialogBox.setMaterial(mat);
        dialogBox.move(315 + ((1280 - 325*2) - width)/2, 190 - height, 0);
        
        guiNode.detachAllChildren();
        guiNode.attachChild(dialogBox);
        guiNode.attachChild(narratorText);

    }

    private float getTextWidth() {
        float width = 0;
        String text = narratorText.getText();
        
        String text2 = text.toString();
        ArrayList<String> s = new ArrayList<>();
        int i = 0;
        int pos = text2.indexOf("\n");
        
        while (pos > 0) {
            System.out.println("---" + text2);
            s.add(text2.substring(0, pos ));
            text2 = text2.substring(pos);
            pos = text2.indexOf('\n');
            i++;
        }
        s.add(text2);
        
        int length = 0;
        for(String string: s)
            if (length < string.length())
                length = string.length();
        
        width = narratorText.getSize()*length - 60;
        if (width > 1280 - 325*2)
            width = 1280 - 325*2;
        
        return width;
    }
}