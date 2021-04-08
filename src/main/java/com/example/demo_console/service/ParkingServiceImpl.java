package com.example.demo_console.service;

import com.example.demo_console.entity.Parking;
import com.example.demo_console.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingServiceImpl(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public List<Parking> findAllParking() {
        return parkingRepository.findAll();
    }

    @Override
    public void saveAllParking(List<Parking> parking) {
        parkingRepository.saveAll(parking);
    }
}
