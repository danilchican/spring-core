package ua.epam.spring.hometask.converter;

import ua.epam.spring.hometask.domain.Seat;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.dto.SeatDTO;
import ua.epam.spring.hometask.dto.TicketDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface SeatConverter extends Converter<Seat, SeatDTO> {

    default Set<SeatDTO> convertToDTOSet(final Collection<Seat> entities) {
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    default Set<Seat> convertToEntitySet(final Collection<SeatDTO> dtos) {
        return dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet());
    }
}
