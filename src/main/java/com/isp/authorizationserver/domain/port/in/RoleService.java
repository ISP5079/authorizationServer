package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;

public interface RoleService {
    RoleRp createRole(CreateRoleRq roleRq);

    RoleRp getRoles();

    RoleRp getRoleByRoleName(String roleName);
}
