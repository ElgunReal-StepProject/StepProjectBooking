package StepProjectBooking.dao;

import StepProjectBooking.concretes.Booking;
import StepProjectBooking.concretes.Passenger;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DAOBookingFile implements DAO<Booking>{

  private final String filename = "src/main/java/StepProjectBooking/Data/booking.txt";

  @Override
  public Collection<Booking> getAll() {
    try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
      return br.lines().map(Booking::parse).collect(Collectors.toList());
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  @Override
  public Collection<Booking> getAllBy(Predicate<Booking> p) {
    return getAll().stream()
            .filter(p)
            .collect(Collectors.toList());
  }

  @Override
  public Optional<Booking> get(int id) {
    Collection<Booking> all = this.getAll();
    return all.stream()
            .filter(x -> x.getBookingID() == id)
            .findFirst();
  }

  @Override
  public void save(Booking booking) {
    int lastID = getAll().size();
    StringBuilder sb = new StringBuilder();
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), true))) {
      sb.append(booking.getFlightID())
              .append("#")
              .append(lastID+1)
              .append("#");
      for (Passenger passenger : booking.getPassengerList()) {
        sb.append(passenger.getName())
                .append(":")
                .append(passenger.getSurname())
                .append(";");
      }
      bw.write(String.valueOf(sb));
      bw.write("\n");
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong: Booking::save()");
    }
  }

  @Override
  public void create() {
    throw new IllegalArgumentException("Not implemented");
  }

  @Override
  public void remove(Booking booking) {
    Collection<Booking> removed = this.getAll();
    removed.remove(booking);
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))) {
      for (Booking book : removed) {
        bw.write(book.fileFormat());
        bw.write("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong: Booking::remove()");
    }
  }
}
