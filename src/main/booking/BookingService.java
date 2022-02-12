package main.booking;

import java.util.ArrayList;

public class BookingService {
    CollectionBookingDao bookingDao = CollectionBookingDao.getInstance();

    public ArrayList<Booking> getAllBookings() { return bookingDao.getAllBookings(); }

//    public void displayAllBookings(FlightController fc) {
//        if (getAllBookings().size() == 0) {
//            System.out.println("Список бронирований пустой\n");
//        } else {
//            getAllBookings().forEach((b) -> {
//                Flight flight = fc.getFlight(b.getFlightId());
//                System.out.printf("%s\n", b.prettyFormat(flight));
//            });
//            System.out.println();
//        }
//    }

    public Booking getBooking(int id) { return bookingDao.getBooking(id); }

    public boolean deleteBooking(int id) { return bookingDao.deleteBooking(id); }

    public void saveBooking(Booking booking) { bookingDao.saveBooking(booking); }

}
