package com.example.airbnb.AirBnb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Airbnb login OTP");
        message.setText(
                "Your OTP is: " + otp + "\n\n" +
                        "This OTP will expire in 5 minutes.\n\n" +
                        "If you did not request this, please ignore this email."
        );

        javaMailSender.send(message);
    }
}
