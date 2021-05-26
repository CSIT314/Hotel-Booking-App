package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertNotNull;
import static app.DBConnection.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kylew
 */
public class UtilTest {

    private Utilities testSubject;

    @Before
    public void setUp() throws Exception {
        this.testSubject = new Utilities();
    }

    @After
    public void tearDown() throws Exception {
        testSubject = null;
    }

    // testing the method convertDate() in Utilities it takes a date object and
    // converts it into an sql date object
    @Test
    public void testConvertDate() {
        System.out.println("\nTesting convertDate()...");
        // setting up a simple date formate for the date object
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        try {
            // opening and reading a file with a data set of dates for march
            // 2021 where the first and last dates are invalid dates 
            // 30/02/2021 and 01/13/2021
            File dates = new File("convertdates.txt");
            Scanner read = new Scanner(dates);
            while (read.hasNextLine()) {
                System.out.println("\nTest " + i);
                String dateData = read.nextLine();
                // turning the date from the data set into a date object
                java.util.Date date1 = formater.parse(dateData);
                // out put of the date from the data set, the date as a 
                // date object and the sql date object it converts to
                System.out.println("input date from data set = " + dateData);
                System.out.println("input date oject from data = " + date1);
                System.out.println("output SQL date of convertDate() = " + testSubject.convertDate(date1));
                assertEquals("testing date converted to sql date", new java.sql.Date(date1.getTime()), testSubject.convertDate(date1));
                i++;
            }
        } catch (ParseException | IOException ex) {
            Logger.getLogger(UtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\nTesting random dates with Testing convertDate()...");
        for (int k = 0; k < 10; k++) {
            System.out.println("\nTest " + i);
            try {
                int year = 2021 + (int) Math.round(Math.random() * (2022 - 2021));
                gc.set(gc.YEAR, year);
                int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));
                gc.set(gc.DAY_OF_YEAR, dayOfYear);
                String date1 = formater.format(gc.getTime());
                java.util.Date date2 = formater.parse(date1);

                System.out.println("input random date = " + date1);
                System.out.println("input date oject of random date = " + date2);
                System.out.println("output SQL date of convertDate() = " + testSubject.convertDate(date2));
                assertEquals("testing date converted to sql date", new java.sql.Date(date2.getTime()), testSubject.convertDate(date2));
                i++;
            } catch (ParseException ex) {
                Logger.getLogger(UtilTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Date randDate() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        int year = 2021; //+ (int) Math.round(Math.random() * (2022 - 2021));
        gc.set(gc.YEAR, year);

        int month = 5 + (int) (Math.round(Math.random()) * (6 - 5));
        gc.set(gc.MONTH, month);

        int dayOfMonth = 1 + (int) Math.round(Math.random() * (1 - gc.getActualMaximum(gc.DAY_OF_MONTH)));
        gc.set(gc.DAY_OF_MONTH, dayOfMonth);

        String rdate = formater.format(gc.getTime());
        System.out.println(rdate);
        java.sql.Date randdate = new java.sql.Date(gc.getTimeInMillis());

        return randdate;
    }

    //  testing the method getDateDifference()in Utilities it taks two dates of
    // SQL format and calculates the difference in days between them
    @Test
    public void testGetDateDifference() {
        System.out.println("\nTesting getDateDifference()...");
        GregorianCalendar gc = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        try {
            File dates = new File("datediff.txt");
            Scanner read = new Scanner(dates);
            while (read.hasNextLine()) {
                // reading in the dataset of dates to be pased to the method
                // and setting up the input date variables as SQL date 
                // objects to be parsed 
                System.out.println("\nTest " + i);
                String dateData1 = read.nextLine();
                String dateData2 = read.nextLine();
                Date date1 = Date.valueOf(dateData1);
                Date date2 = Date.valueOf(dateData2);
                System.out.println("SQL date 1 " + date1);
                System.out.println("SQL date 2 " + date2);
                System.out.println("output of getDateDifference() = " + testSubject.getDateDifference(date1, date2));
                /*
                String query = "DATEDIFF(\"" + date1 + "\", \"" + date2 + "\")";
                ResultSet rs;
                int n = 0;
                try {
                    rs = getResult("SELECT " + query + ";");
                    rs.next();
                    n = rs.getInt(query);
                    System.out.println("diff = " + n);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                 */

                double diffInTime = date1.getTime() - date2.getTime();
                long diffInDays = Math.round(diffInTime / (1000 * 60 * 60 * 24));

                System.out.println(diffInDays);
                assertEquals("testing date diff", diffInDays, testSubject.getDateDifference(date1, date2));
                i++;

            }
            System.out.println("\nTesting random getDateDifference()...");
            for (int k = 0; k < 10; k++) {

                Date rDate1 = randDate();
                Date rDate2 = randDate();

                double diffInTime = rDate1.getTime() - rDate2.getTime();
                long diffInDays = Math.round(diffInTime / (1000 * 60 * 60 * 24));

                System.out.println(diffInDays);
                assertEquals("testing date diff", diffInDays, testSubject.getDateDifference(rDate1, rDate2));
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean insertBookings() {
        Date datein = null;
        Date dateout = null;
        int bookID = 900000;
        int HID = 0;
        String query = "INSERT INTO booking_info (Booking_ID, username, Hotel_ID, rooms_confirmed, rooms_waitlist, Date_In, Date_Out, ID_Type, ID_Number, Status, Booking_Date, Total_Price) VALUES ";

        for (int i = 0; i < 2; i++) {
            bookID = bookID + (i + 1);
            for (int k = 0; k < 24; k++) {
                datein = randDate();
                dateout = randDate();
                bookID = bookID + (k + 1);
                HID = k + 1;
                //HID = 1 + (int) (Math.random() * (29 - 1));
                System.out.println("this is not right ?? " + HID);
                System.out.println("this is not right ?? " + datein);
                System.out.println("this is not right ?? " + dateout);

                if (datein.compareTo(dateout) > 0) {
                    System.out.println("datein > dateout? " + datein + " " + dateout);
                    Date temp = datein;
                    datein = dateout;
                    dateout = temp;
                    System.out.println(datein + " " + dateout);
                } else if (datein.compareTo(dateout) < 0) {
                    System.out.println("datein < dateout? " + datein + " " + dateout);

                }

                //insert into booking info for testing testCheckAvailability()
                query = query+"(\"" + bookID + "\",\"kye\",\"" + HID + "\",1,0,\"" + datein + "\",\"" + dateout + "\",\"Student ID Card\",\"123456\",0,CURDATE(),500)";
                if (i != 1 AND k != 24) {
                    query = query + ",";
                }
            }
        }
        try {
            InsertRow(query);
        }catch (Exception se) {
            se.printStackTrace();
            System.out.println("fail");
            return false;
        }
        return true;
    }

    private void deleteBookings() {

        // Delete rows inserted for testing after finished
        String query = "DELETE FROM booking_info WHERE username=\"kye\"";
        try {
            InsertRow(query);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    // testing the method checkAvailability() in Utilities it takes an int 
    // that represents the hotel ID, and two SQL date objects as checkin date
    // and checkout date then checks the database to see if any rooms are available
    // in that date range at the specified hotel
    @Test
    public void testCheckAvailability() {
        ResultSet rs;
        String query = "SELECT * FROM booking_info WHERE username=\"kye\"";
        rs = getResult(query);
        /*try {
            while (rs.next()) {
                int booking_ID = rs.getInt("Booking_ID");
                String username = rs.getString("username");
                int Hotel_ID = rs.getInt("Hotel_ID");
                int rooms_confirmed = rs.getInt("rooms_confirmed");
                int rooms_waitlist = rs.getInt("rooms_waitlist");
                Date Date_In = rs.getDate("Date_In");
                Date Date_Out = rs.getDate("Date_Out");
                String ID_Type = rs.getString("ID_Type");
                String ID_Number = rs.getString("ID_Number");
                int Status = rs.getInt("Status");
                Date Booking_Date = rs.getDate("Booking_Date");
                int Total_Price = rs.getInt("Total_Price");

                System.out.println("booking_ID " + booking_ID + ", username " + username + ", Hotel_ID " + Hotel_ID + ", rooms_confirmed " + rooms_confirmed
                        + ", rooms_waitlist " + rooms_waitlist + ", Date_In " + Date_In + ", Date_Out " + Date_Out + ", ID_Type " + ID_Type + ", ID_Number " + ID_Number + ", Status " + Status + ", Booking_Date " + Booking_Date + ", Total_Price " + Total_Price);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        deleteBookings();
        System.out.println("\nTesting CheckAvailability()...");
        insertBookings();
        int i = 1;
        try {
            File dates = new File("datesavail.txt");
            Scanner read = new Scanner(dates);
            while (read.hasNextLine()) {
                System.out.println("\nTest " + i);
                // reading in the dataset of hotel ID and dates to be parsed
                // to the method and setting up the dates from the data set
                // as SQL date objects
                int HID = Integer.parseInt(read.nextLine());
                Date datein = Date.valueOf(read.nextLine());
                Date dateout = Date.valueOf(read.nextLine());

                /*
                String query = "INSERT INTO booking_info (Booking_ID, username, Hotel_ID, rooms_confirmed, rooms_waitlist, Date_In, Date_Out, ID_Type, ID_Number, Status, Booking_Date, Total_Price) VALUES(\"" + 900000 + "\",\"kye\",\"" + HID + "\",\" 1 \",\"0\",\"" + datein + "\",\"" + dateout + "\",\"test\",\"test\",\"0\",\"1987-01-01\",\"500\")";
                try {                                                                                                                                                                                         //Booking_ID, username, Hotel_ID, rooms_confirmed, rooms_waitlist, Date_In, Date_Out, ID_Type, ID_Number, Status, Booking_Date, Total_Price
                    InsertRow(query);
                } catch (Exception se) {
                    se.printStackTrace();
                }
                 */
                System.out.println("SQL date in " + datein);
                System.out.println("SQL date out " + dateout);
                System.out.println("\noutput of checkAvailability() = " + testSubject.checkAvailability(HID, datein, dateout));

                //assertNotNull("output of checkAvailability() cannot be null = " + testSubject.checkAvailability(HID, datein, dateout));
                //assertEquals(" = " testAvail + testSubject.checkAvailability(HID, datein, dateout));
                System.out.println(datein);
                i++;

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int j = 0; j < 9; j++) {
            System.out.println("\nTest " + i);
            Date datein = randDate();
            Date dateout = randDate();
            if (datein.compareTo(dateout) > 0) {
                System.out.println("datein > dateout? " + datein + " " + dateout);
                Date temp = datein;
                datein = dateout;
                dateout = temp;
                System.out.println(datein + " " + dateout);
            }
            int HID = 1 + (int) (Math.random() * (25 - 1));

            System.out.println("SQL date in " + datein);
            System.out.println("SQL date out " + dateout);
            System.out.println("\noutput of checkAvailability() = " + testSubject.checkAvailability(HID, datein, dateout));
            i++;
        }
        deleteBookings();
    }
}
