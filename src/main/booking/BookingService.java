package main.booking;

import main.flights.Flight;
import main.flights.FlightController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingService {
    CollectionBookingDao bookingDao = CollectionBookingDao.getInstance();

    public ArrayList<Booking> getAllBookings() { return bookingDao.getAllBookings(); }

    public void displayAllBookings(FlightController fc) {
        if (getAllBookings().size() == 0) {
            System.out.println("Booking list is empty\n");
        } else {
            getAllBookings().forEach((b) -> {
                Optional<Flight> flight = fc.getFlightById(b.getFlightId());
                flight.ifPresent(f -> System.out.printf("%s\n", b.prettyFormat(f)));
            });
            System.out.println();
        }
    }

    public void showUserBookings(String name, String surname, FlightController fc) {
        List<Booking> userBookings = getAllBookings().stream().filter(b -> b.ifUserExist(name, surname)).toList();
        if (userBookings.size() == 0) {
            System.out.println("\nNo bookings found for this user");
        } else {
            System.out.println("\nThis user's bookings: ");
            userBookings.forEach((b) -> {
                Optional<Flight> flight = fc.getFlightById(b.getFlightId());
                flight.ifPresent(f -> System.out.printf("%s\n", b.prettyFormat(f)));
            });
        }
    }

    public Optional<Booking> getBooking(int id) { return bookingDao.getBooking(id); }

    public boolean deleteBooking(int id) { return bookingDao.deleteBooking(id); }

    public void saveBooking(Booking booking) { bookingDao.saveBooking(booking); }

}
