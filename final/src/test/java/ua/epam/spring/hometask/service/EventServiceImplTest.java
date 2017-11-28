package ua.epam.spring.hometask.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.repository.EventRepository;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.impl.EventServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private Event event;

    private static List<Event> events;

    @BeforeClass
    public static void setUp() {
        events = new ArrayList<>();
    }

    @InjectMocks
    private final EventService eventService = new EventServiceImpl();

    @Test
    public void getByName_ReturnsOptionalEvent_WhenEventWithSuchNameExists() throws Exception {
        when(eventRepository.findFirstByName(anyString())).thenReturn(event);

        Optional<Event> actual = eventService.getByName("event name");
        assertTrue(actual.isPresent());
    }

    @Test
    public void getByName_ReturnsEmpty_WhenUserWithSuchEmailDoesntExist() throws Exception {
        when(eventRepository.findFirstByName(anyString())).thenReturn(null);
        Optional<Event> actual = eventService.getByName("event name");

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByName_InvalidName_ExceptionThrown() throws Exception {
        eventService.getByName(null);
    }

    @Test
    public void getForDateRange_ReturnsCollectionOfEventsIncludedInRage_WhenCollectionIsNotNull() throws Exception {
        LocalDateTime from = LocalDateTime.of(2016, 4, 10, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2017, 2, 20, 0, 0, 0);
        when(eventRepository.findEventsByAirDates_dateTimeBetween(from, to)).thenReturn(events);

        Collection<Event> collection = eventService.getForDateRange(from, to);
        assertThat(collection, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getForDateRange_InvalidDates_ExceptionThrown() throws Exception {
        eventService.getForDateRange(null, null);
    }

    @Test
    public void getNextEvents_ReturnsCollectionOfEventsFromNowToDate_WhenCollectionIsNotNull() throws Exception {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.of(2017, 2, 20, 0, 0, 0);

        when(eventRepository.findEventsByAirDates_dateTimeBetween(from, to)).thenReturn(events);

        Collection<Event> collection = eventService.getNextEvents(to);
        assertThat(collection, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNextEvents_InvalidDate_ExceptionThrown() throws Exception {
        eventService.getNextEvents(null);
    }

    @Test
    public void save_ReturnsSavedOrUpdatedEvent_WhenEventPassed() throws Exception {
        when(eventRepository.save(event)).thenReturn(event);

        Event actual = eventService.save(event);
        assertThat(actual, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void save_InvalidUser_ExceptionThrown() throws Exception {
        eventService.save(null);
    }

    @Test
    public void remove_ReturnsNothing_WhenEventPassed() throws Exception {
        eventService.remove(event);
        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    public void getById_ReturnsOptionalEvent_WhenEventWithSuchIdExists() throws Exception {
        when(eventRepository.findOne(anyLong())).thenReturn(event);

        Optional<Event> actual = eventService.findById(1L);
        assertTrue(actual.isPresent());
    }

    @Test
    public void getById_ReturnsEmpty_WhenEventWithSuchIdDoesntExist() throws Exception {
        when(eventRepository.findOne(anyLong())).thenReturn(null);
        Optional<Event> actual = eventService.findById(1L);

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById_InvalidId_ExceptionThrown() throws Exception {
        eventService.findById(null);
    }

    @Test
    public void getAll_ReturnsCollectionOfEvents_WhenCollectionIsNotNull() throws Exception {
        when(eventRepository.findAll()).thenReturn(events);

        Collection<Event> collection = eventService.getAll();
        assertThat(collection, is(notNullValue()));
    }
}