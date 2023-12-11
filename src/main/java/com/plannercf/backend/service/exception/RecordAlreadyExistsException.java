package com.plannercf.backend.service.exception;

public class RecordAlreadyExistsException extends Exception{
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
    public RecordAlreadyExistsException() {

    }
}
