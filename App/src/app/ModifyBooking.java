/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import static app.BookingConfirmation.username;
import static app.Utilities.*;
import java.sql.Date;
import javax.swing.JOptionPane;
import static app.DBConnection.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class ModifyBooking extends javax.swing.JFrame {

    static String username;
    int bid;

    /**
     * Creates new form ModifyBooking
     */
    public ModifyBooking(String username, int bookingID) {
        this.username = username;
        this.bid = bookingID;
        initComponents();
        setComponentsNames();
    }

    private void setComponentsNames() {
        jButton1.setName("jButton1");
        jXDatePicker1.setName("jXDatePicker1");
        jXDatePicker2.setName("jXDatePicker2");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });

        jLabel1.setText("New CheckIn Date");

        jLabel2.setText("New CheckOut Date");

        jLabel4.setText("Modify BookingID: " + bid);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Modification might lead your confirm booking being waitlisted");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jButton1)))
                .addGap(0, 93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Date inDate, outDate;
            try {
                inDate = convertDate(jXDatePicker1.getDate());
                outDate = convertDate(jXDatePicker2.getDate());
            } catch (java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Please fill in dates properly", "WARNING!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            InsertRow("UPDATE booking_info SET Status=2 WHERE Booking_ID=\"" + bid + "\";");
            ResultSet rs = getResult("SELECT * FROM booking_info WHERE Booking_ID = " + bid);
            String id = rs.getString("ID_Number");
            int Hid = rs.getInt("Hotel_ID");
            int total_rooms = rs.getInt("rooms_confirmed") + rs.getInt("rooms_waitlist");
            String IDType = rs.getString("ID_Type");
            String IDNum = rs.getString("ID_Number");
            Date today = null;
            try {
                rs.next();
                today = rs.getDate("CURDATE()");
            } catch (SQLException ex) {
                Logger.getLogger(BookingConfirmation.class.getName()).log(Level.SEVERE, null, ex);
            }
            String query = "INSERT INTO booking_info VALUES (";
            int num_of_days = getDateDifference(outDate, inDate) + 1;
            int tariff = num_of_days * total_rooms;
            rs = getResult("SELECT Tariff FROM room_info where Hotel_ID =" + Hid + ";");
            rs.next();
            int base_rate = rs.getInt("Tariff");
            tariff *= base_rate;

            query = "INSERT INTO booking_info VALUES (";
            query += bid;
            query += ",\"";
            query += username;
            query += "\", ";
            query += Hid;
            query += ", ";

            int guests, rooms_confirmed, rooms_waitlisted, status, available_rooms;
            available_rooms = checkAvailability(Hid, inDate, outDate);
            if (available_rooms >= total_rooms) {
                rooms_confirmed = total_rooms;
                rooms_waitlisted = 0;
                status = 0;
            } else {
                rooms_confirmed = available_rooms;
                rooms_waitlisted = total_rooms - rooms_confirmed;
                status = 1;
            }

            query += rooms_confirmed;
            query += ", ";
            query += rooms_waitlisted;
            query += ", \"";
            query += inDate;
            query += "\", \"";
            query += outDate;
            query += "\", \"";
            query += IDType;
            query += "\", \"";
            query += IDNum;
            query += "\", ";
            query += status;
            query += ", \"";
            query += today;
            query += "\", ";
            query += (tariff);
            query += ");";
            System.out.println(query);
            InsertRow(query);
            JOptionPane.showMessageDialog(null, "Your booking has been generated with booking ID" + bid, "Booking received.", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            new UserProfile(username).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ModifyBooking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModifyBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    // End of variables declaration//GEN-END:variables
}
