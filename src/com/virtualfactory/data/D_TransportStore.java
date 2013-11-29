/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Activity;
import com.virtualfactory.entity.E_TransportStore;
import com.virtualfactory.gui.ProgressBarController;
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
public class D_TransportStore {
    private E_TransportStore objData;
    //private ArrayList<E_TransportStore> arrData;
    private Map<Integer, E_TransportStore> mapData;
    private String tableName = "t_transport_store";
    private ArrayList<String> arrColumns;
    
    public D_TransportStore(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_transport");
        arrColumns.add("id_game");
        arrColumns.add("unit_load");
        arrColumns.add("id_station_ini");
        arrColumns.add("id_station_end");
    }
    
    public Map<Integer, E_TransportStore> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_TransportStore>();
        if (isSQLite){
            String sqlScript = "select " +
            "        s.id_transport, s.unit_load, s.id_station_ini, " +
            "        s.id_station_end, a.id_part, a.activity_description, " +
            "        a.type_activity, a.id_order_activity, a.id_next_activity,  " +
            "        a.processing_time, a.activity_state, a.activity_status, " +
            "        a.cost_per_execution, a.machine_category, a.priority_queue " +
            "    from " +
            "        t_transport_store s, t_activity a " +
            "    where " +
            "        s.id_transport = a.id_activity and " +
            "        s.id_game = a.id_game and " +
            "        a.id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,15);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("TransportStore_Select(" + idGame + ")",15);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_TransportStore();
                objData.setIdTransportStore((Integer)arrObj.get(0));
//                objData.setUnitLoad((Integer)arrObj.get(1));
                objData.setDefaultValue((Integer)arrObj.get(1));
                objData.setUnitLoad(0);
                objData.setIdStationInitial((Integer)arrObj.get(2));
                objData.setIdStationEnd((Integer)arrObj.get(3));
                objData.setIdActivity((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(4));
                objData.setActivityDescription(String.valueOf(arrObj.get(5)));
                objData.setTypeActivity(TypeActivity.valueOf(String.valueOf(arrObj.get(6))));
                objData.setIdOrderActivity((Integer)arrObj.get(7));
                objData.setIdNextActivity((Integer)arrObj.get(8));
                objData.setProcessingTime((Integer)arrObj.get(9));
                objData.setActivityState(String.valueOf(arrObj.get(10)));
                objData.setActivityStatus(String.valueOf(arrObj.get(11)));
                objData.setCostPerExecution(isSQLite == true ? (Double)arrObj.get(12) : ((BigDecimal)(arrObj.get(12))).doubleValue());
                objData.setMachineCategory(String.valueOf(arrObj.get(13)));
//                objData.setPriorityQueue((Integer)arrObj.get(14));
                objData.setDefaultValuePriority((Integer)arrObj.get(14));
                objData.setPriorityQueue(0);
                objData.setStateMachine(new StateMachine());
                objData.setArrSkillsRequired(new D_Skill().SelectSkillsPerActivity(idGame, (Integer)arrObj.get(0), true));
                mapData.put(objData.getIdActivity(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_TransportStore param){
        E_Activity objTemp = (E_Activity)param;
        D_Activity objActivity = new D_Activity();
        objActivity.Insert(idGame, objTemp);
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("TransportStore_Insert(" +
                objTemp.getIdActivity() + "," +
                idGame + "," +
                param.getUnitLoad() + "," +
//                param.getIdMachine() + "," +
                param.getIdStationInitial() + "," +
                param.getIdStationEnd() + ")");
        if (blnResult){
            for (Integer idSkill : param.getArrSkillsRequired()){
                new D_Skill().InsertSkillsPerActivity(idGame, param.getIdActivity(), idSkill);
            }
        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("TransportStore_Select(" + idGame + ")",4);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(3))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_transport");
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
