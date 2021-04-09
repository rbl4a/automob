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

    List<Parking> findAllByPersonFirstName(String firstName);
    List<Parking> findAllByPersonLastName(String lastName);
    List<Parking> findAllByPersonPhoneNumber(String phoneNumber);

    List<Parking> findAllByParkingStartDate(LocalDate startDate);
    List<Parking> findAllByParkingEndDate(LocalDate endDate);

    List<Parking> finaAllParkingStartTime(LocalTime startTime);
    List<Parking> finaAllParkingEndTime(LocalTime endTime);

    void deleteParkingById(Long id);
    Parking getById(Long id);
    void saveParking(Parking parking);



}
