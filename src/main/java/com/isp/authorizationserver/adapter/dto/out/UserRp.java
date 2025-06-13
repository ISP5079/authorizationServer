package com.isp.authorizationserver.adapter.dto.out;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserRp extends GlobalRp {
    private String password;
}
