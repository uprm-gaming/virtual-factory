package com.virtualfactory.utils;

import com.jme3.math.Vector3f;
import com.jme3.post.filters.FadeFilter;
import com.virtualfactory.tutorial.Tutorial;

public class Params {

    public static final boolean DEBUG_ON = false;
    public static final boolean SKIP_LOGIN_SCREEN = true;
    public static final boolean BUILD_FOR_MAC_APP = false;
    public static final boolean BUILD_FOR_TESTING_SESSION = false;

    public static String renderer = "";
    public static final String supportedRenderer = "LWJGL-OpenGL2";
    public static int screenHeight = 0;
    public static int fullScreenHeight = 0;
    public static boolean isTutorialLevel = false;
    public static boolean firstRun = true;
    public static Tutorial tutorial;
    public static boolean isLevelStarted = false;
    
    public static final String ControlsDescription = ""
            + "W: move forward\n"
            + "S: move backward\n"
            + "Q: move left\n"
            + "E: move right\n"
            + "---------------------------------\n"
            + "A: look left\n"
            + "D: look right\n"
            + "Arrow Keys: look around\n"
            + "---------------------------------\n"
            + "Left-Shift:  select an object\n"
            + "---------------------------------\n"
            + "Right-Shift: open/close the dashboard\n"
            + "---------------------------------\n"
            + "F1: turn on/off the full screen\n"
            + "\n"
            + "\n";
    
    public static boolean topViewAvailable = true;
    public static boolean isTopViewEnabled = false;
    public static int viewNumber = 0;
    public static final float playerGravity = 120;
    public static float flyCamRotationSpeed = 0;
    public static Vector3f camAxesLeft = null;
    public static Vector3f camAxesUp = null;
    public static Vector3f camAxesDir = null;
    public static FadeFilter fadeFilter = null;
    public static float rotationSpeed = 0.01f;
    public static float camMaxX = 100f;
    public static float camMinX = -100f;
    public static float camMaxY = 0.65f;
    public static float camMinY = -0.65f;
    public static float camMaxZ = 100f;
    public static float camMinZ = -100f;
    public static final float playerMaxX = 100f;
    public static final float playerMinX = -100f;
    public static final float playerMaxY = 0.65f;
    public static final float playerMinY = -0.65f;
    public static final float playerMaxZ = 100f;
    public static final float playerMinZ = -100f;
    public static final float cam1MaxX = 0f;
    public static final float cam1MinX = -100f;//-0.92f;
    public static final float cam1MaxZ = 100f;//-0.30f;
    public static final float cam1MinZ = -100f;//-0.92f;
    public static final float securityCamsMaxY = -0.32f;
    public static final float securityCamsMinY = -0.92f;




    //-Virtual Factory 1.0 params
    public static int maxTime = 60;
    public static int maxLoadQuantity = 100;
    public static int timeUnitsToRefresh = 2;
    public final static int numberWorkHoursPerDay = 8;
    public static int numberWorkDaysPerMonth = 30;
    public static int standardOperatorWidthLength = 5;
    public static int standardBucketWidthLength = 5;
    public static int standardPartWidthLength = 4;
    public static int standardLocationY = 20;
    public static int terrainWidth = 300;//450;
    public static int terrainHeight = 500;//300;
    public static int terrainWidthLoc = 100;
    public static int terrainHeightLoc = 350;
    public static int startEvent = 1;
    public static int endEvent = 2;
    public static int skillPickUpId = 0;
    public static int skillMoveId = 1;
    public static int skillPlacementId = 2;
    public static int skillStoreId = 3;
    public static int skillOperateId = 4;
    public static int skillCarrier = 5;
    public static int skillAssembler = 6;
    public static double randomVariablePercentageMin = 0.01;//  1%
    public static double randomVariablePercentageMax = 2.5;// 250%
    public static String inactive = "S";
    public static String active = "P";
    public static String opeInactive = "F";
    public static String opeActive = "H";
    public static String moneySign = "USD";
    public static String moneySignSizeField = "30px";
    public static double depreciationRateInMinutes = 60.0*numberWorkHoursPerDay;
    public static String timeUnitShort = "min.";
    public static double overheadRateInMinutes = 60;
//    public static double equipmentNormalParam = 0.75;
    public static double operatorTaskExecutionParam = 0.75;
//    public static double machineNormalParam = 1.25;
    public static double machineTimeParam = 1.25;
    public static int firstPurchaseTime = 3;
    public static float percentageLoading = 0.0f;
    public static float percentageQuote = 0.0f;
    public static double percentageToShowWarningMachineEquipment = 0.90;

    public static String selectDatabase = "remote";

    public static String localhostUser = "root";
    public static String localhostPass = "admin";
    public static String localhostServer = "localhost";
    public static String localhostDB = "gaming";
    public static String remoteUser = "davidbengoa";
    public static String remotePass = "123456";
    public static String remoteServer = "136.145.151.198";
    public static String remoteDB = "gaming";
    public static String errorDatabaseMessage = "";
    public static boolean existsInternetConnection = true;
    public static boolean isSQLiteDatabase = true;
    public static int timeToSaveLogSeconds = 120;//seconds
    public static int timeToCloseGameSeconds = 600;//seconds 600
    public static int timeToExitGameSeconds = 60;//seconds
    public static int timeToUpdateSlotsMinutes = 120;//minutes
    public static double timeToShowDashboard = 0.5;//seconds
    public static double timeToHideDashboard = 0.5;//seconds

    public static String setupDescription = " Please setup the game step by step:";
    public static String setupResources = "1. Setup resources (workers, machines, and equipment)";
    public static String setupStorage = "2. Allocate storages (slots per storage)";
    public static String setupUnitLoad = "3. Setup unit load for each transport activity";
    public static String setupPurchase = "4. Setup reorder point and order quantity for each purchase";
    public static String setupOperators = "5. Assign workers for each activity";
    public static String setupPriority = "6. Setup execution priority for each activity";
    public static int stationList = -2;
    public static int machineList = -2;
    public static int equipmentList = -3;
    public static int operatorList = -2;
    public static int partList = -2;
    public static int supplierList = -2;
    public static int simpackPurchase = 1;
}
