package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AirDateConverter;
import ua.epam.spring.hometask.converter.EventConverter;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.dto.AirDateDTO;
import ua.epam.spring.hometask.dto.EventDTO;

import java.util.Set;

@Component
public class EventConverterImpl implements EventConverter {

    @Autowired
    private AirDateConverter airDateConverter;

    @Override
    public Event createFromDTO(EventDTO dto) {
        return updateEntity(new Event(), dto);
    }

    @Override
    public EventDTO createFromEntity(Event entity) {
        EventDTO eventDTO = new EventDTO();
        Set<AirDateDTO> airDates = airDateConverter.createSetFromEntities(entity.getAirDates());

        eventDTO.setId(entity.getId());
        eventDTO.setName(entity.getName());
        eventDTO.setAirDates(airDates);
        eventDTO.setBasePrice(entity.getBasePrice());
        eventDTO.setRating(entity.getRating());

        return eventDTO;
    }

    @Override
    public Event updateEntity(Event entity, EventDTO dto) {
        Set<AirDate> airDates = airDateConverter.createSetFromDtos(dto.getAirDates());

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAirDates(airDates);
        entity.setBasePrice(dto.getBasePrice());
        entity.setRating(dto.getRating());

        return entity;
    }
}
