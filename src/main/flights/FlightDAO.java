package main.flights;

import java.util.List;

public interface FlightDAO {

  List<Flight> getAllFlights();

  Flight getFlightById(int id);

  void deleteFlight(int id);

  void saveFlight(Flight Flight);
}
