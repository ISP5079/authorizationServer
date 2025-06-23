package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.RoleClientAllowed;

public interface RoleClientAllowedRepository {
    void save(RoleClientAllowed roleClientAllowed);
}
