package ua.epam.spring.hometask.converter;

import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.dto.TicketDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface TicketConverter extends Converter<Ticket, TicketDTO> {

    default Set<TicketDTO> convertToDTOSet(final Collection<Ticket> entities) {
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    default Set<Ticket> convertToEntitySet(final Collection<TicketDTO> dtos) {
        return dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet());
    }
}
