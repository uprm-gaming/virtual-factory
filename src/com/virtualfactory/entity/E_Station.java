package com.virtualfactory.entity;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.virtualfactory.engine.GameEngine;
import com.virtualfactory.threads.StationAnimation;
import com.virtualfactory.utils.Colors;
import com.virtualfactory.utils.Direction;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.SlotStatus;
import com.virtualfactory.utils.StationType;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeElements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class E_Station {
    private int idStation;
    private String stationDescription;
    private int stationLocationX;
    private int stationLocationY;
    private double sizeW;
    private double sizeL;
    private double priceForPurchase;
    private int inputPaletteCapacity;
    private int outputPaletteCapacity;
    private Status status;
    private Owner owner;
    private StationType stationType;
    private ArrayList<E_Bucket> arrBuckets;
    private ArrayList<Pair<ArrayList<Integer>,E_Bucket>> arrBucketsFixed;
    private Object[][] matrix;
    private E_Slot[][] matrixValue;
    private double costPerHour;
    private GameEngine gameEngine;
    private String stationDesign;
    private double defaultValue = 0;
    private int totalSlots = 0;
    private int selectedSlots = 0;
    private int tempSelectedSlots = 0;
    private int numberSlotsForParking = 0;
    private double percentageSelectedSlots;
    private Map<Integer, E_Slot> mapSlots;

    public Object[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Object[][] matrix) {
        this.matrix = matrix;
    }

    public E_Slot[][] getMatrixValue() {
        return matrixValue;
    }

    public void setMatrixValue(E_Slot[][] matrixValue) {
        this.matrixValue = matrixValue;
    }

    public double getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(double defaultValue) {
        this.defaultValue = defaultValue;
    }

    public E_Station(int idStation, String stationDescription, int stationLocationX, int stationLocationY,
            double sizeW, double sizeL, double priceForPurchase, int inputPaletteCapacity, int outputPaletteCapacity,
            Status status, Owner owner) {
        this.idStation = idStation;
        this.stationDescription = stationDescription;
        this.stationLocationX = stationLocationX;
        this.stationLocationY = stationLocationY;
        this.sizeW = sizeW;
        this.sizeL = sizeL;
        this.priceForPurchase = priceForPurchase;
        this.inputPaletteCapacity = inputPaletteCapacity;
        this.outputPaletteCapacity = outputPaletteCapacity;
        this.status = status;
        this.owner = owner;
    }

    public E_Station() {
    }

    public double getPercentageSelectedSlots() {
        return percentageSelectedSlots;
    }

    public void setPercentageSelectedSlots(double percentageSelectedSlots) {
        this.percentageSelectedSlots = percentageSelectedSlots;
    }

    public int getSelectedSlots() {
        return selectedSlots;
    }

    public void setSelectedSlots(int selectedSlots) {
        this.selectedSlots = selectedSlots;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getTempSelectedSlots() {
        return tempSelectedSlots;
    }

    public void setTempSelectedSlots(int tempSelectedSlots) {
        this.tempSelectedSlots = tempSelectedSlots;
    }

    public void initializeMatrix()
    {
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        matrix = new Object[noMatrixWidth][noMatrixLength];
        for (int i=0;i<noMatrixWidth;i++)
            for (int j=0;j<noMatrixLength;j++)
                matrix[i][j] = 0;
    }

    public void initializeSlots(){
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        totalSlots = noMatrixWidth * noMatrixLength - numberSlotsForParking;
        selectedSlots = (int)(totalSlots * percentageSelectedSlots);
        matrixValue = new E_Slot[noMatrixWidth-1][noMatrixLength];
        tempSelectedSlots = selectedSlots;
        int idSlot = 0;
        mapSlots = new HashMap<Integer, E_Slot>();
        for (int i=0;i<(noMatrixWidth-1);i++){
            for (int j=0;j<noMatrixLength;j++){
                idSlot++;
                matrixValue[i][j] = new E_Slot();
                matrixValue[i][j].setIdSlot(idSlot);
                matrixValue[i][j].setPartDescription("");
                matrixValue[i][j].setQuantity(0);
                matrixValue[i][j].setCurrentGame(gameEngine.getGameData().getCurrentGame());
                if (tempSelectedSlots > 0){
                    matrixValue[i][j].setSlotStatus(SlotStatus.Free);
                    tempSelectedSlots--;
                }else{
                    matrixValue[i][j].setSlotStatus(SlotStatus.Unavailable);
                }
                mapSlots.put(matrixValue[i][j].getIdSlot(), matrixValue[i][j]);
            }
        }
    }

    public Map<Integer, E_Slot> getMapSlots() {
        return mapSlots;
    }

    public void updateSlots(){
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        if (tempSelectedSlots > selectedSlots){
            //FREE
            for (int i=0;i<noMatrixWidth-1;i++){
                for (int j=0;j<noMatrixLength;j++){
                    if (tempSelectedSlots > selectedSlots){
                        if (matrixValue[i][j].getSlotStatus().equals(SlotStatus.Unavailable)){
                            matrixValue[i][j].setSlotStatus(SlotStatus.Free);
                            selectedSlots++;
                        }
                    }else{
                        return;
                    }
                }
            }
        }else{
            //UNAVAILABLE
//            numberSlotsToRelease = selectedSlots - tempSelectedSlots;
            for (int i=noMatrixWidth-2;i>=0;i--){
                for (int j=noMatrixLength-1;j>=0;j--){
                    if (selectedSlots > tempSelectedSlots){
                        if (matrixValue[i][j].getSlotStatus().equals(SlotStatus.Free)){
                            matrixValue[i][j].setSlotStatus(SlotStatus.Unavailable);
                            selectedSlots--;
                        }
                    }else{
                        return;
                    }
                }
            }
        }
    }

//    private void updateSlotsToRelease(int posI, int posJ){
//        if (numberSlotsToRelease > 0){
//            if (matrixValue[posI][posJ].getSlotStatus().equals(SlotStatus.Free)){
//                matrixValue[posI][posJ].setSlotStatus(SlotStatus.Unavailable);
//                numberSlotsToRelease--;
//            }
//        }
//    }

    public int getTotalWorkingTimeBySlots(){
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        int accumulatedWorkingTimeBySlots = 0;
        for (int i=0;i<noMatrixWidth-1;i++){
            for (int j=0;j<noMatrixLength;j++){
                accumulatedWorkingTimeBySlots += matrixValue[i][j].getCurrentWorkingTime();
            }
        }
        return accumulatedWorkingTimeBySlots;
    }

    public double getTotalCostBySlots(){
        return (getTotalWorkingTimeBySlots()/60.0)*costPerHour;
    }

    public Pair<Integer,Integer> reserveParkingZone()
    {
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        for (int j=0;j<noMatrixLength;j++){
            matrix[noMatrixWidth-1][j] = 1;
            numberSlotsForParking++;
        }
        return new Pair<Integer, Integer>((int)(stationLocationX + sizeW/2.0 - Params.standardBucketWidthLength/2.0), stationLocationY);
    }

    public Pair<Integer,Integer> getParkingZone()
    {
        Pair<Integer,Integer> parkingZone = null;
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        boolean foundZone = false;
        int j;
        for (j=0;j<noMatrixLength;j++){
            if (matrix[noMatrixWidth-1][j].equals(1)){
                matrix[noMatrixWidth-1][j] = 2;
                foundZone = true;
                break;
            }
        }
        if (foundZone){
            int initialX = (int)(stationLocationX - sizeW/2.0 + Params.standardBucketWidthLength/2.0);
            int initialZ = (int)(stationLocationY - sizeL/2.0 + Params.standardBucketWidthLength/2.0);
            parkingZone = new Pair<Integer, Integer>(initialX + (noMatrixWidth-1)*Params.standardBucketWidthLength, initialZ + j*Params.standardBucketWidthLength);
        }
        return parkingZone;
    }

    public void releaseParkingZone(int locationZ)
    {
        int initialZ = (int)(stationLocationY - sizeL/2.0 + Params.standardBucketWidthLength/2.0);
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int indexZ = (locationZ - initialZ)/Params.standardBucketWidthLength;
        matrix[noMatrixWidth-1][indexZ] = 1;
    }

    public void reserveParkingZone(int locationZ)
    {
        int initialZ = (int)(stationLocationY - sizeL/2.0 + Params.standardBucketWidthLength/2.0);
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int indexZ = (locationZ - initialZ)/Params.standardBucketWidthLength;
        matrix[noMatrixWidth-1][indexZ] = 2;
    }

    public void updateBucketsPosition()
    {
        //FIX ME: there is missing controlling in case there is no space to locate the 'bucket'
        int initialX = (int)(stationLocationX - sizeW/2.0 + Params.standardBucketWidthLength/2.0);
        int initialZ = (int)(stationLocationY - sizeL/2.0 + Params.standardBucketWidthLength/2.0);
        boolean foundFreeZone = false;
        if (arrBuckets != null){
            for (int m=0; m<arrBuckets.size(); m++){
                foundFreeZone = false;
                for (int i=0;i<matrix.length;i++){
                    for (int j=0;j<matrix[i].length;j++)
                        if (matrix[i][j].equals(0)){
                            matrix[i][j] = 1;
                            arrBuckets.get(m).setCurrentLocationX(initialX + i*Params.standardBucketWidthLength);
                            arrBuckets.get(m).setCurrentLocationZ(initialZ + j*Params.standardBucketWidthLength);
                            foundFreeZone = true;
                            break;
                        }
                    if (foundFreeZone)
                        break;
                }
            }
        }
//        printLocations();
    }

    public Pair<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>,Pair<Integer,Integer>> getLocationInMatrix(int tempSizeW, int tempSizeL)
    {
        //Pair<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>,Pair<Integer,Integer>> result = new Pair<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Pair<Integer, Integer>>(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(new Pair<Integer, Integer>(1, 2), new Pair<Integer, Integer>(3, 4)), new Pair<Integer, Integer>(5, 6))
        int initialX = (int)(stationLocationX - sizeW/2.0 + Params.standardBucketWidthLength/2.0);
        int initialZ = (int)(stationLocationY - sizeL/2.0 + Params.standardBucketWidthLength/2.0);
        int noPosW = tempSizeW/Params.standardOperatorWidthLength;
        int noPosL = tempSizeL/Params.standardOperatorWidthLength;
        boolean foundMatrix = true;
        int p=0,q=0;
//        printLocations();
        for (int i=0; i<=(matrix.length-noPosW); i++){
            foundMatrix = false;
            for (int j=0; j<=(matrix[i].length-noPosL); j++){
                foundMatrix = true;
                for (int m=0; m<noPosW; m++){
                    for (int n=0; n<noPosL; n++){
                        if (!matrix[i+m][j+n].equals(0)){
                            foundMatrix = false;
                            break;
                        }
                    }
                    if (!foundMatrix)
                        break;
                }
                if (foundMatrix){
                    p = i;
                    q = j;
                    break;
                }
            }
            if (foundMatrix)
                break;
        }
        if (foundMatrix){
            //reserve the new matrix
            initialX = initialX + p*Params.standardBucketWidthLength + tempSizeW/2;
            initialZ = initialZ + q*Params.standardBucketWidthLength + tempSizeL/2;
            for (int m=p; m<p+noPosW; m++)
                for (int n=q; n<q+noPosL; n++)
                    matrix[m][n] = 1;
            //return new Pair<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Pair<Integer, Integer>>(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(new Pair<Integer, Integer>(p,q), new Pair<Integer, Integer>(p+noPosW-1,q+noPosL-1)), new Pair<Integer, Integer>(initialX, initialZ));
//            printLocations();
            return new Pair(new Pair(new Pair(p,q), new Pair(p+noPosW-1,q+noPosL-1)), new Pair(initialX, initialZ));
        }else
            return null;
    }

    public void releaseLocation(int iniX, int iniZ, int endX, int endZ){
//        System.out.println("iniX:" + iniX + " iniZ:" + iniZ + " endX:" + endX + " endZ:" + endZ);
        for (int m=iniX; m<=endX; m++)
            for (int n=iniZ; n<=endZ; n++)
                matrix[m][n] = 0;
//        printLocations();
    }

    public Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> getAvailableSlot(String partDescription){
        //FirstPair:  first: i position, second: j position
        //SecondPair: first: IdPart, second: Quantity
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> availableSlot = null;
        for (int i=0;i<noMatrixWidth;i++)
            for (int j=0;j<noMatrixLength;j++)
                if (matrix[i][j].equals(0) && matrixValue[i][j].getSlotStatus().equals(SlotStatus.Free)){
                    availableSlot = new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>();
                    availableSlot.setFirst(new Pair(i,j));
                    availableSlot.setSecond(new Pair());
                    matrix[i][j] = availableSlot;
                    matrixValue[i][j].setPartDescription(partDescription);
                    matrixValue[i][j].setQuantity(0);
                    matrixValue[i][j].setSlotStatus(SlotStatus.Busy);
                    return availableSlot;
                }
        return availableSlot;
    }

    private void releaseSlot(int posI, int posJ){
        if (!matrix[posI][posJ].equals(0)){
            matrix[posI][posJ] = 0;
            matrixValue[posI][posJ].setSlotStatus(SlotStatus.Free);
        }
    }

    public void addItemsInSlotDynamically(Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> slotSelected, double timeToFinish, boolean isAdding, boolean isZeroItems, int quantity, int idStrategy){
        Geometry part = (Geometry)gameEngine.jmonkeyApp.getRootNode().getChild(TypeElements.STATION.toString() + idStation + "_" + TypeElements.PART.toString() + slotSelected.getFirst().getFirst() + "_" + slotSelected.getFirst().getSecond());
        if (part != null && quantity > 0){
            ColorRGBA colorRGBA = Colors.getColorRGBA(gameEngine.getGameData().getMapUserPart().get(slotSelected.getSecond().getFirst()).getPartDesignColor());
            //colorRGBA = ColorRGBA.Brown;
            //part.getMaterial().setColor("Color", colorRGBA);
            /*partTexture = ;  */ 
            
            part.getMaterial().setTexture("ColorMap", gameEngine.jmonkeyApp.getAssetManager().loadTexture("Textures/bx05rgb(wood)2.jpg"));         
            
            StationAnimation stationAnimation = new StationAnimation();
//            System.out.println("PartSlot:" + TypeElements.PART.toString() + slotSelected.getFirst().getFirst() + "_" + slotSelected.getFirst().getSecond() + " -isAdding:" + isAdding + " -isZeroItems:" + isZeroItems + " -Part:" + slotSelected.getSecond().getFirst() + " -Quantity:" + quantity + " -availableQuantity:" + slotSelected.getSecond().getSecond() + " -Thread:" + stationAnimation.getName() + " -Strategy:" + idStrategy);
            stationAnimation.setGameEngine(gameEngine);
            stationAnimation.setPartBox((Box)part.getMesh());
            stationAnimation.setTimeToFinish(timeToFinish);
            stationAnimation.setAddItems(isAdding);
            stationAnimation.setIsZeroItems(isZeroItems);
            stationAnimation.setQuantity(quantity);
            stationAnimation.setIdStrategy(idStrategy);
//            if (idStrategy != -1)
//                ((TransportStrategy)gameEngine.getManageEvents().getEvent(idStrategy)).getArrStationAnimation().add(stationAnimation);
//            stationAnimation.setQuantityGral(quantity);
//            stationAnimation.setCurrentSlot(matrixValue[slotSelected.getFirst().getFirst()][slotSelected.getFirst().getSecond()]);
            stationAnimation.start();
        }else{
            System.out.println("ERROR PartSlot:" + TypeElements.PART.toString() + slotSelected.getFirst().getFirst() + "_" + slotSelected.getFirst().getSecond() + " -isAdding:" + isAdding + " -isZeroItems:" + isZeroItems + " -Quantity:" + quantity + " -availableQuantity:" + slotSelected.getSecond().getSecond());
        }
    }

    public void removeItemsInSlotDynamically(int idPart, int quantity, double timeToFinish){
        int noMatrixWidth = (int)sizeW/Params.standardBucketWidthLength;
        int noMatrixLength = (int)sizeL/Params.standardBucketWidthLength;
        Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> availableSlot = null;
        for (int i=0;i<(noMatrixWidth-1);i++)
            for (int j=0;j<noMatrixLength;j++)
                if (!matrix[i][j].equals(0)){
                    availableSlot = (Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>)matrix[i][j];
                    if (quantity == 0) return;
                    if (availableSlot.getSecond().getFirst() == idPart && availableSlot.getSecond().getSecond() > 0){
                        if (availableSlot.getSecond().getSecond() > quantity){
                            availableSlot.getSecond().setSecond(availableSlot.getSecond().getSecond() - quantity);
                            matrixValue[i][j].setQuantity(availableSlot.getSecond().getSecond());
                            addItemsInSlotDynamically(availableSlot, timeToFinish, false, false, quantity, -1);
                            return;
                        }else{
                            quantity = quantity - availableSlot.getSecond().getSecond();
                            addItemsInSlotDynamically(availableSlot, timeToFinish, false, true, availableSlot.getSecond().getSecond(), -1);
                            releaseSlot(i, j);
                        }
                    }
                }
    }

    private void printLocations(){
        if (!stationType.equals(StationType.AssembleZone))  return;
        System.out.println("MATRIX INICIO:");
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("MATRIX FIN");
    }

    public String getStationDesign() {
        return stationDesign;
    }

    public void setStationDesign(String stationDesign) {
        this.stationDesign = stationDesign;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public StationType getStationType() {
        return stationType;
    }

    public void setStationType(StationType stationType) {
        this.stationType = stationType;
    }

    public ArrayList<E_Bucket> getArrBuckets() {
        return arrBuckets;
    }

    public void setArrBuckets(ArrayList<E_Bucket> arrBuckets) {
        this.arrBuckets = arrBuckets;
    }

    public ArrayList<Pair<ArrayList<Integer>, E_Bucket>> getArrBucketsFixed() {
        return arrBucketsFixed;
    }

    public void setArrBucketsFixed(ArrayList<Pair<ArrayList<Integer>, E_Bucket>> arrBucketsFixed) {
        this.arrBucketsFixed = arrBucketsFixed;
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public int getInputPaletteCapacity() {
        return inputPaletteCapacity;
    }

    public void setInputPaletteCapacity(int inputPaletteCapacity) {
        this.inputPaletteCapacity = inputPaletteCapacity;
    }

    public int getOutputPaletteCapacity() {
        return outputPaletteCapacity;
    }

    public void setOutputPaletteCapacity(int outputPaletteCapacity) {
        this.outputPaletteCapacity = outputPaletteCapacity;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public double getPriceForPurchase() {
        return priceForPurchase;
    }

    public void setPriceForPurchase(double priceForPurchase) {
        this.priceForPurchase = priceForPurchase;
    }

    public double getSizeL() {
        return sizeL;
    }

    public void setSizeL(double sizeL) {
        this.sizeL = sizeL;
    }

    public double getSizeW() {
        return sizeW;
    }

    public void setSizeW(double sizeW) {
        this.sizeW = sizeW;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    public int getStationLocationX() {
        return stationLocationX;
    }

    public void setStationLocationX(int stationLocationX) {
        this.stationLocationX = stationLocationX;
    }

    public int getStationLocationY() {
        return stationLocationY;
    }

    public void setStationLocationY(int stationLocationY) {
        this.stationLocationY = stationLocationY;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
        for (E_Bucket tempBucket : arrBuckets){
            tempBucket.setCostPerHour(costPerHour);
        }
    }


    /**Looks inside this Station the output bucket used to hold parts of the given id.
     * @param partId - the unique identification of a part.
     * @return reference to output bucket holding the specified parts or null if there
     *  is no output bucket in the station designated to the partId given.
     */
    public E_Bucket outBucketWith(int partId) {
//        for(E_Bucket bucket : arrBuckets){
//            if(bucket.getIdPart()==partId && bucket.getDirection()==Direction.Output)
//                return bucket;
//        }
        for(Pair<ArrayList<Integer>,E_Bucket> bucket : arrBucketsFixed){
            if(bucket.getSecond().getIdPart()==partId && bucket.getSecond().getDirection()==Direction.Output)
                return bucket.getSecond();
        }
	return null;
    }

    /**Looks inside this Station the input bucket used to hold parts of the given id.
     * @param partId - the unique identification of a part.
     * @return reference to input bucket that holds the specified parts or null if there
     *  is no input bucket in the station designated to the partId given.
     */
    public E_Bucket inBucketWith(int partId) {
//	for(E_Bucket bucket : arrBuckets){
//		if(bucket.getIdPart()==partId && bucket.getDirection()==Direction.Input)
//			return bucket;
//	}
        for(Pair<ArrayList<Integer>,E_Bucket> bucket : arrBucketsFixed){
            if(bucket.getSecond().getIdPart()==partId && bucket.getSecond().getDirection()==Direction.Input)
                return bucket.getSecond();
        }
	return null;
    }

    /**Looks for the bucket being used in both directions(input and output), or input only
     *  that holds parts of the given id. For each bucket, the method will first check
     *  if bucket.direction == 'Both' and then if bucket.direction == 'Input'.
     * @param partId - the unique identification of a part.
     * @return reference to the bucket holding the specified parts or null if there
     *  is no bucket in the station designated to the partId given, as both input and output
     *  or input only.
     */
    public E_Bucket bothOrInBucketWith(int partId) {
//        for(E_Bucket bucket : arrBuckets){
//            if(bucket.getIdPart()==partId && (bucket.getDirection()==Direction.Both || bucket.getDirection()==Direction.Input))
//                return bucket;
//        }
        for(Pair<ArrayList<Integer>,E_Bucket> bucket : arrBucketsFixed){
            if(bucket.getSecond().getIdPart()==partId && (bucket.getSecond().getDirection()==Direction.Both || bucket.getSecond().getDirection()==Direction.Input))
                return bucket.getSecond();
        }
	return null;
    }


    /**Looks for the bucket being used in both directions(input and output), or output only
     *  that holds parts of the given id. For each bucket, the method will first check
     *  if bucket.direction == 'Both' and then if bucket.direction == 'Output'.
     * @param partId - the unique identification of a part.
     * @return reference to the bucket holding the specified parts or null if there
     *  is no bucket in the station designated to the partId given, as both input and output
     *  or output only.
     */
    public E_Bucket bothOrOutBucketWith(int partId) {
//        for(E_Bucket bucket : arrBuckets){
//            if(bucket.getIdPart()==partId && (bucket.getDirection()==Direction.Both || bucket.getDirection()==Direction.Output))
//                return bucket;
//        }
        for(Pair<ArrayList<Integer>,E_Bucket> bucket : arrBucketsFixed){
            if(bucket.getSecond().getIdPart()==partId && (bucket.getSecond().getDirection()==Direction.Both || bucket.getSecond().getDirection()==Direction.Output))
                return bucket.getSecond();
        }
	return null;
    }

    public void updatePartsInBucket(int partId){
        for (Pair<ArrayList<Integer>,E_Bucket> bucketFixed : arrBucketsFixed){
            if (bucketFixed.getSecond().getIdPart() == partId){
                int partsToShow = bucketFixed.getSecond().getSize();
                for (E_Bucket bucket : arrBuckets){
                    if (bucketFixed.getFirst().contains(bucket.getIdBucket())){
                        if (partsToShow > 0){
                            if (bucket.getCapacity() >= partsToShow){
                                bucket.setSize(partsToShow);
                                partsToShow = 0;
                            }else{
                                bucket.setSize(bucket.getCapacity());
                                partsToShow -= bucket.getCapacity();
                            }
                        }else{
                            bucket.setSize(0);
                        }
                        gameEngine.updatePartsInBucket(bucket);
                    }
                }
                break;
            }
        }
    }

    public void updateBucketsArray(){
        arrBucketsFixed = new ArrayList<Pair<ArrayList<Integer>, E_Bucket>>();
        for (E_Bucket bucket : arrBuckets){
            Pair<ArrayList<Integer>,E_Bucket> bucketTemp = null;
            for (Pair<ArrayList<Integer>,E_Bucket> bucketFixed : arrBucketsFixed){
                if (bucketFixed.getSecond().getIdPart() == bucket.getIdPart() && bucketFixed.getSecond().getDirection().equals(bucket.getDirection())){
                    bucketTemp = bucketFixed;
                    break;
                }
            }
            ArrayList<Integer> newArrBucketsId = null;
            E_Bucket newBucket = null;
            if (bucketTemp != null){//found previous bucket inserted
                newArrBucketsId = bucketTemp.getFirst();
                newBucket = bucketTemp.getSecond();
            }else{//new bucket to insert
                newArrBucketsId = new ArrayList<Integer>();
                newBucket = new E_Bucket();
                newBucket.setIdBucket(arrBucketsFixed.size());
                newBucket.setIdPart(bucket.getIdPart());
                newBucket.setDirection(bucket.getDirection());
                arrBucketsFixed.add(new Pair(newArrBucketsId, newBucket));
            }
            newArrBucketsId.add(bucket.getIdBucket());
            if (bucket.getState().equals(ObjectState.Active)){
                newBucket.setCapacity(newBucket.getCapacity() + bucket.getCapacity());
                newBucket.setSize(newBucket.getSize() + bucket.getSize());
                newBucket.setUnitsToArrive(newBucket.getUnitsToArrive() + bucket.getUnitsToArrive());
                newBucket.setUnitsToRemove(newBucket.getUnitsToRemove() + bucket.getUnitsToRemove());
            }
        }
    }

    public void updateBucketsArrayState(E_Bucket bucket){
        for (Pair<ArrayList<Integer>,E_Bucket> bucketFixed : arrBucketsFixed){
            if (bucketFixed.getFirst().contains(bucket.getIdBucket())){
                E_Bucket newBucket = bucketFixed.getSecond();
                if (bucket.getState().equals(ObjectState.Active)){
                    newBucket.setCapacity(newBucket.getCapacity() + bucket.getCapacity());
                    newBucket.setSize(newBucket.getSize() + bucket.getSize());
                    newBucket.setUnitsToArrive(newBucket.getUnitsToArrive() + bucket.getUnitsToArrive());
                    newBucket.setUnitsToRemove(newBucket.getUnitsToRemove() + bucket.getUnitsToRemove());
                }else{
                    newBucket.setCapacity(newBucket.getCapacity() - bucket.getCapacity());
                    newBucket.setSize(newBucket.getSize() - bucket.getSize());
                    newBucket.setUnitsToArrive(newBucket.getUnitsToArrive() - bucket.getUnitsToArrive());
                    newBucket.setUnitsToRemove(newBucket.getUnitsToRemove() - bucket.getUnitsToRemove());
                }
                break;
            }
        }
    }
}
