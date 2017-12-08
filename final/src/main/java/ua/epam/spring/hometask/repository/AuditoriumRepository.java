package ua.epam.spring.hometask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.spring.hometask.domain.Auditorium;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    Auditorium findFirstByName(String name);
}
