package service;

import controller.CommandParser;
import libs.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Service {

    private CommandParser localCommandParser;

    public Service(CommandParser parsed) {
        this.localCommandParser = new CommandParser(parsed);
    }

    public ArrayList<String> run(Person person) throws IOException, IllegalArgumentException {
        BookingService bookingService = new BookingService();
        FlightService flightService = new FlightService();
        if (localCommandParser.isCommandAvailable())
            switch (localCommandParser.getCommand().toString().trim().toLowerCase()) {
                case "b":
                    PrintableDatabase printableDatabase = new PrintableDatabase();
                    return new ArrayList<>(Collections.singletonList(printableDatabase.getPrintable().toString()));

                case "s":
                    if (localCommandParser.getData().size() < 2)
                        throw new IllegalArgumentException("Command error. Please write in right way.");
                    return flightService.showFlightInfo
                            (localCommandParser.getData().get(0).toString().trim(), Integer.parseInt(localCommandParser.getData().get(1).toString().trim()));
                case "r":
                    try {
                        if(bookingService.makeBooking(person, Integer.parseInt(localCommandParser.getData().get(0).toString().trim()),
                                Integer.parseInt(localCommandParser.getData().get(1).toString().trim())))
                            return new ArrayList<>(Collections.singletonList("Make booking successfully"));
                        else
                            return new ArrayList<>(Collections.singletonList("Flight is not available"));
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                    catch (IndexOutOfBoundsException e) {
                        throw new IndexOutOfBoundsException("Command error. Please write in right way.");
                    }
                case "c":
                    if (bookingService.cancelBooking(Integer.parseInt(localCommandParser.getData().get(0).toString())))
                        return new ArrayList<>(Collections.singletonList("Canceling successfully"));
                    else
                        return new ArrayList<>(Collections.singletonList("Can't find booking with this id"));
                case "m":
                    return new ArrayList<>(Collections.singleton(bookingService.getFlightByPerson(person).toString()));
                case "l":
                    bookingService.logout();
                    return new ArrayList<>(Collections.singletonList("See you"));
                case "e":
                    bookingService.exit();
                    return new ArrayList<>(Collections.singletonList("See you"));
            }

        return new ArrayList<>(Collections.singletonList(""));
    }

}
