package service;

import dao.BookingDAO;
import  libs.Booking;
import  libs.Flight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class BookingCanceler {
    private List<Booking> database;

    BookingCanceler() throws IOException {
        BookingDAO data = new BookingDAO();
        database = data.getDatabase();
    }

    private boolean delete(Booking bookingData) throws IOException {
        FlightService service = new FlightService();
        Flight flight = service.showFlightInfo(bookingData.getFlightId());
        service.cancelBook(flight,bookingData.getBookedSits());
        service.writeToDatabase();
        return database.remove(bookingData);
    }

    private void writeToDatabase() throws IOException {
        StringBuilder data = new StringBuilder();
        for (Booking oneBookingData : database) {
            data.append(oneBookingData.toString());
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("BookingDB.txt")));
        writer.write(data.toString());
        writer.close();
    }

    private Booking getBookingById(Integer bookingId) throws IllegalArgumentException {
        for (Booking oneData : database) {
            if (oneData.getBookingId().equals(bookingId))
                return oneData;
        }
        throw new IllegalArgumentException("Booking not found");
    }

    boolean delete(Integer bookingId) throws IOException {
        Booking data = getBookingById(bookingId);
        boolean result = delete(data);
        writeToDatabase();
        return result;
    }
}
