
package app;
import app.UserProfile;
import javax.swing.table.DefaultTableModel;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;
import static app.DBConnection.InsertRow;
import static app.Utilities.*;
import static app.DBConnection.getResult;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JTable;

/**
 *
 * @author ARWA
 */

public class UserProfileTest {
   
 // class variables
    String username;
    DefaultTableModel model;
    JTable Bookings ;

    public UserProfileTest() {  }


    @Before
    public void setUp() {
       // this.testSubject = new UserProfile();  
       }
    
    @After
    public void tearDown() { 
      //  testSubject = null; 
     }
   

    // random string generation for username
    private String generateAlphaNumeric(int topMargin) {
        int leftLimit = 48 ; // numeral '0'
        int rightLimit = 122 ; // letter 'z'
        Random random = new Random();
        int targetStringLength = topMargin;
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;   }


     private Date randDate() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        int year = 2021;
        gc.set(gc.YEAR, year);
        int month = 5 + (int) (Math.round(Math.random()) * (6 - 5));
        gc.set(gc.MONTH, month);
        int dayOfMonth = 1 + (int) Math.round(Math.random() * (1 - gc.getActualMaximum(gc.DAY_OF_MONTH)));
        gc.set(gc.DAY_OF_MONTH, dayOfMonth);
        String rdate = formater.format(gc.getTime());
        java.sql.Date randdate = new java.sql.Date(gc.getTimeInMillis());
        return randdate;  }
     
    
    @Test
   public void CheckBookingsActionPerformedTest (){

         System.out.println(" Testing CheckBookingsActionPerforme()");
         UserProfile frame;
         JButton CheckBookings; 
         int num = 10;
         for (int i = 0; i < num; i++) {
            // generate random username
            username = generateAlphaNumeric(40);
            frame = new UserProfile(username);
            //get access to  button
            //CheckBookings = (JButton) TestUtils.getChildNamed(frame, "CheckBookings");
            Bookings = (JTable)TestUtils.getChildNamed(frame, "Bookings");

            //assertNotNull("CheckBookings inaccessible", CheckBookings);
            assertNotNull("Frame component inaccessible", frame);
            // assertNotNull("Bookings JTable component inaccessible", Bookings);
           
            // to see if each UI component is accessible
            System.out.println("Frame,Bookings and CheckBookings Button found");

            System.out.print("TEST CASE: " + i + "\n");
            //randomised username 
            System.out.println("USERNAME: " + username);
            
            // integrating random dates into datepicker 
            java.sql.Date dateIn = randDate();
            java.sql.Date dateOut = randDate();
            frame.setVisible(true);

            // testing if values of username were correct and implemented correctly in frame variable
            assertEquals(username, frame.username);
            
            // testing the code of button functionality
            try {
                java.sql.Date inDate, outDate;
                try {
                    //inDate = dateIn.getDate();
                    //outDate = dateOut.getDate();
                    // testing if new checkIn and checkOut date are not null 
                    assertNotNull("No Booking Date checkIn", dateIn);
                    assertNotNull("No booking Date checkOut", dateOut);
                    System.out.print("DATE IN: " + dateIn.getDate() + "\n");
                    System.out.print("DATE OUT: " + dateOut.getDate() + "\n");
                    
                } 
                catch (java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Please fill in dates properly", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // check username are not null 
                assertNotNull("Username inaccessible", username);
             
                ResultSet RSet = getResult("SELECT * FROM booking_info WHERE username=\""+username+"\"");
                model = (DefaultTableModel) Bookings.getModel();
                model.setRowCount(0);
                if (RSet.next()) {
                    int bid = RSet.getInt("Booking_ID");
                    int roomconf = RSet.getInt("rooms_confirmed");
                    int roomwait = RSet.getInt("rooms_waitlist");
                    java.sql.Date datein=RSet.getDate("Date_In");
                    java.sql.Date dateout=RSet.getDate("Date_Out");
                    int status=RSet.getInt("Status");
                    int hid=RSet.getInt("Hotel_ID");
                    String stat = null;
                  if(status==0) {
                    stat="Confirmed"; }
                  else if(status==1) {
                    stat="Waitlisted"; }
                  else if(status==2) {
                    stat="Cancelled"; }
                    
                  Object row[] = {bid,roomconf,roomwait,datein,dateout,stat,hid};
                  model.addRow(row);
                 //Bookings.setRowSelectionAllowed(true);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();  }
        
         System.out.println("CheckBookingsActionPerformedTest Booking_ID successful");
         System.out.println("CheckBookingsActionPerformedTest addRow successful");
        
        }
    
    }
    
    @Test
    public void CancelActionPerformedTest (){
         System.out.println(" Testing CancelActionPerformed()");

         UserProfile frame ;
         JButton Cancel; 
         int num = 10;

         for (int i = 0; i < num; i++) {
            // generate random username
            username = generateAlphaNumeric(40);
            frame = new UserProfile(username);
            //get access to  button
            //Cancel = (JButton) TestUtils.getChildNamed(frame, "Cancel");
            Bookings = (JTable)TestUtils.getChildNamed(frame, "Bookings");

            //assertNotNull("Cancel Action Performed inaccessible", Cancel);
            assertNotNull("Frame component inaccessible", frame);
            
            // to see if each UI component is accessible
            System.out.println("Frame, Button found");
            System.out.print("TEST CASE: " + i + "\n");
            //randomised username 
            System.out.println("USERNAME: " + username);
            
            // integrating random dates into datepicker 
            Date dateIn = randDate();
            Date dateOut = randDate();
            frame.setVisible(true);
            // testing if values of username were correct and implemented correctly in frame variable
            assertEquals(username, frame.username);
          
            try{
                 // Change Status to Cancelled    
                 model = (DefaultTableModel) Bookings.getModel();
                int rowIndex = Bookings.getSelectedRow();
                int bookid= (int) model.getValueAt(rowIndex, 0);
                int hid=(int) model.getValueAt(rowIndex,6);
                java.sql.Date datein=(java.sql.Date) model.getValueAt(rowIndex,3);
                java.sql.Date dateout=(java.sql.Date) model.getValueAt(rowIndex,4);
               
                 InsertRow("UPDATE booking_info SET Status=2 WHERE Booking_ID=\""+bookid+"\";");
                 ResultSet rs = getResult("SELECT CURDATE()");
                 rs.next();
                 Date today = rs.getDate("CURDATE()");
                 int setFlag = 0;
                 if(getDateDifference(datein, today) < 3){
                     setFlag = 1;  }
                String query = "SELECT * FROM booking_info where Status=1 AND Hotel_ID = " + hid +  " ORDER BY Booking_ID ASC";
                ResultSet rs2=getResult(query);
                
                while(rs2.next()){
                    int bid = rs2.getInt("Booking_ID");
                    int wlrooms=rs2.getInt("rooms_waitlist");
                    int cnfrooms = rs2.getInt("rooms_confirmed");
                    Date date_in = rs2.getDate("Date_In");
                    Date date_out = rs2.getDate("Date_Out");
                    try{
                        rs = getResult("SELECT CURDATE();");
                        rs.next();            
                        today = rs.getDate("CURDATE()");  }
                    catch(SQLException e){
                        e.printStackTrace();  }
                        
                    int rooms_available = (int) (checkAvailability(hid, date_in, date_out));
                    if(rooms_available > 0){
                        if(rooms_available > wlrooms){
                            cnfrooms += wlrooms;
                            wlrooms = 0;
                        }
                        if(rooms_available == wlrooms){
                            wlrooms = 0;
                            cnfrooms += rooms_available;
                        }
                        else{
                            wlrooms -= rooms_available;
                            cnfrooms += rooms_available;
                        }
                        InsertRow("UPDATE booking_info SET rooms_confirmed = " + cnfrooms + " WHERE Booking_ID= "+bid+";");
                        InsertRow("UPDATE booking_info SET rooms_waitlist = " + wlrooms + " WHERE Booking_ID= "+bid+";");
                    }        
                    if(setFlag == 1){
                        JOptionPane.showMessageDialog(null, "Cancellation fee of 50% has been levied as you're booking within 3 days.", 
                           "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            catch(Exception e){
            System.out.println(e);
            }
       System.out.println("Cancel Action Performed Test Successful");
     }
} 

    
    @Test
    public void ModifyActionPerformedTest (){
         System.out.println(" Testing ModifyActionPerformed()");
         UserProfile frame ;
         JButton Modify; 
         int num = 10;
         for (int i = 0; i < num; i++) {
            // generate random username
            username = generateAlphaNumeric(40);
            frame = new UserProfile(username);
            //get access to  button
            //Modify = (JButton) TestUtils.getChildNamed(frame, "Modify");
            Bookings = (JTable)TestUtils.getChildNamed(frame, "Bookings");

            //assertNotNull("Modify Bookings inaccessible", Modify);
            assertNotNull("Frame component inaccessible", frame);
            
            // to see if each UI component is accessible
            System.out.println("Frame, Button found");

            System.out.print("TEST CASE: " + i + "\n");
            //randomised username 
            System.out.println("USERNAME: " + username);
            
            // integrating random dates into datepicker 
            Date dateIn = randDate();
            Date dateOut = randDate();
            frame.setVisible(true);

            // testing if values of username were correct and implemented correctly in frame variable
            assertEquals(username, frame.username);
            
            // testing the code of button functionality
            try {
                int rowIndex = Bookings.getSelectedRow();
                model = (DefaultTableModel) Bookings.getModel();
                int bookingID = (int) model.getValueAt(rowIndex, 0);
                java.sql.Date datein = (java.sql.Date) model.getValueAt(rowIndex,3);
                java.sql.Date dateout= (java.sql.Date) model.getValueAt(rowIndex,4);
                ResultSet rs = getResult("SELECT CURDATE()");
                rs.next();
                Date today = rs.getDate("CURDATE()");
                if(getDateDifference(datein, today) < 3){
                     JOptionPane.showMessageDialog(null, "You cannot modify now.", "WARNING!", JOptionPane.WARNING_MESSAGE);
                     return;
                }         
                new ModifyBooking(username, bookingID).setVisible(true);
                // this.dispose();
            } 
            catch(Exception e){
            System.out.println(e);
            }
            
            System.out.println("Modify Action Performed Test Successful");
        }        
    }
    
    @Test
    public void RatingOptionActionPerformedTest (){
         System.out.println(" Testing RatingOptionActionPerformed()");    
         
         UserProfile frame ;
         JButton RatingOption; 
         int num = 10;
         for (int i = 0; i < num; i++) {
            // generate random username
            username = generateAlphaNumeric(40);
            frame = new UserProfile(username);
            //get access to  button
            //RatingOption = (JButton) TestUtils.getChildNamed(frame, "RatingOption");
            Bookings = (JTable)TestUtils.getChildNamed(frame, "Bookings");
            JTextField Rating = (JTextField) TestUtils.getChildNamed(frame, "Rating");

            //assertNotNull("Rating Option inaccessible", RatingOption);
            assertNotNull("Frame component inaccessible", frame);
            
            // to see if each UI component is accessible
            System.out.println("Frame, Button found");

            System.out.print("TEST CASE: " + i + "\n");
            //randomised username 
            System.out.println("USERNAME: " + username);
            
            // integrating random dates into datepicker 
            Date dateIn = randDate();
            Date dateOut = randDate();
            frame.setVisible(true);

            // testing if values of username were correct and implemented correctly in frame variable
            assertEquals(username, frame.username);
            
            // testing the code of button functionality
            try {
                 // TODO add your handling code here:
                int rowIndex = Bookings.getSelectedRow();
                model = (DefaultTableModel) Bookings.getModel();
                
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
                
            }   
            catch (SQLException ex) {
            Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Rating Option Action Performed Test Successful");
        }   
    }
}
