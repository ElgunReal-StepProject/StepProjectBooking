package StepProjectBooking.services;

import StepProjectBooking.concretes.Booking;
import StepProjectBooking.concretes.Flight;
import StepProjectBooking.concretes.Passenger;
import StepProjectBooking.dao.DAO;
import StepProjectBooking.dao.DAOBookingFile;
import StepProjectBooking.dao.DAOFlightFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

  private DAO<Flight> daoFlight = new DAOFlightFile();
  private DAO<Booking> daoBooking = new DAOBookingFile();

  public String getAllFlightsInfo() {
    Collection<String> all = daoFlight.getAll()
            .stream().map(Flight::represent).collect(Collectors.toList());
    if(all.isEmpty()){
      return "There is not any flights";
    }
    StringBuilder sb = new StringBuilder();
    all.forEach(x -> sb.append(x).append("\n"));
    return sb.toString();
  }

  public String getAllFlightsIn24HInfo() {
    List<String> collected = daoFlight.getAllBy(x ->
            (x.getDateTime().isBefore(LocalDateTime.now().plusDays(1)) && x.getDateTime().isAfter(LocalDateTime.now())))
            .stream().map(Flight::represent).collect(Collectors.toList());

    if (collected.isEmpty()) {
      return "There is not any flights in 24 hours";
    }
    StringBuilder sb = new StringBuilder();
    collected.forEach(x -> sb.append(x).append("\n"));
    return sb.toString();
  }

  public String getFlightByID(int id) {
    return daoFlight.get(id)
            .map(Flight::represent)
            .orElse(String.format("There is not any flight with this ID: %d", id));
  }

  public String searchFlightsAndGet(String city, LocalDate date, int numOfPeople) {
    List<String> collected = daoFlight.getAllBy(flight -> (flight.getDestination().toLowerCase().equals(city.toLowerCase())
            && flight.getDate().equals(date)
            && flight.getEmptySeats() >= numOfPeople))
            .stream()
            .map(Flight::represent).collect(Collectors.toList());

    if (collected.isEmpty()){
      return String.format("There is not any flights for you: %s %s %s",city,date,numOfPeople);
    }
    StringBuilder sb = new StringBuilder();
    collected.forEach(x -> sb.append(x).append("\n"));
    return sb.toString();
  }

  public void bookFlight(int flightID, List<Passenger> passengers) {
    Booking created = new Booking(flightID, passengers);
    daoBooking.save(created);
    daoFlight.save(daoFlight.get(flightID).orElseThrow(() -> new RuntimeException("Something went wrong: Services::bookFlight")));
  }

}
