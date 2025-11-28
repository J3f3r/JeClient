package com.jeferson.jeclient.controllers.handlers;

import com.jeferson.jeclient.dto.CustomError;
import com.jeferson.jeclient.dto.ValidationError;
import com.jeferson.jeclient.service.exceptions.DatabaseException;
import com.jeferson.jeclient.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> argumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(),status.value(), "Dados inválidos", request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()){// esses gets retornam lista com as exceções geradas conforme as anotations em CustomError
            err.addError(f.getField(), f.getDefaultMessage());// percorre-se o objeto que é o nome erro de validação, passa ela (e) por argumento faz ela adicionar as mensagens customizadas e mostra-las no Postman pelo JSON
        }// e para cada erro f, roda o comando para pegar o campo e a mensagem que está np FieldMessage
        return ResponseEntity.status(status).body(err);
    }
}
