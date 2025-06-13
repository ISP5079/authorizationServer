package com.isp.authorizationserver.adapter.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class UserRq {
    @NotBlank
    private String clientId;
    @Size(min = 1)
    private List<Integer> roles;
    @NotBlank
    private String username;
    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    private String phone;
}
