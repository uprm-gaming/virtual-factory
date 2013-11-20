package edu.uprm.gaming.narrator;

import com.jme3.app.state.AbstractAppState;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.LineWrapMode;
import com.jme3.font.Rectangle;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial.CullHint;

public class NarratorAppState extends AbstractAppState
{
    private AssetManager assetManager;
    private BitmapText narratorText;
    private AudioNode narratorAudio;
    private long textStartTime = 0;
    private long secondsToWait = 0;
    
    public NarratorAppState(AssetManager assetManager, Node guiNode)
    {
        initAssetManager(assetManager);
        initNarratorAudio();
        initNarratorText(guiNode);
    }
    
    private void initAssetManager(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }
    
    private void initNarratorAudio()
    {
        narratorAudio = new AudioNode();
    }
    
    private void initNarratorText(Node guiNode)
    {
        BitmapFont narratorTextFont = assetManager.loadFont("Interface/ArialRoundedMTBold.fnt");
        narratorText = new BitmapText(narratorTextFont);
        narratorText.setLineWrapMode(LineWrapMode.Word);
        narratorText.setSize(narratorTextFont.getCharSet().getRenderedSize());
        
        Rectangle r = new Rectangle(325,0, 1280 - 325*2, 200);
        narratorText.setBox(r);
        narratorText.setAlignment(BitmapFont.Align.Center);
        narratorText.move(0, 200, 1);
        
        guiNode.attachChild(narratorText);
    }
    
    public static NarratorAppState newInstance(AssetManager assetManager, Node guiNode)
    {
        return new NarratorAppState(assetManager, guiNode);
    }
    
    public void talk(String text, String audioPathFile)
    {
        talk(text);
        playAudioFile(audioPathFile);
    }
    
    public void talk(String text, int seconds)
    {
        textStartTime = System.currentTimeMillis();
        secondsToWait = seconds;
        talk(text);
    }
    
    private void talk(String text)
    {
        if (isHidden())
            show();
        narratorText.setText(text);
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

    private boolean hasTimeExpired() {
            if(Math.abs((System.currentTimeMillis() - textStartTime))/1000 > secondsToWait) {
                return true;
            }
            return false;
    }
}