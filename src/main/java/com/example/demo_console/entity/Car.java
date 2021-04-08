package com.example.demo_console.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "car_number")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String govNumber;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;

    public Car(String govNumber, CarModel carModel) {
        this.govNumber = govNumber;
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "govNumber='" + govNumber + '\'' +
                '}';
    }
}
