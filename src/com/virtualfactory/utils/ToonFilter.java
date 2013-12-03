package com.virtualfactory.utils;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * Makes any given game scene or object cartoonish.
 * @author Abner Coimbre
 */
public class ToonFilter 
{
    private AssetManager assetManager;
    private ViewPort viewPort;
    
    public ToonFilter(AssetManager givenAssetManager, ViewPort givenViewPort)
    {
        assetManager = givenAssetManager;
        viewPort = givenViewPort;
    }
    
    public void applyToScene(Spatial scene) 
    {
        activateCartoonEdgeFilter();
        //makeToonish(scene); NOTE: Method will work when we have j3m materials in the factory (e.g. Unshaded).
    }
    
    public void applyToObject(Spatial gameObject) 
    {
        makeToonish(gameObject);
    }

    private void activateCartoonEdgeFilter() 
    {
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        CartoonEdgeFilter edgeFilter = new CartoonEdgeFilter();
        edgeFilter.setEdgeWidth(1.0f);
        edgeFilter.setEdgeIntensity(1.0f);
        edgeFilter.setNormalThreshold(0.8f);
        fpp.addFilter(edgeFilter);
        viewPort.addProcessor(fpp);
    }
    
    private void makeToonish(Spatial spatial) 
    {
        if (spatial instanceof Node) 
        {
            Node n = (Node) spatial;
            
            for (Spatial child : n.getChildren())
                makeToonish(child);

        } 
        else if (spatial instanceof Geometry) 
        {
            Geometry g = (Geometry) spatial;
            
            Material newCartoonishMat = new Material(assetManager, "ShaderBlow/MatDefs/LightBlow.j3md");
    
            newCartoonishMat.setTexture("ColorRamp", assetManager.loadTexture("Textures/toon.png"));

            newCartoonishMat.setBoolean("Toon", true);

            g.setMaterial(newCartoonishMat);
        }
    }
}