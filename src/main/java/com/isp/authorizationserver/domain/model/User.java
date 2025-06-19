package com.isp.authorizationserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id_user", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @NotNull
    @Column(name = "full_name", nullable = false, length = Integer.MAX_VALUE)
    private String fullName;

    @Size(max = 25)
    @Column(name = "user_name", length = 25)
    private String userName;

    @NotNull
    @Column(name = "password_hash", nullable = false, length = Integer.MAX_VALUE)
    private String passwordHash;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    @Size(max = 10)
    @Column(name = "phone", length = 10)
    private String phone;

    @ColumnDefault("true")
    @Column(name = "is_verified")
    private Boolean isVerified;

    @ColumnDefault("(now() + '120 days'::interval)")
    @Column(name = "password_expires_at")
    private OffsetDateTime passwordExpiresAt;

}