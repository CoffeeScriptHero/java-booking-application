package main.booking;
import java.util.ArrayList;

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
    public Booking getBooking(int id) {
        try {
            return bookings.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteBooking(int id) {
        return bookings.removeIf(b -> b.getId() == id);
    }

    @Override
    public void saveBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public void saveBookingData(ArrayList<Booking> bookings, String fileName) {
    }

    @Override
    public ArrayList<Booking> loadBookingData(String fileName) {
        return null;
    }

}