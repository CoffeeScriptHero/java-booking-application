package main;

import main.booking.Booking;
import main.booking.BookingController;
import main.flights.*;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    FlightDAO flightDAO = FlightCollection.instanceOf();
    FlightService flightService = new FlightService(flightDAO);
    FlightController flightController = new FlightController(flightService);
    flightController.generateTestData();
    flightController.displayAllFlights();
//    flightController.getFlightById(54).ifPresent(Flight::prettyFormat);

    BookingController bc = new BookingController();
    Booking book1 = new Booking(1, 1973);
    Booking book2 = new Booking(1, 3625);
//    bc.saveBooking(book1);
//    bc.saveBooking(book2);

    bc.saveBookingData(new ArrayList<Booking>(){{
      add(book1);
      add(book2);
    }}, "bookings_db.txt");

    bc.displayAllBookings(flightController);
    System.out.println(bc.loadBookingData("bookings_db.txt"));

//    private void loadBookings() {
//      int counter = 0;
//      for (Booking b : bc.loadBookingData("bookings_db.txt")) {
//        counter++;
//        bc.saveBooking(b);
//      }
//      if (counter > 0) System.out.println("Брони были успешно загружены из базы данных");
//      else System.out.println("В базе данных пусто");
//    } такой метод можно использовать в классе меню

  }
}
