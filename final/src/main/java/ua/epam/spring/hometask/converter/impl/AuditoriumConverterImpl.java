package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Seat;
import ua.epam.spring.hometask.dto.AuditoriumDTO;
import ua.epam.spring.hometask.dto.SeatDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component("auditoriumConverter")
public class AuditoriumConverterImpl implements Converter<Auditorium, AuditoriumDTO> {

    @Autowired
    @Qualifier("seatConverter")
    private Converter<Seat, SeatDTO> seatConverter;

    @Override
    public Auditorium convertToEntity(AuditoriumDTO dto) {
        Auditorium entity = new Auditorium();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSeats(convertToEntitySet(dto.getSeats()));
        entity.setSeatsAvailable(dto.getSeatsAvailable());

        return entity;
    }

    @Override
    public AuditoriumDTO convertToDTO(Auditorium entity) {
        AuditoriumDTO auditoriumDTO = new AuditoriumDTO();

        auditoriumDTO.setId(entity.getId());
        auditoriumDTO.setName(entity.getName());
        auditoriumDTO.setSeats(convertToDTOSet(entity.getSeats()));
        auditoriumDTO.setSeatsAvailable(entity.getSeatsAvailable());

        return auditoriumDTO;
    }

    private Set<SeatDTO> convertToDTOSet(final Collection<Seat> entities) {
        return entities.stream()
                .map(seatConverter::convertToDTO)
                .collect(Collectors.toSet());
    }

    private Set<Seat> convertToEntitySet(final Collection<SeatDTO> dtos) {
        return dtos.stream()
                .map(seatConverter::convertToEntity)
                .collect(Collectors.toSet());
    }
}
