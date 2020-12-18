package ru.brarion.steamlikeappapi.business.service;

import ru.brarion.steamlikeappapi.business.dto.DeveloperResponse;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;

import java.util.List;

public interface DeveloperService {

    List<DeveloperResponse> findAll();

    DeveloperResponse findById(Long id);

    List<GameResponse> findDeveloperGames(Long id);
}
