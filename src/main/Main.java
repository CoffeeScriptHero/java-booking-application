package main;

import main.booking.Booking;
import main.booking.BookingController;
import main.flights.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Main {

  private static final String FILE_NAME_BOOKING = "bookings_db.txt";
  private static final String FILE_NAME_FLIGHTS = "flights_db.txt";

  private static final FlightDAO flightDAO = FlightCollection.instanceOf();
  private static final FlightService flightService = new FlightService(flightDAO);
  private static final FlightController flightController = new FlightController(flightService);
  private static final BookingController bookingController = new BookingController();
  private static final Console console = new Console(flightController, bookingController);

  public static void main(String[] args) {
    flightController.generateTestData();
      loadBookings();
      loadFlights();
      console.startConsole();
      saveData();
  }

  private static void loadBookings() {
    for (Booking booking : bookingController.loadBookingData(FILE_NAME_BOOKING)) {
      bookingController.saveBooking(booking);
    }
  }
  private static void loadFlights() {
    for (Flight flight : flightController.loadFlightData(FILE_NAME_FLIGHTS)) {
      flightController.saveFlight(flight);
    }
  }

  private static void saveData() {
    bookingController.saveBookingData(bookingController.getAllBookings(), FILE_NAME_BOOKING);
    flightController.saveFlightData(flightController.getAllFlights(), FILE_NAME_FLIGHTS);
    System.exit(0);
  }
}