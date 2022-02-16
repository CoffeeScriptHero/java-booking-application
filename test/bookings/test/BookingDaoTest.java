package bookings.test;

import main.booking.Booking;
import main.booking.CollectionBookingDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingDaoTest {

    CollectionBookingDao bookingDao = CollectionBookingDao.getInstance();
    Booking booking1 = new Booking(1, 1973);
    Booking booking2 = new Booking(2, 8264);


    @BeforeEach
    void setUp() {
        bookingDao.clearCollection();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void GetAllBookingReturnsAnEmptyList() {
        assertEquals(bookingDao.getAllBookings(), new ArrayList<Booking>());
        bookingDao.saveBooking(booking1);
        assertEquals(bookingDao.getAllBookings(), new ArrayList<>(){{add(booking1);}});
    }

    @Test
    public void SaveBookingAddBookingToTheBookingsList() {
        assertEquals(bookingDao.getAllBookings(), new ArrayList<Booking>());
        bookingDao.saveBooking(booking1);
        assertEquals(bookingDao.getAllBookings(), new ArrayList<Booking>(){{add(booking1);}});
    }

    @Test
    public void SaveBookingDoesNotSaveSameBookingToBookingsList() {
        bookingDao.saveBooking(booking1);
        assertEquals(bookingDao.getAllBookings().size(), 1);
        assertEquals(bookingDao.getAllBookings(), new ArrayList<Booking>(){{add(booking1);}});
        bookingDao.saveBooking(booking1);
        assertEquals(bookingDao.getAllBookings().size(), 1);
        assertEquals(bookingDao.getAllBookings(), new ArrayList<Booking>(){{add(booking1);}});
    }

    @Test
    public void GetBookingDoesNotCrashIfThereIsNoBooking() {
        assertEquals(bookingDao.getBooking(3), Optional.empty());
        assertEquals(bookingDao.getBooking(-3), Optional.empty());
    }

    @Test
    public void GetBookingReturnsAnOptionalBookingIfThereIsBooking() {
        bookingDao.saveBooking(booking2);
        assertEquals(bookingDao.getBooking(booking2.getId()), Optional.of(booking2));
    }

    @Test
    public void DeleteBookingDoesNotCrashIfThereIsNoBookingAndReturnsFalse() {
        assertFalse(bookingDao.deleteBooking(1000));
    }

    @Test
    public void DeleteBookingDeletesBookingCorrectlyAndReturnsTrue() {
        bookingDao.saveBooking(booking2);
        assertEquals(bookingDao.getAllBookings(), new ArrayList<Booking>(){{add(booking2);}});
        assertTrue(bookingDao.deleteBooking(booking2.getId()));
        assertEquals(bookingDao.getAllBookings(), new ArrayList<Booking>());
    }

    @Test
    public void SaveBookingDataSavesBookingsToFile() {
        bookingDao.saveBooking(booking1);
        bookingDao.saveBookingData(bookingDao.getAllBookings(), "bookings_db_test.txt");
        assertEquals(bookingDao.loadBookingData("bookings_db_test.txt"), bookingDao.getAllBookings());
    }

    @Test
    public void LoadBookingDataLoadingBookingsFromFile() {
        bookingDao.saveBooking(booking1);
        bookingDao.saveBooking(booking2);
        bookingDao.saveBookingData(bookingDao.getAllBookings(), "bookings_db_test.txt");
        assertEquals(bookingDao.getAllBookings(), bookingDao.loadBookingData("bookings_db_test.txt"));
    }
}