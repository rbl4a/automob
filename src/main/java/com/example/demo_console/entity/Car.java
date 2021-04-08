package com.example.demo_console.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String govNumber;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;

    public Car(String govNumber, CarModel carModel, Person person) {
        this.govNumber = govNumber;
        this.carModel = carModel;
        this.person = person;
    }

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "Car{" +
                "govNumber='" + govNumber + '\'' +
                '}';
    }
}
