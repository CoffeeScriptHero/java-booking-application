package main;

import main.booking.Booking;
import main.booking.BookingController;
import main.errors.NonExistentMenuName;
import main.flights.*;
import main.passenger.Passenger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Console {

    private static final String ONLINE_BOARD = "Online scoreboard";
    private static final String SHOW_INFORMATION = "View flight information";
    private static final String SEARCH_BOOKING = "Flight search and booking";
    private static final String CANCEL_RESERVATION = "Cancel booking";
    private static final String MY_FLIGHTS = "My flights";
    private static final String EXIT = "Exit";
    private static final String ERROR = "The data you entered is incorrect! Try again!";

    private final ArrayList<String> menuTabs = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    private Boolean isRunning = true;
    private static int countPassengers;
    private static List<Flight> availableFlights;

    private final FlightController flightController;
    private final BookingController bookingController;

    public Console(FlightController flightController, BookingController bookingController) {
        this.flightController = flightController;
        this.bookingController = bookingController;
    }

    public void startConsole() {
        putMenuTabs();
        while (isRunning) {
            showMenu();
            chooseMenuTab(scanner.nextLine());
        }
    }

    private void putMenuTabs() {
        menuTabs.add(ONLINE_BOARD);
        menuTabs.add(SHOW_INFORMATION);
        menuTabs.add(SEARCH_BOOKING);
        menuTabs.add(CANCEL_RESERVATION);
        menuTabs.add(MY_FLIGHTS);
        menuTabs.add(EXIT);
    }

    public void showMenu() {
        System.out.println("\n------------Main Menu------------");
        for(String tab: menuTabs) {
            System.out.printf("| %s |\n", tab);
        }
        System.out.println("---------------------------------");
    }

    public void chooseMenuTab(String line) {
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
                case EXIT -> exit();
                default -> throw new NonExistentMenuName("There is no such menu item! Try again!");
            }
        } catch (NonExistentMenuName exMessage) {
            System.err.println(exMessage);
            chooseMenuTab(scanner.nextLine());
        } catch (NumberFormatException | DateTimeParseException | NoSuchElementException exMessage) {
            System.err.println(ERROR);
            chooseMenuTab(line);
        } catch (IndexOutOfBoundsException exMessage) {
            System.err.println(ERROR);
            setNumberFlightTab();
        }
    }

    private void showInformation() {
        System.out.println("Please write flight id!");
        int id = Integer.parseInt(scanner.nextLine());
        flightController.getFlight(id).ifPresent(Flight::prettyFormat);
    }

    private void showOnlineBoard() {
        flightController.displayAllFlights();
    }

    private void showSearchBooking() {
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

    private void setNumberFlightTab() {
        System.out.println("Enter number of flight!");
        int number = Integer.parseInt(scanner.nextLine());

        if(number != 0) {
            showReservePlace(number, availableFlights, countPassengers);
        }
    }

    private void showReservePlace(int number, List<Flight> flightList, int countPassenger) {
        int bookingId = bookingController.getAllBookings().size() + 1;
        int flightId = flightList.get(number - 1).getId();

        ArrayList<Passenger> passengerList = new ArrayList<>();
        for(int i = 0; i < countPassenger; i++) {
            System.out.println("Enter the name!");
            String name = scanner.nextLine();
            System.out.println("Enter the last name!");
            String lastname = scanner.nextLine();
            passengerList.add(new Passenger(name, lastname));
        }

        bookingController.createBooking(bookingId, flightId, passengerList);
        flightController.deleteAvailableSeats(flightId, countPassenger);
    }

    private void showCancelReservation() {
        System.out.println("Enter the booking id to delete it!");
        int id = Integer.parseInt(scanner.nextLine());
        Booking booking = bookingController.getBooking(id).get();
        flightController.addAvailableSeats(booking.getFlightId(), booking.countOccupiedPlaces());
        bookingController.deleteBooking(id);
    }

    private void showMyFlights() {
        System.out.println("Enter the name of the person for whom the ticket is booked!");
        String name = scanner.nextLine();
        System.out.println("Enter the last name!");
        String lastname = scanner.nextLine();
        bookingController.showUserBookings(name, lastname, flightController);
    }

    private void exit() {
        isRunning = false;
    }

}
