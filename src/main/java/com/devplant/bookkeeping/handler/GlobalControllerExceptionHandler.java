package com.devplant.bookkeeping.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleInvalidJson(final JsonProcessingException exception,
                                                             final HttpServletRequest request) {

        return createResponse("Cannot process the request body. Please verify that it is a valid json.", request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleInvalidField(final IllegalArgumentException exception,
                                                              final HttpServletRequest request) {

        return createResponse(exception.getMessage(), request);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody ExceptionResponse handleInvalidRequest(final IllegalStateException exception,
                                                                final HttpServletRequest request) {

        return createResponse(exception.getMessage(), request);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleNoEntity(final NoSuchElementException exception,
                                                          final HttpServletRequest request) {

        return createResponse(exception.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
                                                           final HttpServletRequest request) {

        return createResponse(exception.getMessage(), request);
    }

    private ExceptionResponse createResponse(final String message,
                                             final HttpServletRequest request) {
        return ExceptionResponse.builder()
                .errorMessage(message)
                .requestedURI(request.getRequestURI())
                .build();
    }
}