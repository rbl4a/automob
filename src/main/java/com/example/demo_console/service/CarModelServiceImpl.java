package com.example.demo_console.service;

import com.example.demo_console.entity.CarModel;
import com.example.demo_console.repository.CarModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;

    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    public List<CarModel> findAllCarModel() {
        return carModelRepository.findAll();
    }

    @Override
    public void saveAllCarModel(List<CarModel> carModels) {
        carModelRepository.saveAll(carModels);
    }
}
