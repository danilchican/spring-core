package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AuditoriumConverter;
import ua.epam.spring.hometask.converter.SeatConverter;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.dto.AuditoriumDTO;

@Component
public class AuditoriumConverterImpl implements AuditoriumConverter {

    @Autowired
    private SeatConverter seatConverter;

    @Override
    public Auditorium convertToEntity(AuditoriumDTO dto) {
        Auditorium entity = new Auditorium();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSeats(seatConverter.convertToEntitySet(dto.getSeats()));
        entity.setSeatsAvailable(dto.getSeatsAvailable());

        return entity;
    }

    @Override
    public AuditoriumDTO convertToDTO(Auditorium entity) {
        AuditoriumDTO auditoriumDTO = new AuditoriumDTO();

        auditoriumDTO.setId(entity.getId());
        auditoriumDTO.setName(entity.getName());
        auditoriumDTO.setSeats(seatConverter.convertToDTOSet(entity.getSeats()));
        auditoriumDTO.setSeatsAvailable(entity.getSeatsAvailable());

        return auditoriumDTO;
    }
}
