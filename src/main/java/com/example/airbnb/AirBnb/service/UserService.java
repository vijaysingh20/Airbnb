package com.example.airbnb.AirBnb.service;

import com.example.airbnb.AirBnb.enums.Role;
import com.example.airbnb.AirBnb.models.User;
import com.example.airbnb.AirBnb.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public void completeProfile(Long userId, String firstName, String lastName, String email, String phone) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(Boolean.TRUE.equals(user.getIsProfileCompleted())) {
            return;
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setIsProfileCompleted(true);
        userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void becomeHost(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(user.getRole() == Role.HOST) {
            throw new RuntimeException("User is already a host");
        }

        user.setRole(Role.HOST);
        userRepository.save(user);

        Map<String, Object> claims = Map.of(
                "userId", user.getId(),
                "role", user.getRole()
        );
        System.out.println("user_id"+ user.getPhone());
    }
}
