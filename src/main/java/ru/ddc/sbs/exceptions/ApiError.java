package ru.ddc.sbs.exceptions;

import lombok.Getter;

@Getter
public class ApiError extends Exception {
    private final String message;

    public ApiError(String message) {
        super(message);
        this.message = message;
    }
}
