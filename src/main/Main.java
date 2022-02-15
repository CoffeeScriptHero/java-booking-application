package main;

import main.flights.*;

import java.time.LocalDate;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    FlightDAO flightDAO = FlightCollection.instanceOf();
    FlightService flightService = new FlightService(flightDAO);
    FlightController flightController = new FlightController(flightService);
    flightController.generateTestData();
    flightController.getFlightById(54).ifPresent(Flight::prettyFormat);
    List<Flight> list = flightController.findAvailableFlights("Las Vegas", LocalDate.of(2022, 3, 5), 10);
    System.out.println(list);
  }
}
