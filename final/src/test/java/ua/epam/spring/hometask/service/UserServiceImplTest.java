package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;
import ua.epam.spring.hometask.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;


    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Test
    public void getUserByEmail_ReturnsOptionalUser_WhenUserWithSuchEmailExists() throws Exception {
        when(userRepository.findFirstByEmail(anyString())).thenReturn(user);

        Optional<User> actual = userService.getUserByEmail("danilchican@mail.ru");
        assertTrue(actual.isPresent());
    }

    @Test
    public void getUserByEmail_ReturnsEmpty_WhenUserWithSuchEmailDoesntExist() throws Exception {
        when(userRepository.findFirstByEmail(anyString())).thenReturn(null);
        Optional<User> actual = userService.getUserByEmail("danilchican@mail.ru");

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByEmail_InvalidEmail_ExceptionThrown() throws Exception {
        userService.getUserByEmail(null);
    }

    @Test
    public void save_ReturnsSavedOrUpdatedUser_WhenUserPassed() throws Exception {
        when(userRepository.save(user)).thenReturn(user);

        User actual = userService.save(user);
        assertNotNull(actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void save_InvalidUser_ExceptionThrown() throws Exception {
        userService.save(null);
    }

    @Test
    public void remove_ReturnsNothing_WhenUserPassed() throws Exception {
        userService.remove(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void getById_ReturnsOptionalUser_WhenUserWithSuchIdExists() throws Exception {
        when(userRepository.findOne(anyLong())).thenReturn(user);

        Optional<User> actual = userService.findById(1L);
        assertTrue(actual.isPresent());
    }

    @Test
    public void getById_ReturnsEmpty_WhenUserWithSuchIdDoesntExist() throws Exception {
        when(userRepository.findOne(anyLong())).thenReturn(null);
        Optional<User> actual = userService.findById(1L);

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById_InvalidId_ExceptionThrown() throws Exception {
        userService.findById(null);
    }

    @Test
    public void getAll_ReturnsCollectionOfUsers_WhenCollectionIsNotNull() throws Exception {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        Collection<User> collection = userService.getAll();
        assertThat(collection, is(notNullValue()));
    }
}