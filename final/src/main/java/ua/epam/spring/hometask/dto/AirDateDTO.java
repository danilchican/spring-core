package ua.epam.spring.hometask.dto;

import java.time.LocalDateTime;

public class AirDateDTO extends AbstractDTO {
    private LocalDateTime dateTime;
    private EventDTO event;
    private AuditoriumDTO auditorium;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public AuditoriumDTO getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(AuditoriumDTO auditorium) {
        this.auditorium = auditorium;
    }
}
