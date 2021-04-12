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

/**
 * Контроллер управления записями авто в БД
 */
@RestController
@RequestMapping(path = "/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Начальная страница каталога car
     * @return спискок подкаталогов
     */
    @GetMapping(path = "")
    public String helloPage() {
        return "Параметры:" +
                "\n\t /all - все автомобили" +
                "\n\t/add-car?number&modelName&brandName&firstName&lastName&phonePerson - добавление нового авто в БД" +
                "\n\t/delete-car/{id} - удаление авто по id";
    }

    /**
     * Список всех автомобилей с данными о вледельце
     * @return список всех авто
     */
    @GetMapping(path = "/all")
    public ResponseEntity<String> findAllCar() {
        List<Car> allCar = carService.findAllCar();
        return new ResponseEntity<>(allCar.toString(), HttpStatus.OK);
    }

    /**
     * Добавление нового авто в БД
     * @param carNumber номер авто
     * @param car автомобиль для добавления
     * @return redirect на {@link CarController#findAllCar()}
     */
    @PutMapping(path = "/add-car/{carNumber}")
    public RedirectView addCar(@PathVariable String carNumber, @RequestBody Car car) {
        Person person;
        CarBrand carBrand;
        CarModel carModel;

        List<Car> carsByPhone = carService.findCarsByPhoneNumber(car.getPerson().getPhoneNumber());
        if (!carsByPhone.isEmpty()) {
            person = carsByPhone.get(0).getPerson();
        } else {
            person = new Person(car.getPerson().getFirstName(), car.getPerson().getLastName(), car.getPerson().getPhoneNumber());
        }

        List<Car> carsByBrand = carService.findCarsByBrand(car.getCarModel().getCarBrand().getBrandName());
        if (!carsByBrand.isEmpty()) {
            carBrand = carsByBrand.get(0).getCarModel().getCarBrand();
        } else {
            carBrand = new CarBrand(car.getCarModel().getCarBrand().getBrandName());
        }

        List<Car> carsByModel = carService.findCarsByModel(car.getCarModel().getModelName());
        if (!carsByModel.isEmpty()) {
            carModel = carsByModel.get(0).getCarModel();
        } else {
            carModel = new CarModel(car.getCarModel().getModelName(), carBrand);
        }

        Car resultCar = new Car(carNumber, carModel, person);
        carService.saveCar(resultCar);
        return new RedirectView("/car/all");
    }

    /**
     * Удаление авто по id
     * @param id авто
     */
    @DeleteMapping(path = "/delete-car/{id}")
    public void deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
    }
}
