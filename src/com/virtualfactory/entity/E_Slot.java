package com.virtualfactory.entity;

import com.virtualfactory.utils.SlotStatus;

/**
 *
 * @author David
 */
public class E_Slot {
    private int idSlot;
    private String partDescription;
    private int quantity;
    private SlotStatus slotStatus;
    private int accumulatedTime=0;
    private E_Game initialTime;
    private E_Game currentGame;
    
//    public double getAccumulatedTime() {
//        return accumulatedTime;
//    }
//
//    public void setAccumulatedTime(int accumulatedTime) {
//        this.accumulatedTime = accumulatedTime;
//    }

    public int getIdSlot() {
        return idSlot;
    }

    public void setIdSlot(int idSlot) {
        this.idSlot = idSlot;
    }

    public E_Game getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(E_Game initialTime) {
        this.initialTime = initialTime;
    }

    public E_Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(E_Game currentGame) {
        this.currentGame = currentGame;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
        if (slotStatus.equals(SlotStatus.Free)){
            partDescription = "";
            quantity = 0;
            setInitialTime();
        }else
        if (slotStatus.equals(SlotStatus.Unavailable)){
            if (initialTime != null){
                accumulatedTime += initialTime.getDifferenceTimes_Minutes(currentGame);
                initialTime = null;
            }            
        }
    }
    
    private void setInitialTime(){
        if (initialTime != null) return;
        initialTime = new E_Game(); 
        initialTime.setCurrentMinute(currentGame.getCurrentMinute());
        initialTime.setCurrentHour(currentGame.getCurrentHour());
        initialTime.setCurrentDay(currentGame.getCurrentDay());
        initialTime.setCurrentMonth(currentGame.getCurrentMonth());
    }
    
    public int getCurrentWorkingTime(){
        int newTime = 0;
        if (initialTime != null){
            newTime = initialTime.getDifferenceTimes_Minutes(currentGame);
        }
//        if (idSlot==1) System.out.println("ID 1 - accumulatedTime:" + accumulatedTime + " - newTime:" + newTime);
        return accumulatedTime + newTime;
    }
}
