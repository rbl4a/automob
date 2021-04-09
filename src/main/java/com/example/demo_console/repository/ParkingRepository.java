package com.example.demo_console.repository;

import com.example.demo_console.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> findAllByCar_CarModel_CarBrand(String brandName);
    List<Parking> findAllByCar_CarModel_ModelName(String modelName);
    List<Parking> findAllByCar_GovNumber(String governmentNumber);

    List<Parking> findAllByCar_Person_FirstName(String firstName);
    List<Parking> findAllByCar_Person_LastName(String lastName);
    List<Parking> findAllByCar_PersonPhoneNumber(String phoneNumber);

    List<Parking> findAllByStartDateAfter(LocalDate startDateAfter);
    List<Parking> findAllByEndDateBefore(LocalDate endDateBefore);

    List<Parking> findAllByStartTimeAfter(LocalTime startTimeAfter);
    List<Parking> findAllByEndTimeBefore(LocalTime endTimeBefore);
}
