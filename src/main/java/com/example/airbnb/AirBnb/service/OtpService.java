package com.example.airbnb.AirBnb.service;

import com.example.airbnb.AirBnb.dto.LoginDto.OtpType;
import com.example.airbnb.AirBnb.models.Otp;
import com.example.airbnb.AirBnb.repository.OtpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final EmailSenderService emailSenderService;

    public String generateOtp(String identifier, OtpType type) {
        String otp = String.valueOf((int)(Math.random()*900000) + 100000);
        System.out.println("here" + otp);
        Otp otpEntity = Otp.builder()
                .identifier(identifier)
                .type(type)
                .otp(otp)
                .used(false)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();

        otpRepository.save(otpEntity);

        if(type == OtpType.EMAIL) {
            emailSenderService.sendEmail(identifier, otp);
        }
        System.out.println("otp " + otp);
        return otp;
    }

    @Transactional
    public boolean verifyOtp(String identifier, String otp) {
        System.out.println(identifier + " " + otp);
        Otp phoneOtp = otpRepository.findTopByIdentifierAndOtpAndUsedFalseOrderByCreatedAtDesc(identifier, otp)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if(phoneOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        phoneOtp.setUsed(true);
        return true;
    }
}
