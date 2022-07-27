package com.example.bikestores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bikestores.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
