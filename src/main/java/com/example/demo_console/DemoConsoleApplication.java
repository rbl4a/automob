package com.example.demo_console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsoleApplication.class, args);
    }



    /*public void run(String... args) {

        *//*System.out.println("<<<<<<Поиск по марке авто>>>>>>");
        System.out.println(carRepository.findAllByCarModel_CarBrandBrandName("BMW"));

        System.out.println("<<<<<<Поиск авто по номеру владельца>>>>>>");
        System.out.println(carRepository.findAllByPerson_PhoneNumber("66-56"));

        System.out.println("<<<<<<Поиск владельца по номеру автомобиля>>>>>>");
        System.out.println(personRepository.findAllByCars(carRepository.findCarByGovNumber("а111мр")));

        System.out.println("<<<<<<Все записи парковки>>>>>>");
        System.out.println(parkingRepository.findAll());*//*
    }*/
}
