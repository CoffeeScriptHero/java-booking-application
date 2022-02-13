package main.flights;

import java.util.List;
import java.util.Optional;

public interface FlightDAO {

  List<Flight> getAllFlights();

  Optional<Flight> getFlightById(int id);

  void deleteFlight(int id);

  void saveFlight(Flight Flight);
}
