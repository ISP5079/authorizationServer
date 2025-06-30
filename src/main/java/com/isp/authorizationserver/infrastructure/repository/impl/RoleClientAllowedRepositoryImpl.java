package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.exception.RoleClientAllowedCreateException;
import com.isp.authorizationserver.domain.exception.RoleNotFoundException;
import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import com.isp.authorizationserver.domain.port.out.RoleClientAllowedRepository;
import com.isp.authorizationserver.infrastructure.repository.RoleClientAllowedRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Set<RoleClientAllowed> findAllRoleApp() {
        return Optional.of(roleClientAllowedRepositoryJpa.findAll())
                .filter(list -> !list.isEmpty())
                .map(HashSet::new)
                .orElseThrow(() -> new RoleNotFoundException(RoleNotFoundException.ROLES_NOT_FOUND));
    }

    @Override
    public Set<RoleClientAllowed> findRoleAppByApp(String appName) {
        return Optional.ofNullable(roleClientAllowedRepositoryJpa.findByIdClient_ClientId(appName))
                .filter(set -> !set.isEmpty())
                .orElseThrow(() -> new RoleNotFoundException(RoleNotFoundException.ROLES_NOT_FOUND));
    }

    @Override
    public RoleClientAllowed findRoleAppByAppAndRole(String appName, String roleName) {
        return roleClientAllowedRepositoryJpa.findFisrtByIdClient_ClientIdAndIdRole_Name(appName, roleName).orElseThrow(() -> new RoleNotFoundException(RoleNotFoundException.ROLE_NOT_FOUND));
    }
}
