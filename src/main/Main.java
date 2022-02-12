package main;

import main.booking.Booking;
import main.booking.BookingController;

import java.util.ArrayList;

public class Main {
  public static void main(String[] rgs) {
    BookingController bc = new BookingController();
    Booking booking = new Booking(2, 1);

    bc.saveBooking(booking);

    System.out.println(bc.getAllBookings());
    System.out.println(bc.getBooking(0));
    System.out.println(bc.deleteBooking(booking.getId()));
    System.out.println(bc.getAllBookings());

  }
}
