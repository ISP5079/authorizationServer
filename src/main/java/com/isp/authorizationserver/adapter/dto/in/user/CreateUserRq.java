package com.isp.authorizationserver.adapter.dto.in.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class CreateUserRq {
    @Size(min = 1)
    private List<Integer> roles;
    @NotBlank
    @Length(max = 25)
    private String userName;
    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @Length(max = 10)
    private String phone;
}
