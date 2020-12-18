package ru.brarion.steamlikeappapi.business.dto.mapper;

import org.mapstruct.Mapper;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.entity.Game;

@Mapper
public interface GameMapper extends GenericMapper<Game, GameResponse> {
}
