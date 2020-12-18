package ru.brarion.steamlikeappapi.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.PublisherResponse;
import ru.brarion.steamlikeappapi.business.dto.mapper.GameMapper;
import ru.brarion.steamlikeappapi.business.dto.mapper.PublisherMapper;
import ru.brarion.steamlikeappapi.business.entity.Game;
import ru.brarion.steamlikeappapi.business.entity.Publisher;
import ru.brarion.steamlikeappapi.business.repository.GameRepository;
import ru.brarion.steamlikeappapi.business.repository.PublisherRepository;
import ru.brarion.steamlikeappapi.business.service.PublisherService;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    private final GameRepository gameRepository;

    private final PublisherMapper publisherMapper;

    private final GameMapper gameMapper;

    @Override
    public List<PublisherResponse> findAll() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publisherMapper.mapToResponses(publishers);
    }

    @Override
    public PublisherResponse findById(Long id) {
        Publisher publisher = getPublisher(id);
        return publisherMapper.mapToResponse(publisher);
    }

    @Override
    public List<GameResponse> findPublisherGames(Long id) {
        List<Game> games = gameRepository.findAllByPublisherId(id);
        return gameMapper.mapToResponses(games);
    }

    private Publisher getPublisher(Long id) {
        return publisherRepository
                .findById(id)
                .orElseThrow(() -> new ResolutionException("No publisher with id: " + id));
    }
}
