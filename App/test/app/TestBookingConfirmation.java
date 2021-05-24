package app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;


public class TestBookingConfirmation {

       static BookingConfirmation bookingConfirmation=new BookingConfirmation("TestName",1,"TestHotel","TestAddress","TestCity",1,new java.sql.Date(System.currentTimeMillis()-10*1024*1024),new Date(System.currentTimeMillis()));
    @Before
    public void show(){
        bookingConfirmation.show();
    }
    @Test
    public void testConfirmButtonActionPerformed(){
        BookingConfirmation bookingConfirmation=new BookingConfirmation("TestName",1,"TestHotel","TestAddress","TestCity",1,new java.sql.Date(System.currentTimeMillis()-10*1024*1024),new Date(System.currentTimeMillis()));
        System.out.println(bookingConfirmation.Description);
        System.out.println(bookingConfirmation.rooms_to_book);
        System.out.println(bookingConfirmation.days);
        System.out.println(bookingConfirmation.tariff);
        System.out.println(bookingConfirmation.number_of_rooms);
    }
    private Utilities testSubject;

    @Before
    public void setUp() throws Exception {
        this.testSubject = new Utilities();
    }

    @After
    public void tearDown() throws Exception {
        testSubject = null;
    }
    @Test
    public void testBookDate() {
        System.out.println("\nTesting bookDate()...");
        // setting up a simple date formate for the date object
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
            Logger.getLogger(TestBookingConfirmation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // testing the method checkAvailability() in Utilities it takes an int
    // that represents the hotel ID, and two SQL date objects as checkin date
    // and checkout date then checks the database to see if any rooms are available
    // in that date range at the specified hotel
    @Test
    public void testCheckAvailability() {
        System.out.println("\nTesting getDateDifference()...");
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
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestBookingConfirmation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
