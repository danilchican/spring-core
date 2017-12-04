package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.repository.AirDateRepository;
import ua.epam.spring.hometask.repository.EventRepository;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.AirDateService;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AirDateRepository airDateRepository;

    @Autowired
    private AirDateService airDateService;

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
    public boolean addAirDateTime(Event event, LocalDateTime dateTime, Auditorium auditorium) {
        AirDate foundedAirDate = airDateRepository.findByAuditoriumIdAndDateTime(auditorium.getId(), dateTime);

        /* Auditorium is not aired by date and time */
        if(foundedAirDate == null) {
            AirDate airDateToSave = createAirDate(event, dateTime, auditorium);
            airDateService.addAirDate(airDateToSave);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAirDateTime(Event event, Auditorium auditorium, LocalDateTime dateTime) {
        AirDate airDate = airDateRepository.findByEventIdAndAuditoriumIdAndDateTime(event.getId(), auditorium.getId(), dateTime);

        /* Air date exists */
        if(airDate != null) {
            airDateRepository.delete(airDate.getId());
            return true;
        }

        return false;
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

    private AirDate createAirDate(Event event, LocalDateTime dateTime, Auditorium auditorium) {
        AirDate date = new AirDate();

        date.setEvent(event);
        date.setDateTime(dateTime);
        date.setAuditorium(auditorium);

        return date;
    }
}
