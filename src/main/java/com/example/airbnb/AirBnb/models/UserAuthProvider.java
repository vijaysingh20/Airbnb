package com.example.airbnb.AirBnb.models;

import com.example.airbnb.AirBnb.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "user_auth_provider",
        uniqueConstraints = @UniqueConstraint(columnNames = {"provider", "providerUserId"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthProvider {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    private String providerUserId;
    private String passwordHash;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
