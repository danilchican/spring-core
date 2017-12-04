package ua.epam.spring.hometask.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

/**
 * @param <T> DomainObject subclass
 * @author Yuriy_Tkach
 */
public interface AbstractDomainObjectService<T> {

    /**
     * Saving new object to repository or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    T save(@Nonnull T object);

    /**
     * Removing object from repository
     *
     * @param object Object to remove
     */
    void remove(@Nonnull T object);

    /**
     * Getting object by id from repository
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    Optional<T> findById(@Nonnull Long id);

    /**
     * Getting all objects from repository
     *
     * @return collection of objects
     */
    @Nonnull
    List<T> findAll();
}
