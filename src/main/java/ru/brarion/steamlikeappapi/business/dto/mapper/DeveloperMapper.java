package ru.brarion.steamlikeappapi.business.dto.mapper;

import org.mapstruct.Mapper;
import ru.brarion.steamlikeappapi.business.dto.DeveloperResponse;
import ru.brarion.steamlikeappapi.business.entity.Developer;

@Mapper
public interface DeveloperMapper extends GenericMapper<Developer, DeveloperResponse> {
}
