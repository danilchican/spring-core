package ua.epam.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.dto.UserDTO;
import ua.epam.spring.hometask.facade.UserFacade;
import ua.epam.spring.hometask.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<User, UserDTO> userConverter;

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        return userService
                .findUserByEmail(email)
                .map(userConverter::convertToDTO);
    }

    @Override
    public List<UserDTO> findAll() {
        return userService.findAll()
                .stream()
                .map(userConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO user) {
        User userEntity = userConverter.convertToEntity(user);
        return userConverter.convertToDTO(userService.save(userEntity));
    }
}
