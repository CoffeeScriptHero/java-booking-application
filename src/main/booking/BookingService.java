package main.booking;

import main.flights.Flight;
import main.flights.FlightController;
import main.passenger.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingService {
    CollectionBookingDao bookingDao = CollectionBookingDao.getInstance();

    public void clearCollection() {
        bookingDao.clearCollection();
    }

    public ArrayList<Booking> getAllBookings() { return bookingDao.getAllBookings(); }

    public void printPrettyFormat(Booking booking, FlightController flightController) {
        Optional<Flight> flight = flightController.getFlightById(booking.getFlightId());
        flight.ifPresent(f -> System.out.printf("%s\n", booking.prettyFormat(f)));
    }


    public void displayAllBookings(FlightController flightController) {
        if (getAllBookings().size() == 0) {
            System.out.println("Booking list is empty\n");
        } else {
            getAllBookings().forEach((b) -> printPrettyFormat(b, flightController));
            System.out.println();
        }
    }

    public void showUserBookings(String name, String surname, FlightController flightController) {
        List<Booking> userBookings = getAllBookings().stream().filter(b -> b.ifUserExist(name, surname)).toList();
        if (userBookings.size() == 0) {
            System.out.println("No bookings found for this user");
        } else {
            System.out.println("This user's bookings: ");
            userBookings.forEach((b) -> printPrettyFormat(b, flightController));
        }
    }

    public void createBooking(int id, int flightId, ArrayList<Passenger> passengers) {
        Booking booking = new Booking(id, flightId, passengers);
        bookingDao.saveBooking(booking);
    }

    public void createBooking(int id, int flightId) {
        Booking booking = new Booking(id, flightId);
        bookingDao.saveBooking(booking);
    }

    public Optional<Booking> getBooking(int id) { return bookingDao.getBooking(id); }

    public boolean deleteBooking(int id) { return bookingDao.deleteBooking(id); }

    public void saveBooking(Booking booking) { bookingDao.saveBooking(booking); }

    public void saveBookingData(ArrayList<Booking> bookings, String fileName) {
        bookingDao.saveBookingData(bookings, fileName);
    }

    public ArrayList<Booking> loadBookingData(String fileName) { return bookingDao.loadBookingData(fileName);}

}
