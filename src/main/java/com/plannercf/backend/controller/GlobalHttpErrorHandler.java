package com.plannercf.backend.controller;

import com.plannercf.backend.service.exception.RecordAlreadyExistsException;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<Object> handleRecordAlreadyExistsException(RecordAlreadyExistsException exception) {
        return new ResponseEntity<>("Record already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotExistsException.class)
    public ResponseEntity<Object> handleRecordNotExistsException(RecordNotExistsException exception) {
        return new ResponseEntity<>("Record not exists", HttpStatus.BAD_REQUEST);
    }

}
