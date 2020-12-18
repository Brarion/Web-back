package ru.brarion.steamlikeappapi.api.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.brarion.steamlikeappapi.api.ApiRequestMapping;
import ru.brarion.steamlikeappapi.business.dto.AccountCacheResponse;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.TopUpAccountCacheRequest;
import ru.brarion.steamlikeappapi.business.service.UserAccountingService;

import java.security.Principal;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRequestMapping.USER_ACCOUNTING)
public class UserAccountingController {

    private final UserAccountingService userAccountingService;

    @GetMapping("/bought-games")
    List<GameResponse> getBoughtGames(Principal principal) {
        return userAccountingService.getBoughtGames(principal);
    }

    @PutMapping("/bought-games/{gameId}")
    AccountCacheResponse buyGame(Principal principal, @PathVariable Long gameId) {
        return userAccountingService.buyGame(principal, gameId);
    }

    @GetMapping("/cache")
    AccountCacheResponse getAccountCache(Principal principal) {
        return userAccountingService.getAccountCache(principal);
    }

    @PostMapping("/cache")
    void topUpAccountCache(Principal principal, @RequestBody TopUpAccountCacheRequest cacheRequest) {
        userAccountingService.topUpAccountCache(principal, cacheRequest);
    }
}
