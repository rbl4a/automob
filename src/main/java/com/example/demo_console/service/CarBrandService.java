package com.example.demo_console.service;

import com.example.demo_console.entity.CarBrand;

import java.util.List;

public interface CarBrandService {
    List<CarBrand> findAllCarBrand();
    void saveAllCarBrand(List<CarBrand> carBrands);
}
