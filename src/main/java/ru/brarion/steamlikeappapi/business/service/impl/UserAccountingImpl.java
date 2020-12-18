package ru.brarion.steamlikeappapi.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.brarion.steamlikeappapi.business.dto.AccountCacheResponse;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.TopUpAccountCacheRequest;
import ru.brarion.steamlikeappapi.business.dto.mapper.GameMapper;
import ru.brarion.steamlikeappapi.business.entity.Game;
import ru.brarion.steamlikeappapi.business.entity.User;
import ru.brarion.steamlikeappapi.business.exception.NotEnoughCacheException;
import ru.brarion.steamlikeappapi.business.exception.ResourceNotFoundException;
import ru.brarion.steamlikeappapi.business.repository.GameRepository;
import ru.brarion.steamlikeappapi.business.service.LoadUserService;
import ru.brarion.steamlikeappapi.business.service.UserAccountingService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountingImpl implements UserAccountingService {

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    private final LoadUserService loadUserService;

    @Override
    @Transactional
    public AccountCacheResponse buyGame(Principal principal, Long gameId) {
        User user = loadUserService.loadUserByUsername(principal.getName());
        Game gameToBuy = getGame(gameId);
        requiresEnoughCacheToBuy(user, gameToBuy);
        user.setCache(user.getCache() - gameToBuy.getPrice());
        user.getBoughtGames().add(gameToBuy);
        return new AccountCacheResponse(user.getCache());
    }

    @Override
    public List<GameResponse> getBoughtGames(Principal principal) {
        User user = loadUserService.loadUserByUsername(principal.getName());
        return user.getBoughtGames().stream()
                .map(gameMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountCacheResponse getAccountCache(Principal principal) {
        User user = loadUserService.loadUserByUsername(principal.getName());
        return new AccountCacheResponse(user.getCache());
    }

    @Override
    @Transactional
    public void topUpAccountCache(Principal principal, TopUpAccountCacheRequest cacheRequest) {
        User user = loadUserService.loadUserByUsername(principal.getName());
        user.setCache(user.getCache() + cacheRequest.getSum());
    }

    private void requiresEnoughCacheToBuy(User user, Game gameToBuy) {
        if (user.getCache() < gameToBuy.getPrice()) {
            throw new NotEnoughCacheException(
                    String.format(
                            "Not enough cache. Account cache is %d, Game price is %d",
                            user.getCache(),
                            gameToBuy.getPrice()
                    )
            );
        }
    }

    private Game getGame(Long id) {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No game with id: " + id));
    }
}
