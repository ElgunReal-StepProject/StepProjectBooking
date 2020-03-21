package StepProjectBooking.services;

import StepProjectBooking.concretes.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {
  private BookingService bS;

  @BeforeEach
  void set() {
    bS = new BookingService();
  }

  /**
   *    cancelBooking() method recovered in DAO Unit Tests
   */

  @Test
  void getBookingByIdTest1(){
    String expected = bS.getBookingByID(1);
    String actual = "Flight ID: 59; Booking ID: 1; Passengers: Elgun Cumayev, Real Serxanbeyli";
    assertEquals(expected,actual);
  }

  @Test
  void getBookingByIdTest2(){
    String expected = bS.getBookingByID(10);
    String actual = "There is not any booking with this ID: 10";
    assertEquals(expected,actual);
  }
  @Test
  void getBookingsByPassengerTest(){
    Passenger passenger = new Passenger("Elgun","Cumayev");
    String expected = bS.getBookingsByPassenger(passenger);
    String actual = "Flight ID: 59; Booking ID: 1; Passengers: Elgun Cumayev, Real Serxanbeyli\n";
    assertEquals(expected,actual);
  }
  @Test
  void getLastBookingIDTest(){
    String expected = bS.getLastBookingID();
    String actual = "3";
    assertEquals(expected,actual);
  }
}