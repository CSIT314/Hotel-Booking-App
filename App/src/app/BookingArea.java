/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import static app.DBConnection.getResult;
import static app.Utilities.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rohit
 */
public class BookingArea extends javax.swing.JFrame {

    String username;
    DefaultTableModel model;
    /**
     * Creates new form BookingArea
     */
    public BookingArea(String username) {
        this.username = username;
        initComponents();  
        setComponentsNames();
    }
    
    private void setComponentsNames() {
        AC.setName("AC");
        CarRental.setName("CarRental");
        CompBF.setName("CompBF");
        Pool.setName("Pool");
        Wifi.setName("Wifi");
        City.setName("City");
        MaxPrice.setName("MaxPrice");
        HotelsAvailable.setName("HotelsAvailable");
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
        //    System.out.println(city_names[i]);
        return city_names;
    }
    
    private void update_listing()
    {
        String query= "SELECT * FROM room_info WHERE city=\""+City.getSelectedItem()+"\"";
        if(CompBF.isSelected()){
            query+=" AND COMP_BF = 1";
        }
        if(AC.isSelected()){
            query+=" AND AC = 1";
        }
        if(Wifi.isSelected()){
            query+=" AND WiFi = 1";
        }
        if(CarRental.isSelected()){
            query+=" AND Car_Rental = 1";
        }
        if(Pool.isSelected()){
            query+=" AND Swimming_Pool = 1";
        }
        query+=" AND tariff < ";
        query+= MaxPrice.getValue();
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
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        City = new javax.swing.JComboBox<>();
        Search = new javax.swing.JButton();
        CheckInTimeLabel = new javax.swing.JLabel();
        CheckOutTimeLabel = new javax.swing.JLabel();
        AC = new javax.swing.JCheckBox();
        Wifi = new javax.swing.JCheckBox();
        CarRental = new javax.swing.JCheckBox();
        CompBF = new javax.swing.JCheckBox();
        CheckInDate = new org.jdesktop.swingx.JXDatePicker();
        CheckOutDate = new org.jdesktop.swingx.JXDatePicker();
        Pool = new javax.swing.JCheckBox();
        HotelsScrollArea = new javax.swing.JScrollPane();
        HotelsAvailable = new javax.swing.JTable();
        MaxPrice = new javax.swing.JSlider(50, 99000, 15000);
        MaxPriceLabel = new javax.swing.JLabel();
        BookNow = new javax.swing.JButton();
        myAccount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        City.setModel(new javax.swing.DefaultComboBoxModel(getCityList()));
        City.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CityActionPerformed(evt);
            }
        });

        Search.setText("Go");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        CheckInTimeLabel.setText("CheckIn");

        CheckOutTimeLabel.setText("CheckOut");

        AC.setText("AC");
        AC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACActionPerformed(evt);
            }
        });

        Wifi.setText("WiFi");
        Wifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WifiActionPerformed(evt);
            }
        });

        CarRental.setText("Car Rental");
        CarRental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarRentalActionPerformed(evt);
            }
        });

        CompBF.setText("Complimentary Breakfast");
        CompBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompBFActionPerformed(evt);
            }
        });

        Pool.setText("Swimming Pool");
        Pool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PoolActionPerformed(evt);
            }
        });

        HotelsAvailable.setFont(new java.awt.Font("Noto Sans Mono CJK JP Bold", 0, 12)); // NOI18N
        HotelsAvailable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HID", "Hotel Name", "Address", "Tariff", "Rating"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HotelsAvailable.setRowHeight(50);
        HotelsAvailable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        HotelsScrollArea.setViewportView(HotelsAvailable);

        MaxPrice.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MaxPriceStateChanged(evt);
            }
        });

        MaxPriceLabel.setText("Max Price: " + MaxPrice.getValue());

        BookNow.setText("Check Availability");
        BookNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookNowActionPerformed(evt);
            }
        });

        myAccount.setBackground(new java.awt.Color(240, 242, 242));
        myAccount.setText("u:" + username);
        myAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(HotelsScrollArea, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(CarRental)
                        .addGap(34, 34, 34)
                        .addComponent(Pool)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(City, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CheckInTimeLabel)
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(CompBF)
                                .addGap(18, 18, 18)
                                .addComponent(AC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(Wifi)
                                .addGap(130, 130, 130)
                                .addComponent(MaxPriceLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CheckInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CheckOutTimeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Search)))))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(BookNow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(myAccount)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(myAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(City, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckInDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckOutTimeLabel)
                    .addComponent(CheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckInTimeLabel)
                    .addComponent(Search))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Wifi)
                            .addComponent(AC)
                            .addComponent(CompBF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CarRental)
                            .addComponent(Pool)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(MaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaxPriceLabel)))
                .addGap(18, 18, 18)
                .addComponent(HotelsScrollArea, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BookNow)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        update_listing();
    }//GEN-LAST:event_SearchActionPerformed

    private void CarRentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarRentalActionPerformed
        update_listing();
    }//GEN-LAST:event_CarRentalActionPerformed

    private void CompBFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompBFActionPerformed
        update_listing();
    }//GEN-LAST:event_CompBFActionPerformed

    private void CityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CityActionPerformed
        update_listing();
    }//GEN-LAST:event_CityActionPerformed

    private void PoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoolActionPerformed
        update_listing();
    }//GEN-LAST:event_PoolActionPerformed

    private void MaxPriceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MaxPriceStateChanged
        MaxPriceLabel.setText("Max Price:" + MaxPrice.getValue());
        update_listing();
        
    }//GEN-LAST:event_MaxPriceStateChanged

    private void ACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACActionPerformed
        update_listing();
    }//GEN-LAST:event_ACActionPerformed

    private void WifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WifiActionPerformed
        update_listing();
    }//GEN-LAST:event_WifiActionPerformed

    private void BookNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookNowActionPerformed
        int row = HotelsAvailable.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(null, "Please select a hotel.", "WARNING!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        model = (DefaultTableModel) HotelsAvailable.getModel();
        int HID = (int) model.getValueAt(row, 0);
        String Hotel = (String) model.getValueAt(row, 1);
        int tariff = (int) model.getValueAt(row, 3);
        String Address = (String) model.getValueAt(row, 2);
        System.out.println(HID);
        
        //Checks if dates filled are valid well formed dates
        Date inDate, outDate;
        try{
            inDate = convertDate(CheckInDate.getDate());
            outDate = convertDate(CheckOutDate.getDate());
        }catch(java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please fill in dates properly", "WARNING!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //checks if check in/out dates are in the future and that chec_in date < check_out date
        if(getDateDifference(outDate, inDate) < 0){
            JOptionPane.showMessageDialog(null, "Your check-in date has to be before check out date", "WARNING!", JOptionPane.WARNING_MESSAGE); 
            return;
        }
        if(getDateDifference(outDate, inDate) > 30)
        {
            JOptionPane.showMessageDialog(null, "You appear to be making a booking for greater than 30 days. Our corporate policy allows bookings upto only 30 days.", "WARNING!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            ResultSet rs = getResult("SELECT CURDATE();");
            rs.next();            
            Date today = rs.getDate("CURDATE()");
            if(getDateDifference(inDate, today) < 0){
                JOptionPane.showMessageDialog(null, "Your check in date can't be in the past mate.", "WARNING!", JOptionPane.WARNING_MESSAGE); 
                return;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        this.dispose();
        new BookingConfirmation(username, HID, Hotel, Address, (String) City.getSelectedItem(), tariff, inDate, outDate).setVisible(true);
        return;        
    }//GEN-LAST:event_BookNowActionPerformed

    private void myAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myAccountActionPerformed
        this.dispose();
        new UserProfile(username).setVisible(true);
    }//GEN-LAST:event_myAccountActionPerformed

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
            java.util.logging.Logger.getLogger(BookingArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingArea("abc").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AC;
    private javax.swing.JButton BookNow;
    private javax.swing.JCheckBox CarRental;
    private org.jdesktop.swingx.JXDatePicker CheckInDate;
    private javax.swing.JLabel CheckInTimeLabel;
    private org.jdesktop.swingx.JXDatePicker CheckOutDate;
    private javax.swing.JLabel CheckOutTimeLabel;
    private javax.swing.JComboBox<String> City;
    private javax.swing.JCheckBox CompBF;
    private javax.swing.JTable HotelsAvailable;
    private javax.swing.JScrollPane HotelsScrollArea;
    private javax.swing.JSlider MaxPrice;
    private javax.swing.JLabel MaxPriceLabel;
    private javax.swing.JCheckBox Pool;
    private javax.swing.JButton Search;
    private javax.swing.JCheckBox Wifi;
    private javax.swing.JButton myAccount;
    // End of variables declaration//GEN-END:variables
}
