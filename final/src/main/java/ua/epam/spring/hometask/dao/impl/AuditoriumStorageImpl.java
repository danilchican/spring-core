package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.HashSet;
import java.util.Set;

public class AuditoriumStorageImpl {
    private static Set<Auditorium> auditoriums = new HashSet<>();

    public static Set<Auditorium> getAuditoriums() {
        return auditoriums;
    }
}
