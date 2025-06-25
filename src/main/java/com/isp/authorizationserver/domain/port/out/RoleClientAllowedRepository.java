package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.RoleClientAllowed;

import java.util.Set;

public interface RoleClientAllowedRepository {
    void save(RoleClientAllowed roleClientAllowed);

    Set<RoleClientAllowed> findAllRoleApp();

    Set<RoleClientAllowed> findRoleAppByApp(String appName);
}
