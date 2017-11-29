package ua.epam.spring.hometask.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "base_price")
    private double basePrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Set<AirDate> airDates;

    @Enumerated(EnumType.STRING)
    private EventRating rating;

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
