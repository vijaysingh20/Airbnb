package com.example.airbnb.AirBnb.controller;

import com.example.airbnb.AirBnb.dto.LoginDto.OtpType;
import com.example.airbnb.AirBnb.dto.ResponseDto.ApiResponse;
import com.example.airbnb.AirBnb.dto.ResponseDto.AuthResponseDto;
import com.example.airbnb.AirBnb.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication Api's")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<Map<String, Object>>> sendOtp(
            @RequestParam String identifier,
            @RequestParam OtpType type
    ) {
        authService.sendOtp(identifier, type);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        200,
                        Map.of("message", "OTP sent")
                )
        );
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<AuthResponseDto>> verifyOtp(
            @RequestParam String identifier,
            @RequestParam String otp,
            @RequestParam OtpType type
    ) {
        AuthResponseDto data = authService.verifyOtpAndLogin(identifier, otp, type);
        return ResponseEntity.ok(
                new ApiResponse<>(true, 200, data)
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(HttpServletRequest request) {
        authService.logOut(request);
        return ResponseEntity.ok("Logged out");
    }
}
