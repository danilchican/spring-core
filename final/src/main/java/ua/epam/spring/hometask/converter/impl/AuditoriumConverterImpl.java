package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AuditoriumConverter;
import ua.epam.spring.hometask.converter.SeatConverter;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Seat;
import ua.epam.spring.hometask.dto.AuditoriumDTO;
import ua.epam.spring.hometask.dto.SeatDTO;

import java.util.Set;

@Component
public class AuditoriumConverterImpl implements AuditoriumConverter {

    @Autowired
    private SeatConverter seatConverter;

    @Override
    public Auditorium createFromDTO(AuditoriumDTO dto) {
        return updateEntity(new Auditorium(), dto);
    }

    @Override
    public AuditoriumDTO createFromEntity(Auditorium entity) {
        AuditoriumDTO auditoriumDTO = new AuditoriumDTO();
        Set<SeatDTO> seats = seatConverter.createSetFromEntities(entity.getSeats());

        auditoriumDTO.setId(entity.getId());
        auditoriumDTO.setName(entity.getName());
        auditoriumDTO.setSeats(seats);
        auditoriumDTO.setSeatsAvailable(entity.getSeatsAvailable());

        return auditoriumDTO;
    }

    @Override
    public Auditorium updateEntity(Auditorium entity, AuditoriumDTO dto) {
        Set<Seat> seats = seatConverter.createSetFromDtos(dto.getSeats());

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSeats(seats);
        entity.setSeatsAvailable(dto.getSeatsAvailable());

        return entity;
    }
}
