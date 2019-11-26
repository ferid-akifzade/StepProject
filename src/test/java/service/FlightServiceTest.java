package service;

import  libs.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
    private Flight correct;
    private Flight incorrect = new Flight("Saint",17,45,1,400);
    private FlightService service = new FlightService();

    FlightServiceTest() throws IOException {
    }

    boolean flight(Flight flight) throws IOException
    {
        boolean noException;
        try {
            FlightService service = new FlightService();
            Flight flightIn = service.showFlightInfo(flight.getIntegerId());
            System.out.println(flightIn.toReadableString());
            noException = true;
        } catch (IllegalArgumentException e) {
            noException = false;
        }
        return noException;
    }

    @Test
    void showFlightInfoCorrect() throws IOException {
        correct = new Flight("Saint Petersburg",17,45,7,40);
        assertTrue(flight(correct));
    }
    @Test
    void showFlightInfoIncorrect() throws IOException {
        assertFalse(flight(incorrect));
    }
    @Test
    void bookTestCorrect() throws IOException {
        correct = service.showFlightInfo(40);
        Integer beforeSits = correct.getFreeSits();
        service.book(correct, 1);
        FlightService newService = new FlightService();
        correct = newService.showFlightInfo(40);
        Integer afterSits = correct.getFreeSits();
        assertEquals(beforeSits,afterSits+1);
    }

    @Test
    void bookTestIncorrect() throws IOException {
        boolean catchIn;
        try {
        Integer beforeSits = incorrect.getFreeSits();
        service.book(incorrect, 1);
        catchIn = false;
        }
        catch (IllegalArgumentException e)
        {
            catchIn = true;
        }
        assertTrue(catchIn);
    }
}