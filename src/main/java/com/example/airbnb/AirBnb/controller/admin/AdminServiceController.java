package com.example.airbnb.AirBnb.controller.admin;

import com.example.airbnb.AirBnb.dto.AdminDto.CreateServiceDto;
import com.example.airbnb.AirBnb.dto.ResponseDto.ServiceResponse;
import com.example.airbnb.AirBnb.service.admin.AdminServiceManagementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/services")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name="Services", description = "Service Api's")
public class AdminServiceController {
    private final AdminServiceManagementService adminServiceManagementService;

    @PostMapping("/create")
    public List<ServiceResponse> createService(
            @RequestBody CreateServiceDto serviceDto
            ) {
        return adminServiceManagementService.createServices(serviceDto);
    }
}
