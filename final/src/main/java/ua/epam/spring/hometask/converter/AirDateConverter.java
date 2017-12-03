package ua.epam.spring.hometask.converter;

import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.dto.AirDateDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface AirDateConverter extends Converter<AirDate, AirDateDTO> {

    default Set<AirDateDTO> convertToDTOSet(final Collection<AirDate> entities) {
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    default Set<AirDate> convertToEntitySet(final Collection<AirDateDTO> dtos) {
        return dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet());
    }
}
