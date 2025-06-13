package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Role save(Role role);

    Optional<Role> findByName(String name);
}
