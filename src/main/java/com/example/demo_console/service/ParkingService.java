package com.example.demo_console.service;

import com.example.demo_console.entity.Parking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ParkingService {
    List<Parking> findAllParking();
    void saveAllParking(List<Parking> parking);

    List<Parking> findAllByCarBrand(String brandName);
    List<Parking> findAllByCarModel(String modelName);
    List<Parking> findAllByCarGovNumber(String governmentNumber);

    List<Parking> findAllByPersonPhoneNumber(String phoneNumber);

    List<Parking> findAllByParkingStartDateAfter(LocalDate startDate);
    List<Parking> findAllByParkingEndDateBefore(LocalDate endDate);

    List<Parking> finaAllParkingStartTimeAfter(LocalTime startTime);
    List<Parking> finaAllParkingEndTimeBefore(LocalTime endTime);

    void deleteParkingById(Long id);
    Parking getById(Long id);
    void saveParking(Parking parking);
    List<Parking> findAllParkingCarId(Long id);



}
