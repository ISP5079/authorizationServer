package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.Role;

public interface RoleRepository {
    Role save(Role role);

    Role findByName(String name);
}
