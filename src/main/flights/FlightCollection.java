package main.flights;

import java.util.ArrayList;
import java.util.List;

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
  public Flight getFlightById(int id) {
    return flights.get(id);
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
