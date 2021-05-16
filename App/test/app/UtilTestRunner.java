
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kylew
 */
public class UtilTestRunner {

    public static void main(String args[]) {
        Result result = JUnitCore.runClasses(UtilTest.class);

        System.out.println("Is the test successful?: " + result.wasSuccessful());
	  
        for (Failure failure : result.getFailures()) {
            System.out.println("Failure: " + failure.toString());
      	}
    }
}
