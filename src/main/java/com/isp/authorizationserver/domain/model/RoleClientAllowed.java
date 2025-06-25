package com.isp.authorizationserver.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "role_client_allowed")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleClientAllowed {
    @EmbeddedId
    private RoleClientAllowedId id;

    @MapsId("idRole")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role idRole;

    @MapsId("idClient")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Oauth2RegisteredClient idClient;

}