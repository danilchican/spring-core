package ua.epam.spring.hometask.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.AirDateConverter;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.converter.SeatConverter;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.dto.TicketDTO;

@Component
public class TicketConverterImpl implements Converter<Ticket, TicketDTO> {

    @Autowired
    private AirDateConverter airDateConverter;

    @Autowired
    private SeatConverter seatConverter;

    @Autowired
    private UserConverterImpl userConverter;

    @Override
    public Ticket convertToEntity(TicketDTO dto) {
        Ticket entity = new Ticket();

        entity.setId(dto.getId());
        entity.setAirDate(airDateConverter.convertToEntity(dto.getAirDate()));
        entity.setSeat(seatConverter.convertToEntity(dto.getSeat()));
        entity.setUser(userConverter.convertToEntity(dto.getUser()));
        entity.setTicketPrice(dto.getTicketPrice());

        return entity;
    }

    @Override
    public TicketDTO convertToDTO(Ticket entity) {
        TicketDTO dto = new TicketDTO();

        dto.setId(entity.getId());
        dto.setAirDate(airDateConverter.convertToDTO(entity.getAirDate()));
        dto.setSeat(seatConverter.convertToDTO(entity.getSeat()));
        dto.setUser(userConverter.convertToDTO(entity.getUser()));
        dto.setTicketPrice(entity.getTicketPrice());

        return dto;
    }
}
