package ua.epam.spring.hometask.dto;

import ua.epam.spring.hometask.domain.EventRating;

import java.util.Set;

public class EventDTO extends AbstractDTO {
    private String name;
    private double basePrice;
    private Set<AirDateDTO> airDates;
    private EventRating rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public Set<AirDateDTO> getAirDates() {
        return airDates;
    }

    public void setAirDates(Set<AirDateDTO> airDates) {
        this.airDates = airDates;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }
}
