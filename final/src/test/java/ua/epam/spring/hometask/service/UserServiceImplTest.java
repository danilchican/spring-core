package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.dao.UserDAO;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private User user;

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Test
    public void getUserByEmail_ReturnsOptionalUser_WhenUserWithSuchEmailExists() throws Exception {
        when(userDAO.getUserByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> actual = userService.getUserByEmail("danilchican@mail.ru");
        assertTrue(actual.isPresent());
    }

    @Test
    public void getUserByEmail_ReturnsEmpty_WhenUserWithSuchEmailDoesntExist() throws Exception {
        when(userDAO.getUserByEmail(anyString())).thenReturn(Optional.empty());
        Optional<User> actual = userService.getUserByEmail("danilchican@mail.ru");

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByEmail_InvalidEmail_ExceptionThrown() throws Exception {
        userService.getUserByEmail(null);
    }
}