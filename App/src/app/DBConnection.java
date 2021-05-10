/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.ReadandWrite;

/**
 *
 * @author rohit
 */
public class DBConnection {

    private static String[] getConnection() {
        ReadandWrite env = new ReadandWrite("./.env");
        return env.read().split("\n", 5);
    }

    public static ResultSet getResult(String S){
        try{
            String[] connDetails = getConnection();
         //       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+connDetails[0]+":"+connDetails[1]+"/"+connDetails[2],connDetails[3],connDetails[4]);
            Statement stat = conn.createStatement();
            ResultSet RSet = stat.executeQuery(S);
            return RSet;
        }catch(SQLException se){
            se.printStackTrace();
        }
        return null;
    }
    
    public static void InsertRow(String S){
        try{
            String[] connDetails = getConnection();
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+connDetails[0]+":"+connDetails[1]+"/"+connDetails[2],connDetails[3],connDetails[4]);
            Statement stat = conn.createStatement();
            stat.executeUpdate(S);
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
