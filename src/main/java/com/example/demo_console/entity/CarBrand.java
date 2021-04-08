package com.example.demo_console.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    private int id;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL)
    private List<CarModel> carModel;

    public CarBrand() {
    }

    public CarBrand(String brandName) {
        this.brandName = brandName;
    }
}
