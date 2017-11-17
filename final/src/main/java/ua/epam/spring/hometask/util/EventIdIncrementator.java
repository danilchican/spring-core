package ua.epam.spring.hometask.util;

public class EventIdIncrementator {
    private static Long id;

    public static Long next() {
        return ++id;
    }
}
