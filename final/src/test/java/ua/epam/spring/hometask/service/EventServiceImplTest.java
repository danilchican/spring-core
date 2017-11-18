package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.impl.EventServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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
    private EventDAO eventDAO;

    @Mock
    private Event event;

    @Mock
    private Set<Event> events;

    @InjectMocks
    private final EventService eventService = new EventServiceImpl();

    @Test
    public void getByName_ReturnsOptionalEvent_WhenEventWithSuchNameExists() throws Exception {
        when(eventDAO.getByName(anyString())).thenReturn(Optional.of(event));

        Optional<Event> actual = eventService.getByName("event name");
        assertTrue(actual.isPresent());
    }

    @Test
    public void getByName_ReturnsEmpty_WhenUserWithSuchEmailDoesntExist() throws Exception {
        when(eventDAO.getByName(anyString())).thenReturn(Optional.empty());
        Optional<Event> actual = eventService.getByName("event name");

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByName_InvalidName_ExceptionThrown() throws Exception {
        eventService.getByName(null);
    }

    @Test
    public void getForDateRange_ReturnsCollectionOfEventsIncludedInRage_WhenCollectionIsNotNull() throws Exception {
        LocalDate from = LocalDate.of(2016, 4, 10);
        LocalDate to = LocalDate.of(2017, 2, 20);
        when(eventDAO.getForDateRange(from, to)).thenReturn(events);

        Collection<Event> collection = eventService.getForDateRange(from, to);
        assertThat(collection, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getForDateRange_InvalidDates_ExceptionThrown() throws Exception {
        eventService.getForDateRange(null, null);
    }

    @Test
    public void getNextEvents_ReturnsCollectionOfEventsFromNowToDate_WhenCollectionIsNotNull() throws Exception {
        LocalDateTime to = LocalDateTime.now();
        when(eventDAO.getNextEvents(to)).thenReturn(events);

        Collection<Event> collection = eventService.getNextEvents(to);
        assertThat(collection, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNextEvents_InvalidDate_ExceptionThrown() throws Exception {
        eventService.getNextEvents(null);
    }

    @Test
    public void save_ReturnsSavedOrUpdatedEvent_WhenEventPassed() throws Exception {
        when(eventDAO.save(event)).thenReturn(Optional.of(event));

        Optional<Event> actual = eventService.save(event);
        assertTrue(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void save_InvalidUser_ExceptionThrown() throws Exception {
        eventService.save(null);
    }

    @Test
    public void remove_ReturnsNothing_WhenEventPassed() throws Exception {
        eventService.remove(event);
        verify(eventDAO, times(1)).remove(event);
    }

    @Test
    public void getById_ReturnsOptionalEvent_WhenEventWithSuchIdExists() throws Exception {
        when(eventDAO.getById(anyLong())).thenReturn(Optional.of(event));

        Optional<Event> actual = eventService.getById(1L);
        assertTrue(actual.isPresent());
    }

    @Test
    public void getById_ReturnsEmpty_WhenEventWithSuchIdDoesntExist() throws Exception {
        when(eventDAO.getById(anyLong())).thenReturn(Optional.empty());
        Optional<Event> actual = eventService.getById(1L);

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById_InvalidId_ExceptionThrown() throws Exception {
        eventService.getById(null);
    }

    @Test
    public void getAll_ReturnsCollectionOfEvents_WhenCollectionIsNotNull() throws Exception {
        when(eventDAO.getAll()).thenReturn(events);

        Collection<Event> collection = eventService.getAll();
        assertThat(collection, is(notNullValue()));
    }
}