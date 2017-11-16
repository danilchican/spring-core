package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Optional;
import java.util.Set;

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
}
