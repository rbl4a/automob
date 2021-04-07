package com.example.demo_console.repository;

import com.example.demo_console.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {

    @Query("SELECT model_name, brand_name FROM car_model FULL JOIN car_brand cb on cb.id = car_model.brand_id")
    List<CarModel> findAllModelWithBrands();
}
