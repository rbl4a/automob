package com.example.demo_console.controller;

import com.example.demo_console.entity.*;
import com.example.demo_console.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Контроллер для инициализации таблиц базы данных.
 * Используется один раз совместно с параметом spring.jpa.hibernate.ddl-auto=none
 * в файле application.properties.
 * После инициализации изменить параметр на none (не для H2).
 */
@RestController
@RequestMapping("/init")
public class InitializationController {
    private final CarBrandService carBrandService;
    private final CarModelService carModelService;
    private final PersonService personService;
    private final CarService carService;
    private final ParkingService parkingService;


    public InitializationController(CarBrandService carBrandService,
                                    CarModelService carModelService,
                                    PersonService personService,
                                    CarService carService,
                                    ParkingService parkingService) {
        this.carBrandService = carBrandService;
        this.carModelService = carModelService;
        this.personService = personService;
        this.carService = carService;
        this.parkingService = parkingService;
    }

    /**
     * Инициализация таблиц БД
     * @return redirect в корень
     */
    @GetMapping
    public RedirectView initializationDB() {
        List<CarBrand> carBrands = CarBrand.initBrands();
        List<Person> persons = Person.initPerson();
        List<CarModel> carModels = CarModel.initModels(carBrands);
        List<Car> cars = Car.initCars(carModels, persons);
        List<Parking> parking = Parking.initParking(cars);

        carBrandService.saveAllCarBrand(carBrands);
        personService.saveAllPersons(persons);
        carModelService.saveAllCarModel(carModels);
        carService.saveAllCars(cars);
        parkingService.saveAllParking(parking);

        return new RedirectView("/");
    }
}
