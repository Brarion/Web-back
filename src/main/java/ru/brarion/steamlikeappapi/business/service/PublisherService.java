package ru.brarion.steamlikeappapi.business.service;

import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.PublisherResponse;

import java.util.List;

public interface PublisherService {

    List<PublisherResponse> findAll();

    PublisherResponse findById(Long id);

    List<GameResponse> findPublisherGames(Long id);
}
