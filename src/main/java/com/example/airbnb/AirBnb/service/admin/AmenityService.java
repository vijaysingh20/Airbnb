package com.example.airbnb.AirBnb.service.admin;

import com.example.airbnb.AirBnb.dto.AdminDto.CreateAmenityDto;
import com.example.airbnb.AirBnb.dto.ResponseDto.AmenityResponse;
import com.example.airbnb.AirBnb.models.Amenity;
import com.example.airbnb.AirBnb.repository.AmenityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityService {
    private final AmenityRepository amenityRepository;

    @Transactional
    public List<AmenityResponse> createAmenity(CreateAmenityDto amenities) {
        Set<String> names = amenities.getNames()
                .stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Set<String> existingAmenities = amenityRepository
                .findAllByNameIn(names)
                .stream()
                .map(Amenity::getName)
                .collect(Collectors.toSet());

        List<Amenity> newAmenities = names.stream()
                .filter(name -> !existingAmenities.contains(name))
                .map(name -> Amenity.builder().name(name).build())
                .toList();

        List<Amenity> savedAmenities = amenityRepository.saveAll(newAmenities);

        return savedAmenities.stream()
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
