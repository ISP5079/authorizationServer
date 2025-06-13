package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class UserCreateException extends AuthorizationServerException {
    public static final String ERROR_MESSAGE = "Error creating user";

    public UserCreateException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, ERROR_MESSAGE, cause);
    }
}
