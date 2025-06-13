package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class RoleCreateException extends AuthorizationServerException {

    public static final String ERROR_MESSAGE = "Error creating role";

    public RoleCreateException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, ERROR_MESSAGE, cause);
    }
}
