package com.example.demo_console.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

    @OneToMany (mappedBy = "carModel", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public static List<CarModel> initModels(List<CarBrand> brands) {
        CarModel x5 = new CarModel("X5", brands.get(0));
        CarModel x1 = new CarModel("X1", brands.get(0));
        CarModel skyline = new CarModel("Skyline", brands.get(1));
        CarModel patrol = new CarModel("Patrol", brands.get(1));
        return Arrays.asList(x5, x1, skyline, patrol);
    }
}
