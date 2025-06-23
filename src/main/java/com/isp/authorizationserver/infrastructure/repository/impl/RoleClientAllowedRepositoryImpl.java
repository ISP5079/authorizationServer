package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.exception.RoleClientAllowedCreateException;
import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import com.isp.authorizationserver.domain.port.out.RoleClientAllowedRepository;
import com.isp.authorizationserver.infrastructure.repository.RoleClientAllowedRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class RoleClientAllowedRepositoryImpl implements RoleClientAllowedRepository {
    private final RoleClientAllowedRepositoryJpa roleClientAllowedRepositoryJpa;

    public RoleClientAllowedRepositoryImpl(RoleClientAllowedRepositoryJpa roleClientAllowedRepositoryJpa) {
        this.roleClientAllowedRepositoryJpa = roleClientAllowedRepositoryJpa;
    }

    @Override
    public void save(RoleClientAllowed roleClientAllowed) {
        try {
            roleClientAllowedRepositoryJpa.save(roleClientAllowed);
        } catch (Exception e) {
            throw new RoleClientAllowedCreateException();
        }
    }
}
