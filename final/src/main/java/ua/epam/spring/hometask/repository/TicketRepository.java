package ua.epam.spring.hometask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param eventId  Event to get tickets for
     * @param dateTime Date and time of airing of event
     * @return set of all purchased tickets
     */
    List<Ticket> findByEvent_IdAndDateTimeEquals(Long eventId, LocalDateTime dateTime);
}
