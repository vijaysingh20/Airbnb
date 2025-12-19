package com.example.airbnb.AirBnb.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Property property;

    private String itemName;
    private Integer totalQuantity;
    private Integer availableQuantity;
}
