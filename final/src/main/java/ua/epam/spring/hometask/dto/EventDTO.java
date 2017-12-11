package ua.epam.spring.hometask.dto;

import org.hibernate.validator.constraints.NotBlank;
import ua.epam.spring.hometask.domain.EventRating;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class EventDTO extends AbstractDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @NotBlank
    private double basePrice;

    @NotNull
    private Set<AirDateDTO> airDates;

    @NotNull
    @NotBlank
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
