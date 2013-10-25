/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.data;
import edu.uprm.gaming.data.sqlite.SQLiteConn;
import edu.uprm.gaming.data.sqlite.SQLiteUtilities;
import edu.uprm.gaming.entity.E_Skill;
import edu.uprm.gaming.graphic.nifty.ProgressBarController;
import edu.uprm.gaming.utils.Actions;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.Params;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 
 */
public class D_Skill {
    private E_Skill objData;
    //private ArrayList<E_Skill> arrData;
    private Map<Integer, E_Skill> mapData;
    private String tableName = "t_skill";
    private ArrayList<String> arrColumns;
    private String tableNameActivity = "t_skill_activity";
    private ArrayList<String> arrColumnsActivity;
    private String tableNameOperator = "t_skill_operator";
    private ArrayList<String> arrColumnsOperator;
    
    public D_Skill(){
        arrColumns = new ArrayList<String>();
        arrColumns.add("id_skill");
        arrColumns.add("id_game");
        arrColumns.add("skill_description");
        
        arrColumnsActivity = new ArrayList<String>();
        arrColumnsActivity.add("id_activity");
        arrColumnsActivity.add("id_skill");
        arrColumnsActivity.add("id_game");
        
        arrColumnsOperator = new ArrayList<String>();
        arrColumnsOperator.add("id_skill");
        arrColumnsOperator.add("id_operator");
        arrColumnsOperator.add("id_game");
        arrColumnsOperator.add("efficiency");
        arrColumnsOperator.add("dist_param1");
        arrColumnsOperator.add("dist_param2");
    }
    
    public Map<Integer, E_Skill> Select(int idGame, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        mapData = new HashMap<Integer, E_Skill>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_skill, skill_description " +
            "    from " +
            "        t_skill " +
            "    where " +
            "        id_game = " + idGame + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,2);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("Skill_Select(" + idGame + ")",2);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_Skill();
                objData.setIdSkill((Integer)arrObj.get(0));
                objData.setSkillDescription(String.valueOf(arrObj.get(1)));
                mapData.put(objData.getIdSkill(), objData);
        }
        return mapData;
    }
    
    public ArrayList<Integer> SelectSkillsPerActivity(int idGame, int idActivity, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        ArrayList<Integer> arrTemp = new ArrayList<Integer>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_skill " +
            "    from " +
            "        t_skill_activity " +
            "    where " +
            "        " + idGame + " = id_game and " +
            "        " + idActivity + " = id_activity;";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,1);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("SkillsPerActivity_Select(" + idGame + "," + idActivity + ")",1);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                arrTemp.add((Integer)arrObj.get(0));
        }
        return arrTemp;
    }
    
    public boolean Insert(int idGame, E_Skill param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("Skill_Insert(" +
                param.getIdSkill() + "," +
                idGame + ",'" +
                param.getSkillDescription() + "')");
//        if (blnResult){
//            for (int i=0; i<param.getArrTypeOperation().size(); i++)
//                InsertTypeOperationPerSkill(idGame,param.getIdSkill(),param.getArrTypeOperation().get(i));
//        }
        return blnResult;
    }
    
    public boolean InsertSkillsPerActivity(int idGame, int idActivity, int idSkill){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("SkillsPerActivity_Insert(" +
                idGame + "," +
                idSkill + "," +
                idActivity + ")");
        return blnResult;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("Skill_Select(" + idGame + ")",2);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("String",String.valueOf(arrObj.get(1))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_skill");
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
    
    public void updateLocalDB_SkillActivity(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("SkillsActivity_SelectByGame(" + idGame + ")",2);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));            
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableNameActivity, arrColumnsActivity, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                
            }
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
    
    public void updateLocalDB_SkillOperator(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<ArrayList<Object>> arrArray = MySqlConn.getInstance().ExecuteSP_Data("SkillsOperator_SelectByGame(" + idGame + ")",5);
        ArrayList<Pair<String,String>> sqliteValue;
        for (int i=0; i<arrArray.size(); i++){
            ArrayList<Object> arrObj = arrArray.get(i);
            sqliteValue = new ArrayList<Pair<String, String>>();
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
            sqliteValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(2))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(3))));
            sqliteValue.add(new Pair("Float",String.valueOf(arrObj.get(4))));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableNameOperator, arrColumnsOperator, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<String>();
                filters.add("id_game");
                filters.add("id_skill");
                filters.add("id_operator");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<Pair<String, String>>();
                filtersValue.add(new Pair("Integer",String.valueOf(idGame)));
                filtersValue.add(new Pair("Integer",String.valueOf(arrObj.get(0))));
                filtersValue.add(new Pair("Integer",String.valueOf(arrObj.get(1))));
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getUpdate(tableNameOperator, arrColumnsOperator, sqliteValue, filters, filtersValue));
            }
            pbc.setProgress(Params.percentageLoading + (i+1)*Params.percentageQuote/(arrArray.size()*1.0f));
        }
        Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
        pbc.setProgress(Params.percentageLoading);
    }
}
