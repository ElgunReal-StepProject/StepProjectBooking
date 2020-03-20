package StepProjectBooking.dao;

import StepProjectBooking.concretes.Booking;
import StepProjectBooking.concretes.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DAOFlightFile implements DAO<Flight> {

  private final String filename = "src/main/java/StepProjectBooking/Data/flights.txt";

  @Override
  public Collection<Flight> getAll() {
    try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
      return br.lines().map(Flight::parse).collect(Collectors.toList());
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  @Override
  public Collection<Flight> getAllBy(Predicate<Flight> p) {
    return getAll().stream()
            .filter(p)
            .collect(Collectors.toList());
  }

  @Override
  public Optional<Flight> get(int id) {
    Collection<Flight> all = this.getAll();
    return all.stream()
            .filter(x -> x.getId() == id)
            .findFirst();
  }

  @Override
  public void save(Flight flight) {
    int seats = 0;
    Collection<Booking> allBy = new DAOBookingFile().getAllBy(x -> x.getFlightID() == flight.getId());
    for (Booking b : allBy){
      seats += b.getPassengerList().size();
    }
    final int hold = seats;
    List<Flight> collected = this.getAll().stream()
            .map(x -> {
              if (x.getId() == flight.getId()) {
                return new Flight(flight.getId(),
                        flight.getDestination(),
                        flight.getDateTime(),
                        flight.getAllSeats() - hold,
                        flight.getAllSeats());
              } else return x;
            }).collect(Collectors.toList());

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))) {
      for (Flight flight1 : collected) {
        bw.write(flight1.fileFormat());
        bw.write("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong seriously...", e);
    }
  }

  @Override
  public void remove(Flight flight) {
    throw new RuntimeException("Not implemented");
  }
}
