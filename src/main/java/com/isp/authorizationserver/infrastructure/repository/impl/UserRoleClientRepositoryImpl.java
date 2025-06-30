package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.exception.UserRoleClientCreateException;
import com.isp.authorizationserver.domain.exception.UserRoleClientNotFoundException;
import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.model.UserRoleClient;
import com.isp.authorizationserver.domain.port.out.UserRoleClientRepository;
import com.isp.authorizationserver.infrastructure.repository.UserRoleClienteRepositoryJpa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleClientRepositoryImpl implements UserRoleClientRepository {

    private final UserRoleClienteRepositoryJpa userRoleClienteRepositoryJpa;

    public UserRoleClientRepositoryImpl(UserRoleClienteRepositoryJpa userRoleClienteRepositoryJpa) {
        this.userRoleClienteRepositoryJpa = userRoleClienteRepositoryJpa;
    }

    @Override
    public void save(UserRoleClient userRoleClient) {
        try {
            userRoleClienteRepositoryJpa.save(userRoleClient);
        } catch (DataIntegrityViolationException ex) {
            throw new UserRoleClientCreateException(ex.getCause());
        }
    }

    @Override
    public UserRoleClient findByUserAndClientId(User userId, String clientId) {
        return userRoleClienteRepositoryJpa.findByIdUserAndRoleClientAllowed_IdClient_ClientId(userId, clientId)
                .orElseThrow(UserRoleClientNotFoundException::new);
    }
}
