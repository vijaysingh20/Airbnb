package com.example.airbnb.AirBnb.controller.admin;

import com.example.airbnb.AirBnb.dto.AdminDto.CreateAmenityDto;
import com.example.airbnb.AirBnb.dto.ResponseDto.AmenityResponse;
import com.example.airbnb.AirBnb.service.admin.AmenityService;
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
@RequestMapping("/api/admin/amenities")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Amenities", description = "Amenities Api's")
public class AmenitiesController {
    private final AmenityService amenityService;

    @PostMapping("/create")
    public List<AmenityResponse> createAmenity(
            @RequestBody CreateAmenityDto amenityDto
    ) {
        return amenityService.createAmenity(amenityDto);
    }
}
