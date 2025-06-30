package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;

public interface UserRoleClientService {
    UserRp registerUser(CreateUserRq userRq);
}
