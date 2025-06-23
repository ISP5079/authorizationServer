package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.Role;

import java.util.Set;

public interface RoleRepository {
    Role save(Role role);

    Set<Role> findAll();

    Role findByName(String name);

    Role findById(Integer idRole);
}
