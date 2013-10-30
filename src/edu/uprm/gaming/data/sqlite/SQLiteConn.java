/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.data.sqlite;
import edu.uprm.gaming.utils.Params;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
/**
 *
 * @author David
 */
public class SQLiteConn {
    private static SQLiteConn instance;
    private Connection conn;
    
    private SQLiteConn(){
        
    }
    
    public static synchronized SQLiteConn getInstance(){
        if (instance == null)
            instance = new SQLiteConn();
        return instance;
    }
    
    private void OpenConnection(){
        conn = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:resources/gaming.db");
//            System.out.println(System.getProperty("user.home"));
//            System.out.println("SQLite - Database connection established");
        }
        catch (Exception e)
        {
            System.err.println("SQLite - Error: Cannot connect to database server. " + e.getMessage());
            Params.errorDatabaseMessage = "SQLite - Error: Cannot connect to database server.";
        }
    }

    private void CloseConnection(){
        if (conn != null)
        {
            try
            {
                conn.close();
//                System.out.println("SQLite - Database connection terminated");
            }
            catch (Exception e) 
            {
                System.err.println("SQLite - Error closing connection. " + e.getMessage());
            }
        }
    }
    
    public boolean ExecuteSP(String strSP){
        Statement s;
        boolean result = false;
        OpenConnection();
        if (conn == null) return result;
        try {
                s = conn.createStatement();
                s.execute(strSP.trim());
                System.out.println("SQLite - " + strSP.trim());
                s.close();
                result = true;
        } catch (SQLException e) {
            Params.errorDatabaseMessage = "SQLite - Error: Cannot connect to database server.";
            System.out.println("SQLite - ERROR: " + e.getMessage());
        }finally{
            CloseConnection();
        }        
        return result;
    }

    public ArrayList<ArrayList<Object>> ExecuteSP_Data(String strSP, int intNoCols){
        Statement s;
        OpenConnection();
        ArrayList<ArrayList<Object>> arrArray = new ArrayList<ArrayList<Object>>();
        if (conn == null) return arrArray;
        try {
                s = conn.createStatement();
                s.execute(strSP.trim());
                System.out.println("SQLite - " + strSP.trim());
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
                Params.errorDatabaseMessage = "SQLite - Error: Cannot connect to database server.";
                System.out.println("SQLite - ERROR: " + e.getMessage());
        } finally{
            CloseConnection();    
        }        
        return arrArray;
    }
}
