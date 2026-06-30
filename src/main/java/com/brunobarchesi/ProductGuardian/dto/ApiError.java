package com.brunobarchesi.ProductGuardian.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiError (int status, String message, List<ValidationFieldError> fieldErrorList){

    public static ApiError StandardResponse (String message){
        return new  ApiError(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static  ApiError conflict(String message){
        return new  ApiError(HttpStatus.CONFLICT.value(), message, List.of());
    }



}
