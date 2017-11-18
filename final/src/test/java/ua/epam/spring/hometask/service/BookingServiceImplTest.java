package ua.epam.spring.hometask.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.dao.TicketDAO;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.BookingServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @Mock
    private EventDAO eventDAO;

    @Mock
    private TicketDAO ticketDAO;

    @Mock
    private Event event;

    @Mock
    private Auditorium auditorium;

    @Mock
    private User user;

    @Mock
    private Set<Ticket> tickets;

    @Mock
    private Set<Long> seats;

    @InjectMocks
    private final BookingService bookingService = new BookingServiceImpl();

    @Test
    @Ignore
    public void calculateEventPriceByDiscountPercent_ReturnsEventPrice_WhenArgumentsPassed() throws Exception {
        bookingService.calculateEventPriceByDiscountPercent(10, 25);
        // TODO
    }

    @Test
    @Ignore
    public void getTicketsPrice_ReturnsTicketsPrice_WhenArgumentsAreValid() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();

        when(eventDAO.findAuditoriumOnDateTime(event.getAuditoriums(), dateTime)).thenReturn(auditorium);
        // TODO fails when(bookingService.calculateEventPrice(event, auditorium, seats, anyInt())).thenReturn(anyDouble());
        bookingService.getTicketsPrice(event, dateTime, user, seats);

        verify(eventDAO, times(1)).findAuditoriumOnDateTime(event.getAuditoriums(), dateTime);
        verify(bookingService, times(1)).calculateEventPrice(event, auditorium, seats, anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTicketsPrice_InvalidSomeNonnullArgument_ExceptionThrown() throws Exception {
        bookingService.getTicketsPrice(event, null, user, seats);
    }

    @Test
    @Ignore
    public void bookTickets_ReturnsNothing_WhenTicketsPassed() throws Exception {
        bookingService.bookTickets(tickets);
        // TODO How to test foreach?
    }

    @Test(expected = IllegalArgumentException.class)
    public void bookTickets_InvalidTickets_ExceptionThrown() throws Exception {
        bookingService.bookTickets(null);
    }

    @Test
    public void getPurchasedTicketsForEvent_ReturnsSetOfTickets_WhenEventAndDateIsNotNull() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        when(ticketDAO.getPurchasedTicketsForEvent(event, dateTime)).thenReturn(tickets);

        Collection<Ticket> collection = bookingService.getPurchasedTicketsForEvent(event, dateTime);
        assertThat(collection, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPurchasedTicketsForEvent_InvalidDateTime_ExceptionThrown() throws Exception {
        bookingService.getPurchasedTicketsForEvent(event, null);
    }
}