package com.isp.authorizationserver.adapter.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
public class ClientRq {
    @NotBlank
    @Length(max = 100)
    private String clientId;
    @Size(min = 1)
    private Set<String> scopes;
}
