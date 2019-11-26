package dao;

import datagenerator.BookingGenerator;
import libs.Flight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FlightsDAO implements DAO<ArrayList<Flight>>, Iterable<ArrayList<Flight>> {
    private ArrayList<ArrayList<Flight>> database;

    public FlightsDAO() throws IOException {
        database = new ArrayList<>();
        read();
    }

    private void readDatabaseFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("database.txt")));
        String line;
        ArrayList<Flight> oneLine = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            StringBuilder city = new StringBuilder();
            StringBuilder hour = new StringBuilder();
            StringBuilder minute = new StringBuilder();
            StringBuilder freeSits = new StringBuilder();
            StringBuilder id = new StringBuilder();
            boolean isCity = false;
            boolean isHour = false;
            boolean isMinute = false;
            boolean isFreeSits = false;
            boolean isId = false;
            for (char ch : line.toCharArray()) {
                switch (ch) {
                    case '@':
                        isId = true;
                        isCity = false;
                        isHour = false;
                        isFreeSits = false;
                        isMinute = false;
                        break;
                    case ':':
                        isId = false;
                        isCity = true;
                        isHour = false;
                        isMinute = false;
                        isFreeSits = false;
                        break;
                    case '\\':
                        isId = false;
                        isCity = false;
                        isHour = true;
                        isMinute = false;
                        isFreeSits = false;
                        break;
                    case '.':
                        isId = false;
                        isCity = false;
                        isHour = false;
                        isMinute = true;
                        isFreeSits = false;
                        break;
                    case '|':
                        isId = false;
                        isCity = false;
                        isHour = false;
                        isMinute = false;
                        isFreeSits = true;
                        break;
                    case ',':
                        Flight oneFlight = new Flight(
                                city.toString(),
                                Integer.parseInt(hour.toString()),
                                Integer.parseInt(minute.toString()),
                                Integer.parseInt(freeSits.toString()),
                                Integer.parseInt(id.toString()));
                        Flight one = new Flight(oneFlight);
                        oneLine.add(one);
                        oneFlight.clear();
                        city = new StringBuilder();
                        hour = new StringBuilder();
                        minute = new StringBuilder();
                        freeSits = new StringBuilder();
                        id = new StringBuilder();
                        break;
                    default:
                        if (isCity) city.append(ch);
                        else if (isFreeSits) freeSits.append(ch);
                        else if (isMinute) minute.append(ch);
                        else if (isHour) hour.append(ch);
                        else if (isId) id.append(ch);
                }
            }
            ArrayList<Flight> one = new ArrayList<>(oneLine);
            database.add(one);
            oneLine.clear();
        }
        reader.close();
    }

    @Override
    public void read() throws IOException {
        try {
            readDatabaseFile();
        } catch (IOException e) {
            BookingGenerator bookingGenerator = new BookingGenerator();
            bookingGenerator.generate();
            readDatabaseFile();
        }
    }

    @Override
    public ArrayList<ArrayList<Flight>> getDatabase() throws IOException {
        return database;
    }

    @Override
    public Iterator<ArrayList<Flight>> iterator() {
        return database.iterator();
    }
}
