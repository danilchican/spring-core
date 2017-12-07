package ua.epam.spring.hometask.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.repository.AirDateRepository;
import ua.epam.spring.hometask.repository.AuditoriumRepository;
import ua.epam.spring.hometask.repository.TicketRepository;
import ua.epam.spring.hometask.service.impl.BookingServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private AuditoriumRepository auditoriumRepository;

    @Mock
    private AirDateRepository airDateRepository;

    @Mock
    private Event event;

    @Mock
    private Auditorium auditorium;

    @Mock
    private User user;

    @Mock
    private Ticket ticket;

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
        Set<Long> seats = new HashSet<>();

        when(airDateRepository.findByEventIdAndDateTime(event.getId(), dateTime)).thenReturn(auditorium);
        // TODO fail when(ratingCoefficients.get(event.getRating())).thenReturn(anyDouble());

        bookingService.getTicketsPrice(event, dateTime, user, seats);

        verify(discountService, times(1)).getDiscount(user, event, dateTime, anyInt());
        verify(airDateRepository, times(1)).findByEventIdAndDateTime(event.getId(), dateTime);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTicketsPrice_InvalidSomeNonnullArgument_ExceptionThrown() throws Exception {
        Set<Long> seats = new HashSet<>();
        bookingService.getTicketsPrice(event, null, user, seats);
    }

    @Test
    public void bookTickets_ReturnsNothing_WhenTicketsIsEmpty() throws Exception {
        Set<Ticket> tickets = new HashSet<>();
        bookingService.bookTickets(tickets);
        verify(ticketRepository, never()).save(ticket);
    }

    @Test
    public void bookTickets_ReturnsNothing_WhenTicketsHasAtLeastOneElement() throws Exception {
        Set<Ticket> tickets = new HashSet<Ticket>() {{
            add(ticket);
        }};

        bookingService.bookTickets(tickets);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bookTickets_InvalidTickets_ExceptionThrown() throws Exception {
        bookingService.bookTickets(null);
    }

    @Test
    public void getPurchasedTicketsForEvent_ReturnsSetOfTickets_WhenEventAndDateIsNotNull() throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        when(ticketRepository.findByAirDateEventIdAndAirDateDateTime(event.getId(), dateTime)).thenReturn(tickets);

        Collection<Ticket> collection = bookingService.getPurchasedTicketsForEvent(event.getId(), dateTime);
        assertThat(collection, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPurchasedTicketsForEvent_InvalidDateTime_ExceptionThrown() throws Exception {
        bookingService.getPurchasedTicketsForEvent(event.getId(), null);
    }
}