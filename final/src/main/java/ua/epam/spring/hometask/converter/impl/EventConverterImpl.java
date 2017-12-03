package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AirDateConverter;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.dto.EventDTO;

@Component
public class EventConverterImpl implements Converter<Event, EventDTO> {

    @Autowired
    private AirDateConverter airDateConverter;

    @Override
    public Event convertToEntity(EventDTO dto) {
        Event entity = new Event();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAirDates(airDateConverter.convertToEntitySet(dto.getAirDates()));
        entity.setBasePrice(dto.getBasePrice());
        entity.setRating(dto.getRating());

        return entity;
    }

    @Override
    public EventDTO convertToDTO(Event entity) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setId(entity.getId());
        eventDTO.setName(entity.getName());
        eventDTO.setAirDates(airDateConverter.convertToDTOSet(entity.getAirDates()));
        eventDTO.setBasePrice(entity.getBasePrice());
        eventDTO.setRating(entity.getRating());

        return eventDTO;
    }
}
