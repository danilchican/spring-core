package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.dto.UserDTO;

import java.util.Optional;

public interface UserFacade {

    /**
     * Find user by email.
     *
     * @param email of user
     * @return optional of user DTO
     */
    Optional<UserDTO> findUserByEmail(String email);
}
