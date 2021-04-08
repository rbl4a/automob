package com.example.demo_console.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name = "car_model")
public class CarModel {

    @Id
    @GeneratedValue
    @Column(name = "model_id")
    private int id;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private CarBrand carBrand;

    public CarModel(String modelName, CarBrand carBrand) {
        this.modelName = modelName;
        this.carBrand = carBrand;
    }
}
