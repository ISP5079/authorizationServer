package com.isp.authorizationserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class UserRoleClientId implements java.io.Serializable {
    private static final long serialVersionUID = 835472690731398071L;
    @Column(name = "id_user", nullable = false)
    private UUID idUser;

    @Column(name = "id_role", nullable = false)
    private Integer idRole;

    @Column(name = "client_id", nullable = false, length = Integer.MAX_VALUE)
    private String clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoleClientId entity = (UserRoleClientId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.clientId, entity.clientId) &&
                Objects.equals(this.idRole, entity.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, clientId, idRole);
    }

}