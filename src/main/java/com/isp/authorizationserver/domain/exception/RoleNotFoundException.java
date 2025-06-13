package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends AuthorizationServerException {

    private static final String ERROR_MESSAGE = "Role not found";

    public RoleNotFoundException() {
        super(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
    }
}
