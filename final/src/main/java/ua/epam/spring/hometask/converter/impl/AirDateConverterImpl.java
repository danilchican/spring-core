package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AirDateConverter;
import ua.epam.spring.hometask.converter.AuditoriumConverter;
import ua.epam.spring.hometask.converter.EventConverter;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.dto.AirDateDTO;
import ua.epam.spring.hometask.dto.AuditoriumDTO;
import ua.epam.spring.hometask.dto.EventDTO;

@Component
public class AirDateConverterImpl implements AirDateConverter {

    @Autowired
    private AuditoriumConverter auditoriumConverter;

    @Autowired
    private EventConverter eventConverter;

    @Override
    public AirDate createFromDTO(AirDateDTO dto) {
        return updateEntity(new AirDate(), dto);
    }

    @Override
    public AirDateDTO createFromEntity(AirDate entity) {
        AirDateDTO dto = new AirDateDTO();

        AuditoriumDTO auditoriumDTO = auditoriumConverter.createFromEntity(entity.getAuditorium());
        EventDTO eventDTO = eventConverter.createFromEntity(entity.getEvent());

        dto.setId(entity.getId());
        dto.setAuditorium(auditoriumDTO);
        dto.setDateTime(entity.getDateTime());
        dto.setEvent(eventDTO);

        return dto;
    }

    @Override
    public AirDate updateEntity(AirDate entity, AirDateDTO dto) {
        Auditorium auditorium = auditoriumConverter.createFromDTO(dto.getAuditorium());
        Event event = eventConverter.createFromDTO(dto.getEvent());

        entity.setId(dto.getId());
        entity.setAuditorium(auditorium);
        entity.setDateTime(dto.getDateTime());
        entity.setEvent(event);

        return entity;
    }
}
