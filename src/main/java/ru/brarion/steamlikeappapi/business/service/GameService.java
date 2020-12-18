package ru.brarion.steamlikeappapi.business.service;

import ru.brarion.steamlikeappapi.business.dto.GameResponse;
import ru.brarion.steamlikeappapi.business.dto.PreviewResponse;

import java.util.List;

public interface GameService {

    List<GameResponse> findAllByFilters(String gameNameStart, String developerNameStart, String publisherNameStart);

    GameResponse findById(Long id);

    PreviewResponse getPreviewImage(Long id);
}
