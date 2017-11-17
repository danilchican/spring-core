package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.UserDAO;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable
    @Override
    public Optional<User> getUserByEmail(@Nonnull String email) {
        return userDAO.getUserByEmail(email);
    }

    /**
     * Saving new object to dao or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    @Override
    public Optional<User> save(@Nonnull User object) {
        return userDAO.save(object);
    }

    /**
     * Removing object from dao
     *
     * @param object Object to remove
     */
    @Override
    public void remove(@Nonnull User object) {
        userDAO.remove(object);
    }

    /**
     * Getting object by id from dao
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    @Override
    public Optional<User> getById(@Nonnull Long id) {
        return userDAO.getById(id);
    }

    /**
     * Getting all objects from dao
     *
     * @return collection of objects
     */
    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }
}
