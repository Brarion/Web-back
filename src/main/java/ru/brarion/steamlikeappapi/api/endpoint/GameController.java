package ru.brarion.steamlikeappapi.api.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.brarion.steamlikeappapi.api.ApiRequestMapping;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.PreviewResponse;
import ru.brarion.steamlikeappapi.business.service.GameService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRequestMapping.GAMES)
public class GameController {

    private final GameService gameService;

    @GetMapping
    List<GameResponse> getAllGames(
            @RequestParam(required = false) String gameNameStart,
            @RequestParam(required = false) String developerNameStart,
            @RequestParam(required = false) String publisherNameStart
    ) {
        return gameService.findAllByFilters(gameNameStart, developerNameStart, publisherNameStart);
    }

    @GetMapping("/{id}")
    GameResponse getGameById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @GetMapping("/{id}/preview")
    ResponseEntity<Resource> getGamePreview(@PathVariable Long id) {
        PreviewResponse previewResponse = gameService.getPreviewImage(id);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(31536000, TimeUnit.SECONDS).cachePublic())
                .contentLength(previewResponse.getSize())
                .contentType(previewResponse.getMediaType())
                .body(previewResponse.getResource());
    }
}
