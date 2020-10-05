package com.stromwise.skilltree.infastructure.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ApiErrorResponse {

    private final String code;

    private final String message;

    private final String details;
}
