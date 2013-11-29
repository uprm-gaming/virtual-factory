/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Player;
import com.virtualfactory.entity.E_PlayerLog;
import com.virtualfactory.entity.E_ToUpdate;
import com.virtualfactory.gui.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.GameTables;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author David
 */
public class D_Player {
    private E_Player objData;
    private String tableName = "t_player";
    private ArrayList<String> arrColumns;
    private String tableNameLog = "t_player_log";
    private ArrayList<String> arrColumnsLog;
    
    public D_Player(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_player");
        arrColumns.add("name");
        arrColumns.add("last_name");
        arrColumns.add("email");
        arrColumns.add("password");
        arrColumns.add("status");
        arrColumns.add("status_description");
        arrColumns.add("gender");
        arrColumns.add("degree");
        arrColumns.add("degree_description");
        arrColumns.add("country");
        arrColumns.add("game_time");
        arrColumns.add("last_login_time");
        
        arrColumnsLog = new ArrayList<String>();
        arrColumnsLog.add("id_player_log");
        arrColumnsLog.add("id_player");
        arrColumnsLog.add("login_time");
        arrColumnsLog.add("logout_time");
        arrColumnsLog.add("minutes_time");
        arrColumnsLog.add("game_level");
    }
    
    public boolean ValidateEmail(String email){
        ArrayList<ArrayList<Object>> arrArray;
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Player_ValidateEmail('" + email + "')",1);
        if (arrArray.size() == 1){
            ArrayList<Object> arrObj = arrArray.get(0);
            if ((Long)arrObj.get(0) == 0)
                return true;
            else
                return false;
        }
        return false;
    }
    
    public E_Player Select_OnePlayer(String email, String password){
        ArrayList<ArrayList<Object>> arrArray;
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Player_Select('" + email + "','" + password + "')",13);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Player();
                objData.setIdPlayer((Integer)arrObj.get(0));
                objData.setName(String.valueOf(arrObj.get(1)));
                objData.setLastName(String.valueOf(arrObj.get(2)));
                objData.setEmail(String.valueOf(arrObj.get(3)));
                objData.setPassword(String.valueOf(arrObj.get(4)));
                objData.setStatus(String.valueOf(arrObj.get(5)));
                objData.setStatusDescription(String.valueOf(arrObj.get(6)));
                objData.setGender(String.valueOf(arrObj.get(7)));
                objData.setDegree(String.valueOf(arrObj.get(8)));
                objData.setDegreeDescription(String.valueOf(arrObj.get(9)));
                objData.setCountry(String.valueOf(arrObj.get(10)));
                objData.setGameTime(arrObj.get(11) == null ? 0 : (Integer)arrObj.get(11));
                objData.setLastLoginTime(arrObj.get(12) == null ? 0 : (Integer)arrObj.get(12));
        }
        return objData;
    }
    
    public E_Player SelectSQLite_OnePlayer(String email, String password){
        ArrayList<ArrayList<Object>> arrArray;
        String sqlScript = "select id_player, name, last_name, email, password, status, status_description, gender, degree, degree_description, "
                + "country, game_time, last_login_time from t_player where upper(email) = upper('"+ email +"') and password = '"+ password +"';";
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,13);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Player();
            objData.setIdPlayer((Integer)arrObj.get(0));
            objData.setName(String.valueOf(arrObj.get(1)));
            objData.setLastName(String.valueOf(arrObj.get(2)));
            objData.setEmail(String.valueOf(arrObj.get(3)));
            objData.setPassword(String.valueOf(arrObj.get(4)));
            objData.setStatus(String.valueOf(arrObj.get(5)));
            objData.setStatusDescription(String.valueOf(arrObj.get(6)));
            objData.setGender(String.valueOf(arrObj.get(7)));
            objData.setDegree(String.valueOf(arrObj.get(8)));
            objData.setDegreeDescription(String.valueOf(arrObj.get(9)));
            objData.setCountry(String.valueOf(arrObj.get(10)));
            objData.setGameTime(arrObj.get(11) == null ? 0 : (Integer)arrObj.get(11));
            objData.setLastLoginTime(arrObj.get(12) == null ? 0 : (Integer)arrObj.get(12));
        }
        return objData;
    }
    
    public E_Player SelectPlayerByEmail(String email){
        ArrayList<ArrayList<Object>> arrArray;
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Player_SelectByEmail('" + email + "')",13);
        objData = null;
        if(arrArray == null) return null;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Player();
            objData.setIdPlayer((Integer)arrObj.get(0));
            objData.setName(String.valueOf(arrObj.get(1)));
            objData.setLastName(String.valueOf(arrObj.get(2)));
            objData.setEmail(String.valueOf(arrObj.get(3)));
            objData.setPassword(String.valueOf(arrObj.get(4)));
            objData.setStatus(String.valueOf(arrObj.get(5)));
            objData.setStatusDescription(String.valueOf(arrObj.get(6)));
            objData.setGender(String.valueOf(arrObj.get(7)));
            objData.setDegree(String.valueOf(arrObj.get(8)));
            objData.setDegreeDescription(String.valueOf(arrObj.get(9)));
            objData.setCountry(String.valueOf(arrObj.get(10)));
            objData.setGameTime(arrObj.get(11) == null ? 0 : (Integer)arrObj.get(11));
            objData.setLastLoginTime(arrObj.get(12) == null ? 0 : (Integer)arrObj.get(12));
        }
        return objData;
    }
    
    public E_Player SelectSQLite_PlayerByEmail(String email){
        ArrayList<ArrayList<Object>> arrArray;
        String sqlScript = "select id_player, name, last_name, email, password, status, status_description, gender, degree, degree_description, "
                + "country, game_time, last_login_time from t_player where upper(email) = upper('"+ email +"');";
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,13);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Player();
            objData.setIdPlayer((Integer)arrObj.get(0));
            objData.setName(String.valueOf(arrObj.get(1)));
            objData.setLastName(String.valueOf(arrObj.get(2)));
            objData.setEmail(String.valueOf(arrObj.get(3)));
            objData.setPassword(String.valueOf(arrObj.get(4)));
            objData.setStatus(String.valueOf(arrObj.get(5)));
            objData.setStatusDescription(String.valueOf(arrObj.get(6)));
            objData.setGender(String.valueOf(arrObj.get(7)));
            objData.setDegree(String.valueOf(arrObj.get(8)));
            objData.setDegreeDescription(String.valueOf(arrObj.get(9)));
            objData.setCountry(String.valueOf(arrObj.get(10)));
            objData.setGameTime(arrObj.get(11) == null ? 0 : (Integer)arrObj.get(11));
            objData.setLastLoginTime(arrObj.get(12) == null ? 0 : (Integer)arrObj.get(12));
        }
        return objData;
    }
    
    public void InsertSQLite(E_Player param){
        ArrayList<Pair<String,String>> sqliteValue;
        sqliteValue = new ArrayList<Pair<String, String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        sqliteValue.add(new Pair("String",param.getName()));
        sqliteValue.add(new Pair("String",param.getLastName()));
        sqliteValue.add(new Pair("String",param.getEmail()));
        sqliteValue.add(new Pair("String",param.getPassword()));
        sqliteValue.add(new Pair("String",param.getStatus()));
        sqliteValue.add(new Pair("String",param.getStatusDescription()));
        sqliteValue.add(new Pair("String",param.getGender()));
        sqliteValue.add(new Pair("String",param.getDegree()));
        sqliteValue.add(new Pair("String",param.getDegreeDescription()));
        sqliteValue.add(new Pair("String",param.getCountry()));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getGameTime())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getLastLoginTime())));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
    }
    
    public void UpdateSQLite(E_Player param){
        ArrayList<Pair<String,String>> sqliteValue;
        sqliteValue = new ArrayList<Pair<String, String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        sqliteValue.add(new Pair("String",param.getName()));
        sqliteValue.add(new Pair("String",param.getLastName()));
        sqliteValue.add(new Pair("String",param.getEmail()));
        sqliteValue.add(new Pair("String",param.getPassword()));
        sqliteValue.add(new Pair("String",param.getStatus()));
        sqliteValue.add(new Pair("String",param.getStatusDescription()));
        sqliteValue.add(new Pair("String",param.getGender()));
        sqliteValue.add(new Pair("String",param.getDegree()));
        sqliteValue.add(new Pair("String",param.getDegreeDescription()));
        sqliteValue.add(new Pair("String",param.getCountry()));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getGameTime())));
        sqliteValue.add(new Pair("Integer",String.valueOf("strftime('%s', 'now')")));
        ArrayList<String> filters = new ArrayList<String>();
        filters.add("id_player");
        ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
    }
    
    public int Insert(E_Player param){
        int newIdPlayer = -1;
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Player_Insert('" +
                param.getName() + "','" +
                param.getLastName() + "','" +
                param.getEmail() + "','" +
                param.getPassword() + "','" +
                param.getStatus() + "','" +
                param.getStatusDescription() + "','" +
                param.getGender() + "','" +
                param.getDegree() + "','" +
                param.getDegreeDescription() + "','" +
                param.getCountry() + "'," +
                param.getGameTime() + "," +
                param.getLastLoginTime() + ")",1);
        if (arrArray.size() > 0)
            newIdPlayer = (Integer)arrArray.get(0).get(0);
        return newIdPlayer;
    }
    
    public boolean Update(E_Player param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Player_Update(" +
                param.getIdPlayer() + ",'" +
                param.getName() + "','" +
                param.getLastName() + "','" +
                param.getEmail() + "','" +
                param.getPassword() + "','" +
                param.getStatus() + "','" +
                param.getStatusDescription() + "','" +
                param.getGender() + "','" +
                param.getDegree() + "','" +
                param.getDegreeDescription() + "','" +
                param.getCountry() + "'," +
                param.getGameTime() + "," +
                param.getLastLoginTime() + ")");
        return blnResult;
    }
    
    public void LogOut(int idPlayer){
        long timeStamp = System.currentTimeMillis()/1000;
        MySqlConn.getInstance().ExecuteSP("Player_LogOut(" + idPlayer + "," + (int)timeStamp + ")");
        String sqlScript = "update t_player set game_time = game_time + (" + (int)timeStamp + " - last_login_time)/60 where id_player =" + idPlayer + ";";
        SQLiteConn.getInstance().ExecuteSP(sqlScript);
        sqlScript = "update t_player set last_login_time = " + (int)timeStamp + " where id_player =" + idPlayer + ";";
        SQLiteConn.getInstance().ExecuteSP(sqlScript);
    }
    
    public boolean PlayerLog_Insert(E_PlayerLog param, boolean isSQLite){
        int newIdPlayerLog = -1;
        ArrayList<ArrayList<Object>> arrArray;
        if (isSQLite){
            String sqlScript = "select ifnull(max(id_player_log)+1,1) from t_player_log where id_player = " + param.getIdPlayer() + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,1);
            if (arrArray.size() > 0){
                newIdPlayerLog = (Integer)arrArray.get(0).get(0);
                param.setIdPlayerLog(newIdPlayerLog);
                sqlScript = "INSERT INTO t_player_log(id_player_log, id_player, login_time, game_level, id_player_log_remote) VALUES (" 
                        + newIdPlayerLog + ", " + param.getIdPlayer() + ", " + param.getLoginTime() + ", '" + param.getGameLevel() + "', -1);";
                return SQLiteConn.getInstance().ExecuteSP(sqlScript);
            }
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("PlayerLog_Insert(" + param.getIdPlayer() + "," + param.getLoginTime() + "," + 
                    param.getLogoutTime() + "," + param.getMinutesTime() + ",'" + param.getGameLevel() + "')",1);
            if (arrArray != null){
                if (arrArray.size() > 0){
                    newIdPlayerLog = (Integer)arrArray.get(0).get(0);
                    param.setIdPlayerLogRemote(newIdPlayerLog);
                    return true;
                }
            }
        } 
        return false;
    }
    
    public boolean PlayerLog_Update(E_PlayerLog param, boolean isSQLite){
        if (isSQLite){
            if (param.getIdPlayerLog() != -1){
                String sqlScript = "update t_player_log set logout_time = " + param.getLogoutTime() + ", minutes_time = (" + param.getLogoutTime() + " - login_time)/60, " +
                "id_player_log_remote = " + param.getIdPlayerLogRemote() + " where id_player = " + param.getIdPlayer() + " and id_player_log = " + param.getIdPlayerLog() + ";";
                return SQLiteConn.getInstance().ExecuteSP(sqlScript);
            }
        }else{
            return MySqlConn.getInstance().ExecuteSP("PlayerLog_Update(" + param.getIdPlayer() + "," + param.getIdPlayerLogRemote() + "," + param.getLogoutTime() + ")");
        }
        return false;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        if (actionToDo.equals(Actions.INSERT)){

        }else
        if (actionToDo.equals(Actions.UPDATE)){
            
        }
    }
    
    public Map<Integer, E_PlayerLog> PlayerLogSQLite_Select(int idPlayer, ProgressBarController pbc){
        Map<Integer, E_PlayerLog> mapPlayerLog = new HashMap<Integer, E_PlayerLog>();
        E_PlayerLog objPlayerLog = null;
        ArrayList<ArrayList<Object>> arrArray;
        String sqlScript = "select id_player_log, id_player, login_time, logout_time, minutes_time, game_level, id_player_log_remote from "+ tableNameLog +" where id_player = "+ idPlayer +";";
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,7);
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objPlayerLog = new E_PlayerLog();
                objPlayerLog.setIdPlayerLog((Integer)arrObj.get(0));
                objPlayerLog.setIdPlayer((Integer)arrObj.get(1));
                objPlayerLog.setLoginTime((Integer)arrObj.get(2));
                objPlayerLog.setLogoutTime(arrObj.get(3) == null ? -1 : (Integer)arrObj.get(3));
                objPlayerLog.setMinutesTime(arrObj.get(4) == null ? -1 : (Integer)arrObj.get(4));
                objPlayerLog.setGameLevel(String.valueOf(arrObj.get(5)));
                objPlayerLog.setIdPlayerLogRemote(arrObj.get(6) == null ? -1 : (Integer)arrObj.get(6));
                mapPlayerLog.put(objPlayerLog.getIdPlayerLog(), objPlayerLog);
                pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
        return mapPlayerLog;
    }
    
    public void PlayerLogSQLite_UpdateId(E_PlayerLog param, int newIdPlayer){
        ArrayList<String> arrColumnsTemp = new ArrayList<String>();
        arrColumnsTemp.add("id_player");
        ArrayList<Pair<String,String>> sqliteValue = new ArrayList<Pair<String, String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf(newIdPlayer)));
        ArrayList<String> filters = new ArrayList<String>();
        filters.add("id_player");
        filters.add("id_player_log");
        ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdPlayerLog())));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableNameLog, arrColumnsTemp, sqliteValue, filters, filtersValue));
    }
    
    public void PlayerLogSQLite_Delete(E_PlayerLog param){
        String sqlScript = "delete from " + tableNameLog + " where id_player=" + param.getIdPlayer() + " and id_player_log=" + param.getIdPlayerLog() + ";";
        SQLiteConn.getInstance().ExecuteSP(sqlScript);
    }

}
