package ua.epam.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.dto.EventDTO;
import ua.epam.spring.hometask.facade.EventFacade;
import ua.epam.spring.hometask.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EventFacadeImpl implements EventFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private Converter<Event, EventDTO> eventConverter;

    @Override
    public List<EventDTO> findAll() {
        return eventService.findAll()
                .stream()
                .map(eventConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(EventDTO event) {
        Event eventEntity = eventConverter.convertToEntity(event);
        eventService.save(eventEntity);
    }

    @Override
    public void delete(long eventId) {
        eventService.findById(eventId).ifPresent(eventService::remove);
    }

    @Override
    public Optional<EventDTO> findById(long eventId) {
        return eventService.findById(eventId).map(eventConverter::convertToDTO);
    }

    @Override
    public Optional<EventDTO> findByName(String name) {
        return eventService.findByName(name).map(eventConverter::convertToDTO);
    }

    @Override
    public List<EventDTO> findForDateRange(LocalDateTime from, LocalDateTime to) {
        return eventService.findForDateRange(from, to)
                .stream()
                .map(eventConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> findNextEvents(LocalDateTime to) {
        return eventService.findNextEvents(to)
                .stream()
                .map(eventConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
