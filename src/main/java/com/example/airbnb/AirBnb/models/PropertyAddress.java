package com.example.airbnb.AirBnb.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "property_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
