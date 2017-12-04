package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDateTime;

public interface AirDateService {

    /**
     * Save AirDate by params.
     *
     * @param airDate
     */
    void addAirDate(AirDate airDate);
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
