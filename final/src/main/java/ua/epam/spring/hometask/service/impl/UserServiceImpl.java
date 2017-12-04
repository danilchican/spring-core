package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable
    @Override
    public Optional<User> findUserByEmail(@Nonnull String email) {
        return Optional.ofNullable(userRepository.findFirstByEmail(email));
    }

    /**
     * Saving new object to repository or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    @Override
    public void save(@Nonnull User object) {
        userRepository.save(object);
    }

    /**
     * Removing object from repository
     *
     * @param object Object to remove
     */
    @Override
    public void remove(@Nonnull User object) {
        userRepository.delete(object);
    }

    /**
     * Getting object by id from repository
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    @Override
    public Optional<User> findById(@Nonnull Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    /**
     * Getting all objects from repository
     *
     * @return collection of objects
     */
    @Nonnull
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
