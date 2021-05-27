package app;

import app.UserProfile;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import static app.DBConnection.InsertRow;
import static app.Utilities.*;
import static app.DBConnection.getResult;
import java.awt.Dimension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARWA
 */
public class UserProfileTest {
    
    public UserProfileTest() {
       
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
         this.testSubject = new UserProfile();
    }
    
    @After
    public void tearDown() {
        testSubject = null; 
    }
   
     String username;
     DefaultTableModel model;
     UserProfile frame ;
     model = (DefaultTableModel) Bookings.getModel();
    
    @Test
    public void CheckBookingsActionPerformedTest (){
         System.out.println(" Testing CheckBookingsActionPerforme()");
        
    
    }
    
}
