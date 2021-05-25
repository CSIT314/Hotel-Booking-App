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
    //Declaration
        String username;
        DefaultTableModel model;
        BookingArea frame;
        JSlider MaxPrice;
        JCheckBox AC;
        JCheckBox CarRental;
        JCheckBox CompBF;
        JCheckBox Pool;
        JCheckBox Wifi;
        JComboBox<String> City; 
        JTable HotelsAvailable;
//    JScrollPane HotelsScrollArea;
//    
//    JButton Search;
//    JButton myAccount;
//    JButton BookNow;
//    
//    BookNow = (JButton)TestUtils.getChildNamed(bookingArea, "BookNow");
//    
//    HotelsScrollArea = (JScrollPane)TestUtils.getChildNamed(bookingArea, "HotelsScrollArea");
//    Search = (JButton)TestUtils.getChildNamed(frame, "Search");
//    myAccount = (JButton)TestUtils.getChildNamed(fram, "myAccount");
    @Test
    public void testMaxPrice(){
        //Declare Testing area
        System.out.println("BookingAreaTest testMaxPrice");
        
        //Initiatlise
        username = "test";
        frame = new BookingArea(username);
        frame.setVisible(true);
        
        MaxPrice = (JSlider)TestUtils.getChildNamed(frame, "MaxPrice");
      
        //Test
        try{
            Random rand = new Random(); //instance of random class
            int randPrice = rand.nextInt(99000 + 50); //generate random values from 50-99000 for cities
            
            MaxPrice.setValue(randPrice);
            System.out.println("Max Price set to " + randPrice);
            
            assertNotNull("Can't access the MaxPrice for BookingAreaTest testMaxPrice",MaxPrice);
         
            Assert.assertEquals(randPrice, MaxPrice.getValue());
            
            System.out.println("BookingAreaTest testMaxPrice successful");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        frame = null;
    }
    
    
    @Test
    public void testCitySelection() {    
        //Declare Testing area
        System.out.println("Booking Area Test testCitySelection");
        
        //Initiatlise
        username = "test";
        frame = new BookingArea(username);
        frame.setVisible(true);
        String cities[] = getCityList();
       
        //Test
        try{
            City = (JComboBox<String>)TestUtils.getChildNamed(frame, "City");
        
            City.setModel(new javax.swing.DefaultComboBoxModel(getCityList())); //Set combo box
            
            System.out.println("City selection JComboBox set");

            Random rand = new Random(); //instance of random class
            int randCity = rand.nextInt(6); //generate random values from 0-6 for cities
            City.setSelectedIndex(randCity); //set city randomly
            
            assertNotNull("Can't access the City for BookingAreaTest CityTest",City);
            
            System.out.println(City.getSelectedItem().toString() + " Selected in Jcombobox"); // Print selected city
            Assert.assertTrue(City.getSelectedItem().toString().equals(cities[randCity])); //Assert that the selected item is selected

            System.out.println("City selection passed test");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        frame = null;
    }
    
    private String[] getCityList(){
        ResultSet RSet = getResult("SELECT DISTINCT City FROM room_info");
        ArrayList<String> cities = new ArrayList<>(); 
        try {
            while(RSet.next()){
                cities.add(RSet.getString("City"));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        String[] city_names = cities.toArray(new String[0]);  
        //for(int i=0;i<city_names.length;i++)
            //System.out.println(city_names[i]);
        return city_names;
    }
    
    @Test
    public void checkBoxChecked(){
        //Initialisation
        System.out.println("Booking Area Test checkBoxChecked");
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
        
        System.out.println("Booking Area Test checkBoxes have been checked");
  
        //Checkboxes cannot be null
        assertNotNull("Can't access the AC for BookingAreaTest component",AC);
        assertNotNull("Can't access the CarRental for BookingAreaTest component",CarRental);
        assertNotNull("Can't access the CompBF for BookingAreaTest component",CompBF);
        assertNotNull("Can't access the Pool for BookingAreaTest component",Pool);
        assertNotNull("Can't access the Wifi for BookingAreaTest component",Wifi);
        
        System.out.println("Booking Area Test checkBoxes are not null");
        
        //Test if checked
        Assert.assertTrue(AC.isSelected());
        Assert.assertTrue(CarRental.isSelected());
        Assert.assertTrue(CompBF.isSelected());
        Assert.assertTrue(Pool.isSelected());
        Assert.assertTrue(Wifi.isSelected());
        
        System.out.println("Booking Area Test checkBoxChecked Passed");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        frame = null;
    }
    
    @Test
    public void testUpdateListing(){
    
        //Initialisation
        System.out.println("BookingAreaTest testUpdateListing");
        username = "test";
        frame = new BookingArea(username);
        frame.setVisible(true);
        
        String cities[] = getCityList();
        HotelsAvailable = (JTable)TestUtils.getChildNamed(frame, "HotelsAvailable");
        
        City = (JComboBox<String>)TestUtils.getChildNamed(frame, "City");
        City.setModel(new javax.swing.DefaultComboBoxModel(getCityList())); //Set combo box
        System.out.println("City selection JComboBox set");

        Random rand = new Random(); //instance of random class
        int randCity = rand.nextInt(7); //generate random values from 0-6 for cities
        City.setSelectedIndex(randCity); //set city randomly
        
        String query= "SELECT * FROM room_info WHERE city=\""+City.getSelectedItem()+"\"";

        System.out.println(query);
        ResultSet RSet = getResult(query);
        int i=0;
        model = (DefaultTableModel) HotelsAvailable.getModel();
        model.setRowCount(0);
        try {
            while(RSet.next()){
                int HID = RSet.getInt("Hotel_ID");
                String hotel = RSet.getString("Hotel_Name");
                String address = RSet.getString("Address");
                int tariff = RSet.getInt("Tariff");
                
                int rating = 0;
                int num_of_ratings = 0;
                float final_rating = 0;
                ResultSet a = getResult("SELECT rating from hotel_reviews WHERE Hotel_ID = " + HID + ";");
                while(a.next()){
                    rating += a.getInt("rating");
                    num_of_ratings++;
                }
                if(num_of_ratings != 0)
                    final_rating = (float) ((1.00 * rating)/num_of_ratings);
                else{
                    rating = 0;
                    num_of_ratings = 0;
                }
                String RatingToDisplay = "" + final_rating + "(" + num_of_ratings + ")";
                Object row[] = {HID, hotel, address, tariff, RatingToDisplay};
                model.addRow(row);
                
                Assert.assertNotNull(model);
                System.out.println("BookingAreaTest testUpdateListing Passed and table is able to populate listing");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}