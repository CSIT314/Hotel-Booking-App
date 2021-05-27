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
        
         ResultSet RSet = getResult("SELECT * FROM booking_info WHERE username=\""+username+"\"");
         model.setRowCount(0);
              
         try{
         while(RSet.next()){

          int Booking_ID = RSet.getInt("Booking_ID");
          int rooms_confirmed = RSet.getInt("rooms_confirmed");
          int rooms_waitlist = RSet.getInt("rooms_waitlist");
          Date Date_In = RSet.getDate("Date_In");
          Date Date_Out = RSet.getDate("Date_Out");
          int status = RSet.getInt("Status");
          int Hotel_ID = RSet.getInt("Hotel_ID");
          String stat = null;
          if(status==0){ stat="Confirmed";}
          else if(status==1){stat="Waitlisted"; }
          else if(status==2){stat="Cancelled"; }
          Object row[] = {Booking_ID,rooms_confirmed,rooms_waitlist,Date_In,Date_Out,stat,Hotel_ID};
          model.addRow(row);
                
         }
         assertNotNull("Can't access Booking_ID for CheckBookingsActionPerformedTest ",Booking_ID);
         assertNotNull("Can't access row for CheckBookingsActionPerformedTest ",row );
         
         Assert.assertNotNull(model);
         
         System.out.println("CheckBookingsActionPerformedTest Booking_ID successful");
         System.out.println("CheckBookingsActionPerformedTest addRow successful");
         }
         catch (SQLException ex) {
             ex.printStackTrace();
        }
    
    }
    
}
