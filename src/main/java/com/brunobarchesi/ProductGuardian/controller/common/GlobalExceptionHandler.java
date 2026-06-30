package com.brunobarchesi.ProductGuardian.controller.common;

import com.brunobarchesi.ProductGuardian.dto.ApiError;
import com.brunobarchesi.ProductGuardian.dto.ValidationFieldError;
import com.brunobarchesi.ProductGuardian.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrorList = exception.getFieldErrors();
        List<ValidationFieldError> validationFieldErrors = fieldErrorList.stream().map(fieldError ->
                new ValidationFieldError(fieldError.getField(), fieldError.getDefaultMessage())).toList();

        return new ApiError(HttpStatus.UNPROCESSABLE_CONTENT.value(), "Validation Error", validationFieldErrors);
    }


    @ExceptionHandler(ForbiddenOperationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenException (ForbiddenOperationException exception){
        return new ApiError(HttpStatus.FORBIDDEN.value(), exception.getMessage(), List.of());
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException (BadRequestException exception){
        return new ApiError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), List.of());
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), List.of());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(ConflictException exception){
        return new ApiError(HttpStatus.CONFLICT.value(), exception.getMessage(), List.of());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleUnexpectedException(Exception exception) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred.",
                List.of()
        );
    }


}