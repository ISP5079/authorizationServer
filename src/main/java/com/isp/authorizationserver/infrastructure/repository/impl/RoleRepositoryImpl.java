package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.exception.RoleCreateException;
import com.isp.authorizationserver.domain.exception.RoleNotFoundException;
import com.isp.authorizationserver.domain.model.Role;
import com.isp.authorizationserver.domain.port.out.RoleRepository;
import com.isp.authorizationserver.infrastructure.repository.RoleRespositoryJpa;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleRespositoryJpa rolesRespositoryJpa;

    public RoleRepositoryImpl(RoleRespositoryJpa rolesRespositoryJpa) {
        this.rolesRespositoryJpa = rolesRespositoryJpa;
    }

    @Override
    public Role save(Role role) {
        try {
            return rolesRespositoryJpa.save(role);
        } catch (DataAccessException e) {
            if (e.getCause() instanceof DataIntegrityViolationException)
                throw new RoleCreateException(RoleCreateException.ERROR_ROLE_ALREADY_EXISTS, e.getCause());
            throw new RoleCreateException(RoleCreateException.ERROR_CREATING_ROLE, e.getCause());
        }
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(rolesRespositoryJpa.findAll());
    }

    @Override
    public Role findByName(String name) {
        return rolesRespositoryJpa.findByName(name).orElseThrow(() -> new RoleNotFoundException(RoleNotFoundException.ROLE_NOT_FOUND));
    }

    @Override
    public Role findById(Integer idRole) {
        return rolesRespositoryJpa.findById(idRole).orElseThrow(() -> new RoleNotFoundException(RoleNotFoundException.ROLE_NOT_FOUND));
    }
}
