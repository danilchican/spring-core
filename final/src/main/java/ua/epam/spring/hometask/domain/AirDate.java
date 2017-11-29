package ua.epam.spring.hometask.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dates")
public class AirDate extends AbstractEntity {

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, foreignKey = @ForeignKey(name = "dates_event_id_fk"))
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id", nullable = false, foreignKey = @ForeignKey(name = "dates_auditorium_id_fk"))
    private Auditorium auditorium;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }
}
