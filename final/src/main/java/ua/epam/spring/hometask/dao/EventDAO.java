package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;

public interface EventDAO extends AbstractDAO<Event> {

    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    Optional<Event> getByName(String name);

    /**
     * Finding all events that air on specified date range
     *
     * @param from Start date
     * @param to   End date inclusive
     * @return Set of events
     */
    Set<Event> getForDateRange(LocalDate from, LocalDate to);

    /**
     * Return events from 'now' till the the specified date time
     *
     * @param to End date time inclusive
     * @return Set of events
     */
    Set<Event> getNextEvents(LocalDateTime to);

    /**
     * Checking if event airs on dates between <code>from</code> and
     * <code>to</code> inclusive
     *
     * @param airDates air dates of event
     * @param from     Start date to check
     * @param to       End date to check
     * @return <code>true</code> event airs on dates
     */
    default boolean airsOnDates(NavigableSet<LocalDateTime> airDates, LocalDate from, LocalDate to) {
        return airDates.stream().anyMatch(dt -> dateInRange(dt.toLocalDate(), from, to));
    }

    /**
     * Checking if event airs on dates between <code>from</code> and
     * <code>to</code> inclusive
     *
     * @param airDates air dates of event
     * @param from     Start date to check
     * @param to       End date to check
     * @return <code>true</code> event airs on dates
     */
    default boolean airsOnDates(NavigableSet<LocalDateTime> airDates, LocalDateTime from, LocalDateTime to) {
        return airDates.stream().anyMatch(dt -> dateTimeInRange(dt, from, to));
    }

    /**
     * Checking if datetime in range.
     *
     * @param current Current datetime to check
     * @param from    Start datetime to check
     * @param to      End datetime to check
     * @return <code>true</code> datetime in range
     */
    default boolean dateTimeInRange(LocalDateTime current, LocalDateTime from, LocalDateTime to) {
        return current.compareTo(from) >= 0 && current.compareTo(to) <= 0;
    }

    /**
     * Checking if date in range.
     *
     * @param current Current date to check
     * @param from    Start date to check
     * @param to      End date to check
     * @return <code>true</code> date in range
     */
    default boolean dateInRange(LocalDate current, LocalDate from, LocalDate to) {
        return current.compareTo(from) >= 0 && current.compareTo(to) <= 0;
    }

    /**
     * Add date and time of event air
     *
     * @param airDates air dates of event
     * @param dateTime Date and time to add
     * @return <code>true</code> if successful, <code>false</code> if already
     * there
     */
    default boolean addAirDateTime(NavigableSet<LocalDateTime> airDates, LocalDateTime dateTime) {
        return airDates.add(dateTime);
    }
}
