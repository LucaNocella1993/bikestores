package com.example.bikestores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bikestores.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}

