package com.isp.authorizationserver.domain.port.in.user;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.domain.model.User;

public interface UserSaveService {
    User createUser(CreateUserRq userRq);
}
