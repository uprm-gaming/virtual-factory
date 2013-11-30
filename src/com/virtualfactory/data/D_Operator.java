/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.ui.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Distributions;
import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeActivity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 
 */
public class D_Operator {
    private E_Operator objData;
    //private ArrayList<E_Operator> arrData;
    private Map<Integer, E_Operator> mapData;
    private String tableName = "t_operator";
    private ArrayList<String> arrColumns;
    
    public D_Operator(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_operator");
        arrColumns.add("id_game");
        arrColumns.add("name_operator");
        arrColumns.add("salary_per_hour");
        arrColumns.add("status");
        arrColumns.add("owner");
        arrColumns.add("virtual_id_location");
        arrColumns.add("virtual_matrix_i");
        arrColumns.add("virtual_matrix_j");
        arrColumns.add("current_location_x");
        arrColumns.add("current_location_z");
        arrColumns.add("speed");
        arrColumns.add("is_moving");
        arrColumns.add("id_machine_attached");
        arrColumns.add("end_location_x");
        arrColumns.add("end_location_z");
        arrColumns.add("activity_doing");
        arrColumns.add("id_activity_assigned");
        arrColumns.add("state");
        arrColumns.add("price_for_hire");
        arrColumns.add("price_for_fire");
        arrColumns.add("salary_per_hour_carrier");
        arrColumns.add("salary_per_hour_assembler");
        arrColumns.add("salary_per_hour_versatile");
        arrColumns.add("uniformParam1");
        arrColumns.add("uniformParam2");
        arrColumns.add("normalParamLoad");
        arrColumns.add("normalParamUnload");
    }
    
    public Map<Integer, E_Operator> Select(int idGame, E_Game game, GameEngine gameEngine, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Operator>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_operator, name_operator, salary_per_hour, status, owner, " +
            "        virtual_id_location, virtual_matrix_i, virtual_matrix_j, " +
            "        current_location_x, current_location_z, speed, is_moving, " +
            "        id_machine_attached, end_location_x, end_location_z, " +
            "        activity_doing, id_activity_assigned, state, " +
            "        price_for_hire, price_for_fire, salary_per_hour_carrier, " +
            "        salary_per_hour_assembler, salary_per_hour_versatile, " +
            "        uniformParam1, uniformParam2, normalParamLoad, normalParamUnload " +
            "    from " +
            "        t_operator " +
            "    where " +
            "        id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,27);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Operator_Select(" + idGame + ")",27);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Operator();
                objData.setGameEngine(gameEngine);
                objData.setIdOperator((Integer)arrObj.get(0));
                objData.setNameOperator(String.valueOf(arrObj.get(1)));
                objData.setSalaryPerHour(0);
                objData.setStatus(Status.valueOf(String.valueOf(arrObj.get(3))));
                objData.setOwner(Owner.valueOf(String.valueOf(arrObj.get(4))));
                objData.setVirtualIdLocation(String.valueOf(arrObj.get(5)));
                objData.setVirtualMatrixI((Integer)arrObj.get(6));
                objData.setVirtualMatrixJ((Integer)arrObj.get(7));
                objData.setCurrentLocationX((Integer)arrObj.get(8));
                objData.setCurrentLocationZ((Integer)arrObj.get(9));
                objData.setSpeed(isSQLite == true ? (Double)arrObj.get(10) : ((BigDecimal)(arrObj.get(10))).doubleValue());
                objData.setIsMoving((Integer)arrObj.get(11) == 1 ? true : false);
                objData.setIdMachineAttached((Integer)arrObj.get(12));
                objData.setEndLocationX((Integer)arrObj.get(13));
                objData.setEndLocationZ((Integer)arrObj.get(14));
                objData.setActivityDoing(TypeActivity.valueOf(String.valueOf(arrObj.get(15))));
                objData.setIdStrategyAssigned((Integer)arrObj.get(16));
                objData.setCurrentGame(game);
//                objData.setState(ObjectState.valueOf(String.valueOf(arrObj.get(17))));
                if (ObjectState.Active.toString().equals(String.valueOf(arrObj.get(17)))) 
                    objData.setDefaultValue(1);
                else
                    objData.setDefaultValue(-1);
                objData.setState(ObjectState.Inactive);
                objData.setPriceForHire(isSQLite == true ? (Double)arrObj.get(18) : ((BigDecimal)(arrObj.get(18))).doubleValue());
                objData.setPriceForFire(isSQLite == true ? (Double)arrObj.get(19) : ((BigDecimal)(arrObj.get(19))).doubleValue());
                objData.setSalaryPerHourCarrier(isSQLite == true ? (Double)arrObj.get(20) : ((BigDecimal)(arrObj.get(20))).doubleValue());
                objData.setSalaryPerHourAssembler(isSQLite == true ? (Double)arrObj.get(21) : ((BigDecimal)(arrObj.get(21))).doubleValue());
                objData.setSalaryPerHourVersatile(isSQLite == true ? (Double)arrObj.get(22) : ((BigDecimal)(arrObj.get(22))).doubleValue());
                objData.setUniformParam1(isSQLite == true ? (Double)arrObj.get(23) : ((BigDecimal)(arrObj.get(23))).doubleValue());
                objData.setUniformParam2(isSQLite == true ? (Double)arrObj.get(24) : ((BigDecimal)(arrObj.get(24))).doubleValue());
                objData.setNormalParamLoad(isSQLite == true ? (Double)arrObj.get(25) : ((BigDecimal)(arrObj.get(25))).doubleValue());
                objData.setNormalParamUnload(isSQLite == true ? (Double)arrObj.get(26) : ((BigDecimal)(arrObj.get(26))).doubleValue());
//                objData.setIsExecutingActivity((Integer)arrObj.get(17) == 1 ? true : false);
                SelectSkillPerOperator(idGame,(Integer)arrObj.get(0), objData, isSQLite);
//                objData.updateOperatorCategory();
                objData.isLoadingInitialOperatorData = false;
                mapData.put(objData.getIdOperator(), objData);
        }
        return mapData;
    }
    
    private void SelectSkillPerOperator(int idGame, int idOperator, E_Operator tOperator, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        double efficiencyCalculated = 0;
        tOperator.setArrSkills(new ArrayList<Pair<Integer,Double>>());
        if (isSQLite){
            String sqlScript = "select " +
            "        id_skill, efficiency, dist_param1, dist_param2 " +
            "    from " +
            "        t_skill_operator " +
            "    where " +
            "        id_game = " + idGame + " and " +
            "        id_operator = " + idOperator + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,4);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("SkillPerOperator_Select(" + idGame + "," + idOperator + ")",4);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                efficiencyCalculated = Distributions.calculateDist(Distributions.distUniform, isSQLite == true ? (Double)arrObj.get(2) : ((BigDecimal)(arrObj.get(2))).doubleValue(), isSQLite == true ? (Double)arrObj.get(3) : ((BigDecimal)(arrObj.get(3))).doubleValue());
                tOperator.getArrSkills().add(new Pair((Integer)arrObj.get(0), efficiencyCalculated));
        }
    }
    
    public boolean Insert(int idGame, E_Operator param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Operator_Insert(" +
                param.getIdOperator() + "," +
                idGame + ",'" +
                param.getNameOperator() + "'," +
                param.getSalaryPerHour() + ",'" +
                param.getStatus() + "','" +
                param.getOwner() + "','" +
                param.getVirtualIdLocation() + "'," +
                param.getVirtualMatrixI() + "," +
                param.getVirtualMatrixJ() + "," +
                param.getCurrentLocationX() + "," +
                param.getCurrentLocationZ() + "," +
                param.getSpeed() + "," + 
                (param.isIsMoving() == true ? 1 : 0) + "," +
                param.getIdMachineAttached() + "," +
                param.getEndLocationX() + "," +
                param.getEndLocationZ() + ",'" + 
                param.getActivityDoing().toString() + "'," +
                param.getIdStrategyAssigned() + ",'" +
                param.getState().toString() + "'," + 
                param.getPriceForHire() + "," +
                param.getPriceForFire() + ")");
//                (param.isIsExecutingActivity() == true ? 1 : 0) + ")");
        if (blnResult){
            for (Pair<Integer,Double> tempPair : param.getArrSkills()){
                InsertSkillPerOperator(idGame, tempPair.getFirst(), param.getIdOperator(), tempPair.getSecond());
            }
//            for (int i=0; i<param.getArrSkill().size(); i++)
//                InsertSkillPerOperator(idGame, param.getArrSkill().get(i), param.getIdOperator(), param.getArrEfficiency().get(i));
        }
        return blnResult;
    }
    
    public boolean InsertSkillPerOperator(int idGame, int idSkill, int idOperator, double efficiency){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("SkillPerOperator_Insert(" +
                idGame + "," +
                idSkill + "," +
                idOperator + "," +
                efficiency + ")");
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Operator_Select(" + idGame + ")",27);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(5))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(6))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(7))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(8))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(9))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(10))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(11))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(12))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(13))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(14))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(15))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(16))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(17))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(18))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(19))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(20))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(21))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(22))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(23))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(24))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(25))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(26))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_operator");
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
