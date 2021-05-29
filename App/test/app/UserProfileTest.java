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
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ARWA
 */

public class UserProfileTest {
   
 // class variables
    String username;
    int bid;


    public UserProfileTest() {
       
    }


    @Before
    public void setUp() {
        // this.testSubject = new UserProfile();
    }
    
    @After
    public void tearDown() {
       // testSubject = null; 
    }
   

    // random string generation for username
    private String generateAlphaNumeric(int topMargin) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        int targetStringLength = topMargin;

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }


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

        return randdate;
    }

     
    
      @Test
    public void CheckBookingsActionPerformedTest (){

         System.out.println(" Testing CheckBookingsActionPerforme()");

         UserProfile frame ;
         JButton CheckBookings; 
         int num = 10;


         for (int i = 0; i < num; i++) {
            // generate random username
            username = generateAlphaNumeric(40);
            

            frame = new UserProfile(username);
            //get access to  button
            CheckBookings = (JButton) TestUtils.getChildNamed(frame, "CheckBookings");

            assertNotNull("CheckBookings inaccessible", CheckBookings);
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
                java.sql.Date inDate, outDate;
                try {
                    inDate = dateIn.getDate();
                    outDate = dateOut.getDate();
                    // testing if new checkIn and checkOut date are not null 
                    assertNotNull("No Booking Date checkIn", inDate);
                    assertNotNull("No booking Date checkOut", outDate);
                    System.out.print("DATE IN: " + dateIn.getDate() + "\n");
                    System.out.print("DATE OUT: " + dateOut.getDate(); + "\n");
                } catch (java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException e) {
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
                    stat="Confirmed";
                }
                else if(status==1)
                {
                    stat="Waitlisted";
                }
                else if(status==2)
                {
                    stat="Cancelled";
                }
                Object row[] = {bid,roomconf,roomwait,datein,dateout,stat,hid};
                model.addRow(row);
                //Bookings.setRowSelectionAllowed(true);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        
         System.out.println("CheckBookingsActionPerformedTest Booking_ID successful");
         System.out.println("CheckBookingsActionPerformedTest addRow successful");
        
        }
    
    }
    
    @Test
    public void CancelActionPerformedTest (){

    }
   
    
    @Test
    public void ModifyActionPerformedTest (){
      
    }
    
    @Test
    public void RatingOptionActionPerformedTest (){
         
    }
}
