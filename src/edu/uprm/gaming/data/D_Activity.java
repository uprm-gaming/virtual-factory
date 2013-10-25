package edu.uprm.gaming.data;

import edu.uprm.gaming.data.sqlite.SQLiteConn;
import edu.uprm.gaming.data.sqlite.SQLiteUtilities;
import edu.uprm.gaming.entity.E_Activity;
import edu.uprm.gaming.graphic.nifty.ProgressBarController;
import edu.uprm.gaming.utils.Actions;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.Params;
import edu.uprm.gaming.utils.TypeActivity;
import java.math.BigDecimal;
import java.util.ArrayList;

public class D_Activity {
    private E_Activity objData;
    private ArrayList<E_Activity> arrData;
    private String tableName = "t_activity";
    private ArrayList<String> arrColumns;
    
    public D_Activity(){
        arrColumns = new ArrayList<>();
        arrColumns.add("id_activity");
        arrColumns.add("id_game");
        arrColumns.add("id_part");
        arrColumns.add("activity_description");
        arrColumns.add("type_activity");
        arrColumns.add("id_order_activity");
        arrColumns.add("id_next_activity");
        arrColumns.add("processing_time");
        arrColumns.add("activity_state");
        arrColumns.add("activity_status");
        arrColumns.add("cost_per_execution");
        arrColumns.add("machine_category");
        arrColumns.add("priority_queue");
    }
    
    public ArrayList<E_Activity> Select(int idGame){
        ArrayList<ArrayList<Object>> arrArray;
        arrData = new ArrayList<>();
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Activity_Select(" + idGame + ")",12);
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Activity();
                objData.setIdActivity((Integer)arrObj.get(0));
                objData.setIdPart((Integer)arrObj.get(1));
                objData.setActivityDescription(String.valueOf(arrObj.get(2)));
                objData.setTypeActivity(TypeActivity.valueOf(String.valueOf(arrObj.get(3))));
                objData.setIdOrderActivity((Integer)arrObj.get(4));
                objData.setIdNextActivity((Integer)arrObj.get(5));
                objData.setProcessingTime((Integer)arrObj.get(6));
                objData.setActivityState(String.valueOf(arrObj.get(7)));
                objData.setActivityStatus(String.valueOf(arrObj.get(8)));
                objData.setCostPerExecution(((BigDecimal)(arrObj.get(9))).doubleValue());
                objData.setMachineCategory(String.valueOf(arrObj.get(10)));
                objData.setPriorityQueue((Integer)arrObj.get(11));
                arrData.add(objData);
        }
        return arrData;
    }
    
    public boolean Insert(int idGame, E_Activity param){
        boolean blnResult = false;
        param.setActivityState(param.getStateMachine().getState());
        param.setActivityStatus(param.getStateMachine().getStatus().toString());
        blnResult = MySqlConn.getInstance().ExecuteSP("Activity_Insert(" +
                idGame + "," +
                param.getIdActivity() + "," +
                param.getIdPart() + ",'" + 
                param.getActivityDescription() + "','" +
                param.getTypeActivity() + "'," +
                param.getIdOrderActivity() + "," +
                param.getIdNextActivity() + "," +
                param.getProcessingTime() + ",'" +
                param.getActivityState() + "','" +
                param.getActivityStatus() + "'," +
                param.getCostPerExecution() + "," +
                param.getPriorityQueue() + ")");
//        if (arrArray.size() > 0){
//            param.setIdActivity((Integer)arrArray.get(0).get(0));
//            blnResult = true;
//        }
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<E_Activity> mysqlData = Select(idGame);
        ArrayList<Pair<String,String>> sqliteValue;
        int i=0;
        for (E_Activity temp : mysqlData){
            sqliteValue = new ArrayList<>();
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdActivity())));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdPart())));
            sqliteValue.add(new Pair("String",temp.getActivityDescription()));
            sqliteValue.add(new Pair("String",temp.getTypeActivity().toString()));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdOrderActivity())));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdNextActivity())));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getProcessingTime())));
            sqliteValue.add(new Pair("String",temp.getActivityState()));
            sqliteValue.add(new Pair("String",temp.getActivityStatus()));
            sqliteValue.add(new Pair("Float",String.valueOf(temp.getCostPerExecution())));
            sqliteValue.add(new Pair("String",temp.getMachineCategory()));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getPriorityQueue())));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<>();
                filters.add("id_activity");
                filters.add("id_game");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<>();
                filtersValue.add(new Pair("Integer",String.valueOf(temp.getIdActivity())));
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
