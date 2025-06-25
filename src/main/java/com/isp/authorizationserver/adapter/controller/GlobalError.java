package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.out.ErrorRp;
import com.isp.authorizationserver.adapter.dto.out.GlobalRp;
import com.isp.authorizationserver.domain.exception.AuthorizationServerException;
import com.isp.authorizationserver.shared.constants.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalError {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalRp> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(Messages.BASE_ERROR, ex.getClass().getSimpleName(), ex.getMessage(), ex);

        Map<String, String> errors = new LinkedHashMap<>();

        errors.putAll(ex.getBindingResult()
                .getFieldErrors().stream()
                .filter(fieldError -> fieldError.getDefaultMessage() != null)
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));

        errors.putAll(ex.getBindingResult()
                .getGlobalErrors().stream()
                .filter(globalError -> globalError.getDefaultMessage() != null)
                .collect(Collectors.toMap(ignored -> Messages.TYPE_ERROR_GLOBAL_REQUIRED_FIELDS, ObjectError::getDefaultMessage)));

        return ResponseEntity
                .badRequest()
                .body(new ErrorRp(HttpStatus.BAD_REQUEST, Messages.VALIDATION_FAILED, errors));
    }

    @ExceptionHandler(AuthorizationServerException.class)
    public ResponseEntity<GlobalRp> handleAuthorizationServerException (AuthorizationServerException ex) {
        log.error(Messages.BASE_ERROR, ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorRp(ex.getHttpStatus(), ex.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<GlobalRp> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        log.error(Messages.BASE_ERROR, ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorRp(HttpStatus.UNAUTHORIZED, Messages.UNAUTHORIZED));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalRp> handleException(Exception ex) {
        log.error(Messages.BASE_ERROR, ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorRp(HttpStatus.INTERNAL_SERVER_ERROR, Messages.UNEXPECTED_ERROR));
    }
}
