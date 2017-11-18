package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.dao.AuditoriumDAO;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.impl.AuditoriumServiceImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuditoriumServiceImplTest {

    @Mock
    private AuditoriumDAO auditoriumDAO;

    @Mock
    private Auditorium auditorium;

    @Mock
    private Set<Auditorium> auditoriums;

    @InjectMocks
    private final AuditoriumService auditoriumService = new AuditoriumServiceImpl();

    @Test
    public void getByName_ReturnsOptionalAuditorium_WhenAuditoriumWithSuchNameExists() throws Exception {
        when(auditoriumDAO.getByName(anyString())).thenReturn(Optional.of(auditorium));

        Optional<Auditorium> actual = auditoriumService.getByName("auditorium name");
        assertTrue(actual.isPresent());
    }

    @Test
    public void getByName_ReturnsEmpty_WhenAuditoriumWithSuchNameDoesntExist() throws Exception {
        when(auditoriumDAO.getByName(anyString())).thenReturn(Optional.empty());
        Optional<Auditorium> actual = auditoriumService.getByName("auditorium name");

        assertFalse(actual.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByName_InvalidName_ExceptionThrown() throws Exception {
        auditoriumService.getByName(null);
    }

    @Test
    public void getAll_ReturnsSetOfAuditoriums_WhenSetIsNotNull() throws Exception {
        when(auditoriumDAO.getAll()).thenReturn(auditoriums);

        Collection<Auditorium> collection = auditoriumService.getAll();
        assertThat(collection, is(notNullValue()));
    }
}