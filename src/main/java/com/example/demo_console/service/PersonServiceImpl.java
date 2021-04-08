package com.example.demo_console.service;

import com.example.demo_console.entity.Person;
import com.example.demo_console.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public void saveAllPersons(List<Person> persons) {
        personRepository.saveAll(persons);
    }
}
