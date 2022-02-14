package main.booking;

import main.flights.FlightController;

import java.util.ArrayList;
import java.util.Optional;

public class BookingController {
    BookingService bookingService = new BookingService();

    public ArrayList<Booking> getAllBookings() { return bookingService.getAllBookings(); }

    public void displayAllBookings(FlightController fc) {bookingService.displayAllBookings(fc);}

    public void showUserBookings(String name, String surname, FlightController fc) {
        bookingService.showUserBookings(name, surname, fc);
    }

    public Optional<Booking> getBooking(int id) { return bookingService.getBooking(id); }

    public boolean deleteBooking(int id) { return bookingService.deleteBooking(id); }

    public void saveBooking(Booking booking) { bookingService.saveBooking(booking); }
}
