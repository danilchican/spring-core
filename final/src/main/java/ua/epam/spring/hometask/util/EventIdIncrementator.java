package ua.epam.spring.hometask.util;

public class EventIdIncrementator {
    private static Long id = 0L;

    public static Long next() {
        return ++id;
    }
}
