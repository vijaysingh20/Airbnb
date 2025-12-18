package com.example.airbnb.AirBnb.repository;

import com.example.airbnb.AirBnb.enums.AuthProvider;
import com.example.airbnb.AirBnb.models.UserAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthProviderRepository extends JpaRepository<UserAuthProvider, Long> {
    Optional<UserAuthProvider> findByProviderAndProviderUserId(
            AuthProvider provider,
            String providerUserId
    );

    boolean existsByProviderAndProviderUserId(
            AuthProvider provider,
            String providerUserId
    );
}
