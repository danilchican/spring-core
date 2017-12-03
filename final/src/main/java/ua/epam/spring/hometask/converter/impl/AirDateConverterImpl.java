package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AirDateConverter;
import ua.epam.spring.hometask.converter.AuditoriumConverter;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.dto.AirDateDTO;

@Component
public class AirDateConverterImpl implements AirDateConverter {

    @Autowired
    private AuditoriumConverter auditoriumConverter;

    @Autowired
    private EventConverterImpl eventConverter;

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
