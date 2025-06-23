package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class RoleClientAllowedCreateException extends AuthorizationServerException {

    public RoleClientAllowedCreateException() {
        super(HttpStatus.CONFLICT, "message");
    }
}
