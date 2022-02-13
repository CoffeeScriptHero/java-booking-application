import flights.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

  public static void main(String[] args) {
    FlightDAO flightDAO = FlightCollection.instanceOf();
    FlightService flightService = new FlightService(flightDAO);
    FlightController flightController = new FlightController(flightService);
    flightController.generateTestData();
    flightController.displayAllFlights();
  }
}
