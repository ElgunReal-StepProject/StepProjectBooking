package StepProjectBooking.controller;

import StepProjectBooking.concretes.Passenger;
import StepProjectBooking.services.FlightService;

import java.time.LocalDate;
import java.util.List;

public class FlightController {

  private FlightService fS;

  public FlightController() {
    fS = new FlightService();
  }

  public String allFlightsInfo() {
    return fS.getAllFlightsInfo();
  }

  protected String allFlightsIn24HInfo() {
    return fS.getAllFlightsIn24HInfo();
  }

  public String flightByID(int id) {
    return fS.getFlightByID(id);
  }

  public String flightsFilter(String city, LocalDate date, int numOfPeople) {
    return fS.searchFlightsAndGet(city, date, numOfPeople);
  }

  public void bookingOp(int flightID, List<Passenger> passengers) {
    fS.bookFlight(flightID, passengers);
  }


}
