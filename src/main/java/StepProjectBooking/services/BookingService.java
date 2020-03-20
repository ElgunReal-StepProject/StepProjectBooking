package StepProjectBooking.services;

import StepProjectBooking.concretes.Booking;
import StepProjectBooking.concretes.Flight;
import StepProjectBooking.concretes.Passenger;
import StepProjectBooking.dao.DAO;
import StepProjectBooking.dao.DAOBookingFile;
import StepProjectBooking.dao.DAOFlightFile;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

  private DAO<Flight> daoFlight = new DAOFlightFile();
  private DAO<Booking> daoBooking = new DAOBookingFile();

  public String getBookingByID(int id) {
    return daoBooking.get(id)
            .map(Booking::represent)
            .orElse(String.format("There is not any booking with this ID: %d", id));
  }

  public void cancelBooking(int id) {
    Booking booking = daoBooking.get(id).orElseThrow(() -> new RuntimeException("Something went wrong: Services::cancelBooking"));
    daoBooking.remove(booking);
    daoFlight.save(daoFlight.get(booking.getFlightID()).orElseThrow(() -> new RuntimeException("Something went wrong: Services::cancelBooking")));
  }

  public String getBookingsByPassenger(Passenger passenger) {
    Passenger result = new Passenger(passenger.getName().toLowerCase(),passenger.getSurname().toLowerCase());
    List<String> collected = daoBooking
            .getAllBy(booking -> (booking.getPassengerList().stream().map(x -> new Passenger(x.getName().toLowerCase(),x.getSurname().toLowerCase()))
                    .collect(Collectors.toList())
                    .contains(result)))
            .stream()
            .map(x -> String.format("%s", x.represent()))
            .collect(Collectors.toList());
    if (collected.isEmpty()) {
      return String.format("There is not any booking in your name: %s %s", passenger.getName(), passenger.getSurname());
    }
    StringBuilder sb = new StringBuilder();
    collected.forEach(x -> sb.append(x).append("\n"));
    return sb.toString();
  }

  public String getLastBookingID() {
    return String.valueOf(daoBooking.getAll().size());
  }

}
