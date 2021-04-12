package com.example.demo_console.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

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
    private Long id;

    @Column(name = "brand_name", unique = true)
    private String brandName;

    @JsonIgnore
    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CarModel> carModel;


    public CarBrand(String brandName) {
        this.brandName = brandName;
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

    public static List<CarBrand> initBrands() {
        CarBrand bmw = new CarBrand("BMW");
        CarBrand nissan = new CarBrand("Nissan");
        return Arrays.asList(bmw, nissan);
    }

    public static void main(String[] args) {
        CarBrand toyota = new CarBrand("Toyota");
        System.out.println(toyota);
    }
}
