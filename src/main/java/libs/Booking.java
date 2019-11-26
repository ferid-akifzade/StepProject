package libs;

import dao.BookingDAO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Booking {
    private Integer bookingId;
    private Person person;
    private Integer flightId;
    private Integer bookedSits;

    public Booking(Integer bookingId, Person person, Integer bookedSits, Integer flightId) {
        this.bookingId = bookingId;
        this.person = person;
        this.bookedSits = bookedSits;
        this.flightId = flightId;
    }

    public Booking(Person person, Integer bookedSits, Integer flightId) throws IOException {
        BookingDAO database = new BookingDAO();
        List<Booking> list = database.getDatabase();
        this.bookingId = 1;
        if (!list.isEmpty())
            this.bookingId = list.get(list.size() - 1).getBookingId() + 1;
        this.person = person;
        this.bookedSits = bookedSits;
        this.flightId = flightId;
    }

    public Integer getBookedSits() {
        return bookedSits;
    }

    private Integer getIntegerId() {
        return flightId;
    }

    public Person getPerson() {
        return person;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking that = (Booking) o;
        return person.equals(that.person) &&
                flightId.equals(that.getIntegerId()) &&
                bookedSits.equals(that.bookedSits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, flightId, bookedSits);
    }

    @Override
    public String toString() {
        return "#" + bookingId + "+" + person.getName() + "-" + person.getSurname() + "<" + bookedSits + "@" + flightId + ",\n";

    }

    public String toReadableString() {
        return "Booking id : " + bookingId + ", Name : " + person.getName() + ", Surname : " +
                person.getSurname() + ", Booked Sits: " + bookedSits + ", Flight id :  " + flightId;

    }
}
