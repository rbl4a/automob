package com.example.demo_console.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Car> cars;

    public Person(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Person> initPerson() {
        Person person1 = new Person("Ivan", "Ivanov", "66-56");
        Person person2 = new Person("Petr", "Petrov", "67-72");
        Person person3 = new Person("Ilia", "Ilyin", "78-21");
        Person person4 = new Person("Jhon", "Travolta", "42-35");
        return Arrays.asList(person1, person2, person3, person4);
    }

    public static void main(String[] args) {
        Person person1 = new Person("Ivan", "Ivanov", "66-56");
        System.out.println(person1);
    }
}
