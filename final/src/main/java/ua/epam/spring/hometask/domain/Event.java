package ua.epam.spring.hometask.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "events")
public class Event extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Set<AirDate> airDates = new TreeSet<>();

    @Column(name = "base_price")
    private double basePrice;

    @Enumerated(EnumType.STRING)
    private EventRating rating;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "event_auditorium",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id",  foreignKey = @ForeignKey(name = "ea_event_id_fk")),
            inverseJoinColumns = @JoinColumn(name = "auditorium_id", referencedColumnName = "id",  foreignKey = @ForeignKey(name = "ea_auditorium_id_fk")))
    private Set<Auditorium> auditoriums;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AirDate> getAirDates() {
        return airDates;
    }

    public void setAirDates(Set<AirDate> airDates) {
        this.airDates = airDates;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    public Set<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", airDates=" + airDates +
                ", basePrice=" + basePrice +
                ", rating=" + rating +
                '}';
    }
}
