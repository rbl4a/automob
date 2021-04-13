package com.example.demo_console.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo_console.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @Test
    void findAllByPersonPhoneNumberTest() {
        List<Car> cars = carRepository.findAllByPersonPhoneNumber("67-72");
        assertEquals(1, cars.size());
        assertEquals("67-72", cars.get(0).getPerson().getPhoneNumber());

    }

    @Test
    void findAllByCarModel_ModelNameTest() {
        List<Car> cars = carRepository.findAllByCarModel_ModelName("X5");
        assertEquals(1, cars.size());
        assertEquals("X5", cars.get(0).getCarModel().getModelName());
    }

    @Test
    void findAllByCarModel_CarBrand_BrandNameTest() {
        List<Car> cars = carRepository.findAllByCarModel_CarBrand_BrandName("BMW");
        assertEquals(2, cars.size());
        assertEquals("BMW", cars.get(0).getCarModel().getCarBrand().getBrandName());

    }

}
