package com.example.airbnb.AirBnb.dto.PropertyDto;

import com.example.airbnb.AirBnb.enums.ListingType;
import com.example.airbnb.AirBnb.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreatePropertyDto {
    private String title;
    private String description;
    private String pricePerNight;
    private String maxGuests;
    private PropertyType propertyType;
    private ListingType listingType;

    private PropertyAddressDto propertyAddressDto;

    private Set<UUID> amenityIds;
    private Set<UUID> serviceIds;
}
