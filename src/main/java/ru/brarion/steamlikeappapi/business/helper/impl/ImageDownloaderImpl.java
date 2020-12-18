package ru.brarion.steamlikeappapi.business.helper.impl;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.brarion.steamlikeappapi.business.dto.PreviewResponse;
import ru.brarion.steamlikeappapi.business.exception.DownloadImageException;
import ru.brarion.steamlikeappapi.business.helper.ImageDownloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
class ImageDownloaderImpl implements ImageDownloader {

    @Override
    public PreviewResponse getImage(String imagePath) {
        File file = new File(imagePath);

        Path path = Paths.get(file.getPath());
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            String contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                throw new DownloadImageException(String.format("Cannot download image with URI: %s", imagePath));
            }
            return new PreviewResponse(file.length(), MediaType.parseMediaType(contentType), resource);
        } catch (IOException ex) {
            throw new DownloadImageException(String.format("Cannot parse image with URI: %s", imagePath));
        }
    }
}
