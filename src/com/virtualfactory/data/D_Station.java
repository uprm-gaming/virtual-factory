/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.ui.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.StationType;
import com.virtualfactory.utils.Status;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 
 */
public class D_Station {
    private E_Station objData;
    //private ArrayList<E_Station> arrData;
    private Map<Integer, E_Station> mapData;
    private String tableName = "t_station";
    private ArrayList<String> arrColumns;
    
    public D_Station(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_station");
        arrColumns.add("id_game");
        arrColumns.add("station_description");
        arrColumns.add("station_location_x");
        arrColumns.add("station_location_y");
        arrColumns.add("size_w");
        arrColumns.add("size_l");
        arrColumns.add("price_for_purchase");
        arrColumns.add("input_palette_capacity");
        arrColumns.add("output_palette_capacity");
        arrColumns.add("status");
        arrColumns.add("owner");
        arrColumns.add("station_type");
        arrColumns.add("cost_per_hour");
        arrColumns.add("station_design");
        arrColumns.add("percentage_selected_slots");
    }    
    
    public Map<Integer, E_Station> Select(int idGame, E_Game game, GameEngine gameEngine, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Station>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_station, station_description, station_location_x, " +
            "        station_location_y, size_w, size_l, price_for_purchase, " +
            "        input_palette_capacity, output_palette_capacity,  " +
            "        status, owner, station_type, cost_per_hour, station_design, " +
            "        percentage_selected_slots " +
            "    from " +
            "        t_station " +
            "    where " +
            "        id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,15);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Station_Select(" + idGame + ")",15);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Station();
                objData.setGameEngine(gameEngine);
                objData.setIdStation((Integer)arrObj.get(0));
                objData.setStationDescription(String.valueOf(arrObj.get(1)));
                objData.setStationLocationX((Integer)arrObj.get(2));
                objData.setStationLocationY((Integer)arrObj.get(3));
                objData.setSizeW(isSQLite == true ? (Double)arrObj.get(4) : ((BigDecimal)(arrObj.get(4))).doubleValue());
                objData.setSizeL(isSQLite == true ? (Double)arrObj.get(5) : ((BigDecimal)(arrObj.get(5))).doubleValue());
                objData.setPriceForPurchase(isSQLite == true ? (Double)arrObj.get(6) : ((BigDecimal)(arrObj.get(6))).doubleValue());
                objData.setInputPaletteCapacity((Integer)arrObj.get(7));
                objData.setOutputPaletteCapacity((Integer)arrObj.get(8));
                objData.setStatus(Status.valueOf(String.valueOf(arrObj.get(9))));
                objData.setOwner(Owner.valueOf(String.valueOf(arrObj.get(10))));
                objData.setStationType(StationType.valueOf(String.valueOf(arrObj.get(11))));
                objData.setArrBuckets(new ArrayList(new D_Bucket().Select(idGame,(Integer)arrObj.get(0), game, gameEngine, objData.getStationType(), isSQLite).values()));
                objData.setCostPerHour(isSQLite == true ? (Double)arrObj.get(12) : ((BigDecimal)(arrObj.get(12))).doubleValue());
                objData.setStationDesign(String.valueOf(arrObj.get(13)));
//                objData.setPercentageSelectedSlots(isSQLite == true ? (Double)arrObj.get(14) : ((BigDecimal)(arrObj.get(14))).doubleValue());
                objData.setDefaultValue(isSQLite == true ? (Double)arrObj.get(14) : ((BigDecimal)(arrObj.get(14))).doubleValue());
                objData.setPercentageSelectedSlots(0);
                objData.initializeMatrix();
                objData.updateBucketsArray();
                mapData.put(objData.getIdStation(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Station param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Station_Insert(" +
                param.getIdStation() + "," +
                idGame + ",'" +
                param.getStationDescription() + "'," +
                param.getStationLocationX() + "," +
                param.getStationLocationY() + "," +
                param.getSizeW() + "," +
                param.getSizeL() + "," +
                param.getPriceForPurchase() + "," +
                param.getInputPaletteCapacity() + "," +
                param.getOutputPaletteCapacity() + ",'" +
                param.getStatus() + "','" +
                param.getOwner() + "','" +
                param.getStationType() + "'," +
                param.getCostPerHour() + ",'" +
                param.getStationDesign() + "'," +
                param.getPercentageSelectedSlots() + ")");
        if (blnResult){
            for (int i=0; i<param.getArrBuckets().size(); i++)
                new D_Bucket().Insert(idGame,param.getArrBuckets().get(i));
        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Station_Select(" + idGame + ")",15);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(5))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(6))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(7))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(8))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(9))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(10))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(11))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(12))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(13))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(14))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_station");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
                filtersValue.add(new Pair("Integer",String.valueOf(idGame)));
                filtersValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
            }
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
}
