package com.example.demo_console.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Car car;

    @Column(name = "start")
    private LocalDateTime startTime;

    @Column(name = "end")
    private LocalDateTime endTime;

    private BigDecimal price;

    private static final BigDecimal PRICE_OF_HOUR = BigDecimal.valueOf(25);
    private static final BigDecimal PRICE_OF_DAY = BigDecimal.valueOf(150);

    public Parking(Car car, LocalDateTime startTime, LocalDateTime endTime) {
        this.car = car;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = calculatePrice(startTime, endTime);
    }

    private BigDecimal calculatePrice(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDate endDate = endTime.toLocalDate();
        LocalDate startDate = startTime.toLocalDate();
        BigDecimal resultPrice;
        if (endDate.equals(startDate)) {
            int resultHour = endTime.toLocalTime().getHour() - startTime.toLocalTime().getHour();
            resultHour = resultHour == 0 ? 1 : resultHour;
            resultPrice = BigDecimal.valueOf(resultHour).multiply(PRICE_OF_HOUR);
        } else {
            int resultDay = endDate.getDayOfYear() - startDate.getDayOfYear();
            resultPrice = BigDecimal.valueOf(resultDay).multiply(PRICE_OF_DAY);
        }
        return resultPrice;
    }

}
