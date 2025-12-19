package com.example.airbnb.AirBnb.dto.PropertyDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropertyAddressDto {
    private String country;
    private String state;
    private String city;
    private String locality;
    private String pincode;
    private String street;
    private String landmark;
    private Double latitude;
    private Double longitude;
}