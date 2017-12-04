package ua.epam.spring.hometask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.epam.spring.hometask.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    User findFirstByEmail(String email);

    /**
     * Delete user by id.
     *
     * @param userId
     * @return count of modified rows
     */
    Long deleteById(Long userId);
}
