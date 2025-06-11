package com.isp.authorizationserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Oauth2AuthorizationConsentId implements Serializable {
    @Serial
    private static final long serialVersionUID = -8691279897981122919L;

    @Column(name = "registered_client_id", nullable = false, length = 100)
    private String registeredClientId;

    @Column(name = "principal_name", nullable = false, length = 200)
    private String principalName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Oauth2AuthorizationConsentId entity = (Oauth2AuthorizationConsentId) o;
        return Objects.equals(this.registeredClientId, entity.registeredClientId) &&
                Objects.equals(this.principalName, entity.principalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registeredClientId, principalName);
    }

}