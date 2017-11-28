package ua.epam.spring.hometask.repository;

import org.springframework.data.repository.CrudRepository;
import ua.epam.spring.hometask.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    User findFirstByEmail(String email);
}
