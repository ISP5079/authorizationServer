package com.isp.authorizationserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "oauth2_authorization_consent")
public class Oauth2AuthorizationConsent {
    @EmbeddedId
    private Oauth2AuthorizationConsentId id;

    @Column(name = "authorities", nullable = false, length = 1000)
    private String authorities;
}