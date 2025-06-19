package com.isp.authorizationserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class RoleClientAllowedId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1303077066621686939L;

    @NotNull
    @Column(name = "id_role", nullable = false)
    private Integer idRole;

    @NotNull
    @Column(name = "client_id", nullable = false, length = 100)
    private String clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleClientAllowedId entity = (RoleClientAllowedId) o;
        return Objects.equals(this.clientId, entity.clientId) &&
                Objects.equals(this.idRole, entity.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, idRole);
    }

}