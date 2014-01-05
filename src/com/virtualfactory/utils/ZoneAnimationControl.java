package com.virtualfactory.utils;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.control.GhostControl;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 * Allows a station to be aware of its surroundings so it can make itself visible 
 * or invisible, depending on player proximity (Note: also rotates when visible 
 * to give a cartoonish animation).
 * 
 * @author Abner Coimbre
 */
public class ZoneAnimationControl extends AbstractControl 
{
    private final GhostControl stationRadius;
    
    private long initialTime;
    private long currentTime;
    
    private float rotationDegree = 0.1f;

    private boolean timeToPlayGrowingSound = true;
    private AudioNode growingSound;

    public ZoneAnimationControl (AssetManager givenAssetManager, GhostControl givenStationRadius)
    {
        initStationSounds(givenAssetManager);
        stationRadius = givenStationRadius;
        initialTime = System.currentTimeMillis();
    }
    
    private void initStationSounds(AssetManager assetManager) 
    {
        growingSound = new AudioNode(assetManager, "Sounds/growingSound.wav", false);
        growingSound.setPositional(false);
    }
    
    @Override
    protected void controlUpdate(float tpf) 
    {
        if (stationRadius.getOverlappingCount() > 1)
        {
            if (timeToPlayGrowingSound) 
            {
                growingSound.playInstance();
                timeToPlayGrowingSound = false;
            }
            
            if (spatial.getLocalScale().getX() < 1) 
                spatial.setLocalScale(spatial.getLocalScale().add(0.03f, 0.03f, 0.03f));
            else
                rotate();
        }
        else 
        {
            if (spatial.getLocalScale().getX() > 0)
                spatial.setLocalScale(spatial.getLocalScale().subtract(0.03f, 0.03f, 0.03f));
            timeToPlayGrowingSound = true;
        }
    }
    
    private void rotate() 
    {
        currentTime = System.currentTimeMillis();
        
        if (currentTime - initialTime >= 1000) 
        {
            rotationDegree = -rotationDegree;
            initialTime = System.currentTimeMillis();
        }
        
        spatial.rotate(0.0f, 0.0f, FastMath.DEG_TO_RAD * rotationDegree);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) 
    {} 
}