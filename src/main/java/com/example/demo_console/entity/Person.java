package com.example.demo_console.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
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

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Car> cars;

    public Person(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static List<Person> initPerson() {
        Person person1 = new Person("Ivan", "Ivanov", "66-56");
        Person person2 = new Person("Petr", "Petrov", "67-72");
        Person person3 = new Person("Ilia", "Ilyin", "78-21");
        Person person4 = new Person("Jhon", "Travolta", "42-35");
        return Arrays.asList(person1, person2, person3, person4);
    }
}
