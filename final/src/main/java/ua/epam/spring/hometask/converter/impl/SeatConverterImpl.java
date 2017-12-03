package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AuditoriumConverter;
import ua.epam.spring.hometask.converter.SeatConverter;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Seat;
import ua.epam.spring.hometask.dto.AuditoriumDTO;
import ua.epam.spring.hometask.dto.SeatDTO;

@Component
public class SeatConverterImpl implements SeatConverter {

    @Autowired
    private AuditoriumConverter auditoriumConverter;

    @Override
    public Seat createFromDTO(SeatDTO dto) {
        return updateEntity(new Seat(), dto);
    }

    @Override
    public SeatDTO createFromEntity(Seat entity) {
        SeatDTO seatDTO = new SeatDTO();
        AuditoriumDTO auditoriumDTO = auditoriumConverter.createFromEntity(entity.getAuditorium());

        seatDTO.setId(entity.getId());
        seatDTO.setAuditorium(auditoriumDTO);
        seatDTO.setNumber(entity.getNumber());
        seatDTO.setRow(entity.getRow());
        seatDTO.setVip(entity.isVip());

        return seatDTO;
    }

    @Override
    public Seat updateEntity(Seat entity, SeatDTO dto) {
        Auditorium auditorium = auditoriumConverter.createFromDTO(dto.getAuditorium());

        entity.setId(dto.getId());
        entity.setAuditorium(auditorium);
        entity.setNumber(dto.getNumber());
        entity.setRow(dto.getRow());
        entity.setVip(dto.isVip());

        return entity;
    }
}
