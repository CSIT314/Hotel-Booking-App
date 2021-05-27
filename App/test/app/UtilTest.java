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
import static org.junit.Assert.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static app.DBConnection.*;
import static app.Utilities.getDateDifference;
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
                Date result = testSubject.convertDate(date1);
                System.out.println("output SQL date of convertDate() = " + result);
                assertEquals("testing date converted to sql date", new java.sql.Date(date1.getTime()), result);
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

                Date result = testSubject.convertDate(date2);

                System.out.println("input random date = " + date1);
                System.out.println("input date oject of random date = " + date2);
                System.out.println("output SQL date of convertDate() = " + result);
                assertEquals("testing date converted to sql date ", new java.sql.Date(date2.getTime()), result);
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

                int result = testSubject.getDateDifference(date1, date2);
                System.out.println("output of getDateDifference() = " + result);

                double diffInTime = date1.getTime() - date2.getTime();
                long diffInDays = Math.round(diffInTime / (1000 * 60 * 60 * 24));

                System.out.println("result of test date difference " + diffInDays);
                assertEquals("testing date diff", diffInDays, result);
                i++;

            }
            System.out.println("\nTesting random getDateDifference()...");
            for (int k = 0; k < 10; k++) {

                Date rDate1 = randDate();
                Date rDate2 = randDate();

                int result = testSubject.getDateDifference(rDate1, rDate2);
                System.out.println("output of getDateDifference() = " + result);

                double diffInTime = rDate1.getTime() - rDate2.getTime();
                long diffInDays = Math.round(diffInTime / (1000 * 60 * 60 * 24));

                System.out.println(diffInDays);
                assertEquals("testing date diff", diffInDays, result);
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

                if (datein.compareTo(dateout) > 0) {
                    Date temp = datein;
                    datein = dateout;
                    dateout = temp;
                }

                //insert into booking info for testing testCheckAvailability()
                query = query + "(\"" + bookID + "\",\"kye\",\"" + HID + "\",1,0,\"" + datein + "\",\"" + dateout + "\",\"Student ID Card\",\"123456\",0,CURDATE(),500)";
                if (i == 1 && k == 23) {
                    continue;
                } else {
                    query = query + ",";
                }
            }
        }
        try {
            InsertRow(query);
        } catch (Exception se) {
            System.out.println(query);
            se.printStackTrace();
            System.out.println("fail");
            return false;
        }
        System.out.println("inserting rows for testing");
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
        System.out.println("deleting rows inserted for testing");
    }

    // testing the method checkAvailability() in Utilities it takes an int 
    // that represents the hotel ID, and two SQL date objects as checkin date
    // and checkout date then checks the database to see if any rooms are available
    // in that date range at the specified hotel
    @Test
    public void testCheckAvailability() {
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

                System.out.println("SQL date in " + datein);
                System.out.println("SQL date out " + dateout);
                System.out.println("\noutput of checkAvailability() = " + testSubject.checkAvailability(HID, datein, dateout));

                //assertNotNull("output of checkAvailability() cannot be null = " + testSubject.checkAvailability(HID, datein, dateout));
                //assertEquals(" = " testAvail + testSubject.checkAvailability(HID, datein, dateout));
                System.out.println(datein);
                i++;

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilTest.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        for (int j = 0; j < 10; j++) {
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
            ResultSet rs = getResult("SELECT "/*rooms_confirmed,*/ + " Date_In,Date_Out FROM booking_info WHERE (Status=0 OR STATUS = 1) AND Hotel_ID = " + HID);
            ResultSet rs2 = getResult("SELECT Number_of_rooms FROM room_info WHERE Hotel_ID = " + HID);
            int n = getDateDifference(dateout, datein);
            
            int[] days = new int[n + 1]; //array representing days starting from DateIn to DateOut, both inclusive
            for (int q = 0; q <= n; q++) {
                days[q] = 0;
            }
            try {
                rs2.next();
                int rooms = rs2.getInt("Number_of_rooms");
                rs2.close();
                int roombooking = 0;
                int resTest = 0;
                
                while (rs.next()) {

                    Date checkin = rs.getDate("Date_In");
                    Date checkout = rs.getDate("Date_Out");
                    System.out.println("check in " + checkin);
                    System.out.println("check out " + checkout);

                    if ((checkin.after(datein) || checkin.equals(datein)) && (checkout.before(dateout) || checkout.equals(dateout))) {
                        int q, w;
                        q = getDateDifference(checkin, datein);
                        w = n - getDateDifference(dateout, checkout);
                        for (int k = q; k <= w; k++) {
                            days[k]++;
                        }
                        //roombooking++;
                        System.out.println("1");
                    } else if ((checkout.before(dateout) || checkout.equals(dateout)) && (checkout.after(datein) || checkout.equals(datein)) && (checkin.before(datein) || checkin.equals(datein))) {
                        int q, w;
                        q = 0;
                        w = n - getDateDifference(dateout, checkout);
                        for (int k = q; k <= w; k++) {
                            days[k]++;
                        }
                        //roombooking++;
                        System.out.println("2");
                    } else if ((checkin.after(datein) || checkin.equals(datein)) && (checkin.before(dateout) || checkin.equals(dateout)) && (checkout.after(dateout) || checkin.equals(dateout))) {
                        int q, w;
                        w = n;
                        q = getDateDifference(checkin, datein);
                        for (int k = q; k <= w; k++) {
                            days[k]++;
                        }
                        //roombooking++;
                        System.out.println("3");
                    } else if ((checkin.before(datein) || checkin.equals(datein)) && (checkout.after(dateout) || checkout.equals(dateout))) {
                        for (int q = 0; q <= n; q++) {
                            days[q] += 1;
                        }
                        //roombooking++;
                        System.out.println("4");
                    }

                }
                roombooking = days[0];
                for (int q = 0; q <= n; q++) {
                    System.out.print(days[q] + " ");
                    if (days[q] > roombooking) {
                        roombooking = days[q];
                        System.out.println("roombooking " + roombooking);
                    }
                    System.out.println("roombooking " + roombooking);
                    resTest = rooms - roombooking;
                }
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(UtilTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                int result = testSubject.checkAvailability(HID, datein, dateout);
                System.out.println("\nSQL date in " + datein);
                System.out.println("SQL date out " + dateout);
                System.out.println("total rooms " + rooms);
                System.out.println("\noutput of checkAvailability() = " + result + " results from test = " + resTest);
                assertEquals("output of checkAvailability() = ", resTest, result);
                i++;

            } catch (SQLException ex) {
                Logger.getLogger(UtilTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        deleteBookings();
    }
}
