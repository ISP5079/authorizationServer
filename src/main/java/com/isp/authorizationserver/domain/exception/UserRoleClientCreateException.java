package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class UserRoleClientCreateException extends AuthorizationServerException {
    private static final String ERROR_CREATING_USER_ROLE_CLIENT = "Error creating user role client";

    public UserRoleClientCreateException(Throwable cause) {
        super(HttpStatus.CONFLICT, ERROR_CREATING_USER_ROLE_CLIENT, cause);
    }
}
