package ru.brarion.steamlikeappapi.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.PreviewResponse;
import ru.brarion.steamlikeappapi.business.dto.mapper.GameMapper;
import ru.brarion.steamlikeappapi.business.entity.Game;
import ru.brarion.steamlikeappapi.business.exception.ResourceNotFoundException;
import ru.brarion.steamlikeappapi.business.helper.ImageDownloader;
import ru.brarion.steamlikeappapi.business.repository.GameRepository;
import ru.brarion.steamlikeappapi.business.service.GameService;
import ru.brarion.steamlikeappapi.config.MultipartConfig;

import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    private final ImageDownloader imageDownloader;

    private final MultipartConfig multipartConfig;

    @Override
    public List<GameResponse> findAllByFilters(String gameNameStart, String developerNameStart, String publisherNameStart) {
        if (StringUtils.isEmpty(gameNameStart) && StringUtils.isEmpty(developerNameStart)
                && StringUtils.isEmpty(publisherNameStart)) {
            return findAll();
        }
        Set<GameResponse> foundGamesByName = findOrNot(gameNameStart, this::findAllByNameStartsWith);
        Set<GameResponse> foundGamesByDeveloperName = findOrNot(developerNameStart, this::findAllByDeveloperNameStartsWith);
        Set<GameResponse> foundGamesByPublisherName = findOrNot(publisherNameStart, this::findAllByPublisherNameStartsWith);
        foundGamesByName.retainAll(foundGamesByDeveloperName);
        foundGamesByName.retainAll(foundGamesByPublisherName);
        return new ArrayList<>(foundGamesByName);
    }

    @Override
    public GameResponse findById(Long id) {
        Game game = getGame(id);
        return gameMapper.mapToResponse(game);
    }

    @Override
    public PreviewResponse getPreviewImage(Long id) {
        Game game = getGame(id);
        String uri = game.getPreviewImageUri();
        if (uri == null) {
            throw new ResourceNotFoundException(String.format("No image preview for game with id: %d", id));
        }
        return imageDownloader.getImage(multipartConfig.getLocation() + uri);
    }

    private List<GameResponse> findAll() {
        List<Game> games = gameRepository.findAll();
        return gameMapper.mapToResponses(games);
    }

    private Set<GameResponse> findOrNot(String nameStart, Function<String, Set<GameResponse>> findFunction) {
        return Optional.ofNullable(nameStart)
                .map(findFunction)
                .orElse(Set.of());
    }

    private Set<GameResponse> findAllByNameStartsWith(String nameStart) {
        List<Game> foundGames = gameRepository.findAllByNameStartsWith(nameStart);
        return new HashSet<>(gameMapper.mapToResponses(foundGames));
    }

    private Set<GameResponse> findAllByDeveloperNameStartsWith(String developerNameStart) {
        List<Game> foundGames = gameRepository.findAllByDeveloperNameStartsWith(developerNameStart);
        return new HashSet<>(gameMapper.mapToResponses(foundGames));
    }

    private Set<GameResponse> findAllByPublisherNameStartsWith(String publisherNameStart) {
        List<Game> foundGames = gameRepository.findAllByPublisherNameStartsWith(publisherNameStart);
        return new HashSet<>(gameMapper.mapToResponses(foundGames));
    }

    private Game getGame(Long id) {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No game with id: " + id));
    }
}
