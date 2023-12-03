package com.plannercf.backend.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Record Not Found")
public class RecordNotExistsException extends Exception{
    public RecordNotExistsException(String message) {
        super(message);
    }

    @Nonnull
    public RecordNotExistsException() {
    }
}
