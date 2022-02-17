package flights.test;

import main.flights.Destination;
import main.flights.Flight;
import main.flights.FlightCollection;
import main.flights.FlightService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
  private FlightCollection flightCollection = FlightCollection.instanceOf();
  private FlightService flightService;
  private Flight flight1;
  private Flight flight2;
  private Flight flight3;

  @BeforeEach
  void setUp() {
    flightService = new FlightService(flightCollection);
    flight1 = new Flight(LocalDate.of(2022, 3, 5), LocalTime.of(12, 30), Destination.LAS_VEGAS, 2424, 30);
    flight2 = new Flight(LocalDate.of(2022, 4, 12), LocalTime.of(18, 45), Destination.ZURICH, 5478,30);
    flight3 = new Flight(LocalDate.of(2022, 4, 12), LocalTime.of(18, 45), Destination.ZURICH, 1145,30);
  }
  @AfterEach
  void tearDown () {
    flightService.getAllFlights().clear();
    flightService = null ;
    flightCollection = null;
    flight1 = null;
    flight2 = null;
    flight3 = null;
  }

  @Test
  void doesFlightExistReturnsTrueIfItExists() {
    flightService.getAllFlights().add(flight1);
    assertTrue(flightService.doesFlightExist(flight1.getId()));
  }
  @Test
  void doesFlightExistReturnsFalseIfItDoesNotExist() {
    flightService.getAllFlights().add(flight1);
    assertFalse(flightService.doesFlightExist(flight2.getId()));
  }


  @Test
  void flightsExistReturnsTrueIfThereIsAtLeastOneFlight() {
    flightService.getAllFlights().add(flight1);
    assertTrue(flightService.flightsExist());
  }

  @Test
  void flightsExistReturnsFalseIfThereAreNoFlights() {
    assertFalse(flightService.flightsExist());
  }

  @Test
  void availableSeatsExistById() {
    flightService.getAllFlights().add(flight1);
    assertTrue(flightService.availableSeatsExist(flight1.getId(), 14));

  }
  @Test
  void availableSeatsDoNotExistById() {
    flightService.getAllFlights().add(flight1);
    assertFalse(flightService.availableSeatsExist(flight1.getId(), 35));
  }
  @Test
  void availableSeatsExistByFlightInstance() {
    flightService.getAllFlights().add(flight1);
    assertTrue(flightService.availableSeatsInFlight(flight1, 14));

  }
  @Test
  void availableSeatsDoNotExistByFlightInstance() {
    flightService.getAllFlights().add(flight1);
    assertFalse(flightService.availableSeatsInFlight(flight1, 35));
  }



  @Test
  void destinationIsAvailable() {
    String destination = "Zurich";
    assertTrue(flightService.isDestinationAvailable(destination));
  }
  @Test
  void destinationIsNotAvailable() {
    String destination = "Panama";
    assertFalse(flightService.isDestinationAvailable(destination));
  }
  @Test
  void deleteAvailableSeats() {
    flightService.getAllFlights().add(flight1);
    flightService.deleteAvailableSeats(flight1.getId(), 15);
    assertEquals(15, flight1.getAvailableSeats());
  }

  @Test
  void addAvailableSeats() {
    flightService.getAllFlights().add(flight1);
    flightService.addAvailableSeats(flight1.getId(), 15);
    assertEquals(45, flight1.getAvailableSeats());

  }

  @Test
  void findAvailableFlights() {
    List<Flight> flightsTestList = Arrays.asList(flight2,flight3);
    flightService.getAllFlights().add(flight1);
    flightService.getAllFlights().add(flight2);
    flightService.getAllFlights().add(flight3);
List<Flight> availableFlights = flightService.findAvailableFlights("Zurich",LocalDate.of(2022,4,12),10);
    assertEquals(flightsTestList, availableFlights);
  }

  @Test
  void CanNotFindAvailableFlights() {
    flightService.getAllFlights().add(flight1);
    flightService.getAllFlights().add(flight2);
    flightService.getAllFlights().add(flight3);
    List<Flight> availableFlights = flightService.findAvailableFlights("Singapore",LocalDate.of(2022,4,12),10);
    assertTrue(availableFlights.isEmpty());
  }
  @Test
  void testDataIsGenerated() {
    flightService.generateTestData();
    assertEquals(flightService.getAllFlights().size(),15);
  }
}