package com.example.demo_console.repository;

import com.example.demo_console.entity.CarBrand;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CarBrandRepositoryTest {

    @Autowired
    CarBrandRepository carBrandRepository;

    @Test
    void findByBrandName() {
        List<CarBrand> carBrand = carBrandRepository.findByBrandName("BMW");
        assertFalse(carBrand.isEmpty());
        assertEquals("BMW", carBrand.get(0).getBrandName());

    }
}