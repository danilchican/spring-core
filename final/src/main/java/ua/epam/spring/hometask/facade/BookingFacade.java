package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.dto.TicketDTO;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingFacade {

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param eventId  Event to get tickets for
     * @param dateTime Date and time of airing of event
     * @return set of all purchased tickets
     */
    List<TicketDTO> findPurchasedTicketsForEvent(long eventId, LocalDateTime dateTime);
}
