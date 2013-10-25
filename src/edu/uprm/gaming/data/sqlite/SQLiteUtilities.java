/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.data.sqlite;

import edu.uprm.gaming.utils.Pair;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class SQLiteUtilities {
    
    public static String getUpdate(String table, ArrayList<String> columns, ArrayList<Pair<String, String>> values,
            ArrayList<String> filters, ArrayList<Pair<String, String>> filterValues){
        String newScript = "";
        newScript += "UPDATE '" + table + "' SET ";
        for (int i=0; i<columns.size(); i++){
            if (i > 0)
                newScript += ",";
            newScript += columns.get(i) + " = ";
            if (values.get(i).getFirst().toUpperCase().equals("STRING"))
                newScript += "'" + values.get(i).getSecond() + "'";
            else
                newScript += values.get(i).getSecond();
        }
        if (filters.size() > 0){
            newScript += " WHERE ";
            for (int i=0; i<filters.size(); i++){
                if (i > 0)
                    newScript += " AND ";
                newScript += filters.get(i) + " = ";
                if (filterValues.get(i).getFirst().toUpperCase().equals("STRING"))
                    newScript += "'" + filterValues.get(i).getSecond() + "'";
                else
                    newScript += filterValues.get(i).getSecond();
            }
        }
        newScript += ";";
        return newScript;
    }
    
    public static String getInsert(String table, ArrayList<String> columns, ArrayList<Pair<String, String>> values){
        String newScript = "";
        newScript += "INSERT INTO '" + table + "' (";
        for (int i=0; i<columns.size(); i++){
            if (i > 0)
                newScript += ",";
            newScript += columns.get(i);
        }
        newScript += ") VALUES (";
        for (int i=0; i<values.size(); i++){
            if (i > 0)
                newScript += ",";
            if (values.get(i).getFirst().toUpperCase().equals("STRING"))
                newScript += "'" + values.get(i).getSecond() + "'";
            else
                newScript += values.get(i).getSecond();
        }
        newScript += ");";
        return newScript;
    }
    
    public static String getSelect(String initialScript, ArrayList<Pair<String, String>> values){
        for (int i=0; i<values.size(); i++){
            if (values.get(i).getFirst().toUpperCase().equals("STRING"))
                initialScript = initialScript.replaceFirst("?", "'" + values.get(i).getSecond() + "'");
            else
                initialScript = initialScript.replaceFirst("?", values.get(i).getSecond());
        }
        return initialScript;
    }
}
