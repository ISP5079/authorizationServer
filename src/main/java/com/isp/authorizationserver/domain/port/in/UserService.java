package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.in.user.FindUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;

public interface UserService {
    UserRp getUser(FindUserRq findUserRq);

    UserRp createUser(CreateUserRq userRq);
}
