package com.example.demo_console.service;

import com.example.demo_console.entity.Car;
import com.example.demo_console.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAllCar() {
        return carRepository.findAll();
    }

    @Override
    public void saveAll(List<Car> cars) {
        carRepository.saveAll(cars);
    }
}
