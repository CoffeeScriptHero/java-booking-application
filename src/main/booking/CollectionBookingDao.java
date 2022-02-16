package main.booking;
import main.passenger.Passenger;

import java.io.*;
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

    public void clearCollection() {
        this.bookings.clear();
    }


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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Booking booking : bookings) { oos.writeObject(booking);}
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Booking> loadBookingData(String fileName) {
        ArrayList<Booking> bookings = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            while(fis.available() > 0)
            {
                bookings.add((Booking)ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bookings;
    }

}