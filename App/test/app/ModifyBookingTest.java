package app;

import static app.DBConnection.InsertRow;
import static app.DBConnection.getResult;
import app.ModifyBooking;
import static app.ModifyBooking.username;
import static app.Utilities.checkAvailability;
import static app.Utilities.convertDate;
import static app.Utilities.getDateDifference;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
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
import org.jdesktop.swingx.JXDatePicker;

public class ModifyBookingTest {

    // class variables
    String username;
    int bid;

    public ModifyBookingTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {
    }

    // random generation of ints used for BID variable
    private int generateRandomInt() {
        int number = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        return number;
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

    // random date generation to be placed in datepickers
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
    
// testing jxdatepicker input and variables, username and booking id (BID)
    @Test
    public void testInputs() throws FileNotFoundException, ParseException {
        //UI components 
        ModifyBooking frame;
        JXDatePicker jXDatePicker1;
        JXDatePicker jXDatePicker2;
        int num = 10;

        for (int i = 0; i < num; i++) {
            System.out.print("\n TEST CASE: " + i);
            username = generateAlphaNumeric(40);
            bid = generateRandomInt();

            frame = new ModifyBooking(username, bid);
            frame.setVisible(true);
            System.out.println("");
            System.out.println("USERNAME: " + username);
            System.out.println("BID: " + bid);
            //get access to datepickers
            jXDatePicker1 = (JXDatePicker) TestUtils.getChildNamed(frame, "jXDatePicker1");
            jXDatePicker2 = (JXDatePicker) TestUtils.getChildNamed(frame, "jXDatePicker2");

            assertNotNull("Can't access the frame component", frame);
            assertNotNull("Date in inaccessible", jXDatePicker1);
            assertNotNull("Date out inaccessible", jXDatePicker2);
            // to see if each UI component is accessible
            System.out.println("Frame and DatePickers found");
            System.out.println("Test inputting dates in JXDatePicker");
            // integrating random dates into datepicker 
            Date dateIn = randDate();
            Date dateOut = randDate();
            jXDatePicker1.setDate(dateIn);
            jXDatePicker2.setDate(dateOut);
            System.out.print("DATE IN: " + jXDatePicker1.getDate() + "\n");
            System.out.print("DATE OUT: " + jXDatePicker2.getDate() + "\n");

            System.out.print("");
            // testing if values of username were correct and implemented correctly in frame variable
            assertEquals(username, frame.username);
            // testing if values of bid were correct and implemented correctly in frame variable
            assertEquals(bid, frame.bid);
        }
    }

    // testing the buttion function in modifyBooking
    @Test
    public void testButtonFunction() {
        System.out.println("\n");
        System.out.println("Testing Modify Booking Button");
        ModifyBooking frame;
        JXDatePicker jXDatePicker1;
        JXDatePicker jXDatePicker2;
        JButton button;
        int num = 10;

        //test cases
        for (int i = 0; i < num; i++) {
            // generate random username
            username = generateAlphaNumeric(40);
            //generate random bid
            bid = generateRandomInt();

            frame = new ModifyBooking(username, bid);
            //get access to datepickers and button
            jXDatePicker1 = (JXDatePicker) TestUtils.getChildNamed(frame, "jXDatePicker1");
            jXDatePicker2 = (JXDatePicker) TestUtils.getChildNamed(frame, "jXDatePicker2");
            button = (JButton) TestUtils.getChildNamed(frame, "jButton1");

            assertNotNull("jButton inaccessible", button);
            assertNotNull("Frame component inaccessible", frame);
            assertNotNull("Date in inaccessible", jXDatePicker1);
            assertNotNull("Date out inaccessible", jXDatePicker2);
            // to see if each UI component is accessible
            System.out.println("Frame, Button and DatePickers found");

            System.out.println("\n");
            System.out.print("TEST CASE: " + i + "\n");
            //randomised username and bid
            System.out.println("USERNAME: " + username);
            System.out.println("BID: " + bid);
            // integrating random dates into datepicker 
            Date dateIn = randDate();
            Date dateOut = randDate();
            jXDatePicker1.setDate(dateIn);
            jXDatePicker2.setDate(dateOut);
            frame.setVisible(true);
            // testing if values of username were correct and implemented correctly in frame variable
            assertEquals(username, frame.username);
            // testing if values of bid were correct and implemented correctly in frame variable
            assertEquals(bid, frame.bid);

            // testing the code of button functionality
            try {
                java.sql.Date inDate, outDate;
                try {
                    inDate = convertDate(jXDatePicker1.getDate());
                    outDate = convertDate(jXDatePicker2.getDate());
                    // testing if new checkIn and checkOut date are not null 
                    assertNotNull("No Booking Date checkIn", inDate);
                    assertNotNull("No booking Date checkOut", outDate);
                    System.out.print("DATE IN: " + jXDatePicker1.getDate() + "\n");
                    System.out.print("DATE OUT: " + jXDatePicker2.getDate() + "\n");
                } catch (java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Please fill in dates properly", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // check username and bid are not null 
                assertNotNull("Username inaccessible", username);
                assertNotNull("Booking ID inaccessible", bid);

                InsertRow("UPDATE booking_info SET Status=2 WHERE Booking_ID=\"" + bid + "\";");
                ResultSet rs = getResult("SELECT * FROM booking_info WHERE Booking_ID = " + bid);
                if (rs.next()) {
                    String id = rs.getString("ID_Number");
                    int Hid = rs.getInt("Hotel_ID");
                    int total_rooms = rs.getInt("rooms_confirmed") + rs.getInt("rooms_waitlist");
                    String IDType = rs.getString("ID_Type");
                    String IDNum = rs.getString("ID_Number");
                    java.sql.Date today = null;
                    try {
                        rs.next();
                        today = rs.getDate("CURDATE()");
                    } catch (SQLException ex) {
                        Logger.getLogger(BookingConfirmation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String query = "INSERT INTO booking_info VALUES (";
                    int num_of_days = getDateDifference(outDate, inDate) + 1;
                    int tariff = num_of_days * total_rooms;
                    rs = getResult("SELECT Tariff FROM room_info where Hotel_ID =" + Hid + ";");
                    rs.next();
                    int base_rate = rs.getInt("Tariff");
                    tariff *= base_rate;
                    query = "INSERT INTO booking_info VALUES (";
                    query += bid;
                    query += ",\"";
                    query += username;
                    query += "\", ";
                    query += Hid;
                    query += ", ";

                    int guests, rooms_confirmed, rooms_waitlisted, status, available_rooms;
                    available_rooms = checkAvailability(Hid, inDate, outDate);
                    if (available_rooms >= total_rooms) {
                        rooms_confirmed = total_rooms;
                        rooms_waitlisted = 0;
                        status = 0;
                    } else {
                        rooms_confirmed = available_rooms;
                        rooms_waitlisted = total_rooms - rooms_confirmed;
                        status = 1;
                    }

                    query += rooms_confirmed;
                    query += ", ";
                    query += rooms_waitlisted;
                    query += ", \"";
                    query += inDate;
                    query += "\", \"";
                    query += outDate;
                    query += "\", \"";
                    query += IDType;
                    query += "\", \"";
                    query += IDNum;
                    query += "\", ";
                    query += status;
                    query += ", \"";
                    query += today;
                    query += "\", ";
                    query += (tariff);
                    query += ");";
                    System.out.println(query);
                    InsertRow(query);
                    JOptionPane.showMessageDialog(null, "Your booking has been generated with booking ID" + bid, "Booking received.", JOptionPane.ERROR_MESSAGE);

                    new UserProfile(username).setVisible(true);
                    frame.setVisible(true);
                    // check if query was inserted and is not null
                    assertNotNull("Query did not update", query);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifyBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
            // success on modify booking test
            System.out.println("Modify Booking Test Successful");
        }

    }
}
