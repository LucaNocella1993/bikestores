package com.example.bikestores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bikestores.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

