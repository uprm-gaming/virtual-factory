package com.virtualfactory.data;

import com.virtualfactory.data.sqlite.SQLiteConn;
import com.virtualfactory.data.sqlite.SQLiteUtilities;
import com.virtualfactory.entity.E_AssemblyDetails;
import com.virtualfactory.graphic.nifty.ProgressBarController;
import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import java.util.ArrayList;

/**
 *
 * 
 */
public class D_AssemblyDetails {
    //FIXME: Need an unique key to use java.util.Map instead of java.util.Arraylist. Two 
    // integers are used as primary key in database.(for one game)
    private E_AssemblyDetails objData;
    private ArrayList<E_AssemblyDetails> arrData;
    private String tableName = "t_assembly_details";
    private ArrayList<String> arrColumns;
    
    public D_AssemblyDetails(){
        arrColumns = new ArrayList<>();
        arrColumns.add("id_output_part");
        arrColumns.add("id_input_part");
        arrColumns.add("id_game");
        arrColumns.add("quantity");
        arrColumns.add("assembly_description");
    }
    
    public ArrayList<E_AssemblyDetails> Select(int idGame, int idPart, boolean isSQLite){
        ArrayList<ArrayList<Object>> arrArray;
        arrData = new ArrayList<>();
        if (isSQLite){
            String sqlScript = "select " +
            "        id_output_part, id_input_part,  " +
            "        quantity, assembly_description " +
            "    from " +
            "        t_assembly_details " +
            "    where " +
            "        id_game = " + idGame + " and " +
            "        id_output_part = " + idPart + ";";
            arrArray = SQLiteConn.getInstance().ExecuteSP_Data(sqlScript,4);
        }else{
            arrArray = MySqlConn.getInstance().ExecuteSP_Data("AssemblyDetails_Select(" + idGame +"," + idPart + ")",4);
        }
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_AssemblyDetails();
                objData.setIdOutputPart((Integer)arrObj.get(0));
                objData.setIdInputPart((Integer)arrObj.get(1));
                objData.setQuantity((Integer)arrObj.get(2));
                objData.setAssemblyDescription(String.valueOf(arrObj.get(3)));
                arrData.add(objData);
        }
        return arrData;
    }
    
    public boolean Insert(int idGame, E_AssemblyDetails param){
        boolean blnResult = MySqlConn.getInstance().ExecuteSP("AssemblyDetails_Insert(" +
                idGame + "," +
                param.getIdOutputPart() + "," +
                param.getIdInputPart() + "," +
                param.getQuantity() + ",'" +
                param.getAssemblyDescription() + "')");
        return blnResult;
    }
    
    public ArrayList<E_AssemblyDetails> SelectByGame(int idGame){
        ArrayList<ArrayList<Object>> arrArray;
        arrData = new ArrayList<>();
        arrArray = MySqlConn.getInstance().ExecuteSP_Data("AssemblyDetails_SelectByGame(" + idGame + ")",4);
        for (int i=0; i<arrArray.size(); i++){
                ArrayList<Object> arrObj = arrArray.get(i);
                objData = new E_AssemblyDetails();
                objData.setIdOutputPart((Integer)arrObj.get(0));
                objData.setIdInputPart((Integer)arrObj.get(1));
                objData.setQuantity((Integer)arrObj.get(2));
                objData.setAssemblyDescription(String.valueOf(arrObj.get(3)));
                arrData.add(objData);
        }
        return arrData;
    }
    
    public void updateLocalDB(int idGame, Actions actionToDo, ProgressBarController pbc){
        ArrayList<E_AssemblyDetails> mysqlData = SelectByGame(idGame);
        ArrayList<Pair<String,String>> sqliteValue;
        int i=0;
        for (E_AssemblyDetails temp : mysqlData){
            sqliteValue = new ArrayList<>();
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdOutputPart())));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getIdInputPart())));
            sqliteValue.add(new Pair("Integer",String.valueOf(idGame)));
            sqliteValue.add(new Pair("Integer",String.valueOf(temp.getQuantity())));
            sqliteValue.add(new Pair("String",temp.getAssemblyDescription()));
            if (actionToDo.equals(Actions.INSERT)){
                SQLiteConn.getInstance().ExecuteSP(SQLiteUtilities.getInsert(tableName, arrColumns, sqliteValue));
            }else
            if (actionToDo.equals(Actions.UPDATE)){
                ArrayList<String> filters = new ArrayList<>();
                filters.add("id_output_part");
                filters.add("id_input_part");
                filters.add("id_game");
                ArrayList<Pair<String,String>> filtersValue = new ArrayList<>();
                filtersValue.add(new Pair("Integer",String.valueOf(temp.getIdOutputPart())));
                filtersValue.add(new Pair("Integer",String.valueOf(temp.getIdInputPart())));
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
