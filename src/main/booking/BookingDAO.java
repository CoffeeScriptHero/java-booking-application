package main.booking;

import java.util.ArrayList;
import java.util.Optional;

public interface BookingDAO<T> {
    ArrayList<T> getAllBookings();
    Optional<T> getBooking(int id);
    boolean deleteBooking(int id);
    void saveBooking(T booking);
    void saveBookingData(ArrayList<T> bookings, String fileName);
    ArrayList<T> loadBookingData(String fileName);
}