package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.AirDate;

public interface AirDateService {

    /**
     * Save AirDate by params.
     *
     * @param airDate
     */
    void addAirDate(AirDate airDate);
}
