package service;

import  dao.BookingDAO;
import  libs.Booking;
import  libs.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingServiceTest {
    private Integer flightId = 15;
    private Integer desiredSits = 1;
    private Person person = new Person("default", "default");

    @Test
    void makeBookingSuccess() throws IOException {
        BookingService service= new BookingService();
        assertTrue(service.makeBooking(person, flightId, desiredSits));

    }

    @Test
    void makeBookingException() throws IOException {
        flightId = 1000;
        BookingService service = new BookingService();
        assertFalse(service.makeBooking(person, flightId, desiredSits));
    }

    @Test
    void getFlightByPerson() throws IOException {
        BookingService service = new BookingService();
        ArrayList<String> flightByPerson = service.getFlightByPerson(person);
        System.out.println(flightByPerson);
    }

    Optional<Booking> getBookingByPerson(Person person) throws IOException {
        BookingDAO bookings = new BookingDAO();
        bookings.read();
        ArrayList<Booking> database = bookings.getDatabase();
        return database.stream().filter( booking -> booking.getPerson().equals(person)).findFirst();
    }
    @Test
    void cancelBooking() throws IOException {
        BookingService service = new BookingService();
        Person person = new Person("default", "default");
        Optional<Booking> bookingByPerson = getBookingByPerson(person);
        service.cancelBooking(bookingByPerson.map(Booking::getBookingId).get());
    }
}