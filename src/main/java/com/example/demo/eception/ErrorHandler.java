package com.example.demo.eception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void playerNotFoundHandler() {
    }

    @ExceptionHandler({ConstraintViolationException.class, PlayerBadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void commonBadRequestHandler() {
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<Violation>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<Violation> violations = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new Violation(err.getField(), err.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(violations, HttpStatus.BAD_REQUEST);
    }
}
