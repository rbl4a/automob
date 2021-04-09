package com.example.demo_console.controller;

import com.example.demo_console.entity.Car;
import com.example.demo_console.entity.CarBrand;
import com.example.demo_console.entity.CarModel;
import com.example.demo_console.entity.Person;
import com.example.demo_console.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
                "\n\t /all - все автомобили" +
                "\n\t/add-car?number&modelName&brandName&firstName&lastName&phonePerson - добавление нового авто в БД" +
                "\n\t/delete-car/{id} - удаление авто по id";
    }

    @GetMapping(path = "/all")
    public ResponseEntity<String> findAllCar() {
        List<Car> allCar = carService.findAllCar();
        return new ResponseEntity<>(allCar.toString(), HttpStatus.OK);
    }

    @PutMapping(path = "/add-car")
    public RedirectView addCar(@RequestParam("number") String carNumber,
                               @RequestParam("modelName") String modelName,
                               @RequestParam("brandName") String brandName,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("phonePerson") String personPhone) {
        Person person;
        CarBrand carBrand;
        CarModel carModel;

        List<Car> carsByPhone = carService.findCarsByPhoneNumber(personPhone);
        if (!carsByPhone.isEmpty()) {
            person = carsByPhone.get(0).getPerson();
        } else {
            person = new Person(firstName, lastName, personPhone);
        }

        List<Car> carsByBrand = carService.findCarsByBrand(brandName);
        if (!carsByBrand.isEmpty()) {
            carBrand = carsByBrand.get(0).getCarModel().getCarBrand();
        } else {
            carBrand = new CarBrand(brandName);
        }

        List<Car> carsByModel = carService.findCarsByModel(modelName);
        if (!carsByModel.isEmpty()) {
            carModel = carsByModel.get(0).getCarModel();
        } else {
            carModel = new CarModel(modelName, carBrand);
        }

        Car car = new Car(carNumber, carModel, person);
        carService.saveCar(car);
        return new RedirectView("/car/all");
    }

    @DeleteMapping(path = "/delete-car/{id}")
    public void deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
    }
}
