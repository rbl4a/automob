package com.example.demo_console.service;

import com.example.demo_console.entity.Parking;
import com.example.demo_console.repository.ParkingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;

    @Override
    public List<Parking> findAllParking() {
        return parkingRepository.findAll();
    }

    @Override
    public void saveAllParking(List<Parking> parking) {
        parkingRepository.saveAll(parking);
    }

    @Override
    public List<Parking> findAllByCarBrand(String brandName) {
        return parkingRepository.findAllByCar_CarModel_CarBrand(brandName);
    }

    @Override
    public List<Parking> findAllByCarModel(String modelName) {
        return null;
    }

    @Override
    public List<Parking> findAllByCarGovNumber(String governmentNumber) {
        return parkingRepository.findAllByCar_GovNumber(governmentNumber);
    }

    @Override
    public List<Parking> findAllByPersonFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Parking> findAllByPersonLastName(String lastName) {
        return null;
    }

    @Override
    public List<Parking> findAllByPersonPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<Parking> findAllByParkingStartDate(LocalDate startDate) {
        return null;
    }

    @Override
    public List<Parking> findAllByParkingEndDate(LocalDate endDate) {
        return null;
    }

    @Override
    public List<Parking> finaAllParkingStartTime(LocalTime startTime) {
        return null;
    }

    @Override
    public List<Parking> finaAllParkingEndTime(LocalTime endTime) {
        return null;
    }

}
