package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class UserNotAvailableException extends AuthorizationServerException {
    public static final String USER_NOT_AVAILABLE = "User not available";

    public UserNotAvailableException() {
        super(HttpStatus.CONFLICT, USER_NOT_AVAILABLE);
    }
}
