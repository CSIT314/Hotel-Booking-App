package app;

import app.Login;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import static app.DBConnection.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Random;
import java.util.ArrayList; 

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author ezeutno (Ivan Ezechial S)
 */
public class LoginTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void mainLoginFill() {
        System.out.println("Test [1] Fill Main Login Form");

        /*
            Declare use variable for testing
        */
        Login frame;
        JTextField username;
        JPasswordField password;


        /*
            Constuct Login app, get attribute, set text and get output value.
            Check whether the input is equal to the output.

            1. Get the names of each component
            2. Test input and output
        */
        frame = new Login();
        frame.setVisible(true);

        username = (JTextField) TestUtils.getChildNamed(frame, "jTextField1");
        password = (JPasswordField) TestUtils.getChildNamed(frame, "jPasswordField1");

        System.out.println("JUnit found TextField and PassowrdField on Java Swing Form");

        assertNotNull("Can't access the JTextField component",username);
        assertNotNull("Can't access the JPasswordField component",password);

        System.out.println("Put text and get text from field JTextField and JPasswordField");
        username.setText("test");
        password.setText("test");

        assertEquals("test", username.getText());
        assertEquals("test", new String(password.getPassword()));

        frame = null;
    }

    @Test
    public void testLoginButton() {
        System.out.println("\nTest [2] Test button functionality");
        Login frame;

        JTextField username;
        JPasswordField password;
        JButton button;

        frame = new Login();
        frame.setVisible(true);

        username = (JTextField) TestUtils.getChildNamed(frame, "jTextField1");
        password = (JPasswordField) TestUtils.getChildNamed(frame, "jPasswordField1");
        button = (JButton) TestUtils.getChildNamed(frame, "jButton1");
        assertNotNull("Can't access the JButton for Login component",button);

        username.setText("test");
        password.setText("test");

        System.out.println("Invoke Login Button");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                button.doClick();
            }});

        frame = null;
    }

    @Test
    public void testRegisterButton() {
        System.out.println("\nTest [3] Test Register functionality");
        
        Login frame;
        JButton button;

        frame = new Login();
        frame.setVisible(true);

        //Testing the existance of Register button
        button = (JButton) TestUtils.getChildNamed(frame, "Register");
        assertNotNull("Can't access the JButton for Register component",button);
        
        //Testing the used of Register button
        System.out.println("Invoked Register Button");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                button.doClick();
            }});

        frame = null;
    }

    private boolean insertUser(String username, String password, String testCase) {

        //Feed data to user info for testing the login function

        String query = "INSERT INTO user_info (username, firstname, lastname, password, DOB, address,email) VALUES(\"" + username + "\",\"test\",\"test\",\"" + password + "\",\"1987-01-01\",\"test\",\"" + testCase + "\")";
        try {
            InsertRow(query);
            return true;
        }catch(Exception se){
            se.printStackTrace();
            return false;
        }
    }

    private boolean deleteUser(String testCase) {

        // Delete query after finished testing
        String query = "DELETE FROM user_info WHERE email=\"" + testCase + "\"";
        try {
            InsertRow(query);
            return true;
        }catch(Exception se){
            se.printStackTrace();
            return false;
        }
    }

    // generate random character in java 
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
    @Test
    public void testLoginTrue() {
        System.out.println("\nTest [4] Check Login if TRUE");
        //Load data [INSERT INTO] to database randomly of 20 values
        System.out.println("Load & Generate data into Databse");
        int numberofTest = 20;

        Login frame = new Login();
        frame.setVisible(true);

        String testCaseId = generateAlphaNumeric(9);
        System.out.println("Test CaseID of "+testCaseId);

        for (int i = 0; i<numberofTest; i++) {
            System.out.println("\nTest Case Number : "+i);

            // generate random username and password for database
            String username = generateAlphaNumeric(36);
            String password = generateAlphaNumeric(16);
            System.out.println("Generated username and password : "+username+" "+password);

            // insert user to database after random generation
            insertUser(username,password,testCaseId);

            // validate login using the validate login function
            assertEquals(true, frame.validate_login(username, password));
        }

        //Delete [DELETE] data to database
        deleteUser(testCaseId);
        frame = null;
    }

    @Test
    public void testLoginFalse() {
        System.out.println("\nTest [5] Check Login if FALSE");
        int numberofTest = 5;

        //generate the Login class
        Login frame = new Login();
        frame.setVisible(true);

        for (int i = 0; i<numberofTest; i++) {
            System.out.println("\nTest Case Number : "+i);

            // generate random username and password for database
            String username = generateAlphaNumeric(36);
            String password = generateAlphaNumeric(16);
            System.out.println("Generated username and password : "+username+" "+password);

            // validate login using the validate login function
            assertEquals(false, frame.validate_login(username, password));
        }

        frame = null;
    }
}