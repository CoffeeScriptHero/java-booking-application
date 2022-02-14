package main.booking;
import main.passenger.Passenger;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectionBookingDao implements BookingDAO<Booking>{

    private static CollectionBookingDao INSTANCE;

    private CollectionBookingDao() {}

    public static CollectionBookingDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CollectionBookingDao();
        }
        return INSTANCE;
    }

    private final ArrayList<Booking> bookings = new ArrayList<>();

    @Override
    public ArrayList<Booking> getAllBookings() {
        return bookings;
    }

    @Override
    public Optional<Booking> getBooking(int id) {
        return bookings.stream().filter(b -> b.getId() == id).findAny();
    }

    @Override
    public boolean deleteBooking(int id) {
        return bookings.removeIf(b -> b.getId() == id);
    }

    @Override
    public void saveBooking(Booking booking) {
        if (bookings.size() > 0 && bookings.stream().anyMatch(booking::equals)) {
            int replaceIndex = bookings.indexOf(booking);
            bookings.set(replaceIndex, booking);
        } else {
            bookings.add(booking);
        }
    }

    @Override
    public void saveBookingData(ArrayList<Booking> bookings, String fileName) {
    }

    @Override
    public ArrayList<Booking> loadBookingData(String fileName) {
        return null;
    }

}