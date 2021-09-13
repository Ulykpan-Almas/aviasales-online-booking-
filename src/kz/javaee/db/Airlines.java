package kz.javaee.db;

import java.sql.Timestamp;

public class Airlines {
    private Long id;
    private String from_city;
    private String to_city;
    private int when_flight;
    private int people_number;

    public Airlines(Long id, String from_city, String to_city, int when_flight, int people_number) {
        this.id = id;
        this.from_city = from_city;
        this.to_city = to_city;
        this.when_flight = when_flight;
        this.people_number = people_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public int getWhen_flight() {
        return when_flight;
    }

    public void setWhen_flight(int when_flight) {
        this.when_flight = when_flight;
    }

    public int getPeople_number() {
        return people_number;
    }

    public void setPeople_number(int people_number) {
        this.people_number = people_number;
    }
}
