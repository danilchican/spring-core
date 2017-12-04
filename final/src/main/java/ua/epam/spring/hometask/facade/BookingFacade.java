package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.dto.TicketDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingFacade {

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     *
     * @param tickets Set of tickets
     */
    void bookTickets(Set<TicketDTO> tickets);

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param eventId  Event to get tickets for
     * @param dateTime Date and time of airing of event
     * @return set of all purchased tickets
     */
    List<TicketDTO> findPurchasedTicketsForEvent(long eventId, LocalDateTime dateTime);
}
