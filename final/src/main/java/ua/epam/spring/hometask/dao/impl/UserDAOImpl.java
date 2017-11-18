package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.UserDAO;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.util.UserIdIncrementator;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private static Map<Long, User> users;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.entrySet().stream()
                .filter(Objects::nonNull)
                .filter(user -> email.equals(user.getValue().getEmail()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    @Override
    public Optional<User> save(User object) {
        Optional<User> foundedUser = getById(object.getId());

        if (foundedUser.isPresent()) {
            return update(foundedUser.get(), object);
        }

        // TODO id
        users.put(object.getId(), object);
        return foundedUser;
    }

    @Override
    public Optional<User> update(User old, User object) {
        users.put(object.getId(), object);
        return Optional.of(object);
    }

    @Override
    public void remove(User object) {
        users.remove(object.getId());
    }

    @Override
    public Optional<User> getById(Long id) {
        return users.entrySet().stream()
                .filter(Objects::nonNull)
                .filter(user -> id.equals(user.getValue().getId()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    public void setUsers(Map<Long, User> usersMap) {
        users = usersMap;
    }
}
