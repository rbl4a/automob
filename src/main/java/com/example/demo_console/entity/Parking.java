package com.example.demo_console.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    private BigDecimal price;

    private static final BigDecimal PRICE_OF_HOUR = BigDecimal.valueOf(25);
    private static final BigDecimal PRICE_OF_DAY = BigDecimal.valueOf(150);


    public Parking(Car car, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = calculatePriceOfDays(startDate, endDate);
    }

    private BigDecimal calculatePriceOfDays(LocalDate startDate, LocalDate endDate) {
        if (endDate.equals(startDate)) {
            return calculatePriceOfHours(this.startTime, this.endTime);
        } else {
            int resultDay = endDate.getDayOfYear() - startDate.getDayOfYear();
            return BigDecimal.valueOf(resultDay).multiply(PRICE_OF_DAY);
        }
    }

    private BigDecimal calculatePriceOfHours(LocalTime startTime, LocalTime endTime) {
        int resultHour = endTime.getHour() - startTime.getHour();
        resultHour = resultHour == 0 ? 1 : resultHour;
        return BigDecimal.valueOf(resultHour).multiply(PRICE_OF_HOUR);
    }

    @Override
    public String toString() {
        return "Parking{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                '}';
    }
}
