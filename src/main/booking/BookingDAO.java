package main.booking;

import java.util.ArrayList;

public interface BookingDAO<T> {
    ArrayList<T> getAllBookings();
    T getBooking(int id);
    boolean deleteBooking(int id);
    void saveBooking(T booking);
    void saveBookingData(ArrayList<T> bookings, String fileName);
    ArrayList<T> loadBookingData(String fileName);
}