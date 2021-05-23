/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.BookingArea;
import static app.DBConnection.getResult;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import static app.DBConnection.getResult;
import static app.Utilities.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.junit.Assert;


/**
 *
 * @author liamt
 */
public class BookingAreaTest {
    
    public BookingAreaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class BookingArea.
     */
    @Test
    public void BookingAreaFill() {
        //Declare Testing Variables
        String username;
        BookingArea bookingArea;     
        
        JButton BookNow;
        org.jdesktop.swingx.JXDatePicker CheckInDate;
        org.jdesktop.swingx.JXDatePicker CheckOutDate;
        JComboBox<String> City;
        JTable HotelsAvailable;
        JScrollPane HotelsScrollArea;
        JSlider MaxPrice;
        JLabel MaxPriceLabel;
        JButton Search;
        JButton myAccount;
        // End of variables declaration                
        
        //Declare Testing area
        System.out.println("Booking Area");
        
        /*
            Constuct Booking areas, get attributes, set text and get output value.
            Check whether the input is equal to the output.

            1. Get the names of each component
            2. Test input and output
        */
        
        username = "test";
        bookingArea = new BookingArea(username);
        bookingArea.setVisible(true);
       
        BookNow = (JButton)TestUtils.getChildNamed(bookingArea, "BookNow");
        CheckInDate = (org.jdesktop.swingx.JXDatePicker)TestUtils.getChildNamed(bookingArea, "CheckInDate");
        CheckOutDate = (org.jdesktop.swingx.JXDatePicker)TestUtils.getChildNamed(bookingArea, "CheckOutDate");
        City = (JComboBox<String>)TestUtils.getChildNamed(bookingArea, "City");
        HotelsAvailable = (JTable)TestUtils.getChildNamed(bookingArea, "HotelsAvailable");
        HotelsScrollArea = (JScrollPane)TestUtils.getChildNamed(bookingArea, "HotelsScrollArea");
        MaxPrice = (JSlider)TestUtils.getChildNamed(bookingArea, "MaxPrice");
        Search = (JButton)TestUtils.getChildNamed(bookingArea, "Search");
        myAccount = (JButton)TestUtils.getChildNamed(bookingArea, "myAccount");
        
        Random rand = new Random(); //instance of random class
        int citylimit = 7;
        //generate random values from 0-7 for cities
        int mCity = rand.nextInt(citylimit); 
    }
    
    @Test
    public void checkBoxChecked(){
        //Declaration
        String username;
        BookingArea frame;
        JCheckBox AC;
        JCheckBox CarRental;
        JCheckBox CompBF;
        JCheckBox Pool;
        JCheckBox Wifi;
        
        //Initialisation
        username = "test";
        frame = new BookingArea(username);
        frame.setVisible(true);
        
        AC = (JCheckBox)TestUtils.getChildNamed(frame, "AC");
        CarRental = (JCheckBox)TestUtils.getChildNamed(frame, "CarRental");
        CompBF = (JCheckBox)TestUtils.getChildNamed(frame, "CompBF");
        Pool = (JCheckBox)TestUtils.getChildNamed(frame, "Pool");
        Wifi = (JCheckBox)TestUtils.getChildNamed(frame, "Wifi");
        
        
        //Check the Checkboxes
        try{
        AC.setSelected(true);
        CarRental.setSelected(true);
        CompBF.setSelected(true);
        Pool.setSelected(true);
        Wifi.setSelected(true);
  
        //Checkboxes cannot be null
        assertNotNull("Can't access the AC for BookingAreaTest component",AC);
        assertNotNull("Can't access the CarRental for BookingAreaTest component",CarRental);
        assertNotNull("Can't access the CompBF for BookingAreaTest component",CompBF);
        assertNotNull("Can't access the Pool for BookingAreaTest component",Pool);
        assertNotNull("Can't access the Wifi for BookingAreaTest component",Wifi);
        
        //Test if checked
        Assert.assertTrue(AC.isSelected());
        Assert.assertTrue(CarRental.isSelected());
        Assert.assertTrue(CompBF.isSelected());
        Assert.assertTrue(Pool.isSelected());
        Assert.assertTrue(Wifi.isSelected());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        frame = null;
    }
    
    
}