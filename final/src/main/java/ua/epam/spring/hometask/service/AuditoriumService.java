package ua.epam.spring.hometask.service;

import org.springframework.data.domain.Page;
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
    List<Auditorium> getAll();

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    @Nullable
    Optional<Auditorium> getByName(@Nonnull String name);


    /**
     * Getting all seats from range.
     *
     * @param page
     * @param numberOfSeats
     * @return set of seats
     */
    @Nonnull
    Page<Auditorium> getAllSeats(int page, int numberOfSeats);
}
