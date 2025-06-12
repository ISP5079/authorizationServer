package com.isp.authorizationserver.adapter.dto.in;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isp.authorizationserver.shared.deserializer.UpperCaseDeserializer;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRq {
    @NotBlank
    @JsonDeserialize(using = UpperCaseDeserializer.class)
    private String name;
    @NotBlank
    private String description;
}
