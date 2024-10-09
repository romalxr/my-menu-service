package ru.javaops.cloudjava.mymenuservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MenuServiceException extends RuntimeException {

    private final HttpStatus status;

    public MenuServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
