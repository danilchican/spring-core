package ua.epam.spring.hometask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.spring.hometask.domain.AirDate;

public interface AirDateRepository extends JpaRepository<AirDate, Long> {
}
