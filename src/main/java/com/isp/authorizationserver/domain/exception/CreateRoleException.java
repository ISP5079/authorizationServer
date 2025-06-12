package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class CreateRoleException extends AuthorizationServerException {

    public static final String ERROR_MESSAGE = "Error creating role";

    public CreateRoleException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, ERROR_MESSAGE, cause);
    }
}
