package com.isp.authorizationserver.domain.port.in.user;

import com.isp.authorizationserver.adapter.dto.in.user.FindUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;

public interface UserGetInfoService {
    UserRp getUser(FindUserRq findUserRq);
}
