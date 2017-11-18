package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EventDAOImpl implements EventDAO {

    private static Set<Event> events;

    @Override
    public Optional<Event> getByName(String name) {
        return events.stream()
                .filter(Objects::nonNull)
                .filter(event -> name.equals(event.getName()))
                .findFirst();
    }

    @Override
    public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
        return events.stream()
                .filter(Objects::nonNull)
                .filter(event -> airsOnDates(event.getAirDates(), from, to))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        LocalDateTime from = LocalDateTime.now();

        return events.stream()
                .filter(Objects::nonNull)
                .filter(event -> airsOnDates(event.getAirDates(), from, to))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Event> save(Event object) {
        Optional<Event> foundedEvent = getById(object.getId());

        if (foundedEvent.isPresent()) {
            return update(foundedEvent.get(), object);
        }

        // TODO id
        events.add(object);
        return Optional.of(object);
    }

    @Override
    public Optional<Event> update(Event old, Event object) {
        events.remove(old);
        events.add(object);

        return Optional.of(object);
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

    public void setEvents(Set<Event> eventsSet) {
        events = eventsSet;
    }
}
