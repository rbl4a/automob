package com.example.demo_console.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car_model")
public class CarModel {

    @Id
    @GeneratedValue
    @Column(name = "model_id")
    private Long id;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private CarBrand carBrand;

    @JsonIgnore
    @OneToMany (mappedBy = "carModel", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Car> carNumber;

    public CarModel(String modelName, CarBrand carBrand) {
        this.modelName = modelName;
        this.carBrand = carBrand;
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

    public static List<CarModel> initModels(List<CarBrand> brands) {
        CarModel x5 = new CarModel("X5", brands.get(0));
        CarModel x1 = new CarModel("X1", brands.get(0));
        CarModel skyline = new CarModel("Skyline", brands.get(1));
        CarModel patrol = new CarModel("Patrol", brands.get(1));
        return Arrays.asList(x5, x1, skyline, patrol);
    }

    public static void main(String[] args) {
        CarModel carModel = new CarModel("X5", new CarBrand("BMW"));
        System.out.println(carModel);
    }
}
