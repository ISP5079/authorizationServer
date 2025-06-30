package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class UserRoleClientNotFoundException extends AuthorizationServerException {

    private static final String NOT_FOUND_USER_ROLE_CLIENT = "User role client not found";

    public UserRoleClientNotFoundException() {
        super(HttpStatus.NOT_FOUND, NOT_FOUND_USER_ROLE_CLIENT);
    }
}
