package ru.ddc.sbs.exceptions;

import lombok.Getter;

@Getter
public class PersistException extends Exception {
    private final String message;

    public PersistException(String message) {
        super(message);
        this.message = message;
    }
}
