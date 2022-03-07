package com.example.src.impl.handler;

import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Generated
@RestControllerAdvice
public class GenericHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handlerInternalServerException(Exception exception) {
        return new ExceptionResponse.ExceptionResponseBuilder()
            .name("InternalErrorException")
            .cause(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handlerApiExceptionException(ApiException exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .name("ApiException")
            .cause(exception.getMessage())
            .httpStatus(exception.getHttpStatus())
            .timestamp(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(exceptionResponse, exception.getHttpStatus());
    }
}