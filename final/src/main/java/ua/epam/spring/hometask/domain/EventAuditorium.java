package ua.epam.spring.hometask.domain;

import javax.persistence.*;

@Entity
@Table(name = "event_auditorium")
public class EventAuditorium extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, foreignKey = @ForeignKey(name = "ea_event_id_fk"))
    private Event event;

    @ManyToOne
    @JoinColumn(name = "auditorium_id", nullable = false, foreignKey = @ForeignKey(name = "ea_auditorium_id_fk"))
    private Auditorium auditorium;
}