package service;

import  dao.FlightsDAO;
import  libs.Flight;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


class PrintableDatabase {
    StringBuilder getPrintable() throws IOException {
        FlightsDAO dataReader = new FlightsDAO();
        StringBuilder result = new StringBuilder();
        ArrayList<ArrayList<Flight>> data = dataReader.getDatabase();
        for (int i = 0; i < data.size(); i++) {
            LocalDate localDate = LocalDate.now().plusDays(i);
            for (Flight oneFlight : data.get(i)) {
                result.append(oneFlight.toString()).append(" Date: ").append(localDate.toString()).append("\n");
            }
        }
        return result;
    }
}
