package ua.epam.spring.hometask.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.converter.Converter;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.dto.AuditoriumDTO;
import ua.epam.spring.hometask.facade.AuditoriumFacade;
import ua.epam.spring.hometask.service.AuditoriumService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuditoriumFacadeImpl implements AuditoriumFacade {

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private Converter<Auditorium, AuditoriumDTO> auditoriumConverter;

    @Override
    public List<AuditoriumDTO> findAll() {
        return auditoriumService.findAll()
                .stream()
                .map(auditoriumConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuditoriumDTO> findAuditoriumByName(String name) {
        return auditoriumService
                .findByName(name)
                .map(auditoriumConverter::convertToDTO);
    }
}
