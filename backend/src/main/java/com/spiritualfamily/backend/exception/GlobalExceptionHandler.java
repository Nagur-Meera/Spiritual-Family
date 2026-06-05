package com.spiritualfamily.backend.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(
            Exception ex
    ) {

        return ResponseEntity
                .badRequest()
                .body(
                        Map.of(
                                "success", false,
                                "message", ex.getMessage()
                        )
                );
    }
}