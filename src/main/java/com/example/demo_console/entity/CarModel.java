package com.example.demo_console.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class CarModel {

    @Id
    @GeneratedValue
    private int id;

    private String modelName;

    public CarModel() {
    }

    public CarModel(String modelName) {
        this.modelName = modelName;
    }
}
