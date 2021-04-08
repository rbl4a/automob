package com.example.demo_console.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "car_number")
public class CarGovNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String govNumber;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;
}
