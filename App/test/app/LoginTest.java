package app;

import app.Login;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

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
    }
}