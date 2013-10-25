package edu.uprm.gaming;

import com.jme3.asset.AssetManager;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.post.filters.FogFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.MagFilter;
import com.jme3.texture.Texture.MinFilter;
import com.jme3.texture.Texture.WrapMode;
import java.io.IOException;

/**
* Contains the Sky's behavior.
* @author t0neg0d 
* [Note: Cleaned & Updated by Abner Coimbre]
*/
public class SkyDomeControl implements Control {
    private ViewPort viewPort;
    private Spatial spatial;
    private AssetManager assetManager;
    private Node skyNight;
    private Camera cam;
    private boolean enabled = true;
    private FogFilter fog = null;
    private DirectionalLight sun = null;
    private ColorRGBA sunDayLight = new ColorRGBA(1f, 1f, 1f, 1f);
    private ColorRGBA sunNightLight = new ColorRGBA(.45f, .45f, .45f, 1f);
    private boolean controlFog = false;
    private boolean controlSun = false;
    private String model, nightSkyMap, moonMap, cloudsMap, fogAlphaMap;
    private boolean cycleCI = false, cycleCO = false;
    private float cloudMaxAlpha = 1f, cloudMinAlpha = 0f, cloudsAlpha = 1;
    private float cloudCycleSpeed = .125f;
    private float cloud1Rotation = FastMath.HALF_PI + 0.02f;
    private float cloud1Speed = .025f;
    private float cloud2Rotation = FastMath.HALF_PI + 0.023f;
    private float cloud2Speed = .05f;
    private float moonRotation = 75f;
    private float moonSpeed = .0185f;
    private boolean isDay = false;
    private boolean cycleN2D = false, cycleD2N = false;
    private float dayAlpha = 0;
    private float cycleSpeed = .125f;
    private ColorRGBA fogColor = new ColorRGBA(0.7f, 0.7f, 0.7f, 0.6f);
    private ColorRGBA fogNightColor = new ColorRGBA(0.3f, 0.3f, 0.3f, 0.6f);
    private ColorRGBA dayColor = new ColorRGBA(.7f, .7f, 1.0f, 1.0f);
    private ColorRGBA nightColor = new ColorRGBA(.4f, .3f, .6f, 1.0f);
    private Texture tex_Sky, tex_Moon, tex_FogAlpha, tex_Clouds;
    private Material mat_Sky;

    /**
     * Creates a new SkyDome control
     *
     * @param assetManager A pointer to the JME application AssetManager
     * @param cam A pointer to the default Camera of the JME application
     * @param model j3o to use as the Sky Dome
     * @param nightSkyMap The string value of the texture asset for night time
     * sky
     * @param moonMap The string value of the texture asset for the moon. This
     * is the only param that accepts null
     * @param cloudsMap The string value of the texture asset for the clouds
     * @param fogAlphaMap The string value of the texture asset for the blending
     * alpha map for fog coloring
     */
    public SkyDomeControl(AssetManager assetManager, Camera cam, String model, String nightSkyMap, String moonMap, String cloudsMap, String fogAlphaMap) {
        this.assetManager = assetManager;
        this.cam = cam;

        this.model = model;
        this.nightSkyMap = nightSkyMap;
        this.moonMap = moonMap;
        this.cloudsMap = cloudsMap;
        this.fogAlphaMap = fogAlphaMap;

        tex_FogAlpha = assetManager.loadTexture(fogAlphaMap);
        tex_FogAlpha.setMinFilter(MinFilter.NearestNoMipMaps);
        tex_FogAlpha.setMagFilter(MagFilter.Nearest);
        tex_FogAlpha.setWrap(WrapMode.Repeat);

        tex_Sky = assetManager.loadTexture(nightSkyMap);
        tex_Sky.setMinFilter(MinFilter.BilinearNearestMipMap);
        tex_Sky.setMagFilter(MagFilter.Bilinear);
        tex_Sky.setWrap(WrapMode.Repeat);

        if (moonMap != null) {
            tex_Moon = assetManager.loadTexture(moonMap);
            tex_Moon.setMinFilter(MinFilter.BilinearNearestMipMap);
            tex_Moon.setMagFilter(MagFilter.Bilinear);
            tex_Moon.setWrap(WrapMode.Repeat);
        }

        tex_Clouds = assetManager.loadTexture(cloudsMap);
        tex_Clouds.setMinFilter(MinFilter.BilinearNoMipMaps);
        tex_Clouds.setMagFilter(MagFilter.Bilinear);
        tex_Clouds.setWrap(WrapMode.Repeat);

        mat_Sky = new Material(assetManager, "ShaderBlow/MatDefs/SkyDome/SkyDome.j3md");
        mat_Sky.setTexture("SkyNightMap", tex_Sky);
        if (moonMap != null) {
            mat_Sky.setTexture("MoonMap", tex_Moon);
            mat_Sky.setFloat("MoonDirection", moonRotation);
            mat_Sky.setFloat("MoonSpeed", moonSpeed);
        }
        mat_Sky.setColor("ColorDay", dayColor);
        mat_Sky.setColor("ColorNight", nightColor);
        mat_Sky.setFloat("Alpha", dayAlpha);
        mat_Sky.setTexture("FogAlphaMap", tex_FogAlpha);
        mat_Sky.setTexture("CloudMap1", tex_Clouds);
        mat_Sky.setFloat("CloudDirection1", cloud1Rotation);
        mat_Sky.setFloat("CloudSpeed1", cloud1Speed);
        mat_Sky.setFloat("CloudDirection2", cloud2Rotation);
        mat_Sky.setFloat("CloudSpeed2", cloud2Speed);
        mat_Sky.setFloat("CloudsAlpha", cloudsAlpha);
        mat_Sky.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
        mat_Sky.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        mat_Sky.getAdditionalRenderState().setDepthWrite(false);

        skyNight = (Node) assetManager.loadModel(model);
        skyNight.setCullHint(Spatial.CullHint.Never);
        skyNight.setLocalScale(5f, 5f, 5f);
        skyNight.setMaterial(mat_Sky);
    }

    @Override
    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
        ((Node) spatial).attachChild(skyNight);
    }

    /**
     * Enable the SkyDome control
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns if the SkyDome control is enabled
     *
     * @return enabled
     */
    public boolean isEnabled() {
        return this.enabled;
    }

// Transitions
    public boolean getIsDay() {
        return this.isDay;
    }

    /**
     * Sets the speed at which the transition from night to day/day to night
     * happens
     *
     * @param cycleSpeed Default value is .125f
     */
    public void setDayNightTransitionSpeed(float cycleSpeed) {
        this.cycleSpeed = cycleSpeed;
    }

    /**
     * Gets the speed at which the transition from night to day/day to night
     * happens
     *
     * @return cycleSpeed Default value is .125f
     */
    public float getDayNightTransitionSpeed() {
        return this.cycleSpeed;
    }

    /**
     * Begins cycle day to night
     */
    public void cycleDayToNight() {
        this.cycleD2N = true;
        this.cycleN2D = false;
    }

    /**
     * Begins cycle night to day
     */
    public void cycleNightToDay() {
        this.cycleD2N = false;
        this.cycleN2D = true;
    }

// Fog
    /**
     * Sets a pointer to the fog filter used by the JME application that
     * initialized the SkyDome control
     *
     * @param fog The FogFilter to adjust during transitions
     * @param viewPort The default ViewPort for background color manipulation
     * used for fog blending
     */
    public void setFogFilter(FogFilter fog, ViewPort viewPort) {
        this.fog = fog;
        this.viewPort = viewPort;
    }

    /**
     * Sets the day time fog color to use
     *
     * @param fogColor Default value is 0.7f, 0.7f, 0.7f, 0.6f
     */
    public void setFogColor(ColorRGBA fogColor) {
        this.fogColor = fogColor;
        if (mat_Sky != null) {
            mat_Sky.setColor("FogColor", fogColor);
        }
    }

    /**
     * Gets the day time fog color
     *
     * @return fogColor Default value is 0.7f, 0.7f, 0.7f, 0.6f
     */
    public ColorRGBA getFogColor() {
        return this.fogColor;
    }

    /**
     * Sets the night time fog color to use
     *
     * @param fogNightColor Default value is 0.3f, 0.3f, 0.3f, 0.6f
     */
    public void setFogNightColor(ColorRGBA fogNightColor) {
        this.fogNightColor = fogNightColor;
        if (mat_Sky != null) {
            mat_Sky.setColor("FogNightColor", fogNightColor);
        }
    }

    /**
     * Gets the night time fog color
     *
     * @return fogNightColor Default value is 0.3f, 0.3f, 0.3f, 0.6f
     */
    public ColorRGBA getFogNightColor() {
        return this.fogNightColor;
    }

    /**
     * Enable SkyDome to control the JME application FogFilter
     *
     * @param controlFog Default value is false
     */
    public void setControlFog(boolean controlFog) {
        this.controlFog = controlFog;
    }

    /**
     * Returns if SkyDome controls the JME application FogFilter
     *
     * @return controlFog Default value is false
     */
    public boolean getControlFog() {
        return this.controlFog;
    }

// Sun
    /**
     * Pointer to the Directional Light used by your JME application as a sun
     * light
     *
     * @param sun
     */
    public void setSun(DirectionalLight sun) {
        this.sun = sun;
    }

    /**
     * Sets the color used by the sun during day time
     *
     * @param sunDayLight Default value is 1f, 1f, 1f, 1f
     */
    public void setSunDayLight(ColorRGBA sunDayLight) {
        this.sunDayLight = sunDayLight;
    }

    /**
     * Sets the color used by the sun during night time
     *
     * @param sunNightLight Default value is .45f, .45f, .45f, 1f
     */
    public void setSunNightLight(ColorRGBA sunNightLight) {
        this.sunNightLight = sunNightLight;
    }

    /**
     * Gets the color used by the sun during day time
     *
     * @return sunDayLight Default value is 1f, 1f, 1f, 1f
     */
    public ColorRGBA getSunDayLight() {
        return this.sunDayLight;
    }

    /**
     * Gets the color used by the sun during night time
     *
     * @return sunNightLight Default value is .45f, .45f, .45f, 1f
     */
    public ColorRGBA getSunNightLight() {
        return this.sunNightLight;
    }

    /**
     * Enable SkyDome to control the JME application DirectionLight
     *
     * @param controlSun Default value is false
     */
    public void setControlSun(boolean controlSun) {
        this.controlSun = controlSun;
    }

    /**
     * Returns if SkyDome controls the JME application DirectionalLight
     *
     * @return controlSun Default value is false
     */
    public boolean getControlSun() {
        return this.controlSun;
    }

// Day time color
    /**
     * Sets the color used for day time sky
     *
     * @param dayColor Default value is .7f, .7f, 1f, 1f
     */
    public void setDaySkyColor(ColorRGBA dayColor) {
        this.dayColor = dayColor;
        if (mat_Sky != null) {
            mat_Sky.setColor("ColorDay", dayColor);
        }
    }

    /**
     * Gets the color used for day time sky
     *
     * @return dayColor Default value is .7f, .7f, 1f, 1f
     */
    public ColorRGBA getDaySkyColor() {
        return this.dayColor;
    }

    /**
     * Sets the color blended to the day time sky for transitioning from day to
     * night/night to day
     *
     * @param nightColor Default value is .4f, .3f, .6f, 1f
     */
    public void setSkyNightColor(ColorRGBA nightColor) {
        this.nightColor = nightColor;
        if (mat_Sky != null) {
            mat_Sky.setColor("ColorNight", nightColor);
        }
    }

    /**
     * Gets the color blended to the day time sky for transitioning from day to
     * night/night to day
     *
     * @return nightColor Default value is .4f, .3f, .6f, 1f
     */
    public ColorRGBA getSkyNightColor() {
        return this.nightColor;
    }

// Moon
    /**
     * Sets the rotation/direction the moon moves in
     *
     * @param moonRotation Default value 75f
     */
    public void setMoonRotation(float moonRotation) {
        this.moonRotation = moonRotation;
        if (mat_Sky != null) {
            mat_Sky.setFloat("MoonRotation", moonRotation);
        }
    }

    /**
     * Gets the rotation/direction the moon moves in
     *
     * @return moonRotation Default value 75f
     */
    public float getMoonRotation() {
        return this.moonRotation;
    }

    /**
     * Sets the speed the moon moves
     *
     * @param moonSpeed Default value .0185f
     */
    public void setMoonSpeed(float moonSpeed) {
        this.moonSpeed = moonSpeed;
        if (mat_Sky != null) {
            mat_Sky.setFloat("MoonSpeed", moonSpeed);
        }
    }

    /**
     * Gets the speed the moon moves
     *
     * @return moonSpeed Default value .0185f
     */
    public float getMoonSpeed() {
        return this.moonSpeed;
    }

// Clouds
    /**
     * Sets the near cloud layer movement rotation/direction
     *
     * @param cloudRotation Default value FastMath.HALF_PI+0.02f
     */
    public void setCloudsNearRotation(float cloudRotation) {
        this.cloud2Rotation = cloudRotation;
        if (mat_Sky != null) {
            mat_Sky.setFloat("CloudDirection2", cloudRotation);
        }
    }

    /**
     * Gets the near cloud layer movement rotation/direction
     *
     * @return cloud2Rotation Default value FastMath.HALF_PI+0.02f
     */
    public float getCloudsNearRotation() {
        return this.cloud2Rotation;
    }

    /**
     * Sets the near cloud layer movement speed
     *
     * @param cloudSpeed Default value .05f
     */
    public void setCloudsNearSpeed(float cloudSpeed) {
        this.cloud2Speed = cloudSpeed;
        if (mat_Sky != null) {
            mat_Sky.setFloat("CloudSpeed2", cloudSpeed);
        }
    }

    /**
     * Gets the near cloud layer movement speed
     *
     * @param cloud2Speed Default value .05f
     */
    public float getCloudsNearSpeed() {
        return this.cloud2Speed;
    }

    /**
     * Sets the far cloud layer movement rotation/direction
     *
     * @param cloudRotation Default value FastMath.HALF_PI+0.023f
     */
    public void setCloudsFarRotation(float cloudRotation) {
        this.cloud1Rotation = cloudRotation;
        if (mat_Sky != null) {
            mat_Sky.setFloat("CloudDirection1", cloudRotation);
        }
    }

    /**
     * Gets the near cloud layer movement rotation/direction
     *
     * @return cloud1Rotation Default value FastMath.HALF_PI+0.02f
     */
    public float getCloudsFarRotation() {
        return this.cloud1Rotation;
    }

    /**
     * Sets the far cloud layer movement speed
     *
     * @param cloudSpeed Default value .025f
     */
    public void setCloudsFarSpeed(float cloudSpeed) {
        this.cloud1Speed = cloudSpeed;
        if (mat_Sky != null) {
            mat_Sky.setFloat("CloudSpeed1", cloudSpeed);
        }
    }

    /**
     * Gets the far cloud layer movement speed
     *
     * @return cloud1Speed Default value .025f
     */
    public float getCloudsFarSpeed() {
        return this.cloud1Speed;
    }

    /**
     * Sets the near and far cloud layers maximum opacity for cycling clouds
     * in/out
     *
     * @param cloudMaxOpacity Default value 1f
     */
    public void setCloudMaxOpacity(float cloudMaxOpacity) {
        this.cloudMaxAlpha = cloudMaxOpacity;
    }

    /**
     * Gets the near and far cloud layers maximum opacity for cycling clouds
     * in/out
     *
     * @return cloudMaxOpacity Default value 1f
     */
    public float getCloudMaxOpacity() {
        return this.cloudMaxAlpha;
    }

    /**
     * Sets the near and far cloud layers minimum opacity for cycling clouds
     * in/out
     *
     * @param cloudMinOpacity Default value 0f
     */
    public void setCloudMinOpacity(float cloudMinOpacity) {
        this.cloudMinAlpha = cloudMinOpacity;
    }

    /**
     * Gets the near and far cloud layers minimum opacity for cycling clouds
     * in/out
     *
     * @return cloudMinOpacity Default value 0f
     */
    public float getCloudMinOpacity() {
        return this.cloudMinAlpha;
    }

    /**
     * Sets the speed at which the near and far cloud layers are cycled in/out
     *
     * @param cloudCycleSpeed Default value .125f
     */
    public void setCloudCycleSpeed(float cloudCycleSpeed) {
        this.cloudCycleSpeed = cloudCycleSpeed;
    }

    /**
     * Gets the speed at which the near and far cloud layers are cycled in/out
     *
     * @return cloudCycleSpeed Default value .125f
     */
    public float getCloudCycleSpeed() {
        return this.cloudCycleSpeed;
    }

// Color mix function
    /**
     * Blends two ColorRGBAs by the amount passed in
     *
     * @param c1 The color being blended into
     * @param c2 The color to blend
     * @param amount The amount of c2 to blend into c1
     * @return r The resulting ColorRGBA
     */
    private ColorRGBA mix(ColorRGBA c1, ColorRGBA c2, float amount) {
        ColorRGBA r = new ColorRGBA();
        r.interpolate(c1, c2, amount);
        return r;
    }

// Day to night/night to day cycles
    /**
     * Begin cycle clouds in
     */
    public void cycleCloudsIn() {
        this.cycleCI = true;
        this.cycleCO = false;
    }

    /**
     * Begin cycle clouds out
     */
    public void cycleCloudsOut() {
        this.cycleCI = false;
        this.cycleCO = true;
    }

    @Override
    public void update(float tpf) {
        if (spatial != null && enabled) {
            Vector3f camLoc = cam.getLocation();
            float[] camLF = camLoc.toArray(null);
            spatial.setLocalTranslation(camLF[0], camLF[1] + .25f, camLF[2]);

// Day/Night Cycle
            if (cycleN2D) {
                if (dayAlpha < 1.0f) {
                    dayAlpha += tpf * cycleSpeed;
                    mat_Sky.setFloat("Alpha", dayAlpha);
                    if (fog != null && controlFog) {
                        viewPort.setBackgroundColor(mix(fogNightColor, fogColor, dayAlpha));
                        fog.setFogColor(mix(fogNightColor, fogColor, dayAlpha));
                    }
                    if (controlSun) {
                        sun.setColor(mix(sunNightLight, sunDayLight, dayAlpha));
                    }
                } else {
                    dayAlpha = 1.0f;
                    mat_Sky.setFloat("Alpha", dayAlpha);
                    if (fog != null && controlFog) {
                        viewPort.setBackgroundColor(fogColor);
                        fog.setFogColor(fogColor);
                    }
                    if (controlSun) {
                        sun.setColor(sunDayLight);
                    }
                    cycleN2D = false;
                }
            } else if (cycleD2N) {
                if (dayAlpha > 0.0f) {
                    dayAlpha -= tpf * cycleSpeed;
                    mat_Sky.setFloat("Alpha", dayAlpha);
                    if (fog != null && controlFog) {
                        viewPort.setBackgroundColor(mix(fogNightColor, fogColor, dayAlpha));
                        fog.setFogColor(mix(fogNightColor, fogColor, dayAlpha));
                    }
                    if (controlSun) {
                        sun.setColor(mix(sunNightLight, sunDayLight, dayAlpha));
                    }
                } else {
                    dayAlpha = 0.0f;
                    mat_Sky.setFloat("Alpha", dayAlpha);
                    if (fog != null && controlFog) {
                        viewPort.setBackgroundColor(fogNightColor);
                        fog.setFogColor(fogNightColor);
                    }
                    if (controlSun) {
                        sun.setColor(sunNightLight);
                    }
                    cycleD2N = false;
                }
            }

// Clouds Cycle
            if (cycleCI) {
                if (cloudsAlpha < cloudMaxAlpha) {
                    cloudsAlpha += tpf * cloudCycleSpeed;
                    mat_Sky.setFloat("CloudsAlpha", cloudsAlpha);
                } else {
                    cloudsAlpha = cloudMaxAlpha;
                    mat_Sky.setFloat("CloudsAlpha", cloudsAlpha);
                    cycleCI = false;
                }
            } else if (cycleCO) {
                if (cloudsAlpha > cloudMinAlpha) {
                    cloudsAlpha -= tpf * cloudCycleSpeed;
                    mat_Sky.setFloat("CloudsAlpha", cloudsAlpha);
                } else {
                    cloudsAlpha = cloudMinAlpha;
                    mat_Sky.setFloat("CloudsAlpha", cloudsAlpha);
                    cycleCO = false;
                }
            }
        }
    }

    @Override
    public void render(RenderManager rm, ViewPort vp) {}

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        SkyDomeControl control = new SkyDomeControl(this.assetManager, this.cam,
                this.model,
                this.nightSkyMap,
                this.moonMap,
                this.cloudsMap,
                this.fogAlphaMap);
        control.spatial.addControl(control);
        return control;
    }
    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(enabled, "enabled", true);
        oc.write(spatial, "spatial", null);
    }
    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule ic = im.getCapsule(this);
        enabled = ic.readBoolean("enabled", true);
        spatial = (Spatial) ic.readSavable("spatial", null);
    }
}