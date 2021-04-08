package com.example.demo_console.service;

import com.example.demo_console.entity.CarBrand;
import com.example.demo_console.repository.CarBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;

    public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    @Override
    public List<CarBrand> findAllCarBrand() {
        return carBrandRepository.findAll();
    }

    @Override
    public void saveAllCarBrand(List<CarBrand> carBrands) {
        carBrandRepository.saveAll(carBrands);
    }
}
