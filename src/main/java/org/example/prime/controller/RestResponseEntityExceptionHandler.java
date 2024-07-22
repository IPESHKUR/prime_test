package org.example.prime.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.prime.exception.LotWasReservedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> notFound(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(LotWasReservedException.class)
    protected ResponseEntity<Object> notFound(LotWasReservedException ex) {
        return ResponseEntity
                .status(HttpStatus.LOCKED)
                .body(ex.getMessage());
    }
}
