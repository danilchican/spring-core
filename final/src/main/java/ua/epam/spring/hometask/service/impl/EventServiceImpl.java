package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.AuditoriumDAO;
import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private AuditoriumDAO auditoriumDAO;

    @Nullable
    @Override
    public Optional<Event> getByName(@Nonnull String name) {
        return eventDAO.getByName(name);
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return eventDAO.getForDateRange(from, to);
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return eventDAO.getNextEvents(to);
    }

    @Override
    public boolean assignAuditorium(Event event, LocalDateTime dateTime, Auditorium auditorium) {
        NavigableSet<LocalDateTime> airDates = event.getAirDates();
        NavigableMap<LocalDateTime, Auditorium> auditoriums = event.getAuditoriums();

        if (airDates.contains(dateTime)) {
            auditoriums.put(dateTime, auditorium);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAirDateTime(Event event, LocalDateTime dateTime) {
        NavigableSet<LocalDateTime> airDates = event.getAirDates();
        NavigableMap<LocalDateTime, Auditorium> auditoriums = event.getAuditoriums();

        boolean isRemoved = airDates.remove(dateTime);

        if (isRemoved) {
            auditoriums.remove(dateTime);
        }

        return isRemoved;
    }

    @Override
    public boolean removeAuditoriumAssignment(NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime) {
        return auditoriums.remove(dateTime) != null;
    }

    @Override
    public boolean airsOnDate(NavigableSet<LocalDateTime> airDates, LocalDate date) {
        //TODO Remove when integrated with DB.
        return airDates.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
    }

    @Override
    public boolean airsOnDateTime(NavigableSet<LocalDateTime> airDates, LocalDateTime dateTime) {
        //TODO Remove when integrated with DB.
        return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
    }

    @Override
    public boolean addAirDateTime(Event event, LocalDateTime dateTime, Auditorium auditorium) {
        NavigableSet<LocalDateTime> airDates = event.getAirDates();

        boolean result = eventDAO.addAirDateTime(airDates, dateTime);

        if (result) {
            // TODO duplicate assignAuditorium
            assignAuditorium(event, dateTime, auditorium);
        }

        return result;
    }

    @Override
    public Optional<Event> save(@Nonnull Event object) {
        return eventDAO.save(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDAO.remove(object);
    }

    @Override
    public Optional<Event> getById(@Nonnull Long id) {
        return eventDAO.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDAO.getAll();
    }
}
