package ru.brarion.steamlikeappapi.business.exception;

public class DownloadImageException extends RuntimeException {

    public DownloadImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadImageException(String message) {
        super(message);
    }
}
