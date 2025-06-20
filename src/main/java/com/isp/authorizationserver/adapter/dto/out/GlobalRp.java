package com.isp.authorizationserver.adapter.dto.out;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class GlobalRp {

    protected Integer status;
    protected String message;

    protected GlobalRp(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
