package main;

import main.booking.BookingController;
import main.errors.NonExistentMenuName;
import main.flights.*;
import main.passenger.Passenger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {

  private static final String ONLINE_BOARD = "Онлайн-табло";
  private static final String SHOW_INFORMATION = "Посмотреть информацию о рейсе";
  private static final String SEARCH_BOOKING = "Поиск и бронировка рейса";
  private static final String CANCEL_RESERVATION = "Отменить бронирование";
  private static final String MY_FLIGHTS = "Мои рейсы";
  private static final String EXIT = "Выход";
  private static final String ERROR = "The data you entered is incorrect! Try again!";

  private static final FlightDAO flightDAO = FlightCollection.instanceOf();
  private static final FlightService flightService = new FlightService(flightDAO);
  private static final FlightController flightController = new FlightController(flightService);
  private static final BookingController bookingController = new BookingController();


  private static final ArrayList<String> menuTabs = new ArrayList<>();
  private static final Scanner scanner = new Scanner(System.in);
  private static Boolean isRunning = true;

  public static void main(String[] args) {
    FlightDAO flightDAO = FlightCollection.instanceOf();
    FlightService flightService = new FlightService(flightDAO);
    FlightController flightController = new FlightController(flightService);
    flightController.generateTestData();
    putMenuTabs();
    while (isRunning) {
      showMenu();
      chooseMenuTab(scanner.nextLine());
    }
  }

  private static void putMenuTabs() {
    menuTabs.add(ONLINE_BOARD);
    menuTabs.add(SHOW_INFORMATION);
    menuTabs.add(SEARCH_BOOKING);
    menuTabs.add(CANCEL_RESERVATION);
    menuTabs.add(MY_FLIGHTS);
    menuTabs.add(EXIT);
  }

  public static void showMenu() {
    System.out.println("\n------------Главное меню------------");
    for(String tab: menuTabs) {
      System.out.printf("| %s |\n", tab);
    }
    System.out.println("------------------------------------");
  }

  public static void chooseMenuTab(String line) {
    try {
      String finalLine = line.toLowerCase().trim();
      String searchMenuTab = menuTabs
              .stream()
              .filter(value -> finalLine.equals(value.toLowerCase()))
              .findAny().orElse("");
      switch(searchMenuTab) {
        case ONLINE_BOARD -> showOnlineBoard();
        case SHOW_INFORMATION -> showInformation();
        case SEARCH_BOOKING -> showSearchBooking();
        case CANCEL_RESERVATION -> showCancelReservation();
        case MY_FLIGHTS ->  showMyFlights();
        case EXIT -> isRunning = false;
        default -> throw new NonExistentMenuName("There is no such menu item! Try again!");
      }
    } catch (NonExistentMenuName exMessage) {
      System.err.println(exMessage);
      chooseMenuTab(scanner.nextLine());
    } catch (NumberFormatException | DateTimeParseException exMessage) {
      System.err.println(ERROR);
      chooseMenuTab(line);
    } catch (IndexOutOfBoundsException exMessage) {
      System.err.println(ERROR);
      setNumberFlightTab();
    }
  }

  private static void showInformation() {
    System.out.println("Please write flight id!");
    int id = Integer.parseInt(scanner.nextLine());
    flightController.getFlight(id).ifPresent(Flight::prettyFormat);
  }

  private static void showOnlineBoard() {
    flightController.displayAllFlights();
  }

  private static int countPassengers;
  private static List<Flight> availableFlights;

  private static void showSearchBooking() {
    System.out.println("Enter destination!");
    String destination = scanner.nextLine();
    System.out.println("Enter flight date! Format dd-mm-yyyy !");
    String dateText = scanner.nextLine();
    LocalDate date = LocalDate.parse(dateText);
    System.out.println("Enter number of passengers!");
    countPassengers = Integer.parseInt(scanner.nextLine());
    availableFlights = flightController.findAvailableFlights(destination, date, countPassengers);
    flightController.displayFlights(availableFlights);
    if(availableFlights.size() != 0) {
      setNumberFlightTab();
    } else {
      System.out.println("No flights with the specified parameters were found!");
    }
  }

  private static void setNumberFlightTab() {
    System.out.println("Enter number of flight!");
    int number = Integer.parseInt(scanner.nextLine());
    if(number != 0) {
      showReservePlace(number, availableFlights, countPassengers);
    }
  }

  private static void showReservePlace(int number, List<Flight> flightList, int countPassenger) {
    int bookingId = new Random(System.currentTimeMillis()).nextInt();
    int flightId = flightList.get(number - 1).getId();
    ArrayList<Passenger> passengerList = new ArrayList<>();
    for(int i = 0; i < countPassenger; i++) {
      System.out.println("Enter the name!");
      String name = scanner.nextLine();
      System.out.println("Enter the last name!");
      String lastname = scanner.nextLine();
      passengerList.add(bookingController.createPassenger(name, lastname));
    }
    bookingController.createBooking(bookingId, flightId, passengerList);
  }

  private static void showCancelReservation() {
    System.out.println("Enter the booking id to delete it!");
    int id = Integer.parseInt(scanner.nextLine());
    bookingController.deleteBooking(id);
  }

  private static void showMyFlights() {
    System.out.println("Enter the name of the person for whom the ticket is booked!");
    String name = scanner.nextLine();
    System.out.println("Enter the last name!");
    String lastname = scanner.nextLine();
    bookingController.showUserBookings(name, lastname, flightController);
  }
}
