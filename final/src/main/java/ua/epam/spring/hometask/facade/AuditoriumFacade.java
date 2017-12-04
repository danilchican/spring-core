package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.dto.AuditoriumDTO;

import java.util.List;
import java.util.Optional;

public interface AuditoriumFacade {

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    List<AuditoriumDTO> findAll();

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    Optional<AuditoriumDTO> findAuditoriumByName(String name);
}
