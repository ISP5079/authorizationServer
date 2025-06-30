package com.isp.authorizationserver.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user_role_client")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleClient {
    @EmbeddedId
    private UserRoleClientId id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", insertable = false, updatable = false)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", insertable = false, updatable = false)
    private RoleClientAllowed roleClientAllowed;

}