/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.utils;

/**
 *
 * @author David
 */
public class Messages {//46 characters
    public static final String wildCard = "#VAR#";
    public static final String wildCard2 = "#VAR2#";
    public static final String wildCard3 = "#VAR3#";
    public static String forgotPassword = "Hello " + wildCard + "\nWe received a request to recover the password associated with this e-mail address.\nYou can find it below:\n\nPassword: " + wildCard2 + "\n\nThank you,\nGaming Support";
    public static String newPassword = "Hello " + wildCard + "\nWe received a request to create a new profile associated with this e-mail address.\nYou can find your autogenerated password below:\n\nPassword: " + wildCard2 + "\n\nThank you,\nGaming Support";
    public static String moneyBetween1000and980 = "You'll go bankrupt.You won't have enough money";
    public static String moneyLessThan100 = "You'll go bankrupt.You almost don't have money";
    public static String gamePlay = "Game is running...";
    public static String gamePause = "Game is paused";
    public static String timeFactor = "Game is running at " + wildCard + " speed";
    public static String machineEquipmentBroken = wildCard + " " + wildCard2 + " is broken";
    public static String machineEquipmentFixed = wildCard + " " + wildCard2 + " is fixed";
    public static String machineEquipmentWarning = wildCard + " " + wildCard2 + " requires a preventive maintenance";
    public static String machineEquipmentPurchased = wildCard + " ID: " + wildCard2 + " has been purchased";
    public static String machineEquipmentSold = wildCard + " ID: " + wildCard2 + " has been sold";
    public static String machineEquipmentReturned = "The " + wildCard + " " + wildCard2 + " sale has been canceled";
    public static String machineEquipmentPreventiveMaintenance = "Preventive maintenance performed to the " + wildCard + " " + wildCard2;
    public static String orderArrived = "Order " + wildCard + " has arrived. Due date is in " + wildCard2 + " minutes";
    public static String orderWorking = "Order " + wildCard + " is in process";
    public static String orderFailed = "Order " + wildCard + " has been lost";
    public static String orderDone = "Order " + wildCard + " has been completed successfully";
    public static String orderEnoughParts = "You have enough " + wildCard + " to fulfill the order";
    public static String orderMoveParts = "You have an order waiting, move " + wildCard + " to Shipping Zone";
    public static String orderInsufficientParts = "You don't have enough " + wildCard + " to fulfill the order";
    public static String noSpaceAvailableInStation = "There is no space available in '" +  wildCard + "' to make a transport activity";
    public static String gameSetup = "Please setup your game before start";
    public static String gameClosing = "Your session will be closed in: " + wildCard + " seconds";
    public static String gameIsClosed = "Your session is closing now!";
}
