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

/**
 *
 * @author rohit
 */
public class DBConnection {
    public static ResultSet getResult(String S){
        try{
                Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking_app?zeroDateTimeBehavior=convertToNull","root","test");
                Statement stat = conn.createStatement();
            ResultSet RSet = stat.executeQuery(S);
            return RSet;
        }catch(SQLException se){
            se.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
