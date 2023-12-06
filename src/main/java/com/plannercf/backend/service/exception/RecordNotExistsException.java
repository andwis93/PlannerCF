package com.plannercf.backend.service.exception;

public class RecordNotExistsException extends Exception{
    public RecordNotExistsException(String message) {
        super(message);
    }

    public RecordNotExistsException() {
    }
}
