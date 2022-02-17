package flights.test;


import main.flights.Destination;
import main.flights.Flight;
import main.flights.FlightCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;

class FlightCollectionTest {
private FlightCollection flightCollection;
private Flight flight1;
  private Flight flight2;



  @BeforeEach
  void setUp() {
    flightCollection = FlightCollection.instanceOf();
    flight1 = new Flight(LocalDate.of(2022, 3, 5), LocalTime.of(12, 30), Destination.LAS_VEGAS, 2424, 30);
     flight2 = new Flight(LocalDate.of(2022, 4, 12), LocalTime.of(18, 45), Destination.ZURICH, 5478,30);


  }
  @AfterEach
  void tearDown () {
    flightCollection.getAllFlights().clear();
    flightCollection = null;
    flight1 = null;
     flight2 = null;
  }

  @Test
  public void allFlightsAreReturned () {
    flightCollection.getAllFlights().add(flight1);
    flightCollection.getAllFlights().add(flight2);
    assertEquals(2 , flightCollection.getAllFlights().size() );
  }

  @Test
  public void optionalIsPresentIfFlightExistsByID () {
    flightCollection.getAllFlights().add(flight1);
    assertTrue(flightCollection.getFlight(flight1.getId()).isPresent());
  }

  @Test
  public void optionalIsNotPresentIfFlightExistsByID () {
    flightCollection.getAllFlights().add(flight1);
    assertTrue(flightCollection.getFlight(flight2.getId()).isEmpty());
  }

  @Test
  public void optionalIsPresentIfFlightExistsByInstance () {
    flightCollection.getAllFlights().add(flight1);
    assertTrue(flightCollection.getFlight(flight1).isPresent());
  }

  @Test
  public void optionalIsNotPresentIfFlightExistsByInstance () {
    flightCollection.getAllFlights().add(flight1);
    assertTrue(flightCollection.getFlight(flight2).isEmpty());
  }
  @Test
  public void flightIsActuallyDeleted () {
    flightCollection.getAllFlights().add(flight1);
    flightCollection.deleteFlight(flight1.getId());
    assertTrue(flightCollection.getFlight(flight1.getId()).isEmpty());
  }

  @Test
  public void flightIsSavedIfNotExists () {
    flightCollection.saveFlight(flight1);
    flightCollection.saveFlight(flight2);
    assertEquals(flightCollection.getFlight(flight2).get(), flight2);
  }

  @Test
  public void flightIsModifiedIfExists () {
    flightCollection.saveFlight(flight1);
    flightCollection.saveFlight(flight1);
    assertEquals(flightCollection.getAllFlights().size(), 1);
  }

  @Test
  public void SaveFlightDataActuallySavesFlightsToFile() {
    flightCollection.saveFlight(flight1);
    flightCollection.saveFlightData(flightCollection.getAllFlights(), "flights_db_test.txt");
    assertEquals(flightCollection.loadFlightData("flights_db_test.txt"), flightCollection.getAllFlights());

  }

  @Test
  public void LoadFlightDataActuallyLoadsFlightsFromFile() {
    flightCollection.saveFlight(flight1);
    flightCollection.saveFlight(flight2);
    flightCollection.saveFlightData(flightCollection.getAllFlights(), ("flights_db_test.txt"));
    assertEquals(flightCollection.getAllFlights(), flightCollection.loadFlightData("flights_db_test.txt"));
  }
}