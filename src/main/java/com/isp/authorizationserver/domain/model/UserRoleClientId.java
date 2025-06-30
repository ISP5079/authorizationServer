package com.isp.authorizationserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleClientId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 835472690731398071L;

    @NotNull
    @Column(name = "id_user", nullable = false)
    private UUID idUser;

    @NotNull
    @Column(name = "id_role", nullable = false)
    private Integer idRole;

    @NotNull
    @Column(name = "id_client", nullable = false, length = Integer.MAX_VALUE)
    private String idClient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoleClientId entity = (UserRoleClientId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idClient, entity.idClient) &&
                Objects.equals(this.idRole, entity.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idClient, idRole);
    }

}