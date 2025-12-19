package com.example.airbnb.AirBnb.repository;

import com.example.airbnb.AirBnb.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
    Optional<Service> findByName(String name);

    Optional<Service> findAllByNameIn(Set<String> names);
}
