/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Part;
import com.virtualfactory.graphic.nifty.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Unit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 
 */
public class D_Part {
    private E_Part objData;
    private Map<Integer, E_Part> mapData;
    private String tableName = "t_part";
    private ArrayList<String> arrColumns;
    
    public D_Part(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_part");
        arrColumns.add("id_game");
        arrColumns.add("part_description");
        arrColumns.add("unit");
        arrColumns.add("volume");
        arrColumns.add("weight");
        arrColumns.add("quantity_palette");
        arrColumns.add("current_stock");
        arrColumns.add("price_for_sale");
        arrColumns.add("owner");
        arrColumns.add("part_design");
        arrColumns.add("part_design_scale");
        arrColumns.add("part_design_color");
        arrColumns.add("factor");
        arrColumns.add("output_quantity");
    }
    
    public Map<Integer, E_Part> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Part>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_part, part_description, unit, volume, weight,  " +
            "        quantity_palette, current_stock, price_for_sale, " +
            "        owner, part_design, part_design_scale, part_design_color,  " +
            "        factor, output_quantity " +
            "    from  " +
            "        t_part " +
            "    where " +
            "        id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,14);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Part_Select(" + idGame + ")",14);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Part();
                objData.setIdPart((Integer)arrObj.get(0));
                objData.setPartDescription(String.valueOf(arrObj.get(1)));
                objData.setUnit(Unit.valueOf(String.valueOf(arrObj.get(2))));
                objData.setVolume(isSQLite == true ? (Double)arrObj.get(3) : ((BigDecimal)(arrObj.get(3))).doubleValue());
                objData.setWeight(isSQLite == true ? (Double)arrObj.get(4) : ((BigDecimal)(arrObj.get(4))).doubleValue());
                objData.setQuantityPalette((Integer)arrObj.get(5));
                objData.setCurrentStock((Integer)arrObj.get(6));
                objData.setPriceForSale(isSQLite == true ? (Double)arrObj.get(7) : ((BigDecimal)(arrObj.get(7))).doubleValue());
                objData.setOwner(Owner.valueOf(String.valueOf(arrObj.get(8))));
                objData.setArrAssemblyDetails(new D_AssemblyDetails().Select(idGame,(Integer)arrObj.get(0), isSQLite));
                objData.setPartDesign(String.valueOf(arrObj.get(9)));
                objData.setPartDesignScale(isSQLite == true ? (Double)arrObj.get(10) : ((BigDecimal)(arrObj.get(10))).doubleValue());
                objData.setPartDesignColor(String.valueOf(arrObj.get(11)));
                objData.setFactor(isSQLite == true ? (Double)arrObj.get(12) : ((BigDecimal)(arrObj.get(12))).doubleValue());
                objData.setOutputQuantity((Integer)arrObj.get(13));
                mapData.put(objData.getIdPart(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Part param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Part_Insert(" +
                param.getIdPart() + "," +
                idGame + ",'" +
                param.getPartDescription() + "','" +
                param.getUnit() + "'," +
                param.getVolume() + "," +
                param.getWeight() + "," +
                param.getQuantityPalette() + "," +
                param.getCurrentStock() + "," +
                param.getPriceForSale() + ",'" +
                param.getOwner() + "','" +
                param.getPartDesign() + "'," +
                param.getPartDesignScale() + ",'" +
                param.getPartDesignColor() + "'," +
                param.getFactor() + "," +
                param.getOutputQuantity() + ")");
        if (blnResult){
            for (int i=0; i<param.getArrAssemblyDetails().size(); i++)
                new D_AssemblyDetails().Insert(idGame,param.getArrAssemblyDetails().get(i));
        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Part_Select(" + idGame + ")",14);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(5))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(6))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(7))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(8))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(9))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(10))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(11))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(12))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(13))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_part");
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
