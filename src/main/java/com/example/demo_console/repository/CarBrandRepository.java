package com.example.demo_console.repository;

import com.example.demo_console.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {
    List<CarBrand> findByBrandName(String brandName);
}
