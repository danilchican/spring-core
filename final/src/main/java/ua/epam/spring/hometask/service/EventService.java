package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Yuriy_Tkach
 */
public interface EventService extends AbstractDomainObjectService<Event> {

    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    @Nullable
    Optional<Event> getByName(@Nonnull String name);

    /**
     * Finding all events that air on specified date range
     *
     * @param from Start date
     * @param to   End date inclusive
     * @return Set of events
     */
    @Nonnull
    List<Event> getForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to);

    /**
     * Return events from 'now' till the the specified date time
     *
     * @param to End date time inclusive
     * @return Set of events
     */
    @Nonnull
    List<Event> getNextEvents(@Nonnull LocalDateTime to);

    /**
     * Adding date and time of event air and assigning auditorium to that
     *
     * @param event      Event to add air date and time
     * @param dateTime   Date and time to add
     * @param auditorium Auditorium to add if success in date time add
     * @return <code>true</code> if successful, <code>false</code> if already
     * there
     */
    boolean addAirDateTime(Event event, LocalDateTime dateTime, Auditorium auditorium);

    /**
     * Removes the air date of event by assigned auditorium.
     *
     * @param event      Event to find air date
     * @param auditorium Auditorium to find air date
     * @param dateTime   Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    boolean removeAirDateTime(Event event, Auditorium auditorium, LocalDateTime dateTime);
}
