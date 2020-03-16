package StepProjectBooking.Concretes;

import java.util.Objects;

public class Passenger {
  private String name;
  private String surname;

  public Passenger(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public static Passenger parse(String line) {
    String[] splitted = line.split(":");
    return new Passenger(splitted[0], splitted[1]);
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.getName(), this.getSurname());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Passenger passenger = (Passenger) o;
    return Objects.equals(name, passenger.name) &&
            Objects.equals(surname, passenger.surname);
  }

}
