package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.in.user.FindUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.domain.port.in.UserRoleClientService;
import com.isp.authorizationserver.domain.port.in.user.UserGetInfoService;
import com.isp.authorizationserver.shared.constants.EndPoints;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.USER)
public class UserController {

    private final UserGetInfoService userService;
    private final UserRoleClientService userRoleClientService;

    public UserController(UserGetInfoService userService, UserRoleClientService userRoleClientService) {
        this.userService = userService;
        this.userRoleClientService = userRoleClientService;
    }

    @PreAuthorize("hasAuthority(T(com.isp.authorizationserver.shared.constants.RoleAuthorization).APP.getScopeWithPrefix())")
    @GetMapping()
    public ResponseEntity<UserRp> getUserInfo(@Valid @RequestBody FindUserRq findUserRq) {
        return ResponseEntity.ok().body(userService.getUser(findUserRq));
    }

    @PreAuthorize("hasAuthority(T(com.isp.authorizationserver.shared.constants.RoleAuthorization).APP.getScopeWithPrefix())")
    @PostMapping
    public ResponseEntity<UserRp> createUser(@Valid @RequestBody CreateUserRq userRq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRoleClientService.registerUser(userRq));
    }
}
