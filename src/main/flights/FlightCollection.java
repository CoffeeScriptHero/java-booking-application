package main.flights;

import java.io.*;
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
  public Optional<Flight> getFlight(int id) {
    return flights.stream().filter(flight -> flight.getId() == id).findAny();
  }

  @Override
  public Optional<Flight> getFlight(Flight flight) {
    return flights.stream().filter(flight::equals).findAny();
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

  @Override
  public void saveFlightData(List<Flight> flights,String fileName) {
    try (ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream(fileName))) {
      for (Flight flight : flights) { oOS.writeObject(flight);}
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Flight> loadFlightData(String fileName) {
    List<Flight> flights = new ArrayList<>();
    try (
      FileInputStream fIS = new FileInputStream(fileName);
      ObjectInputStream oIS = new ObjectInputStream(fIS)
    ) {
      while(fIS.available() > 0)
      {
        flights.add((Flight)oIS.readObject());
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return flights;
  }

}
