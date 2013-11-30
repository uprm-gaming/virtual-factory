/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Bucket;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.screen.other.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Direction;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.StationType;
import com.virtualfactory.utils.Unit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 
 */
public class D_Bucket {
    private E_Bucket objData;
    //private ArrayList<E_Bucket> arrData;
    private Map<Integer, E_Bucket> mapData;
    private String tableName = "t_bucket";
    private ArrayList<String> arrColumns;
    
    public D_Bucket(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_bucket");
        arrColumns.add("id_station");
        arrColumns.add("id_game");
        arrColumns.add("capacity");
        arrColumns.add("unit");
        arrColumns.add("size");
        arrColumns.add("id_part");
        arrColumns.add("direction");
        arrColumns.add("units_to_arrive");
        arrColumns.add("units_to_remove");
        arrColumns.add("state");
    }
    
    public Map<Integer, E_Bucket> Select(int idGame, int idStation, E_Game game, GameEngine gameEngine, StationType stationType, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Bucket>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_bucket, capacity, unit, size, id_part,  " +
            "        direction, units_to_arrive, units_to_remove, state " +
            "    from " +
            "        t_bucket " +
            "    where " +
            "        id_game = " + idGame + " and " +
            "        id_station = " + idStation + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,9);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Bucket_Select(" + idGame +"," + idStation + ")",9);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Bucket();
                objData.setGameEngine(gameEngine);
                objData.setIdBucket((Integer)arrObj.get(0));
                objData.setIdStation(idStation);
                if (stationType.equals(StationType.StorageFG) || stationType.equals(StationType.StorageIG) || stationType.equals(StationType.StorageRM))
                    objData.setCapacity(Integer.MAX_VALUE);
                else
                    objData.setCapacity((Integer)arrObj.get(1));
                objData.setUnit(Unit.valueOf(String.valueOf(arrObj.get(2))));
                objData.setSize((Integer)arrObj.get(3));
                objData.setIdPart((Integer)arrObj.get(4));
                objData.setDirection(Direction.valueOf(String.valueOf(arrObj.get(5))));
                objData.setUnitsToArrive((Integer)arrObj.get(6));
                objData.setUnitsToRemove((Integer)arrObj.get(7));
                objData.setCurrentGame(game);
                objData.setState(ObjectState.valueOf(String.valueOf(arrObj.get(8))));                
                //arrData.add(objData);
                mapData.put(objData.getIdBucket(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Bucket param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Bucket_Insert(" +
                param.getIdBucket() + "," +
                param.getIdStation() + "," +
                idGame + "," +
                param.getCapacity() + ",'" +
                param.getUnit() + "'," +
                param.getSize() + "," +
                param.getIdPart() + ",'" +
                param.getDirection() + "'," +
                param.getUnitsToArrive() + "," +
                param.getUnitsToRemove() + ",'" +
                param.getState().toString() + "')");
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Bucket_SelectByGame(" + idGame + ")",10);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String,String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(5))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(6))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(7))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(8))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(9))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_bucket");
                filters.add("id_station");
                filters.add("id_game");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String,String>>();
                filtersValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
                filtersValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
                filtersValue.add(new Pair("Integer",String.valueOf(idGame)));
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
            }            
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
}
