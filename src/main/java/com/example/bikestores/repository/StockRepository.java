package com.example.bikestores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bikestores.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}

