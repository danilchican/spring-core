package ua.epam.spring.hometask.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class AuditoriumDTO extends AbstractDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Min(5)
    private int seatsAvailable;

    @NotNull
    private Set<SeatDTO> seats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public Set<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatDTO> seats) {
        this.seats = seats;
    }
}
