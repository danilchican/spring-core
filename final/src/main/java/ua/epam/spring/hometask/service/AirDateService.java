package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDateTime;

public interface AirDateService {

    /**
     * Save AirDate by params.
     *
     * @param event
     * @param dateTime
     * @param auditorium
     */
    void addAirDate(Event event, LocalDateTime dateTime, Auditorium auditorium);
}
