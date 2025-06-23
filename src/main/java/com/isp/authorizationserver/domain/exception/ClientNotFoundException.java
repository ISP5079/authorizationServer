package com.isp.authorizationserver.domain.exception;

import org.springframework.http.HttpStatus;

public class ClientNotFoundException extends AuthorizationServerException {

    private static final String NOT_FOUND_CLIENT = "Client not found";

    public ClientNotFoundException() {
        super(HttpStatus.NOT_FOUND, NOT_FOUND_CLIENT);
    }
}
