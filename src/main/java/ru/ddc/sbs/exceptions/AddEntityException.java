package ru.ddc.sbs.exceptions;

import lombok.Getter;

@Getter
public class AddEntityException extends Exception {
    private final String message;

    public AddEntityException(String message) {
        super(message);
        this.message = message;
    }
}
