package ru.brarion.steamlikeappapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.brarion.steamlikeappapi.business.exception.DownloadImageException;
import ru.brarion.steamlikeappapi.business.exception.NotEnoughCacheException;
import ru.brarion.steamlikeappapi.business.exception.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Value
    private static class ErrorResponse {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime timestamp;

        HttpStatus httpStatus;

        int status;

        String error;

        String message;

        String path;

        ErrorResponse(HttpStatus httpStatus, String message, String path) {
            this.timestamp = LocalDateTime.now();
            this.httpStatus = httpStatus;
            this.status = httpStatus.value();
            this.error = httpStatus.getReasonPhrase();
            this.message = message;
            this.path = path;
        }

        HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }

    private RestApiExceptionHandler() {
        super();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
        log.info(
                "Handling resource not found exception at [{}] path. {}",
                errorResponse.getPath(),
                errorResponse.getMessage()
        );
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ErrorResponse> handleCredentialsException(
            AuthenticationException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI());
        log.info(
                "Handling authentication exception at [{}] path. {}",
                errorResponse.getPath(),
                errorResponse.getMessage()
        );
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(NotEnoughCacheException.class)
    ResponseEntity<ErrorResponse> handleNotEnoughCacheException(
            NotEnoughCacheException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        log.info(
                "Handling not enough cache exception at [{}] path. {}",
                errorResponse.getPath(),
                errorResponse.getMessage()
        );
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(DownloadImageException.class)
    ResponseEntity<ErrorResponse> handleDownloadImageException(
            DownloadImageException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
        log.info(
                "Handling download image exception at [{}] path. {}",
                errorResponse.getPath(),
                errorResponse.getMessage()
        );
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
