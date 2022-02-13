package main.booking;

import java.util.ArrayList;

public class BookingController {
    BookingService bookingService = new BookingService();

    public ArrayList<Booking> getAllBookings() { return bookingService.getAllBookings(); }

    public Booking getBooking(int id) { return bookingService.getBooking(id); }

    public boolean deleteBooking(int id) { return bookingService.deleteBooking(id); }

    public void saveBooking(Booking booking) { bookingService.saveBooking(booking); }
}
