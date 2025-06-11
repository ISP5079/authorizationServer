package com.isp.authorizationserver.domain.exception;

public class CreateClientException extends RuntimeException {
    public CreateClientException(Throwable cause) {
        super("Error creating client", cause);
    }
}
