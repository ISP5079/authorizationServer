package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleAppRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import org.springframework.security.oauth2.jwt.Jwt;

public interface RoleAppService {
    RoleRp createRoleApp(CreateRoleAppRq createRoleApp);

    RoleRp findRoleAppByApp(String appName);

    RoleRp findRoleAppByApp(Jwt jwt);
}
