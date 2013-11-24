/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.graphic.nifty.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.GameCategory;
import com.virtualfactory.utils.GameStatus;
import com.virtualfactory.utils.GameType;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author xxx
 */
public class D_Game {
    private E_Game objData;
    private ArrayList<E_Game> arrData;
    private String tableName = "t_game";
    private ArrayList<String> arrColumns;

    public D_Game(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_game");
        arrColumns.add("date_time");
        arrColumns.add("id_player");
        arrColumns.add("current_money");
        arrColumns.add("current_minute");
        arrColumns.add("current_hour");
        arrColumns.add("current_day");
        arrColumns.add("current_month");
        arrColumns.add("current_game_time");
        arrColumns.add("time_factor");
        arrColumns.add("game_type");
        arrColumns.add("game_category");
        arrColumns.add("description");
        arrColumns.add("initial_money");
        arrColumns.add("percentage_to_win");
        arrColumns.add("name");
        arrColumns.add("game_status");
        arrColumns.add("id_phase");
        arrColumns.add("next_id_phase");
        arrColumns.add("id_terrain");
        arrColumns.add("objective");
        arrColumns.add("flow_image");
        arrColumns.add("attempt_numbers");
        arrColumns.add("overhead");
    }
    
    public void AddNewGameAttempt(int idGame, int idPlayer){
        long timeStamp = System.currentTimeMillis()/1000;
        //MySQL
        MySqlConn.getInstance().ExecuteSP("Game_AddNewAttempt(" + idGame + "," + idPlayer + "," + (int)timeStamp + ")");
        //SQLite
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("attempt_numbers");
        columns.add("date_time");
        ArrayList<Pair<String,String>> sqliteValue;
        sqliteValue = new ArrayList<Pair<String,String>>();
        sqliteValue.add(new Pair("Integer","attempt_numbers + 1"));
        sqliteValue.add(new Pair("Integer",String.valueOf((int)timeStamp)));
        ArrayList<String> filters = new ArrayList<String>();
        filters.add("id_game");
        filters.add("id_player");
        ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String,String>>();
        filtersValue.add(new Pair("Integer",String.valueOf(idGame)));
        filtersValue.add(new Pair("Integer",String.valueOf(idPlayer)));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, columns, sqliteValue, filters, filtersValue));
    }
    
    public boolean Update(E_Game param){
        boolean blnResult = false;
        blnResult = MySqlConn.getInstance().ExecuteSP("Game_Update(" +
                param.getIdGame() + "," +
                param.getDateTime() + "," +
                param.getIdPlayer() + "," +
                param.getCurrentMoney() + "," + 
                param.getCurrentMinute() + "," +
                param.getCurrentHour() + "," +
                param.getCurrentDay() + "," +
                param.getCurrentMonth() + "," +
                param.getCurrentTime() + "," +
                param.getTimeFactor() + ",'" +
                param.getGameType().toString() + "','" +
                param.getGameCategory().toString() + "','" +
                param.getDescription().toString() + "'," +
                param.getInitialMoney() + "," +
                param.getPercentageToWin() + ",'" +
                param.getGameName() + "','" +
                param.getGameStatus().toString() + "'," +
                param.getIdPhase() + "," +
                param.getNextIdPhase() + "," +
                param.getIdTerrain() + ",'" +
                param.getObjective() + "','" +
                param.getFlowImage() + "'," + 
                param.getAttemptNumbers() + "," +
                param.getOverhead() + ")");
        return blnResult;
    }
    
    public void SQLite_Update(E_Game param){
        E_Game tempSelectGame = SQLite_SelectGames_OneGame(param.getIdGame(), param.getIdPlayer());
        double newCurrentMoney = param.getCurrentMoney() > tempSelectGame.getCurrentMoney() ? param.getCurrentMoney() : tempSelectGame.getCurrentMoney();
        ArrayList<Pair<String,String>> sqliteValue;
        sqliteValue = new ArrayList<Pair<String,String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdGame())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getDateTime())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        sqliteValue.add(new Pair("Float",String.valueOf(newCurrentMoney)));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentMinute())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentHour())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentDay())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentMonth())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getCurrentTime())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getTimeFactor())));
        sqliteValue.add(new Pair("String",param.getGameType().toString()));
        sqliteValue.add(new Pair("String",param.getGameCategory().toString()));
        sqliteValue.add(new Pair("String",param.getDescription()));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getInitialMoney())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getPercentageToWin())));
        sqliteValue.add(new Pair("String",param.getGameName()));
        sqliteValue.add(new Pair("String",param.getGameStatus().toString()));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdPhase())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getNextIdPhase())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdTerrain())));
        sqliteValue.add(new Pair("String",param.getObjective()));
        sqliteValue.add(new Pair("String",param.getFlowImage()));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getAttemptNumbers())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getOverhead())));
        ArrayList<String> filters = new ArrayList<String>();
        filters.add("id_game");
        filters.add("id_player");
        ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdGame())));
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
    }
    
    public void updateGameBestScore(E_Game param){
        ArrayList<E_Game> tempCPUGames = SQLite_SelectGames_ByType(GameType.CPU);
        for (int i=0; i<tempCPUGames.size(); i++){
            if (tempCPUGames.get(i).getIdGame() == param.getIdGame()){
                if (tempCPUGames.get(i).getCurrentMoney() < param.getCurrentMoney()){
                    //SQLite
                    ArrayList<String> columns = new ArrayList<String>();
                    columns.add("current_money");
                    columns.add("date_time");
                    ArrayList<Pair<String,String>> sqliteValue = new ArrayList<Pair<String, String>>();
                    sqliteValue.add(new Pair("Double",String.valueOf(param.getCurrentMoney())));
                    sqliteValue.add(new Pair("Integer",String.valueOf(param.getDateTime())));
                    ArrayList<String> filters = new ArrayList<String>();
                    filters.add("id_player");
                    filters.add("id_game");
                    ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
                    filtersValue.add(new Pair("Integer",String.valueOf(tempCPUGames.get(i).getIdPlayer())));
                    filtersValue.add(new Pair("Integer",String.valueOf(tempCPUGames.get(i).getIdGame())));
                    SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, columns, sqliteValue, filters, filtersValue));
                }
            }
        }
    }
    
    public void updateWonGame(E_Game param){
        ArrayList<E_Game> arrGamesPlayer = SQLite_SelectGames_OfPlayer(param.getIdPlayer());
        for (E_Game nextGame : arrGamesPlayer){
            if (nextGame.getIdPhase() == param.getNextIdPhase()){
                if (nextGame.getGameStatus().equals(GameStatus.Completed))
                    return;
            }
        }
        //SQLite
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("date_time");
        columns.add("game_status");
        ArrayList<Pair<String,String>> sqliteValue = new ArrayList<Pair<String, String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getDateTime())));
        sqliteValue.add(new Pair("String","Unlocked"));
        ArrayList<String> filters = new ArrayList<String>();
        filters.add("id_player");
        filters.add("id_phase");
        ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        filtersValue.add(new Pair("Integer",String.valueOf(param.getNextIdPhase())));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, columns, sqliteValue, filters, filtersValue));
        //MySQL
        MySqlConn.getInstance().ExecuteSP("Game_UpdateWonGame(" + param.getIdPlayer() + "," + param.getNextIdPhase() + "," + param.getDateTime() + ")");
    }
    
    public void updateFailedGame(E_Game param){
        long timeStamp = System.currentTimeMillis()/1000;
        //SQLite
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("date_time");
        ArrayList<Pair<String,String>> sqliteValue = new ArrayList<Pair<String, String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf((int)timeStamp)));
        ArrayList<String> filters = new ArrayList<String>();
        filters.add("id_game");
        filters.add("id_player");
        ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdGame())));
        filtersValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, columns, sqliteValue, filters, filtersValue));
        //MySQL
        MySqlConn.getInstance().ExecuteSP("Game_UpdateFailedGame(" + param.getIdGame() + "," + param.getIdPlayer() + "," + (int)timeStamp + ")");
    }
        
    public int Insert(E_Game param){
        int newIdGame = 0;
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Game_Insert(" +
                param.getIdGame() + "," + 
                param.getDateTime() + "," + 
                param.getIdPlayer() + "," +
                param.getCurrentMoney() + "," +
                param.getCurrentMinute() + "," +
                param.getCurrentHour() + "," +
                param.getCurrentDay() + "," +
                param.getCurrentMonth() + "," +
                param.getCurrentTime() + "," + 
                param.getTimeFactor() + ",'" + 
                param.getGameType().toString() + "','" + 
                param.getGameCategory().toString() + "','" +
                param.getDescription() + "'," +
                param.getInitialMoney() + "," +
                param.getPercentageToWin() + ",'" +
                param.getGameName() + "','" +
                param.getGameStatus().toString() + "'," +
                param.getIdPhase() + "," +
                param.getNextIdPhase() + "," +
                param.getIdTerrain() + ",'" +
                param.getObjective() + "','" +
                param.getFlowImage() + "'," +
                param.getAttemptNumbers() + "," +
                param.getOverhead() + ")",1);
        if (arrArray != null)
            if (arrArray.size() > 0)
                newIdGame = (Integer)arrArray.get(0).get(0);
        return newIdGame;
    }
    
    public void SQLite_Insert(E_Game param){
        ArrayList<Pair<String,String>> sqliteValue;
        sqliteValue = new ArrayList<Pair<String, String>>();
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdGame())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getDateTime())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdPlayer())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getCurrentMoney())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentMinute())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentHour())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentDay())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getCurrentMonth())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getCurrentTime())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getTimeFactor())));
        sqliteValue.add(new Pair("String",String.valueOf(param.getGameType().toString())));
        sqliteValue.add(new Pair("String",String.valueOf(param.getGameCategory().toString())));
        sqliteValue.add(new Pair("String",String.valueOf(param.getDescription())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getInitialMoney())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getPercentageToWin())));
        sqliteValue.add(new Pair("String",String.valueOf(param.getGameName())));
        sqliteValue.add(new Pair("String",String.valueOf(param.getGameStatus().toString())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdPhase())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getNextIdPhase())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getIdTerrain())));
        sqliteValue.add(new Pair("String",String.valueOf(param.getObjective())));
        sqliteValue.add(new Pair("String",String.valueOf(param.getFlowImage())));
        sqliteValue.add(new Pair("Integer",String.valueOf(param.getAttemptNumbers())));
        sqliteValue.add(new Pair("Float",String.valueOf(param.getOverhead())));
        SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
    }
    
    public ArrayList<E_Game> SelectGames_OfAPlayer(int idPlayer){
        ArrayList<ArrayList<Object>> arrArray;
        arrData = new ArrayList<E_Game>();
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Game_Select(" + idPlayer + ")",26);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Game();
                objData.setIdGame((Integer)arrObj.get(0));
                objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
                objData.setIdPlayer((Integer)arrObj.get(2));
                objData.setCurrentMoney(((BigDecimal)(arrObj.get(3))).doubleValue());
                objData.setCurrentMinute((Integer)arrObj.get(4));
                objData.setCurrentHour((Integer)arrObj.get(5));
                objData.setCurrentDay((Integer)arrObj.get(6));
                objData.setCurrentMonth((Integer)arrObj.get(7));
                objData.setCurrentTime((Double)arrObj.get(8));
                objData.setTimeFactor((Double)arrObj.get(9));
                objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
                objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
                objData.setDescription(String.valueOf(arrObj.get(12)));
                objData.setInitialMoney((Double)arrObj.get(13));
                objData.setPercentageToWin(((BigDecimal)arrObj.get(14)).doubleValue());
                objData.setGameName(String.valueOf(arrObj.get(15)));
                objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
                objData.setIdPhase((Integer)arrObj.get(17));
                objData.setNextIdPhase((Integer)arrObj.get(18));
                objData.setIdTerrain((Integer)arrObj.get(19));
                objData.setObjective(String.valueOf(arrObj.get(20)));
                objData.setFlowImage(String.valueOf(arrObj.get(21)));
                objData.setAttemptNumbers((Integer)arrObj.get(22));
                objData.setYourBestScore(Params.moneySign + " " + Utils.formatValue2DecToString(((BigDecimal)(arrObj.get(23))).doubleValue()));
                objData.setGameBestScore(Params.moneySign + " " + Utils.formatValue2DecToString(((BigDecimal)(arrObj.get(24))).doubleValue()));
                objData.setOverhead(((BigDecimal)arrObj.get(25)).doubleValue());
                arrData.add(objData);
        }
        return arrData;
    }
    
    public ArrayList<E_Game> SQLite_SelectGames_OfPlayer(int idPlayer){
        arrData = new ArrayList<E_Game>();
        ArrayList<ArrayList<Object>> arrArray;
        String sqlScript = "select " +
            "id_game, date_time, id_player, current_money, current_minute, current_hour, current_day, current_month, " +
            "current_game_time, time_factor, game_type, game_category, (select description from t_game where game_type = 'CPU' and id_game = a.id_game) as description, initial_money, percentage_to_win, " +
            "name, game_status, id_phase, next_id_phase, id_terrain, objective, flow_image, attempt_numbers, current_money, " +
            "(select current_money from t_game where game_type = 'CPU' and id_game = a.id_game) as gameBestScore, overhead " +
            "from t_game as a where id_player = "+ idPlayer +" order by id_phase;";
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,26);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Game();
            objData.setIdGame((Integer)arrObj.get(0));
            objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
            objData.setIdPlayer((Integer)arrObj.get(2));
            objData.setCurrentMoney((Double)arrObj.get(3));
            objData.setCurrentMinute((Integer)arrObj.get(4));
            objData.setCurrentHour((Integer)arrObj.get(5));
            objData.setCurrentDay((Integer)arrObj.get(6));
            objData.setCurrentMonth((Integer)arrObj.get(7));
            objData.setCurrentTime((Double)arrObj.get(8));
            objData.setTimeFactor((Double)arrObj.get(9));
            objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
            objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
            objData.setDescription(String.valueOf(arrObj.get(12)));
            objData.setInitialMoney((Double)arrObj.get(13));
            objData.setPercentageToWin((Double)arrObj.get(14));
            objData.setGameName(String.valueOf(arrObj.get(15)));
            objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
            objData.setIdPhase((Integer)arrObj.get(17));
            objData.setNextIdPhase((Integer)arrObj.get(18));
            objData.setIdTerrain((Integer)arrObj.get(19));
            objData.setObjective(String.valueOf(arrObj.get(20)));
            objData.setFlowImage(String.valueOf(arrObj.get(21)));
            objData.setAttemptNumbers((Integer)arrObj.get(22));
            objData.setYourBestScore(Params.moneySign + " " + Utils.formatValue2DecToString((Double)arrObj.get(23)));
            objData.setGameBestScore(Params.moneySign + " " + Utils.formatValue2DecToString((Double)arrObj.get(24)));
            objData.setOverhead((Double)arrObj.get(25));
            arrData.add(objData);
        }
        return arrData;
    }
    
    public Map<Integer, E_Game> SelectGames_OfPlayer(int idPlayer){
        Map<Integer, E_Game> mapData = new HashMap<Integer, E_Game>();
        ArrayList<ArrayList<Object>> arrArray;
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Game_Select(" + idPlayer + ")",26);
        objData = null;
        if (arrArray == null) return mapData;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Game();
            objData.setIdGame((Integer)arrObj.get(0));
            objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
            objData.setIdPlayer((Integer)arrObj.get(2));
            objData.setCurrentMoney(((BigDecimal)(arrObj.get(3))).doubleValue());
            objData.setCurrentMinute((Integer)arrObj.get(4));
            objData.setCurrentHour((Integer)arrObj.get(5));
            objData.setCurrentDay((Integer)arrObj.get(6));
            objData.setCurrentMonth((Integer)arrObj.get(7));
            objData.setCurrentTime((Double)arrObj.get(8));
            objData.setTimeFactor((Double)arrObj.get(9));
            objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
            objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
            objData.setDescription(String.valueOf(arrObj.get(12)));
            objData.setInitialMoney((Double)arrObj.get(13));
            objData.setPercentageToWin(((BigDecimal)arrObj.get(14)).doubleValue());
            objData.setGameName(String.valueOf(arrObj.get(15)));
            objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
            objData.setIdPhase((Integer)arrObj.get(17));
            objData.setNextIdPhase((Integer)arrObj.get(18));
            objData.setIdTerrain((Integer)arrObj.get(19));
            objData.setObjective(String.valueOf(arrObj.get(20)));
            objData.setFlowImage(String.valueOf(arrObj.get(21)));
            objData.setAttemptNumbers((Integer)arrObj.get(22));
            objData.setYourBestScore(Params.moneySign + " " + Utils.formatValue2DecToString(((BigDecimal)(arrObj.get(23))).doubleValue()));
            objData.setGameBestScore(Params.moneySign + " " + Utils.formatValue2DecToString(((BigDecimal)(arrObj.get(24))).doubleValue()));
            objData.setOverhead(((BigDecimal)arrObj.get(25)).doubleValue());
            mapData.put(objData.getIdGame(), objData);
        }
        return mapData;
    }
    
    public Map<Integer, E_Game> SQLite_SelectGames_OfAPlayer(int idPlayer){
        Map<Integer, E_Game> mapData = new HashMap<Integer, E_Game>();
        ArrayList<ArrayList<Object>> arrArray;
        String sqlScript = "select " +
            "id_game, date_time, id_player, current_money, current_minute, current_hour, current_day, current_month, " +
            "current_game_time, time_factor, game_type, game_category, description, initial_money, percentage_to_win, " +
            "name, game_status, id_phase, next_id_phase, id_terrain, objective, flow_image, attempt_numbers, current_money, " +
            "(select current_money from t_game where game_type = 'CPU' and id_game = a.id_game) as gameGestScore, overhead " +
            "from t_game as a where id_player = "+ idPlayer +" order by id_phase;";
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,26);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Game();
            objData.setIdGame((Integer)arrObj.get(0));
            objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
            objData.setIdPlayer((Integer)arrObj.get(2));
            objData.setCurrentMoney((Double)arrObj.get(3));
            objData.setCurrentMinute((Integer)arrObj.get(4));
            objData.setCurrentHour((Integer)arrObj.get(5));
            objData.setCurrentDay((Integer)arrObj.get(6));
            objData.setCurrentMonth((Integer)arrObj.get(7));
            objData.setCurrentTime((Double)arrObj.get(8));
            objData.setTimeFactor((Double)arrObj.get(9));
            objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
            objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
            objData.setDescription(String.valueOf(arrObj.get(12)));
            objData.setInitialMoney((Double)arrObj.get(13));
            objData.setPercentageToWin((Double)arrObj.get(14));
            objData.setGameName(String.valueOf(arrObj.get(15)));
            objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
            objData.setIdPhase((Integer)arrObj.get(17));
            objData.setNextIdPhase((Integer)arrObj.get(18));
            objData.setIdTerrain((Integer)arrObj.get(19));
            objData.setObjective(String.valueOf(arrObj.get(20)));
            objData.setFlowImage(String.valueOf(arrObj.get(21)));
            objData.setAttemptNumbers((Integer)arrObj.get(22));
            objData.setYourBestScore(Params.moneySign + " " + Utils.formatValue2DecToString((Double)arrObj.get(23)));
            objData.setGameBestScore(Params.moneySign + " " + Utils.formatValue2DecToString((Double)arrObj.get(24)));
            objData.setOverhead((Double)arrObj.get(25));
            mapData.put(objData.getIdGame(), objData);
        }
        return mapData;
    }
    
    public E_Game SelectGames_OneGame(int idGame, int idPlayer){
        ArrayList<ArrayList<Object>> arrArray;
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Game_SelectAGame(" + idGame + "," + idPlayer + ")",24);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Game();
            objData.setIdGame((Integer)arrObj.get(0));
            objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
            objData.setIdPlayer((Integer)arrObj.get(2));
            objData.setCurrentMoney(((BigDecimal)(arrObj.get(3))).doubleValue());
            objData.setCurrentMinute((Integer)arrObj.get(4));
            objData.setCurrentHour((Integer)arrObj.get(5));
            objData.setCurrentDay((Integer)arrObj.get(6));
            objData.setCurrentMonth((Integer)arrObj.get(7));
            objData.setCurrentTime((Double)arrObj.get(8));
            objData.setTimeFactor((Double)arrObj.get(9));
            objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
            objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
            objData.setDescription(String.valueOf(arrObj.get(12)));
            objData.setInitialMoney((Double)arrObj.get(13));
            objData.setPercentageToWin(((BigDecimal)arrObj.get(14)).doubleValue());
            objData.setGameName(String.valueOf(arrObj.get(15)));
            objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
            objData.setIdPhase((Integer)arrObj.get(17));
            objData.setNextIdPhase((Integer)arrObj.get(18));
            objData.setIdTerrain((Integer)arrObj.get(19));
            objData.setObjective(String.valueOf(arrObj.get(20)));
            objData.setFlowImage(String.valueOf(arrObj.get(21)));
            objData.setAttemptNumbers((Integer)arrObj.get(22));
            objData.setOverhead(((BigDecimal)arrObj.get(23)).doubleValue());
        }
        return objData;
    }

    public E_Game SQLite_SelectGames_OneGame(int idGame, int idPlayer){
        ArrayList<ArrayList<Object>> arrArray;
        String sqlScript = "select " +
            "id_game, date_time, id_player, current_money, current_minute, current_hour, current_day, current_month, " +
            "current_game_time, time_factor, game_type, game_category, description, initial_money, percentage_to_win, " +
            "name, game_status, id_phase, next_id_phase, id_terrain, objective, flow_image, attempt_numbers, overhead " +
            "from t_game as a where id_game = "+ idGame +" and id_player = "+ idPlayer +" order by date_time desc;";
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,24);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Game();
            objData.setIdGame((Integer)arrObj.get(0));
            objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
            objData.setIdPlayer((Integer)arrObj.get(2));
            objData.setCurrentMoney((Double)arrObj.get(3));
            objData.setCurrentMinute((Integer)arrObj.get(4));
            objData.setCurrentHour((Integer)arrObj.get(5));
            objData.setCurrentDay((Integer)arrObj.get(6));
            objData.setCurrentMonth((Integer)arrObj.get(7));
            objData.setCurrentTime((Double)arrObj.get(8));
            objData.setTimeFactor((Double)arrObj.get(9));
            objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
            objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
            objData.setDescription(String.valueOf(arrObj.get(12)));
            objData.setInitialMoney((Double)arrObj.get(13));
            objData.setPercentageToWin((Double)arrObj.get(14));
            objData.setGameName(String.valueOf(arrObj.get(15)));
            objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
            objData.setIdPhase((Integer)arrObj.get(17));
            objData.setNextIdPhase((Integer)arrObj.get(18));
            objData.setIdTerrain((Integer)arrObj.get(19));
            objData.setObjective(String.valueOf(arrObj.get(20)));
            objData.setFlowImage(String.valueOf(arrObj.get(21)));
            objData.setAttemptNumbers((Integer)arrObj.get(22));
            objData.setOverhead((Double)arrObj.get(23));
        }
        return objData;
    }
    
    public ArrayList<E_Game> SelectGames_ByType(GameType gameType){
        ArrayList<ArrayList<Object>> arrArray;
        arrData = new ArrayList<E_Game>();
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("Game_SelectByType('" + gameType.toString() + "')",24);
        objData = null;
        if (arrArray == null) return arrData;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            objData = new E_Game();
            objData.setIdGame((Integer)arrObj.get(0));
            objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
            objData.setIdPlayer((Integer)arrObj.get(2));
            objData.setCurrentMoney(((BigDecimal)(arrObj.get(3))).doubleValue());
            objData.setCurrentMinute((Integer)arrObj.get(4));
            objData.setCurrentHour((Integer)arrObj.get(5));
            objData.setCurrentDay((Integer)arrObj.get(6));
            objData.setCurrentMonth((Integer)arrObj.get(7));
            objData.setCurrentTime((Double)arrObj.get(8));
            objData.setTimeFactor((Double)arrObj.get(9));
            objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
            objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
            objData.setDescription(String.valueOf(arrObj.get(12)));
            objData.setInitialMoney((Double)arrObj.get(13));
            objData.setPercentageToWin(((BigDecimal)arrObj.get(14)).doubleValue());
            objData.setGameName(String.valueOf(arrObj.get(15)));
            objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
            objData.setIdPhase((Integer)arrObj.get(17));
            objData.setNextIdPhase((Integer)arrObj.get(18));
            objData.setIdTerrain((Integer)arrObj.get(19));
            objData.setObjective(String.valueOf(arrObj.get(20)));
            objData.setFlowImage(String.valueOf(arrObj.get(21)));
            objData.setAttemptNumbers((Integer)arrObj.get(22));
            objData.setOverhead(((BigDecimal)arrObj.get(23)).doubleValue());
            arrData.add(objData);
        }
        return arrData;
    }
    
    public ArrayList<E_Game> SQLite_SelectGames_ByType(GameType gameType){
        ArrayList<ArrayList<Object>> arrArray;
        arrData = new ArrayList<E_Game>();
        String sqlScript = "select " +
            "id_game, date_time, id_player, current_money, current_minute, current_hour, current_day, current_month, " +
            "current_game_time, time_factor, game_type, game_category, description, initial_money, percentage_to_win, " +
            "name, game_status, id_phase, next_id_phase, id_terrain, objective, flow_image, attempt_numbers, overhead " +
            "from t_game where game_type = '"+ gameType +"' order by game_category, id_game;";
        arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,24);
        objData = null;
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Game();
                objData.setIdGame((Integer)arrObj.get(0));
                objData.setDateTime(arrObj.get(1) == null ? 0 : (Integer)arrObj.get(1));
                objData.setIdPlayer((Integer)arrObj.get(2));
                objData.setCurrentMoney((Double)arrObj.get(3));
                objData.setCurrentMinute((Integer)arrObj.get(4));
                objData.setCurrentHour((Integer)arrObj.get(5));
                objData.setCurrentDay((Integer)arrObj.get(6));
                objData.setCurrentMonth((Integer)arrObj.get(7));
                objData.setCurrentTime((Double)arrObj.get(8));
                objData.setTimeFactor((Double)arrObj.get(9));
                objData.setGameType(GameType.valueOf(String.valueOf(arrObj.get(10))));
                objData.setGameCategory(GameCategory.valueOf(String.valueOf(arrObj.get(11))));
                objData.setDescription(String.valueOf(arrObj.get(12)));
                objData.setInitialMoney((Double)arrObj.get(13));
                objData.setPercentageToWin((Double)arrObj.get(14));
                objData.setGameName(String.valueOf(arrObj.get(15)));
                objData.setGameStatus(GameStatus.valueOf(String.valueOf(arrObj.get(16))));
                objData.setIdPhase((Integer)arrObj.get(17));
                objData.setNextIdPhase((Integer)arrObj.get(18));
                objData.setIdTerrain((Integer)arrObj.get(19));
                objData.setObjective(String.valueOf(arrObj.get(20)));
                objData.setFlowImage(String.valueOf(arrObj.get(21)));
                objData.setAttemptNumbers((Integer)arrObj.get(22));
                objData.setOverhead((Double)arrObj.get(23));
                arrData.add(objData);
        }
        return arrData;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Game_SelectByCPUGame(" + idGame + ")",24);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(4))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(5))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(6))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(7))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(8))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(9))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(10))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(11))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(12))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(13))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(14))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(15))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(16))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(17))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(18))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(19))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(20))));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(21))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(22))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(23))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("game_type");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
                filtersValue.add(new Pair("Integer",String.valueOf(idGame)));
                filtersValue.add(new Pair("String",String.valueOf(arrObj.get(10))));
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableName, arrColumns, sqliteValue, filters, filtersValue));
            }
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
    
    public void updateLocalDBByPlayer(int idPlayer, ProgressBarController pbc){
        ArrayList<E_Game> playerGames = SelectGames_OfAPlayer(idPlayer);
        E_Game playerGame = null;
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<playerGames.size(); i++){
            playerGame = playerGames.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getIdGame())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getDateTime())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getIdPlayer())));
            sqliteValue.add(new Pair("Float",String.valueOf(playerGame.getCurrentMoney())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getCurrentMinute())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getCurrentHour())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getCurrentDay())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getCurrentMonth())));
            sqliteValue.add(new Pair("Float",String.valueOf(playerGame.getCurrentTime())));
            sqliteValue.add(new Pair("Float",String.valueOf(playerGame.getTimeFactor())));
            sqliteValue.add(new Pair("String",playerGame.getGameType().toString()));
            sqliteValue.add(new Pair("String",playerGame.getGameCategory().toString()));
            sqliteValue.add(new Pair("String",playerGame.getDescription()));
            sqliteValue.add(new Pair("Float",String.valueOf(playerGame.getInitialMoney())));
            sqliteValue.add(new Pair("Float",String.valueOf(playerGame.getPercentageToWin())));
            sqliteValue.add(new Pair("String",playerGame.getGameName()));
            sqliteValue.add(new Pair("String",playerGame.getGameStatus().toString()));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getIdPhase())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getNextIdPhase())));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getIdTerrain())));
            sqliteValue.add(new Pair("String",playerGame.getObjective()));
            sqliteValue.add(new Pair("String",playerGame.getFlowImage()));
            sqliteValue.add(new Pair("Integer",String.valueOf(playerGame.getAttemptNumbers())));
            sqliteValue.add(new Pair("Float",String.valueOf(playerGame.getOverhead())));
            SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(playerGames.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
}
