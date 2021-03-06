package com.example.demo_console.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@JsonView
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
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
        if (endDate != null && endDate.isBefore(startDate)) throw new IllegalArgumentException("End date shouldn't before start date");
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        if (startDate.equals(endDate) && endTime.isBefore(startTime)) throw new IllegalArgumentException("End time shouldn't before start time");
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = calculatePriceOfDays(startDate, endDate);
    }

    private BigDecimal calculatePriceOfDays(LocalDate startDate, LocalDate endDate) {
        if (endDate == null) {
            return BigDecimal.valueOf(0);
        }
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

    public void updatePrice() {
        if (this.endDate != null) {
            setPrice(calculatePriceOfDays(startDate, endDate));
        }
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

    public static List<Parking> initParking(List<Car> cars) {
        LocalDate localDate1 = LocalDate.of(2021, 4,8 );
        LocalDate localDate2 = LocalDate.of(2021, 4,10 );

        LocalTime localTime1 = LocalTime.of(12, 10);
        LocalTime localTime2 = LocalTime.of(12, 20);
        LocalTime localTime3 = LocalTime.of(14, 20);
        LocalTime localTime4 = LocalTime.of(15, 30);


        Parking parking1 = new Parking(cars.get(0), localDate1, localDate1, localTime1, localTime2);
        Parking parking2 = new Parking(cars.get(1),localDate1, null, localTime1, null);
        Parking parking3 = new Parking(cars.get(2),localDate1, localDate1, localTime2, localTime3);
        Parking parkin4 = new Parking(cars.get(3), localDate2, localDate2, localTime3, localTime4);
        Parking parkin5 = new Parking(cars.get(1), localDate2, localDate2, localTime3, localTime4);

        return Arrays.asList(parking1, parking2, parking3, parkin4, parkin5);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) throw new IllegalArgumentException("End date shouldn't before start date");
        this.endDate = endDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        if (startDate.equals(endDate) && endTime.isBefore(startTime)) throw new IllegalArgumentException("End time shouldn't before start time");
        this.endTime = endTime;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
