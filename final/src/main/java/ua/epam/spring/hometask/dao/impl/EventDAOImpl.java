package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.util.EventIdIncrementator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class EventDAOImpl implements EventDAO {

    private static Set<Event> events;

    public void setEvents(Set<Event> eventsSet) {
        events = eventsSet;
    }

    @Override
    public Optional<Event> getByName(String name) {
        return events.stream()
                .filter(Objects::nonNull)
                .filter(event -> name.equals(event.getName()))
                .findFirst();
    }

    @Override
    public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
        // TODO
        return null;
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        // TODO
        return null;
    }

    @Override
    public Optional<Event> save(Event object) {
        Optional<Event> foundedEvent = getById(object.getId());

        if (!foundedEvent.isPresent()) {
            object.setId(EventIdIncrementator.next());
            foundedEvent = Optional.of(object);
        }

        events.add(foundedEvent.get());
        return foundedEvent;
    }

    @Override
    public void remove(Event object) {
        events.remove(object);
    }

    @Override
    public Optional<Event> getById(Long id) {
        return events.stream()
                .filter(Objects::nonNull)
                .filter(event -> id.equals(event.getId()))
                .findFirst();
    }

    @Override
    public Collection<Event> getAll() {
        return events;
    }
}
