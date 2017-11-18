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
     * Checks if event airs on particular date
     *
     * @param airDates air dates of event
     * @param date     Date to ckeck
     * @return <code>true</code> event airs on that date
     */
    default boolean airsOnDate(NavigableSet<LocalDateTime> airDates, LocalDate date) {
        return airDates.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
    }

    /**
     * Checks if event airs on particular date and time
     *
     * @param airDates air dates of event
     * @param dateTime Date and time to check
     * @return <code>true</code> event airs on that date and time
     */
    default boolean airsOnDateTime(NavigableSet<LocalDateTime> airDates, LocalDateTime dateTime) {
        return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
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
     * Get auditorium on particular <code>dateTime</code>
     *
     * @param auditoriums auditoriums for event
     * @param dateTime    Date and time of aired event
     * @return auditorium on particular <code>dateTime</code>
     */
    default Auditorium findAuditoriumOnDateTime(NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime) {
        return auditoriums.get(dateTime);
    }

    /**
     * Checks if event is aired on particular <code>dateTime</code> and assigns
     * auditorium to it.
     *
     * @param airDates    air dates of event
     * @param auditoriums auditoriums for event
     * @param dateTime    Date and time of aired event for which to assign
     * @param auditorium  Auditorium that should be assigned
     * @return <code>true</code> if successful, <code>false</code> if event is
     * not aired on that date
     */
    default boolean assignAuditorium(NavigableSet<LocalDateTime> airDates, NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime, Auditorium auditorium) {
        if (airDates.contains(dateTime)) {
            auditoriums.put(dateTime, auditorium);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     *
     * @param airDates    air dates of event
     * @param auditoriums auditoriums for event
     * @param dateTime    Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    default boolean removeAirDateTime(NavigableSet<LocalDateTime> airDates, NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime) {
        boolean result = airDates.remove(dateTime);

        if (result) {
            auditoriums.remove(dateTime);
        }

        return result;
    }

    /**
     * Removes auditorium assignment from event
     *
     * @param auditoriums auditoriums for event
     * @param dateTime    Date and time to remove auditorium for
     * @return <code>true</code> if successful, <code>false</code> if not
     * removed
     */
    default boolean removeAuditoriumAssignment(NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime) {
        return auditoriums.remove(dateTime) != null;
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

    /**
     * Adding date and time of event air and assigning auditorium to that
     *
     * @param airDates    air dates of event
     * @param auditoriums auditoriums for event
     * @param dateTime    Date and time to add
     * @param auditorium  Auditorium to add if success in date time add
     * @return <code>true</code> if successful, <code>false</code> if already
     * there
     */
    default boolean addAirDateTime(NavigableSet<LocalDateTime> airDates, NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime, Auditorium auditorium) {
        boolean result = airDates.add(dateTime);

        if (result) {
            auditoriums.put(dateTime, auditorium);
        }

        return result;
    }
}
