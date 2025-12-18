package com.example.airbnb.AirBnb.service;

import com.example.airbnb.AirBnb.dto.LoginDto.OtpType;
import com.example.airbnb.AirBnb.dto.ResponseDto.AuthResponseDto;
import com.example.airbnb.AirBnb.enums.Role;
import com.example.airbnb.AirBnb.models.User;
import com.example.airbnb.AirBnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final OtpService otpService;

    public void sendOtp(String identifier, OtpType type) {
        otpService.generateOtp(identifier, type);
    }

    public AuthResponseDto verifyOtpAndLogin(String identifier, String otp, OtpType type) {
        otpService.verifyOtp(identifier, otp);

        User user = getOrCreateUser(identifier, type);

        Map<String, Object> claims = Map.of(
                "userId", user.getId(),
                "role", user.getRole()
        );

        return new AuthResponseDto(
                jwtService.generateToken(claims, identifier),
                user.getIsProfileCompleted()
        );
    }

    private User getOrCreateUser(String identifier, OtpType type) {
        User user = switch (type) {
            case PHONE -> userRepository.findByPhone(identifier).orElse(null);
            case EMAIL -> userRepository.findByEmail(identifier).orElse(null);
        };
        return switch (type) {
            case PHONE -> userRepository.findByPhone(identifier)
                    .orElseGet(() -> userRepository.save(
                            User.builder()
                                    .phone(identifier)
                                    .phoneVerified(true)
                                    .firstName("Guest")
                                    .lastName("User")
                                    .isProfileCompleted(false)
                                    .role(Role.USER)
                                    .build()
                    ));
            case EMAIL -> userRepository.findByEmail(identifier)
                    .orElseGet(() -> userRepository.save(
                            User.builder()
                                    .email(identifier)
                                    .emailVerified(true)
                                    .firstName("Guest")
                                    .lastName("User")
                                    .isProfileCompleted(false)
                                    .role(Role.USER)
                                    .build()
                    ));
        };
    }
}
