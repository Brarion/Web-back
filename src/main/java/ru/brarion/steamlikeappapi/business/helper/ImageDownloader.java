package ru.brarion.steamlikeappapi.business.helper;

import ru.brarion.steamlikeappapi.business.dto.PreviewResponse;

public interface ImageDownloader {

    PreviewResponse getImage(String path);
}
