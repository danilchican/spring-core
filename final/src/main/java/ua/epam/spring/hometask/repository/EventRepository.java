package ua.epam.spring.hometask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    Event findFirstByName(String name);

    /**
     * Finding all events that air on specified date range
     *
     * @param from Start date
     * @param to   End date inclusive
     * @return Set of events
     */
    List<Event> findEventsByAirDatesDateTimeBetween(LocalDateTime from, LocalDateTime to);
}
