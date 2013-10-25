/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.data;

import edu.uprm.gaming.data.sqlite.SQLiteConn;
import edu.uprm.gaming.data.sqlite.SQLiteUtilities;
import edu.uprm.gaming.entity.E_Terrain;
import edu.uprm.gaming.graphic.nifty.ProgressBarController;
import edu.uprm.gaming.utils.Actions;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.Params;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class D_Terrain {
    private E_Terrain objData;
    private String tableName = "t_terrain";
    private ArrayList<String> arrColumns;
    
    public D_Terrain(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_terrain");
        arrColumns.add("name");
        arrColumns.add("file_name");
        arrColumns.add("local_scale");
        arrColumns.add("model");
    }
    
    public E_Terrain Select(int idTerrain, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        if (isSQLite){
            String sqlScript = "select " +
            "        id_terrain, name, file_name, local_scale, model " +
            "    from " +
            "        t_terrain " +
            "    where " +
            "        id_terrain = " + idTerrain + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,5);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Terrain_Select(" + idTerrain + ")",5);
        }
        ArrayList<Object> arrObj = arrArray.get(0);
        objData = new E_Terrain();
        objData.setIdTerrain((Integer)arrObj.get(0));
        objData.setName(String.valueOf(arrObj.get(1)));
        objData.setFileName(String.valueOf(arrObj.get(2)));
        objData.setLocalScale(isSQLite == true ? (Double)arrObj.get(3) : ((BigDecimal)(arrObj.get(3))).floatValue());
        objData.setModel(String.valueOf(arrObj.get(4)));
        objData.setArrZones(new D_TerrainReserved().Select((Integer)arrObj.get(0), isSQLite));
        return objData;
    }
    
    public void updateLocalDB(int idTerrain, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Terrain_Select(" + idTerrain + ")",5);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(4))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_terrain");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
                filtersValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
            }
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
}
