package com.example.demo_console.service;

import com.example.demo_console.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAllPersons();
    void saveAllPersons(List<Person> persons);
}
