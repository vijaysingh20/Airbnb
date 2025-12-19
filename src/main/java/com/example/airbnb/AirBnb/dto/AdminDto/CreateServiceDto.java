package com.example.airbnb.AirBnb.dto.AdminDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class CreateServiceDto {
    private Set<String> names;
}
