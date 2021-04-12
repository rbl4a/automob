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
        return parkingRepository.findAllByCar_CarModel_ModelName(modelName);
    }

    @Override
    public List<Parking> findAllByCarGovNumber(String governmentNumber) {
        return parkingRepository.findAllByCar_GovNumber(governmentNumber);
    }

    @Override
    public List<Parking> findAllByPersonPhoneNumber(String phoneNumber) {
        return parkingRepository.findAllByCar_PersonPhoneNumber(phoneNumber);
    }

    @Override
    public List<Parking> findAllByParkingStartDateAfter(LocalDate startDate) {
        return parkingRepository.findAllByStartDateAfter(startDate);
    }

    @Override
    public List<Parking> findAllByParkingEndDateBefore(LocalDate endDate) {
        return parkingRepository.findAllByEndDateBefore(endDate);
    }

    @Override
    public List<Parking> finaAllParkingStartTimeAfter(LocalTime startTime) {
        return parkingRepository.findAllByStartTimeAfter(startTime);
    }

    @Override
    public List<Parking> finaAllParkingEndTimeBefore(LocalTime endTime) {
        return parkingRepository.findAllByEndTimeBefore(endTime);
    }

    @Override
    public void deleteParkingById(Long id) {
        parkingRepository.deleteById(id);
    }

    @Override
    public Parking getById(Long id) {
        return parkingRepository.getOne(id);
    }

    @Override
    public void saveParking(Parking parking) {
        parkingRepository.save(parking);
    }

    @Override
    public List<Parking> findAllParkingCarId(Long id) {
        return parkingRepository.findAllByCarId(id);
    }

}
