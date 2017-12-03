package ua.epam.spring.hometask.facade;

import ua.epam.spring.hometask.dto.UserDTO;

public interface UserFacade {
    UserDTO findUserByEmail(String email);
}
