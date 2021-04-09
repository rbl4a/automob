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
    public void saveAllCars(List<Car> cars) {
        carRepository.saveAll(cars);
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> findCarsByPhoneNumber(String phoneNumber) {
        return carRepository.findAllByPersonPhoneNumber(phoneNumber);
    }

    @Override
    public List<Car> findCarsByModel(String modelName) {
        return carRepository.findAllByCarModel_ModelName(modelName);
    }

    @Override
    public List<Car> findCarsByBrand(String brandName) {
        return carRepository.findAllByCarModel_CarBrand_BrandName(brandName);
    }

    @Override
    public Car findCarById(Long id) {
        return carRepository.getOne(id);
    }
}
