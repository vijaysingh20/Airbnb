package com.example.airbnb.AirBnb.controller;

import com.example.airbnb.AirBnb.dto.ResponseDto.AmenityResponse;
import com.example.airbnb.AirBnb.service.AmenityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/amenities")
@Tag(name = "Amenities", description = "Amenities Api's")
public class AmenityController {
    private final AmenityService amenityService;

    @GetMapping("/get-all")
    public List<AmenityResponse> getALl() {
        return amenityService.getAll();
    }
}
