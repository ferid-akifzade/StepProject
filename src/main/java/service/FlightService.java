package service;

import  dao.FlightsDAO;
import  libs.Flight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FlightService {
    private ArrayList<ArrayList<Flight>> database;

    public FlightService() throws IOException {
        FlightsDAO dataReader = new FlightsDAO();
        this.database = dataReader.getDatabase();
    }

    public void book(Flight flight, Integer desiredSits) throws IOException {
        boolean found = false;
        for (ArrayList<Flight> oneLine : database) {
            if (!found) {
                for (Flight oneFlight : oneLine) {
                    if (oneFlight.equals(flight)) {
                        oneFlight.booked(desiredSits);
                        found = true;
                        break;
                    }
                }
            } else break;
        }
        if (found)
        writeToDatabase();
        else throw new IllegalArgumentException("Flight not found");
    }
    void cancelBook(Flight flight, Integer desiredSits) throws IOException
    {
        boolean found = false;
        for (ArrayList<Flight> oneLine : database) {
            if (!found) {
                for (Flight oneFlight : oneLine) {
                    if (oneFlight.equals(flight)) {
                        oneFlight.cancelBook(desiredSits);
                        found = true;
                        break;
                    }
                }
            } else break;
        }
        if (found)
        writeToDatabase();
        else throw new IllegalArgumentException("Flight not found");
    }
    void writeToDatabase() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("database.txt")));
        int id = 0;
        for (ArrayList<Flight> oneLine : database) {
            for (Flight oneFlight : oneLine) {
                String data = "@" + id +
                        ":" + oneFlight.getCity() +
                        "\\" + String.format("%02d", oneFlight.getHour()) +
                        "." + String.format("%02d", oneFlight.getMinute()) +
                        "|" + oneFlight.getFreeSits() +
                        ",";
                bufferedWriter.write(data);
                id++;
            }
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
    }

    ArrayList<String> showFlightInfo(String city, Integer desiredSits) throws IOException {
        FlightsDAO reader = new FlightsDAO();
        ArrayList<ArrayList<Flight>> database = reader.getDatabase();
        ArrayList<String> result = new ArrayList<>();
        for (ArrayList<Flight> oneLine : database) {
            for (Flight oneFlight : oneLine) {
                if (oneFlight.getCity().toLowerCase().equals(city.toLowerCase()) && oneFlight.getFreeSits() >= desiredSits)
                    result.add(oneFlight.toString() + "\n");
            }
        }
        return result;
    }
    public Flight showFlightInfo(Integer flightId) throws IOException {
        FlightsDAO reader = new FlightsDAO();
        ArrayList<ArrayList<Flight>> database = reader.getDatabase();
        for (ArrayList<Flight> oneLine : database) {
            for (Flight oneFlight : oneLine) {
                if (oneFlight.getIntegerId().equals(flightId))
                    return  oneFlight;
            }
        }
        throw new IllegalArgumentException("Flight not found");
    }
}