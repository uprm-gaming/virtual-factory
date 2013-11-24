/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_ToUpdate;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.GameTables;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author David
 */
public class D_ToUpdate {
    private E_ToUpdate objData;
    private Map<Integer, E_ToUpdate> mapData;
    private ArrayList<String> arrColumns;
    
    public D_ToUpdate(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_to_update");
        arrColumns.add("id_game");
        arrColumns.add("game_table_key");
        arrColumns.add("game_table");
        arrColumns.add("action_to_do");
    }
    
    public Map<Integer, E_ToUpdate> Select(){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_ToUpdate>();
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("ToUpdate_Select()",6);
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_ToUpdate();
                objData.setIdToUpdate((Integer)arrObj.get(0));
                objData.setIdGame((Integer)arrObj.get(1));
                objData.setGameTableKey((Integer)arrObj.get(2));
                objData.setGameTable(GameTables.valueOf(String.valueOf(arrObj.get(3))));
                objData.setActionToDo(Actions.valueOf(String.valueOf(arrObj.get(4))));
                mapData.put(objData.getIdToUpdate(), objData);
        }
        return mapData;
    }
    
    public Map<Integer, E_ToUpdate> SelectSQLite(){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_ToUpdate>();
        String sqlScript = "select id_to_update, id_game, game_table_key, game_table, action_to_do from t_to_update order by 1;";        
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,5);
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_ToUpdate();
                objData.setIdToUpdate((Integer)arrObj.get(0));
                objData.setIdGame((Integer)arrObj.get(1));
                objData.setGameTableKey((Integer)arrObj.get(2));
                objData.setGameTable(GameTables.valueOf(String.valueOf(arrObj.get(3))));
                objData.setActionToDo(Actions.valueOf(String.valueOf(arrObj.get(4))));
                mapData.put(objData.getIdToUpdate(), objData);
        }
        return mapData;
    }
    
    public Map<Integer, E_ToUpdate> getDifference(Map<Integer, E_ToUpdate> localData, Map<Integer, E_ToUpdate> remoteData){
        Map<Integer, E_ToUpdate> differenceData = new HashMap<Integer, E_ToUpdate>();

        for (E_ToUpdate temp : remoteData.values()){
            if (!localData.containsKey(temp.getIdToUpdate()))
                differenceData.put(temp.getIdToUpdate(), temp);
        }
        return differenceData;
    }
    
    public void insertLocalDB(E_ToUpdate toUpdate){
        ArrayList<Pair<String,String>> sqliteValue;
        sqliteValue = new ArrayList<Pair<String, String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf(toUpdate.getIdToUpdate())));
        sqliteValue.add(new Pair("Integer",String.valueOf(toUpdate.getIdGame())));
        sqliteValue.add(new Pair("Integer",String.valueOf(toUpdate.getGameTableKey())));
        sqliteValue.add(new Pair("String",toUpdate.getGameTable().toString()));
        sqliteValue.add(new Pair("String",toUpdate.getActionToDo().toString()));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert("t_to_update", arrColumns, sqliteValue));
    }
}