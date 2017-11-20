package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public interface AuditoriumDAO {

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    Set<Auditorium> getAll();

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    Optional<Auditorium> getByName(String name);

    /**
     * Get auditorium on particular <code>dateTime</code>
     *
     * @param auditoriums auditoriums for event
     * @param dateTime    Date and time of aired event
     * @return auditorium on particular <code>dateTime</code>
     */
    Auditorium findAuditoriumOnDateTime(NavigableMap<LocalDateTime, Auditorium> auditoriums, LocalDateTime dateTime);

}
