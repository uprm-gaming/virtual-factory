/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.entity;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Unit;
import com.virtualfactory.utils.Direction;
import com.virtualfactory.utils.Utils;
import com.virtualfactory.engine.GameEngine;
import com.virtualfactory.exceptions.ExceededCapacityException;
import com.virtualfactory.exceptions.InsufficientPartsException;
/**
 *
 *
 */
public class E_Bucket {
    private int idBucket;
    private int idStation;
    private int capacity = 0;
    private Unit unit;
    private int size = 0;
    private int idPart;
    private Direction direction;
    private int unitsToArrive=0;
    private int unitsToRemove=0;
    private int currentLocationX;
    private int currentLocationZ;
    private int oldSize = 0;
    private double totalCost = 0;
    private double costPerHour;
    private ObjectState state;
    private E_Game initialGame = null;
    private E_Game finalGame = null;
    private E_Game currentGame = null;
    public boolean activateLaterDeactivation = false;
    private GameEngine gameEngine = null;

    public E_Bucket(int idBucket, int idStation, int capacity, Unit unit, int size, int idPart, Direction direction, int unitsToArrive) {
        this.idBucket = idBucket;
        this.idStation = idStation;
        this.capacity = capacity;
        this.unit = unit;
        this.size = size;
        this.idPart = idPart;
        this.direction = direction;
        this.unitsToArrive = unitsToArrive;
    }

    public E_Bucket() {
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }
    
    private void setInitialTime(){
        if (initialGame != null && finalGame != null){
            double activeMin = (double)initialGame.getDifferenceTimes_Minutes(finalGame);
            totalCost += Utils.formatValue2Dec((activeMin/60)*costPerHour);
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
    
    public double updateAndGetTotalCost(){
        double activeMin = 0;
        if (initialGame == null) return totalCost;
        if (state.equals(ObjectState.Active))
            activeMin = (double)initialGame.getDifferenceTimes_Minutes(currentGame);
        else
            activeMin = (double)initialGame.getDifferenceTimes_Minutes(finalGame);
        return totalCost + Utils.formatValue2Dec((activeMin/60)*costPerHour);
    }

    public ObjectState getState() {
        return state;
    }

    public void setState(ObjectState state) {
        this.state = state;
        if (state.equals(ObjectState.Active))
            setInitialTime();
        else
            setFinalTime();
        if (gameEngine.getGameData().getMapUserStation() != null)
            if (gameEngine.getGameData().getMapUserStation().get(idStation) != null)
                gameEngine.getGameData().getMapUserStation().get(idStation).updateBucketsArrayState(this);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    public E_Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(E_Game currentGame) {
        this.currentGame = currentGame;
    }

    public int getOldSize() {
        return oldSize;
    }

    public void setOldSize(int oldSize) {
        this.oldSize = oldSize;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getIdBucket() {
        return idBucket;
    }

    public void setIdBucket(int idBucket) {
        this.idBucket = idBucket;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        if (this.size == 0 && activateLaterDeactivation){
            activateLaterDeactivation = false;
            setState(ObjectState.Inactive);
        }
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getUnitsToArrive() {
        return unitsToArrive;
    }

    public void setUnitsToArrive(int unitsToArrive) {
        this.unitsToArrive = unitsToArrive;
    }
    
    public int getUnitsToRemove() {
        return unitsToRemove;
    }

    public void setUnitsToRemove(int unitsToRemove) {
        this.unitsToRemove = unitsToRemove;
    }
    
    /**Adds the given amount of parts to this bucket.
     * @param amount - the number of parts to add.
     * @throws ExceededCapacityException if the current amount of parts 
     * plus the amount to add exceeds the bucket's max capacity. 
     */
    private void add(int amount) throws ExceededCapacityException{
        int newSize = this.size + amount;
	if(newSize <= this.capacity)
            this.size = newSize;
	else
            throw new ExceededCapacityException("This bucket's capacity cannot exceed "+this.capacity);
    }

    /**Subtracts the given amount of parts from this bucket.
     * @param amount - the number of parts to subtract.
     * @throws InsufficientPartsException if there are less parts on the 
     * bucket than the amount to subtract. 
     */
    public void subtract(int amount) throws InsufficientPartsException{
	int newSize = this.size - amount;
	if(newSize >= 0)
            this.size = newSize;
	else
            throw new InsufficientPartsException("There are less than" +amount+ "parts on the bucket. Parts in bucket: "+this.size);
        if (this.size == 0 && activateLaterDeactivation){
            activateLaterDeactivation = false;
            setState(ObjectState.Inactive);
        }
    }

    /**Adds to the number of units to arrive the given amount of parts.
     * @param amount - the aditional number of parts that will arrive later.
     * @throws ExceededCapacityException if the current amount of parts 
     * plus the new amount to arrive would exceed the bucket's max capacity. 
     */
    public void addUnitsToArrive(int amount) throws ExceededCapacityException{
        int newToArrive = this.unitsToArrive + amount;
	if(newToArrive <= this.capacity)
            this.unitsToArrive = newToArrive;
	else
            throw new ExceededCapacityException("This bucket's capacity cannot exceed "+this.capacity);
    }

    /**Subtracts to the number of unist to arrive the given amount of parts,
     * then adds it to the bucket.
     * @param amount - the number of parts that just arrived.
     * @throws InsufficientPartsException if there are less parts reported to
     * arrive than the amount to subtract. 
     * @throws ExceededCapacityException if the current amount of parts plus
     * the amount to add exceeds the bucket's max capacity. 
     */
    public void arrivedParts(int amount) throws InsufficientPartsException, ExceededCapacityException{
	int newToArrive = this.unitsToArrive - amount;
	if(newToArrive >= 0){
            this.add(amount);
            this.unitsToArrive = newToArrive;
        }
	else
            throw new InsufficientPartsException("There are only" +this.unitsToArrive+ "parts reported to arrive.");
    }

}
