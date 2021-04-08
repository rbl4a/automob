package com.example.demo_console.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@Entity
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

    @OneToMany (mappedBy = "carModel", fetch = FetchType.EAGER)
    private Collection<Car> carNumber;

    public CarModel(String modelName, CarBrand carBrand) {
        this.modelName = modelName;
        this.carBrand = carBrand;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "modelName='" + modelName + '\'' +
                '}';
    }
}
