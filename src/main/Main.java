package main;

import main.booking.Booking;
import main.booking.BookingController;
import main.flights.*;

public class Main {

  public static void main(String[] args) {
    FlightDAO flightDAO = FlightCollection.instanceOf();
    FlightService flightService = new FlightService(flightDAO);
    FlightController flightController = new FlightController(flightService);
    flightController.generateTestData();
    flightController.displayAllFlights();
    flightController.getFlightById(54).ifPresent(Flight::prettyFormat);

    BookingController bc = new BookingController();
    Booking book = new Booking(1, 2424);
    Booking book2 = new Booking(2, 1973);
    book.addPassenger("Denis", "Kozarenko");
    book.addPassenger("Vanya", "fsdfds");
    book2.addPassenger("Denis", "Kozarenko");
    bc.saveBooking(book);
    bc.saveBooking(book2);
    bc.deleteBooking(2);
    book.printId();
    bc.showUserBookings("Denis", "Kozarenko", flightController);
  }
}
