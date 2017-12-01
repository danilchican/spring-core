package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.repository.AirDateRepository;
import ua.epam.spring.hometask.service.AirDateService;

import java.time.LocalDateTime;

@Service
public class AirDateServiceImpl implements AirDateService {

    @Autowired
    private AirDateRepository airDateRepository;

    @Override
    public void addAirDate(Event event, LocalDateTime dateTime, Auditorium auditorium) {
        AirDate airDate = new AirDate();

        airDate.setAuditorium(auditorium);
        airDate.setDateTime(dateTime);
        airDate.setEvent(event);

        airDateRepository.save(airDate);
    }
}