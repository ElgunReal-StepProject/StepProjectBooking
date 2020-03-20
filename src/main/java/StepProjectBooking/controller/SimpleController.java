package StepProjectBooking.controller;

import StepProjectBooking.concretes.Passenger;
import StepProjectBooking.services.SimpleServices;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class SimpleController {

  private SimpleServices simpleServices;
  private Console cc = new Console();
  private BookingController bC;
  private FlightController fC;

  public SimpleController() {
    simpleServices = new SimpleServices();
    bC = new BookingController();
    fC = new FlightController();
  }

  public String showMenu() {
    return simpleServices.getMenu();
  }

  public Passenger newPassenger(String name, String surname) {
    return simpleServices.newPassenger(name, surname);
  }

  public void case1OP() {
    String response = fC.allFlightsIn24HInfo();
    if(response.equals("")){
      cc.println("There is no flights in 24 hours.\n");
    }
    else {
      cc.println(response);
    }
  }

  public void case2OP() {
    try {
      cc.println("Enter the flight ID or 0 to exit: ");
      String choiceOrId = cc.readline();
      if (choiceOrId.equals("0")) {
        return;
      }
      int id = Integer.parseInt(choiceOrId);
      String respond = fC.flightByID(id);
      if(respond.equals("")){
        cc.println(String.format("There is not any flight with this id: %s\n",id));
      }
      else {
        cc.println(respond);
      }
    }
    catch (NumberFormatException numberFormEx) {
      cc.println("Wrong input. Please try again.\n");
    }
  }

  public void case3OP() {
    while (true) {
      try {
        String city;
        LocalDate date;
        int numOfPassengers;
        cc.println("City: ");
        city = cc.readline().toLowerCase();
        cc.println("Date (YYYY-MM-DD): ");
        date = LocalDate.parse(cc.readline());
        cc.println("Number of passengers: ");
        numOfPassengers = Integer.parseInt(cc.readline());
        String currentList = fC.flightsFilter(city, date, numOfPassengers);
        if (currentList.equals("")) {
          cc.println("No matching flight.");
          return;
        }
        else {
          cc.println(currentList);
        }
        cc.println("Please enter flight ID or 0 to exit: ");
        String choiceOrId = cc.readline();
        if (choiceOrId.equals("0")) {
          return;
        }
        int flightID = Integer.parseInt(choiceOrId);
        String respond = fC.flightByID(flightID);
        if (respond.equals("")) {
          throw new NumberFormatException();
        }
        List<Passenger> newPassengers = new ArrayList<>();
        for (int i = 0; i < numOfPassengers; i++) {
          cc.print("Passenger's name: ");
          String name = cc.readline();
          cc.print("Passenger's surname: ");
          String surname = cc.readline();
          cc.println("Saved!");
          newPassengers.add(this.newPassenger(name, surname));
        }
        fC.bookingOp(flightID, newPassengers);
        cc.println("The operation successfully completed!");
        cc.println(String.format("Your ID is: %s",bC.lastBookingID()));
        return;
      } catch (DateTimeParseException dateTimeEx) {
        cc.println("Your date input is not in specified order. Please try again.\n");
      } catch (NumberFormatException numberFormEx) {
        cc.println("Wrong input. Please try again.\n");
      }
    }
  }

  public void case4OP() {
    cc.println("Enter your booking ID: ");
    int id;
    try {
      id = Integer.parseInt(cc.readline());
    } catch (NumberFormatException e) {
      cc.println("Wrong input. Please try again.\n");
      return;
    }
    String respond = bC.bookingByID(id);
    if (respond.equals("")) {
      cc.println(String.format("There is not any booking with this id: %s",id));
      return;
    }
    bC.cancelBooking(id);
    cc.println("The operation successfully completed!");
  }

  public void case5OP() {
    cc.print("Enter your name: ");
    String yourname = cc.readline();
    cc.print("Enter your surname: ");
    String yoursurname = cc.readline();
    String response = bC.bookingsByPassenger(this.newPassenger(yourname, yoursurname));
    if (response.equals("")){
      cc.println(String.format("There is not any booking with your name: %s %Ss",yourname,yoursurname));
    }
    else {
      cc.println(response);
    }
  }
}