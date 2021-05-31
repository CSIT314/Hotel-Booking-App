package app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;


public class TestBookingConfirmation {

    BookingConfirmation bookingConfirmation;

    @Test
    public void testBooking() {
        System.out.println(bookingConfirmation.Description);
        System.out.println(bookingConfirmation.rooms_to_book);
        System.out.println(bookingConfirmation.days);
        System.out.println(bookingConfirmation.tariff);
        System.out.println(bookingConfirmation.number_of_rooms);
        assertEquals(bookingConfirmation.HID, 1);
        assertEquals(bookingConfirmation.Address, "TestAddress");
        assertEquals(bookingConfirmation.City, "TestCity");
        assertEquals(bookingConfirmation.tariff, 1);
        assertEquals(bookingConfirmation.days, 1);
    }

    @Test
    public void testBookRoom() {
        String address = "testAddress";
        String hotelName = "testName";
        String hotel = "testHotel";
        String city = "testCity";
        String name = "testName";
        int rooms_to_book = bookingConfirmation.rooms_to_book;
        int number_of_rooms = bookingConfirmation.number_of_rooms;
        for (int i = 0; i < 5; i++) {
            BookingConfirmation bookingConfirmation = new BookingConfirmation(name, 1, hotel, address, city, 1, new java.sql.Date(System.currentTimeMillis() - 3600 ), new Date(System.currentTimeMillis()));
            assertEquals(bookingConfirmation.number_of_rooms, number_of_rooms - i );
            assertEquals(bookingConfirmation.rooms_to_book, 1);
            bookingConfirmation = null;
        }
    }

    @Before
    public void setUp() throws Exception {
        this.bookingConfirmation = new BookingConfirmation("TestName", 1, "TestHotel", "TestAddress", "TestCity", 1, new java.sql.Date(System.currentTimeMillis() - 3600 * 24 * 3), new Date(System.currentTimeMillis()));
    }

    @After
    public void shutdown() throws Exception {
        bookingConfirmation = null;
    }

}
