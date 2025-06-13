package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.in.UserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public ResponseEntity<UserRp> createUser(@Valid @RequestBody UserRq userRq) {
        return ResponseEntity.ok().build();
    }
}
