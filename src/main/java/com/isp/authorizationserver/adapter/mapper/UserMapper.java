package com.isp.authorizationserver.adapter.mapper;

import com.isp.authorizationserver.adapter.dto.in.UserRq;
import com.isp.authorizationserver.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUserByUserRq(UserRq userRq);
}
