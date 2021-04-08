package com.example.demo_console.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String govNumber;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;

    public Car(String govNumber, CarModel carModel, Person person) {
        this.govNumber = govNumber;
        this.carModel = carModel;
        this.person = person;
    }

    @OneToMany (mappedBy = "car", fetch = FetchType.EAGER)
    private List<Parking> parking;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "Car{" +
                "govNumber='" + govNumber + '\'' +
                '}';
    }

    public static List<Car> initCars(List<CarModel> carModels, List<Person> persons) {
        Car car = new Car("а111мр", carModels.get(0), persons.get(0));
        Car car1 = new Car("а222мр", carModels.get(1), persons.get(1));
        Car car2 = new Car("а333мр", carModels.get(2), persons.get(2));
        Car car3 = new Car("а444мр", carModels.get(3), persons.get(3));
        return Arrays.asList(car, car1, car2, car3);
    }
}
