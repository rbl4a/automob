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
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    private int id;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CarModel> carModel;


    public CarBrand(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "brandName='" + brandName + '\'' +
                '}';
    }
}
