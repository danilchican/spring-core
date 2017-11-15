package ua.epam.spring.hometask.storage;

import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

public final class UserStorage {
    private static Map<Long, User> users = new HashMap<>();

    public static Map<Long, User> getUsers() {
        return users;
    }
}
