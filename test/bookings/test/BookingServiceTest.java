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

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    BookingService bookingService = new BookingService();

    Booking booking1 = new Booking(1, 1973);
    Booking booking2 = new Booking(2, 8264);

    @BeforeEach
    void setUp() {
        bookingService.clearCollection();
    }

    @AfterEach
    void tearDown() {
    }

}