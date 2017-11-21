package ua.epam.spring.hometask.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.dao.AuditoriumDAO;
import ua.epam.spring.hometask.dao.TicketDAO;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.impl.BookingServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @Mock
    private TicketDAO ticketDAO;

    @Mock
    private AuditoriumDAO auditoriumDAO;

    @Mock
    private Event event;

    @Mock
    private Auditorium auditorium;

    @Mock
    private User user;

    @Mock
    private Ticket ticket;

    @Mock
    private Set<Ticket> tickets;

    @Mock
    private Set<Long> seats;

    @Mock
    private static Map<EventRating, Double> ratingCoefficients;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private final BookingService bookingService = new BookingServiceImpl();

    @Test
    @Ignore
    public void getTicketsPrice_ReturnsTicketsPrice_WhenArgumentsAreValid() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();

        when(auditoriumDAO.findAuditoriumOnDateTime(event.getAuditoriums(), dateTime)).thenReturn(auditorium);
        // TODO fail when(ratingCoefficients.get(event.getRating())).thenReturn(anyDouble());

        bookingService.getTicketsPrice(event, dateTime, user, seats);

        verify(discountService, times(1)).getDiscount(user, event, dateTime, anyInt());
        verify(auditoriumDAO, times(1)).findAuditoriumOnDateTime(event.getAuditoriums(), dateTime);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTicketsPrice_InvalidSomeNonnullArgument_ExceptionThrown() throws Exception {
        bookingService.getTicketsPrice(event, null, user, seats);
    }

    @Test
    public void bookTickets_ReturnsNothing_WhenTicketsIsEmpty() throws Exception {
        bookingService.bookTickets(tickets);
        verify(ticketDAO, never()).save(ticket);
    }

    @Test
    public void bookTickets_ReturnsNothing_WhenTicketsHasAtLeastOneElement() throws Exception {
        Set<Ticket> tickets = new HashSet<Ticket>() {{
            add(ticket);
        }};

        bookingService.bookTickets(tickets);
        verify(ticketDAO, times(1)).save(ticket);
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