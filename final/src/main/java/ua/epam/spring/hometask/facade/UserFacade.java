package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

    /**
     * Find user by email.
     *
     * @param email of user
     * @return optional of user DTO
     */
    Optional<UserDTO> findUserByEmail(String email);

    /**
     * Find all users.
     *
     * @return list of users
     */
    List<UserDTO> findAll();

    /**
     * Save new user.
     *
     * @param user to save
     */
    void save(UserDTO user);

    /**
     * Remove user if exists.
     *
     * @param userId
     */
    void delete(long userId);

    /**
     * Find user by id.
     *
     * @param userId
     * @return founded user
     */
    Optional<UserDTO> findById(long userId);
}
