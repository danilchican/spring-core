package ua.epam.spring.hometask.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.Auditorium;

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
    Set<Auditorium> getAll();

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
     * @param numberOfSeats
     * @return set of seats
     */
    @Nonnull
    Set<Long> getAllSeats(long numberOfSeats);
}
