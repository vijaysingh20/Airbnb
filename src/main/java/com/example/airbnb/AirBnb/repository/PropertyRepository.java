package com.example.airbnb.AirBnb.repository;

import com.example.airbnb.AirBnb.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {
}
