package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Optional;

/**
 * @author Yuriy_Tkach
 */
public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable
    Optional<User> findUserByEmail(@Nonnull String email);

    /**
     * Delete user by id.
     *
     * @param userId
     */
    void deleteUserById(long userId);
}
