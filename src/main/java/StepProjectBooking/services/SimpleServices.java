package StepProjectBooking.services;

import StepProjectBooking.concretes.Passenger;

public class SimpleServices {

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

  public Passenger newPassenger(String name, String surname) {
    return new Passenger(name, surname);
  }
}
