package com.example.demo_console.repository;

import com.example.demo_console.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByCarModel_CarBrandBrandName(String brandName);
    List<Car> findAllByPerson_PhoneNumber(String phoneNumber);
    Car findCarByGovNumber(String number);
}
