package ru.brarion.steamlikeappapi.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brarion.steamlikeappapi.business.dto.DeveloperResponse;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.mapper.DeveloperMapper;
import ru.brarion.steamlikeappapi.business.dto.mapper.GameMapper;
import ru.brarion.steamlikeappapi.business.entity.Developer;
import ru.brarion.steamlikeappapi.business.entity.Game;
import ru.brarion.steamlikeappapi.business.exception.ResourceNotFoundException;
import ru.brarion.steamlikeappapi.business.repository.DeveloperRepository;
import ru.brarion.steamlikeappapi.business.repository.GameRepository;
import ru.brarion.steamlikeappapi.business.service.DeveloperService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    private final GameRepository gameRepository;

    private final DeveloperMapper developerMapper;

    private final GameMapper gameMapper;

    @Override
    public List<DeveloperResponse> findAll() {
        List<Developer> developers = developerRepository.findAll();
        return developerMapper.mapToResponses(developers);
    }

    @Override
    public DeveloperResponse findById(Long id) {
        Developer developer = getDeveloper(id);
        return developerMapper.mapToResponse(developer);
    }

    @Override
    public List<GameResponse> findDeveloperGames(Long id) {
        List<Game> games = gameRepository.findAllByDeveloperId(id);
        return gameMapper.mapToResponses(games);
    }

    private Developer getDeveloper(Long id) {
        return developerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No developer with id: " + id));
    }
}
