package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.repository.AuditoriumRepository;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

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
    public List<Auditorium> getAll() {
        return auditoriumRepository.findAll();
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
        return Optional.ofNullable(auditoriumRepository.findFirstByName(name));
    }

    /**
     * Getting all seats from range.
     *
     * @param numberOfSeats
     * @return set of seats
     */
    @Nonnull
    @Override
    public Page<Auditorium> getAllSeats(int page, int numberOfSeats) {
        PageRequest request = PageRequest.of(page, numberOfSeats + 1);
        return auditoriumRepository.findAll(request);
    }
}
