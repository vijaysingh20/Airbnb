package com.example.airbnb.AirBnb.repository;

import com.example.airbnb.AirBnb.models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AmenityRepository extends JpaRepository<Amenity, UUID> {
    Optional<Amenity> findByName(String name);

    Optional<Amenity> findAllByNameIn(Set<String> names);

}
