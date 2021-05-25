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
    
    @Test
    public void CancelActionPerformedTest (){
         System.out.println(" Testing CancelActionPerformed()");
         
          try{
              int rowIndex = Bookings.getSelectedRow();
              int bookid= (int) model.getValueAt(rowIndex, 0);
              int hid=(int) model.getValueAt(rowIndex,6);
              Date datein= model.getValueAt(rowIndex,3);
              Date dateout= model.getValueAt(rowIndex,4);
             InsertRow("UPDATE booking_info SET Status=2 WHERE Booking_ID=\""+bookid+"\";");
             
             
             assertNotNull("Can't access Booking_ID for CancelActionPerformedTest ",bookid );       
             System.out.println("CancelActionPerformedTest Booking_ID successful");
         }
         catch (SQLException ex) {
             System.out.println(e);
        }
    }
   
    
    @Test
    public void ModifyActionPerformedTest (){
         System.out.println(" Testing ModifyActionPerformed()");  
         
         try {
             int rowIndex = Bookings.getSelectedRow();
             
             int bookingID = (int) model.getValueAt(rowIndex, 0);
             java.sql.Date datein=(java.sql.Date) model.getValueAt(rowIndex,3);
             java.sql.Date dateout=(java.sql.Date) model.getValueAt(rowIndex,4);
             ResultSet rs = getResult("SELECT CURDATE()");
             rs.next();
            
              assertNotNull("Can't access Booking_ID for ModifyActionPerformedTest ",Booking_ID);
              assertNotNull("Can't access row SQL for ModifyActionPerformedTest ",ResultSet );
         
              Assert.assertNotNull(model);
         
             System.out.println("ModifyActionPerformedTest Booking_ID successful");
             System.out.println("ModifyActionPerformedTest row SQL successful");
        } 
         
         catch (SQLException ex) {
            Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    @Test
    public void RatingOptionActionPerformedTest (){
         System.out.println(" Testing RatingOptionActionPerformed()");    
         try{
            int rowIndex = Bookings.getSelectedRow();
            int bookingID = (int) model.getValueAt(rowIndex, 0);
            int rating = Integer.parseInt(Rating.getText());
            ResultSet rs = getResult("SELECT Hotel_ID from booking_info WHERE Booking_ID = " + bookingID + ";");            
            rs.next();
            int hid = rs.getInt("Hotel_ID");
            String query = "SELECT MAX(review_id) FROM hotel_reviews;";
            rs = getResult(query);
            rs.next();
            int cid = (int) rs.getInt("MAX(review_id)") + 1;
            query = "INSERT INTO hotel_reviews VALUES(" + cid + ", \"" + username + "\", " + hid + ", " + rating +");"; 
            InsertRow(query);  

            assertNotNull("Can't access Booking_ID for RatingOptionActionPerformedTest ",Booking_ID);
            assertNotNull("Can't access row SQL for RatingOptionActionPerformedTest ",query );
         
            Assert.assertNotNull(model);
         
            System.out.println("RatingOptionActionPerformedTest Booking_ID successful");
            System.out.println("RatingOptionActionPerformedTest row SQL successful");
         }
         catch (SQLException ex) {
            Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
