package com.virtualfactory.screen.other;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial.CullHint;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class handles the text displayed by the security cameras.
 * @author Abner Coimbre
 */
public class VideoCamGUI
{
    private final Node guiNode;
    private final AssetManager assetManager;
    
    public static final String STATIC_CAM = "STATIC CAM 0";
    
    public static final String SECURITY_CAM_1 = "SECURITY CAM 1";
    public static final String SECURITY_CAM_2 = "SECURITY CAM 2";
    public static final String SECURITY_CAM_3 = "SECURITY CAM 3";
    public static final String SECURITY_CAM_4 = "SECURITY CAM 4";
    
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    private BitmapText dateAndTime;
    private BitmapFont myFont;
    private BitmapText camInfo;
    
    public VideoCamGUI(AssetManager assetManager, Node guiNode)
    {
        this.assetManager = assetManager;
        this.guiNode = guiNode; 
        initHUD();
    }
    
    private void initHUD()
    {
        myFont = assetManager.loadFont("Interface/Fonts/AmericanTypewriter.fnt");
        initCamInfo();
        initDateAndTime();
    }
    
    private void initCamInfo()
    {
        camInfo = new BitmapText(myFont);
        
        camInfo.setName("cam text");
        camInfo.setSize(myFont.getCharSet().getRenderedSize());
        camInfo.setText("");
        camInfo.setColor(ColorRGBA.Red);
        camInfo.move(100, 100, 0);
        
        guiNode.attachChild(camInfo);
    }
    
    public void showCamInfo(String cameraName)
    {
        if (camInfo.getCullHint() == CullHint.Always)
            camInfo.setCullHint(CullHint.Never);
        
        String text;
        
        switch(cameraName)
        {
            case STATIC_CAM:
                text = STATIC_CAM;
                break;
            
            case SECURITY_CAM_1:
                text = SECURITY_CAM_1;
                break;
                
            case SECURITY_CAM_2:
                text = SECURITY_CAM_2;
                break;   
            
            case SECURITY_CAM_3:
                text = SECURITY_CAM_3;
                break;
                
            case SECURITY_CAM_4:
                text = SECURITY_CAM_4;
                break;
               
            default:
                text = "Unknown Cam";
                break;
        }
        
        camInfo.setText(text);
    }
    
    public void hideCameraInfo()
    {
        camInfo.setCullHint(CullHint.Always);
    }

    private void initDateAndTime()
    {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy   HH:mm:ss");
        calendar = Calendar.getInstance();
        
        dateAndTime = new BitmapText(myFont);
        dateAndTime.setName("date text");
        dateAndTime.setSize(myFont.getCharSet().getRenderedSize());
        dateAndTime.setText("");
        dateAndTime.setColor(ColorRGBA.Red);
        dateAndTime.move(100, 50, 0);
        
        guiNode.attachChild(dateAndTime);
    }
    
    public void showDateAndTime()
    {
        dateAndTime.setCullHint(CullHint.Never);
        updateDateAndTime();
    }
    
    public void hideDateAndTime()
    {
        dateAndTime.setCullHint(CullHint.Always);
    }
    
    public void updateDateAndTime()
    {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy   HH:mm:ss");
        calendar = Calendar.getInstance();
        dateAndTime.setText(dateFormat.format(calendar.getTime()));
    }
    
    public String getDisplayedDateAndTime()
    {
        return dateAndTime.getText();
    }
    
    public String getUpdatedDateAndTime()
    {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy   HH:mm:ss");
        calendar = Calendar.getInstance();
        
        return dateFormat.format(calendar.getTime());
    }
    
    public void enable()
    {
        showCamInfo("");
        showDateAndTime();
    }
    
    public boolean isEnabled()
    {
        return camInfo.getCullHint() == CullHint.Never && dateAndTime.getCullHint() == CullHint.Never;
    }
    
    public void disable()
    {
        hideCameraInfo();
        hideDateAndTime();
    }
    
    public boolean isDisabled()
    {
        return camInfo.getCullHint() == CullHint.Always && dateAndTime.getCullHint() == CullHint.Always;
    }
}