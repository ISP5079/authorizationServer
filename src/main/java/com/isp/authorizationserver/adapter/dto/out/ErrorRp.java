package com.isp.authorizationserver.adapter.dto.out;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorRp extends GlobalRp {
    private LocalDateTime timestamp;
    private String error;
    private Map<String, String> errores;

    public ErrorRp(HttpStatus status, String message) {
        super(status.value(), message);
        this.timestamp = LocalDateTime.now();
        this.error = status.getReasonPhrase();
    }

    public ErrorRp(HttpStatus status, String message, Map<String, String> errores) {
        super(status.value(), message);
        this.timestamp = LocalDateTime.now();
        this.error = status.getReasonPhrase();
        this.errores = errores;
    }
}
