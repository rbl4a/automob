package com.example.demo_console.repository;

import com.example.demo_console.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByPersonPhoneNumber(String phoneNumber);
    List<Car> findAllByCarModel_ModelName(String modelName);
    List<Car> findAllByCarModel_CarBrand_BrandName(String brandName);
}
