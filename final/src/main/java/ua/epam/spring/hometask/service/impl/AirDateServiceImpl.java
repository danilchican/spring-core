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
    public void addAirDate(AirDate airDate) {
        airDateRepository.save(createAirDate(airDate));
    }

    @Override
    public void removeAirDateTime(AirDate airDate) {
        airDateRepository.delete(airDate.getId());
    }

    private AirDate createAirDate(AirDate airDate) {
        AirDate airDateToSave = new AirDate();

        airDateToSave.setAuditorium(airDate.getAuditorium());
        airDateToSave.setDateTime(airDate.getDateTime());
        airDateToSave.setEvent(airDate.getEvent());

        return airDateToSave;
    }
}
