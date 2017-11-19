package ua.epam.spring.hometask.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;

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
    Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to);

    /**
     * Return events from 'now' till the the specified date time
     *
     * @param to End date time inclusive
     * @return Set of events
     */
    @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDateTime to);

    /**
     * Checks if event is aired on particular <code>dateTime</code> and assigns
     * auditorium to it.
     *
     * @param event      Event to check is aired
     * @param dateTime   Date and time of aired event for which to assign
     * @param auditorium Auditorium that should be assigned
     * @return <code>true</code> if successful, <code>false</code> if event is
     * not aired on that date
     */
    boolean assignAuditorium(Event event, LocalDateTime dateTime, Auditorium auditorium);

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     *
     * @param event    Event to retrieve airDates and auditoriums
     * @param dateTime Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    boolean removeAirDateTime(Event event, LocalDateTime dateTime);

    /**
     * Removes auditorium assignment from event
     *
     * @param auditoriums auditoriums for event
     * @param dateTime    Date and time to remove auditorium for
     * @return <code>true</code> if successful, <code>false</code> if not
     * removed
     */
    boolean removeAuditoriumAssignment(NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime);
}
