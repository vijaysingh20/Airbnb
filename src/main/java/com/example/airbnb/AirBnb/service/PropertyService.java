package com.example.airbnb.AirBnb.service;

import com.example.airbnb.AirBnb.dto.PropertyDto.CreatePropertyDto;
import com.example.airbnb.AirBnb.dto.PropertyDto.PropertyAddressDto;
import com.example.airbnb.AirBnb.models.Property;
import com.example.airbnb.AirBnb.models.PropertyAddress;
import com.example.airbnb.AirBnb.models.User;
import com.example.airbnb.AirBnb.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public Property createProperty(CreatePropertyDto createPropertyDto, User host) {
        PropertyAddressDto propertyAddress = createPropertyDto.getPropertyAddressDto();
        PropertyAddress address = PropertyAddress.builder()
                .street(propertyAddress.getStreet())
                .locality(propertyAddress.getLocality())
                .city(propertyAddress.getCity())
                .state(propertyAddress.getState())
                .country(propertyAddress.getCountry())
                .pincode(propertyAddress.getPincode())
                .landmark(propertyAddress.getLandmark())
                .longitude(propertyAddress.getLongitude())
                .latitude(propertyAddress.getLatitude())
                .build();

        Property property = new Property();

        return propertyRepository.save(property);

    }
}
