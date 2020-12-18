package ru.brarion.steamlikeappapi.api.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.brarion.steamlikeappapi.api.ApiRequestMapping;
import ru.brarion.steamlikeappapi.business.dto.DeveloperResponse;
import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.service.DeveloperService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRequestMapping.DEVELOPERS)
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    List<DeveloperResponse> getAllDevelopers() {
        return developerService.findAll();
    }

    @GetMapping("/{id}")
    DeveloperResponse getDeveloperById(@PathVariable Long id) {
        return developerService.findById(id);
    }

    @GetMapping("/{developerId}/games")
    List<GameResponse> getDeveloperGames(@PathVariable Long developerId) {
        return developerService.findDeveloperGames(developerId);
    }
}
