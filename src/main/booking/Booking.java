package main.booking;

import java.util.List;
import java.util.Objects;

public class Booking {
    //    private List<Passenger> passengers;
    private int id;
    private int flightId;

    public Booking(int id, int flightId) {
        this.id = id;
        this.flightId = flightId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int raceId) {
        this.flightId = raceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return getId() == booking.getId() && getFlightId() == booking.getFlightId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFlightId());
    }
}