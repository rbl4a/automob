package com.example.demo_console.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class CarBrand {

    @Id
    @GeneratedValue
    private int id;

    private String brandName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private List<CarModel> carModel;

    public CarBrand() {
    }

    public CarBrand(String brandName) {
        this.brandName = brandName;
    }
}
