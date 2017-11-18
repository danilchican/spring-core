package ua.epam.spring.hometask.util;

public class UserIdIncrementator {
    private static Long id = 0L;

    public static Long next() {
        return ++id;
    }
}
