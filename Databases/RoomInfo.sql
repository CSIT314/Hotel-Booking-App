CREATE TABLE room_info(
   Hotel_ID        INTEGER  NOT NULL PRIMARY KEY 
  ,Hotel_Name      VARCHAR(26) NOT NULL
  ,Address         VARCHAR(105) NOT NULL
  ,Number_of_rooms INTEGER  NOT NULL
  ,City            VARCHAR(11) NOT NULL
  ,Tariff          INTEGER  NOT NULL
  ,Comp_BF         BIT  NOT NULL
  ,WiFi            BIT  NOT NULL
  ,Car_Rental      BIT  NOT NULL
  ,AC              BIT  NOT NULL
  ,Swimming_Pool   BIT  NOT NULL
);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (1,'Taj Intercontinental','B-35/1, Race Course Road, South Bombay, Memapur',5,'Mumbai',32300,1,1,1,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (2,'Hotel Bhimas','High Court Road, Nyay Marg, Townie Central, Navi Mumbai',4,'Mumbai',8700,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (3,'Brigade Metropolis Plus','Gulab Estate, T2 Terminal International Airport, MTNL Road, Sakinaka, Andheri, Mumbai, Maharashtra 400072',7,'Mumbai',10249,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (4,'Oyo Manikantha 5166','Gulab Estate, 8B, Sakinaka Tele Exchange Lane, T2 Airport Road, Sakinaka',6,'Mumbai',3400,1,1,1,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (5,'Hotel Grandeur','Crystal Plaza Plot N0.18/27, Sector 7 Kharghar, Navi Mumbai',4,'Mumbai',4500,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (6,'Geetha Apsara Residemcy','Danavaipeta, opposite Anand Regancy Theatre',5,'Rajahmundry',940,0,0,1,0,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (7,'Shelton Grand','26-3-7, Jampet, NAD Road',4,'Rajahmundry',2400,0,1,0,1,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (8,'Udipi Akshaya','NH-162, Bobbaralanka Nagar, near Kadiyam',6,'Rajahmundry',1300,0,1,0,1,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (9,'Satya Homestay','Maisamma Chaurassa Circle, Near Satyam Brewing Agencies, NTPL Road',9,'Rajahmundry',450,0,0,1,0,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (10,'La Hospin','101-A/2, Raj Marg, New Delhi Capital Area (UT)',3,'Delhi',3400,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (11,'Rafale Grand','Door NM-101, Scamabad, Bharath Vihar Nagar, Gandhinagar',4,'Delhi',8500,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (12,'Tempe Residency','Wahi Bagh, Banega Nagar, Gurgaon-Delhi Religious Highway',5,'Delhi',9250,1,1,1,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (13,'Oyo 2020','Vision Metropolis Complex, near AIIMS Delhi, Birla Nagar',6,'Delhi',1560,1,1,0,1,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (14,'Hotel Unity','G-101/A, Gandhi Road, MG Nagar',5,'Vadodara',3300,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (15,'Democles Grand','7, Jetalpur Rd, Sampatrao Colony, Alkapuri',6,'Vadodara',2100,0,1,1,1,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (16,'Geass Residencies','Layout 54-3/A, Sardar Vallabhai Nagar, Iron Man Road',5,'Vadodara',1070,0,1,0,0,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (17,'Yagami Heights','Comicon Road, Gacchibowli Area, opposite Light Avenue',5,'Hyderabad',1050,0,1,0,1,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (18,'Valmiki Bhawan, BPHC','Shameerpet (de)Forested Area, near Jawaharnagar, Thumukunta',5,'Hyderabad',350,0,0,1,0,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (19,'Alankrita Holistic Resorts','Middle of nowhere, Thumukunta, CRPF Highway – 500078',5,'Hyderabad',5840,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (20,'Capital O Metropolis','A90, Manikantha Marg, near SC Railway Station',4,'Hyderabad',5800,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (21,'Nestlay Cafa','New No. 6, Old No. 9, Plot No. 7, Zerald Garden, Second Lane, Casa Major Road, Egmore',5,'Chennai',5040,1,1,1,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (22,'Regentra Central','38, Royapettah High Rd, Jagadambal colony, Sripuram, Teachers Colony, Royapettah',3,'Chennai',6980,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (23,'Ulli Residency','Rama Pillai Street,Jawaharlal nehru Stadium, Periyamet',7,'Chennai',7050,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (24,'The Belstead','103, Nungambakkam High Rd, Ponnangipuram, Nungambakkam',4,'Chennai',13425,1,1,1,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (25,'Kantha Brigade','near National Institute of Design, Miyapur, Thumakuru',6,'Bangalore',2990,1,1,0,1,0);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (27,'Leela Palace','23, HAL Old Airport Rd, HAL 2nd Stage, Kodihalli',3,'Bangalore',4570,1,1,0,1,1);
INSERT INTO room_info(Hotel_ID,Hotel_Name,Address,Number_of_rooms,City,Tariff,Comp_BF,WiFi,Car_Rental,AC,Swimming_Pool) VALUES (29,'Hotel Sitara','VM-123/4, Gandhi Road, opposite BSNL Bhawan, Kotagummam',5,'Bangalore',1250,0,1,0,1,0);
