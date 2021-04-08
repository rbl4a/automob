package com.example.demo_console.repository;

import com.example.demo_console.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {

}
