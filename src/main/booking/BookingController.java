package main.booking;

import main.flights.FlightController;
import main.passenger.Passenger;

import java.util.ArrayList;
import java.util.Optional;

public class BookingController {
    BookingService bookingService = new BookingService();

    public void clearCollection() {bookingService.clearCollection();}

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

    public void createBooking(int id, int flightId, ArrayList<Passenger> passengers) {
        bookingService.createBooking(id, flightId, passengers);
    }

    public void createBooking(int id, int flightId) {
        bookingService.createBooking(id, flightId);
    }

    public Passenger createPassenger(String name, String lastname) {
        return bookingService.createPassenger(name, lastname);
    }

    public Optional<Booking> getBooking(int id) { return bookingService.getBooking(id); }

    public boolean deleteBooking(int id) { return bookingService.deleteBooking(id); }

    public void saveBooking(Booking booking) { bookingService.saveBooking(booking); }

    public void saveBookingData(ArrayList<Booking> bookings, String fileName) {
        bookingService.saveBookingData(bookings, fileName);
    }

    public ArrayList<Booking> loadBookingData(String fileName) {return bookingService.loadBookingData(fileName);}
}
