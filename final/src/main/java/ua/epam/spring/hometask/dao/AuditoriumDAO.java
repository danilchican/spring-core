package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Collection;
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
     * Counts how many vip seats are there in supplied <code>seats</code>
     *
     * @param vipSeats Vip seats to process
     * @param seats Seats to process
     * @return number of vip seats in request
     */
    default long countVipSeats(Set<Long> vipSeats, Collection<Long> seats) {
        return seats.stream().filter(vipSeats::contains).count();
    }

    /**
     * Getting all seats from range.
     *
     * @param numberOfSeats
     * @return set of seats
     */
    default Set<Long> getAllSeats(long numberOfSeats) {
        return LongStream.range(1, numberOfSeats + 1)
                .boxed()
                .collect(Collectors.toSet());
    }
}
