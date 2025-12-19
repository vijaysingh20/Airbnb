package com.example.airbnb.AirBnb.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class AmenityResponse {
    private UUID id;
    private String name;
}
