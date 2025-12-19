package com.example.airbnb.AirBnb.controller;

import com.example.airbnb.AirBnb.dto.ResponseDto.ServiceResponse;
import com.example.airbnb.AirBnb.service.ServiceCatalog;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
@Tag(name = "Service", description = "Service Api's")
public class ServiceController {
    private final ServiceCatalog serviceCatalog;

    @GetMapping("/get-all")
    public List<ServiceResponse> getAll() {
        return serviceCatalog.getAll();
    }
}
