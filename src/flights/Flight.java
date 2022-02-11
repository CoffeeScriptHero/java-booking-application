package flights;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight {
 private LocalDate date;
 private LocalTime time;
 private Destination destination;
 private int id;
 private int availableSeats;

 public Flight(LocalDate date, LocalTime time, Destination destination, int id, int availableSeats) {
  this.date = date;
  this.time = time;
  this.destination = destination;
  this.id = id;
  this.availableSeats = availableSeats;
 }

 @Override
 public String toString() {
  return String.format("Flight ID : %d, Destination : %s, Date : %s, Time : %s, Available seats : %d %n " ,
     id, destination.getName(), date.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")), time.format(DateTimeFormatter.ofPattern("h:mm a")), availableSeats);
 }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Flight flight = (Flight) o;
  return id == flight.id && date.equals(flight.date) && time.equals(flight.time) && destination == flight.destination;
 }

 @Override
 public int hashCode() {
  return Objects.hash(date, time, destination, id);
 }

 public LocalDate getDate() {
  return date;
 }

 public void setDate(LocalDate date) {
  this.date = date;
 }

 public LocalTime getTime() {
  return time;
 }

 public void setTime(LocalTime time) {
  this.time = time;
 }

 public Destination getDestination() {
  return destination;
 }

 public void setDestination(Destination destination) {
  this.destination = destination;
 }

 public int getAvailableSeats() {
  return availableSeats;
 }

 public void setAvailableSeats(int availableSeats) {
  this.availableSeats = availableSeats;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }
}
