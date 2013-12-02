/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.entity;

import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.OperatorCategory;
import com.virtualfactory.utils.TypeElements;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.TypeActivity;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.Utils;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.LoopMode;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionTrack;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.virtualfactory.engine.GameEngine;
import java.util.ArrayList;
import com.jme3.scene.shape.Sphere;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class E_Operator {
    private int idOperator;
    private String nameOperator;
    private Status status;
    private Owner owner;
//    private ArrayList<Integer> arrSkill;
//    private ArrayList<Double> arrEfficiency;
    private ArrayList<Pair<Integer,Double>> arrSkills;
    private String tempVirtualIdLocation;
    private int tempVirtualMatrixI;
    private int tempVirtualMatrixJ;
    //display
    private String virtualIdLocation_StaffZone;
    private String virtualIdLocation;
    private int virtualMatrixI;
    private int virtualMatrixJ;
    private int currentLocationX;
    private int currentLocationZ;
    private double speed;
    private CharacterControl character;
    private AnimChannel animationChannel;
    private Node modelCharacter;
    private Geometry baseCharacter;
    private Vector3f vectorFrom = null;
    private Vector3f vectorTo = null;
    private Vector3f physicsLocation = null;
    private MotionPath motionPath;
    private MotionTrack motionControl;
    private double tempSpeed;
    private ObjectState state;
    private int defaultValue = 0;
    public boolean activateLaterDeactivation = false;
    private double salaryPerHour;
    private double salaryPerHourCarrier;
    private double salaryPerHourAssembler;
    private double salaryPerHourVersatile;
    private double priceForHire;
    private double priceForFire;
    private double earnedMoney = 0;
    private double accumulatedActiveTime = 0;
    private double accumulatedWorkingTime = 0;
    private E_Game initialGame = null;
    private E_Game finalGame = null;
    private E_Game currentGame = null;
    private E_Game initialBusyGame = null;
    private OperatorCategory category = OperatorCategory.None;
    private Pair<Integer,Double> categoryCarrier = null;
    private Pair<Integer,Double> categoryAssembler = null;
    
    //PARAMS FOR ANIMATION
    private TypeActivity activityDoing = TypeActivity.None;
    private boolean isMoving = false;
    private int idMachineAttached = -1;;
//    private E_Machine machineAttached;
    private int endLocationX = -1;
    private int endLocationZ = -1;
    private int idStrategyAssigned = -1;
    private GameEngine gameEngine = null;
    private Geometry inactiveOperator;
    private Material materialForOperator;
    private AssetManager assetManager;
    private Node rootNode;
    private Map<Integer,E_Activity> arrActivitiesAssigned;
    private String activityDescriptionAssigned = "";
    private double uniformParam1 = 0.0;
    private double uniformParam2 = 0.0;
    private double operatorSpeedCalculated = 0.0;
    private double normalParamLoad = 0.0;
    private double normalParamUnload = 0.0;
    public boolean isLoadingInitialOperatorData = false;
    private Sphere sphereSpot;
    
    private static String[] operatorAssembler = new String[6];
    private static int index1 = -1;
    
    private static String[] operatorCarrier = new String[8];
    private static int index2 = -1;
    
    private static String[] operatorVersatile = new String[2];
    private static int index3 = -1;


    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public double getOperatorSpeedCalculated(double normalParam) {
        operatorSpeedCalculated = normalParam;
//        operatorSpeedCalculated = Distributions.calculateDist(Distributions.distNormal, normalParam, 
//                Distributions.calculateDist(Distributions.distUniform, uniformParam1, uniformParam2));
        return operatorSpeedCalculated;
    }

    public double getNormalParamLoad() {
        return normalParamLoad;
    }

    public void setNormalParamLoad(double normalParamLoad) {
        this.normalParamLoad = normalParamLoad;
    }

    public double getNormalParamUnload() {
        return normalParamUnload;
    }

    public void setNormalParamUnload(double normalParamUnload) {
        this.normalParamUnload = normalParamUnload;
    }

    public double getUniformParam1() {
        return uniformParam1;
    }

    public void setUniformParam1(double uniformParam1) {
        this.uniformParam1 = uniformParam1;
    }

    public double getUniformParam2() {
        return uniformParam2;
    }

    public void setUniformParam2(double uniformParam2) {
        this.uniformParam2 = uniformParam2;
    }

    public String getActivityDescriptionAssigned() {
        return activityDescriptionAssigned;
    }

    public void setActivityDescriptionAssigned(String activityDescriptionAssigned) {
        this.activityDescriptionAssigned = activityDescriptionAssigned;
    }

    public Map<Integer, E_Activity> getArrActivitiesAssigned() {
        return arrActivitiesAssigned;
    }

    public void setArrActivitiesAssigned(Map<Integer, E_Activity> arrActivitiesAssigned) {
        this.arrActivitiesAssigned = arrActivitiesAssigned;
    }

    public double getPercentageUsage() {
        double activeMin = 0;
        double activeWorkMin = 0;
        if (initialGame != null){
            if (state.equals(ObjectState.Active))
                activeMin = (double)initialGame.getDifferenceTimes_Minutes(currentGame);
            else
                activeMin = (double)initialGame.getDifferenceTimes_Minutes(finalGame);
        }
        if (initialBusyGame != null){
            activeWorkMin = (double)initialBusyGame.getDifferenceTimes_Minutes(currentGame);
        }
        double resultUsage = (accumulatedWorkingTime + activeWorkMin)/activeMin;//(accumulatedActiveTime + activeMin);
        return (resultUsage > 1 ? 1 : resultUsage);
    }
    
    private void setInitialTimeWorkingOperator(){
        initialBusyGame = new E_Game(); 
        initialBusyGame.setCurrentMinute(currentGame.getCurrentMinute());
        initialBusyGame.setCurrentHour(currentGame.getCurrentHour());
        initialBusyGame.setCurrentDay(currentGame.getCurrentDay());
        initialBusyGame.setCurrentMonth(currentGame.getCurrentMonth());
    }
    
    private void setInitialTime(){
        if (initialGame != null && finalGame != null){
            double activeMin = (double)initialGame.getDifferenceTimes_Minutes(finalGame);
            earnedMoney += Utils.formatValue2Dec((activeMin/60)*salaryPerHour);
            accumulatedActiveTime += activeMin;
        }
        initialGame = new E_Game(); 
        initialGame.setCurrentMinute(currentGame.getCurrentMinute());
        initialGame.setCurrentHour(currentGame.getCurrentHour());
        initialGame.setCurrentDay(currentGame.getCurrentDay());
        initialGame.setCurrentMonth(currentGame.getCurrentMonth());
    }
    
    private void setFinalTime(){
        finalGame = new E_Game();
        finalGame.setCurrentMinute(currentGame.getCurrentMinute());
        finalGame.setCurrentHour(currentGame.getCurrentHour());
        finalGame.setCurrentDay(currentGame.getCurrentDay());
        finalGame.setCurrentMonth(currentGame.getCurrentMonth());
    }
    
    public double updateAndGetEarnedMoney(){
        double activeMin = 0;
        if (initialGame == null) return earnedMoney;
        if (state.equals(ObjectState.Active))
            activeMin = (double)initialGame.getDifferenceTimes_Minutes(currentGame);
        else
            activeMin = (double)initialGame.getDifferenceTimes_Minutes(finalGame);
        return earnedMoney + Utils.formatValue2Dec((activeMin/60)*salaryPerHour);
    }
    
    public ObjectState getState() {
        return state;
    }

    public void setState(ObjectState state) {
        this.state = state;
        if (state.equals(ObjectState.Active)){
            accumulatedActiveTime = 0;
            accumulatedWorkingTime = 0;
            showHideInactiveOperator(false);
            setInitialTime();
        }else{
            setFinalTime();
            moveToStaffZone();
            showHideInactiveOperator(true);
        }
            
    }
    
    public void updateOperatorCategory(){
        boolean isCarrier = false;
        boolean isAssembler = false;
        for (Pair<Integer,Double> tempSkill : getArrSkills()){
            if (tempSkill.getFirst() == Params.skillCarrier)
                isCarrier = true;
            else
            if (tempSkill.getFirst() == Params.skillAssembler)
                isAssembler = true;
        }
        if (isCarrier && isAssembler){
            category = OperatorCategory.Versatile;
            index3 = (index3 + 1)%operatorVersatile.length;
            modelCharacter.setMaterial(assetManager.loadMaterial(operatorVersatile[index3]));
            System.out.println("Entered first method. operatorCarrier[" + index3 + "] = " + operatorVersatile[index3]);
            salaryPerHour = salaryPerHourVersatile;
        }else
        if (isCarrier){
            category = OperatorCategory.Carrier;
            index2 = (index2 + 1)%operatorCarrier.length;
            modelCharacter.setMaterial(assetManager.loadMaterial(operatorCarrier[index2]));
            System.out.println("Entered first method. operatorCarrier[" + index2+ "] = " + operatorCarrier[index2]);
            salaryPerHour = salaryPerHourCarrier;
        }else
        if (isAssembler){
            category = OperatorCategory.Assembler;
            index1 = (index1  + 1)%operatorAssembler.length;
            modelCharacter.setMaterial(assetManager.loadMaterial(operatorAssembler[index1]));
            System.out.println("Entered first method. operatorAssembler[" + index1 + "] = " + operatorAssembler[index1]);
            salaryPerHour = salaryPerHourAssembler;
        }
    }

    public OperatorCategory getCategory() {
        return category;
    }

    public void setCategory(OperatorCategory category) {
        this.category = category;
    }
    
    private void moveToStaffZone(){
        if (isLoadingInitialOperatorData) return;
        E_Station station = gameEngine.getGameData().getUserStation_StaffZone();
        if (station == null) return;
        Pair<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>,Pair<Integer, Integer>> operatorLocation = null;        
        operatorLocation = station.getLocationInMatrix(Params.standardOperatorWidthLength, Params.standardOperatorWidthLength);
        if (operatorLocation != null){
            setStatus(Status.Busy);
            tempVirtualMatrixI = operatorLocation.getFirst().getFirst().getFirst();
            tempVirtualMatrixJ = operatorLocation.getFirst().getFirst().getSecond();
            tempVirtualIdLocation = TypeElements.STATION + String.valueOf(station.getIdStation());
            gameEngine.operatorWalksToStaffZone(this, operatorLocation.getSecond().getFirst(), operatorLocation.getSecond().getSecond());
            virtualIdLocation = Status.Walking.toString();
        }
    }
    
    public void updateInactiveBrokenOperator(){
        /*
        Sphere sphere = new Sphere(16, 16, (float)Params.standardPartWidthLength/2);
        //boxForMachine = new Box(Vector3f.ZERO, (float)Params.standardPartWidthLength*2, (float)Params.standardPartWidthLength*2, (float)Params.standardPartWidthLength*2);
        inactiveOperator = new Geometry(TypeElements.OPERATOR + String.valueOf(getIdOperator()) + "_INACTIVE",sphere);
        materialForOperator = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //materialForMachine.setTexture("ColorMap", assetManager.loadTexture("Interface/inactive.png"));
        materialForOperator.setColor("Color", ColorRGBA.Red);
        inactiveOperator.setMaterial(materialForOperator);
        rootNode.attachChild(inactiveOperator);
        inactiveOperator.setLocalTranslation(new Vector3f(0, -(float)Params.standardPartWidthLength*4, 0));
        */
    }
    
    public void showHideInactiveOperator(boolean showItems){
        if (inactiveOperator != null){
            if (showItems){
                getModelCharacter().attachChild(inactiveOperator);
                getModelCharacter().getChild(inactiveOperator.getName()).setLocalTranslation(new Vector3f(0, 8f, 0));
            }else{
                inactiveOperator.removeFromParent();
            }
        }        
    }
    
    public void showHideSpot(boolean showSpot){
        if (showSpot){
            gameEngine.getShowSpotObject().setMesh(sphereSpot);
            getModelCharacter().attachChild(gameEngine.getShowSpotObject());
            getModelCharacter().getChild(gameEngine.getShowSpotObject().getName()).setLocalTranslation(new Vector3f(0, 8f, 0));
        }else{
            gameEngine.getShowSpotObject().removeFromParent();
        }
    }

    public double getSalaryPerHourAssembler() {
        return salaryPerHourAssembler;
    }

    public void setSalaryPerHourAssembler(double salaryPerHourAssembler) {
        this.salaryPerHourAssembler = salaryPerHourAssembler;
    }

    public double getSalaryPerHourCarrier() {
        return salaryPerHourCarrier;
    }

    public void setSalaryPerHourCarrier(double salaryPerHourCarrier) {
        this.salaryPerHourCarrier = salaryPerHourCarrier;
    }

    public double getSalaryPerHourVersatile() {
        return salaryPerHourVersatile;
    }

    public void setSalaryPerHourVersatile(double salaryPerHourVersatile) {
        this.salaryPerHourVersatile = salaryPerHourVersatile;
    }
    
    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }
    
    private void releaseStaffZone(){
        E_Station station = gameEngine.getGameData().getUserStation_StaffZone();
        station.releaseLocation(virtualMatrixI, virtualMatrixJ, virtualMatrixI, virtualMatrixJ);
    }

    public String getVirtualIdLocation_StaffZone() {
        return virtualIdLocation_StaffZone;
    }

    public void setVirtualIdLocation_StaffZone(String virtualIdLocation_StaffZone) {
        this.virtualIdLocation_StaffZone = virtualIdLocation_StaffZone;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public E_Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(E_Game currentGame) {
        this.currentGame = currentGame;
    }

    public int getIdStrategyAssigned() {
        return idStrategyAssigned;
    }

    public void setIdStrategyAssigned(int idStrategyAssigned) {
        this.idStrategyAssigned = idStrategyAssigned;
    }

//    public boolean isIsExecutingActivity() {
//        return isExecutingActivity;
//    }
//
//    public void setIsExecutingActivity(boolean isExecutingActivity) {
//        this.isExecutingActivity = isExecutingActivity;
//    }

    public TypeActivity getActivityDoing() {
        return activityDoing;
    }

    public void setActivityDoing(TypeActivity activityDoing) {
        this.activityDoing = activityDoing;
    }

    public boolean isIsMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public int getIdMachineAttached() {
        return idMachineAttached;
    }

    public void setIdMachineAttached(int idMachineAttached) {
        this.idMachineAttached = idMachineAttached;
    }

    public int getEndLocationX() {
        return endLocationX;
    }

    public void setEndLocationX(int endLocationX) {
        this.endLocationX = endLocationX;
    }

    public int getEndLocationZ() {
        return endLocationZ;
    }

    public void setEndLocationZ(int endLocationZ) {
        this.endLocationZ = endLocationZ;
    }

    //END PARAMS FOR ANIMATION
    public void updateTempToCurrentVirtualPosition(){
        this.virtualIdLocation = tempVirtualIdLocation;
        this.virtualMatrixI = tempVirtualMatrixI;
        this.virtualMatrixJ = tempVirtualMatrixJ;
    }
    
    public void updateVirtualPosition(String vIdLocation, int matrixI, int matrixJ)
    {
        if (virtualIdLocation.equals(virtualIdLocation_StaffZone) && !vIdLocation.equals(virtualIdLocation_StaffZone)){
            releaseStaffZone();
        }
        this.virtualIdLocation = vIdLocation;
        this.virtualMatrixI = matrixI;
        this.virtualMatrixJ = matrixJ;
    }

    public void setInitialPosition(int iniX, int iniZ)
    {
        this.currentLocationX = iniX;
        this.currentLocationZ = iniZ;
        this.character.setPhysicsLocation(new Vector3f(iniX, Params.standardLocationY, iniZ));
    }

    public MotionTrack getMotionControl() {
        return motionControl;
    }

    public void setMotionControl(MotionTrack motionControl) {
        this.motionControl = motionControl;
    }
    
    public void updateMotionControl(float distance, float speed)
    {
        motionControl = new MotionTrack(modelCharacter,motionPath);
        motionControl.setDirectionType(MotionTrack.Direction.Path);
        motionControl.setInitialDuration(distance);//distance, calculated by A* pathfinding
        motionControl.setSpeed(speed);//speed, in seconds
    }
    
    public void playStopAnimation(boolean play)
    {
        if (play){
            switch (activityDoing){
                case Transport:
                case Store:
                    if (!"Walk".equals(animationChannel.getAnimationName())) {
                            animationChannel.setAnim("Walk",0);
                            animationChannel.setLoopMode(LoopMode.Loop);
                            animationChannel.setSpeed(gameEngine.getLayerScreenController().getTimeFactorForSpeed());
                        }
                    break;
                case Operation:
                    if (!"Dodge".equals(animationChannel.getAnimationName())) {
                        getCharacter().setViewDirection(new Vector3f(-32, 0, 0)); // face machine
                        animationChannel.setAnim("Dodge",0);
                        animationChannel.setLoopMode(LoopMode.Loop);
                        animationChannel.setSpeed(gameEngine.getLayerScreenController().getTimeFactorForSpeed());
                    }
                    break;                
            }
        }else{
            if (!"stand".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("stand");
            }
        }
    }

    public double getTempSpeed() {
        return tempSpeed;
    }

    public void setTempSpeed(double tempSpeed) {
        this.tempSpeed = tempSpeed;
    }
    
    public void updatePhysicsLocation()
    {
        getCharacter().setPhysicsLocation(physicsLocation);
    }
    
    public MotionPath getMotionPath() {
        return motionPath;
    }

    public void setMotionPath(MotionPath motionPath) {
        this.motionPath = motionPath;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public int getCurrentLocationX() {
        return currentLocationX;
    }

    public void setCurrentLocationX(int currentLocationX) {
        this.currentLocationX = currentLocationX;
    }

    public int getCurrentLocationZ() {
        return currentLocationZ;
    }

    public void setCurrentLocationZ(int currentLocationZ) {
        this.currentLocationZ = currentLocationZ;
    }

    public String getVirtualIdLocation() {
        return virtualIdLocation;
    }

    public void setVirtualIdLocation(String virtualIdLocation) {
        this.virtualIdLocation = virtualIdLocation;
    }

    public int getVirtualMatrixI() {
        return virtualMatrixI;
    }

    public void setVirtualMatrixI(int virtualMatrixI) {
        this.virtualMatrixI = virtualMatrixI;
    }

    public int getVirtualMatrixJ() {
        return virtualMatrixJ;
    }

    public void setVirtualMatrixJ(int virtualMatrixJ) {
        this.virtualMatrixJ = virtualMatrixJ;
    }
    
    public Vector3f getVectorFrom() {
        return vectorFrom;
    }

    public void setVectorFrom(Vector3f vectorFrom) {
        this.vectorFrom = vectorFrom;
    }

    public Vector3f getVectorTo() {
        return vectorTo;
    }

    public void setVectorTo(Vector3f vectorTo) {
        this.vectorTo = vectorTo;
    }

    public Vector3f getPhysicsLocation() {
        return physicsLocation;
    }

    public void setPhysicsLocation(Vector3f physicsLocation) {
        this.physicsLocation = physicsLocation;
    }
    
    public AnimChannel getAnimationChannel() {
        return animationChannel;
    }

    public void setAnimationChannel(AnimChannel animationChannel) {
        this.animationChannel = animationChannel;
    }

    public Geometry getBaseCharacter() {
        return baseCharacter;
    }

    public void setBaseCharacter(Geometry baseCharacter) {
        this.baseCharacter = baseCharacter;
    }

    public CharacterControl getCharacter() {
        return character;
    }

    public void setCharacter(CharacterControl character) {
        this.character = character;
    }

    public Node getModelCharacter() {
        return modelCharacter;
    }

    public void setModelCharacter(Node modelCharacter) {
        this.modelCharacter = modelCharacter;
    }

    public ArrayList<Pair<Integer, Double>> getArrSkills() {
        return arrSkills;
    }

    public void setArrSkills(ArrayList<Pair<Integer, Double>> arrSkills) {
        this.arrSkills = arrSkills;
    }

//    public ArrayList<Double> getArrEfficiency() {
//        return arrEfficiency;
//    }
//
//    public void setArrEfficiency(ArrayList<Double> arrEfficiency) {
//        this.arrEfficiency = arrEfficiency;
//    }
//
//    public ArrayList<Integer> getArrSkill() {
//        return arrSkill;
//    }
//
//    public void setArrSkill(ArrayList<Integer> arrSkill) {
//        this.arrSkill = arrSkill;
//    }
    
    public E_Operator(int idOperator, String nameOperator, double salaryPerHour, Status status, Owner owner) {
        this.idOperator = idOperator;
        this.nameOperator = nameOperator;
        this.salaryPerHour = salaryPerHour;
        this.status = status;
        this.owner = owner;
    }

    public E_Operator() {
        operatorAssembler[0] = "Models/Operator/operatorAssembler.j3m";
        for (int i = 1; i < operatorAssembler.length; i++)
            operatorAssembler[i] = "Models/Operator/operatorAssembler" + i + ".j3m";
        
        operatorCarrier[0] = "Models/Operator/operatorCarrier.j3m";
        for (int i = 1; i < operatorCarrier.length; i++)
            operatorCarrier[i] = "Models/Operator/operatorCarrier" + i + ".j3m";
        
        operatorVersatile[0] = "Models/Operator/operatorVersatile.j3m";
        for (int i = 1; i < operatorVersatile.length; i++)
            operatorVersatile[i] = "Models/Operator/operatorVersatile" + i + ".j3m";
        
        isLoadingInitialOperatorData = true;
        sphereSpot = new Sphere(16, 16, (float)Params.standardPartWidthLength/2);
        arrActivitiesAssigned = new HashMap<Integer, E_Activity>();
    }

    public int getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(int idOperator) {
        this.idOperator = idOperator;
    }

    public String getNameOperator() {
        return nameOperator;
    }

    public void setNameOperator(String nameOperator) {
        this.nameOperator = nameOperator;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHourNew) {
        if (this.salaryPerHour != salaryPerHourNew){
            setFinalTime();
            setInitialTime();
        }
        this.salaryPerHour = salaryPerHourNew;
    }

    public double getPriceForFire() {
        return priceForFire;
    }

    public void setPriceForFire(double priceForFire) {
        this.priceForFire = priceForFire;
    }

    public double getPriceForHire() {
        return priceForHire;
    }

    public void setPriceForHire(double priceForHire) {
        this.priceForHire = priceForHire;
    }

    public double getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(double earnedMoney) {
        this.earnedMoney = earnedMoney;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        if (status.equals(Status.Idle) && category.equals(OperatorCategory.Carrier)){
            if (gameEngine.getGameData().getMapUserMachinePendingToMoveToMachineZone().size() > 0){
                for (E_Machine tempMachine : gameEngine.getGameData().getMapUserMachinePendingToMoveToMachineZone().values()){
                    tempMachine.moveToMachineZone();
                    gameEngine.getGameData().getMapUserMachinePendingToMoveToMachineZone().remove(tempMachine.getIdMachine());
                    break;
                }
            }
        }
        if (status.equals(Status.Busy)){
            setInitialTimeWorkingOperator();
        }else
        if (status.equals(Status.Idle) && initialBusyGame != null){
            accumulatedWorkingTime += (double)initialBusyGame.getDifferenceTimes_Minutes(currentGame);
            initialBusyGame = null;
        }
        if (activateLaterDeactivation && status == Status.Idle){
            activateLaterDeactivation = false;
            setState(ObjectState.Inactive);
//            gameEngine.getLayerScreenController().updateOptionButtonClicked();    //update buttons
        }
        if (status.equals(Status.Idle))
            activityDescriptionAssigned = "";
    }
    
    public void updateSkillsPerCategory(){
        if (categoryAssembler == null)
            categoryAssembler = getSkillSelected(Params.skillAssembler);
        if (categoryCarrier == null)
            categoryCarrier = getSkillSelected(Params.skillCarrier);
        if (category.equals(OperatorCategory.Assembler)){
            setSalaryPerHour(salaryPerHourAssembler);
            index1 = (index1 + 1) % operatorAssembler.length;
            modelCharacter.setMaterial(assetManager.loadMaterial(operatorAssembler[index1]));
            System.out.println("Entered second method. operatorAssembler[" + index1 + "] = " + operatorAssembler[index1]);
            if (getSkillSelected(Params.skillAssembler) == null){
                if (categoryAssembler != null)
                    arrSkills.add(categoryAssembler);
                else{
                    categoryAssembler = new Pair(Params.skillAssembler,categoryCarrier.getSecond());
                    arrSkills.add(categoryAssembler);
                }                    
            }
            removeSkillSelected(Params.skillCarrier);
        }else
        if (category.equals(OperatorCategory.Carrier)){
            setSalaryPerHour(salaryPerHourCarrier);
            modelCharacter.setMaterial(assetManager.loadMaterial("Models/Operator/operatorCarrier.j3m"));
            if (getSkillSelected(Params.skillCarrier) == null){
                if (categoryCarrier != null)
                    arrSkills.add(categoryCarrier);
                else{
                    categoryCarrier = new Pair(Params.skillCarrier,categoryAssembler.getSecond());
                    arrSkills.add(categoryCarrier);
                }                    
            }
            removeSkillSelected(Params.skillAssembler); 
        }else
        if (category.equals(OperatorCategory.Versatile)){
            setSalaryPerHour(salaryPerHourVersatile);
            modelCharacter.setMaterial(assetManager.loadMaterial("Models/Operator/operatorVersatile.j3m"));
            if (getSkillSelected(Params.skillAssembler) == null){
                if (categoryAssembler != null)
                    arrSkills.add(categoryAssembler);
                else{
                    categoryAssembler = new Pair(Params.skillAssembler,categoryCarrier.getSecond());
                    arrSkills.add(categoryAssembler);
                }                    
            }
            if (getSkillSelected(Params.skillCarrier) == null){
                if (categoryCarrier != null)
                    arrSkills.add(categoryCarrier);
                else{
                    categoryCarrier = new Pair(Params.skillCarrier,categoryAssembler.getSecond());
                    arrSkills.add(categoryCarrier);
                }                    
            }
        }
//        gameEngine.getLayerScreenController().updateOptionButtonClicked();    //update buttons
    }
    
    private Pair<Integer,Double> getSkillSelected(int idskillRequired){
        for (Pair<Integer,Double> temp : arrSkills){
            if (temp.getFirst() == idskillRequired)
                return temp;
        }
        return null;
    }
    
    private void removeSkillSelected(int idskillRequired){
        for (Pair<Integer,Double> temp : arrSkills){
            if (temp.getFirst() == idskillRequired){
                arrSkills.remove(temp);
                return;
            }                
        }
    }
}
