package com.example.ApiSporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiSporte.model.ExChange;

@Repository
public interface ExchangeRepository extends JpaRepository<ExChange, Integer>{
    
}
