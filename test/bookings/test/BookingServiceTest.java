package bookings.test;

import main.booking.Booking;
import main.booking.BookingService;
import main.booking.CollectionBookingDao;
import main.flights.Flight;
import main.flights.FlightController;
import main.flights.FlightService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    BookingService bookingService = new BookingService();

    Booking booking1;
    Booking booking2;

    @BeforeEach
    void setUp() {
        bookingService.clearCollection();
        booking1 = new Booking(1, 1973);
        booking2 = new Booking(2, 8264);
    }

    @AfterEach
    void tearDown() {
        booking1 = null;
        booking2 = null;
    }

    @Test
    void CreateBookingAddCreatedBookingToBookingsList() {
        assertEquals(bookingService.getAllBookings(), new ArrayList<Booking>());
        bookingService.createBooking(1, 5);
        assertEquals(bookingService.getAllBookings(), new ArrayList<Booking>(){{add(new Booking(1, 5));}});
    }

    @Test
    void GetAllBookingsIsEmptyIfThereIsNoBookings() {
        assertEquals(0, bookingService.getAllBookings().size());
    }

    @Test
    void GetBookingReturnsOptionalEmptyIfThereIsNoBooking() {
        assertEquals(Optional.empty(), bookingService.getBooking(5345345));
    }

    @Test
    void GetBookingReturnsOptionalOfBookingIfBookingExists() {
        bookingService.saveBooking(booking1);
        assertEquals(Optional.of(booking1), bookingService.getBooking(booking1.getId()));
    }

}