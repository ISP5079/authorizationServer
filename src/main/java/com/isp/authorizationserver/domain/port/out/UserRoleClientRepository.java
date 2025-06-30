package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.model.UserRoleClient;

public interface UserRoleClientRepository {
    void save(UserRoleClient userRoleClient);

    UserRoleClient findByUserAndClientId(User user, String clientId);
}
