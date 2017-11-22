package ua.epam.spring.hometask.service;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Nonnull;

/**
 * @param <T> DomainObject subclass
 * @author Yuriy_Tkach
 */
public interface AbstractDomainObjectService<T> {

    /**
     * Saving new object to dao or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    Optional<T> save(@Nonnull T object);

    /**
     * Removing object from dao
     *
     * @param object Object to remove
     */
    void remove(@Nonnull T object);

    /**
     * Getting object by id from dao
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    Optional<T> getById(@Nonnull Long id);

    /**
     * Getting all objects from dao
     *
     * @return collection of objects
     */
    @Nonnull
    Collection<T> getAll();
}
