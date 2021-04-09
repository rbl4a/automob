package com.example.demo_console.entity;

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
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    private int id;

    @Column(name = "brand_name", unique = true)
    private String brandName;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CarModel> carModel;


    public CarBrand(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "\nCarBrand{" +
                "brandName='" + brandName + '\'' +
                '}';
    }

    public static List<CarBrand> initBrands() {
        CarBrand bmw = new CarBrand("BMW");
        CarBrand nissan = new CarBrand("Nissan");
        return Arrays.asList(bmw, nissan);
    }
}
