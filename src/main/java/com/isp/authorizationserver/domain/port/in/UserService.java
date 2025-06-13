package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.UserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;

public interface UserService {
    UserRp createUser(UserRq userRq);
}
