package ru.brarion.steamlikeappapi.business.service;

import ru.brarion.steamlikeappapi.business.dto.AccountCacheResponse;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.TopUpAccountCacheRequest;

import java.security.Principal;
import java.util.List;

public interface UserAccountingService {

    AccountCacheResponse buyGame(Principal principal, Long gameId);

    List<GameResponse> getBoughtGames(Principal principal);

    AccountCacheResponse getAccountCache(Principal principal);

    void topUpAccountCache(Principal principal, TopUpAccountCacheRequest cacheRequest);
}
