package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import java.util.Optional;

public interface UserDAO extends AbstractDAO<User> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    Optional<User> getUserByEmail(String email);
}
