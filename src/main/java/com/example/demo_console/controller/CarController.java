package com.example.demo_console.controller;

import com.example.demo_console.entity.Car;
import com.example.demo_console.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = "")
    public String helloPage() {
        return "Параметры:" +
                "\n\t /all - все автомобили";
    }

    @GetMapping(path = "/all")
    public ResponseEntity<String> findAllCar() {
        List<Car> allCar = carService.findAllCar();
        return new ResponseEntity<>(allCar.toString(), HttpStatus.OK);
    }
}
