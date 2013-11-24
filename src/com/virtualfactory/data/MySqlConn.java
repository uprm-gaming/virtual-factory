/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.data;
import com.virtualfactory.utils.Params;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author David...
 */
public class MySqlConn {
    private static MySqlConn instance;
    private Connection conn;
    private String strUser = "";
    private String strPass = "";
    private String strServer = "";
    private String strDB = "";
    
    public void MySqlConn(){
        
    }
    
    private MySqlConn(){
        if (Params.selectDatabase.equals("localhost")){
            strUser = Params.localhostUser;
            strPass = Params.localhostPass;
            strServer = Params.localhostServer;
            strDB = Params.localhostDB;
        }else{
            strUser = Params.remoteUser;
            strPass = Params.remotePass;
            strServer = Params.remoteServer;
            strDB = Params.remoteDB;
        }
    }
    
    public static synchronized MySqlConn getInstance(){
        if (instance == null)
            instance = new MySqlConn();
        return instance;
    }

    private void OpenConnection(){
        conn = null;
        try
        {
            String userName = strUser;
            String password = strPass;
            String url = "jdbc:mysql://" + strServer + "/" + strDB;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
//            System.out.println("MySQL - Database connection established");
        }
        catch (Exception e)
        {
            System.err.println("MySQL - Error: Cannot connect to database server. " + e.getMessage());
            Params.errorDatabaseMessage = "MySQL - Error: Cannot connect to database server.";
            Params.existsInternetConnection = false;
        }
    }

    private void CloseConnection(){
        if (conn != null)
        {
            try
            {
                conn.close();
//                System.out.println("MySQL - Database connection terminated");
            }
            catch (Exception e) 
            {
                System.err.println("MySQL - Error closing connection. " + e.getMessage());
                Params.existsInternetConnection = false;
            }
        }
    }

    public synchronized boolean ExecuteSP(String strSP){
        if (!Params.existsInternetConnection){
            Params.errorDatabaseMessage = "MySQL - Error: There is no Internet connection.";
            return false;
        }
        Statement s;
        boolean result = false;
        if (!Params.DEBUG_ON) {
            OpenConnection();
        } else {
            conn = null;
        }
        if (conn == null) {
            return result;
        }
        try {
            s = conn.createStatement();
                s.execute("call " + strDB + "." + strSP.trim());
                System.out.println("MySQL - " + strSP.trim());
                s.close();
                result = true;
        } catch (SQLException e) {
            //e.printStackTrace();
            Params.existsInternetConnection = false;
            boolean ExecuteSP = this.ExecuteSP("Log_Insert('Proc: " + strSP.replace("'", "") + " - Error: " + e.getMessage().replace("'", "") + "')");//"´"
        }finally{
            CloseConnection();
        }        
        return result;
    }

    public synchronized ArrayList<ArrayList<Object>> ExecuteSP_Data(String strSP, int intNoCols){
        if (!Params.existsInternetConnection){
            Params.errorDatabaseMessage = "MySQL - Error: There is no Internet connection.";
            return null;
        }
        Statement s;
        if (!Params.DEBUG_ON) {
            OpenConnection();
        } else {
            conn = null;
        }

        ArrayList<ArrayList<Object>> arrArray = new ArrayList<ArrayList<Object>>();
        if (conn == null) return arrArray;
        try {
                s = conn.createStatement();
                s.execute("call " + strDB + "." + strSP.trim());
                System.out.println("MySQL - " + strSP.trim());
                ResultSet rs = s.getResultSet();
                ArrayList<Object> arrObj;
                while (rs.next())
                {
                        arrObj = new ArrayList<Object>();
                        for (int i=1; i<=intNoCols; i++){
                                arrObj.add(rs.getObject(i));
                        }
                        arrArray.add(arrObj);
                }
                rs.close();
                s.close();
        } catch (SQLException e) {
            Params.existsInternetConnection = false;
            this.ExecuteSP("Log_Insert('Proc: " + strSP + " - Error: " + e.getMessage().replace("'", "´") + "')");
        } finally{
            CloseConnection();    
        }        
        return arrArray;
    }
}
