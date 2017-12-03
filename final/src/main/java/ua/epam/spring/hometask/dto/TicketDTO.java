package ua.epam.spring.hometask.dto;

public class TicketDTO extends AbstractDTO {
    private UserDTO user;
    private SeatDTO seat;
    private AirDateDTO airDate;
    private double ticketPrice;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }

    public AirDateDTO getAirDate() {
        return airDate;
    }

    public void setAirDate(AirDateDTO airDate) {
        this.airDate = airDate;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
