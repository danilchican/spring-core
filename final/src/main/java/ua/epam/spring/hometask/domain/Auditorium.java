package ua.epam.spring.hometask.domain;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "auditoriums")
public class Auditorium extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auditorium")
    private Set<Seat> seats = new TreeSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "auditoriums")
    private Set<Event> events;

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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
