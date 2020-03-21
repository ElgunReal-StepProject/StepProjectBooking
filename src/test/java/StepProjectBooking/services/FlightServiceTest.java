package StepProjectBooking.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
  FlightService fS;

  @BeforeEach
  void set() {
    fS = new FlightService();
  }

  /**
   *    bookFlight() method recovered in DAO Unit Tests
   */

  @Test
  void getAllFlightsInfoTest() {
    String expected = fS.getAllFlightsInfo();
    String actual = "Flight ID: 59, Destination: Baku, Date and Time: 2020-03-13T17:00, Empty seats: 56, All seats: 60\n" +
            "Flight ID: 61, Destination: Moscow, Date and Time: 2020-03-14T15:00, Empty seats: 68, All seats: 70\n" +
            "Flight ID: 62, Destination: Kiev, Date and Time: 2020-03-25T16:00, Empty seats: 80, All seats: 80\n" +
            "Flight ID: 63, Destination: Moscow, Date and Time: 2020-03-20T21:00, Empty seats: 70, All seats: 70\n";
    assertEquals(expected, actual);
  }

  @Test
  void getAllFlightsIn24HInfoTest() {
    String expected = fS.getAllFlightsIn24HInfo();
    String actual = "There is not any flights in 24 hours";
    assertEquals(expected, actual);
  }

  @Test
  void getFlightByIDTest() {
    String expected = fS.getFlightByID(63);
    String actual = "Flight ID: 63, Destination: Moscow, Date and Time: 2020-03-20T21:00, Empty seats: 70, All seats: 70";
    assertEquals(expected, actual);
  }

  @Test
  void searchFlightsAndGetTest(){
    String expected = fS.searchFlightsAndGet("Baku", LocalDate.parse("2020-03-13"),3);
    String actual = "Flight ID: 59, Destination: Baku, Date and Time: 2020-03-13T17:00, Empty seats: 56, All seats: 60\n";
    assertEquals(expected, actual);
  }
}