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
        for(int i =0; i<10;i++){
            try {
                int year = 2021 + (int)Math.round(Math.random() * (2022 - 2021));
                
                gc.set(gc.YEAR, year);
                
                int dayOfYear = 1 + (int)Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));
                
                gc.set(gc.DAY_OF_YEAR, dayOfYear);
                String date1 = formater.format(gc.getTime());
                java.util.Date date2 = formater.parse(date1);
                System.out.println(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
                System.out.println(date1);
                System.out.println(date2);
            } catch (ParseException ex) {
                Logger.getLogger(newJavaFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
