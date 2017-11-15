package ua.epam.spring.hometask.util;

public class UserIdIncrementator {
    private static Long id;

    public static Long next() {
        return ++id;
    }

}
