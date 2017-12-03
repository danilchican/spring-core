package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.converter.TicketConverter;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.dto.UserDTO;

import java.util.Set;

@Component
public class UserConverterImpl implements Converter<User, UserDTO> {

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public User createFromDTO(UserDTO dto) {
        return updateEntity(new User(), dto);
    }

    @Override
    public UserDTO createFromEntity(User entity) {
        Set<TicketDTO> tickets = ticketConverter.createSetFromEntities(entity.getTickets());
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setBirthday(entity.getBirthday());
        dto.setTickets(tickets);

        return dto;
    }

    @Override
    public User updateEntity(User entity, UserDTO dto) {
        Set<Ticket> tickets = ticketConverter.createSetFromDtos(dto.getTickets());

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthday(dto.getBirthday());
        entity.setEmail(dto.getEmail());
        entity.setTickets(tickets);

        return entity;
    }
}
