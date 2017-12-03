package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.dto.UserDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component("userConverter")
public class UserConverterImpl implements Converter<User, UserDTO> {

    @Autowired
    @Qualifier("ticketConverter")
    private Converter<Ticket, TicketDTO> ticketConverter;

    @Override
    public User convertToEntity(UserDTO dto) {
        User entity = new User();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthday(dto.getBirthday());
        entity.setEmail(dto.getEmail());
        entity.setTickets(convertToEntitySet(dto.getTickets()));

        return entity;
    }

    @Override
    public UserDTO convertToDTO(User entity) {
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setBirthday(entity.getBirthday());
        dto.setTickets(convertToDTOSet(entity.getTickets()));

        return dto;
    }

    private Set<TicketDTO> convertToDTOSet(final Collection<Ticket> entities) {
        return entities.stream()
                .map(ticketConverter::convertToDTO)
                .collect(Collectors.toSet());
    }

    private Set<Ticket> convertToEntitySet(final Collection<TicketDTO> dtos) {
        return dtos.stream()
                .map(ticketConverter::convertToEntity)
                .collect(Collectors.toSet());
    }
}
