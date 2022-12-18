package ru.ddc.sbs.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ddc.sbs.apiresponse.ApiResponse;
import ru.ddc.sbs.apiresponse.responsebuilder.ApiResponseBuilder;
import ru.ddc.sbs.exceptions.ApiError;
import ru.ddc.sbs.exceptions.PersistError;

import javax.persistence.EntityNotFoundException;

import static ru.ddc.sbs.apiresponse.ApiResponseCode.*;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final ApiResponseBuilder apiResponseBuilder;

    public ApiExceptionHandler(ApiResponseBuilder apiResponseBuilder) {
        this.apiResponseBuilder = apiResponseBuilder;
    }

    @ExceptionHandler(ApiError.class)
    protected ResponseEntity<Object> handleApiException(ApiError ex) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(API_ERROR, ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(PersistError.class)
    protected ResponseEntity<Object> handlePersistException(PersistError ex) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(PERSIST_ERROR, ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException (EntityNotFoundException ex) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(NOT_FOUND, ex.getMessage());
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(GENERAL_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, status);
    }
}
