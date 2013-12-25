package com.virtualfactory.utils;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * With a given size and location, once attached to the root node, it'll block 
 * the path of any physical object (including the player).
 * 
 * Note: You can see your invisible wall by setting bulletAppState.setDebugEnabled(true)
 * 
 * @author Abner Coimbre
 */
public class InvisibleWall extends Geometry
{
    private InvisibleWall(Vector3f size, Vector3f location)
    {
        super("invisible wall", new Box(10.0f, 10.0f, 10.0f));
        setLocalScale(size);
        setLocalTranslation(location);
    }
    
    public InvisibleWall(BulletAppState bulletAppState, Vector3f size, Vector3f location)
    {
        this(size, location);
        makeSolid(bulletAppState);
        makeInvisible();
    }
    
    private void makeSolid(BulletAppState physicsEngine)
    {
        CollisionShape boxShape = CollisionShapeFactory.createBoxShape(this);
        RigidBodyControl rigidBody = new RigidBodyControl(boxShape, 0.0f);
        addControl(rigidBody);
        physicsEngine.getPhysicsSpace().add(rigidBody);
    }
    
    private void makeInvisible()
    {
        setCullHint(CullHint.Always);
    }
}