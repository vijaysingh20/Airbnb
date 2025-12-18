package com.example.airbnb.AirBnb.repository;

import com.example.airbnb.AirBnb.dto.LoginDto.OtpType;
import com.example.airbnb.AirBnb.models.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findTopByIdentifierAndOtpAndUsedFalseOrderByCreatedAtDesc(
            String identifier,
            String otp
    );

    Optional<Otp> findByIdentifierAndOtpAndTypeAndUsedFalse(
            String identifier,
            String otp,
            OtpType type
    );

    void deleteByExpiresAtBefore(LocalDateTime time);

    void deleteByIdentifier(String identifier);
}
