package ru.brarion.steamlikeappapi.business.dto.mapper;

import java.util.List;

public interface GenericMapper<E, RP> {

    RP mapToResponse(E entity);

    List<RP> mapToResponses(List<E> entities);
}
