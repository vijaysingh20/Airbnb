package com.example.airbnb.AirBnb.controller;

import com.example.airbnb.AirBnb.dto.ResponseDto.ApiResponse;
import com.example.airbnb.AirBnb.dto.UserDto.CompleteProfileDto;
import com.example.airbnb.AirBnb.models.User;
import com.example.airbnb.AirBnb.security.UserPrincipal;
import com.example.airbnb.AirBnb.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User related API's")
public class UserController {

    private final UserService userService;

    @PutMapping("/complete-profile")
    public ResponseEntity<ApiResponse<Map<String, Object>>> completeProfile(
            @Valid @RequestBody CompleteProfileDto request,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        userService.completeProfile(
                userPrincipal.getUserId(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        200,
                        Map.of("message", "Profile completed successfully")
                )
        );
    }



    @GetMapping("get-user-by-id")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUserById(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        User user = userService.getUserById(
                userPrincipal.getUserId()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        200,
                        Map.of("data", user)
                )
        );
    }

    @PostMapping("become-host")
    public ResponseEntity<ApiResponse<Map<String, Object>>> becomeHost(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.becomeHost(userPrincipal.getUserId());
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        200,
                        Map.of("message", "Successfully become host")
                )
        );
    }

}
