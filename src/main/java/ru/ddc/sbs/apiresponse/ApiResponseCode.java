package ru.ddc.sbs.apiresponse;

import lombok.Getter;

@Getter
public enum ApiResponseCode {
    PERSIST_ERROR(0),
    API_ERROR(1),
    NOT_FOUND(2),
    ARGUMENT_NOT_VALID(3),
    GENERAL_ERROR (-1);

    private final int code;

    ApiResponseCode(int code) {
        this.code = code;
    }
}
