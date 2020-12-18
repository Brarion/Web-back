package ru.brarion.steamlikeappapi.business.dto.mapper;

import org.mapstruct.Mapper;
import ru.brarion.steamlikeappapi.business.dto.PublisherResponse;
import ru.brarion.steamlikeappapi.business.entity.Publisher;

@Mapper
public interface PublisherMapper extends GenericMapper<Publisher, PublisherResponse> {
}
