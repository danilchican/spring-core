package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Yuriy_Tkach
 */
public interface AuditoriumService {

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    @Nonnull
    List<Auditorium> findAll();

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    @Nullable
    Optional<Auditorium> findByName(@Nonnull String name);
}
