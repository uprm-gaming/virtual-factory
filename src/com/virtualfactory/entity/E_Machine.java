package com.virtualfactory.entity;

import com.jme3.animation.AnimChannel;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionTrack;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.screen.layer.components.GameLogScreenController;
import com.virtualfactory.threads.MachineAnimation;
import com.virtualfactory.utils.Colors;
import com.virtualfactory.utils.Distributions;
import com.virtualfactory.utils.MachineCategory;
import com.virtualfactory.utils.MessageType;
import com.virtualfactory.utils.Messages;
import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Sounds;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeElements;
import com.virtualfactory.utils.Utils;

/**
 *
 * @author David
 */
public class E_Machine{
    private int idMachine;
    private String machineDescription;
    private double speed;
    private double weightCapacity;
    private double volumeCapacity;
    private String pickUpTimeDistn;
    private double pickUpTimeParameter1;
    private double pickUpTimeParameter2;
    private double pickUpTimeCalculated;
    private String machineTimeDistn;
    private double machineTimeParameter1;
    private double machineTimeParameter2;
    private double machineTimeCalculated;
    private String placementTimeDistn;
    private double placementTimeParameter1;
    private double placementTimeParameter2;
    private double placementTimeCalculated;
    private String timeBetweenFailuresDistn;
    private double timeBetweenFailuresParameter1;
    private double timeBetweenFailuresParameter2;
    private double timeBetweenFailuresCalculated;
    private String repairTimeDistn;
    private double repairTimeParameter1;
    private double repairTimeParameter2;
    private double repairTimeCalculated;
    private double priceForPurchase;
    private double percentageDepreciation;
    private double percentageAccumulatedDepreciation=0;
    private double priceForSell;
    private double tempPriceForSell = 0;
    private double pricePreventiveMaintenance = 0;
    private Status status;
    private Owner owner;
    private String machineDesign;
    private String machineMaterial;
    private double currentWorkingTime=0;
    private boolean isAlreadyBroken = false;
    private MachineCategory machineCategory;
    private ObjectState state;
    private int defaultValue = 0;
    public boolean activateLaterDeactivation = false;
    private double costPerHour;
    private double totalCost = 0;
    private E_Game initialGame = null;
    private E_Game finalGame = null;
    private E_Game currentGame = null;
    private E_Game initialDepreciationGame = null;
    private E_Game initialMachineBroken = null;
    private E_Game initialMachineUsage = null;
    private double accumulatedTimeMachineBroken = 0;
    private double accumulatedTimeMachineUsage = 0;
    private int accumulatedPartsProduced = 0;
    private int partsToProduce = 0;
    private boolean startProduction = false;
    private boolean initializing = false;
    private String virtualIdLocation_MachineZone = "";
    //display machine
    private String virtualIdLocation;
    private String tempVirtualIdLocation;
    private boolean movingToMachineZone = false;
    private int tempVirtualMatrixIniI;
    private int tempVirtualMatrixIniJ;
    private int tempVirtualMatrixEndI;
    private int tempVirtualMatrixEndJ;
    private int tempFinalLocationX;
    private int tempFinalLocationZ;
    private int virtualMatrixIniI;
    private int virtualMatrixIniJ;
    private int virtualMatrixEndI;
    private int virtualMatrixEndJ;
    private int currentLocationX;
    private int currentLocationZ;
    private int sizeW;
    private int sizeL;
    private AnimChannel animationChannel;
    private Node modelCharacter;
    private MotionPath motionPath;
    private MotionTrack motionControl;
    private Geometry itemsForMachine;
    private Geometry inactiveMachine;
    private Geometry brokenMachine;
    private Box boxForMachine;
    private Material materialForMachine;
    private AssetManager assetManager;
    private Node rootNode;
    private BulletAppState bulletAppState;
    private Vector3f physicsLocation = null;
    private MachineAnimation machineAnimation = null;
    private GameEngine gameEngine = null;
    private boolean machineIsAdding = false;
    private boolean machineIsRemoving = false;
    private String activityDescriptionAssigned = "";
    private double factor_calculated;
    private double factor_param1;
    private double factor_param2;
    private Sphere sphereSpot;

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public double getFactor_calculated() {
        factor_calculated = Distributions.calculateDist(Distributions.distUniform, factor_param1, factor_param2);
        return factor_calculated;
    }

    public double getFactor_param1() {
        return factor_param1;
    }

    public void setFactor_param1(double factor_param1) {
        this.factor_param1 = factor_param1;
    }

    public double getFactor_param2() {
        return factor_param2;
    }

    public void setFactor_param2(double factor_param2) {
        this.factor_param2 = factor_param2;
    }
        
    //station PARKING ZONE
    private E_Station stationParkingZone = null;
    private Pair<Integer,Integer> parkingZoneLocation = null;

    public String getActivityDescriptionAssigned() {
        return activityDescriptionAssigned;
    }

    public void setActivityDescriptionAssigned(String activityDescriptionAssigned) {
        this.activityDescriptionAssigned = activityDescriptionAssigned;
    }
    
    public void assignMachineInParkingZone(E_Station station, Pair<Integer,Integer> location){
        this.stationParkingZone = station;
        this.parkingZoneLocation = location;
    }
    
    private void releaseMachineInParkingZone(){
        this.stationParkingZone.releaseParkingZone(this.parkingZoneLocation.getSecond());
    }

    public boolean isMovingToMachineZone() {
        return movingToMachineZone;
    }

    public void setMovingToMachineZone(boolean movingToMachineZone) {
        this.movingToMachineZone = movingToMachineZone;
    }

    public String getVirtualIdLocation_MachineZone() {
        return virtualIdLocation_MachineZone;
    }

    public void setVirtualIdLocation_MachineZone(String virtualIdLocation_MachineZone) {
        this.virtualIdLocation_MachineZone = virtualIdLocation_MachineZone;
    }
    
    public void updateVirtualPosition(String vIdLocation, int matrixIniI, int matrixIniJ, int matrixEndI, int matrixEndJ)
    {
        if (virtualIdLocation.equals(virtualIdLocation_MachineZone) && !vIdLocation.equals(virtualIdLocation_MachineZone)){
            releaseMachineZone();
        }
        this.virtualIdLocation = vIdLocation;
        this.virtualMatrixIniI = matrixIniI;
        this.virtualMatrixIniJ = matrixIniJ;
        this.virtualMatrixEndI = matrixEndI;
        this.virtualMatrixEndJ = matrixEndJ;
        
        if (vIdLocation.contains(Status.Walking.toString()) && stationParkingZone != null && parkingZoneLocation != null){
            releaseMachineInParkingZone();
//            System.out.println("FREE Machine:" + idMachine + " - StationParkingZone:" + stationParkingZone.getStationDescription() + " - ParkingZoneLocation:" + parkingZoneLocation.getSecond());
//        }else{
//            System.out.println("BUSY Machine:" + idMachine + " - StationParkingZone:" + stationParkingZone + " - ParkingZoneLocation:" + parkingZoneLocation);
        }
    }
    
    private void releaseMachineZone(){
        E_Station station = gameEngine.getGameData().getUserStation_MachineZone();
        station.releaseLocation(virtualMatrixIniI, virtualMatrixIniJ, virtualMatrixEndI, virtualMatrixEndJ);
    }
    
    public void updatePhysicsLocation()
    {
        getModelCharacter().setLocalTranslation(physicsLocation.getX(), 0.5f, physicsLocation.getZ());
        currentLocationX = (int)physicsLocation.getX();
        currentLocationZ = (int)physicsLocation.getZ();
    }
    
    public void updateInactiveBrokenMachine(){
        /*
        Sphere sphere = new Sphere(16, 16, (float)Params.standardPartWidthLength*3);
        //boxForMachine = new Box(Vector3f.ZERO, (float)Params.standardPartWidthLength*2, (float)Params.standardPartWidthLength*2, (float)Params.standardPartWidthLength*2);
        inactiveMachine = new Geometry(TypeElements.MACHINE + String.valueOf(getIdMachine()) + "_INACTIVE",sphere);
        materialForMachine = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //materialForMachine.setTexture("ColorMap", assetManager.loadTexture("Interface/inactive.png"));
        materialForMachine.setColor("Color", ColorRGBA.Red);
        inactiveMachine.setMaterial(materialForMachine);
        rootNode.attachChild(inactiveMachine);
        inactiveMachine.setLocalTranslation(new Vector3f(0, -(float)Params.standardPartWidthLength*4, 0));
        
        //boxForMachine = new Box(Vector3f.ZERO, (float)Params.standardPartWidthLength*2, (float)Params.standardPartWidthLength*2, (float)Params.standardPartWidthLength*2);
        brokenMachine = new Geometry(TypeElements.MACHINE + String.valueOf(getIdMachine()) + "_BROKEN",sphere);
        materialForMachine = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //materialForMachine.setTexture("ColorMap", assetManager.loadTexture("Interface/broken.png"));
        materialForMachine.setColor("Color", ColorRGBA.Yellow);
        brokenMachine.setMaterial(materialForMachine);
        rootNode.attachChild(brokenMachine);
        brokenMachine.setLocalTranslation(new Vector3f(0, -(float)Params.standardPartWidthLength*4, 0));
        */
    }
    
    public void updateItemsForMachine(int idPart, String colorPart, String texturePart){
        itemsForMachine = (Geometry)rootNode.getChild(TypeElements.MACHINE + String.valueOf(getIdMachine()) + "_" + TypeElements.PART + idPart);
        if (itemsForMachine == null){
            boxForMachine = new Box(Vector3f.ZERO, (float)Params.standardPartWidthLength*2, 0f, (float)Params.standardPartWidthLength*2);
            itemsForMachine = new Geometry(TypeElements.MACHINE + String.valueOf(getIdMachine()) + "_" + TypeElements.PART + idPart, boxForMachine);
            materialForMachine = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            if (colorPart.length() > 0) materialForMachine.setColor("Color", Colors.getColorRGBA(colorPart));
            if (texturePart.length() > 0) materialForMachine.setTexture("ColorMap", assetManager.loadTexture(texturePart));
            itemsForMachine.setMaterial(materialForMachine);
            rootNode.attachChild(itemsForMachine);
            itemsForMachine.setLocalTranslation(new Vector3f(0, -(float)Params.standardPartWidthLength*4, 0));
        }
//        System.out.println("UPDATE ITEMS FOR MACHINE");
    }
    
    public void showHideItemsInMachine(boolean showItems){
        if (showItems){
            getModelCharacter().attachChild(itemsForMachine);
            getModelCharacter().getChild(itemsForMachine.getName()).setLocalTranslation(new Vector3f(0, 6f, 0));
        }else{
            itemsForMachine.removeFromParent();
        }
    }
    
    public void showHideInactiveMachine(boolean showItems){
        if (inactiveMachine != null){
            if (showItems){
                getModelCharacter().attachChild(inactiveMachine);
                getModelCharacter().getChild(inactiveMachine.getName()).setLocalTranslation(new Vector3f(0, 60f, 0));
            }else{
                inactiveMachine.removeFromParent();
            }
        }        
    }
    
    public void showHideBrokenMachine(boolean showItems){
        if (brokenMachine != null){
            if (showItems){
                getModelCharacter().attachChild(brokenMachine);
                getModelCharacter().getChild(brokenMachine.getName()).setLocalTranslation(new Vector3f(0, 60f, 0));
            }else{
                brokenMachine.removeFromParent();
            }
        }        
    }
    
    public void showHideSpot(boolean showSpot){
        if (showSpot){
            gameEngine.getShowSpotObject().setMesh(sphereSpot);
            getModelCharacter().attachChild(gameEngine.getShowSpotObject());
            getModelCharacter().getChild(gameEngine.getShowSpotObject().getName()).setLocalTranslation(new Vector3f(0, 60f, 0));
        }else{
            gameEngine.getShowSpotObject().removeFromParent();
        }
    }
    
    public void addItemsDynamically(boolean start, int itemsQuantity, double animationTime){
        if (start){
            double tempAnimationTime;
            int tempQuantity;
            if (itemsQuantity == 0 && animationTime == 0){
                tempAnimationTime = machineAnimation.getTimeToFinish();
                tempQuantity = machineAnimation.getQuantity();
            }else{
                tempAnimationTime = animationTime;
                tempQuantity = itemsQuantity;
            }
            machineAnimation = new MachineAnimation();
            machineAnimation.setGameEngine(gameEngine);
            machineAnimation.setBoxForMachine(boxForMachine);
            machineAnimation.setTimeToFinish(tempAnimationTime);
            machineAnimation.setAddItems(true);
            machineAnimation.setIsZeroItems(false);
            machineAnimation.setQuantity(tempQuantity);
            machineAnimation.setMoveItemsDynamically(true);
            machineAnimation.start();
            machineIsAdding = true;
        }else{
            machineAnimation.setMoveItemsDynamically(false);
            machineIsAdding = false;
        }
    }
    
    public void removeItemsDynamically(boolean start, int itemsQuantity, double animationTime){
        if (start){
            double tempAnimationTime;
            int tempQuantity;
            if (itemsQuantity == 0 && animationTime == 0){
                tempAnimationTime = machineAnimation.getTimeToFinish();
                tempQuantity = machineAnimation.getQuantity();
            }else{
                tempAnimationTime = animationTime;
                tempQuantity = itemsQuantity;
            }
            machineAnimation = new MachineAnimation();
            machineAnimation.setGameEngine(gameEngine);
            machineAnimation.setBoxForMachine(boxForMachine);
            machineAnimation.setTimeToFinish(tempAnimationTime);
            machineAnimation.setAddItems(false);
            machineAnimation.setIsZeroItems(true);
            machineAnimation.setQuantity(tempQuantity);
            machineAnimation.setMoveItemsDynamically(true);            
            machineAnimation.start();
            machineIsRemoving = true;
        }else{
            machineAnimation.setMoveItemsDynamically(false);
            machineIsRemoving = false;
        }
    }

    public boolean isMachineIsAdding() {
        return machineIsAdding;
    }

    public void setMachineIsAdding(boolean machineIsAdding) {
        this.machineIsAdding = machineIsAdding;
    }

    public boolean isMachineIsRemoving() {
        return machineIsRemoving;
    }

    public void setMachineIsRemoving(boolean machineIsRemoving) {
        this.machineIsRemoving = machineIsRemoving;
    }

    public E_Machine(int idMachine, String machineDescription, double speed, double weightCapacity, double volumeCapacity, String pickUpTimeDistn, double pickUpTimeParameter1, double pickUpTimeParameter2, String machineTimeDistn, double machineTimeParameter1, double machineTimeParameter2, String placementTimeDistn, double placementTimeParameter1, double placementTimeParameter2, String timeBetweenFailuresDistn, double timeBetweenFailuresParameter1, double timeBetweenFailuresParameter2, String repairTimeDistn, double repairTimeParameter1, double repairTimeParameter2, double priceForPurchase, Status status, Owner owner, String machineDesign) {
        this.idMachine = idMachine;
        this.machineDescription = machineDescription;
        this.speed = speed;
        this.weightCapacity = weightCapacity;
        this.volumeCapacity = volumeCapacity;
        this.pickUpTimeDistn = pickUpTimeDistn;
        this.pickUpTimeParameter1 = pickUpTimeParameter1;
        this.pickUpTimeParameter2 = pickUpTimeParameter2;
        this.machineTimeDistn = machineTimeDistn;
        this.machineTimeParameter1 = machineTimeParameter1;
        this.machineTimeParameter2 = machineTimeParameter2;
        this.placementTimeDistn = placementTimeDistn;
        this.placementTimeParameter1 = placementTimeParameter1;
        this.placementTimeParameter2 = placementTimeParameter2;
        this.timeBetweenFailuresDistn = timeBetweenFailuresDistn;
        this.timeBetweenFailuresParameter1 = timeBetweenFailuresParameter1;
        this.timeBetweenFailuresParameter2 = timeBetweenFailuresParameter2;
        this.repairTimeDistn = repairTimeDistn;
        this.repairTimeParameter1 = repairTimeParameter1;
        this.repairTimeParameter2 = repairTimeParameter2;
        this.priceForPurchase = priceForPurchase;
        this.status = status;
        this.owner = owner;
        this.machineDesign = machineDesign;
    }

    public int getPartsToProduce() {
        return partsToProduce;
    }

    public void setPartsToProduce(int partsToProduce) {
        this.partsToProduce = partsToProduce;
    }
    
    public void setEndProduction(){
        if (startProduction){
            accumulatedPartsProduced += partsToProduce;
            partsToProduce = 0;
            startProduction = false;
        }
    }
    
    public int getAccumulatedPartsProduced(){
        return accumulatedPartsProduced;
    }
    
    public double getPercentageAvailability(){
        if (initialDepreciationGame == null) return 0;
        double activeMinutes = (double)initialDepreciationGame.getDifferenceTimes_Minutes(currentGame);
        double newBrokenMinutes = 0;
        if (initialMachineBroken != null){
            newBrokenMinutes = (double)initialMachineBroken.getDifferenceTimes_Minutes(currentGame);
        }
        double timeAvailabilityMachine = activeMinutes - accumulatedTimeMachineBroken - newBrokenMinutes;
//        if (idMachine==1) System.out.println("timeAvailability:" + timeAvailabilityMachine + " - active:" + activeMinutes);
        return timeAvailabilityMachine/activeMinutes;
    }
    
    public double getPercentageUsage(){
        if (initialDepreciationGame == null) return 0;
        double activeMinutes = (double)initialDepreciationGame.getDifferenceTimes_Minutes(currentGame);
        double newUsageMinutes = 0;
        if (initialMachineUsage != null){
            newUsageMinutes = (double)initialMachineUsage.getDifferenceTimes_Minutes(currentGame);
        }
//        if (idMachine==1) System.out.println("accumulatedTimeMachineUsage:" + (accumulatedTimeMachineUsage + newUsageMinutes) + " - active:" + activeMinutes);
        return (accumulatedTimeMachineUsage + newUsageMinutes)/activeMinutes;
    }
    
    private void setInitialUsageTime(){
        initialMachineUsage = new E_Game();
        initialMachineUsage.setCurrentMinute(currentGame.getCurrentMinute());
        initialMachineUsage.setCurrentHour(currentGame.getCurrentHour());
        initialMachineUsage.setCurrentDay(currentGame.getCurrentDay());
        initialMachineUsage.setCurrentMonth(currentGame.getCurrentMonth());
    }
    
    private void setFinalUsageTime(){
        if (initialMachineUsage != null){
            accumulatedTimeMachineUsage += (double)initialMachineUsage.getDifferenceTimes_Minutes(currentGame);
            initialMachineUsage = null;
        }
    }
    
    private void setInitialBrokenTime(){
        initialMachineBroken = new E_Game();
        initialMachineBroken.setCurrentMinute(currentGame.getCurrentMinute());
        initialMachineBroken.setCurrentHour(currentGame.getCurrentHour());
        initialMachineBroken.setCurrentDay(currentGame.getCurrentDay());
        initialMachineBroken.setCurrentMonth(currentGame.getCurrentMonth());
    }
    
    private void setFinalBrokenTime(){
        if (initialMachineBroken != null){
            accumulatedTimeMachineBroken += (double)initialMachineBroken.getDifferenceTimes_Minutes(currentGame);
            initialMachineBroken = null;
        }
    }
    
    public double getPercentageAccumulatedDepreciation(){
        if (initialDepreciationGame != null){
            double depreciationMinutes = (double)initialDepreciationGame.getDifferenceTimes_Minutes(currentGame);
            percentageAccumulatedDepreciation = depreciationMinutes*percentageDepreciation/Params.depreciationRateInMinutes;
            priceForSell = priceForPurchase*(1 - percentageAccumulatedDepreciation);
            if (priceForSell < 0) priceForSell = 0;
            if (percentageAccumulatedDepreciation > 1) percentageAccumulatedDepreciation = 1;
        }        
        return percentageAccumulatedDepreciation;
    }
    
    private void setInitialDepreciationTime(){
        initialDepreciationGame = new E_Game();
        initialDepreciationGame.setCurrentMinute(currentGame.getCurrentMinute());
        initialDepreciationGame.setCurrentHour(currentGame.getCurrentHour());
        initialDepreciationGame.setCurrentDay(currentGame.getCurrentDay());
        initialDepreciationGame.setCurrentMonth(currentGame.getCurrentMonth());
    }

    private void setInitialTime(){
//        if (initialGame != null && finalGame != null){
//            double activeMin = (double)initialGame.getDifferenceTimes_Minutes(finalGame);
//            totalCost += Utils.formatValue2Dec((activeMin/60)*costPerHour);
//        }
        initialGame = new E_Game();
        initialGame.setCurrentMinute(currentGame.getCurrentMinute());
        initialGame.setCurrentHour(currentGame.getCurrentHour());
        initialGame.setCurrentDay(currentGame.getCurrentDay());
        initialGame.setCurrentMonth(currentGame.getCurrentMonth());
    }
    
    private void setFinalTime(){
//        finalGame = new E_Game();
//        finalGame.setCurrentMinute(currentGame.getCurrentMinute());
//        finalGame.setCurrentHour(currentGame.getCurrentHour());
//        finalGame.setCurrentDay(currentGame.getCurrentDay());
//        finalGame.setCurrentMonth(currentGame.getCurrentMonth());        
        if (initialGame != null){
            totalCost += Utils.formatValue2Dec(((double)initialGame.getDifferenceTimes_Minutes(currentGame)/60)*costPerHour);
            initialGame = null;
        }
    }
    
    public double getAccumulatedCost(){
        double newCosts = 0;
        if (initialGame != null){
            newCosts = Utils.formatValue2Dec(((double)initialGame.getDifferenceTimes_Minutes(currentGame)/60)*costPerHour);
        }
        return totalCost + newCosts;
    }
    
//    public double updateAndGetTotalCost(){
//        double activeMin = 0;
//        if (initialGame == null) return totalCost;
//        if (state.equals(ObjectState.Active))
//            activeMin = (double)initialGame.getDifferenceTimes_Minutes(currentGame);
//        else
//            activeMin = (double)initialGame.getDifferenceTimes_Minutes(finalGame);
//        return totalCost + Utils.formatValue2Dec((activeMin/60)*costPerHour);
//    }
    
    public ObjectState getMachineState() {
        return state;
    }

    public void setMachineState(ObjectState state) {
        this.state = state;
        if (state.equals(ObjectState.Active)){
            percentageAccumulatedDepreciation = 0;
            accumulatedTimeMachineBroken = 0;
            accumulatedTimeMachineUsage = 0;
            setInitialDepreciationTime();
            showHideInactiveMachine(false);
        }else{
            initialDepreciationGame = null;
            showHideInactiveMachine(true);
            if (initializing) return;
            if (machineCategory.equals(MachineCategory.Transport)){
                if (Integer.parseInt(virtualIdLocation.replace(TypeElements.STATION.toString(), "")) != gameEngine.getGameData().getUserStation_MachineZone().getIdStation())
                    if (!moveToMachineZone())
                        gameEngine.getGameData().getMapUserMachinePendingToMoveToMachineZone().put(idMachine, this);
            }
        }
    }
    
    public boolean moveToMachineZone(){
        movingToMachineZone = true;
        E_Operator tempOperator = gameEngine.getGameData().getNearestCarrierOperatorTo(currentLocationX, currentLocationZ);
        if (tempOperator == null) return false;
        E_Station station = gameEngine.getGameData().getUserStation_MachineZone();
        if (station == null) return false;
        Pair<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>,Pair<Integer, Integer>> machineLocation = null;        
        machineLocation = station.getLocationInMatrix(getSizeW(), getSizeL());
        if (machineLocation != null){
            releaseMachineInParkingZone();
            setStatus(Status.Busy);
            tempOperator.setStatus(Status.Busy);
            tempVirtualMatrixIniI = machineLocation.getFirst().getFirst().getFirst();
            tempVirtualMatrixIniJ = machineLocation.getFirst().getFirst().getSecond();
            tempVirtualMatrixEndI = machineLocation.getFirst().getSecond().getFirst();
            tempVirtualMatrixEndJ = machineLocation.getFirst().getSecond().getSecond();
            tempFinalLocationX = machineLocation.getSecond().getFirst();
            tempFinalLocationZ = machineLocation.getSecond().getSecond();
            tempVirtualIdLocation = TypeElements.STATION + String.valueOf(station.getIdStation());
            if (tempOperator.getCurrentLocationX() == currentLocationX && tempOperator.getCurrentLocationZ() == currentLocationZ){
                double factorMachine = getFactor_calculated();
                double factorOperator = 1;
                double tempOperatorSpeed = tempOperator.getOperatorSpeedCalculated(tempOperator.getNormalParamUnload());
                for (Pair<Integer,Double> temp : tempOperator.getArrSkills()){
                    if (temp.getFirst() == Params.skillMoveId){
                        factorOperator = temp.getSecond();
                        break;
                    }
                }
                tempOperator.setTempSpeed(tempOperatorSpeed / (factorMachine * factorOperator));
                gameEngine.operatorAndMachineMovingToMachineZone(tempOperator, this, tempFinalLocationX, tempFinalLocationZ);
            }else
                gameEngine.operatorWalksToSpecificMachine(tempOperator, this, currentLocationX, currentLocationZ);
//            setCurrentLocationX(machineLocation.getSecond().getFirst());
//            setCurrentLocationZ(machineLocation.getSecond().getSecond());
            virtualIdLocation = Status.Walking.toString();
        }else
            return false;
        return true;
    }
    
    public void updateTempToCurrentVirtualPosition(){
        this.virtualIdLocation = tempVirtualIdLocation;
        this.virtualMatrixIniI = tempVirtualMatrixIniI;
        this.virtualMatrixIniJ = tempVirtualMatrixIniJ;
        this.virtualMatrixEndI = tempVirtualMatrixEndI;
        this.virtualMatrixEndJ = tempVirtualMatrixEndJ;
    }

    public int getTempFinalLocationX() {
        return tempFinalLocationX;
    }

    public void setTempFinalLocationX(int tempFinalLocationX) {
        this.tempFinalLocationX = tempFinalLocationX;
    }

    public int getTempFinalLocationZ() {
        return tempFinalLocationZ;
    }

    public void setTempFinalLocationZ(int tempFinalLocationZ) {
        this.tempFinalLocationZ = tempFinalLocationZ;
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

    public Vector3f getPhysicsLocation() {
        return physicsLocation;
    }

    public void setPhysicsLocation(Vector3f physicsLocation) {
        this.physicsLocation = physicsLocation;
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

    public MotionPath getMotionPath() {
        return motionPath;
    }

    public void setMotionPath(MotionPath motionPath) {
        this.motionPath = motionPath;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }

    public void setBulletAppState(BulletAppState bulletAppState) {
        this.bulletAppState = bulletAppState;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public double getPercentageDepreciation() {
        return percentageDepreciation;
    }

    public void setPercentageDepreciation(double percentageDepreciation) {
        this.percentageDepreciation = percentageDepreciation;
    }
    
    public E_Machine() {
        sphereSpot = new Sphere(16, 16, (float)Params.standardPartWidthLength*3);
        this.initializing = true;
    }

    public void setInitializing(boolean initializing) {
        this.initializing = initializing;
    }

    public int getSizeL() {
        return sizeL;
    }

    public void setSizeL(int sizeL) {
        this.sizeL = sizeL;
    }

    public int getSizeW() {
        return sizeW;
    }

    public void setSizeW(int sizeW) {
        this.sizeW = sizeW;
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

    public int getVirtualMatrixEndI() {
        return virtualMatrixEndI;
    }

    public void setVirtualMatrixEndI(int virtualMatrixEndI) {
        this.virtualMatrixEndI = virtualMatrixEndI;
    }

    public int getVirtualMatrixEndJ() {
        return virtualMatrixEndJ;
    }

    public void setVirtualMatrixEndJ(int virtualMatrixEndJ) {
        this.virtualMatrixEndJ = virtualMatrixEndJ;
    }

    public int getVirtualMatrixIniI() {
        return virtualMatrixIniI;
    }

    public void setVirtualMatrixIniI(int virtualMatrixIniI) {
        this.virtualMatrixIniI = virtualMatrixIniI;
    }

    public int getVirtualMatrixIniJ() {
        return virtualMatrixIniJ;
    }

    public void setVirtualMatrixIniJ(int virtualMatrixIniJ) {
        this.virtualMatrixIniJ = virtualMatrixIniJ;
    }
    
    public void calculateDistributionsTime(){
        pickUpTimeCalculated = Distributions.calculateDist(pickUpTimeDistn, pickUpTimeParameter1, pickUpTimeParameter2);
        machineTimeCalculated = Distributions.calculateDist(machineTimeDistn, machineTimeParameter1, machineTimeParameter2);
        placementTimeCalculated = Distributions.calculateDist(placementTimeDistn, placementTimeParameter1, placementTimeParameter2);
        timeBetweenFailuresCalculated = Distributions.calculateDist(timeBetweenFailuresDistn, timeBetweenFailuresParameter1, timeBetweenFailuresParameter2);
        repairTimeCalculated = Distributions.calculateDist(repairTimeDistn, repairTimeParameter1, repairTimeParameter2);
//        System.out.println("Machine:" + idMachine + " - TimeBetween:" + timeBetweenFailuresCalculated);
    }
    
    public double getCurrentWorkingTime() {
        return currentWorkingTime;
    }

    public void setCurrentWorkingTime(double currentWorkingTime) {
        this.currentWorkingTime = currentWorkingTime;
    }

    public boolean isIsAlreadyBroken() {
        return isAlreadyBroken;
    }

    public void setIsAlreadyBroken(boolean isAlreadyBroken) {
        this.isAlreadyBroken = isAlreadyBroken;
    }
    
    public AnimChannel getAnimationChannel() {
        return animationChannel;
    }

    public void setAnimationChannel(AnimChannel animationChannel) {
        this.animationChannel = animationChannel;
    }

    public Node getModelCharacter() {
        return modelCharacter;
    }

    public void setModelCharacter(Node modelCharacter) {
        this.modelCharacter = modelCharacter;
    }
    
    public double getMachineTimeCalculated() {
        return machineTimeCalculated;
    }

    public void setMachineTimeCalculated(double machineTimeCalculated) {
        this.machineTimeCalculated = machineTimeCalculated;
    }

    public double getPickUpTimeCalculated() {
        return pickUpTimeCalculated;
    }

    public void setPickUpTimeCalculated(double pickUpTimeCalculated) {
        this.pickUpTimeCalculated = pickUpTimeCalculated;
    }

    public double getPlacementTimeCalculated() {
        return placementTimeCalculated;
    }

    public void setPlacementTimeCalculated(double placementTimeCalculated) {
        this.placementTimeCalculated = placementTimeCalculated;
    }
    
    public double getRepairTimeCalculated() {
        return repairTimeCalculated;
    }

    public void setRepairTimeCalculated(double repairTimeCalculated) {
        this.repairTimeCalculated = repairTimeCalculated;
    }

    public double getTimeBetweenFailuresCalculated() {
        return timeBetweenFailuresCalculated;
    }

    public void setTimeBetweenFailuresCalculated(double timeBetweenFailuresCalculated) {
        this.timeBetweenFailuresCalculated = timeBetweenFailuresCalculated;
    }

    public int getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(int idMachine) {
        this.idMachine = idMachine;
    }

    public String getMachineDescription() {
        return machineDescription;
    }

    public void setMachineDescription(String machineDescription) {
        this.machineDescription = machineDescription;
    }

    public String getMachineTimeDistn() {
        return machineTimeDistn;
    }

    public void setMachineTimeDistn(String machineTimeDistn) {
        this.machineTimeDistn = machineTimeDistn;
    }

    public double getMachineTimeParameter1() {
        return machineTimeParameter1;
    }

    public void setMachineTimeParameter1(double machineTimeParameter1) {
        this.machineTimeParameter1 = machineTimeParameter1;
    }

    public double getMachineTimeParameter2() {
        return machineTimeParameter2;
    }

    public void setMachineTimeParameter2(double machineTimeParameter2) {
        this.machineTimeParameter2 = machineTimeParameter2;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getPickUpTimeDistn() {
        return pickUpTimeDistn;
    }

    public void setPickUpTimeDistn(String pickUpTimeDistn) {
        this.pickUpTimeDistn = pickUpTimeDistn;
    }

    public double getPickUpTimeParameter1() {
        return pickUpTimeParameter1;
    }

    public void setPickUpTimeParameter1(double pickUpTimeParameter1) {
        this.pickUpTimeParameter1 = pickUpTimeParameter1;
    }

    public double getPickUpTimeParameter2() {
        return pickUpTimeParameter2;
    }

    public void setPickUpTimeParameter2(double pickUpTimeParameter2) {
        this.pickUpTimeParameter2 = pickUpTimeParameter2;
    }

    public String getPlacementTimeDistn() {
        return placementTimeDistn;
    }

    public void setPlacementTimeDistn(String placementTimeDistn) {
        this.placementTimeDistn = placementTimeDistn;
    }

    public double getPlacementTimeParameter1() {
        return placementTimeParameter1;
    }

    public void setPlacementTimeParameter1(double placementTimeParameter1) {
        this.placementTimeParameter1 = placementTimeParameter1;
    }

    public double getPlacementTimeParameter2() {
        return placementTimeParameter2;
    }

    public void setPlacementTimeParameter2(double placementTimeParameter2) {
        this.placementTimeParameter2 = placementTimeParameter2;
    }

    public double getPriceForPurchase() {
        return priceForPurchase;
    }

    public void setPriceForPurchase(double priceForPurchase) {
        this.priceForPurchase = priceForPurchase;
    }

    public double getPricePreventiveMaintenance() {
        return pricePreventiveMaintenance;
    }

    public void setPricePreventiveMaintenance(double pricePreventiveMaintenance) {
        this.pricePreventiveMaintenance = pricePreventiveMaintenance;
    }

    public double getTempPriceForSell() {
        double newTempPriceForSell = tempPriceForSell;
        tempPriceForSell = 0.0;
        return newTempPriceForSell;
    }
    
    public double getPriceForSell() {
        getPercentageAccumulatedDepreciation();
        tempPriceForSell = priceForSell;
        return priceForSell;
    }

    public void setPriceForSell(double priceForSell) {
        this.priceForSell = priceForSell;
    }

    public String getRepairTimeDistn() {
        return repairTimeDistn;
    }

    public void setRepairTimeDistn(String repairTimeDistn) {
        this.repairTimeDistn = repairTimeDistn;
    }

    public double getRepairTimeParameter1() {
        return repairTimeParameter1;
    }

    public void setRepairTimeParameter1(double repairTimeParameter1) {
        this.repairTimeParameter1 = repairTimeParameter1;
    }

    public double getRepairTimeParameter2() {
        return repairTimeParameter2;
    }

    public void setRepairTimeParameter2(double repairTimeParameter2) {
        this.repairTimeParameter2 = repairTimeParameter2;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        if (activateLaterDeactivation && status == Status.Idle){
            activateLaterDeactivation = false;
            tempPriceForSell = 0.0;
            setMachineState(ObjectState.Inactive);
//            gameEngine.getGeneralScreenController().updateOptionButtonClicked();
        }
        if (status.equals(Status.Broken)){
            showHideBrokenMachine(true);
            setInitialBrokenTime();
        }else{
            showHideBrokenMachine(false);
            setFinalBrokenTime();
        }
        if (status.equals(Status.Busy)){
            setInitialUsageTime();
            setInitialTime();
            startProduction = true;
        }else
        if (status.equals(Status.Idle)){
            activityDescriptionAssigned = "";
            setFinalUsageTime();
            setFinalTime();
            setEndProduction();
            if (isAlreadyBroken){
                gameEngine.getGameSounds().playSound(Sounds.MachineEquipmentBroken);
                isAlreadyBroken = false;
                setCurrentWorkingTime(-getRepairTimeCalculated());
                setStatus(Status.Broken);
                if (getMachineCategory().equals(MachineCategory.Operation))
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentBroken.replace(Messages.wildCard, "Machine").replace(Messages.wildCard2, String.valueOf(getIdMachine())));
                else
                    GameLogScreenController.addMessage(MessageType.Notification, Messages.machineEquipmentBroken.replace(Messages.wildCard, "Equipment").replace(Messages.wildCard2, String.valueOf(getIdMachine())));
            }
        }
    }

    public String getTimeBetweenFailuresDistn() {
        return timeBetweenFailuresDistn;
    }

    public void setTimeBetweenFailuresDistn(String timeBetweenFailuresDistn) {
        this.timeBetweenFailuresDistn = timeBetweenFailuresDistn;
    }

    public double getTimeBetweenFailuresParameter1() {
        return timeBetweenFailuresParameter1;
    }

    public void setTimeBetweenFailuresParameter1(double timeBetweenFailuresParameter1) {
        this.timeBetweenFailuresParameter1 = timeBetweenFailuresParameter1;
    }

    public double getTimeBetweenFailuresParameter2() {
        return timeBetweenFailuresParameter2;
    }

    public void setTimeBetweenFailuresParameter2(double timeBetweenFailuresParameter2) {
        this.timeBetweenFailuresParameter2 = timeBetweenFailuresParameter2;
    }

    public double getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(double volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(double weightCapacity) {
        this.weightCapacity = weightCapacity;
    }
    
    public String getMachineDesign() {
        return machineDesign;
    }

    public void setMachineDesign(String machineDesign) {
        this.machineDesign = machineDesign;
    }

    public String getMachineMaterial() {
        return machineMaterial;
    }

    public void setMachineMaterial(String machineMaterial) {
        this.machineMaterial = machineMaterial;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public MachineCategory getMachineCategory() {
        return machineCategory;
    }

    public void setMachineCategory(MachineCategory machineCategory) {
        this.machineCategory = machineCategory;
    }
    
}
