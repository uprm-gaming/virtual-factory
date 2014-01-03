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
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial.CullHint;

public class Narrator extends AbstractAppState
{
    private AssetManager assetManager;
    private BitmapText narratorText;
    private AudioNode narratorAudio;
    private long textStartTime = 0;
    private long secondsToWait = 0;
    
    public Narrator(AppStateManager stateManager, AssetManager assetManager, Node guiNode)
    {
        this.assetManager = assetManager;
        narratorAudio = new AudioNode();
        initNarratorText(guiNode);
        stateManager.attach(this);
    }
    
    private void initNarratorText(Node guiNode)
    {
        BitmapFont narratorTextFont = assetManager.loadFont("Interface/ArialRoundedMTBold.fnt");

        narratorText = new BitmapText(narratorTextFont);
        narratorText.setSize(narratorTextFont.getCharSet().getRenderedSize());
        narratorText.setLineWrapMode(LineWrapMode.Word);
        narratorText.setBox(new Rectangle(325,0, 1280 - 325*2, 200));
        narratorText.setAlignment(BitmapFont.Align.Center);
        narratorText.move(0, 200, 1);
        narratorText.setColor(ColorRGBA.DarkGray);
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
    }
    
    public void hide()
    {
        narratorText.setCullHint(CullHint.Always);
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
}