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
}
