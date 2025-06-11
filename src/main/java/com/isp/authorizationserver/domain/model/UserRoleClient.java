package com.isp.authorizationserver.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_role_client")
public class UserRoleClient {
    @EmbeddedId
    private UserRoleClientId id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private com.isp.authorizationserver.domain.model.User idUser;

    @MapsId("idRole")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role idRole;

    @MapsId("clientId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "client_id")
    private Oauth2RegisteredClient client;

}