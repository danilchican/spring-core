package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.repository.AuditoriumRepository;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumRepository.getAll();
    }

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    @Nullable
    @Override
    public Optional<Auditorium> getByName(@Nonnull String name) {
        return auditoriumRepository.getByName(name);
    }

    /**
     * Getting all seats from range.
     *
     * @param numberOfSeats
     * @return set of seats
     */
    @Nonnull
    @Override
    public Set<Long> getAllSeats(long numberOfSeats) {
        return LongStream.range(1, numberOfSeats + 1)
                .boxed()
                .collect(Collectors.toSet());
    }
}
