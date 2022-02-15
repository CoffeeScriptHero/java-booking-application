package main.flights;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightController {
  private final FlightService flightService;

  public FlightController(FlightService flightService) {
    this.flightService = flightService;
  }

  public List<Flight> getAllFlights() {
    return flightService.getAllFlights();
  }

public boolean doesFlightExist (int id ) { return flightService.doesFlightExist(id);};

  public Optional<Flight> getFlight(int id) {
    return flightService.getFlight(id);
  }
  public Optional<Flight> getFlight(Flight flight) {
    return flightService.getFlight(flight);
  }
  public void deleteFlight(int id) {flightService.deleteFlight(id);}

  public void saveFlight(Flight flight) {flightService.saveFlight(flight);}

  public boolean flightsExist() {
    return (flightService.getAllFlights().size() > 0);
  }

  public void displayFlights(List<Flight> flights) {flightService.displayFlights(flights);}

  public void displayAllFlights () { flightService.displayAllFlights();}

  public void generateTestData() { flightService.generateTestData();}

  public List<Flight> findAvailableFlights (String destination, LocalDate date, int seats) {
    return flightService.findAvailableFlights(destination,date,seats);
  }

  public  boolean availableSeatsExist (int id, int size) {
    return flightService.availableSeatsExist(id, size);
  }


  public boolean isDestinationAvailable (String destination) {
   return flightService.isDestinationAvailable(destination);
  }

  public void deleteAvailableSeats (int id, int seats) {
    flightService.deleteAvailableSeats(id, seats);
  }
  public void addAvailableSeats (int id, int seats) {
    flightService.addAvailableSeats(id, seats);
  }
  public void saveFlightData(List<Flight> flights, String fileName) {
    flightService.saveFlightData(flights,fileName);
  }


  public List<Flight> loadFlightData(String fileName) {
    return flightService.loadFlightData(fileName);
  }
}
