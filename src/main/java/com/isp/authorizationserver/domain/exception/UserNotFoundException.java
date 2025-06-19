package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AuthorizationServerException {
    public static final String USER_NOT_FOUND = "User not found";

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
    }
}
