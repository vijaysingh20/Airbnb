package com.example.airbnb.AirBnb.service;

import com.example.airbnb.AirBnb.dto.ResponseDto.AmenityResponse;
import com.example.airbnb.AirBnb.models.Amenity;
import com.example.airbnb.AirBnb.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public List<AmenityResponse> getAll() {
        return amenityRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AmenityResponse toResponse(Amenity amenity) {
        return AmenityResponse.builder()
                .id(amenity.getId())
                .name(amenity.getName())
                .build();
    }
}
