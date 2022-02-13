package main.flights;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightCollection implements FlightDAO {

  private static final FlightCollection flightCollection = new FlightCollection();
  private final List<Flight> flights = new ArrayList<>();

  private FlightCollection() {
  }

  public static FlightCollection instanceOf() {
    return flightCollection;
  }


  @Override
  public List<Flight> getAllFlights() {
    return flights;
  }

  @Override
  public Optional<Flight> getFlightById(int id) {
    Optional<Flight> optionalFlight = flights.stream().filter(flight -> flight.getId() == id).findAny();
    if(optionalFlight.isEmpty()) {
      System.out.println("We couldn't find a flight for this ID");
    }
    return optionalFlight;

  }

  @Override
  public void deleteFlight(int id) {
    flights.removeIf(flight -> flight.getId() == id);
  }

  @Override
  public void saveFlight(Flight flight) {
    if (flights.size() >= 1 && flights.stream().anyMatch(flight::equals)) {
      int replaceIndex = flights.indexOf(flight);
      flights.set(replaceIndex, flight);
    } else {
      flights.add(flight);
    }
  }
}
