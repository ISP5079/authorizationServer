package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.RoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;

public interface RoleService {
    RoleRp createRole(RoleRq roleRq);

}
