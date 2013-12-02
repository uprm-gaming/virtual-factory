package com.virtualfactory.utils;

import com.jme3.math.ColorRGBA;

/**
 *
 * @author David
 */
public class Colors {
    private static String cBlack = "BLACK";
    private static String cBlue = "BLUE";
    private static String cBrown = "BROWN";
    private static String cCyan = "CYAN";
    private static String cDarkGray = "DRAKGRAY";
    private static String cGray = "GRAY";
    private static String cLightGray = "LIGHTGRAY";
    private static String cGreen = "GREEN";
    private static String cMagenta = "MAGENTA";
    private static String cOrange = "ORANGE";
    private static String cPink = "PINK";
    private static String cRed = "RED";
    private static String cWhite = "WHITE";
    private static String cYellow = "YELLOW";
    
    public static ColorRGBA getColorRGBA(String nameColor)
    {
        if (nameColor.toUpperCase().equals(cBlack.toUpperCase()))   return ColorRGBA.Black;
        if (nameColor.toUpperCase().equals(cBlue.toUpperCase()))   return ColorRGBA.Blue;
        if (nameColor.toUpperCase().equals(cBrown.toUpperCase()))   return ColorRGBA.Brown;
        if (nameColor.toUpperCase().equals(cCyan.toUpperCase()))   return ColorRGBA.Cyan;
        if (nameColor.toUpperCase().equals(cDarkGray.toUpperCase()))   return ColorRGBA.DarkGray;
        if (nameColor.toUpperCase().equals(cGray.toUpperCase()))   return ColorRGBA.Gray;
        if (nameColor.toUpperCase().equals(cLightGray.toUpperCase()))   return ColorRGBA.LightGray;
        if (nameColor.toUpperCase().equals(cGreen.toUpperCase()))   return ColorRGBA.Green;
        if (nameColor.toUpperCase().equals(cMagenta.toUpperCase()))   return ColorRGBA.Magenta;
        if (nameColor.toUpperCase().equals(cOrange.toUpperCase()))   return ColorRGBA.Orange;
        if (nameColor.toUpperCase().equals(cPink.toUpperCase()))   return ColorRGBA.Pink;
        if (nameColor.toUpperCase().equals(cRed.toUpperCase()))   return ColorRGBA.Red;
        if (nameColor.toUpperCase().equals(cWhite.toUpperCase()))   return ColorRGBA.White;
        if (nameColor.toUpperCase().equals(cYellow.toUpperCase()))   return ColorRGBA.Yellow;
        return ColorRGBA.randomColor();
    }
}
