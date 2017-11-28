package ua.epam.spring.hometask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.spring.hometask.domain.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    /**
     * Count seats of the auditorium.
     *
     * @param auditoriumId
     * @return seats count
     */
    Long countByAuditoriumId(Long auditoriumId);


    /**
     * Count of vip seats for the auditorium.
     *
     * @param auditoriumId Auditorium id
     * @return count of vip seats
     */
    Long countByAuditoriumIdAndVipIsTrue(Long auditoriumId);
}
