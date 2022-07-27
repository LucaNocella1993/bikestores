package com.example.bikestores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bikestores.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}

