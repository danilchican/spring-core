package ua.epam.spring.hometask.domain;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "tickets_user_id_fk"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false, foreignKey = @ForeignKey(name = "tickets_seat_id_fk"))
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id", nullable = false, foreignKey = @ForeignKey(name = "tickets_dates_id_fk"))
    private AirDate airDate;

    @Column(name = "ticket_price")
    private double ticketPrice;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AirDate getAirDate() {
        return airDate;
    }

    public void setAirDate(AirDate airDate) {
        this.airDate = airDate;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
