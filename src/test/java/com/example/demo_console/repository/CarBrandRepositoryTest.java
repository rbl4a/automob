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
    void findByNameSizeListTest() {
        List<CarBrand> carBrand = carBrandRepository.findByBrandName("BMW");
        assertEquals(1, carBrand.size());
    }

    @Test
    void findByBrandNameTest() {
        List<CarBrand> carBrand = carBrandRepository.findByBrandName("BMW");
        assertEquals("BMW", carBrand.get(0).getBrandName());

    }
}