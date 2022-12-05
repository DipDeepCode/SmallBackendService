package ru.ddc.sbs.exceptions;

import lombok.Getter;

@Getter
public class UpdateEntityException extends Exception {
    private final String message;

    public UpdateEntityException(String message) {
        super(message);
        this.message = message;
    }
}
