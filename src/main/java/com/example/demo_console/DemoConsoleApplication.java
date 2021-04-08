package com.example.demo_console;

import com.example.demo_console.entity.*;
import com.example.demo_console.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        LocalDate localDate1 = LocalDate.of(2021, 4,8 );
        LocalDate localDate2 = LocalDate.of(2021, 4,10 );

        LocalTime localTime1 = LocalTime.of(12, 10);
        LocalTime localTime2 = LocalTime.of(12, 20);
        LocalTime localTime3 = LocalTime.of(14, 20);
        LocalTime localTime4 = LocalTime.of(15, 30);


        Parking parking1 = new Parking(cars.get(0), localDate1, localDate1, localTime1, localTime2);
        Parking parking2 = new Parking(cars.get(1),localDate1, localDate2, localTime1, localTime3);
        Parking parking3 = new Parking(cars.get(2),localDate1, localDate1, localTime2, localTime3);
        Parking parkin4 = new Parking(cars.get(3), localDate2, localDate2, localTime3, localTime4);
        return Arrays.asList(parking1, parking2, parking3, parkin4);
    }
}
