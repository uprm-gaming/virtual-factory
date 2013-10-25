/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.data;

import edu.uprm.gaming.data.sqlite.SQLiteConn;
import edu.uprm.gaming.data.sqlite.SQLiteUtilities;
import edu.uprm.gaming.entity.E_TerrainReserved;
import edu.uprm.gaming.graphic.nifty.ProgressBarController;
import edu.uprm.gaming.utils.Actions;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.Params;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class D_TerrainReserved {
    private E_TerrainReserved objData;
    private Map<Integer, E_TerrainReserved> mapData;
    private String tableName = "t_terrain_reserved";
    private ArrayList<String> arrColumns;
    
    public D_TerrainReserved(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_terrain_reserved");
        arrColumns.add("id_terrain");
        arrColumns.add("location_x");
        arrColumns.add("location_z");
        arrColumns.add("width");
        arrColumns.add("length");
    }
    
    public Map<Integer, E_TerrainReserved> Select(int idTerrain, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_TerrainReserved>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_terrain_reserved, location_x, location_z, width, length " +
            "    from " +
            "        t_terrain_reserved " +
            "    where " +
            "        id_terrain = " + idTerrain + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,5);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Terrain_Reserved_Select(" + idTerrain + ")",5);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_TerrainReserved();
                objData.setIdTerrainReserved((Integer)arrObj.get(0));
                objData.setLocationX((Integer)arrObj.get(1));
                objData.setLocationZ((Integer)arrObj.get(2));
                objData.setWidth((Integer)arrObj.get(3));
                objData.setLength((Integer)arrObj.get(4));
                mapData.put(objData.getIdTerrainReserved(), objData);
        }
        return mapData;
    }
    
    public void updateLocalDB(int idTerrain, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Terrain_Reserved_Select(" + idTerrain + ")",5);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idTerrain)));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(4))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_terrain");
                filters.add("id_terrain_reserved");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
                filtersValue.add(new Pair("Integer",String.valueOf(idTerrain)));
                filtersValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
            }
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
}
