package ru.brarion.steamlikeappapi.api.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.brarion.steamlikeappapi.api.ApiRequestMapping;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.PublisherResponse;
import ru.brarion.steamlikeappapi.business.service.PublisherService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRequestMapping.PUBLISHERS)
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    List<PublisherResponse> getAllPublishers() {
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    PublisherResponse getPublisherById(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @GetMapping("/{publisherId}/games")
    List<GameResponse> getPublisherGames(@PathVariable Long publisherId) {
        return publisherService.findPublisherGames(publisherId);
    }
}
