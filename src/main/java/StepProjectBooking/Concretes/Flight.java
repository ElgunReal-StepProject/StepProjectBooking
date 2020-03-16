package StepProjectBooking.Concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Flight {
  private int id;
  private String destination;
  private LocalDateTime dateTime;
  private LocalDate date;
  private int allSeats;
  private int emptySeats;
  private static int counter = 0;

  public Flight(String destination, LocalDateTime time, int emptySeats, int allSeats) {
    this.destination = destination;
    this.dateTime = time;
    this.allSeats = allSeats;
    this.emptySeats = emptySeats;
    this.id = ++counter;
    this.date = time.toLocalDate();
  }

  public Flight(int id, String destination, LocalDateTime dateTime, int emptySeats, int allSeats) {
    this.destination = destination;
    this.dateTime = dateTime;
    this.allSeats = allSeats;
    this.emptySeats = emptySeats;
    this.id = id;
    this.date = dateTime.toLocalDate();
  }

  public String getDestination() {
    return this.destination;
  }

  public LocalDateTime getDateTime() {
    return this.dateTime;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public int getEmptySeats() {
    return this.emptySeats;
  }

  public int getAllSeats() {
    return allSeats;
  }

  public int getId() {
    return this.id;
  }

  public String represent() {
    return String.format("Flight ID: %s, Destination: %s, Date and Time: %s, Empty seats: %s, All seats: %s",
            this.getId(),
            this.getDestination(),
            this.getDateTime(),
            this.getEmptySeats(),
            this.getAllSeats());
  }

  public static Flight parse(String line) {
    String[] splitted = line.split(";");
    return new Flight(Integer.parseInt(splitted[0]), splitted[1], LocalDateTime.parse(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
  }

  public String fileFormat() {
    return String.format("%s;%s;%s;%s;%s", this.getId()
            , this.getDestination()
            , this.getDateTime().toString()
            , this.getEmptySeats()
            , this.getAllSeats());
  }
}
