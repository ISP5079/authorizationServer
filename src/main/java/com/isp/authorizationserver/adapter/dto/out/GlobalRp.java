package com.isp.authorizationserver.adapter.dto.out;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class GlobalRp {

    protected int status;
    protected String message;

    protected GlobalRp(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
