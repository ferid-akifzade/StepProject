package dao;

import  libs.Person;
import  libs.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BookingDAO implements DAO<Booking>, Iterable<Booking> {
    private ArrayList<Booking> database;
    public BookingDAO() throws IOException {
        read();
    }
    @Override
    public void read() throws IOException {
        ArrayList<Booking> databaseTMP = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("BookingDB.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            StringBuilder name = new StringBuilder();
            StringBuilder surname = new StringBuilder();
            StringBuilder bookedSits = new StringBuilder();
            StringBuilder flightId = new StringBuilder();
            StringBuilder bookingId = new StringBuilder();
            boolean isName = false;
            boolean isSurname = false;
            boolean isBookedSits = false;
            boolean isId = false;
            boolean isBookingId = false;
            for (char ch : line.toCharArray()) {
                switch (ch) {
                    case '#':
                        isBookingId = true;
                        isName = false;
                        isSurname = false;
                        isBookedSits = false;
                        isId = false;
                        break;
                    case '+':
                        isBookingId = false;
                        isName = true;
                        isSurname = false;
                        isBookedSits = false;
                        isId = false;
                        break;
                    case '-':
                        isBookingId = false;
                        isName = false;
                        isSurname = true;
                        isBookedSits = false;
                        isId = false;
                        break;
                    case '<':
                        isBookingId = false;
                        isName = false;
                        isSurname = false;
                        isBookedSits = true;
                        isId = false;
                        break;

                    case '@':
                        isBookingId = false;
                        isName = false;
                        isSurname = false;
                        isBookedSits = false;
                        isId = true;
                        break;
                    case ',':
                        Booking personBook = new Booking(Integer.parseInt(bookingId.toString()), new Person(name.toString(), surname.toString()),
                                Integer.parseInt(bookedSits.toString()), Integer.parseInt(flightId.toString()));
                        databaseTMP.add(personBook);
                        bookingId = new StringBuilder();
                        name = new StringBuilder();
                        surname = new StringBuilder();
                        bookedSits = new StringBuilder();
                        flightId = new StringBuilder();
                        break;
                    default:
                        if (isBookingId) bookingId.append(ch);
                        else if (isName) name.append(ch);
                        else if (isSurname) surname.append(ch);
                        else if (isBookedSits) bookedSits.append(ch);
                        else if (isId) flightId.append(ch);
                }
            }

        }
        database = databaseTMP;
        reader.close();
    }

    @Override
    public ArrayList<Booking> getDatabase() throws IOException {
        read();
        return database;
    }

    @Override
    public Iterator<Booking> iterator() {
        return database.iterator();
    }
}
