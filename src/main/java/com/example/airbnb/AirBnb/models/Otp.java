package com.example.airbnb.AirBnb.models;

import com.example.airbnb.AirBnb.dto.LoginDto.OtpType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "otp",
        indexes = {
                @Index(name = "idx_identifier", columnList = "identifier"),
                @Index(name = "idx_identifier_otp", columnList = "identifier, otp")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Otp {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false, length = 6)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OtpType type;

    @Column(nullable = false)
    private Boolean used;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
