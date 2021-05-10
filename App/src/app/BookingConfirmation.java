/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import static app.Utilities.*;
import static app.DBConnection.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author rohit
 */
public class BookingConfirmation extends javax.swing.JFrame {
//            Wifi_img.setIcon(new javax.swing.ImageIcon(getClass().getResource(WiFi)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    int HID, tariff, number_of_rooms, rooms_to_book = 1,days;
    Date checkIn, checkOut;
    String hotel_name, Address, Description, City;
    String image;
    String WiFi, Car_Rental, AC, Swimming_Pool, Comp_BF;
    static String username;
    static String yes = "/resources/Yes.png";
    static String no = "/resources/No.png";
    BookingConfirmation(String username, int HID, String Hotel, String Address, String City, int tariff, Date inDate, Date outDate) {
        this.username = username;
        this.HID = HID;
        this.checkIn = inDate;
        this.checkOut = outDate;
        this.tariff = tariff;
        this.hotel_name = Hotel;
        this.Address = Address;
        this.City = City;
        days = getDateDifference(checkOut, checkIn) + 1;
        image = "/resources/";
        if(HID%3 == 0){
            Description = "Located in close proximity of the Airport and Bus Stand,  " + Hotel + " offers you premium rooms at extremely affordable rates. ";
            Description += "Book your room today, and leave your stay on us!\n";
            Description += "\nAddress: ";
            Description += Address;
            image += "0.jpg";
        }
        else if(HID%3 == 1){
            Description = "There was an idea. An idea called the H Initiative.";
            Description +="The idea was to bring together a group of remarkable people, see if they could become something more.";
            Description +="See if they could all work together and provide the best of hospitality. So that you worry about having fun.";
            Description +="While we look after everything else\n\n";
            Description +=Address;
            image += "1.jpg";
        }
        else{
            Description = "Home away from home.";
            Description += "We believe that your stay should be as comfortable as your home. And we deliver.";
            Description += "With personalised 24/7 service that is unparallelled elsewhere";
            Description += ("\n\nAddress: " + Address);
            image += "2.jpg";
        }
        ResultSet rs = getResult("SELECT WiFi, Car_Rental, AC, Swimming_Pool, Comp_BF, Number_of_rooms FROM room_info WHERE Hotel_ID = " + HID + ";");
        try{
            rs.next();
            this.number_of_rooms = rs.getInt("Number_of_rooms");
            if(rs.getInt("WiFi") == 1){
                WiFi = yes;
            }
            else{
                WiFi = no;
            }
            if(rs.getInt("Comp_BF") == 1){
                Comp_BF = yes;
            }
            else{
                Comp_BF = no;
            }
            if(rs.getInt("Swimming_Pool") == 1)
                Swimming_Pool = yes;
            else
                Swimming_Pool = no;
            if(rs.getInt("Car_Rental") == 1)
                Car_Rental = yes;
            else
                Car_Rental = no;
            if(rs.getInt("AC") == 1){
                AC = yes;
            }
            else
                AC = no;
        }catch(SQLException e){
            e.printStackTrace();
        }
        initComponents();               
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Image = new javax.swing.JLabel();
        HotelName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DetailsAboutHotel = new javax.swing.JTextArea();
        NumberOfGuests = new javax.swing.JSlider(1,number_of_rooms*3,1);
        ConfirmButton = new javax.swing.JButton();
        WiFiLabel = new javax.swing.JLabel();
        Comp_BFLabel = new javax.swing.JLabel();
        CarRentalLabel = new javax.swing.JLabel();
        SwimmingPoolLabel = new javax.swing.JLabel();
        ACLabel = new javax.swing.JLabel();
        CompBF_img = new javax.swing.JLabel();
        Wifi_img = new javax.swing.JLabel();
        Pool_img = new javax.swing.JLabel();
        AC_img = new javax.swing.JLabel();
        CarRental_img = new javax.swing.JLabel();
        AmenitiesLabel = new javax.swing.JLabel();
        GuestsNumberDisclaimer = new javax.swing.JLabel();
        TnC = new javax.swing.JLabel();
        NumberOfRoomsLabel = new javax.swing.JLabel();
        usernameDisplayLabel = new javax.swing.JLabel();
        IDType = new javax.swing.JComboBox<>();
        EnterIDNumLabel = new javax.swing.JLabel();
        IDNumber = new javax.swing.JTextField();
        CheckOutDateLabel = new javax.swing.JLabel();
        RoomsAvailableInHotel = new javax.swing.JLabel();
        CheckInDateLabel = new javax.swing.JLabel();
        GoBack = new javax.swing.JButton();
        TotalCost = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource(image)));
        Image.setText("Image");

        HotelName.setFont(new java.awt.Font("Noto Sans Mono CJK TC Regular", 1, 18)); // NOI18N
        HotelName.setText(hotel_name +", " + City);
        HotelName.setToolTipText("");

        DetailsAboutHotel.setEditable(false);
        DetailsAboutHotel.setBackground(new java.awt.Color(242, 241, 240));
        DetailsAboutHotel.setColumns(20);
        DetailsAboutHotel.setLineWrap(true);
        DetailsAboutHotel.setRows(5);
        DetailsAboutHotel.setText(Description);
        DetailsAboutHotel.setToolTipText("");
        DetailsAboutHotel.setAutoscrolls(false);
        DetailsAboutHotel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(DetailsAboutHotel);

        NumberOfGuests.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        NumberOfGuests.setMajorTickSpacing(1);
        NumberOfGuests.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                NumberOfGuestsStateChanged(evt);
            }
        });

        ConfirmButton.setText("Confirm");
        ConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmButtonActionPerformed(evt);
            }
        });

        WiFiLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        WiFiLabel.setText("WiFi");

        Comp_BFLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        Comp_BFLabel.setText("Complimentary BF");

        CarRentalLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        CarRentalLabel.setText("Car Rental");

        SwimmingPoolLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        SwimmingPoolLabel.setText("Swimming Pool");

        ACLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        ACLabel.setText("AC");

        CompBF_img.setIcon(new javax.swing.ImageIcon(Comp_BF));

        Wifi_img.setIcon(new javax.swing.ImageIcon(WiFi));

        Pool_img.setIcon(new javax.swing.ImageIcon(Swimming_Pool));

        AC_img.setIcon(new javax.swing.ImageIcon(AC));

        CarRental_img.setIcon(new javax.swing.ImageIcon(Car_Rental));

        AmenitiesLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        AmenitiesLabel.setText("Amenities");

        GuestsNumberDisclaimer.setText("* Three guests maximum per room");

        TnC.setText("By pressing confirm you agree to the Terms and Conditions.");

        NumberOfRoomsLabel.setText("Number of Rooms*: " + rooms_to_book);

        usernameDisplayLabel.setText("Username: " + username);

        IDType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aadhar Card", "Drivers Lisence", "Passport", "Voter ID Card", "Student ID Card" }));
        IDType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IDTypeItemStateChanged(evt);
            }
        });
        IDType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDTypeActionPerformed(evt);
            }
        });

        EnterIDNumLabel.setText("Enter " + IDType.getSelectedItem() + " number:");

        IDNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDNumberActionPerformed(evt);
            }
        });

        CheckOutDateLabel.setText("Check-out: " + checkOut);

        RoomsAvailableInHotel.setText("Rooms available: " + checkAvailability(HID, checkIn, checkOut));

        CheckInDateLabel.setText("Check in: " + checkIn);

        GoBack.setText("Back");
        GoBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoBackActionPerformed(evt);
            }
        });

        TotalCost.setText("Total cost: Rs" + (rooms_to_book * tariff * days));

        jLabel1.setText("Select Number of Guests:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(GoBack)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TnC)
                        .addGap(217, 217, 217))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(CompBF_img, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(NumberOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(178, 178, 178))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(TotalCost)
                                                    .addComponent(NumberOfRoomsLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(usernameDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(EnterIDNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(IDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(IDType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Comp_BFLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(RoomsAvailableInHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CheckOutDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CheckInDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(12, 12, 12)
                                                        .addComponent(Pool_img, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(SwimmingPoolLabel))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(Wifi_img, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(WiFiLabel)
                                                        .addGap(48, 48, 48)
                                                        .addComponent(AC_img, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(16, 16, 16)
                                                        .addComponent(ACLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(CarRental_img, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(CarRentalLabel))))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(HotelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(264, 264, 264)
                                        .addComponent(AmenitiesLabel))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GuestsNumberDisclaimer)
                                .addGap(282, 282, 282)
                                .addComponent(ConfirmButton)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(HotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AmenitiesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(AC_img, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Wifi_img, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(WiFiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Pool_img, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ACLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CarRental_img, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(CarRentalLabel)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(SwimmingPoolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CompBF_img, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(Comp_BFLabel))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GoBack, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NumberOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NumberOfRoomsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TotalCost)
                        .addGap(24, 24, 24)
                        .addComponent(GuestsNumberDisclaimer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(usernameDisplayLabel)
                                .addGap(12, 12, 12)
                                .addComponent(IDType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EnterIDNumLabel)
                                    .addComponent(IDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(CheckInDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CheckOutDateLabel)
                                .addGap(18, 18, 18)
                                .addComponent(RoomsAvailableInHotel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ConfirmButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TnC)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmButtonActionPerformed
        int booking_id = 0;
        String ID_Number = IDNumber.getText();
        if (ID_Number.length() == 0){
             JOptionPane.showMessageDialog(null, "As per government orders, you must present valid ID while booking hotel.", "Enter ID Number", JOptionPane.ERROR_MESSAGE); 
             return;
        }
        String query = "SELECT MAX(Booking_ID) FROM booking_info;";
        ResultSet rs = getResult(query);
        try{
            rs.next();
            int n = rs.getInt("MAX(Booking_ID)");
            if(n==0){
                booking_id = 32647;
            }
            else{
                booking_id = n+1;
            }
            System.out.println("HELLO MOM + n: "  + n);
        }catch(SQLException e){
            e.printStackTrace();
        }
        query = "INSERT INTO booking_info VALUES (";
        query += booking_id;
        query += ",\"";
        query += username;
        query += "\", ";
        query += HID;
        query += ", ";
        
        int guests, total_rooms, rooms_confirmed, rooms_waitlisted, status, available_rooms;
        available_rooms = checkAvailability(HID, checkIn, checkOut);
        guests = NumberOfGuests.getValue();
        total_rooms = (int) guests/3;
        if(guests%3 != 0){
            total_rooms++;
        }
        if(available_rooms >= total_rooms){
            rooms_confirmed = total_rooms;
            rooms_waitlisted = 0;
            status = 0;
        }
        else{
            rooms_confirmed = available_rooms;
            rooms_waitlisted = total_rooms - rooms_confirmed;
            status = 1;
        }
        rs = getResult("SELECT CURDATE()");
        Date today = null;
        try {
            rs.next();
            today = rs.getDate("CURDATE()");
        } catch (SQLException ex) {
            Logger.getLogger(BookingConfirmation.class.getName()).log(Level.SEVERE, null, ex);
        }
        query += rooms_confirmed;
        query += ", ";
        query += rooms_waitlisted;
        query += ", \"";
        query += checkIn;
        query += "\", \"";
        query += checkOut;
        query += "\", \"";
        query += IDType.getSelectedItem();
        query += "\", \"";
        query += ID_Number;
        query += "\", ";
        query += status;
        query+=", \"";
        query += today;
        query += "\", ";
        query+= (tariff*rooms_to_book*days);
        query += ");";
        System.out.println(query);
        InsertRow(query);
        JOptionPane.showMessageDialog(null, "Your booking has been generated with booking ID" + booking_id, "Booking received." , JOptionPane.ERROR_MESSAGE); 
        this.dispose();
        new BookingArea(username).setVisible(true);
        return;
    }//GEN-LAST:event_ConfirmButtonActionPerformed

    private void NumberOfGuestsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_NumberOfGuestsStateChanged
        int n = NumberOfGuests.getValue();
        if(n%3 == 0){
            rooms_to_book = n/3;
        }
        else{
            rooms_to_book = (n/3) + 1;
        }
        NumberOfRoomsLabel.setText("Number of Rooms*: " + rooms_to_book); 
        TotalCost.setText("Total cost: Rs" + (rooms_to_book * tariff * days));
    }//GEN-LAST:event_NumberOfGuestsStateChanged

    private void IDNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDNumberActionPerformed

    private void IDTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IDTypeItemStateChanged
        EnterIDNumLabel.setText("Enter " + IDType.getSelectedItem() + " number:");
    }//GEN-LAST:event_IDTypeItemStateChanged

    private void IDTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDTypeActionPerformed

    private void GoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoBackActionPerformed
        this.dispose();
        BookingArea makeBooking = new BookingArea(username);
        makeBooking.setVisible(true);
    }//GEN-LAST:event_GoBackActionPerformed

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
            java.util.logging.Logger.getLogger(BookingConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ACLabel;
    private javax.swing.JLabel AC_img;
    private javax.swing.JLabel AmenitiesLabel;
    private javax.swing.JLabel CarRentalLabel;
    private javax.swing.JLabel CarRental_img;
    private javax.swing.JLabel CheckInDateLabel;
    private javax.swing.JLabel CheckOutDateLabel;
    private javax.swing.JLabel CompBF_img;
    private javax.swing.JLabel Comp_BFLabel;
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JTextArea DetailsAboutHotel;
    private javax.swing.JLabel EnterIDNumLabel;
    private javax.swing.JButton GoBack;
    private javax.swing.JLabel GuestsNumberDisclaimer;
    private javax.swing.JLabel HotelName;
    private javax.swing.JTextField IDNumber;
    private javax.swing.JComboBox<String> IDType;
    private javax.swing.JLabel Image;
    private javax.swing.JSlider NumberOfGuests;
    private javax.swing.JLabel NumberOfRoomsLabel;
    private javax.swing.JLabel Pool_img;
    private javax.swing.JLabel RoomsAvailableInHotel;
    private javax.swing.JLabel SwimmingPoolLabel;
    private javax.swing.JLabel TnC;
    private javax.swing.JLabel TotalCost;
    private javax.swing.JLabel WiFiLabel;
    private javax.swing.JLabel Wifi_img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel usernameDisplayLabel;
    // End of variables declaration//GEN-END:variables
}
