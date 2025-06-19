package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class RoleCreateException extends AuthorizationServerException {

    public static final String ERROR_ROLE_ALREADY_EXISTS = "Error role already exists";
    public static final String ERROR_CREATING_ROLE = "Error creating role";

    public RoleCreateException(String message, Throwable cause) {
        super(HttpStatus.CONFLICT, message, cause);
    }
}
