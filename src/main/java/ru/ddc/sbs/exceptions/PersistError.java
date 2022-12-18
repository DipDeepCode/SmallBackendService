package ru.ddc.sbs.exceptions;

import lombok.Getter;

@Getter
public class PersistError extends Exception {
    private final String message;

    public PersistError(String message) {
        super(message);
        this.message = message;
    }
}
