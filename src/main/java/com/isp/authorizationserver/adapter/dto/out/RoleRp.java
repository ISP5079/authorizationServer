package com.isp.authorizationserver.adapter.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RoleRp extends GlobalRp{
    private Integer idRole;

    public RoleRp(HttpStatus status, String message, Integer idRole) {
        super(status.value(), message);
        this.idRole = idRole;
    }
}
