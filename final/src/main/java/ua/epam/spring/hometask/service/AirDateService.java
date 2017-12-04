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
     * @param airDate date to remove
     */
    void removeAirDateTime(AirDate airDate);
}
