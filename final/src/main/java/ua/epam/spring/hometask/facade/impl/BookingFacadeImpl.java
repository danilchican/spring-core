package ua.epam.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.facade.BookingFacade;
import ua.epam.spring.hometask.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private BookingService bookingService;

    @Autowired
    @Qualifier("ticketConverter")
    private Converter<Ticket, TicketDTO> ticketConverter;

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     *
     * @param tickets Set of tickets
     */
    @Override
    public void bookTickets(Set<TicketDTO> tickets) {
        Set<Ticket> convertedTickets = tickets
                .stream()
                .map(ticketConverter::convertToEntity)
                .collect(Collectors.toSet());

        bookingService.bookTickets(convertedTickets);
    }

    @Override
    public List<TicketDTO> findPurchasedTicketsForEvent(long eventId, LocalDateTime dateTime) {
        return bookingService.getPurchasedTicketsForEvent(eventId, dateTime)
                .stream()
                .map(ticketConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
