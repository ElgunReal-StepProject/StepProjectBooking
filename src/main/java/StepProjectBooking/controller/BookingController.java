package StepProjectBooking.controller;

import StepProjectBooking.concretes.Passenger;
import StepProjectBooking.services.BookingService;

public class BookingController {

  private BookingService bS;

  public BookingController(){
    bS = new BookingService();
  }

  protected String bookingByID(int id) {
    return bS.getBookingByID(id);
  }

  public String bookingsByPassenger(Passenger passenger) {
    return bS.getBookingsByPassenger(passenger);
  }

  public void cancelBooking(int id) {
    bS.cancelBooking(id);
  }

  protected String lastBookingID() {
    return bS.getLastBookingID();
  }
}
