package com.example.demo_console.service;

import com.example.demo_console.entity.CarModel;

import java.util.List;

public interface CarModelService {
    List<CarModel> findAllCarModel();
    void saveAllCarModel(List<CarModel> carModels);
}
