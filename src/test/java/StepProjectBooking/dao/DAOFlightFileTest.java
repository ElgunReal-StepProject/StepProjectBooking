package StepProjectBooking.dao;

import StepProjectBooking.concretes.Booking;
import StepProjectBooking.concretes.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DAOFlightFileTest {
  private DAO<Flight> daoFlight;
  private Collection<Flight> flights;
  private DAO<Booking> daoBooking;

  @BeforeEach
  void set() {
    daoFlight = new DAOFlightFile();
    flights = new ArrayList<>();
    daoBooking = new DAOBookingFile();
  }

  @Test
  void getAllTest (){
    flights.add(Flight.parse("59;Baku;2020-03-13T17:00;56;60"));
    flights.add(Flight.parse("61;Moscow;2020-03-14T15:00;68;70"));
    flights.add(Flight.parse("62;Kiev;2020-03-25T16:00;80;80"));
    flights.add(Flight.parse("63;Moscow;2020-03-20T21:00;70;70"));
    Collection<Flight> expected = daoFlight.getAll();
    Collection<Flight> actual = flights;
    assertEquals(expected,actual);
  }

  @Test
  void getAllByTest (){
    flights.add(Flight.parse("61;Moscow;2020-03-14T15:00;68;70"));
    flights.add(Flight.parse("63;Moscow;2020-03-20T21:00;70;70"));
    Collection<Flight> expected = daoFlight.getAllBy(x -> x.getDestination().equals("Moscow"));
    Collection<Flight> actual = flights;
    assertEquals(expected,actual);
  }
  @Test
  void getTest (){
    Flight expected = daoFlight.get(59).orElseThrow(() -> new RuntimeException(""));
    Flight actual = Flight.parse("59;Baku;2020-03-13T17:00;56;60");
    assertEquals(expected,actual);
  }

  @Test
  void saveTest(){
    flights.add(Flight.parse("59;Baku;2020-03-13T17:00;56;60"));
    flights.add(Flight.parse("61;Moscow;2020-03-14T15:00;68;70"));
    flights.add(Flight.parse("62;Kiev;2020-03-25T16:00;80;80"));
    daoBooking.save(Booking.parse("63#4#Name1:Surname1;Name2:Surname2;"));
    flights.add(Flight.parse("63;Moscow;2020-03-20T21:00;68;70"));
    daoFlight.save(Flight.parse("63;Moscow;2020-03-20T21:00;70;70"));
    Collection<Flight> expected = daoFlight.getAll();
    Collection<Flight> actual = flights;
    assertEquals(expected,actual);
    daoBooking.remove(Booking.parse("63#4#Name1:Surname1;Name2:Surname2;"));
    daoFlight.save(Flight.parse("63;Moscow;2020-03-20T21:00;70;70"));
  }

}