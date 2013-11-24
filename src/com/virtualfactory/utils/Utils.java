/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.utils;

import java.text.DecimalFormat;
/**
 *
 * @author David
 */
public class Utils {
    
    public static String dropNoIntegers(String tempVal){
        String newString = "";
        for (int i=0; i<tempVal.length(); i++){
            if (48 <= (int)tempVal.charAt(i) && (int)tempVal.charAt(i) <= 57)
                newString = newString + tempVal.charAt(i);
        }
        return newString;
    }
    public static String dropNoIntegers_AllowPoint(String tempVal){
        String newString = "";
        for (int i=0; i<tempVal.length(); i++){
            if ((48 <= (int)tempVal.charAt(i) && (int)tempVal.charAt(i) <= 57) || (int)tempVal.charAt(i) == 46)
                newString = newString + tempVal.charAt(i);
        }
        return newString;
    }
    public static double formatValue2Dec(double tempVal){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(tempVal));
    }
    
    public static String formatValue2DecToString(double tempVal){
        DecimalFormat df = new DecimalFormat("###,###,##0.00");
        return String.valueOf(df.format(tempVal));
    }
    
    public static boolean isParsableToInt(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static String convertToMonth_short(int month){
        switch (month){
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
            default: return "";
        }
    }
    
    public static String convertToMonth_long(int month){
        switch (month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }
}