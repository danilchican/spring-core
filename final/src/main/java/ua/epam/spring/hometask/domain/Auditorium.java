package ua.epam.spring.hometask.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auditoriums")
public class Auditorium extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "seats_available")
    private int seatsAvailable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auditorium")
    private Set<Seat> seats;

    public Auditorium() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
