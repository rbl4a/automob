package com.example.demo_console.service;

import com.example.demo_console.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAllCar();
    void saveAllCars(List<Car> cars);
    void saveCar(Car car);
    void deleteCarById(Long id);
    List<Car> findCarsByPhoneNumber(String phoneNumber);
    List<Car> findCarsByModel(String modelName);
    List<Car> findCarsByBrand(String brandName);
    Car findCarById(Long id);
}
