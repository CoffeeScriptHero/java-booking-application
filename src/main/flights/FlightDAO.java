package main.flights;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FlightDAO {

  List<Flight> getAllFlights();

  Optional<Flight> getFlight(int id);
  Optional<Flight> getFlight(Flight flight);
  void deleteFlight(int id);

  void saveFlight(Flight Flight);

  void saveFlightData (List<Flight> flights,String fileName) ;

  List<Flight> loadFlightData (String fileName);

}
