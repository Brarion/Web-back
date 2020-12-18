package ru.brarion.steamlikeappapi.business.exception;

public class NotEnoughCacheException extends RuntimeException {

    public NotEnoughCacheException(String message) {
        super(message);
    }
}
