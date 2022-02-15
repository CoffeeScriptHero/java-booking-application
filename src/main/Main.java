package main;

import main.errors.NonExistentMenuName;
import main.flights.*;

import java.util.*;

public class Main {

  private static final String ONLINE_BOARD = "Онлайн-табло";
  private static final String SHOW_INFORMATION = "Посмотреть информацию о рейсе";
  private static final String SEARCH_BOOKING = "Поиск и бронировка рейса";
  private static final String CANCEL_RESERVATION = "Отменить бронирование";
  private static final String MY_FLIGHTS = "Мои рейсы";
  private static final String EXIT = "Выход";

  private static final FlightDAO flightDAO = FlightCollection.instanceOf();
  private static final FlightService flightService = new FlightService(flightDAO);
  private static final FlightController flightController = new FlightController(flightService);


  private static final ArrayList<String> menuTabs = new ArrayList<>();
  private static final Scanner scanner = new Scanner(System.in);
  private static Boolean isRunning = true;

  public static void main(String[] args) {
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
        case ONLINE_BOARD -> flightController.displayAllFlights();
        case SHOW_INFORMATION -> {
          System.out.println("Please write flight id!");
          int id = Integer.parseInt(scanner.nextLine());
          flightController.getFlightById(id).ifPresent(Flight::prettyFormat);
        }
        case SEARCH_BOOKING -> {

        }
        case CANCEL_RESERVATION -> {

        }
        case MY_FLIGHTS -> {

        }
        case EXIT -> {
          isRunning = false;
        }
        default -> {
          throw new NonExistentMenuName("There is no such menu item! Try again!");
        }
      }
    } catch (NonExistentMenuName exMessage) {
      System.out.println(exMessage);
      chooseMenuTab(scanner.nextLine());
    } catch (NumberFormatException exMessage) {
      System.out.println("The data you entered is incorrect! Try again!");
      chooseMenuTab(line);
    }
  }
}
