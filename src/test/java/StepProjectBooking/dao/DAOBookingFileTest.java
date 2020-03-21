package StepProjectBooking.dao;

import StepProjectBooking.concretes.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DAOBookingFileTest {

  private DAO<Booking> daoBooking;
  private Collection<Booking> bookings;

  @BeforeEach
  void set() {
    daoBooking = new DAOBookingFile();
    bookings = new ArrayList<>();
  }

  @Test
  void getAllTest() {
    bookings.add(Booking.parse("59#1#Elgun:Cumayev;Real:Serxanbeyli;"));
    bookings.add(Booking.parse("59#2#Aqil:Isayev;Resid:Memmedrzayev;"));
    bookings.add(Booking.parse("61#3#Mickey:Mouse;Donald:Duck;"));
    Collection<Booking> expected = daoBooking.getAll();
    Collection<Booking> actual = bookings;
    assertEquals(expected, actual);
  }

  @Test
  void getAllByTest() {
    bookings.add(Booking.parse("59#1#Elgun:Cumayev;Real:Serxanbeyli;"));
    bookings.add(Booking.parse("59#2#Aqil:Isayev;Resid:Memmedrzayev;"));
    Collection<Booking> expected = daoBooking.getAllBy(x -> x.getFlightID() == 59);
    Collection<Booking> actual = bookings;
    assertEquals(expected, actual);
  }

  @Test
  void getTest() {
    Booking expected = daoBooking.get(2).orElseThrow(() -> new RuntimeException(""));
    Booking actual = Booking.parse("59#2#Aqil:Isayev;Resid:Memmedrzayev;");
    assertEquals(expected, actual);
  }

  @Test
  void saveTest() {
    bookings.add(Booking.parse("59#1#Elgun:Cumayev;Real:Serxanbeyli;"));
    bookings.add(Booking.parse("59#2#Aqil:Isayev;Resid:Memmedrzayev;"));
    bookings.add(Booking.parse("61#3#Mickey:Mouse;Donald:Duck;"));
    bookings.add(Booking.parse("63#4#Name:Surname;Name2:Surname2;"));
    daoBooking.save(Booking.parse("63#4#Name:Surname;Name2:Surname2;"));
    Collection<Booking> expected = daoBooking.getAll();
    Collection<Booking> actual = bookings;
    assertEquals(expected, actual);
    daoBooking.remove(Booking.parse("63#4#Name:Surname;Name2:Surname2;"));
  }

  @Test
  void removeTest() {
    bookings.add(Booking.parse("59#1#Elgun:Cumayev;Real:Serxanbeyli;"));
    bookings.add(Booking.parse("59#2#Aqil:Isayev;Resid:Memmedrzayev;"));
    daoBooking.remove(Booking.parse("61#3#Mickey:Mouse;Donald:Duck;"));
    Collection<Booking> expected = daoBooking.getAll();
    Collection<Booking> actual = bookings;
    assertEquals(expected, actual);
    daoBooking.save(Booking.parse("61#3#Mickey:Mouse;Donald:Duck;"));
  }
}