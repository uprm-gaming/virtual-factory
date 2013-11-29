/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Activity;
import com.virtualfactory.entity.E_Purchase;
import com.virtualfactory.nifty.screens.controllers.ProgressBarController;
import com.virtualfactory.strategy.StateMachine;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.TypeActivity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 
 */
public class D_Purchase {
    private E_Purchase objData;
    //private ArrayList<E_Purchase> arrData;
    private Map<Integer, E_Purchase> mapData;
    private String tableName = "t_purchase";
    private ArrayList<String> arrColumns;
    
    public D_Purchase(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_purchase");
        arrColumns.add("id_game");
        arrColumns.add("id_station");
        arrColumns.add("id_supplier");
        arrColumns.add("order_point");
        arrColumns.add("order_quantity");
    }
    
    public Map<Integer, E_Purchase> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Purchase>();
        if (isSQLite){
            String sqlScript = "select " +
            "        p.id_purchase, p.id_station, a.id_part, a.activity_description, " +
            "        a.type_activity, a.id_order_activity, a.id_next_activity, " +
            "        a.processing_time, p.id_supplier, p.order_point, p.order_quantity, " +
            "        a.activity_state, a.activity_status, a.cost_per_execution, a.priority_queue " +
            "    from " +
            "        t_purchase p, t_activity a " +
            "    where " +
            "        p.id_purchase = a.id_activity and " +
            "        p.id_game = a.id_game and " +
            "        a.id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,15);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Purchase_Select(" + idGame + ")",15);
        }        
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Purchase();
                objData.setIdPurchase((Integer)arrObj.get(0));
                objData.setIdStation((Integer)arrObj.get(1));
                objData.setIdActivity((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(2));
                objData.setActivityDescription(String.valueOf(arrObj.get(3)));
                objData.setTypeActivity(TypeActivity.valueOf(String.valueOf(arrObj.get(4))));
                objData.setIdOrderActivity((Integer)arrObj.get(5));
                objData.setIdNextActivity((Integer)arrObj.get(6));
                objData.setProcessingTime((Integer)arrObj.get(7));
                objData.setIdSupplier((Integer)arrObj.get(8));
//                objData.setOrderPoint((Integer)arrObj.get(9));
                objData.setDefaultValueOP((Integer)arrObj.get(9));
                objData.setOrderPoint(0);
//                objData.setOrderQuantity((Integer)arrObj.get(10));
                objData.setDefaultValueOQ((Integer)arrObj.get(10));
                objData.setOrderQuantity(0);
                objData.setActivityState(String.valueOf(arrObj.get(11)));
                objData.setActivityStatus(String.valueOf(arrObj.get(12)));
                objData.setCostPerExecution(isSQLite == true ? (Double)arrObj.get(13) : ((BigDecimal)(arrObj.get(13))).doubleValue());
//                objData.setPriorityQueue((Integer)arrObj.get(14));
                objData.setDefaultValuePriority((Integer)arrObj.get(14));
                objData.setPriorityQueue(0);
                objData.setStateMachine(new StateMachine());
                objData.setArrSkillsRequired(new D_Skill().SelectSkillsPerActivity(idGame, (Integer)arrObj.get(0), true));
                mapData.put(objData.getIdActivity(), objData);
        }
        return mapData;
    }
   
    public boolean Insert(int idGame, E_Purchase param){
        E_Activity objTemp = (E_Activity)param;
        D_Activity objActivity = new D_Activity();
        objActivity.Insert(idGame, objTemp);
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Purchase_Insert(" +
                objTemp.getIdActivity() + "," +
                idGame + "," +
                param.getIdStation() + "," +
                param.getIdSupplier() + "," +
                param.getOrderPoint() + "," +
                param.getOrderQuantity() + ")");
        if (blnResult){
            for (Integer idSkill : param.getArrSkillsRequired()){
                new D_Skill().InsertSkillsPerActivity(idGame, param.getIdActivity(), idSkill);
            }
        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Purchase_SelectByGame(" + idGame + ")",5);
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
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_purchase");
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
