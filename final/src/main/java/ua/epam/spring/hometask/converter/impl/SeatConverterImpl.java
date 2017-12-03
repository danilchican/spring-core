package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AuditoriumConverter;
import ua.epam.spring.hometask.converter.SeatConverter;
import ua.epam.spring.hometask.domain.Seat;
import ua.epam.spring.hometask.dto.SeatDTO;

@Component
public class SeatConverterImpl implements SeatConverter {

    @Autowired
    private AuditoriumConverter auditoriumConverter;

    @Override
    public Seat convertToEntity(SeatDTO dto) {
        Seat entity = new Seat();

        entity.setId(dto.getId());
        entity.setAuditorium(auditoriumConverter.convertToEntity(dto.getAuditorium()));
        entity.setNumber(dto.getNumber());
        entity.setRow(dto.getRow());
        entity.setVip(dto.isVip());

        return entity;
    }

    @Override
    public SeatDTO convertToDTO(Seat entity) {
        SeatDTO seatDTO = new SeatDTO();

        seatDTO.setId(entity.getId());
        seatDTO.setAuditorium(auditoriumConverter.convertToDTO(entity.getAuditorium()));
        seatDTO.setNumber(entity.getNumber());
        seatDTO.setRow(entity.getRow());
        seatDTO.setVip(entity.isVip());

        return seatDTO;
    }
}
