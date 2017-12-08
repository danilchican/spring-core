package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.repository.EventRepository;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Nullable
    @Override
    public Optional<Event> findByName(@Nonnull String name) {
        return Optional.ofNullable(eventRepository.findFirstByName(name));
    }

    @Nonnull
    @Override
    public List<Event> findForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to) {
        return eventRepository.findEventsByAirDatesDateTimeBetween(from, to);
    }

    @Nonnull
    @Override
    public List<Event> findNextEvents(@Nonnull LocalDateTime to) {
        return findForDateRange(LocalDateTime.now(), to);
    }

    @Override
    public void deleteEventById(long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public void save(@Nonnull Event object) {
        eventRepository.save(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventRepository.delete(object);
    }

    @Override
    public Optional<Event> findById(@Nonnull Long id) {
        return Optional.ofNullable(eventRepository.findOne(id));
    }

    @Nonnull
    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
