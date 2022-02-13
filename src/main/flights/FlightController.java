package main.flights;

import java.util.List;
import java.util.Optional;

public class FlightController {
  private final FlightService flightService;

  public FlightController(FlightService flightService) {
    this.flightService = flightService;
  }

  public List<Flight> getAllFlights() {
    return flightService.getAllFlights();
  }

  public Optional<Flight> getFlightById(int id) {
    return flightService.getFlightById(id);
  }

  public void deleteFlight(int id) {flightService.deleteFlight(id);}

  public void saveFlight(Flight flight) {flightService.saveFlight(flight);}

  public boolean flightsExist() {
    return (flightService.getAllFlights().size() > 0);
  }

  public void displayFlights(List<Flight> flights) {flightService.displayFlights(flights);}

  public void displayAllFlights () { flightService.displayAllFlights();}

  public void generateTestData() { flightService.generateTestData();}

}
