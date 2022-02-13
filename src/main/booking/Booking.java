package main.booking;

import main.flights.Flight;
import main.passenger.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Booking {
    private List<Passenger> passengers;
    private int id;
    private int flightId;

    public Booking(int id, int flightId) {
        this.id = id;
        this.flightId = flightId;
    }

    public Booking(int id, int flightId, ArrayList<Passenger> passengers) {
        this.id = id;
        this.flightId = flightId;
        this.passengers = passengers;
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

    public int countFreePlaces() {
        return 30 - this.passengers.size();
    }

    public void addPassenger(Passenger passenger) { this.passengers.add(passenger); }

    public String prettyFormat(Flight flight) {
        String title = "------------------------Booking------------------------";
        String end = "-------------------------------------------------------";
        return String.format("%s\nFrom: Kyiv\nTo: %s\nDate: %s\tFree places: %d \tFlight id: %d\n%s",
                title, flight.getDestination(), flight.getDate(), this.countFreePlaces(), flightId, end);
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

    @Override
    public String toString() {
        return String.format("Booking{'passengers=%s', 'id=%d', 'flightId=%d'", passengers, id, flightId);
    }
}