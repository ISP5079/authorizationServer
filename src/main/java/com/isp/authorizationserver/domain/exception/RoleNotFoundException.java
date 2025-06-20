package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends AuthorizationServerException {

    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String ROLES_NOT_FOUND = "Roles not found";

    public RoleNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
