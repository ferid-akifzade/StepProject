package service;

import  controller.LogRegController;
import dao.BookingDAO;
import  dao.FlightsDAO;
import  libs.Flight;
import  libs.Person;
import  libs.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class BookingService {

    private BookingDAO personDatabase = new BookingDAO();

    public BookingService() throws IOException {
    }

    public boolean makeBooking(Person person, Integer flightId, Integer desiredSits) throws IOException, IllegalArgumentException {

        if (getFlightById(flightId).isPresent() && isAvailable(getFlightById(flightId).get())) {
            FlightService service = new FlightService();
            service.book(getFlightById(flightId).get(), desiredSits);
            Booking personBook = new Booking(person, desiredSits, flightId);
            add(personBook);
            return true;
        } else { return false; }
    }

    private boolean isAvailable(Flight flight) throws IOException {
        FlightsDAO dataReader = new FlightsDAO();
        ArrayList<ArrayList<Flight>> database = dataReader.getDatabase();
        for (ArrayList<Flight> oneLine : database) {
            for (Flight oneFlight : oneLine) {
                if (oneFlight.equals(flight)) return true;
            }
        }
        return false;
    }

    public ArrayList<String> getFlightByPerson(Person person) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        BookingService service = new BookingService();
        for (Booking personBook : personDatabase) {
            if (service.getFlightById(personBook.getFlightId()).isPresent() && personBook.getPerson().equals(person))
                result.add(personBook.toReadableString() + service.getFlightById(personBook.getFlightId()).get().toReadableString() + "\n");
        }
        return result;
    }

    private void add(Booking personBook) throws IOException {
        StringBuilder databaseToWrite = new StringBuilder();
        for (Booking oneBookingData : personDatabase)
            databaseToWrite.append(oneBookingData.toString());
        databaseToWrite.append(personBook.toString());
        write(databaseToWrite);
    }

    private void write(StringBuilder databaseToWrite) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("BookingDB.txt")));
        bufferedWriter.write(databaseToWrite.toString());
        bufferedWriter.close();
    }

    public boolean cancelBooking(Integer bookingId) throws IOException {
        BookingCanceler updater = new BookingCanceler();
        return updater.delete(bookingId);
    }

    private Optional<Flight> getFlightById(Integer id) throws IOException {
        FlightsDAO database = new FlightsDAO();
        for (ArrayList<Flight> oneLine : database) {
            for (Flight oneFlight : oneLine) {
                if (oneFlight.getIntegerId().equals(id)) return Optional.of(oneFlight);
            }
        }
        return Optional.empty();
    }

    void exit() {
        LogRegController.exit = true;
    }
    void logout() {
        LogRegController.isLogged = false;
    }
}
