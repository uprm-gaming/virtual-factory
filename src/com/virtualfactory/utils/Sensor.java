package com.virtualfactory.utils;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Box;

/**
 * A sensor that can be placed in the game. 
 * 
 * Use isPlayerInRange() to determine if the player is within the sensor's range.
 * 
 * [Warning: If the sensor is placed somewhere where there are already overlapping
 *  physical objects, then isPlayerInRange() will always give true, even if the player isn't there.]
 * 
 * @author Abner Coimbre
 */
public class Sensor extends GhostControl 
{
    Vector3f size;
    Vector3f location;
    BulletAppState physicsEngine;
    
    public Sensor(Vector3f boxSize, Vector3f location, BulletAppState physicsEngine)
    {
        super(new BoxCollisionShape(new Vector3f(boxSize)));
        
        this.size = boxSize;
        this.location = location;
        this.physicsEngine = physicsEngine;
        
        initSensor();
    }
    
    private void initSensor()
    {
        Geometry boxGeometry = new Geometry("sensor box", new Box(1, 1, 1));    
        boxGeometry.setLocalTranslation(location);
        boxGeometry.setCullHint(CullHint.Always);
        boxGeometry.addControl(this);
        
        physicsEngine.getPhysicsSpace().add(this);
    }
    
    public boolean isPlayerInRange()
    { 
        return this.getOverlappingCount() > 1; 
    }
}