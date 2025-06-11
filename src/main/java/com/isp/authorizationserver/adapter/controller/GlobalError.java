package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.out.ClientRp;
import com.isp.authorizationserver.domain.exception.CreateClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalError {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalError.class);

    @ExceptionHandler(CreateClientException.class)
    public ResponseEntity<ClientRp> createClientError(CreateClientException ex) {
        LOG.error("Error: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ClientRp(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }
}
