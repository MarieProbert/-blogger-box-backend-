package com.dauphine.blogger.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler({
            CategoryIdNotFound.class,
            PostIdNotFound.class,
            CategoryNameNotFound.class,
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        logger.warn("[NOT FOUND] {}", ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler({
            CategoryNameAlreadyExists.class
    })
    public ResponseEntity<String> handleConflictException(Exception ex) {
        logger.warn("[CONFLICT] {}", ex.getMessage());
        return ResponseEntity
                .status(409)
                .body(ex.getMessage());
    }
}