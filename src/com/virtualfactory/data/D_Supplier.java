/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Catalog;
import com.virtualfactory.entity.E_Supplier;
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
 * 
 */
public class D_Supplier {
    private E_Supplier objData;
    //private ArrayList<E_Supplier> arrData;
    private Map<Integer, E_Supplier> mapData;
    private String tableName = "t_supplier";
    private ArrayList<String> arrColumns;
    
    public D_Supplier(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_supplier");
        arrColumns.add("id_game");
        arrColumns.add("supplier_description");
        arrColumns.add("supplier_location_x");
        arrColumns.add("supplier_location_y");
        arrColumns.add("service_level");
    }
    
    public Map<Integer, E_Supplier> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Supplier>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_supplier, supplier_description, supplier_location_x, " +
            "        supplier_location_y, service_level " +
            "    from " +
            "        t_supplier " +
            "    where " +
            "        id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,5);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Supplier_Select(" + idGame + ")",5);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Supplier();
                objData.setIdSupplier((Integer)arrObj.get(0));
                objData.setSupplierDescription(String.valueOf(arrObj.get(1)));
                objData.setSupplierLocationX((Integer)arrObj.get(2));
                objData.setSupplierLocationY((Integer)arrObj.get(3));
                objData.setServiceLevel(isSQLite == true ? (Double)arrObj.get(4) : ((BigDecimal)(arrObj.get(4))).doubleValue());
                objData.setArrCatalog(new D_Catalog().Select(idGame,(Integer)arrObj.get(0), isSQLite));
                //arrData.add(objData);
                mapData.put(objData.getIdSupplier(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Supplier param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Supplier_Insert(" +
                param.getIdSupplier() + "," +
                idGame + ",'" +
                param.getSupplierDescription() + "'," +
                param.getSupplierLocationX() + "," +
                param.getSupplierLocationY() + "," +
                param.getServiceLevel() + ")");
        if (blnResult){
            Map<Integer, E_Catalog> arrCatalog = param.getArrCatalog();
            for (E_Catalog tempCat : arrCatalog.values())
                new D_Catalog().Insert(idGame,tempCat);
        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Supplier_Select(" + idGame + ")",5);
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
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_supplier");
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
