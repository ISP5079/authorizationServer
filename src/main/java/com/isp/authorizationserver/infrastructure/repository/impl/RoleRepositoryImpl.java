package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.model.Role;
import com.isp.authorizationserver.domain.port.out.RoleRepository;
import com.isp.authorizationserver.infrastructure.repository.RoleRespositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleRespositoryJpa rolesRespositoryJpa;

    public RoleRepositoryImpl(RoleRespositoryJpa rolesRespositoryJpa) {
        this.rolesRespositoryJpa = rolesRespositoryJpa;
    }

    @Override
    public Role save(Role role) {
        return rolesRespositoryJpa.save(role);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return rolesRespositoryJpa.findByName(name);
    }
}
