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

    public void setUsers(Map<Long, User> usersMap) {
        users = usersMap;
    }

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

        if (!foundedUser.isPresent()) {
            object.setId(UserIdIncrementator.next());
            foundedUser = Optional.of(object);
        }

        users.put(foundedUser.get().getId(), foundedUser.get());
        return foundedUser;
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
}
