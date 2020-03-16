package StepProjectBooking.Services;

import StepProjectBooking.Concretes.Booking;
import StepProjectBooking.Concretes.Flight;
import StepProjectBooking.Concretes.Passenger;
import StepProjectBooking.DAO.DAO;
import StepProjectBooking.DAO.DAOFlightFile;
import StepProjectBooking.DAO.DAOBookingFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Services {
  private DAO<Flight> daoFlight = new DAOFlightFile();
  //private DAO<User> daoUser = new DAOUserFile();
  private DAO<Booking> daoBooking = new DAOBookingFile();

  public String getMenu() {
    StringBuilder sB = new StringBuilder();
    sB.append("1. **Online-board**\n")
            .append("2. **Show the flight info**\n")
            .append("3. **Search and book a flight**\n")
            .append("4. **Cancel the booking**\n")
            .append("5. **My flights**\n")
            .append("6. **Exit**");
    return sB.toString();
  }

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
    List<String> collected = daoFlight.getAllBy(x -> x.getDateTime().isBefore(LocalDateTime.now().plusDays(1)))
            .stream().map(flight -> flight.represent()).collect(Collectors.toList());

    if (collected.isEmpty()) {
      return "There is not any flights in 24 hours";
    }
    StringBuilder sb = new StringBuilder();
    collected.forEach(x -> sb.append(x).append("\n"));
    return sb.toString();
  }

  public String getFlightByID(int id) {
    return daoFlight.get(id)
            .map(flight -> flight.represent())
            .orElse(String.format("There is not any flight with this ID: %d", id));
  }

  public String getBookingByID(int id) {
    return daoBooking.get(id)
            .map(Booking::represent)
            .orElse(String.format("There is not any booking with this ID: %d", id));
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

  public Passenger newPassenger(String name, String surname) {
    return new Passenger(name, surname);
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
