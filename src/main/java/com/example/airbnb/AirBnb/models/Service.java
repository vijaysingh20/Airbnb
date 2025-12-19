package com.example.airbnb.AirBnb.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private Double price;
    private Boolean perDay;
}
