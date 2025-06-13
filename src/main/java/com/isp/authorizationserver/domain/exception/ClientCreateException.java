package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class ClientCreateException extends AuthorizationServerException {
    public static final String ERROR_MESSAGE = "Error creating client";

    public ClientCreateException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, ERROR_MESSAGE, cause);
    }
}
