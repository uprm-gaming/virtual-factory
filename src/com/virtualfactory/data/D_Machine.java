/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.ui.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Distributions;
import com.virtualfactory.utils.MachineCategory;
import com.virtualfactory.utils.Owner;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Status;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 
 */
public class D_Machine {
    private E_Machine objData;
    //private ArrayList<E_Machine> arrData;
    private Map<Integer, E_Machine> mapData;
    private String tableName = "t_machine";
    private ArrayList<String> arrColumns;
    
    public D_Machine(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_machine");
        arrColumns.add("id_game");
        arrColumns.add("machine_description");
        arrColumns.add("speed");
        arrColumns.add("weight_capacity");
        arrColumns.add("volume_capacity");
        arrColumns.add("pick_up_time_distn");
        arrColumns.add("pick_up_time_parameter1");
        arrColumns.add("pick_up_time_parameter2");
        arrColumns.add("machine_time_distn");
        arrColumns.add("machine_time_parameter1");
        arrColumns.add("machine_time_parameter2");
        arrColumns.add("placement_time_distn");
        arrColumns.add("placement_time_parameter1");
        arrColumns.add("placement_time_parameter2");
        arrColumns.add("time_between_failures_distn");
        arrColumns.add("time_between_failures_parameter1");
        arrColumns.add("time_between_failures_parameter2");
        arrColumns.add("repair_time_distn");
        arrColumns.add("repair_time_parameter1");
        arrColumns.add("repair_time_parameter2");
        arrColumns.add("price_for_purchase");
        arrColumns.add("status");
        arrColumns.add("owner");
        arrColumns.add("machine_design");
        arrColumns.add("virtual_id_location");
        arrColumns.add("virtual_matrix_ini_i");
        arrColumns.add("virtual_matrix_ini_j");
        arrColumns.add("virtual_matrix_end_i");
        arrColumns.add("virtual_matrix_end_j");
        arrColumns.add("current_location_x");
        arrColumns.add("current_location_z");
        arrColumns.add("size_w");
        arrColumns.add("size_l");
        arrColumns.add("cost_per_hour");
        arrColumns.add("machine_category");
        arrColumns.add("state");
        arrColumns.add("machine_material");
        arrColumns.add("percentage_depreciation");
        arrColumns.add("price_preventive_maintenance");
        arrColumns.add("factor_param1");
        arrColumns.add("factor_param2");
    }
    
    public Map<Integer, E_Machine> Select(int idGame, E_Game game, GameEngine gameEngine, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Machine>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_machine, machine_description, speed, weight_capacity, " +
            "        volume_capacity, pick_up_time_distn, pick_up_time_parameter1, " +
            "        pick_up_time_parameter2, machine_time_distn, machine_time_parameter1, " +
            "        machine_time_parameter2, placement_time_distn, placement_time_parameter1, " +
            "        placement_time_parameter2, time_between_failures_distn,  " +
            "        time_between_failures_parameter1, time_between_failures_parameter2,  " +
            "        repair_time_distn, repair_time_parameter1, repair_time_parameter2, " +
            "        price_for_purchase, status, owner, machine_design, virtual_id_location, " +
            "        virtual_matrix_ini_i, virtual_matrix_ini_j, virtual_matrix_end_i, " +
            "        virtual_matrix_end_j, current_location_x, current_location_z, " +
            "        size_w, size_l, cost_per_hour, machine_category, state, machine_material, " +
            "        percentage_depreciation, price_preventive_maintenance, " +
            "        factor_param1, factor_param2 " +
            "    from " +
            "        t_machine " +
            "    where " +
            "        id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,41);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Machine_Select(" + idGame + ")",41);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Machine();
                objData.setIdMachine((Integer)arrObj.get(0));
                objData.setMachineDescription(String.valueOf(arrObj.get(1)));
                objData.setSpeed(isSQLite == true ? (Double)arrObj.get(2) : ((BigDecimal)(arrObj.get(2))).doubleValue());
                objData.setWeightCapacity(isSQLite == true ? (Double)arrObj.get(3) : ((BigDecimal)(arrObj.get(3))).doubleValue());
                objData.setVolumeCapacity(isSQLite == true ? (Double)arrObj.get(4) : ((BigDecimal)(arrObj.get(4))).doubleValue());
                objData.setPickUpTimeDistn(String.valueOf(arrObj.get(5)).equals("") ? Distributions.distNone : String.valueOf(arrObj.get(5)));
                objData.setPickUpTimeParameter1(isSQLite == true ? (Double)arrObj.get(6) : ((BigDecimal)(arrObj.get(6))).doubleValue());
                objData.setPickUpTimeParameter2(isSQLite == true ? (Double)arrObj.get(7) : ((BigDecimal)(arrObj.get(7))).doubleValue());
                objData.setMachineTimeDistn(String.valueOf(arrObj.get(8)).equals("") ? Distributions.distNone : String.valueOf(arrObj.get(8)));
                objData.setMachineTimeParameter1(isSQLite == true ? (Double)arrObj.get(9) : ((BigDecimal)(arrObj.get(9))).doubleValue());
                objData.setMachineTimeParameter2(isSQLite == true ? (Double)arrObj.get(10) : ((BigDecimal)(arrObj.get(10))).doubleValue());
                objData.setPlacementTimeDistn(String.valueOf(arrObj.get(11)).equals("") ? Distributions.distNone : String.valueOf(arrObj.get(11)));
                objData.setPlacementTimeParameter1(isSQLite == true ? (Double)arrObj.get(12) : ((BigDecimal)(arrObj.get(12))).doubleValue());
                objData.setPlacementTimeParameter2(isSQLite == true ? (Double)arrObj.get(13) : ((BigDecimal)(arrObj.get(13))).doubleValue());
                objData.setTimeBetweenFailuresDistn(String.valueOf(arrObj.get(14)).equals("") ? Distributions.distNone : String.valueOf(arrObj.get(14)));
                objData.setTimeBetweenFailuresParameter1(isSQLite == true ? (Double)arrObj.get(15) : ((BigDecimal)(arrObj.get(15))).doubleValue());
                objData.setTimeBetweenFailuresParameter2(isSQLite == true ? (Double)arrObj.get(16) : ((BigDecimal)(arrObj.get(16))).doubleValue());
                objData.setRepairTimeDistn(String.valueOf(arrObj.get(17)).equals("") ? Distributions.distNone : String.valueOf(arrObj.get(17)));
                objData.setRepairTimeParameter1(isSQLite == true ? (Double)arrObj.get(18) : ((BigDecimal)(arrObj.get(18))).doubleValue());
                objData.setRepairTimeParameter2(isSQLite == true ? (Double)arrObj.get(19) : ((BigDecimal)(arrObj.get(19))).doubleValue());
                objData.setPriceForPurchase(isSQLite == true ? (Double)arrObj.get(20) : ((BigDecimal)(arrObj.get(20))).doubleValue());
                objData.setStatus(Status.valueOf(String.valueOf(arrObj.get(21))));
                objData.setOwner(Owner.valueOf(String.valueOf(arrObj.get(22))));
                objData.setMachineDesign(String.valueOf(arrObj.get(23)));
                objData.setVirtualIdLocation(String.valueOf(arrObj.get(24)));
                objData.setVirtualMatrixIniI((Integer)arrObj.get(25));
                objData.setVirtualMatrixIniJ((Integer)arrObj.get(26));
                objData.setVirtualMatrixEndI((Integer)arrObj.get(27));
                objData.setVirtualMatrixEndJ((Integer)arrObj.get(28));
                objData.setCurrentLocationX((Integer)arrObj.get(29));
                objData.setCurrentLocationZ((Integer)arrObj.get(30));
                objData.setSizeW((Integer)arrObj.get(31));
                objData.setSizeL((Integer)arrObj.get(32));
                objData.setCostPerHour(isSQLite == true ? (Double)arrObj.get(33) : ((BigDecimal)(arrObj.get(33))).doubleValue());
                objData.setMachineCategory(MachineCategory.valueOf(String.valueOf(arrObj.get(34))));
                objData.setCurrentGame(game);
                objData.setGameEngine(gameEngine);
//                objData.setMachineState(ObjectState.valueOf(String.valueOf(arrObj.get(35))));
                if (ObjectState.Active.toString().equals(String.valueOf(arrObj.get(35)))) 
                    objData.setDefaultValue(1);
                else
                    objData.setDefaultValue(-1);
                objData.setMachineState(ObjectState.Inactive);
                objData.setMachineMaterial(String.valueOf(arrObj.get(36)));
                objData.setPercentageDepreciation(isSQLite == true ? (Double)arrObj.get(37) : ((BigDecimal)(arrObj.get(37))).doubleValue());
                objData.setPricePreventiveMaintenance(isSQLite == true ? (Double)arrObj.get(38) : ((BigDecimal)(arrObj.get(38))).doubleValue());
                objData.setFactor_param1(isSQLite == true ? (Double)arrObj.get(39) : ((BigDecimal)(arrObj.get(39))).doubleValue());
                objData.setFactor_param2(isSQLite == true ? (Double)arrObj.get(40) : ((BigDecimal)(arrObj.get(40))).doubleValue());
                objData.calculateDistributionsTime();
                objData.setInitializing(false);
                mapData.put(objData.getIdMachine(), objData);
        }
        return mapData;
    }
    
    public boolean Insert(int idGame, E_Machine param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Machine_Insert(" +
                param.getIdMachine() + "," +
                idGame + ",'" +
                param.getMachineDescription() + "'," +
                param.getSpeed() + "," +
                param.getWeightCapacity() + "," +
                param.getVolumeCapacity() + ",'" +
                param.getPickUpTimeDistn() + "'," +
                param.getPickUpTimeParameter1() + "," +
                param.getPickUpTimeParameter2() + ",'" +
                param.getMachineTimeDistn() + "'," +
                param.getMachineTimeParameter1() + "," +
                param.getMachineTimeParameter2() + ",'" +
                param.getPlacementTimeDistn() + "'," +
                param.getPlacementTimeParameter1() + "," +
                param.getPlacementTimeParameter2() + ",'" +
                param.getTimeBetweenFailuresDistn() + "'," +
                param.getTimeBetweenFailuresParameter1() + "," +
                param.getTimeBetweenFailuresParameter2() + ",'" +
                param.getRepairTimeDistn() + "'," +
                param.getRepairTimeParameter1() + "," +
                param.getRepairTimeParameter2() + "," +
                param.getPriceForPurchase() + ",'" +
                param.getStatus() + "','" +
                param.getOwner() + "','" + 
                param.getMachineDesign() + "','" +
                param.getVirtualIdLocation() + "'," +
                param.getVirtualMatrixIniI() + "," +
                param.getVirtualMatrixIniJ() + "," +
                param.getVirtualMatrixEndI() + "," +
                param.getVirtualMatrixEndJ() + "," +
                param.getCurrentLocationX() + "," +
                param.getCurrentLocationZ() + "," +
                param.getSizeW() + "," +
                param.getSizeL() + "," +
                param.getCostPerHour() + ",'" +
                param.getMachineCategory().toString() + "','" +
                param.getMachineState().toString() + "','" +
                param.getMachineMaterial() + "'," +
                param.getPercentageDepreciation() + ")");
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Machine_Select(" + idGame + ")",41);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(5))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(6))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(7))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(8))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(9))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(10))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(11))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(12))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(13))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(14))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(15))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(16))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(17))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(18))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(19))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(20))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(21))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(22))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(23))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(24))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(25))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(26))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(27))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(28))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(29))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(30))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(31))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(32))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(33))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(34))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(35))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(36))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(37))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(38))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(39))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(40))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_machine");
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
