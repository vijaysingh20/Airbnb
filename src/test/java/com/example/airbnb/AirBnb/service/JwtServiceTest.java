package com.example.airbnb.AirBnb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class JwtServiceTest {
    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateOrValidateToken() {
        String subject = "8447457913";

        String token = jwtService.generateToken(Map.of(), subject);
        System.out.println("token"+ token);

        assertThat(token).isNotNull();
        assertThat(jwtService.extractSubject(token)).isEqualTo(subject);
        assertThat(jwtService.isValidToken(token, subject)).isTrue();
    }

    @Test
    void shouldGenerateTokenWithClaims() {
        String subject = "8447457913";

        String token = jwtService.generateToken(
                Map.of("role", "USER", "userId", 1L),
                subject
        );
        System.out.println("token" + token);

        String role = jwtService.extractClaim(token, c -> c.get("role", String.class));
        UUID userId = jwtService.extractClaim(token, c -> c.get("userId", UUID.class));

        assertThat(role).isEqualTo("USER");
        assertThat(userId).isEqualTo(UUID.fromString("a7c3f891-2d4e-4b3a-9f7c-8e5d6a1b2c3d"));
    }
}
