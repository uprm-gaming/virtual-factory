/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Activity;
import com.virtualfactory.entity.E_Ship;
import com.virtualfactory.ui.ProgressBarController;
import com.virtualfactory.strategy.StateMachine;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Distributions;
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
public class D_Ship {
    private E_Ship objData;
    //private ArrayList<E_Ship> arrData;
    private Map<Integer, E_Ship> mapData;
    private String tableName = "t_ship";
    private ArrayList<String> arrColumns;
    
    public D_Ship(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_ship");
        arrColumns.add("id_game");
        arrColumns.add("id_station");
        arrColumns.add("load_quantity");
        arrColumns.add("shipping_time_distn");
        arrColumns.add("shipping_time_parameter1");
        arrColumns.add("shipping_time_parameter2");
    }
    
    public Map<Integer, E_Ship> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        //arrData = new ArrayList<E_Ship>();
        mapData = new HashMap<Integer, E_Ship>();
        if (isSQLite){
            String sqlScript = "select " +
            "        s.id_ship, s.load_quantity, a.id_part, a.activity_description, " +
            "        a.type_activity, a.id_order_activity, a.id_next_activity,  " +
            "        a.processing_time, s.id_station, a.activity_state,  " +
            "        a.activity_status, a.cost_per_execution, a.priority_queue, " +
            "        s.shipping_time_distn, s.shipping_time_parameter1,  " +
            "        s.shipping_time_parameter2 " +
            "    from " +
            "        t_ship s, t_activity a " +
            "    where " +
            "        s.id_ship = a.id_activity and " +
            "        s.id_game = a.id_game and " +
            "        a.id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,16);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Ship_Select(" + idGame + ")",16);
        }        
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Ship();
                objData.setIdShip((Integer)arrObj.get(0));
                objData.setLoadQuantity((Integer)arrObj.get(1));
                objData.setIdActivity((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(2));
                objData.setActivityDescription(String.valueOf(arrObj.get(3)));
                objData.setTypeActivity(TypeActivity.valueOf(String.valueOf(arrObj.get(4))));
                objData.setIdOrderActivity((Integer)arrObj.get(5));
                objData.setIdNextActivity((Integer)arrObj.get(6));
                objData.setProcessingTime((Integer)arrObj.get(7));
                objData.setIdStation((Integer)arrObj.get(8));
                objData.setActivityState(String.valueOf(arrObj.get(9)));
                objData.setActivityStatus(String.valueOf(arrObj.get(10)));
                objData.setCostPerExecution(isSQLite == true ? (Double)arrObj.get(11) : ((BigDecimal)(arrObj.get(11))).doubleValue());
//                objData.setPriorityQueue((Integer)arrObj.get(12));
                objData.setDefaultValuePriority((Integer)arrObj.get(12));
                objData.setPriorityQueue(0);
                objData.setStateMachine(new StateMachine());
                objData.setShippingTimeDistn(String.valueOf(arrObj.get(13)).equals("") ? Distributions.distNone : String.valueOf(arrObj.get(13)));
                objData.setShippingTimeParameter1(isSQLite == true ? (Double)arrObj.get(14) : ((BigDecimal)(arrObj.get(14))).doubleValue());
                objData.setShippingTimeParameter2(isSQLite == true ? (Double)arrObj.get(15) : ((BigDecimal)(arrObj.get(15))).doubleValue());
                objData.calculateDistributionsTime();
                objData.setArrSkillsRequired(new D_Skill().SelectSkillsPerActivity(idGame, (Integer)arrObj.get(0), true));
                mapData.put(objData.getIdActivity(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Ship param){
        E_Activity objTemp = (E_Activity)param;
        D_Activity objActivity = new D_Activity();
        objActivity.Insert(idGame, objTemp);
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Ship_Insert(" +
                objTemp.getIdActivity() + "," +
                idGame + "," +
                param.getIdStation() + "," +
                param.getLoadQuantity() + ",'" +
                param.getShippingTimeDistn() + "'," +
                param.getShippingTimeParameter1() + "," +
                param.getShippingTimeParameter2() + ")" );
        if (blnResult){
            for (Integer idSkill : param.getArrSkillsRequired()){
                new D_Skill().InsertSkillsPerActivity(idGame, param.getIdActivity(), idSkill);
            }
        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Ship_SelectByGame(" + idGame + ")",6);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(5))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_ship");
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
