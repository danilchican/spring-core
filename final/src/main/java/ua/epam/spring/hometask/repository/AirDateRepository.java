package ua.epam.spring.hometask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Auditorium;

import java.time.LocalDateTime;

public interface AirDateRepository extends JpaRepository<AirDate, Long> {

    /**
     * Find auditorium to a particular event by date and time.
     *
     * @param eventId  Particular event to find auditorium
     * @param dateTime Date and time for auditorium
     * @return founded auditorium
     */
    Auditorium findByEventIdAndDateTime(Long eventId, LocalDateTime dateTime);

    /**
     * Check if the auditorium is not aired by particular datetime.
     *
     * @param auditoriumId Auditorium to checking
     * @param dateTime     Date and time for auditorium
     * @return founded air date
     */
    AirDate findByAuditoriumIdAndDateTime(Long auditoriumId, LocalDateTime dateTime);

    /**
     * Check if the auditorium is not aired by particular datetime for the event.
     *
     * @param eventId      Event to checking
     * @param auditoriumId Auditorium to checking
     * @param dateTime     Date and time for auditorium
     * @return founded air date
     */
    AirDate findByEventIdAndAuditoriumIdAndDateTime(Long eventId, Long auditoriumId, LocalDateTime dateTime);
}
