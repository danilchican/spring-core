package ua.epam.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.dto.AirDateDTO;
import ua.epam.spring.hometask.facade.AirDateFacade;
import ua.epam.spring.hometask.service.AirDateService;

@Component
public class AirDateFacadeImpl implements AirDateFacade {

    @Autowired
    private AirDateService airDateService;

    @Autowired
    @Qualifier("airDateConverter")
    private Converter<AirDate, AirDateDTO> airDateConverter;

    @Override
    public void addAirDate(AirDateDTO date) {
        airDateService.addAirDate(airDateConverter.convertToEntity(date));
    }
}
