package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.dto.AirDateDTO;
import ua.epam.spring.hometask.dto.AuditoriumDTO;
import ua.epam.spring.hometask.dto.EventDTO;

@Component("airDateConverter")
public class AirDateConverterImpl implements Converter<AirDate, AirDateDTO> {

    @Autowired
    @Qualifier("auditoriumConverter")
    private Converter<Auditorium, AuditoriumDTO> auditoriumConverter;

    @Autowired
    @Qualifier("eventConverter")
    private Converter<Event, EventDTO> eventConverter;

    @Override
    public AirDate convertToEntity(AirDateDTO dto) {
        AirDate entity = new AirDate();

        entity.setId(dto.getId());
        entity.setAuditorium(auditoriumConverter.convertToEntity(dto.getAuditorium()));
        entity.setDateTime(dto.getDateTime());
        entity.setEvent(eventConverter.convertToEntity(dto.getEvent()));

        return entity;
    }

    @Override
    public AirDateDTO convertToDTO(AirDate entity) {
        AirDateDTO dto = new AirDateDTO();

        dto.setId(entity.getId());
        dto.setAuditorium(auditoriumConverter.convertToDTO(entity.getAuditorium()));
        dto.setDateTime(entity.getDateTime());
        dto.setEvent(eventConverter.convertToDTO(entity.getEvent()));

        return dto;
    }
}
