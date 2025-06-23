package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleAppRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;

public interface RoleAppService {
    RoleRp createRoleApp(CreateRoleAppRq createRoleApp);
}
