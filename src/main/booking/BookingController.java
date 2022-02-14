package main.booking;

import main.flights.FlightController;

import java.util.ArrayList;
import java.util.Optional;

public class BookingController {
    BookingService bookingService = new BookingService();

    public ArrayList<Booking> getAllBookings() { return bookingService.getAllBookings(); }

    public void printPrettyFormat(Booking booking, FlightController flightController) {
        bookingService.printPrettyFormat(booking, flightController);
    }

    public void displayAllBookings(FlightController flightController) {
        bookingService.displayAllBookings(flightController);
    }

    public void showUserBookings(String name, String surname, FlightController flightController) {
        bookingService.showUserBookings(name, surname, flightController);
    }

    public Optional<Booking> getBooking(int id) { return bookingService.getBooking(id); }

    public boolean deleteBooking(int id) { return bookingService.deleteBooking(id); }

    public void saveBooking(Booking booking) { bookingService.saveBooking(booking); }

    public void saveBookingData(ArrayList<Booking> bookings, String fileName) {
        bookingService.saveBookingData(bookings, fileName);
    }

    public ArrayList<Booking> loadBookingData(String fileName) {return bookingService.loadBookingData(fileName);}
}
