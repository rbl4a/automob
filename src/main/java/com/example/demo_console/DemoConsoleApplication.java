package com.example.demo_console;

import com.example.demo_console.entity.CarBrand;
import com.example.demo_console.entity.CarModel;
import com.example.demo_console.repository.CarBrandRepository;
import com.example.demo_console.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoConsoleApplication implements CommandLineRunner {

    public DemoConsoleApplication(CarBrandRepository carBrandRepository, CarModelRepository carModelRepository) {
        this.carBrandRepository = carBrandRepository;
        this.carModelRepository = carModelRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoConsoleApplication.class, args);
    }

    final CarBrandRepository carBrandRepository;
    final CarModelRepository carModelRepository;

    @Override
    public void run(String... args) throws Exception {
        CarBrand bmw = new CarBrand("BMW");
        CarBrand nissan = new CarBrand("Nissan");

        CarModel x5 = new CarModel("X5");
        CarModel x1 = new CarModel("X1");
        CarModel skyline = new CarModel("Skyline");
        CarModel patrol = new CarModel("Patrol");

        bmw.setCarModel(Arrays.asList(x5, x1));
        nissan.setCarModel(Arrays.asList(skyline, patrol));

        List<CarBrand> brands = Arrays.asList(bmw, nissan);

        carBrandRepository.saveAll(brands);

        System.out.println("<<<<<<" + carModelRepository.findAll());

    }
}
