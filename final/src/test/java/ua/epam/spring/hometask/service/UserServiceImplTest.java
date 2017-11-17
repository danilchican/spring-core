package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.UserDAO;
import ua.epam.spring.hometask.domain.User;

import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserByEmailSuccessScenarioTest() throws Exception {
        User expected = new User();
        when(userDAO.getUserByEmail(anyString())).thenReturn(Optional.of(expected));

        User actual = userService.getUserByEmail("danilchican@mail.ru");
        assertEquals(expected, actual);
    }

    @Test
    public void getUserByEmailFirstFailureScenarioTest() throws Exception {
        when(userDAO.getUserByEmail(anyString())).thenReturn(Optional.empty());
        User actual = userService.getUserByEmail("danilchican@mail.ru");

        assertThat(actual, is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByEmailSecondFailureScenarioTest() throws Exception {
        userService.getUserByEmail(null);
    }
}