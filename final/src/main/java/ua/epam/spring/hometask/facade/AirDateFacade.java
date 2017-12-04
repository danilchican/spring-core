package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.dto.AirDateDTO;

public interface AirDateFacade {

    /**
     * Add new air date.
     *
     * @param date to addAirDate
     */
    void addAirDate(AirDateDTO date);
}