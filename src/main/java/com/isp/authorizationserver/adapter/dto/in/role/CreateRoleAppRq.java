package com.isp.authorizationserver.adapter.dto.in.role;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isp.authorizationserver.shared.annotation.AtLeastOneNotNull;
import com.isp.authorizationserver.shared.deserializer.UpperCaseDeserializer;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastOneNotNull(field1 = "idRole", field2 = "roleName")
public class CreateRoleAppRq {
    private Integer idRole;
    @JsonDeserialize(using = UpperCaseDeserializer.class)
    private String roleName;
    @NotBlank
    private String appName;
}
