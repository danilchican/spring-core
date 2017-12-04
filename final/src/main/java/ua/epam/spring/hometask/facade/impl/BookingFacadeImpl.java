package ua.epam.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.dto.EventDTO;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.facade.BookingFacade;
import ua.epam.spring.hometask.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private Converter<Ticket, TicketDTO> ticketConverter;

    @Override
    public List<TicketDTO> findPurchasedTicketsForEvent(long eventId, LocalDateTime dateTime) {
        return bookingService.getPurchasedTicketsForEvent(eventId, dateTime)
                .stream()
                .map(ticketConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
