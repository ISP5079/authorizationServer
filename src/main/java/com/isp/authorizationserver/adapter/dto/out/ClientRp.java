package com.isp.authorizationserver.adapter.dto.out;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ClientRp extends GlobalRp {

    public ClientRp(HttpStatus status, String message) {
        super(status.value(), message);
    }
}
