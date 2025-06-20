package com.isp.authorizationserver.adapter.dto.out;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
public class RoleRp extends GlobalRp{
    private Integer idRole;
    private String roleName;
    private Set<RoleRp> roles;
}
