package com.isp.authorizationserver.adapter.mapper;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserByUserRq(CreateUserRq userRq);

    UserRp toUserRpByUser(User user);
}
