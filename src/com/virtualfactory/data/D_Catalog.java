/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Catalog;
import com.virtualfactory.gui.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * 
 */
public class D_Catalog {
    //FIXME: Need an unique key to use java.util.Map instead of java.util.Arraylist. Two 
    // integers are used as primary key in database.(for one game)
    private E_Catalog objData;
    //private ArrayList<E_Catalog> arrData;
    private Map<Integer, E_Catalog> mapData;
    private String tableName = "t_catalog";
    private ArrayList<String> arrColumns;
    
    public D_Catalog(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_supplier");
        arrColumns.add("id_part");
        arrColumns.add("id_game");
        arrColumns.add("quality");
        arrColumns.add("production_distn");
        arrColumns.add("production_parameter1");
        arrColumns.add("production_parameter2");
        arrColumns.add("price_function1_limit");
        arrColumns.add("price_function1_charge");
        arrColumns.add("price_function2_limit");
        arrColumns.add("price_function2_charge");
        arrColumns.add("price_function3_limit");
        arrColumns.add("price_function3_charge");
    }
    
    public Map<Integer, E_Catalog> Select(int idGame, int idSupplier, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Catalog>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_supplier, id_part, quality, production_distn, " +
            "        production_parameter1, production_parameter2, price_function1_limit, " +
            "        price_function1_charge, price_function2_limit, price_function2_charge, " +
            "        price_function3_limit, price_function3_charge " +
            "    from " +
            "        t_catalog " +
            "    where " +
            "        id_game = " + idGame + " and " +
            "        id_supplier = " + idSupplier + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,12);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Catalog_Select(" + idGame +"," + idSupplier + ")",12);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Catalog();
                objData.setIdSupplier((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(1));
                objData.setQuality(isSQLite == true ? (Double)arrObj.get(2) : ((BigDecimal)(arrObj.get(2))).doubleValue());
                objData.setProductionDistn(String.valueOf(arrObj.get(3)));
                objData.setProductionParameter1(isSQLite == true ? (Double)arrObj.get(4) : ((BigDecimal)(arrObj.get(4))).doubleValue());
                objData.setProductionParameter2(isSQLite == true ? (Double)arrObj.get(5) : ((BigDecimal)(arrObj.get(5))).doubleValue());
                objData.setPriceFunction1Limit((Integer)arrObj.get(6));
                objData.setPriceFunction1Charge(isSQLite == true ? (Double)arrObj.get(7) : ((BigDecimal)(arrObj.get(7))).doubleValue());
                objData.setPriceFunction2Limit((Integer)arrObj.get(8));
                objData.setPriceFunction2Charge(isSQLite == true ? (Double)arrObj.get(9) : ((BigDecimal)(arrObj.get(9))).doubleValue());
                objData.setPriceFunction3Limit((Integer)arrObj.get(10));
                objData.setPriceFunction3Charge(isSQLite == true ? (Double)arrObj.get(11) : ((BigDecimal)(arrObj.get(11))).doubleValue());
                objData.calculateDistributionsTime();
                mapData.put(objData.getIdPart(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Catalog param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Catalog_Insert(" +
                idGame + "," +
                param.getIdSupplier() + "," + 
                param.getIdPart() + "," +
                param.getQuality() + ",'" +
                param.getProductionDistn() + "'," +
                param.getProductionParameter1() + "," +
                param.getProductionParameter2() + "," +
                param.getPriceFunction1Limit() + "," +
                param.getPriceFunction1Charge() + "," +
                param.getPriceFunction2Limit() + "," +
                param.getPriceFunction2Charge() + "," +
                param.getPriceFunction3Limit() + "," +
                param.getPriceFunction3Charge() + ")");
        return blnResult;
    }
    
    private ArrayList<E_Catalog> SelectByGame(int idGame){
        ArrayList<ArrayList<Object>> arrArray;
        ArrayList<E_Catalog> arrData = new ArrayList<E_Catalog>();
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Catalog_SelectByGame(" + idGame + ")",12);
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Catalog();
                objData.setIdSupplier((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(1));
                objData.setQuality(((BigDecimal)(arrObj.get(2))).doubleValue());
                objData.setProductionDistn(String.valueOf(arrObj.get(3)));
                objData.setProductionParameter1(((BigDecimal)(arrObj.get(4))).doubleValue());
                objData.setProductionParameter2(((BigDecimal)(arrObj.get(5))).doubleValue());
                objData.setPriceFunction1Limit((Integer)arrObj.get(6));
                objData.setPriceFunction1Charge(((BigDecimal)(arrObj.get(7))).doubleValue());
                objData.setPriceFunction2Limit((Integer)arrObj.get(8));
                objData.setPriceFunction2Charge(((BigDecimal)(arrObj.get(9))).doubleValue());
                objData.setPriceFunction3Limit((Integer)arrObj.get(10));
                objData.setPriceFunction3Charge(((BigDecimal)(arrObj.get(11))).doubleValue());
                arrData.add(objData);
        }
        return arrData;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<E_Catalog> mysqlData = SelectByGame(idGame);
        ArrayList<Pair<String,String>> sqliteValue;
        int i=0;
        for (E_Catalog temp : mysqlData){
            sqliteValue = new ArrayList<Pair<String,String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdSupplier())));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdPart())));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Float",String.valueOf(temp.getQuality())));
            sqliteValue.add(new Pair("String",temp.getProductionDistn()));
            sqliteValue.add(new Pair("Float",String.valueOf(temp.getProductionParameter1())));
            sqliteValue.add(new Pair("Float",String.valueOf(temp.getProductionParameter2())));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getPriceFunction1Limit())));
            sqliteValue.add(new Pair("Float",String.valueOf(temp.getPriceFunction1Charge())));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getPriceFunction2Limit())));
            sqliteValue.add(new Pair("Float",String.valueOf(temp.getPriceFunction2Charge())));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getPriceFunction3Limit())));
            sqliteValue.add(new Pair("Float",String.valueOf(temp.getPriceFunction3Charge())));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_supplier");
                filters.add("id_part");
                filters.add("id_game");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String,String>>();
                filtersValue.add(new Pair("Integer",String.valueOf(temp.getIdSupplier())));
                filtersValue.add(new Pair("Integer",String.valueOf(temp.getIdPart())));
                filtersValue.add(new Pair("Integer",String.valueOf(idGame)));
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
            }
            i++;
            pbc.setProgress(Params.percentageLoading + i*Params.percentageQuote/(mysqlData.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
}
