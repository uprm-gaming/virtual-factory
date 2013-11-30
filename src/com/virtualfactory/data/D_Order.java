/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;

import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Order;
import com.virtualfactory.ui.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class D_Order {
    private E_Order objData;
    private Map<Integer, E_Order> mapData;
    private String tableName = "t_order";
    private ArrayList<String> arrColumns;
    
    public D_Order(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_order");
        arrColumns.add("id_game");
        arrColumns.add("id_part");
        arrColumns.add("quantity");
        arrColumns.add("time_units");
        arrColumns.add("time_to_appear");
        arrColumns.add("percentage_variability_min");
        arrColumns.add("percentage_variability_max");
    }
    
    public Map<Integer, E_Order> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Order>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_order, id_part, quantity, time_units, " +
            "        time_to_appear, percentage_variability_min, " +
            "        percentage_variability_max " +
            "    from " +
            "        t_order " +
            "    where " +
            "        id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,7);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Order_Select(" + idGame + ")",7);
        }        
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Order();
                objData.setIdOrder((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(1));
                objData.setQuantity((Integer)arrObj.get(2));
                objData.setTimeUnits((Integer)arrObj.get(3));
                objData.setTimeToAppear((Integer)arrObj.get(4));
                objData.setPercentageVariabilityMin(isSQLite == true ? (Double)arrObj.get(5) : ((BigDecimal)arrObj.get(5)).doubleValue());
                objData.setPercentageVariabilityMax(isSQLite == true ? (Double)arrObj.get(6) : ((BigDecimal)arrObj.get(6)).doubleValue());
                mapData.put(objData.getIdOrder(), objData);
        }
        return mapData;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Order_Select(" + idGame + ")",7);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(5))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(6))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_order");
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
