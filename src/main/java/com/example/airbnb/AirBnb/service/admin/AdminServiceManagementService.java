package com.example.airbnb.AirBnb.service.admin;

import com.example.airbnb.AirBnb.dto.AdminDto.CreateServiceDto;
import com.example.airbnb.AirBnb.dto.ResponseDto.ServiceResponse;
import com.example.airbnb.AirBnb.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceManagementService {

    private final ServiceRepository serviceRepository;

    @Transactional
    public List<ServiceResponse> createServices(CreateServiceDto serviceDto) {
        Set<String> names = serviceDto.getNames()
                .stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Set<String> existingServices = serviceRepository
                .findAllByNameIn(names)
                .stream()
                .map(com.example.airbnb.AirBnb.models.Service::getName)
                .collect(Collectors.toSet());

        List<com.example.airbnb.AirBnb.models.Service> newServices = names.stream()
                .filter(name -> !existingServices.contains(name))
                .map(name -> com.example.airbnb.AirBnb.models.Service.builder().name(name).build())
                .toList();

        return serviceRepository.saveAll(newServices)
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
