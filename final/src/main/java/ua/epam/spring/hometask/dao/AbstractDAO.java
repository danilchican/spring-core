package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.DomainObject;

import java.util.Collection;
import java.util.Optional;

interface AbstractDAO<T extends DomainObject> {

    /**
     * Saving new object to dao or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    Optional<T> save(T object);

    /**
     * Removing object from dao
     *
     * @param object Object to remove
     */
    void remove(T object);

    /**
     * Getting object by id from dao
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    Optional<T> getById(Long id);

    /**
     * Getting all objects from dao
     *
     * @return collection of objects
     */
    Collection<T> getAll();
}
