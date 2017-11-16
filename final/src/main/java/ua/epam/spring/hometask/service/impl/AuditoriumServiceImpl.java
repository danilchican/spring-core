package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.dao.impl.AuditoriumStorageImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService {

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return AuditoriumStorageImpl.getAuditoriums();
    }

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Set<Auditorium> auditoriums = AuditoriumStorageImpl.getAuditoriums();

        return auditoriums.stream()
                .filter(a -> a != null && name.equals(a.getName()))
                .findFirst()
                .orElse(null);
    }
}
