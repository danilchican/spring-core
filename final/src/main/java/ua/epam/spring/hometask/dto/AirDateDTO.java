package ua.epam.spring.hometask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AirDateDTO extends AbstractDTO {

    @NotNull
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @NotNull
    private EventDTO event;

    @NotNull
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
