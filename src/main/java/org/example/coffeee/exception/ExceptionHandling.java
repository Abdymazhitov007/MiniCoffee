package org.example.coffeee.exception;

import org.example.coffeee.exception.exceptions.EmptyListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<?> exceptionHandle(EmptyListException e) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Empty list");
    }


}
