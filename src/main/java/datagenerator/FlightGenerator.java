package datagenerator;

import java.util.Random;

class FlightGenerator {
    private String[] cities = new String[]{"Baku", "Los Angeles", "Istanbul", "Moscow", "New York", "Saint Petersburg", "London", "Rio de Janeiro"};
    private String flights = "";

    void generate() {
        flights = "";
        Random generator = new Random();
        FreeSitsGenerator freeSitsGenerator = new FreeSitsGenerator();
        freeSitsGenerator.generate();
        int cityIndex = Math.abs(generator.nextInt()) & 7;
        Integer flightHour = Math.abs(generator.nextInt()) % 24;
        Integer flightMinute = Math.abs(generator.nextInt()) % 12 * 5;
        String flightTime = String.format("%02d", flightHour) + "." + String.format("%02d", flightMinute);
        flights = "@" + BoardGenerator.id++ + ":" + cities[cityIndex] + "\\" + flightTime + "|" + freeSitsGenerator.getFreeSits();
    }

    String getFlights() {
        return flights;
    }


}
