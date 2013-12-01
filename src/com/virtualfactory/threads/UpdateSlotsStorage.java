package com.virtualfactory.threads;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Slot;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.SlotStatus;
import com.virtualfactory.utils.TypeElements;
import java.util.Map;

/**
 *
 * @author David
 */
public class UpdateSlotsStorage extends Thread{
    private Map<Integer, E_Station> mapUserStorageStation;
    private GameEngine gameEngine;
    
    @Override
    public void run(){
        E_Slot[][] matrixValue;
        Box partBox;
        Geometry part;
//        System.out.println("-- Start UpdateSlotsStorage.. Thread:" + currentThread().getName());
//        int slotsCleaned = 0;
        for(E_Station station : mapUserStorageStation.values()){
            matrixValue = station.getMatrixValue();
            Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> slot = null;
            for (int i=0; i<matrixValue.length; i++){
                for (int j=0; j<matrixValue[i].length; j++){
                    if (matrixValue[i][j].getSlotStatus().equals(SlotStatus.Free) || matrixValue[i][j].getSlotStatus().equals(SlotStatus.Unavailable)){
                        if (!station.getMatrix()[i][j].equals(0)){
                            slot = (Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>)station.getMatrix()[i][j];
                            part = (Geometry)gameEngine.jmonkeyApp.getRootNode().getChild(TypeElements.STATION.toString() + station.getIdStation() + "_" 
                                    + TypeElements.PART.toString() + slot.getFirst().getFirst() + "_" + slot.getFirst().getSecond());
                            partBox = (Box)part.getMesh();
                            if (partBox.getYExtent() > 0){
                                partBox.updateGeometry(new Vector3f(partBox.getCenter().getX(), 0, partBox.getCenter().getZ()), partBox.getXExtent(), 0, partBox.getZExtent());
//                                slotsCleaned++;
                            }
                        }
                    }
                }
            }
        }
//        System.out.println("-- End UpdateSlotsStorage: SlotsCleaned = " + slotsCleaned);
    }

    public Map<Integer, E_Station> getMapUserStorageStation() {
        return mapUserStorageStation;
    }

    public void setMapUserStorageStation(Map<Integer, E_Station> mapUserStorageStation) {
        this.mapUserStorageStation = mapUserStorageStation;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    
}
