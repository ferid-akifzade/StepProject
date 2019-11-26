package libs;

import java.util.Objects;

public class Flight {
    private String city;
    private Integer hour;
    private Integer minute;
    private Integer freeSits;
    private Integer integerId;

    public Flight(String city, Integer hour, Integer minute, Integer freeSits, Integer integerId) {
        this.city = city;
        this.hour = hour;
        this.minute = minute;
        this.freeSits = freeSits;
        this.integerId = integerId;
    }

    public Flight(Flight flight) {
        integerId = flight.getIntegerId();
        city = flight.getCity();
        hour = flight.getHour();
        minute = flight.getMinute();
        freeSits = flight.getFreeSits();
    }

    public Integer getIntegerId() {
        return integerId;
    }

    public String getCity() {
        return city;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public Integer getFreeSits() {
        return freeSits;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return city.equals(flight.city) &&
                hour.equals(flight.hour) &&
                minute.equals(flight.minute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, hour, minute, freeSits);
    }

    public void clear() {
        city = "";
        hour = -1;
        minute = -1;
        freeSits = -1;
    }

    public void booked(Integer desiredSits) {
        this.freeSits -= desiredSits;
    }

    public void cancelBook(Integer desiredSits)
    {
        this.freeSits += desiredSits;
    }

    @Override
    public String toString() {
        return integerId + ") " + city + "  " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + "  Free sits = " + freeSits;
    }

    public String toReadableString() {
        return " City : " + city + ", Time :  " + String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }

}
