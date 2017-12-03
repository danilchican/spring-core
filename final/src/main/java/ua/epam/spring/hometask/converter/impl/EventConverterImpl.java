package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.dto.AirDateDTO;
import ua.epam.spring.hometask.dto.EventDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component("eventConverter")
public class EventConverterImpl implements Converter<Event, EventDTO> {

    @Autowired
    @Qualifier("airDateConverter")
    private Converter<AirDate, AirDateDTO> airDateConverter;

    @Override
    public Event convertToEntity(EventDTO dto) {
        Event entity = new Event();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAirDates(convertToEntitySet(dto.getAirDates()));
        entity.setBasePrice(dto.getBasePrice());
        entity.setRating(dto.getRating());

        return entity;
    }

    @Override
    public EventDTO convertToDTO(Event entity) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setId(entity.getId());
        eventDTO.setName(entity.getName());
        eventDTO.setAirDates(convertToDTOSet(entity.getAirDates()));
        eventDTO.setBasePrice(entity.getBasePrice());
        eventDTO.setRating(entity.getRating());

        return eventDTO;
    }

    private Set<AirDateDTO> convertToDTOSet(final Collection<AirDate> entities) {
        return entities.stream()
                .map(airDateConverter::convertToDTO)
                .collect(Collectors.toSet());
    }

    private Set<AirDate> convertToEntitySet(final Collection<AirDateDTO> dtos) {
        return dtos.stream()
                .map(airDateConverter::convertToEntity)
                .collect(Collectors.toSet());
    }
}
