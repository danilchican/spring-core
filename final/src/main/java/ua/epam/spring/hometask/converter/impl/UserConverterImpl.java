package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.converter.TicketConverter;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.dto.UserDTO;

@Component
public class UserConverterImpl implements Converter<User, UserDTO> {

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public User convertToEntity(UserDTO dto) {
        User entity = new User();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthday(dto.getBirthday());
        entity.setEmail(dto.getEmail());
        entity.setTickets(ticketConverter.convertToEntitySet(dto.getTickets()));

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
        dto.setTickets(ticketConverter.convertToDTOSet(entity.getTickets()));

        return dto;
    }
}
