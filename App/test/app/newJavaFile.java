/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kylew
 */
public class newJavaFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        long now = System.currentTimeMillis();
        Date test = new Date(now);
        //System.out.println(test);
        //System.out.println(now);

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar gc = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();
        for (int i = 0; i < 10; i++) {
            try {
                int year = 2021 + (int) Math.round(Math.random() * (2022 - 2021));
                gc.set(gc.YEAR, year);
                int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));
                gc.set(gc.DAY_OF_YEAR, dayOfYear);
                String date1 = formater.format(gc.getTime());
                java.util.Date date2 = formater.parse(date1);
                System.out.println(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));

                System.out.println(date2);

                int year2 = 2021 + (int) Math.round(Math.random() * (2022 - 2021));
                gc2.set(gc2.YEAR, year2);
                int dayOfYear2 = 1 + (int) Math.round(Math.random() * (gc2.getActualMaximum(gc2.DAY_OF_YEAR) - 1));
                gc2.set(gc2.DAY_OF_YEAR, dayOfYear2);
                String date3 = formater.format(gc2.getTime());
                java.util.Date date4 = formater.parse(date3);
                System.out.println(gc2.DAY_OF_YEAR);

                long test2 = (gc.DAY_OF_YEAR - gc2.DAY_OF_YEAR);
                Date test3 = new Date(test2);
                System.out.println(test2);

                Date d1 = Date.valueOf("2021-03-10");
                Date d2 = Date.valueOf("2021-03-25");

                // Calucalte time difference
                // in milliseconds
                long difference_In_Time
                        = d2.getTime() - d1.getTime();

                // Calucalte time difference in
                // seconds, minutes, hours, years,
                // and days
                long difference_In_Seconds
                        = (difference_In_Time
                        / 1000)
                        % 60;

                long difference_In_Minutes
                        = (difference_In_Time
                        / (1000 * 60))
                        % 60;

                long difference_In_Hours
                        = (difference_In_Time
                        / (1000 * 60 * 60))
                        % 24;

                long difference_In_Years
                        = (difference_In_Time
                        / (1000l * 60 * 60 * 24 * 365));

                long difference_In_Days
                        = (difference_In_Time
                        / (1000 * 60 * 60 * 24));
                        //% 365;

                System.out.print(
                        "Difference "
                        + "between two dates is: ");

                System.out.println(
                        difference_In_Years
                        + " years, "
                        + difference_In_Days
                        + " days, "
                        + difference_In_Hours
                        + " hours, "
                        + difference_In_Minutes
                        + " minutes, "
                        + difference_In_Seconds
                        + " seconds");

            } catch (ParseException ex) {
                Logger.getLogger(newJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
