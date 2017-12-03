package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AirDateConverter;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.converter.SeatConverter;
import ua.epam.spring.hometask.converter.UserConverter;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Seat;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.dto.AirDateDTO;
import ua.epam.spring.hometask.dto.SeatDTO;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.dto.UserDTO;

@Component
public class TicketConverterImpl implements Converter<Ticket, TicketDTO> {

    @Autowired
    private AirDateConverter airDateConverter;

    @Autowired
    private SeatConverter seatConverter;

    @Autowired
    private UserConverter userConverter;

    @Override
    public Ticket createFromDTO(TicketDTO dto) {
        return updateEntity(new Ticket(), dto);
    }

    @Override
    public TicketDTO createFromEntity(Ticket entity) {
        TicketDTO dto = new TicketDTO();

        AirDateDTO airDateDTO = airDateConverter.createFromEntity(entity.getAirDate());
        SeatDTO seatDTO = seatConverter.createFromEntity(entity.getSeat());
        UserDTO userDTO = userConverter.createFromEntity(entity.getUser());

        dto.setId(entity.getId());
        dto.setAirDate(airDateDTO);
        dto.setSeat(seatDTO);
        dto.setUser(userDTO);
        dto.setTicketPrice(entity.getTicketPrice());

        return dto;
    }

    @Override
    public Ticket updateEntity(Ticket entity, TicketDTO dto) {
        AirDate airDate = airDateConverter.createFromDTO(dto.getAirDate());
        Seat seat = seatConverter.createFromDTO(dto.getSeat());
        User user = userConverter.createFromDTO(dto.getUser());

        entity.setId(dto.getId());
        entity.setAirDate(airDate);
        entity.setSeat(seat);
        entity.setUser(user);
        entity.setTicketPrice(dto.getTicketPrice());

        return entity;
    }
}
