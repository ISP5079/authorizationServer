package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class CreateClientException extends AuthorizationServerException {
    public static final String ERROR_MESSAGE = "Error creating client";

    public CreateClientException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, ERROR_MESSAGE, cause);
    }
}
