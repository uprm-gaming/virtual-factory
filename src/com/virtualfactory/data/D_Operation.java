/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Activity;
import com.virtualfactory.entity.E_Operation;
import com.virtualfactory.ui.ProgressBarController;
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
public class D_Operation {
    private E_Operation objData;
    //private ArrayList<E_Operation> arrData;
    private Map<Integer, E_Operation> mapData;
    private String tableName = "t_operation";
    private ArrayList<String> arrColumns;
    
    public D_Operation(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_operation");
        arrColumns.add("id_game");
        arrColumns.add("operation_description");
        arrColumns.add("production_policy");
        arrColumns.add("id_station");
        arrColumns.add("quantity_output");
    }
    
    public Map<Integer, E_Operation> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Operation>();
        if (isSQLite){
            String sqlScript = "select " +
            "        o.id_operation, o.operation_description, " +
            "        o.production_policy, " +
            "        o.id_station, o.quantity_output, a.id_part, a.activity_description, " +
            "        a.type_activity, a.id_order_activity, a.id_next_activity, " +
            "        a.processing_time, a.activity_state, a.activity_status, " +
            "        a.cost_per_execution, a.machine_category, a.priority_queue " +
            "    from " +
            "        t_operation o, t_activity a " +
            "    where " +
            "        o.id_operation = a.id_activity and " +
            "        o.id_game = a.id_game and " +
            "        a.id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,16);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Operation_Select(" + idGame + ")",16);
        }        
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Operation();
                objData.setIdOperation((Integer)arrObj.get(0));
                objData.setOperationDescription(String.valueOf(arrObj.get(1)));
                objData.setProductionPolicy((Integer)arrObj.get(2));
                objData.setIdStation((Integer)arrObj.get(3));
                objData.setQuantityOutput((Integer)arrObj.get(4));
                objData.setIdActivity((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(5));
                objData.setActivityDescription(String.valueOf(arrObj.get(6)));
                objData.setTypeActivity(TypeActivity.valueOf(String.valueOf(arrObj.get(7))));
                objData.setIdOrderActivity((Integer)arrObj.get(8));
                objData.setIdNextActivity((Integer)arrObj.get(9));
                objData.setProcessingTime((Integer)arrObj.get(10));
                objData.setActivityState(String.valueOf(arrObj.get(11)));
                objData.setActivityStatus(String.valueOf(arrObj.get(12)));
                objData.setCostPerExecution(isSQLite == true ? (Double)arrObj.get(13) : ((BigDecimal)(arrObj.get(13))).doubleValue());
                objData.setMachineCategory(String.valueOf(arrObj.get(14)));
//                objData.setPriorityQueue((Integer)arrObj.get(15));
                objData.setDefaultValuePriority((Integer)arrObj.get(15));
                objData.setPriorityQueue(0);
                objData.setStateMachine(new StateMachine());
                objData.setArrSkillsRequired(new D_Skill().SelectSkillsPerActivity(idGame, (Integer)arrObj.get(0), isSQLite));
                mapData.put(objData.getIdActivity(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Operation param){
        E_Activity objTemp = (E_Activity)param;
        D_Activity objActivity = new D_Activity();
        objActivity.Insert(idGame, objTemp);
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Operation_Insert(" +
                objTemp.getIdActivity() + "," +
                idGame + ",'" +
                param.getOperationDescription() + "'," +
//                param.getProcessingDistn() + "'," +
//                param.getProcessingParameter1() + "," +
//                param.getProcessingParameter2() + "," +
                param.getProductionPolicy() + "," +
//                param.getIdTypeOperation() + "," +
//                param.getIdMachine() + "," +
                param.getIdStation() + "," +
                param.getQuantityOutput() + ")");
        if (blnResult){
            for (Integer idSkill : param.getArrSkillsRequired()){
                new D_Skill().InsertSkillsPerActivity(idGame, param.getIdActivity(), idSkill);
            }
        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Operation_Select(" + idGame + ")",5);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(4))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_operation");
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
