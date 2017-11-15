package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.storage.UserStorage;
import ua.epam.spring.hometask.util.UserIdIncrementator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

public class UserServiceImpl implements UserService {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        Map<Long, User> users = UserStorage.getUsers();

        Map.Entry<Long, User> userEntry = users.entrySet().stream()
                .filter(e -> e.getValue() != null && e.getValue().getEmail().equals(email))
                .findFirst()
                .orElse(null);

        return userEntry != null
                ? userEntry.getValue()
                : null;
    }

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    @Override
    public User save(@Nonnull User object) {
        Map<Long, User> users = UserStorage.getUsers();

        object.setId(UserIdIncrementator.next());
        users.put(object.getId(), object);

        return object;
    }

    /**
     * Removing object from storage
     *
     * @param object Object to remove
     */
    @Override
    public void remove(@Nonnull User object) {
        UserStorage.getUsers().remove(object.getId());
    }

    /**
     * Getting object by id from storage
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    @Override
    public User getById(@Nonnull Long id) {
        return UserStorage.getUsers().get(id);
    }

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    @Nonnull
    @Override
    public Collection<User> getAll() {
        return UserStorage.getUsers().values();
    }
}
