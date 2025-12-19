package com.example.airbnb.AirBnb.service;

import com.example.airbnb.AirBnb.dto.ResponseDto.ServiceResponse;
import com.example.airbnb.AirBnb.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCatalog {
    private final ServiceRepository serviceRepository;

    public List<ServiceResponse> getAll() {
        return serviceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ServiceResponse toResponse(com.example.airbnb.AirBnb.models.Service service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .name(service.getName())
                .build();
    }
}
