package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.AuditoriumDAO;
import ua.epam.spring.hometask.domain.Auditorium;

import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Repository
public class AuditoriumDAOImpl implements AuditoriumDAO {

    private static Set<Auditorium> auditoriums;

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    @Override
    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    @Override
    public Optional<Auditorium> getByName(String name) {
        return auditoriums.stream()
                .filter(Objects::nonNull)
                .filter(aud -> name.equals(aud.getName()))
                .findFirst();
    }

    @Override
    public Auditorium findAuditoriumOnDateTime(NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime) {
        return auditoriums.get(dateTime);
    }

    public void setAuditoriums(Set<Auditorium> auditoriumsSet) {
        auditoriums = auditoriumsSet;
    }
}
