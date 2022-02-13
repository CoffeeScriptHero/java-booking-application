package main;

import main.flights.*;

public class Main {

  public static void main(String[] args) {
    FlightDAO flightDAO = FlightCollection.instanceOf();
    FlightService flightService = new FlightService(flightDAO);
    FlightController flightController = new FlightController(flightService);
    flightController.generateTestData();
//    flightController.displayAllFlights();
    flightController.getFlightById(54).ifPresent(Flight::prettyFormat);
  }
}
