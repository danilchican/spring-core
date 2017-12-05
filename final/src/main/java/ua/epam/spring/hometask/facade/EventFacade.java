package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.dto.EventDTO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventFacade {

    /**
     * Find all events.
     *
     * @return list of events
     */
    List<EventDTO> findAll();

    /**
     * Save new event.
     *
     * @param event to save
     */
    void save(EventDTO event);

    /**
     * Remove event if exists.
     *
     * @param eventId event id to remove
     */
    void delete(long eventId);

    /**
     * Find event by id.
     *
     * @param eventId event id to search
     * @return founded event
     */
    Optional<EventDTO> findById(long eventId);

    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    Optional<EventDTO> findByName(String name);

    /**
     * Finding all events that air on specified date range
     *
     * @param from Start date
     * @param to   End date inclusive
     * @return Set of events
     */
    List<EventDTO> findForDateRange(LocalDateTime from, LocalDateTime to);

    /**
     * Return events from 'now' till the the specified date time
     *
     * @param to End date time inclusive
     * @return Set of events
     */
    List<EventDTO> findNextEvents(LocalDateTime to);
}
