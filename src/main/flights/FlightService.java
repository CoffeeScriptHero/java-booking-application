package main.flights;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlightService {
  private final FlightDAO flightDAO;
  public FlightService(FlightDAO flightDAO) {
    this.flightDAO = flightDAO;
  }


  public List<Flight> getAllFlights() {
    return flightDAO.getAllFlights();
  }

  public Optional<Flight> getFlightById(int id) {
    return flightDAO.getFlightById(id);
  }

  public boolean doesFlightExist (int id) {
    return flightDAO.getAllFlights().stream().anyMatch(flight -> flight.getId() == id);
  }

  public void deleteFlight(int id) {flightDAO.deleteFlight(id);}


  public void saveFlight(Flight flight) {flightDAO.saveFlight(flight);}

  public boolean flightsExist() {
    return (flightDAO.getAllFlights().size() > 0);
  }

  public  boolean availableSeatsExist (int id, int size) {
    if(doesFlightExist(id)) {
      return getFlightById(id).get().getAvailableSeats() - size >= 0;
    } else  return false;
    }


public boolean availableSeatsInFlight (Flight flight , int size) {
  return flight.getAvailableSeats() - size >= 0;
}

    public boolean isDestinationAvailable (String destination) {
     return Arrays.stream(Destination.values()).anyMatch(existingDestination -> existingDestination.getName().equals(destination));
    }

    public void deleteAvailableSeats (int id, int seats) {
      flightDAO.getFlightById(id).get().deleteSeats(seats);
    }
    public void addAvailableSeats (int id, int seats) {
      flightDAO.getFlightById(id).get().addSeats(seats);
    }

  public List<Flight> findAvailableFlights (String destination, LocalDate date, int seats) {
 return getAllFlights().stream()
   .filter(flight ->
     flight.getDestination().getName().equals(destination) && flight.getDate().isEqual(date) && availableSeatsInFlight(flight, seats))
   .collect(Collectors.toList());
}

  public void displayFlights(List<Flight> flights) {
    if (!flightsExist()) {
      System.out.println("No flights to display");
    } else {
      IntStream.range(0, flights.size()).forEach(i -> flights.get(i).prettyFormat());
    }
  }
public void displayAllFlights () {
    if(flightsExist()) {
      displayFlights(getAllFlights());
    }
}

public void generateTestData () {
  Flight flight1 = new Flight(LocalDate.of(2022,3,5), LocalTime.of(12,30), Destination.LAS_VEGAS, 2424, 30);
  Flight flight2 = new Flight(LocalDate.of(2022,4,12), LocalTime.of(18,45), Destination.ZURICH, 5478, 30);
  Flight flight3 = new Flight(LocalDate.of(2022,5,13), LocalTime.of(22,0), Destination.BERLIN, 1647, 30);
  Flight flight4 = new Flight(LocalDate.of(2022,6,8), LocalTime.of(2,0), Destination.BORA_BORA, 1246, 30);
  Flight flight5 = new Flight(LocalDate.of(2022,8,28), LocalTime.of(7,15), Destination.MADRID, 8968, 30);
  Flight flight6 = new Flight(LocalDate.of(2022,9,17), LocalTime.of(15,30), Destination.SYDNEY, 4578, 30);
  Flight flight7 = new Flight(LocalDate.of(2022,12,13), LocalTime.of(23,45), Destination.TOKYO, 4567, 30);
  Flight flight8 = new Flight(LocalDate.of(2022,10,2), LocalTime.of(1,30), Destination.OSAKA, 7854, 30);
  Flight flight9 = new Flight(LocalDate.of(2022,11,14), LocalTime.of(16,0), Destination.SINGAPORE, 3625, 30);
  Flight flight10 = new Flight(LocalDate.of(2022,5,1), LocalTime.of(18,0), Destination.BALI, 8972, 30);
  Flight flight11 = new Flight(LocalDate.of(2022,7,10), LocalTime.of(19,30), Destination.PRAGUE, 1973, 30);
  Flight flight12 = new Flight(LocalDate.of(2022,9,16), LocalTime.of(20,15), Destination.ROME, 1672, 30);
  Flight flight13 = new Flight(LocalDate.of(2022,12,22), LocalTime.of(5,45), Destination.TEL_AVIV, 8264, 30);
  Flight flight14 = new Flight(LocalDate.of(2022,8,23), LocalTime.of(4,0), Destination.PHUKET, 1973, 30);
  Flight flight15 = new Flight(LocalDate.of(2022,7,9), LocalTime.of(3,30), Destination.MAUI, 4672, 30);
  saveFlight(flight1);
  saveFlight(flight2);
  saveFlight(flight3);
  saveFlight(flight4);
  saveFlight(flight5);
  saveFlight(flight6);
  saveFlight(flight7);
  saveFlight(flight8);
  saveFlight(flight9);
  saveFlight(flight10);
  saveFlight(flight11);
  saveFlight(flight12);
  saveFlight(flight13);
  saveFlight(flight14);
  saveFlight(flight15);
}
}
