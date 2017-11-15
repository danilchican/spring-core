package ua.epam.spring.hometask.storage;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class AuditoriumStorage {
    private static Set<Auditorium> auditoriums = new HashSet<>();

    public static Set<Auditorium> getAuditoriums() {
        return auditoriums;
    }
}
