package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.in.user.FindUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.domain.port.in.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/isExist")
    public ResponseEntity<UserRp> isUserExist(@Valid @RequestBody FindUserRq isExistUserRq) {
        return ResponseEntity.ok().body(userService.isUserExist(isExistUserRq));
    }

    @GetMapping()
    public ResponseEntity<UserRp> getUserInfo(@Valid @RequestBody FindUserRq findUserRq) {
        return ResponseEntity.ok().body(userService.getUser(findUserRq));
    }

    @PostMapping
    public ResponseEntity<UserRp> createUser(@Valid @RequestBody CreateUserRq userRq) {
        return ResponseEntity.ok().body(userService.createUser(userRq));
    }
}
