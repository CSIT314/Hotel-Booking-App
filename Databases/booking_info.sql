CREATE TABLE booking_info(
Booking_ID INT NOT NULL,
username VARCHAR(255) NOT NULL, 
Room_ID INTEGER NOT NULL,
Hotel_ID INTEGER NOT NULL,
Date_In DATE NOT NULL,
Date_Out DATE NOT NULL,
Valid INT NOT NULL,
PRIMARY KEY (Booking_ID),
FOREIGN KEY (username) REFERENCES user_info(username),
FOREIGN KEY (Room_ID) REFERENCES room_info(Room_ID)
);