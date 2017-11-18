package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.TicketDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TicketDAOImpl implements TicketDAO {

    private static Set<Ticket> tickets;

    @Override
    public Optional<Ticket> save(Ticket object) {
        // TODO save & update
        return null;
    }

    @Override
    public void remove(Ticket object) {
        tickets.remove(object);
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        return tickets.stream()
                .filter(Objects::nonNull)
                .filter(ticket -> id.equals(ticket.getId()))
                .findFirst();
    }

    @Override
    public Collection<Ticket> getAll() {
        return tickets;
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return tickets.stream()
                .filter(Objects::nonNull)
                .filter(ticket -> Objects.equals(ticket.getEvent(), event))
                .filter(ticket -> Objects.equals(ticket.getDateTime(), dateTime))
                .collect(Collectors.toSet());
    }

    public void setTickets(Set<Ticket> ticketsSet) {
        tickets = ticketsSet;
    }
}
