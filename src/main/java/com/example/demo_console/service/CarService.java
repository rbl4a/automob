package com.example.demo_console.service;

import com.example.demo_console.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAllCarService();
    void saveAll(List<Car> cars);
}
