package com.isp.authorizationserver.adapter.dto.in;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isp.authorizationserver.shared.deserializer.UpperCaseDeserializer;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RoleRq {
    @NotBlank
    @Length(max = 50)
    @JsonDeserialize(using = UpperCaseDeserializer.class)
    private String name;
    @NotBlank
    @Length(max = 100)
    private String description;
}
