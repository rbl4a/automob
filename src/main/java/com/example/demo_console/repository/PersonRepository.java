package com.example.demo_console.repository;

import com.example.demo_console.entity.Car;
import com.example.demo_console.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findAllByCars(Car car);
}
