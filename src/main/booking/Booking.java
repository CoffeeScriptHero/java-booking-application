package main.booking;

import main.flights.Flight;
import main.passenger.Passenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Booking implements Serializable {
    private List<Passenger> passengers;
    private int id;
    private int flightId;

    public Booking(int id, int flightId) {
        this.id = id;
        this.flightId = flightId;
        this.passengers = new ArrayList<Passenger>();
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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public boolean ifUserExist(String name, String surname) {
        Optional<Passenger> passenger = this.passengers.stream().
                filter(p -> Objects.equals(p.getName(), name) && Objects.equals(p.getSurname(), surname)).findAny();
        return passenger.isPresent();
    }

    public void addPassenger(Passenger passenger) {
        if (!this.passengers.contains(passenger) && this.passengers.size() < 30) this.passengers.add(passenger);
    }

    public void addPassenger(String name, String surname) {
        Passenger passenger = new Passenger(name, surname);
        if (!this.passengers.contains(passenger) && this.passengers.size() < 30) this.passengers.add(passenger);
    }

    public int countOccupiedPlaces() {
        return this.passengers.size();
    }

    public String prettyFormat(Flight flight) {
        String title = "------------------------Booking------------------------";
        String end = "-------------------------------------------------------";
        return String.format("%s\nFrom: Kyiv\nTo: %s\nDate: %s\tTime: %s\nOccupied seats: %d \tFlight id: %d\n%s\n",
                title, flight.getDestination(), flight.getDate(), flight.getTime(),
                this.countOccupiedPlaces(), this.flightId, end);
    }


    public void printId() {
        System.out.printf("Id of your booking is: %d", this.id);
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