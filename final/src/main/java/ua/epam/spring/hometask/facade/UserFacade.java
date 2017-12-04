package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.domain.User;
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
     * @return saved user
     */
    UserDTO save(UserDTO user);
}
