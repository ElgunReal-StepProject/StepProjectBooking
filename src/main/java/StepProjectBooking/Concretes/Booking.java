package StepProjectBooking.Concretes;

import StepProjectBooking.DAO.DAOBookingFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Booking {
  private int bookingID;
  private int flightID = 0;
  private List<Passenger> passengerList;

  public Booking(int flightID, List<Passenger> passengerList) {
    this.flightID = flightID;
    this.passengerList = passengerList;
  }

  private Booking(int bookingID, int flightID, List<Passenger> passengerList) {
    this.bookingID = bookingID;
    this.flightID = flightID;
    this.passengerList = passengerList;
  }

  public int getBookingID() {
    return bookingID;
  }

  public List<Passenger> getPassengerList() {
    return passengerList;
  }

  public int getFlightID() {
    return flightID;
  }

  public static Booking parse(String line) {
    String[] splitted = line.split("#");
    int flightID = Integer.parseInt(splitted[0]);
    int bookingID = Integer.parseInt(splitted[1]);
    String[] splitted2 = splitted[2].split(";");
    List<Passenger> collected = Arrays.stream(splitted2).map(Passenger::parse).collect(Collectors.toList());
    return new Booking(bookingID, flightID, collected);
  }

  public String represent() {
    final int size = this.getPassengerList().toString().length();
    return String.format("Flight ID: %s; Booking ID: %s; Passengers: %s",
            this.getFlightID(),
            this.getBookingID(),
            this.getPassengerList().toString().substring(1,size-1));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Booking booking = (Booking) o;
    return bookingID == booking.bookingID &&
            flightID == booking.flightID;
  }
  public String fileFormat() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getFlightID())
            .append("#")
            .append(this.getBookingID())
            .append("#");
    for (Passenger passenger : this.getPassengerList()) {
      sb.append(passenger.getName())
              .append(":")
              .append(passenger.getSurname())
              .append(";");
    }
    return sb.toString();
  }
}
