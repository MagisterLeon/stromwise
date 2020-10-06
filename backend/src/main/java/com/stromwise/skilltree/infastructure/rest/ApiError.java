package com.stromwise.skilltree.infastructure.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum ApiError {
    //Security errors
    INTERNAL_ERROR("01001", "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR("01002", "Bad request", BAD_REQUEST),
    ;

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;

    private String details;

    public ApiErrorResponse toApiErrorResponse() {
        return ApiErrorResponse.builder()
                .code(code)
                .message(message)
                .details(details)
                .build();
    }

    public ApiError withDetails(String details) {
        this.details = details;
        return this;
    }

}
