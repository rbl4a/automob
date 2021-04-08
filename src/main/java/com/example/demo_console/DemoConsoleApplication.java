package com.example.demo_console;

import com.example.demo_console.entity.*;
import com.example.demo_console.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoConsoleApplication implements CommandLineRunner {

    public DemoConsoleApplication(CarBrandRepository carBrandRepository,
                                  CarModelRepository carModelRepository,
                                  CarRepository carRepository,
                                  PersonRepository personRepository,
                                  ParkingRepository parkingRepository) {
        this.carBrandRepository = carBrandRepository;
        this.carModelRepository = carModelRepository;
        this.carRepository = carRepository;
        this.personRepository = personRepository;
        this.parkingRepository = parkingRepository;

    }

    public static void main(String[] args) {
        SpringApplication.run(DemoConsoleApplication.class, args);
    }

    final CarBrandRepository carBrandRepository;
    final CarModelRepository carModelRepository;
    final CarRepository carRepository;
    final PersonRepository personRepository;
    final ParkingRepository parkingRepository;

    @Override
    public void run(String... args) {


        List<CarBrand> brands = initBrands();
        List<Person> persons = initPerson();
        List<CarModel> models = initModels(brands);
        List<Car> cars = initCars(models, persons);
        List<Parking> parkings = initParking(cars);

        carBrandRepository.saveAll(brands);
        carModelRepository.saveAll(models);
        personRepository.saveAll(persons);
        carRepository.saveAll(cars);
        parkingRepository.saveAll(parkings);

        System.out.println("<<<<<<Поиск по марке авто>>>>>>");
        System.out.println(carRepository.findAllByCarModel_CarBrandBrandName("BMW"));

        System.out.println("<<<<<<Поиск авто по номеру владельца>>>>>>");
        System.out.println(carRepository.findAllByPerson_PhoneNumber("66-56"));

        System.out.println("<<<<<<Поиск владельца по номеру автомобиля>>>>>>");
        System.out.println(personRepository.findAllByCars(carRepository.findCarByGovNumber("а111мр")));
    }

    private List<CarBrand> initBrands() {
        CarBrand bmw = new CarBrand("BMW");
        CarBrand nissan = new CarBrand("Nissan");
        return Arrays.asList(bmw, nissan);
    }

    private List<Person> initPerson() {
        Person person1 = new Person("Ivan", "Ivanov", "66-56");
        Person person2 = new Person("Petr", "Petrov", "67-72");
        Person person3 = new Person("Ilia", "Ilyin", "78-21");
        Person person4 = new Person("Jhon", "Travolta", "42-35");
        return Arrays.asList(person1, person2, person3, person4);
    }

    private List<CarModel> initModels(List<CarBrand> brands) {
        CarModel x5 = new CarModel("X5", brands.get(0));
        CarModel x1 = new CarModel("X1", brands.get(0));
        CarModel skyline = new CarModel("Skyline", brands.get(1));
        CarModel patrol = new CarModel("Patrol", brands.get(1));
        return Arrays.asList(x5, x1, skyline, patrol);
    }

    private List<Car> initCars(List<CarModel> carModels, List<Person> persons) {
        Car car = new Car("а111мр", carModels.get(0), persons.get(0));
        Car car1 = new Car("а222мр", carModels.get(1), persons.get(1));
        Car car2 = new Car("а333мр", carModels.get(2), persons.get(2));
        Car car3 = new Car("а444мр", carModels.get(3), persons.get(3));
        return Arrays.asList(car, car1, car2, car3);
    }

    private List<Parking> initParking(List<Car> cars) {
        int year = 2021;
        int month = 4;
        int day = 8;
        int hour = 12;
        int minute = 10;
        Parking parking1 = new Parking(cars.get(0), LocalDateTime.of(year, month, day, hour, minute), LocalDateTime.of(year, month, day, hour + 1, minute));
        Parking parking2 = new Parking(cars.get(1), LocalDateTime.of(year, month, day, hour + 2, minute), LocalDateTime.of(year, month, day, hour + 5, minute));
        Parking parking3 = new Parking(cars.get(2), LocalDateTime.of(year, month, day + 1, hour, minute), LocalDateTime.of(year, month, day + 3, hour + 1, minute));
        Parking parkin4 = new Parking(cars.get(0), LocalDateTime.of(year, month, day + 4, hour, minute), LocalDateTime.of(year, month, day + 6, hour - 2, minute));
        return Arrays.asList(parking1, parking2, parking3, parkin4);
    }
}
